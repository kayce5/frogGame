import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Truck extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread truckT;
	private JLabel truckLabel, frogLabel;
	private Frog frog1;
	private Car car;
	

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

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	
	public void setCarLabel(JLabel temp) {
		this.truckLabel = temp;
	}
	
	public JLabel getTruckLabel() {
		return truckLabel;
	}

	public void setTruckLabel(JLabel truckLabel) {
		this.truckLabel = truckLabel;
	}

	//Default Constructor
	public Truck() {
		super(80, 40, "truck.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use Car Label
	public Truck(JLabel temp) {
		super(124, 200, "truck.png");
		this.visible = (true);
		this.moving = false;
		this.truckLabel = temp;
	} 
	
	/*/Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	} */
	
	//Move Truck Thread
	public void moveTruck() {
		truckT = new Thread(this, "Truck Thread");
			truckT.start();  //Get the run below, running
	}
	
	
	//Run Routine
	@Override
	public void run() {
		this.moving = true; 
		frogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")) );
		
		while(moving) { //Code here will remain moving until moving false - infinate loop
			//Move Routine
			//Get current x/y
			int tx = this.x;
			int ty = this.y;
			
			//Move right to left 
			tx = tx + GameProperties.CHARACTER_STEP;
			
			//If graphic has went off screen
			if (tx > GameProperties.SCREEN_WIDTH) {
				tx = -1 * this.width;
			}
			
			//Update the x/y
			this.setX(tx);
			this.setY(ty);
			
			//Update Car Label
			truckLabel.setLocation(this.x, this.y);
			
			//Detect Colision
			this.detectTruckCollision();
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
		
		
	}
	
	private void detectTruckCollision() {
		int fy = frog1.getY();
		
		
		
		if(fy <= 660 && fy >= 430 && this.rectangle.intersects(frog1.getRectangle())) {
			//System.out.println("Your on the road");
			//System.out.print("\n Colision Truck \n");
			this.moving = (false);
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDead.png")) );
			
			//Collide Sound
			String filepath = "end.wav";
			Sound sound = new Sound();
			sound.playSound(filepath);
			
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
				int input = JOptionPane.showConfirmDialog(null, "Sorry you are out of lives ! \nWould you like to play again? ", "Gameover!", JOptionPane.YES_NO_OPTION);
				if(input == 0) {
					System.out.println("You clicked yes and would like to play again");
					GameMain frogGame = new GameMain();
					frogGame.setVisible(true);
					GameMain.life = 3;
					GameMain.score = 0;
				} else {
					System.exit(0);
				}

			}
			
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