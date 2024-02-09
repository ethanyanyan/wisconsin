import torch
import threading
import modelserver_pb2, modelserver_pb2_grpc
import grpc
from concurrent import futures

class PredictionCache:
    def __init__(self):
        self.coefs = None
        self.cache = []
        self.max_cache = 10
        self.lock = threading.Lock()

    def SetCoefs(self, coefs):
        with self.lock:
            if not isinstance(coefs, torch.Tensor):
                raise ValueError("coefs must be a PyTorch tensor")
            if coefs.dim() != 2 or coefs.shape[0] != coefs.shape[1] != 1:
                raise ValueError("coefs should be a 1D tensor")
            self.cache = []
            self.coefs = coefs

    def Predict(self, X):
        with self.lock:
            X = torch.round(X, decimals=4)
            hitBool = False
            if self.coefs is None:
                return None, hitBool
            if not isinstance(X, torch.Tensor):
                raise ValueError("X must be a PyTorch tensor")
            if X.dim() != 2:
                raise ValueError("X should be a 2D tensor")
            if X.shape[1] != self.coefs.shape[0]:
                return None, hitBool
            X_tuple = tuple(X.flatten().tolist())
    
            # implement LRU caching
            if X_tuple in self.cache:
                hitBool = True
                self.cache.remove(X_tuple)
                self.cache.append(X_tuple)
            else:
                if len(self.cache) < self.max_cache:
                    self.cache.append(X_tuple)
                else:
                    self.cache.pop(0)
                    self.cache.append(X_tuple)

            y = X @ self.coefs
            return y, hitBool


class ModelServer(modelserver_pb2_grpc.ModelServerServicer):

    def __init__(self):
        self.prediction_cache = PredictionCache()

    def SetCoefs(self, request, context):
        try:
            tensor_coefs = torch.tensor(request.coefs).view(-1, 1)
            self.prediction_cache.SetCoefs(tensor_coefs)
            return modelserver_pb2.SetCoefsResponse(error="")
        except Exception as e:
            return modelserver_pb2.SetCoefsResponse(error=str(e))

    def Predict(self, request, context):
        try:
            tensor_X = torch.tensor(request.X).view(1, -1)
            y, hit = self.prediction_cache.Predict(tensor_X)
            
            if y is None:
                raise ValueError("Coefficient is not set.")
            
            return modelserver_pb2.PredictResponse(y=y.item(), hit=hit, error="")
        except Exception as e:
            return modelserver_pb2.PredictResponse(y=0, hit=False, error=str(e))
        

def serve():
    server = grpc.server(futures.ThreadPoolExecutor(max_workers=4), options=(('grpc.so_reuseport', 0),))
    modelserver_pb2_grpc.add_ModelServerServicer_to_server(ModelServer(), server)
    server.add_insecure_port("[::]:5440", )
    server.start()
    server.wait_for_termination()

if __name__ == "__main__":
    serve()
