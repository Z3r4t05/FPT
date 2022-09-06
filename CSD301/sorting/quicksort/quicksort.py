def qsort(arr):
    """_summary_
    1. Pick the first element of array arr[0] as pivot
    2. qsort those elements of array which are less than pivot with List Comprehension
    3. qsort those elements of array which are larger than pivot with List Comprehension
    Args:
        arr (_type_): array to be sorted

    Returns:
        _type_: array sorted
    """
    if len(arr) <= 1:
        return arr
    else:
        return qsort([x for x in arr[1:] if x < arr[0]]) + [arr[0]] + qsort([x for x in arr[1:] if x >= arr[0]])

array = [97, 200, 100, 101, 211, 107]

print(qsort(array))