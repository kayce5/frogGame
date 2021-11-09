
import javax.swing.JLabel;


public class Frog extends Sprite /*implements Runnable*/ {
	private Boolean moving, visible;
	private Boolean[] onLilyPad;
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
	/*
	public Boolean getOnLilyPad() {
		return onLilyPad;
	}*/

	public void setOnLilyPad(Boolean onLilyPad, int position) {
		this.onLilyPad[position] = onLilyPad;
	}

	public Boolean checkFrogLilyPad() {
		Boolean check = false; 
		for(int i = 0; i < onLilyPad.length; i++) {
			if(onLilyPad[i]) {
				check = true;
				break;
			}
		}
		return check; 
	}
	
	public Frog () {
		super(46, 68, "frog.png");
		onLilyPad = new Boolean[8];
		for(int i = 0; i < onLilyPad.length; i++) {
			onLilyPad[i] = false;
		}
	}	
	
	//Constructor to use Car Label
	public Frog(JLabel temp) {
		super(124, 200, "car.png");
		onLilyPad = new Boolean[8];
		this.visible = (true);
		this.moving = false;
		this.frogLabel = temp;
		for(int i = 0; i < onLilyPad.length; i++) {
			onLilyPad[i] = false;
		}
	} 
	
}
