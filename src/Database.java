import java.sql.*;
public class Database {

	private void dataBase() {
		//Database*** Fix
		/*
				Connection conn; 
				Statement stmt; 
				String name = "Kiely";
				score = 0;
				
				String nameReturn;
				int scoreReturn;
				
				try {
					//Load DB driver
					Class.forName("com.mysql.jdbc.Driver");
					
					//Create Connection String
					String URL = "jdbc.mysql://127.0.0.1:8889/playerTable";
					
					//Connect to DB 
					conn = DriverManager.getConnection(URL, "root", "root");
					
					//Initialize Statement 
					stmt = conn.createStatement();
					
					//Insert
					stmt.execute("INSERT INTO playerTable (name, score) VALUES ('"+name+"', '"+score+"')");
					
					//Retrive
					ResultSet rs = stmt.executeQuery("SELECT * FROM playerTable");
					
					while(rs.next()) {
						nameReturn = rs.getString("name");
						scoreReturn = rs.getInt("score");
						
						System.out.println("Name: " + name + "\nScore: " + score);
					}
					
					
				} catch (Exception e){
					e.printStackTrace();
				}//end of catch
			*/	
	}
	

}
