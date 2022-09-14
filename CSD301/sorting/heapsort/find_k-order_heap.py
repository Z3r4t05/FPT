def min_heapify(A, k):
    l = left(k)
    r = right(k)
    if l<len(A) and A[l] < A[k]:
        smallest = l
    else: 
        smallest = k
    if r < len(A) and A[r] < A[smallest]:
        smallest = r
    if smallest != k:
        A[k], A[smallest] = A[smallest], A[k]
        min_heapify(A, smallest)
    
    
def left(k):
    return 2*k + 1

def right(k):
    return 2*k + 2

def build_min_heap(A):
    n = int((len(A)//2)-1)
    for k in range(n, -1, -1):
        min_heapify(A,k)

A = [9, 12, 8, 7, 6, 5, 4, 2, 11]
heap = []

for i in range(5):
    build_min_heap(A)
    print(f"A = {A}")
    A[0], A[-1] = A[-1], A[0]
    heap = [A[-1]] + heap
    A = A[:-1]
    print(f"A = {A}")
    print(f"heap = {heap}")
    

