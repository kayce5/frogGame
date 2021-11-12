import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;



public class GameMain extends JFrame implements ActionListener, KeyListener{
	private static final long serialVersionUID = -4418414814196675442L;
	
	//Storage Classes
	private Frog frog1;
	private Car cars[];
	private LilyPad lilyPads[];
	private LilyPadOrange lilyPadsOrange[];
	private Truck trucks[];
	private Water water;
	private Road road;	
	private Grass grass;
	
	//Graphic Labels
	private JLabel frogLabel, waterLabel, roadLabel, grassLabel;
	private JLabel carsLabel[];
	private JLabel trucksLabel[];
	private JLabel lilyPadsLabel[];
	private JLabel lilyPadsOrangeLabel[];
	
	
	private ImageIcon frogImage, waterImage, roadImage, grassImage;
	private ImageIcon carsImage[];
	private ImageIcon trucksImage[];
	private ImageIcon lilyPadsImage[];
	private ImageIcon lilyPadsOrangeImage[];
	
	
	//Container for graphics - **set background , color etc**
	private Container content; 
	
	//Start Button
	private JButton startGameBtn;
	
	//Lives 
	public static int life = 3;
	
	//Score
	public static String name = "Matthew";
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
		
		//Start Button Initial 
		startGameBtn = new JButton();
		startGameBtn.setBorder(BorderFactory.createBevelBorder(1, Color.white, Color.white));
		startGameBtn.setText("<html><p style='text-align:center'>Welcome to Frogger<br />Please Click to Start Game</p></html>");
		Color backgroundColor = Color.decode("#295c46");
		startGameBtn.setBackground(backgroundColor);
		startGameBtn.setFont(new Font("Arial", Font.BOLD, 20));
		Color textColor = Color.decode("#ffffff");
		startGameBtn.setForeground(textColor);
		startGameBtn.setSize(300, 150);
		startGameBtn.setLocation(350, 320); 
		add(startGameBtn);
		startGameBtn.setFocusable(false); //Cannot grab focus away*
		startGameBtn.addActionListener(this); //Add action listener to the button so it will respond }
		
