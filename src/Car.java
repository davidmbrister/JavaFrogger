import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Car extends Sprite implements Runnable {
	//CLASS MEMBERS
	//variable speed to be determined through constructor because I will have two types of cars
	private float speed;
	private Thread t;
	//does the car need to know about anything 
	//it needs to control value of the frog, and a frog label
	private JLabel FrogLabel, CarLabel;
	private Frog frog;
	
	//CONSTRUCTOR
	public Car() {
		super(0, 0, "pinkCar.png", 80, 50);
		
	}
	//if I want to have two cars the only difference between them will be x position, speed and same-size PNG
	public Car(int x, int y, float s, String name, int w, int h) {
		this.spriteX = x;
		this.spriteY = y;
		this.speed = s;
		this.spriteName = name;
		this.spriteW = w;
		this.spriteH = h;
		r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
	}
	
	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
	}
	
	public void Display() {
		System.out.println("X,Y: " + spriteX + ", " + spriteY + "," + "Speed: " + speed);
	}
	
	public void startCar() {
		t = new Thread(this, "Car");
		t.start();
	}

	@Override
	public void run() {
		
		
		while(frog.isFrogAlive()) { // method to determine if the frog is alive
						
			int tX = this.spriteX; // tX = tetrominoeX
			
			
			tX += speed; //the only thing that will vary unless I want to create another car class
			 if (tX > 0 && tX > GameProperties.BOARD_WIDTH + this.spriteW) {
			     tX = -this.spriteW;
			    } else if (speed < 0 && tX < 0 - this.spriteW ) {
			      tX = GameProperties.BOARD_WIDTH + spriteW;
			    }
			 this.setSpriteX(tX);
			 CarLabel.setLocation(this.spriteX, this.spriteY);
			//test intersection
			Rectangle rFrog = frog.getRectangle();
			Rectangle rCar= this.r;
			//TEST
			//System.out.println(rCar.toString()); //PROBLEM
			//no y,w,or h???
			
			
			
			
			if (rCar.intersects(rFrog) || rFrog.intersects(rCar)) {
				
				System.out.println("Crash!");
				frog.setFrogAlive(false); //break cycle
				
				//end of cycle cleanup up/mutations
				//change frog label
				frog.setSpriteName("frogDead.png");
				FrogLabel.setIcon( new ImageIcon(getClass().getResource(frog.getSpriteName() ) ));
				
				//decrement lives
				int currentLives = Main.getFrogLives() -1;
				Main.setFrogLives(currentLives);
				System.out.println(Main.getFrogLives());
				
				try {
					Thread.sleep(500);
					FrogLabel.setVisible(false);
					
				} catch (Exception e) {
					
				}
				
				
			}
			
			try {
				Thread.sleep(10);
			} catch (Exception e) {
				
			}
			
		}
	}

	
	public void setFrogLabel(JLabel frogLabel) {
		FrogLabel = frogLabel;
	}
	

	public void setCarLabel(JLabel carLabel) {
		CarLabel = carLabel;
	}

	
	public void setFrog(Frog frog) {
		this.frog = frog;
	}
				
}


