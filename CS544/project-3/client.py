import sys
import grpc
import csv
import threading
import modelserver_pb2
import modelserver_pb2_grpc

class ClientWorker(threading.Thread):

    lock = threading.Lock()
    
    def __init__(self, stub, csv_file):
        super(ClientWorker, self).__init__()
        self.stub = stub
        self.csv_file = csv_file
        self.hits = 0
        self.total = 0

    def run(self):
        with open(self.csv_file, 'r') as f:
            reader = csv.reader(f)
            for row in reader:
                values = [float(val) for val in row]
                response = self.stub.Predict(modelserver_pb2.PredictRequest(X=values))
                with ClientWorker.lock:
                    if response.hit:
                        self.hits += 1
                    self.total += 1

        print(f"Thread for {self.csv_file} finished. Hits: {self.hits}, Total: {self.total}")

def main():
    if len(sys.argv) < 4:
        print("Usage: python3 client.py <PORT> <COEF> <THREAD1-WORK.csv> <THREAD2-WORK.csv> ...")
        sys.exit(1)

    port = sys.argv[1]
    coefs = [float(val) for val in sys.argv[2].split(',')]
    csv_files = sys.argv[3:]

    # Connect to the gRPC server
    channel = grpc.insecure_channel(f"localhost:{port}")
    stub = modelserver_pb2_grpc.ModelServerStub(channel)

    # Set the coefficients
    stub.SetCoefs(modelserver_pb2.SetCoefsRequest(coefs=coefs))

    # Create and start the threads
    threads = []
    for csv_file in csv_files:
        thread = ClientWorker(stub, csv_file)
        thread.start()
        threads.append(thread)

    # Wait for all threads to finish
    hit_count = 0
    total_count = 0
    for thread in threads:
        thread.join()
        hit_count += thread.hits
        total_count += thread.total
        

    print("All threads completed.")
    if total_count != 0:
        hit_rate = hit_count / total_count
    else:
        hit_rate = 0

    print("Overall hit rate: ")
    print(hit_rate)

if __name__ == "__main__":
    main()
