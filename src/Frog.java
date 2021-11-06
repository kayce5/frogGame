
import javax.swing.JLabel;


public class Frog extends Sprite /*implements Runnable*/ {
	private Boolean moving, visible;
	private JLabel frogLabel;
	
	
	public JLabel getFrogLabel() {
		return frogLabel;
	}

	public void setFrogLabel(JLabel frogLabel) {
		this.frogLabel = frogLabel;
	}

	private Water water;
	
	public Water getWater() {
		return water;
	}

	public void setWater(Water water) {
		this.water = water;
	}

	public Boolean getMoving() {
		return moving;
	}

	public void setMoving(Boolean moving) {
		this.moving = moving;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	
	public Frog () {
		super(46, 68, "frog.png");
	}	
	
	//Constructor to use Car Label
	public Frog(JLabel temp) {
		super(124, 200, "car.png");
		this.visible = (true);
		this.moving = false;
		this.frogLabel = temp;
	} 
	
}
