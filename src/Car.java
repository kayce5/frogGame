import javax.swing.JButton;
import javax.swing.JLabel;

public class Car extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread carT;
	private JLabel carLabel;
	private JButton startGameBtn;
	
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
	
	//Setter for LilyPad Label
	public void setLilyPadLabel(JLabel temp) {
		this.carLabel = temp;
	}

	//Default Constructor
	public Car() {
		super(124, 200, "car.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use LilyPad Label
	public Car(JLabel temp) {
		super(124, 200, "car.png");
		this.visible = (true);
		this.moving = false;
		this.carLabel = temp;
	} 
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}
	
	public void moveCar() {
		carT = new Thread(this, "Car Thread");
			carT.start();  //Get the run below, running
	}

	@Override
	public void run() {
		this.moving = true; 
		
		while(moving) { //Code here will remain moving until moving false - infinate loop
			//Move Routine
			//Get current x/y
			int tx = this.x;
			int ty = this.y;
			
			//Move left to right 
			tx = tx - GameProperties.CHARACTER_STEP;
			
			//If graphic has went off screen
			if(tx > GameProperties.SCREEN_WIDTH) {
				tx = -1 * this.width;
			}
			
			//Update the x
			this.x = tx;
			this.y = ty;
			
			//Update LilyPad Label
			carLabel.setLocation(this.x, this.y);
			
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
	}
	
	
	
}