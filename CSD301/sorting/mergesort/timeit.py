import operator
import time
from functools import wraps

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
def mergesort0(xs, key=lambda x: x, reverse=False):
    if len(xs) <= 1:
        return xs

    def mergesort(zs):
        if len(zs) <= 1:
            return zs

        ls = mergesort(zs[: len(zs) // 2])
        rs = mergesort(zs[len(zs) // 2:])

        li, ri = 0, 0

        while li < len(ls) and ri < len(rs):
            if cmp(ls[li], rs[ri]):
                zs[li + ri] = ls[li]

                li += 1
            else:
                zs[li + ri] = rs[ri]

                ri += 1

        for i in range(li, len(ls)):
            zs[i + ri] = ls[i]
        for i in range(ri, len(rs)):
            zs[li + i] = rs[i]

        return zs

    zs = list(zip([key(x) for x in xs], xs))
    cmp = operator.gt if reverse else operator.lt

    return [z[1] for z in mergesort(zs)]

@timeit
def mergesort1(xs, key=lambda x: x, reverse=False):
    n, step = len(xs), 1

    ys = [key(x) for x in xs]
    cmp = operator.gt if reverse else operator.lt

    while step < n:
        for i in range(0, n - step, 2 * step):
            xls, xrs = xs[i: i + step], xs[i + step: i + 2 * step]
            yls, yrs = ys[i: i + step], ys[i + step: i + 2 * step]

            li, ri = 0, 0
            ln, rn = len(yls), len(yrs)
            while li < len(yls) and ri < len(yrs):
                if cmp(yls[li], yrs[ri]):
                    xs[i + li + ri] = xls[li]
                    ys[i + li + ri] = yls[li]
                    li += 1
                else:
                    xs[i + li + ri] = xrs[ri]
                    ys[i + li + ri] = yrs[ri]
                    ri += 1

            if li != ln:
                xs[i + li + ri: i + ln + ri] = xls[li:]
                ys[i + li + ri: i + ln + ri] = yls[li:]

            if ri != rn:
                xs[i + li + ri: i + li + rn] = xrs[ri:]
                ys[i + li + ri: i + li + rn] = yrs[ri:]

        step *= 2
    print(xs)

@timeit
def mergesort2(xs, key=lambda x: x, reverse=False):
    n, step = len(xs), 1

    ys = [key(x) for x in xs]
    cmp = operator.gt if reverse else operator.lt

    while step < n:
        for i in range(0, n - step, 2 * step):
            xaux = xs[i: i + 2 * step]
            yaux = ys[i: i + 2 * step]

            li, ri = 0, step

            while li < step and ri < len(yaux):
                if cmp(yaux[li], yaux[ri]):
                    xs[i + li + ri - step] = xaux[li]
                    ys[i + li + ri - step] = yaux[li]
                    li += 1
                else:
                    xs[i + li + ri - step] = xaux[ri]
                    ys[i + li + ri - step] = yaux[ri]
                    ri += 1

            for j in range(li, step):
                xs[i + j + ri - step] = xaux[j]
                ys[i + j + ri - step] = yaux[j]
            for j in range(ri, len(yaux)):
                xs[i + li + j - step] = xaux[j]
                xs[i + li + j - step] = yaux[j]

        step *= 2
    print(xs)

@timeit
def mergesort3(xs, key=lambda x: x, reverse=False):
    n, step = len(xs), 2

    ys = [key(x) for x in xs]
    cmp = operator.gt if reverse else operator.lt

    while step < 2 * n:
        for i in range(0, n, step):
            xls, xrs = xs[i: i + step // 2], xs[i + step // 2: i + step]
            yls, yrs = ys[i: i + step // 2], ys[i + step // 2: i + step]

            li, ri = 0, 0
            ln, rn = len(yls), len(yrs)

            while li < ln and ri < rn:
                if cmp(yls[li], yrs[ri]):
                    xs[i + li + ri] = xls[li]
                    ys[i + li + ri] = yls[li]
                    li += 1
                else:
                    xs[i + li + ri] = xrs[ri]
                    ys[i + li + ri] = yrs[ri]
                    ri += 1

            if li != ln:
                xs[i + li + ri: i + ln + ri] = xls[li:]
                ys[i + li + ri: i + ln + ri] = yls[li:]

            if ri != rn:
                xs[i + li + ri: i + li + rn] = xrs[ri:]
                ys[i + li + ri: i + li + rn] = yrs[ri:]

        step *= 2
    
        
if __name__ == '__main__':
    import numpy as np
    array = [111,111,11,23,23,4234,23,23,45,45,6,6,6,6,6,9,9,9]
    print(f"array = {array}")
    mergesort3(array)
    print(f"array = {array}")