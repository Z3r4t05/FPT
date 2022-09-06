import random

def qsort(xs, fst, lst):
    '''
    Sort the range xs[fst, lst] in-place with vanilla QuickSort

    :param xs:  the list of numbers to sort
    :param fst: the first index from xs to begin sorting from,
                must be in the range [0, len(xs))
    :param lst: the last index from xs to stop sorting at
                must be in the range [fst, len(xs))
    :return:    nothing, the side effect is that xs[fst, lst] is sorted
    '''
    if fst >= lst:
        return

    i, j = fst, lst
    pivot = xs[random.randint(fst, lst)]

    while i <= j:
        while xs[i] < pivot:
            i += 1
        while xs[j] > pivot:
            j -= 1

        if i <= j:
            xs[i], xs[j] = xs[j], xs[i]
            i, j = i + 1, j - 1
    qsort(xs, fst, j)
    qsort(xs, i, lst)