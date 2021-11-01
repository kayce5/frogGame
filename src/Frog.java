
public class Frog extends Sprite {
	private Boolean moving, visible;
	private int life = 3;
	
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
		life = 3;
	}	
}
