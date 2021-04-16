import pandas as pd
import matplotlib.pyplot as plt


df = pd.read_csv("results.csv")
df.plot(y=['Combined Index'], xticks=range(2,500,50),yticks=range(0,50,10),style = 'x-')
plt.xlabel('Query Size')
plt.ylabel('Runtime ms')
plt.show()
