import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

//DATABASE IMPORTS
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener, KeyListener {

	private int spriteIndex;
	
	Connection conn = null;
	Statement stmt = null;
	
	private Frog frog;
	private Car cars[];
	private Log logs[];
	private Timer timer;
	private String name;
	
	private int time = 20;
	private int gameTimer = 0;
	
	//image icons to display image icons
	private ImageIcon frogImage;
	
	private ImageIcon carImage;
	private ImageIcon roadImage;
	private ImageIcon waterImage;
	private ImageIcon logImage;
	
	//labels to store image icons
	private JLabel frogLabel, carLabel, logLabel, roadLabel, waterLabel, timeLabel, levelLabel, scoreLabel;
	//screen container
	private Container content;
	
	//Game Global variables that concern different levels
	public static int totalGameTime = 0;
	public static int score = 0;
	public static int frogLives = 3;
	public static int level = 1;

	
	public static int getFrogLives() {
		return frogLives;
	}

	public static void setFrogLives(int Lives) {
		frogLives = Lives;
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
		
		
		
		timer = new Timer(10, this);
		timer.start();
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
		
		cars = new Car[level*9]; 	
		int spriteChooser = 0;
		//ROW 1
		for(int i = 0; i < level*2 - 1; i++) {
			int x  = i * 200; // 0 * 200 = 0->80; 1 * 200 = 200->280; 2 * 200 = 400->480; 3 * 200 = 600->680;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_2_BASE - GameProperties.TRACK, -3, "orangeCar.png", 80, 50);
			carLabel = new JLabel();
			carLabel.setFocusable(false);
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
			
			cars[spriteIndex].setSpriteName("orangeCar.png");
				
				
			
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
		//ROW 2
		for(int i = 0; i < 1; i ++) {
			int x  = (i + 1) * 200;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_3_BASE - GameProperties.TRACK, level*4, "orangeCar.png", 80, 50);
			carLabel = new JLabel();
			carLabel.setFocusable(false);
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
			
			cars[spriteIndex].setSpriteName("orangeCarFlipped.png");
			
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
		//ROW 3
		for(int i = 0; i < level*3; i ++) {
			int x  = (i + 1) * 200;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_4_BASE - GameProperties.TRACK, 3, "pinkCar.png", 80, 50);
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
		//ROW 4
		for(int i = 0; i < level*2; i ++) {
			int x  = i * 200 + 50;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_5_BASE - GameProperties.TRACK, -7, "greenCar.png", 80, 50);
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
		logs = new Log[9];
		
		//ROW 5
		for(int i = 0; i < 4; i ++) {
			int x  = i * 200 + 30;
			logs[spriteIndex] = new Log(x, GameProperties.TRACK_7_BASE - GameProperties.TRACK, level*4 - 1, "log2.png", 90, 50);
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
		
		//ROW 6
		for(int i = 0; i < 3; i ++) {
			
			int x  = i * 200 + 30;
			logs[spriteIndex] = new Log(x, GameProperties.TRACK_8_BASE - GameProperties.TRACK, level*-4 + 1, "log1.png", 50, 50);
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
		
		//ROW 7
		for(int i = 0; i < 2; i ++) {
			int x  = i * 200 + 30;
			logs[spriteIndex] = new Log(x, GameProperties.TRACK_9_BASE - GameProperties.TRACK, level*2 - 1, "log2.png", 90, 50);
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
		roadLabel.setLocation(0,250);
		add(roadLabel);
		//water graphic
		waterImage = new ImageIcon(getClass().getResource("water.png"));
		waterLabel.setIcon(waterImage);
		waterLabel.setSize(600,150);
		waterLabel.setLocation(0,50);
		add(waterLabel);
		
		timeLabel = new JLabel("20");
		timeLabel.setSize(35,35);
		timeLabel.setLocation(15,15);
		timeLabel.setFont(new Font("Serif", Font.BOLD, 18));
		timeLabel.setForeground(Color.white);
		add(timeLabel);
		
		levelLabel = new JLabel("Level " + level);
		levelLabel.setSize(100,50);
		levelLabel.setLocation(GameProperties.BOARD_WIDTH/2 - 50, 7);
		levelLabel.setFont(new Font("Serif", Font.BOLD, 18));
		levelLabel.setForeground(Color.white);
		add(levelLabel);
		
		scoreLabel = new JLabel("Score: " + score);
		scoreLabel.setSize(100,50);
		scoreLabel.setLocation(GameProperties.BOARD_WIDTH - 100, 15);
		scoreLabel.setFont(new Font("Serif", Font.BOLD, 18));
		scoreLabel.setForeground(Color.white);
		add(scoreLabel);
		
		content.addKeyListener(this);
		content.setFocusable(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 	
		
		
	}
	
	
	public static void main(String[] args) {
		Main newGame = new Main();
		newGame.setVisible(true);
	}

	@Override
	public void keyTyped(KeyEvent e) {

		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		frog.setFrogAttached(false);//assume this is false
	
		frog.moveFrog(e);
		System.out.println(frog.getSpriteY());
		if (frog.getSpriteY() == 0) {
			totalGameTime += time;
			score += totalGameTime;
			JOptionPane.showMessageDialog(null, "<html><body>Level complete! <br> Total score so far: " + score + "</body></html>");
			level++;
			
			System.out.println(level); //test
			// I COULD HAVE 50x50 GOAL POSTS and add the X values in these win conditions. 
			if (level == 2) {
			Main levelTwoGame = new Main();
			levelTwoGame.setVisible(true);
			}
			
			if (level == 3) {
			Main levelThreeGame = new Main();
			levelThreeGame.setVisible(true);
			}
			//WIN CONDITION MET
			if (level == 4) {
				JOptionPane.showMessageDialog(null,"<html><body>Game complete! Congratulations! <br> Total time taken so far: " + (60 - totalGameTime) + " seconds. <br> Your Score is: " + score + "</body></html>");
				ConnectToDatabase();
			}
		
		}
		if (frog.getSpriteY() <= GameProperties.TRACK_8_BASE && frog.getSpriteY() >= GameProperties.TRACK_10_BASE){ 
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
		
		System.out.println(gameTimer);
		gameTimer += 1;
		
		if (gameTimer == 100) {
			System.out.println("hi");
			time--;
			timeLabel.setText(String.valueOf(time));;
			gameTimer = 0;
		}
		
		if (frogLives == 0) {
			
		}
	    
		if (frog.getSpriteY() == 0) {
			timer.stop();
		}
		
		if (time == 0 && frogLives > 1) {
			timer.stop();
			frogLives--;
			frog.setFrogAlive(false); 
			
			frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
			frogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
			Main.setFrogLives(Main.getFrogLives()-1);
			System.out.println(Main.getFrogLives());
			frog.setFrogAlive(true); 
			time = 20;
			timer.restart();
		}
		
		if (time == 0 && frogLives == 1 || frogLives == 0) {
			frogLives--;
			timer.stop();
			JOptionPane.showMessageDialog(null,"<html><body>Game Over! <br> Your Score is: " + score + "</body></html>");
			name = JOptionPane.showInputDialog("Enter Your Name!");
			// put name and score in DB, show DB results
			ConnectToDatabase();
		}

	}
	public void ConnectToDatabase() {
		System.out.println("hey");
		
		try {
			//load the DB driver
			Class.forName("org.sqlite.JDBC");
			String dbURL = "jdbc:sqlite:scores.db";
			conn = DriverManager.getConnection(dbURL);
			if (conn != null) {
				System.out.println("Connection established");
				
				conn.setAutoCommit(false);
				DatabaseMetaData dm = (DatabaseMetaData)conn.getMetaData();
				
				
				stmt = conn.createStatement();
				String sql = "";
				ResultSet rs = null;
				
				//CREATE TABLE IF NOT EXIST
				sql = "CREATE TABLE IF NOT EXISTS scores ("+ 
					  "id INTEGER PRIMARY KEY, " +
					  "name TEXT NOT NULL, " +
					  "score INT NOT NULL" + ")";
				
				stmt.executeUpdate(sql);
				conn.commit();
				
				//insert the current game's data
				//Insert data
				
				sql = "INSERT INTO scores (name, score) VALUES ('" +
				       name + "', " + score + ")";
				
				stmt.executeUpdate(sql);
				conn.commit();
				
				//Retrieve
				sql = "SELECT * FROM scores";
				rs = stmt.executeQuery(sql);
				System.out.println("The current data is => ");
				DisplayRecords(rs);
				rs.close();
				
			} else {
				System.out.println("Cannot establish connection");
			}
						
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//cleanup
		}
	

	}
	
	public static void DisplayRecords(ResultSet rs) throws SQLException {
		while ( rs.next() ) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int score = rs.getInt("score");
			
			
			System.out.println("#: " + id);
			System.out.println("Name: " + name);
			System.out.println("Score: " + score);
			
		}
	}
}
