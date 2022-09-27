import heapq as heap

def korder(iterable, k):
    h = []
    for value in iterable:
        heap.heappush(h, value)
    a = []
    for i in range(k):
       # if i == (k - 1):
            # print(f"before removing the root {h}") 
        a.append(heap.heappop(h))
        if len(a) == (k - 1):
            print(f"{k-1}th-order statistic: {a[-1]}")
        if i == (k - 1):
            # print(f"after removing the root {h}") 
            s = ""
            for i in h:
                s += str(i) + " "
            print(f"{s}")
    
    heap.heappop(h)
    
   # print(f"heap = {a[::-1]}")
    print(f"{k}th-order statistic: {a[-1]}")
k = input("k = ")
a = [9, 12, 8, 7, 6, 5, 4, 2, 11]
s = ""
for i in a:
    s += str(i) + " "
print(s)
korder(a, int(k))
 