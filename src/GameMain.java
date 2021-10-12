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

public class GameMain extends JFrame implements ActionListener, KeyListener{
	private static final long serialVersionUID = -4418414814196675442L;
	
	//Storage Classes
	private Frog frog1;
	private LilyPad lilyPad;
	private Car car;
	
	//Graphic Labels
	private JLabel frogLabel, lilyPadLabel;
	private JLabel carLabel;
	private ImageIcon frogImage, lilyPadImage, carImage;
	//Container for graphics - **set background , color etc**
	private Container content; 
	
	//Start Button
	private JButton startGameBtn;
	
	
	
	//Gui Constructor 
	public GameMain() {
		super("Frogger");
		//Set Size of Screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		//Do not allow screen to be resized
		setResizable(false);
		
		
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
		
		//Initialize Car
		car = new Car();
		carLabel = new JLabel();
		carImage = new ImageIcon(getClass().getResource(car.getFilename()));
		carLabel.setIcon(carImage);
		carLabel.setSize(car.getWidth(), car.getHeight());
		car.setCarLabel(carLabel);
		car.setFrog(frog1);
		car.setFrogLabel(frogLabel);
		car.setLilyPad(lilyPad);
		car.setLilyPadLabel(lilyPadLabel);
		
		
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
		content.setBackground(Color.gray); //**Need to fix
		setLayout(null); //Allow to position characters on screen
		
		//Adding characters on screen
		frog1.setX(400);
		frog1.setY(615);
		add(frogLabel);
	
		
		//lilyPad.setX(100);
		//lilyPad.setY(200);
		add(lilyPadLabel);
		lilyPadLabel.setVisible(lilyPad.getVisible());
		
		//???
		car.setX(800);
		car.setY(525);
		
		
		
		add(carLabel);
		carLabel.setVisible(car.getVisible());
		
		
		//Update Label Positions - match stored values
		frogLabel.setLocation(frog1.getX(), frog1.getY());
		lilyPadLabel.setLocation(lilyPad.getX(), lilyPad.getY());
		carLabel.setLocation(car.getX(), car.getY());
		
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
	}



	//Functions for ActionListener and KeyListener
	//Start Button
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameBtn) {
			if(lilyPad.getMoving() && car.getMoving()) { //Tell whether or not its moving
				lilyPad.setMoving(false);
				car.setMoving(false);
				
				startGameBtn.setVisible(true); //Hide Button
			} else { 
				startGameBtn.setVisible(false); //Show Button
				lilyPad.moveLilyPad();
				car.moveCar();
			}
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
			//if(fy + frog1.getWidth() < 0) fy = GameProperties.SCREEN_HEIGHT; -- Need to stop frog from going off screen
			
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			fy = fy + GameProperties.CHARACTER_STEP;
			//if(fy > GameProperties.SCREEN_HEIGHT) fy = -1 * frog1.getHeight();
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			fx = fx - GameProperties.CHARACTER_STEP;
			//if(fx + frog1.getWidth() < 0) fx = GameProperties.SCREEN_WIDTH;
			
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			fx = fx + GameProperties.CHARACTER_STEP;
			//if(fx > GameProperties.SCREEN_WIDTH) fx = -1 * frog1.getWidth();
		}
		
		//Update
		frog1.setX(fx);
		frog1.setY(fy);
		
		frogLabel.setLocation(frog1.getX(), frog1.getY());

		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
