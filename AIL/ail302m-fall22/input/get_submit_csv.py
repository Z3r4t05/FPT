from cProfile import run
from subprocess import HIGH_PRIORITY_CLASS
import pandas as pd
df = pd.read_csv('ail302m-fall22\input\cheating_data.csv')
print(df)
df[['id', 'quality']].to_csv('submission.csv', index=False)
 

'''
0 fixed acidity           1.770634 high
1 volatile acidity        1.357692 high
2 citric acid             0.498180 low
3 residual sugar          1.449472 high
4 chlorides               5.692030 high
5 free sulfur dioxide     1.169458 high
6 total sulfur dioxide    0.012788 low
7 density                 0.425231 low
8 pH                      0.416576 low
9 sulphates               2.157673 high
10 alcohol                 0.616383 moderate
11 quality                 0.333877 low
12 type

fixed acidity            5.214376 
volatile acidity         2.183113 
citric acid              2.240237
residual sugar           4.350428
chlorides               53.779375
free sulfur dioxide      7.556816
total sulfur dioxide    -0.461114
density                  6.075478
pH                       0.454295
sulphates               11.391954
alcohol                 -0.497568
quality                  0.267243'''


'''0	1	2	3
0	0.285785	0.202502	-1.287845	-1.301002
1	-0.386120	-1.481509	0.599565	1.063286
2	0.218594	0.220229	0.930690	0.067797
3	0.084213	0.291134	-0.195134	0.627760
4	-0.453311	0.752022	1.659164	-0.305512'''