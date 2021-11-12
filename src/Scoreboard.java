/*
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Scoreboard {
	static JFrame frame = new JFrame();
	JLabel test = new JLabel();
	
	Scoreboard() {
		test.setBounds(0, 0, 100, 40);
		test.setFont(new Font("Arial", Font.PLAIN, 10));
		frame.add(test);
		
		frame.setResizable(false);
		frame.setSize(600, 600);
		frame.setLayout(null);
		frame.setVisible(true);
		
		//Declare connection and sql statement
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Database Driver Loaded");
			
			String dbURL = "jdbc:sqlite:playerScore.db";
			conn = DriverManager.getConnection(dbURL);
			
			if (conn != null) {
				System.out.println("Connected to database");
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				
				String sql = "CREATE TABLE IF NOT EXISTS PLAYER_SCORE" +
				             "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
						     " NAME TEXT NOT NULL, " + 
				             " SCORE INT NOT NULL)";
				stmt.executeUpdate(sql);
				conn.commit();
				System.out.println("Table Created Successfully");
				
				ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_SCORE");
				DisplayRecords(rs);
				rs.close();

				conn.close(); //Close Connection to DB File
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
	
	//Database Display
		public static void DisplayRecords(ResultSet rs) throws SQLException {
			
			
			while ( rs.next() ) {
				//int id = rs.getInt("id");
				String name = rs.getString("name");
				int score = rs.getInt("score");
				JLabel[] lblName = new JLabel[6];
				JLabel[] lblScore = new JLabel[6];
			
				for(int i = 0; i < 6; i++) {
		
					lblName[i].setText("Name: " + name);
					lblName[i].setBounds(0, 0, 100, 500);
					lblName[i].setFont(new Font(null, Font.PLAIN, 15));
					frame.add(lblName[i]);
					
					lblScore[i].setText("Score: " + score);
					lblScore[i].setBounds(0, 0, 100, 500);
					lblScore[i].setFont(new Font(null, Font.PLAIN, 15));
					frame.add(lblScore[i]);
				}
				
				
				//System.out.println("ID = " + id);
				//System.out.println("Name = " + name);
				//System.out.println("Score = " + score);
				System.out.println();
			}
		}

	
}
*/
