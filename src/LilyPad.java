
public class LilyPad extends Sprite{
	
	private Boolean moving;
	
	//Getters and Setters
	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}
	
	//Default Constructor
	public LilyPad() {
		super(124, 200, "lilypad1.png");
		this.moving = false;
	} 
	
	//Displaying the x and y
	public void Display() {
		System.out.println("x,y: " + this.x + " " + this.y);
	}
	
	
	
	
}
