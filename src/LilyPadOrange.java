import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LilyPadOrange extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private int position; 
	private Thread lilyOrangeT;
	private JLabel lilyPadOrangeLabel, frogLabel;
	private Frog frog1;
	

	//Getters and Setters
	public int getPosition() {
		return position; 
	}
	
	public void setPosition(int position) {
		this.position = position;
	}
	
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
	
	//Getter
	public JLabel getFrogLabel() {
		return frogLabel;
	}

	public Frog getFrog1() {
		return frog1;
	}
	
	
	//Setter for Frog
	public void setFrog(Frog temp) {
		this.frog1 = temp;
	}
	
	//Setter for Frog Label
	public void setFrogLabel(JLabel temp) {
		this.frogLabel = temp;
	}
	
	//Setter for LilyPad Label
	public void setLilyPadLabel(JLabel temp) {
		this.lilyPadOrangeLabel = temp;
	}

	//Default Constructor
	public LilyPadOrange() {
		super(220, 75, "lilypadOrange.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use LilyPad Label
	public LilyPadOrange(JLabel temp) {
		super(220, 75, "lilypadOrange.png");
		this.visible = (true);
		this.moving = false;
		this.lilyPadOrangeLabel = temp;
	} 
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}
	
	public void moveLilyPadOrange() {
		lilyOrangeT = new Thread(this, "LilyPadOrange Thread");
				lilyOrangeT.start();  //Get the run below, running
	}
	
	//Frog x

	@Override
	public void run() {
		this.moving = true;
		
		//Display();
		
		//Get current x/y
		int lx = this.x;
		int ly = this.y;
		
		while(moving) { //Code here will remain moving until moving false - infinate loop
			//Move Routine
			
			
			//Move left to right 
			lx = lx - GameProperties.CHARACTER_STEP;
			
			//If graphic has went off screen
			if(lx + lilyPadOrangeLabel.getWidth() < 0) {
				lx = GameProperties.SCREEN_WIDTH;
			}
			
			//Update the x/y
			this.setX(lx);
			this.setY(ly);
			
			//Update LilyPad Label
			lilyPadOrangeLabel.setLocation(this.x, this.y);
			
			//Detect Colision
			this.detectLilyPadOrangeCollision();
			//System.out.println("LP Mobe");
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
		
	}
	
	private void detectLilyPadOrangeCollision() {
		int fx = frog1.getX();
		int fy = frog1.getY();
		
		//boolean onLilyPad = false;
		
		if (this.rectangle.intersects(frog1.getRectangle())) {
			//onLilyPad = true;
			frog1.setOnLilyPad(true, this.position);
		} else {
			frog1.setOnLilyPad(false, this.position);
		}
		
		//Need frog to die if he hits water unless he is on lilypad
		if(fy <= 335 && fy >= 40 && !frog1.checkFrogLilyPad()) {
			this.moving = (false);
			frog1.setX(480);
			frog1.setY(700);
			frogLabel.setLocation(frog1.getX(), frog1.getY());
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
				}//End of 2nd else

			} //End of 1st else
			
		} else if (this.rectangle.intersects(frog1.getRectangle())) {
			//To make frog move with lily pad
			fx = this.getX();
			fy = this.getY();
			
			frog1.setX(fx + 3);
			frog1.setY(fy + 3);
			
			frogLabel.setLocation(fx, fy);
		
		}
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



