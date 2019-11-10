import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Car extends Sprite implements Runnable {
		//CLASS MEMBERS
		private float speed; //variable speed which will determine pixel change rate per thread loop; can be negative to move things left
		private Thread t; 
		//it needs to control value of the frog, and a frog and car label
		private JLabel FrogLabel, CarLabel;
		private Frog frog;
		
		//CONSTRUCTORS
		public Car() {
			super(0, 0, "pinkCar.png", 80, 50);	
		}
	
		public Car(int x, int y, float s, String name, int w, int h) {
			this.spriteX = x;
			this.spriteY = y;
			this.speed = s;
			this.spriteName = name;
			this.spriteW = w;
			this.spriteH = h;
			r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
		}
		
		//GETTERS/SETTERS
		public float getSpeed() {
			return speed;
		}
		
		public void setSpeed(float speed) {
			this.speed = speed;
		}
		
		public void Display() {
			System.out.println("X,Y: " + spriteX + ", " + spriteY + "," + "Speed: " + speed);
		}
		//THREAD MAKER/STARTER
		public void startCar() {
			t = new Thread(this, "Car");
			t.start();
		}
	
		@Override
		public void run() {
			
			//stop the car's thread if the frog has no lives
			while(Main.getFrogLives() > 0) { 
							
				int tX = this.spriteX; // store a copy of value of current this.car X val
				
				//BASIC CAR MOVEMENT TO CYCLE THE SAME CAR ON AND OFF THE SCREEN	
				tX += speed; //add the speed of the cars (per-loop-time x-range pixel transformation) to this.sprite's x-coord every thread loop
				if (tX > 0 && tX > GameProperties.BOARD_WIDTH + this.spriteW) {
					tX = -this.spriteW; //if it goes off-screen to the right, move it to back to the left to reappear on screen left
				} else if (speed < 0 && tX < 0 - this.spriteW ) { // if off-screen left, then move to off-screen right
				      tX = GameProperties.BOARD_WIDTH + spriteW;
				}
				this.setSpriteX(tX); //update position
				CarLabel.setLocation(this.spriteX, this.spriteY);
				
				 //test intersection
				Rectangle rFrog = frog.getRectangle(); //get their rectangles which were created by the super Sprite class contractor
				Rectangle rCar= this.r;
				
				if (rCar.intersects(rFrog) || rFrog.intersects(rCar)) {
					
					System.out.println("Crash!");
					frog.setFrogAlive(false); //frog is dead - if i need to know that somewhere 
					//RESET FROG
					frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
					FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
					rFrog = frog.getRectangle();
					//Decrement frog lives
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
	
		//SET LABELS AND FROG
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


