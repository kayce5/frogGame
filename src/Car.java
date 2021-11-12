import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Car extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread carT;
	private JLabel carLabel, frogLabel;
	private Frog frog1;

	
	//Getters and Setters
	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public void setFrog(Frog temp) {
		this.frog1 = temp;
	}

	public void setFrogLabel(JLabel temp) {
		this.frogLabel = temp;
	}

	public void setCarLabel(JLabel temp) {
		this.carLabel = temp;
	}
	
	//Default Constructor
	public Car() {
		super(80, 40, "car.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use Car Label
	public Car(JLabel temp) {
		super(124, 200, "car.png");
		this.visible = (true);
		this.moving = false;
		this.carLabel = temp;
	} 
	
	/*/Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}*/
	
	//Thread
	public void moveCar() {
		carT = new Thread(this, "Car Thread");
			carT.start(); 
	}
	

	@Override
	public void run() {
		//System.out.printf("Lives: %d \n", GameMain.life);
		this.moving = true; 
		
		frogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")) );
		
		while(moving) { //Code here will remain moving until moving false - infinate loop
			//Move Routine
			//Get current x/y
			int cx = this.x;
			int cy = this.y;
			
			//Move right to left 
			cx = cx - GameProperties.CHARACTER_STEP;
			
			//If graphic has went off screen
			if(cx + carLabel.getWidth() < 0) {
				cx = GameProperties.SCREEN_WIDTH;
			}
			
			//Update the x/y
			this.setX(cx);
			this.setY(cy);
			
			//Update Car Label
			carLabel.setLocation(this.x, this.y);
			
			//Detect Colision
			this.detectCarCollision();
			
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
		
		
	}
	
	private void detectCarCollision() {
		int fy = frog1.getY();
		//See if you won - checks y so will work for all
		if(fy <= 40 && fy >= 0) {
			frog1.setX(480);
			frog1.setY(700);
			JOptionPane.showMessageDialog(null, "YOU DID IT");
			GameMain.name = JOptionPane.showInputDialog("What is your name?");
			JOptionPane.showMessageDialog(null, "Youre score is: " + GameMain.score);
			
			//Declare connection and sql statement
			Connection conn = null;
			Statement stmt = null;
			try {
				Class.forName("org.sqlite.JDBC");
				//System.out.println("Database Driver Loaded");
				
				String dbURL = "jdbc:sqlite:playerScore.db";
				conn = DriverManager.getConnection(dbURL);
				
				if (conn != null) {
					//System.out.println("Connected to database");
					conn.setAutoCommit(false);
					stmt = conn.createStatement();
					
					String sql = "CREATE TABLE IF NOT EXISTS PLAYER_SCORE" +
					             "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
							     " NAME TEXT NOT NULL, " + 
					             " SCORE INT NOT NULL)";
					stmt.executeUpdate(sql);
					conn.commit();
					//System.out.println("Table Created Successfully");
					
					//Insert
					sql = "INSERT INTO PLAYER_SCORE (NAME, SCORE) VALUES " + 
	                        "('"+ GameMain.name +"', '"+ GameMain.score +"')";
					stmt.executeUpdate(sql);
					conn.commit();//*/
					
					ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_SCORE ORDER BY SCORE DESC");
					System.out.println("     SCOREBOARD");
					System.out.println(" ====================");
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
			} //End of Database 
			int input = JOptionPane.showConfirmDialog(null, "You WON! Congratulations - Would you like to play again? ", "WINNER", JOptionPane.YES_NO_OPTION);
			if(input == 0) {
				//System.out.println("You clicked yes and would like to play again");
				GameMain frogGame = new GameMain();
				frogGame.setVisible(true);
				GameMain.life = 3;
				GameMain.score = 0;
			} else {
				System.exit(0);
			}	
		}
		
		if(fy <= 660 && fy >= 430 && this.rectangle.intersects(frog1.getRectangle())) {
			//System.out.println("Your on the road");
			//System.out.print("\n Colision Car \n");
			this.moving = (false);
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDead.png")) );
			
			//Decide options based on lives
			GameMain.life = GameMain.life - 1;
			if(GameMain.life != 0) {
				frog1.setX(480);
				frog1.setY(700);
				frogLabel.setLocation(frog1.getX(), frog1.getY());
				JOptionPane.showMessageDialog(null, "You now have lives: " + GameMain.life);
				frogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")) );
			} else {
				frog1.setX(480);
				frog1.setY(700);
				frogLabel.setLocation(frog1.getX(), frog1.getY());
				JOptionPane.showMessageDialog(null, "You now have lives: " + GameMain.life);
				GameMain.name = JOptionPane.showInputDialog("What is your name?");
				JOptionPane.showMessageDialog(null, "Youre score is: " + GameMain.score);
				
				//Declare connection and sql statement
				Connection conn = null;
				Statement stmt = null;
				try {
					Class.forName("org.sqlite.JDBC");
					//System.out.println("Database Driver Loaded");
					
					String dbURL = "jdbc:sqlite:playerScore.db";
					conn = DriverManager.getConnection(dbURL);
					
					if (conn != null) {
						//System.out.println("Connected to database");
						conn.setAutoCommit(false);
						stmt = conn.createStatement();
						
						String sql = "CREATE TABLE IF NOT EXISTS PLAYER_SCORE" +
						             "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
								     " NAME TEXT NOT NULL, " + 
						             " SCORE INT NOT NULL)";
						stmt.executeUpdate(sql);
						conn.commit();
						//System.out.println("Table Created Successfully");
						
						//Insert
						sql = "INSERT INTO PLAYER_SCORE (NAME, SCORE) VALUES " + 
		                        "('"+ GameMain.name +"', '"+ GameMain.score +"')";
						stmt.executeUpdate(sql);
						conn.commit();//*/
						
						ResultSet rs = stmt.executeQuery("SELECT * FROM PLAYER_SCORE ORDER BY SCORE DESC");
						System.out.println("     SCOREBOARD");
						System.out.println(" ====================");
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
				} //End of Database 
				
				
				int input = JOptionPane.showConfirmDialog(null, "Sorry you are out of lives ! \nWould you like to play again? ", "Gameover!", JOptionPane.YES_NO_OPTION);
				if(input == 0) {
					System.out.println("You clicked yes and would like to play again");
					GameMain frogGame = new GameMain();
					frogGame.setVisible(true);
					GameMain.life = 3;
					GameMain.score = 0;
				} else {
					System.exit(0);
				}//End of 2nd else

			} //End of 1st else
			
		}//End of is statement
		this.moving = (true);
		
	}
	public static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next() ) {
			String name = rs.getString("name");
			int score = rs.getInt("score");
			
			System.out.println("    Name  = " + name);
			System.out.println("    Score = " + score);
			System.out.println(" ====================");

		}
	}

}