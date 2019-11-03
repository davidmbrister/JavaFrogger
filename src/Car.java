import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Car extends Sprite implements Runnable {
	//CLASS MEMBERS
	private float speed; //variable speed to be determined through constructor because I will have two types of cars
	private Thread t; //it needs to control value of the frog, and a frog label
	private JLabel FrogLabel, CarLabel;
	private Frog frog;
	
	//CONSTRUCTORS
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
		
		
		while(Main.getFrogLives() > 0) { // method to determine if the frog is alive
						
			int tX = this.spriteX; 
			
			
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
				frog.setFrogAlive(false); 
					
				frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
				FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
				rFrog = frog.getRectangle();
				Main.setFrogLives(Main.getFrogLives()-1);
				System.out.println(Main.getFrogLives());
				frog.setFrogAlive(true); 
			}
			
			try {
				Thread.sleep(20);
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


