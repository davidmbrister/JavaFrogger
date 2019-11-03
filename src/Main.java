import java.awt.Color;
import java.awt.Container;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, KeyListener {

	private int spriteIndex;
	
	private Frog frog;
	private Car cars[];
	private Log logs[];
	
	
	//image icons to display image icons
	private ImageIcon frogImage;
	
	private ImageIcon carImage;
	private ImageIcon roadImage;
	private ImageIcon waterImage;
	private ImageIcon logImage;
	
	//labels to store image icons
	private JLabel frogLabel, carLabel, logLabel, roadLabel, waterLabel;
	//screen container
	private Container content;
	
	//Game Global variables that concern different levels
	public static int frogLives = 3;
	public static int level = 1;

	
	
	public static int getFrogLives() {
		return frogLives;
	}


	public static void setFrogLives(int Lives) {
		frogLives = Lives;
	}
	
	public void getNewFrog() {
		for(Car car : cars) {
			car.setFrog(frog);
			
		}
	}
	
	public void restart() {
		
		frog.setFrogLabel(frogLabel);
		
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog.getSpriteW(), frog.getSpriteH());
		
		frogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
		content.add(frogLabel);
		frogLabel.repaint();
		frog.setFrogAlive(true);
		
	}

	
	public Main() {
		/*
		 * way to add sprite
		  1 create and store local object 
		  2 create and set class-native label for object type
		   
		  3 instantiate an icon image from this.SpriteName declared in object constructor
		   
		  4 object.setLabel
		  5 objectLabel.setIcon with icon image
		  6 objectLabel.setSize of component with object's declared or constructed sprite dimensions
		  7 objectLabel.setLocation
		  8 add(objectLabel) to parent 
		 * */
		//add content pane
		content = getContentPane();
		//FROG SETUP
		
		frog = new Frog();
		frogLabel = new JLabel();
		roadLabel = new JLabel();
		waterLabel = new JLabel();
		frogImage = new ImageIcon(getClass().getResource(frog.getSpriteName()) );
		
		restart();//configure frog in starting location
		
		
		setSize(GameProperties.BOARD_WIDTH, GameProperties.BOARD_HEIGHT_WITH_TOP_BAR);
		setLayout(null);
		
		
		//setResizable(false);
		
		setLocationRelativeTo(null);
		content.setBackground(Color.darkGray);
		
		//set road
		
		 
		
		//CARS SETUP
		// for loops to instantiate multiples cars
		spriteIndex = 0;

		cars = new Car[level*6]; 	//however many cars I end up having will go here
		int spriteChooser = 0;
		for(int i = 0; i < level*3; i ++) {
			int x  = (i + 1) * 200;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_TWO_BASE - GameProperties.TRACK, 3, "pinkCar.png", 80, 50);
			carLabel = new JLabel();
			carLabel.setFocusable(false);
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
			if(spriteChooser % 2 == 0) {
				cars[spriteIndex].setSpriteName("pinkCar.png");
			} else {	
				cars[spriteIndex].setSpriteName("greenCarFlipped.png");
			}
			carImage = new ImageIcon(getClass().getResource(cars[spriteIndex].getSpriteName()) );
			cars[spriteIndex].setCarLabel(carLabel);
			carLabel.setIcon(carImage);
			carLabel.setSize(cars[spriteIndex].getSpriteW(), cars[spriteIndex].getSpriteH());
			carLabel.setLocation(cars[spriteIndex].getSpriteX(), cars[spriteIndex].getSpriteY());
			cars[spriteIndex].startCar();
			content.add(carLabel);
			
			//DEGBUG
			System.out.println(frog.isFrogAlive()); //prints true...PASS
			System.out.println(carLabel.getLocation()); //it's the correct coords. PASS
			spriteIndex++;
			spriteChooser++;
		}
		spriteChooser = 0;
		for(int i = 0; i < level*3; i ++) {
			int x  = i * 200 + 50;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_THREE_BASE - GameProperties.TRACK, -7, "greenCar.png", 80, 50);
			carLabel = new JLabel();
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
			if(spriteChooser % 2 == 0) {
				cars[spriteIndex].setSpriteName("greenCar.png");
			} else {	
				cars[spriteIndex].setSpriteName("redCarFlipped.png");
			}
			carImage = new ImageIcon(getClass().getResource(cars[spriteIndex].getSpriteName()) );
			cars[spriteIndex].setCarLabel(carLabel);
			carLabel.setIcon(carImage);
			carLabel.setSize(cars[spriteIndex].getSpriteW(), cars[spriteIndex].getSpriteH());
			carLabel.setLocation(cars[spriteIndex].getSpriteX(), cars[spriteIndex].getSpriteY());
			cars[spriteIndex].startCar();
			content.add(carLabel);
			
			//DEGBUG
			System.out.println(frog.isFrogAlive()); //prints true...PASS
			System.out.println(carLabel.getLocation()); //it's the correct coords. PASS
			spriteIndex++;
			spriteChooser++;
		}
		
		//Initiate logs
		spriteIndex = 0;

		logs = new Log[level*5];
		
		for(int i = 0; i < level*3; i ++) {
			
			int x  = i * 200 + 30;
			logs[spriteIndex] = new Log(x, GameProperties.TRACK_SIX_BASE - GameProperties.TRACK, -7, "log1.png", 50, 50);
			logLabel = new JLabel();
			logs[spriteIndex].setFrog(frog);
			logs[spriteIndex].setFrogLabel(frogLabel);
			logImage = new ImageIcon(getClass().getResource(logs[spriteIndex].getSpriteName()) );
			logs[spriteIndex].setLogLabel(logLabel);
			logLabel.setIcon(logImage);
			logLabel.setSize(logs[spriteIndex].getSpriteW(), logs[spriteIndex].getSpriteH());
			logLabel.setLocation(logs[spriteIndex].getSpriteX(), logs[spriteIndex].getSpriteY());
			logs[spriteIndex].startLog();
			content.add(logLabel);
			
			spriteIndex++;
			
		}
		
		
		for(int i = 0; i < level*2; i ++) {
			int x  = i * 200 + 30;
			logs[spriteIndex] = new Log(x, GameProperties.TRACK_SEVEN_BASE - GameProperties.TRACK, 9, "log2.png", 90, 50);
			logLabel = new JLabel();
			logs[spriteIndex].setFrog(frog);
			logs[spriteIndex].setFrogLabel(frogLabel);
			logImage = new ImageIcon(getClass().getResource(logs[spriteIndex].getSpriteName()) );
			logs[spriteIndex].setLogLabel(logLabel);
			logLabel.setIcon(logImage);
			logLabel.setSize(logs[spriteIndex].getSpriteW(), logs[spriteIndex].getSpriteH());
			logLabel.setLocation(logs[spriteIndex].getSpriteX(), logs[spriteIndex].getSpriteY());
			logs[spriteIndex].startLog();
			content.add(logLabel);	
			
			spriteIndex++;
		}
		
		//road graphic
		roadImage = new ImageIcon(getClass().getResource("road.jpg"));
		roadLabel.setIcon(roadImage);
		roadLabel.setSize(600,200);
		roadLabel.setLocation(0,200);
		add(roadLabel);
		//water graphic
		waterImage = new ImageIcon(getClass().getResource("water.png"));
		waterLabel.setIcon(waterImage);
		waterLabel.setSize(600,100);
		waterLabel.setLocation(0,50);
		add(waterLabel);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
	
		
		
		
	}
	
	
	public static void main(String[] args) {
		Main newGame = new Main();
		newGame.setVisible(true);
		
		//if Level increases, dispose of old frame and make a new game
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		frog.setFrogAttached(false);//assume this is false
	
		frog.moveFrog(e);
		System.out.println(frog.getSpriteY());
		if (frog.getSpriteY() == 0) {
			System.out.println("you won");
			level++;
			System.out.println(level);
			System.out.println("true");
			
			Main levelTwoGame = new Main();
			levelTwoGame.setVisible(true);
			
			
			//enact win condition code by playing with static variables or ending game and displaying statss
		}
		if (frog.getSpriteY() <= GameProperties.TRACK_SEVEN_BASE && frog.getSpriteY() >= GameProperties.TRACK_EIGHT_BASE){ 
			 System.out.println("in the blue");	
			 for (Log log : logs) {
				 	
			 		Rectangle rFrog = frog.getRectangle();
					Rectangle rLog= log.r;
			 		if (rFrog.intersects(rLog)){
			 			frog.setFrogAttached(true);
			 			
			 		}
			 }
	 		if (!frog.isFrogAttached()) {
	 		System.out.println("Splash!");
	 		frog.setFrogAttached(false);
	 		frog.setFrogAlive(false); 
	 		
	 		frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
	 		frogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
	 		
	 		Main.setFrogLives(Main.getFrogLives()-1);
	 		System.out.println(Main.getFrogLives());
	 		frog.setFrogAlive(true); 
	 		}
	 	
			 
		   }	
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// System.out.println("Key has been released");
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}



}
