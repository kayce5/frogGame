
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class LilyPad extends Sprite implements Runnable {
	
	private Boolean moving, visible;
	private Thread lilyT;
	private JLabel lilyPadLabel, frogLabel;
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
	
	//Getter Frog
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
		this.lilyPadLabel = temp;
	}

	//Default Constructor
	public LilyPad() {
		super(220, 80, "lilypad1.png");
		this.visible = (true);
		this.moving = false;
	} 
	
	//Constructor to use LilyPad Label
	public LilyPad(JLabel temp) {
		super(220, 80, "lilypad1.png");
		this.visible = (true);
		this.moving = false;
		this.lilyPadLabel = temp;
	} 
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: / vis" + this.x + "," + this.y + " / " + this.visible);
	}
	
	//Move LilyPad Thread
	public void moveLilyPad() {
		lilyT = new Thread(this, "LilyPad Thread");
				lilyT.start();  //Get the run below, running
	}
	
	//Run Routine
	@Override
	public void run() {
		this.moving = true; 
		
		int fx = frog1.getX();	
		int fy = frog1.getY();
		
		//Display();
		
		//Get current x/y
		int lx = this.x;
		int ly = this.y;
		
		while(moving) { //Code here will remain moving until moving false - infinate loop
			//Move left to right 
			lx = lx + GameProperties.CHARACTER_STEP;
			
			//If graphic has went off screen
			if(lx > GameProperties.SCREEN_WIDTH) {
				lx = -1 * this.width;
			}
			
			//Update the x/y
			this.setX(lx);
			this.setY(ly);
			
			//Update LilyPad Label
			lilyPadLabel.setLocation(this.x, this.y);
			
			//Detect Colision
			this.detectLilyPadCollision();
			//System.out.println("LP Mobe");
			
			//Pause it 
			try {
				Thread.sleep(200);
			} catch (Exception e) {
				
			}
		}
		
	}
	
	private void detectLilyPadCollision() {
		int fx = frog1.getX();
		int fy = frog1.getY();
		
		boolean onLilyPad = false;
		
		
		if (this.rectangle.intersects(frog1.getRectangle())) {
			onLilyPad = true;
		}
		
		//Need frog to die if he hits water unless he is on lilypad
		if(fy <= 335 && fy >= 40 && onLilyPad == false) {
			System.out.println("In water");
			JOptionPane.showMessageDialog(null, "Uh-Oh");
			frog1.setX(480);
			frog1.setY(700);
			frogLabel.setLocation(frog1.getX(), frog1.getY());
			
		} else if (this.rectangle.intersects(frog1.getRectangle())) {
			//System.out.print("Colision LilyPad");
			//To make frog move with lily pad
			fx = this.getX();
			fy = this.getY();
			
			frog1.setX(fx);
			frog1.setY(fy);
			
			frogLabel.setLocation(fx, fy);
		
		}
	}
	
}



