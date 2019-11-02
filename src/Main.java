import java.awt.Color;
import java.awt.Container;
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
	
	//image icons to display image icons
	private ImageIcon frogImage;
	private ImageIcon carImage;
	
	//labels to store image icons
	private JLabel frogLabel, carLabel;
	//screen container
	private Container content;
	
	private static int frogLives = 3;
	//private Log logs[];
	
	
	public static int getFrogLives() {
		return frogLives;
	}


	public static void setFrogLives(int Lives) {
		frogLives = Lives;
	}
	
	public void restart() {
		frog = new Frog();
		frogLabel = new JLabel();
		

		frogImage = new ImageIcon(getClass().getResource(frog.getSpriteName()) );
		frog.setFrogLabel(frogLabel);
		
		
		frogLabel.setIcon(frogImage);
		frogLabel.setSize(frog.getSpriteW(), frog.getSpriteH());
		
		frogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
		content.add(frogLabel);
			
	}

	
	public Main() {
		/*
		 * way to add sprite
		   create and store local object 
		   create and set class-native label for object type
		   set icon image from this.SpriteName declared in object constructor
		   object.setLabel
		   objectLabel.setIcon with icon image
		   objectLabel.setSize of component with object's declared or constructed sprite dimensions
		   objectLabel.setLocation
		   add(objectLabel) to parent 
		 * */
		//add content pane
		content = getContentPane();
		//FROG SETUP
		restart();
		
		setSize(GameProperties.BOARD_WIDTH, GameProperties.BOARD_HEIGHT_WITH_TOP_BAR);
		setLayout(null);
		
		//setResizable(false);
		
		setLocationRelativeTo(null);
		content.setBackground(Color.gray);
	
		
		 
		
		//CARS SETUP
		// for loops to instantiate multiples cars
		spriteIndex = 0;

		cars = new Car[6]; 	//however many cars I end up having will go here
		
		for(int i = 0; i < 3; i ++) {
			int x  = (i + 1) * 200;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_TWO_HEIGHT - GameProperties.TRACK, 1, "pinkCar.png", 80, 50);
			carLabel = new JLabel();
			carLabel.setFocusable(false);
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
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
		}
		for(int i = 0; i < 3; i ++) {
			int x  = (i + 1) * 200;
			cars[spriteIndex] = new Car(x, GameProperties.TRACK_THREE_HEIGHT - GameProperties.TRACK, -4, "greenCar.png", 80, 50);
			carLabel = new JLabel();
			cars[spriteIndex].setFrog(frog);
			cars[spriteIndex].setFrogLabel(frogLabel);
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
		}
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if (!frog.isFrogAlive() && e.getKeyCode() == KeyEvent.VK_SPACE) {
			System.out.println("hey");
			try {
				Thread.sleep(500);
			} catch (Exception SMH) {
				
			}
			restart();
			
			frogLabel.repaint();
		} else {
		frog.moveFrog(e);
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
