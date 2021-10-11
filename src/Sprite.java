import java.awt.Rectangle;

//Basics for characters
public class Sprite {
	
	//Define Data Members 
	protected int width, height ; //Size
	protected int x, y; //Using these for the upper left coordinate 
	protected Rectangle rectangle; 
	protected String filename;
	
	//Getters and Setters 
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
		this.rectangle.setSize(this.width, this.height);
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
		this.rectangle.setSize(this.width, this.height);
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
		this.rectangle.setLocation(this.x, this.y);
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
		this.rectangle.setLocation(this.x, this.y);
	}
	
	public Rectangle getRectangle() {
		return this.rectangle;
	}
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	//Default Constructor 
	public Sprite() {
		super();
		this.width = 0;
		this.height = 0;
		this.x = 0;
		this.y = 0; 
		this.filename = "";
		this.rectangle = new Rectangle(this.x, this.y, this.width, this.height); //Has to be at bottom so x,y set
	}
	
	//Secondary Constructor 
	public Sprite(int width, int height, int x, int y, String filename) {
		super();
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		this.filename = filename;
		this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	//Secondary Constructor 
	public Sprite(int width, int height, String filename) {
		super();
		this.width = width;
		this.height = height;
		this.x = 0;
		this.y = 0;
		this.filename = filename;
		this.rectangle = new Rectangle(this.x, this.y, this.width, this.height);
	} 
	
	//Display Function 
	public void Display() {
		System.out.println("x, y: " + this.x + "," + this.y);
	}
	
}
