from calendar import c
from this import d
import numpy as np
import random
import operator
from functools import wraps
import time


def timeit(func):
    @wraps(func)
    def timeit_wrapper(*args, **kwargs):
        start_time = time.perf_counter()
        result = func(*args, **kwargs)
        end_time = time.perf_counter()
        total_time = end_time - start_time
        print(f'Function {func.__name__} {args} {kwargs} took {total_time}')
        return result
    return timeit_wrapper


@timeit
def qsort0(xs, key=lambda x: x, reverse=False):
    def qsort(fst, lst):
        if fst >= lst:
            return

        i, j = fst, lst
        pivot = ys[random.randint(fst, lst)]

        while i <= j:
            while cmp(ys[i], pivot):
                i += 1
            while cmp(pivot, ys[j]):
                j -= 1

            if i <= j:
                xs[i], xs[j] = xs[j], xs[i]
                ys[i], ys[j] = ys[j], ys[i]
                i, j = i + 1, j - 1

        qsort(fst, j)
        qsort(i, lst)

    ys = [key(x) for x in xs]
    cmp = operator.gt if reverse else operator.lt

    qsort(0, len(xs) - 1)


@timeit
def qsort1(xs, key=lambda x: x, reverse=False):
    def qsort(fst, lst):
        if fst >= lst:
            return

        i, j = fst, lst
        pivot = ys[random.randint(fst, lst)]

        while i < j:
            while cmp(ys[i], pivot):
                i += 1
            while cmp(pivot, ys[j]):
                j -= 1

            if i > j:
                break

            xs[i], xs[j] = xs[j], xs[i]
            ys[i], ys[j] = ys[j], ys[i]
            i, j = i + 1, j - 1

        qsort(fst, j)
        qsort(i, lst)

    ys = [key(x) for x in xs]
    cmp = operator.gt if reverse else operator.lt

    qsort(0, len(ys) - 1)


@timeit
def qsort2(xs, key=lambda x: x, reverse=False):
    def qsort(zs):
        if not zs:
            return zs

        pivot = zs[random.randrange(0, len(zs))]

        xhead = qsort([z for z in zs if cmp(z, pivot)])
        xtail = qsort([z for z in zs if cmp(pivot, z)])

        return xhead + [z[1] for z in zs if z == pivot] + xtail

    cmp = operator.gt if reverse else operator.lt

    return qsort(list(zip([key(x) for x in xs], xs)))


@timeit
def qsort3(xs, key=lambda x: x, reverse=False):
    """ J. Bentley and D. McIlroy partitioning 
    - Key idea:
        - choose a pivot
        - move from left to find an element that is not less than pivot
        - move from right to find an element that is not greater than pivot
        - stop if pointers cross
        - swap elements
        - if left elements equal to pivot, exchange to left end
        - if right elements equal to pivot, exchange to right end
            - equal | less | pivot | greater | equal
        - swap equals to center after partitioning
            - less | equal | greater
    - Key features:
        - always use N-1 (three-way) compares
        - no extra overhead if no equal keys
        - only one "extra" exchange per equal key
    - Args:
        - xs (_type_): sort target
        - key (_type_, optional): func. Defaults to lambda x:x.
        - reverse (bool, optional): reverse sorting. Defaults to False.
    """

    def qsort(fst, lst):

        if fst >= lst:
            return

        i, j = fst, lst  # [p, i) are < pivot, (j, q] are > pivot
        p, q = fst, lst  # [fst, p) and (q, lst] are all equal to pivot
        # random pivot to avoid worst case
        pivot = ys[random.randint(fst, lst)]
        # Stop if pointers cross
        while i <= j:
            # Move from left to find an element that is not less than pivot (reverse=False)
            while i <= j and cmp(ys[i], pivot):
                # if left elements equal to pivot, exchange to left end
                if ys[i] == pivot:
                    xs[i], xs[p] = xs[p], xs[i]
                    ys[i], ys[p] = ys[p], ys[i]
                    p += 1
                i += 1
            # Move from right to find an element that is not greater than pivot (reverse=False)
            while i <= j and cmp(pivot, ys[j]):
                if ys[j] == pivot:
                    xs[j], xs[q] = xs[q], xs[j]
                    ys[j], ys[q] = ys[q], ys[j]
                    q -= 1
                j -= 1
            # Stop if pointers cross. Swap elements
            if i <= j:
                xs[i], xs[j] = xs[j], xs[i]
                ys[i], ys[j] = ys[j], ys[i]
                i, j = i + 1, j - 1
        # Swap equals to center after partitioning
        for k in range(fst, p):
            xs[j], xs[k] = xs[k], xs[j]
            ys[j], ys[k] = ys[k], ys[j]
            j -= 1
        # Swap equals to center after partitioning
        for k in range(q + 1, lst + 1):
            xs[i], xs[k] = xs[k], xs[i]
            ys[i], ys[k] = ys[k], ys[i]
            i += 1
        # Recursively sort less and greater
        qsort(fst, j)
        qsort(i, lst)

    ys = [key(x) for x in xs]
    cmp = operator.ge if reverse else operator.le

    qsort(0, len(xs) - 1)


if __name__ == '__main__':
    import numpy as np
    array = np.random.randint(2, size=10)
    qsort0(array)
    qsort1(array)
    qsort2(array)
    qsort3(array)
