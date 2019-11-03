import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* The only thing that makes this different than the Car is it's runnable, i.e. 
 * the way the frog interacts with it, which makes this code a bit redundant.
 * To make it less redundant should I .. extract the Runnable interfaces and use one Object class for 
 * both cars and logs.?????
 *
 * */
 
public class Log extends Sprite implements Runnable{
	//CLASS MEMBERS
		//variable speed to be determined through constructor because I will have two types of cars
		private float speed;
		private Thread thread;
		private JLabel FrogLabel, LogLabel;
		private Frog frog;
		
		public Log() {
			super(0, 0, "log1.png", 80, 50);
		}
		public Log(int x, int y, float s, String name, int w, int h) {
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
		
		public void startLog() {
			thread = new Thread(this, "Car");
			thread.start();
		}
		@Override
		public void run() {
			
			while(Main.getFrogLives() > 0) {
				int tX = this.spriteX;
				
				tX += speed;
				if (tX > 0 && tX > GameProperties.BOARD_WIDTH + this.spriteW) {
				     tX = -this.spriteW;
				    } else if (speed < 0 && tX < 0 - this.spriteW ) {
				      tX = GameProperties.BOARD_WIDTH + spriteW;
				    }
				 this.setSpriteX(tX);
				 LogLabel.setLocation(this.spriteX, this.spriteY);
				 //check if frogtangle is in bounds
				 Rectangle rFrog = frog.getRectangle();
				 Rectangle rLog= this.r;
				 
				 if ((frog.getSpriteY() > GameProperties.TRACK_FIVE_BASE && frog.getSpriteY() < GameProperties.TRACK_SEVEN_BASE) && rLog.intersects(rFrog) || rFrog.intersects(rLog)) {
						
						System.out.println("On it!");
										
						frog.setFrogCoords(this.getSpriteX(),this.getSpriteY()); //quickly update frog coords with log coords
						FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
						
						//if Frog is greater than or less than board with, kick him back within board bounds
					
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
		

		public void setLogLabel(JLabel logLabel) {
			LogLabel = logLabel;
		}

		
		public void setFrog(Frog frog) {
			this.frog = frog;
		}
		
}
