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
        print(f'Function {func.__name__}{args} {kwargs} took {total_time}')
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

            if i > j: break

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
        if not zs: return zs

        pivot = zs[random.randrange(0, len(zs))]

        xhead = qsort([z for z in zs if cmp(z, pivot)])
        xtail = qsort([z for z in zs if cmp(pivot, z)])

        return xhead + [z[1] for z in zs if z == pivot] + xtail

    cmp = operator.gt if reverse else operator.lt

    return qsort(list(zip([key(x) for x in xs], xs)))

@timeit
def qsort3(xs, key=lambda x: x, reverse=False):
    """ J. Bentley and D. McIlroy partitioning """

    def qsort(fst, lst):
        if fst >= lst: return

        i, j = fst, lst  # [p, i) are < pivot, (j, q] are > pivot
        p, q = fst, lst  # [fst, p) and (q, lst] are all equal to pivot
        pivot = ys[random.randint(fst, lst)]

        while i <= j:
            while i <= j and cmp(ys[i], pivot):
                if ys[i] == pivot:
                    xs[i], xs[p] = xs[p], xs[i]
                    ys[i], ys[p] = ys[p], ys[i]
                    p += 1
                i += 1
            while i <= j and cmp(pivot, ys[j]):
                if ys[j] == pivot:
                    xs[j], xs[q] = xs[q], xs[j]
                    ys[j], ys[q] = ys[q], ys[j]
                    q -= 1
                j -= 1

            if i <= j:
                xs[i], xs[j] = xs[j], xs[i]
                ys[i], ys[j] = ys[j], ys[i]
                i, j = i + 1, j - 1

        for k in range(fst, p):
            xs[j], xs[k] = xs[k], xs[j]
            ys[j], ys[k] = ys[k], ys[j]
            j -= 1

        for k in range(q + 1, lst + 1):
            xs[i], xs[k] = xs[k], xs[i]
            ys[i], ys[k] = ys[k], ys[i]
            i += 1

        qsort(fst, j)
        qsort(i, lst)

    ys = [key(x) for x in xs]
    cmp = operator.ge if reverse else operator.le

    qsort(0, len(xs) - 1)
    

if __name__=='__main__':
    from timeit import Timer
    import numpy as np
    array = np.random.randint(1000, size = 1000000)
    a = Timer("qsort0(array)", "from __main__ import qsort0")
    a = Timer("qsort1(array)", "from __main__ import qsort1")
    a = Timer("qsort2(array)", "from __main__ import qsort2")
    a = Timer("qsort3(array)", "from __main__ import qsort3")
    print(a.timeit())
    print(b.timeit())
    print(c.timeit())
    print(d.timeit())
