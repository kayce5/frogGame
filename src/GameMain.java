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
	
	private Frog frog1;
	private LilyPad lilyPad;
	
	//Graphic Labels
	private JLabel frogLabel, lilyPadLabel;
	private ImageIcon frogImage, lilyPadImage;
	
	//Container for graphics - **set background , color etc**
	private Container content; 
	
	//Start Button
	private JButton startGameBtn;
	
	
	
	//Gui Constructor 
	public GameMain() {
		super("Frogger");
		//Set Size of Screen
		setSize(GameProperties.SCREEN_WIDTH, GameProperties.SCREEN_HEIGHT);
		
		
		//Initialize Frog
		frog1 = new Frog();
		frogLabel = new JLabel();
		frogImage = new ImageIcon(getClass().getResource(frog1.getFilename()));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog1.getWidth(), frog1.getHeight());
		//Initialize LilyPad
		lilyPad = new LilyPad();
		lilyPadLabel = new JLabel();
		lilyPadImage = new ImageIcon(getClass().getResource(lilyPad.getFilename()));
		lilyPadLabel.setIcon(lilyPadImage);
		lilyPadLabel.setSize(lilyPad.getWidth(), lilyPad.getHeight());
		
		//Main content container
		content = getContentPane();
		content.setBackground(Color.gray); //**Need to fix
		setLayout(null); //Allow to position characters on screen
		
		//Adding characters on screen
		frog1.setX(400);
		frog1.setY(535);
		add(frogLabel);
		
		lilyPad.setX(100);
		lilyPad.setY(200);
		add(lilyPadLabel);
		
		//Update Label Positions - match stored values
		frogLabel.setLocation(frog1.getX(), frog1.getY());
		lilyPadLabel.setLocation(lilyPad.getX(), lilyPad.getY());
		
		//Start Button Initial 
		startGameBtn = new JButton(" Start ");
		startGameBtn.setSize(75, 40);
		startGameBtn.setLocation(GameProperties.SCREEN_WIDTH - 100, GameProperties.SCREEN_HEIGHT - 90); 
		add(startGameBtn);
		startGameBtn.setFocusable(false); //Cannot grab focus away*
		
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
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameBtn) {
			
		} 
		
	}




	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
}
