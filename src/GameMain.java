import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class GameMain extends JFrame implements ActionListener, KeyListener{
	private static final long serialVersionUID = -4418414814196675442L;
	
	//Storage Classes
	private Frog frog1;
	private LilyPad lilyPad;
	private LilyPadOrange lilyPadOrange;
	private Car car;
	private Truck truck;
	private Water water;
	private Road road;
	
	//Graphic Labels
	private JLabel frogLabel, lilyPadLabel, lilyPadOrangeLabel ,waterLabel, roadLabel;
	private JLabel carLabel, truckLabel; //?? private JLabel[] carLabel;
	
	private ImageIcon frogImage, lilyPadImage, lilyPadOrangeImage, carImage, waterImage, roadImage, truckImage;
	//Container for graphics - **set background , color etc**
	private Container content; 
	
	//Start Button
	private JButton startGameBtn;
	
	//Lives 
	public static int life = 3;
	
	//Score
	public static int score = 0;
	
	//Gui Constructor 
	public GameMain() {
		super("Frogger");
		//Set Size of Screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		//Do not allow screen to be resized
		setResizable(false);
		
		//Make the screen open the center
		setLocationRelativeTo(null);
		
		//Initialize Frog
		frogLabel = new JLabel();
		frog1 = new Frog();
		frogImage = new ImageIcon(getClass().getResource(frog1.getFilename()));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog1.getWidth(), frog1.getHeight());
		
		//Initialize LilyPad
		lilyPad = new LilyPad();
		lilyPadLabel = new JLabel();
		lilyPadImage = new ImageIcon(getClass().getResource(lilyPad.getFilename()));
		lilyPadLabel.setIcon(lilyPadImage);
		lilyPadLabel.setSize(lilyPad.getWidth(), lilyPad.getHeight());
		lilyPad.setLilyPadLabel(lilyPadLabel);
		lilyPad.setFrog(frog1);
		lilyPad.setFrogLabel(frogLabel);
		
		//Initialize LilyPadOrange
		lilyPadOrange = new LilyPadOrange();
		lilyPadOrangeLabel = new JLabel();
		lilyPadOrangeImage = new ImageIcon(getClass().getResource(lilyPadOrange.getFilename()));
		lilyPadOrangeLabel.setIcon(lilyPadOrangeImage);
		lilyPadOrangeLabel.setSize(lilyPadOrange.getWidth(), lilyPadOrange.getHeight());
		lilyPadOrange.setLilyPadLabel(lilyPadOrangeLabel);
		lilyPadOrange.setFrog(frog1);
		lilyPadOrange.setFrogLabel(frogLabel);
		
		//Initialize Car
		car = new Car();
		carLabel = new JLabel();
		carImage = new ImageIcon(getClass().getResource(car.getFilename()));
		carLabel.setIcon(carImage);
		carLabel.setSize(car.getWidth(), car.getHeight());
		car.setCarLabel(carLabel);
		car.setFrog(frog1);
		car.setFrogLabel(frogLabel);
		
		//Initialize Truck
		truck = new Truck();
		truckLabel = new JLabel();
		truckImage = new ImageIcon(getClass().getResource(truck.getFilename()));
		truckLabel.setIcon(truckImage);
		truckLabel.setSize(truck.getWidth(), truck.getHeight());
		truck.setTruckLabel(truckLabel);
		truck.setFrog(frog1);
		truck.setFrogLabel(frogLabel);
		truck.setCar(car);
	
		//Initialize Water
		waterLabel = new JLabel();
		water = new Water();
		waterImage = new ImageIcon(getClass().getResource(water.getFilename()));
		waterLabel.setIcon(waterImage);
		waterLabel.setSize(1000, 800);
		
		//Initialize Road
		roadLabel = new JLabel();
		road = new Road();
		roadImage = new ImageIcon(getClass().getResource(road.getFilename()));
		roadLabel.setIcon(roadImage);
		roadLabel.setSize(1000, 800);
		
		//Start Button Initial 
		startGameBtn = new JButton(" Start ");
		startGameBtn.setSize(120, 40);
		startGameBtn.setLocation(GameProperties.SCREEN_WIDTH - 150, GameProperties.SCREEN_HEIGHT - 90); 
		add(startGameBtn);
		startGameBtn.setFocusable(false); //Cannot grab focus away*
		startGameBtn.addActionListener(this); //Add action listener to the button so it will respond
		car.setStartGameBtn(startGameBtn);
		lilyPad.setStartGameBtn(startGameBtn);
				
		
		//Main content container
		content = getContentPane();
		Color myColor = Color.decode("#477d36");
		content.setBackground(myColor); //**Need to fix
		setLayout(null); //Allow to position characters on screen
		
		//Adding characters on screen
		frog1.setX(480);
		frog1.setY(700);
		add(frogLabel);
	
		//Add LilyPad
		lilyPad.setX(20);
		lilyPad.setY(290);
		add(lilyPadLabel);
		lilyPadLabel.setVisible(lilyPad.getVisible());
		
		//Add LilyPadOrange
		lilyPadOrange.setX(750);
		lilyPadOrange.setY(210);
		add(lilyPadOrangeLabel);
		lilyPadOrangeLabel.setVisible(lilyPadOrange.getVisible());
		
		//Add Car
		car.setX(850);
		car.setY(650);
		add(carLabel);
		carLabel.setVisible(car.getVisible());
		
		//Add Truck
		truck.setX(0);
		truck.setY(600);
		add(truckLabel);
		truckLabel.setVisible(truck.getVisible());
		
		//Add Water 
		water.setX(100);
		water.setY(100);
		waterLabel.setLocation(0, -180);
		add(waterLabel);
		
		//Add Water 
		roadLabel.setLocation(0, 170);
		add(roadLabel);


		//Update Label Positions - match stored values
		frogLabel.setLocation(frog1.getX(), frog1.getY());
		lilyPadLabel.setLocation(lilyPad.getX(), lilyPad.getY());
		lilyPadOrangeLabel.setLocation(lilyPadOrange.getX(), lilyPadOrange.getY());
		carLabel.setLocation(car.getX(), car.getY());
		truckLabel.setLocation(truck.getX(), truck.getY());
		
		
		//Container - Need Down here
		content.addKeyListener(this); //Adds keylistener to main window
		content.setFocusable(true); //Grabs focus for main content
		
		//To Completely Exit Program
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	} //End Gui Constructor
	
	
	
	
	//Start of Main
	public static void main(String[] args) {
		GameMain frogGame = new GameMain();
		frogGame.setVisible(true);
	
		
	} //End of Main
	
	
	public void score() {
		score++;
		System.out.printf("Score: %d \n", score);
	}
	
	//Functions for ActionListener and KeyListener
	//Start Button
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameBtn) {
			lilyPad.moveLilyPad();
			lilyPadOrange.moveLilyPadOrange();
			car.moveCar();
			truck.moveTruck();
			startGameBtn.setVisible(false);
		} 
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override //Frog Movement
	public void keyPressed(KeyEvent e) {
		int fx = frog1.getX();
		int fy = frog1.getY();
		
		//Determine which key is pressed
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			fy = fy - GameProperties.CHARACTER_STEP; 
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogUp.png")) );
			//if(fy + frog1.getWidth() < 0) fy = GameProperties.SCREEN_HEIGHT; -- Need to stop frog from going off screen
			frogLabel.setSize(46, 68);
			//score();
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			fy = fy + GameProperties.CHARACTER_STEP;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDown.png")) );
			frogLabel.setSize(44, 65);
			//score();
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			fx = fx - GameProperties.CHARACTER_STEP;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogLeft.png")) );
			frogLabel.setSize(65, 44);
			//score();
			
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			fx = fx + GameProperties.CHARACTER_STEP;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogRight.png")) );
			frogLabel.setSize(65, 44);
			//score();
		}
		
		//Update
		frog1.setX(fx);
		frog1.setY(fy);
		frogLabel.setLocation(frog1.getX(), frog1.getY());
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frog.png")) );
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDown2.png")) );
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogLeft2.png")) );
			
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogRight2.png")) );
		}
		
	}

	
	
}
