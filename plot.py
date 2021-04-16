import pandas as pd
import matplotlib.pyplot as plt


Cov = pd.read_csv("plot_input.txt", sep=' ', names=["Sequence", "Start", "End", "Coverage"])

Cov = Cov.reset_index()

import numpy
Cov = pd.DataFrame(numpy.asarray(Cov).T.tolist(),
                      columns=['Score-ordered','Id-ordered'])

Cov.plot( y=["Score-ordered","Id-ordered"],style='o')
plt.xlabel('Query size')
plt.ylabel('Runtime ms')

#Cov.plot(y=['B','P'], xticks=range(0,1000,100),style = 'o')


plt.show()

