import pandas as pd
import math
from sklearn.metrics import mean_squared_error as rmse

def compute_rmse(y_true, y_pred):
    return rmse(y_true['quality'], y_pred['quality'], squared=False)

if __name__ == "__main__":
    df1 = pd.read_csv('input/cheating_data.csv')
    df2 = pd.read_csv('submission.csv')
    print(compute_rmse(df1, df2))