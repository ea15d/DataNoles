import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
estimate_id_filepath = "estimate_id.csv" 
estimate_score_filepath = "estimate_score.csv" 
results_filepath = "results.csv"

estimate_id = pd.read_csv(estimate_id_filepath, parse_dates=True, infer_datetime_format=True)
estimate_score = pd.read_csv(estimate_score_filepath, parse_dates=True, infer_datetime_format=True)
results = pd.read_csv(results_filepath, parse_dates=True, infer_datetime_format=True)
                      

#print(estimate_id)
#print(estimate_score)

ax=estimate_id.plot(y=['Runtime'], xticks=range(2,500,50),style = 'x-')
estimate_score.plot(y=['Runtime'], xticks=range(2,500,50),style = 'x-',ax=ax)
results.plot(y=['Runtime'], xticks=range(2,500,50),style = 'x-', ax=ax)

plt.show()
