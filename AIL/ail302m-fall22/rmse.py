import pandas as pd
import numpy as np
import seaborn as sns

# Load the data
df = pd.read_csv('train.csv')
# dataframe shape
print(df.shape)
# dataframe info
print(df.info())