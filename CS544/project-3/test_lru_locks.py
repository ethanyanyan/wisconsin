import numpy as np
import torch
import threading

def test_cache(cache, inputs_list):
    for inputs in inputs_list:
        (y, hit) = cache.Predict(X=inputs)
        print(f"Thread {threading.current_thread().name}: y={y.item()}, hit={hit}")

prediction_cache = PredictionCache()

coefs = torch.Tensor(np.array([1, 2, 3], dtype=np.float32).reshape(-1, 1))
prediction_cache.SetCoefs(coefs=coefs)

# Create 10 unique inputs
inputs_list = [torch.Tensor(np.array([i, i+1, i+2], dtype=np.float32).reshape(1, -1)) for i in range(10)]

# create multiple threads to test the locking mechanism
threads = []
for i in range(5):  # creating 5 threads
    t = threading.Thread(target=test_cache, args=(prediction_cache, inputs_list), name=f"Thread-{i}")
    t.start()
    threads.append(t)

# Waiting for all threads to finish
for t in threads:
    t.join()

# Now add another input, which should evict the least recently used input from the cache
new_input = torch.Tensor(np.array([11, 12, 13], dtype=np.float32).reshape(1, -1))
(y, hit) = prediction_cache.Predict(X=new_input)
print(f"y={y.item()}, hit={hit}")

# Verify that the first input (index 0) is no longer in the cache
(y, hit) = prediction_cache.Predict(X=inputs_list[0])
print(f"y={y.item()}, hit={hit}")

