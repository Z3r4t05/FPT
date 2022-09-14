import heapq as heap

def korder(iterable, k):
    h = []
    for value in iterable:
        heap.heappush(h, value)
    a = []
    for i in range(k):
        if i == (k - 1):
            print(f"before removing the root {h}") 
        a.append(heap.heappop(h))
        if i == (k - 1):
            print(f"after removing the root {h}") 
    
    heap.heappop(h)
    
    print(f"heap = {a[::-1]}")
    print(f"{k}th-order = {a[-1]}")
korder([9, 12, 8, 7, 6, 5, 4, 2, 11], 5)
    