		//Initialize Frog
		frogLabel = new JLabel();
		frog1 = new Frog();
		frogImage = new ImageIcon(getClass().getResource(frog1.getFilename()));
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog1.getWidth(), frog1.getHeight());
		frog1.setX(480);
		frog1.setY(700);
		add(frogLabel);
		frogLabel.setLocation(frog1.getX(), frog1.getY());
	
		//**Initialize LilyPad**
		//Array LilyPad 
		lilyPads = new LilyPad[4];
		lilyPads[0] = new LilyPad();
		lilyPads[0].setX(750);
		lilyPads[0].setY(145); 
		lilyPads[0].setFilename("lilypad1.png");
		
		lilyPads[1] = new LilyPad();
		lilyPads[1].setX(750);
		lilyPads[1].setY(300);
		lilyPads[1].setFilename("lilypad1.png");
		
		lilyPads[2] = new LilyPad();
		lilyPads[2].setX(200);
		lilyPads[2].setY(145);
		lilyPads[2].setFilename("lilypad1.png");
		
		lilyPads[3] = new LilyPad();
		lilyPads[3].setX(400);
		lilyPads[3].setY(300);
		lilyPads[3].setFilename("lilypad1.png");
		
		//For loop for Array LilyPad Labels
		lilyPadsLabel = new JLabel[4];
		for(int i = 0; i < 4; i++) {
			lilyPadsLabel[i] = new JLabel();
		}
		
		//For Loop for Array LilyPad Images
		lilyPadsImage = new ImageIcon[4];
		for(int i=0; i < 4; i++) {
			lilyPadsImage[i] = new ImageIcon(getClass().getResource(lilyPads[i].getFilename()));
		}
		
	
		//For Loop for Array LilyPad Labels Set Icon
		for(int i = 0; i < 4; i++) {
			lilyPadsLabel[i].setIcon(lilyPadsImage[i]);
		}
		
		
		//For Loop for Array LilyPad Labels Set Size
		for(int i = 0; i < 4; i++) {
			lilyPadsLabel[i].setSize(lilyPads[i].getWidth(), lilyPads[i].getHeight());
		}
		

		//For Loop for Array Set LilyPad Labels 
		for(int i = 0; i < 4; i++) {
			lilyPads[i].setLilyPadLabel(lilyPadsLabel[i]);
		}
		
		//For Loop Add LilyPad
		for(int i = 0; i < 4; i++) {
			add(lilyPadsLabel[i]);
		}
				
		//For Loop LilyPad Label Visible 
		for(int i = 0; i < 4; i++) {
			lilyPadsLabel[i].setVisible(lilyPads[i].getVisible());
		}
		
		//For Loop LilyPad Label SetLocation 
		for(int i = 0; i < 4; i++) {
			lilyPadsLabel[i].setLocation(lilyPads[i].getX(), lilyPads[i].getY());
		}
		
		//For Loop for Array Set Frog
		for(int i = 0; i < 4; i++) {
			lilyPads[i].setFrog(frog1);
			lilyPads[i].setPosition(i);
		}
		
		//For Loop Array Set Frog Label
		for(int i = 0; i < 4; i++) {
			lilyPads[i].setFrogLabel(frogLabel);
		}
		//*End of Initialize LilyPad
		
		//Initialize LilyPadOrange**
		//Array LilyPadOrange
		lilyPadsOrange = new LilyPadOrange[4];
		lilyPadsOrange[0] = new LilyPadOrange();
		lilyPadsOrange[0].setX(200);
		lilyPadsOrange[0].setY(65);
		lilyPadsOrange[0].setFilename("lilypadOrange.png");
		
		lilyPadsOrange[1] = new LilyPadOrange();
		lilyPadsOrange[1].setX(750);
		lilyPadsOrange[1].setY(65);
		lilyPadsOrange[1].setFilename("lilypadOrange.png");
		
		lilyPadsOrange[2] = new LilyPadOrange();
		lilyPadsOrange[2].setX(200);
		lilyPadsOrange[2].setY(225);
		lilyPadsOrange[2].setFilename("lilypadOrange.png");
		
		lilyPadsOrange[3] = new LilyPadOrange();
		lilyPadsOrange[3].setX(700);
		lilyPadsOrange[3].setY(225);
		lilyPadsOrange[3].setFilename("lilypadOrange.png");
		
		//For loop for Array LilyPad Labels
		lilyPadsOrangeLabel = new JLabel[4];
		for(int i = 0; i < 4; i++) {
			lilyPadsOrangeLabel[i] = new JLabel();
		}
		
		//For Loop for Array LilyPad Images
		lilyPadsOrangeImage = new ImageIcon[4];
		for(int i=0; i < 4; i++) {
			lilyPadsOrangeImage[i] = new ImageIcon(getClass().getResource(lilyPadsOrange[i].getFilename()));
		}
		
	
		//For Loop for Array LilyPad Labels Set Icon
		for(int i = 0; i < 4; i++) {
			lilyPadsOrangeLabel[i].setIcon(lilyPadsOrangeImage[i]);
		}
		
		
		//For Loop for Array LilyPad Labels Set Size
		for(int i = 0; i < 4; i++) {
			lilyPadsOrangeLabel[i].setSize(lilyPadsOrange[i].getWidth(), lilyPadsOrange[i].getHeight());
		}
		

		//For Loop for Array Set LilyPad Labels 
		for(int i = 0; i < 4; i++) {
			lilyPadsOrange[i].setLilyPadLabel(lilyPadsOrangeLabel[i]);
		}
		
	
		//For Loop for Array Set Frog
		for(int i = 0; i < 4; i++) {
			lilyPadsOrange[i].setFrog(frog1);
			int offset = i + 4;
			lilyPadsOrange[i].setPosition(offset);
		}
		
		//For Loop Array Set Frog Label
		for(int i = 0; i < 4; i++) {
			lilyPadsOrange[i].setFrogLabel(frogLabel);
		}
		
		
		//For Loop Add LilyPad
		for(int i = 0; i < 4; i++) {
			add(lilyPadsOrangeLabel[i]);
		}
				
		//For Loop LilyPad Label Visible 
		for(int i = 0; i < 4; i++) {
			lilyPadsOrangeLabel[i].setVisible(lilyPadsOrange[i].getVisible());
		}
		
		//For Loop LilyPad Label SetLocation 
		for(int i = 0; i < 4; i++) {
			lilyPadsOrangeLabel[i].setLocation(lilyPadsOrange[i].getX(), lilyPadsOrange[i].getY());
		}
		//*End of Initialize LilyPad */
		
		//**Initialize Car**
		//Array Car 
		cars = new Car[8];
		cars[0] = new Car();
		cars[0].setX(480);
		cars[0].setY(650);
		cars[0].setFilename("car.png");
		
		cars[1] = new Car();
		cars[1].setX(600);
		cars[1].setY(650);
		cars[1].setFilename("car2.png");
		
		cars[2] = new Car();
		cars[2].setX(900);
		cars[2].setY(650);
		cars[2].setFilename("car.png");
		
		cars[3] = new Car();
		cars[3].setX(200);
		cars[3].setY(650);
		cars[3].setFilename("car3.png");
		//New Line - Change Y
		cars[4] = new Car();
		cars[4].setX(480);
		cars[4].setY(465);
		cars[4].setFilename("car3.png");
		
		cars[5] = new Car();
		cars[5].setX(600);
		cars[5].setY(465);
		cars[5].setFilename("car.png");
		
		cars[6] = new Car();
		cars[6].setX(900);
		cars[6].setY(465);
		cars[6].setFilename("car2.png");
		
		cars[7] = new Car();
		cars[7].setX(200);
		cars[7].setY(465);
		cars[7].setFilename("car2.png");
		
		
		//For loop to add labels 
		carsLabel = new JLabel[8];
		for(int i = 0; i < 8; i++) {
			carsLabel[i] = new JLabel();
		}
		
		//For Loop for Array Car Labels
		carsImage = new ImageIcon[8];
		for(int i=0; i < 8; i++) {
			carsImage[i] = new ImageIcon(getClass().getResource(cars[i].getFilename()));
		}
		
	
		//For Loop for Array Car Labels Set Icon
		for(int i = 0; i < 8; i++) {
			carsLabel[i].setIcon(carsImage[i]);
		}
		
		
		//For Loop for Array Car Labels Set Size
		for(int i = 0; i < 8; i++) {
			carsLabel[i].setSize(cars[i].getWidth(), cars[i].getHeight());
		}
		

		//For Loop for Array Set Car Labels 
		for(int i = 0; i < 8; i++) {
			cars[i].setCarLabel(carsLabel[i]);
		}
		
	
		//For Loop for Array Set Frog
		for(int i = 0; i < 8; i++) {
			cars[i].setFrog(frog1);
		}
		
		//For Loop Array Set Frog Label
		for(int i = 0; i < 8; i++) {
			cars[i].setFrogLabel(frogLabel);
		}
		
		//For Loop Add Car
		for(int i = 0; i < 8; i++) {
			add(carsLabel[i]);
		}
				
		
		//For Loop Car Label Visible 
		for(int i = 0; i < 8; i++) {
			carsLabel[i].setVisible(cars[i].getVisible());
		}
		

		//For Loop Car Label SetLocation 
		for(int i = 0; i < 8; i++) {
			carsLabel[i].setLocation(cars[i].getX(), cars[i].getY());
		}
		//*End of Initialize Car*
		
		
		//**Initialize Truck**
		//Array Truck 
		trucks = new Truck[3];
		trucks[0] = new Truck();
		trucks[0].setX(150);
		trucks[0].setY(560);
		trucks[0].setFilename("truck.png");
		
		trucks[1] = new Truck();
		trucks[1].setX(500);
		trucks[1].setY(560);
		trucks[1].setFilename("truck2.png");
		
		trucks[2] = new Truck();
		trucks[2].setX(800);
		trucks[2].setY(560);
		trucks[2].setFilename("truck.png");
		
		
		//For loop for Array Truck Labels
		trucksLabel = new JLabel[3];
		for(int i = 0; i < 3; i++) {
			trucksLabel[i] = new JLabel();
		}
		
		//For Loop for Array Truck Images
		trucksImage = new ImageIcon[3];
		for(int i=0; i < 3; i++) {
			trucksImage[i] = new ImageIcon(getClass().getResource(trucks[i].getFilename()));
		}
		
	
		//For Loop for Array Truck Labels Set Icon
		for(int i = 0; i < 3; i++) {
			trucksLabel[i].setIcon(trucksImage[i]);
		}
		
		
		//For Loop for Array Truck Labels Set Size
		for(int i = 0; i < 3; i++) {
			trucksLabel[i].setSize(trucks[i].getWidth(), trucks[i].getHeight());
		}
		

		//For Loop for Array Set Truck Labels 
		for(int i = 0; i < 3; i++) {
			trucks[i].setCarLabel(trucksLabel[i]);
		}
		
	
		//For Loop for Array Set Frog
		for(int i = 0; i < 3; i++) {
			trucks[i].setFrog(frog1);
		}
		
		//For Loop Array Set Frog Label
		for(int i = 0; i < 3; i++) {
			trucks[i].setFrogLabel(frogLabel);
		}
		

		//For Loop set Truck
		for(int i = 0; i < 3; i++) {
			trucks[i].setCar(cars[i]);
		}
		
		//For Loop Add Truck
		for(int i = 0; i < 3; i++) {
			add(trucksLabel[i]);
		}
				
		//For Loop Trucks Label Visible 
		for(int i = 0; i < 3; i++) {
			trucksLabel[i].setVisible(trucks[i].getVisible());
		}
		
		//For Loop Truck Label SetLocation 
		for(int i = 0; i < 3; i++) {
			trucksLabel[i].setLocation(trucks[i].getX(), trucks[i].getY());
		}
		//*End of Initialize Truck*
		
		
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
	
		//Initialize Grass
		grassLabel = new JLabel();
		grass = new Grass();
		grassImage = new ImageIcon(getClass().getResource(grass.getFilename()));
		grassLabel.setIcon(grassImage);
		grassLabel.setSize(1000, 800);
		
		//Main content container
		content = getContentPane();
		setLayout(null); //Allow to position characters on screen
		

		//Add Water 
		water.setX(100);
		water.setY(100);
		waterLabel.setLocation(0, -180);
		add(waterLabel);
		
		//Add Road 
		roadLabel.setLocation(0, 170);
		add(roadLabel);
		
		//Background Image
		grassLabel.setLocation(0, 0);
		add(grassLabel);

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
		
		//Declare connection and sql statement
		Connection conn = null;
		Statement stmt = null;
		
		try {
			Class.forName("org.sqlite.JDBC");
			System.out.println("Database Driver Loaded");
			
			String dbURL = "jdbc:sqlite:playerScore.db";
			conn = DriverManager.getConnection(dbURL);
			
			if (conn != null) {
				System.out.println("Connected to database");
				conn.setAutoCommit(false);
				stmt = conn.createStatement();
				
				String sql = "CREATE TABLE IF NOT EXISTS PLAYER_SCORE" +
				             "(ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
						     " NAME TEXT NOT NULL, " + 
				             " SCORE INT NOT NULL)";
				stmt.executeUpdate(sql);
				conn.commit();
				System.out.println("Table Created Successfully");
				
				/*/Insert
				sql = "INSERT INTO PLAYER_SCORE (NAME, SCORE) VALUES " + 
                        "('"+ name +"', '"+ score +"')";
				stmt.executeUpdate(sql);
				conn.commit();*/
				

				conn.close(); //Close Connection to DB File
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}


				
	} //End of Main

	
	//Functions for ActionListener and KeyListener
	//Start Button
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameBtn) {		
			//For Loop Move Car
			for(int i = 0; i < 8; i++) {
				cars[i].moveCar();
			}
			
			//For Loop Move Trucks
			for(int i = 0; i < 3; i++) {
				trucks[i].moveTruck();
			}
			
			//For Loop Move LilyPad
			for(int i = 0; i < 4; i++) {
				lilyPads[i].moveLilyPad();
			}
			
			//For Loop Move LilyPadOrange
			for(int i = 0; i < 4; i++) {
				lilyPadsOrange[i].moveLilyPadOrange();
			} // */
			
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
			fy = fy - GameProperties.CHARACTER_MOVE; 
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogUp.png")) );
			frogLabel.setSize(46, 68);
			if (fy < 0) {fy = 0;} //Keep From Going off screen
			score = score + 10;
			System.out.printf("Score: %d \n", score);
			
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN){
			fy = fy + GameProperties.CHARACTER_MOVE;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogDown.png")) );
			frogLabel.setSize(44, 65);
			if (fy > 700) {fy = 700;} //Keep From Going off screen
	
			
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			fx = fx - GameProperties.CHARACTER_MOVE;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogLeft.png")) );
			frogLabel.setSize(65, 44);
			if(fx < 0) {fx = 0;} //Keep From Going off screen
			score = score + 2;
			System.out.printf("Score: %d \n", score);
			
		} else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			fx = fx + GameProperties.CHARACTER_MOVE;
			frogLabel.setIcon(new ImageIcon(getClass().getResource("frogRight.png")) );
			frogLabel.setSize(65, 44);
			if (fx > 920) {fx = 920;} //Keep From Going off screen
			score = score + 2;
			System.out.printf("Score: %d \n", score);
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
