import javax.swing.JButton;
import javax.swing.JLabel;

public class LilyPad extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread lilyT;
	private JLabel lilyPadLabel;
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
		this.lilyPadLabel = temp;
	}

	//Default Constructor
	public LilyPad() {
		super(124, 200, "lilypad1.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use LilyPad Label
	public LilyPad(JLabel temp) {
		super(124, 200, "lilypad1.png");
		this.visible = (true);
		this.moving = false;
		this.lilyPadLabel = temp;
	} 
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}
	
	public void moveLilyPad() {
		lilyT = new Thread(this, "LilyPad Thread");
				lilyT.start();  //Get the run below, running
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
			tx += GameProperties.CHARACTER_STEP;
			
			//Update the x
			this.setX(tx);; 
			this.setY(ty); 
			
			//Update LilyPad Label
			lilyPadLabel.setLocation(this.x, this.y);
			
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
	}
	
	
	
}
