def mergesort(A, p, r):
    """
    Sorts the list A in place from index p to index r
    - not using list.pop() to iterate values
    - not creating a new list for saving result, modifying the original one instead
    - not using float('inf') as sentinel values
    Args:
        A (_type_): list of numbers
        p (_type_): start index
        r (_type_): end index
    """
    if(p < r):
        q = (p+r)//2
        mergesort(A, p, q)
        mergesort(A, q+1, r)
        merge(A, p, q, r)

def merge(A, p, q, r):
    """merge two sorted sublists into one sorted list

    Args:
        A (_type_): list of numbers
        p (_type_): start index
        q (_type_): middle index
        r (_type_): end index
    """
    L = A[p:q+1]
    R = A[q+1:r+1]
    i = 0
    j = 0
    k = p
    while i < len(L) and j < len(R):
        if(L[i] < R[j]):
            A[k] = L[i]
            i += 1
        else:
            A[k] = R[j]
            j += 1
        k += 1
    if i < len(L):
        A[k:r+1] = L[i:]

if __name__ == "__main__":
    items = [6, 2, 9, 1, 7, 3, 4, 5, 8]
    mergesort(items, 0, len(items)-1)
    print(items)
    assert items == [1, 2, 3, 4, 5, 6, 7, 8, 9]
