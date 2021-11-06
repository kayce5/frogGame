import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Truck extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread truckT;
	private JLabel truckLabel, frogLabel;
	private JButton startGameBtn;
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
	
	//Setter for Game Button
	public void setStartGameBtn(JButton temp) {
		this.startGameBtn = temp;
	}
	
	//Setter for Frog
	public void setFrog(Frog temp) {
		this.frog1 = temp;
	}
	
	
	//Setter for Frog Label
	public void setFrogLabel(JLabel temp) {
		this.frogLabel = temp;
	}

	//Setter for Car Label
	public void setCarLabel(JLabel temp) {
		this.truckLabel = temp;
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
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}
	
	public void moveTruck() {
		truckT = new Thread(this, "Truck Thread");
			truckT.start();  //Get the run below, running
	}
	
	

	@Override
	public void run() {
		System.out.printf("Lives: %d \n", GameMain.life);
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
		if(this.rectangle.intersects(frog1.getRectangle())) {
			System.out.print("\n Colision Truck \n");
			this.moving = (false);
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDead.png")) );
			//Need to set up so that it gives 3 lives and then is a play again button
			GameMain.life = GameMain.life - 1;
			if(GameMain.life != 0) {
				JOptionPane.showMessageDialog(null, "Uh-Oh");
				System.out.printf("Lives: %d \n", GameMain.life);
				frog1.setX(480);
				frog1.setY(700);
				frogLabel.setLocation(frog1.getX(), frog1.getY());
				frogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")) );
			} else {
				JOptionPane.showMessageDialog(null, "Game Over!");
				//Add code in here to start new game 
				//Below makes a new game screen but does close other one ******
				//GameMain frogGame = new GameMain();
				//frogGame.setVisible(true);
				System.exit(0);
			}

		}
		
		this.moving = (true);
	}
	
	
}