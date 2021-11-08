import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
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
	private Car cars[]; 
	private Truck trucks[];
	private Water water;
	private Road road;	
	
	//Graphic Labels
	private JLabel frogLabel, lilyPadLabel, lilyPadOrangeLabel ,waterLabel, roadLabel, lifeLabel;
	private JLabel carsLabel[];
	private JLabel trucksLabel[];
	
	
	private ImageIcon frogImage, lilyPadImage, lilyPadOrangeImage, waterImage, roadImage, lifeImage;
	private ImageIcon carsImage[];
	private ImageIcon trucksImage[];
	
	
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
		
		//**Initialize Car**
		//Array Car 
		cars = new Car[4];
		cars[0] = new Car();
		cars[0].setX(480);
		cars[0].setY(650);
		cars[0].setFilename("car.png");
		
		cars[1] = new Car();
		cars[1].setX(600);
		cars[1].setY(650);
		cars[1].setFilename("car.png");
		
		cars[2] = new Car();
		cars[2].setX(900);
		cars[2].setY(650);
		cars[2].setFilename("car.png");
		
		cars[3] = new Car();
		cars[3].setX(200);
		cars[3].setY(650);
		cars[3].setFilename("car.png");
		
		
		//For loop to add labels 
		carsLabel = new JLabel[4];
		for(int i = 0; i < 4; i++) {
			carsLabel[i] = new JLabel();
		}
		
		//For Loop for Array Car Labels
		carsImage = new ImageIcon[4];
		for(int i=0; i < 4; i++) {
			carsImage[i] = new ImageIcon(getClass().getResource(cars[i].getFilename()));
		}
		
	
		//For Loop for Array Car Labels Set Icon
		for(int i = 0; i < 4; i++) {
			carsLabel[i].setIcon(carsImage[i]);
		}
		
		
		//For Loop for Array Car Labels Set Size
		for(int i = 0; i < 4; i++) {
			carsLabel[i].setSize(cars[i].getWidth(), cars[i].getHeight());
		}
		

		//For Loop for Array Set Car Labels 
		for(int i = 0; i < 4; i++) {
			cars[i].setCarLabel(carsLabel[i]);
		}
		
	
		//For Loop for Array Set Frog
		for(int i = 0; i < 4; i++) {
			cars[i].setFrog(frog1);
		}
		
		//For Loop Array Set Frog Label
		for(int i = 0; i < 4; i++) {
			cars[i].setFrogLabel(frogLabel);
		}
		
		//For Loop Add Car
		for(int i = 0; i < 4; i++) {
			add(carsLabel[i]);
		}
				
		
		//For Loop Car Label Visible 
		for(int i = 0; i < 4; i++) {
			carsLabel[i].setVisible(cars[i].getVisible());
		}
		
		//For Loop Start Game Button Cars Array
		for(int i = 0; i < 4; i++) {
			cars[i].setStartGameBtn(startGameBtn);
		}
		
		//For Loop Car Label SetLocation 
		for(int i = 0; i < 4; i++) {
			carsLabel[i].setLocation(cars[i].getX(), cars[i].getY());
		}
		//*End of Initialize Car*
		
		
		//**Initialize Truck**
		//Array Truck 
		trucks = new Truck[3];
		trucks[0] = new Truck();
		trucks[0].setX(150);
		trucks[0].setY(590);
		trucks[0].setFilename("truck.png");
		
		trucks[1] = new Truck();
		trucks[1].setX(500);
		trucks[1].setY(590);
		trucks[1].setFilename("truck.png");
		
		trucks[2] = new Truck();
		trucks[2].setX(800);
		trucks[2].setY(590);
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
		
		//Frog Life 
		lifeLabel = new JLabel();
		lifeImage = new ImageIcon(getClass().getResource("frogLife.png"));
		lifeLabel.setIcon(lifeImage);
		lifeLabel.setSize(50 ,50);
		add(lifeLabel);
		lifeLabel.setLocation(0, 60);
		
		//Start Button Initial 
		startGameBtn = new JButton(" Start ");
		startGameBtn.setSize(120, 40);
		startGameBtn.setLocation(GameProperties.SCREEN_WIDTH - 150, GameProperties.SCREEN_HEIGHT - 90); 
		add(startGameBtn);
		startGameBtn.setFocusable(false); //Cannot grab focus away*
		startGameBtn.addActionListener(this); //Add action listener to the button so it will respond
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
		
		//Check for water col?**
		frogGame.waterCol();
	
		
		//Database*** Fix
		/*
				Connection conn; 
				Statement stmt; 
				String name = "Kiely";
				score = 0;
				
				String nameReturn;
				int scoreReturn;
				
				try {
					//Load DB driver
					Class.forName("com.mysql.jdbc.Driver");
					
					//Create Connection String
					String URL = "jdbc.mysql://127.0.0.1:8889/playerTable";
					
					//Connect to DB 
					conn = DriverManager.getConnection(URL, "root", "root");
					
					//Initialize Statement 
					stmt = conn.createStatement();
					
					//Insert
					stmt.execute("INSERT INTO playerTable (name, score) VALUES ('"+name+"', '"+score+"')");
					
					//Retrive
					ResultSet rs = stmt.executeQuery("SELECT * FROM playerTable");
					
					while(rs.next()) {
						nameReturn = rs.getString("name");
						scoreReturn = rs.getInt("score");
						
						System.out.println("Name: " + name + "\nScore: " + score);
					}
					
					
				} catch (Exception e){
					e.printStackTrace();
				}//end of catch
			*/	
		
		
		
		
	} //End of Main
	
	
	
	
	//Check for water col?**
	private void waterCol() {
		
		int fx = frog1.getX();
		int wx = water.getX();
		
		//if the frogs x and y not the water than collision
		/*
		if(){
			System.out.println("WATERCOL");
		}
		*/
		
	}
	
	public void score() {
		score = score + 10;
		System.out.printf("Score: %d \n", score);
	}
	
	

	//Functions for ActionListener and KeyListener
	//Start Button
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == startGameBtn) {
			lilyPad.moveLilyPad();
			lilyPadOrange.moveLilyPadOrange();
			
			//For Loop Move Car
			for(int i = 0; i < 4; i++) {
				cars[i].moveCar();
			}
			
			//For Loop Move Trucks
			for(int i = 0; i < 3; i++) {
				trucks[i].moveTruck();
			}
			
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
