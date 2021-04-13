import java.sql.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class Main {
	
	static int SS_BEG = 2;
	static int SS_END = 500;
	static double c1 = 25;//3.53; //27
	static double c2 = 0.075;//.001 //025

	
	public static void main(String[] args) throws IOException{
		
		
		
		// CONNECT TO DATABASE
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dataset","root","root");
			Statement st = conn.createStatement();
	    	FileWriter f = new FileWriter("src/results.csv");
	    	// write column headers
	    	f.write("Runtime,Query Size\n");
			
			/*=================PROJECT_BEG==================*/	    	
			st.executeUpdate("TRUNCATE mysql.slow_log;");
			st.executeQuery("SET GLOBAL slow_query_log ='OFF';");
			String view_query;
			ResultSet rs;
			System.out.println("Printing Top 20!");
			for (int setSize = SS_BEG; setSize <= SS_END; setSize++) {
				
				String table_var = get_table(setSize);
				
				st.executeQuery("SET GLOBAL slow_query_log ='ON';");
				st.executeQuery("SET GLOBAL log_output='TABLE';");
				st.executeQuery("SET GLOBAL long_query_time=0;");
				st.executeQuery("SET Global log_queries_not_using_indexes ='ON';");

				view_query = "CREATE OR REPLACE VIEW tmp AS SELECT * FROM "+ table_var +" order by rand() LIMIT "+ setSize +";";
				st.executeUpdate(view_query);

				
				if (table_var.compareTo("songs_id_index") == 0)
					{st.executeQuery("SELECT SongNumber, SongID, Duration, Title FROM songs_id_index FORCE INDEX(idx_songs_id_index_SongNumber) WHERE SongNumber IN (SELECT SongNumber FROM dataset.tmp) ORDER BY Duration LIMIT 20;");}
				if (table_var.compareTo("songs_cost_index") == 0)
					{st.executeQuery("SELECT SongNumber, SongID, Duration, Title FROM songs_cost_index FORCE INDEX(idx_songs_cost_index_Duration) WHERE SongNumber IN (SELECT SongNumber FROM dataset.tmp) ORDER BY Duration LIMIT 20;");}
				
				st.executeQuery("SET GLOBAL slow_query_log='OFF';");
				rs = st.executeQuery("SELECT * FROM mysql.slow_log where rows_sent<>0;");				
				

				while(rs.next())
					{
					String time = rs.getString(3).split("[.]")[1]; // currently in microseconds
					String ms = "";
					for (int i = 0; i < 3; i++)
						ms += time.charAt(i);
					ms += ".";
					for (int i = 3; i < 6; i++)
						ms += time.charAt(i);
					//System.out.println("SetSize = " + setSize + " on Table " + table_var + " "+rs.getString(1)+"  "+time+"  "+" "+rs.getString(7));
					f.write(ms + "," + setSize + "\n");
					}
				rs.close();
				st.executeUpdate("TRUNCATE mysql.slow_log;");
			}
			/*=================PROJECT_END==================*/
			f.close();
			conn.close();
		}
		catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		// GRAPHING OF MODEL VERIFICATION
		final int n = 10000;
		final int b = 63;
		final int k = 20;
	    writeToFile(n,b,k);						//writes IE and SE to a text file
	    
	    System.out.println("Done!");
	    
	}
	
	public static String get_table(int setSize) {
		final int n = 10000;
		final int b = 63;
		final int k = 20;
		int cmp = IdEquation.IE(n, b, setSize, c1, c2).compareTo(Score_Equation.SE(n, b, setSize, k, c1, c2));
		
		if ( cmp == 0 ) // Choose ID
			return "songs_id_index";
		else if ( cmp == 1 ) // ID is greater, choose Score
			return "songs_cost_index";
		else // Score is greater, choose ID
			return "songs_id_index";
	}
	
    public static void writeToFile(int indSize, int tupSize, int num_req) throws IOException {
    	int setSize;
    	FileWriter f = new FileWriter("src/estimate_id.csv");
    	// write column headers
    	f.write("Runtime,Query Size\n");
    	for (setSize = SS_BEG; setSize <= SS_END; setSize++) {
			f.write(IdEquation.IE(indSize, tupSize, setSize, c1, c2) + "," + setSize + "\n");
		}
		
		f.close();
		
		f = new FileWriter("src/estimate_score.csv");
    	// write header
		f.write("Runtime,Query Size\n");
		for (setSize = SS_BEG; setSize <= SS_END; setSize++) {
			f.write(Score_Equation.SE(indSize, tupSize, setSize, num_req, c1, c2) + "," + setSize + "\n");
		}
		
        f.close();
    }
    
}