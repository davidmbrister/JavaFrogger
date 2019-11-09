import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* The only thing that makes this different than the Car is its runnable, i.e. 
 * the way the frog interacts with it, which makes this code a bit redundant?
 * */
 
public class Log extends Sprite implements Runnable{
	//CLASS MEMBERS
		private int speed;
		private Thread thread;
		private JLabel FrogLabel, LogLabel;
		private Frog frog;
	
	//CONSTRUCTORS
		public Log() {
			super(0, 0, "log1.png", 80, 50);
		}
		public Log(int x, int y, int s, String name, int w, int h) {
			this.spriteX = x;
			this.spriteY = y;
			this.speed = s;
			this.spriteName = name;
			this.spriteW = w;
			this.spriteH = h;
			r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
		}
	//GETTERS AND SETTERS
		public float getSpeed() {
			return speed;
		}
		public void setSpeed(int speed) {
			this.speed = speed;
		}
		
		public void Display() {
			System.out.println("X,Y: " + spriteX + ", " + spriteY + "," + "Speed: " + speed);
		}
	//make and start thread, call it car
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
				 
				 if ((frog.getSpriteY() > GameProperties.TRACK_8_BASE && frog.getSpriteY() < GameProperties.TRACK_7_BASE) && rLog.intersects(rFrog) || rFrog.intersects(rLog)) {
						
						System.out.println("On it!");
						
						frog.setSpriteX(frog.getSpriteX() + speed);
						//add log speed per thread loop to Frog and frog label x coords
						FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
						frog.setFrogAttached(true);
						
						//if Frog and log are intersecting and frog is greater than or less than board with, kick him back within board bounds
						if(frog.getSpriteX() < 0) {
							//if the whole log (to which the frog is attached)has moved off screen, the frog is detached and is pushed into the water
							if (tX == -this.spriteW + 7) {
								
								frog.setFrogAttached(false);	
												
								//REFACTOR THIS
								System.out.println("Splash!");
								frog.setFrogAlive(false); 
								//starting position 
								frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
								FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
								
								Main.setFrogLives(Main.getFrogLives()-1);
								System.out.println(Main.getFrogLives());
								
								frog.setFrogAlive(true); 
								
							} else {
								
							frog.setSpriteX(0);
							FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
							
							}
						}
						//if frog x plus its width are greater than board width, then frog is no longer fully on-screen
						if(frog.getSpriteX() + GameProperties.FROG_STEP > GameProperties.BOARD_WIDTH) {
								//if log is (almost) fully off-screen, push frog into water
								if (tX > GameProperties.BOARD_WIDTH - 7) {
									frog.setFrogAttached(false);	
									
									System.out.println("Splash!");
									frog.setFrogAlive(false); 
									
									frog.setFrogCoords(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP);
									FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
									
									Main.setFrogLives(Main.getFrogLives()-1);
									System.out.println(Main.getFrogLives());
									frog.setFrogAlive(true); 
									
								} else {
									
									System.out.println("offScreenRight");
								frog.setSpriteX(GameProperties.BOARD_WIDTH - GameProperties.FROG_STEP);
								FrogLabel.setLocation(frog.getSpriteX(), frog.getSpriteY());
								
								}
						}
				 }
				 
				 try {
					   Thread.sleep(20); //Thread time
					 } catch (Exception e) {
						
						}
			}
			
		}
		
		//SET LABELS AND FROG
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
