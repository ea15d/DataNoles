# DataNoles
Advanced Database Systems Project

Dataset (this assumes user has a MySQL database called "dataset", with login="root" and password="root")
* HD5 files from Million Song Subset already converted to CSV
* Download 'SongCSV (1).csv' file
* Use "Table Data Import Wizard" to import "songs_id_index" table from 'SongCSV (1).csv'
* Assign SongNumber as Primary Key (which will function as our id-index)
  * Name index "idx_songs_id_index_SongNumber"
* Use "Table Data Import Wizard" to import "songs_cost_index" table from 'SongCSV (1).csv'
* Assign Duration as BTREE index (which will function as our score-index)
  * Name index "idx_songs_score_index_Duration"


Running Program (assumes user has Eclipse)
* Install MySQL Java connector into project directory
* Install Apache Commons Math3 3.6.1 library (https://commons.apache.org/proper/commons-math/download_math.cgi)
* Download "IdEquation.java", "Score_Equation.java", and "Main.java" into src directory of project
* Click Run on "Main.java"


Plot Results (assumes user has Python3 with Matplotlib, Pandas, and Numpy libraries)
* Download "submittedplot.py" and "Python Scatter Plot.py"
* Download the "estimate_id.csv", "estimate_score.csv" and "results.csv" into the same folder as the python files.
* Run using Python3 to get the graph results for the project. 
