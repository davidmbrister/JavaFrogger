import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frog extends Sprite {

	private JLabel FrogLabel;
	private boolean isFrogAlive = true; // a frog starts being a frog by being alive...
	private boolean attached = false; //initially the frog is nowhere close to being attached to a log
	private Thread t;
	
	//CONSTRUCTOR
	public Frog() {
		super(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP, "frogAlive.png",50,50);	//starting position
	}
	
	//GETTERS/SETTERS
    public boolean isFrogAttached() { 	//is the frog on a log

    	return attached;
    }
    public void setFrogAttached(boolean attachedOrNot) {
    	
    	this.attached = attachedOrNot;
    	if(attachedOrNot) {
  		
    	}
    }
    
	public boolean isFrogAlive() {
		return isFrogAlive;
	}

	public void setFrogAlive(boolean bool) {
		this.isFrogAlive = bool;
	}
	
	public JLabel getFrogLabel() {
		return FrogLabel;
	}

	public void setFrogLabel(JLabel frogLabel) {
		this.FrogLabel = frogLabel;
	}
	
	public void setFrogCoords(int x, int y) {
		this.spriteX = x;
		this.spriteY = y;
		r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
	}
	
	
	public void moveFrog(KeyEvent e) {
		
		int frogX = this.spriteX;
		int frogY = this.spriteY;
		
		if (this.isFrogAlive() ) { //if frog is dead you can't move it
		
			
				if (e.getKeyCode() == KeyEvent.VK_DOWN) { 

					int prevY = this.getSpriteY(); //compare this against next Y to determine if jump is from log to land 
					System.out.println(this.getSpriteY()+"prevY");//TEST STATEMENT
					
					frogY += GameProperties.FROG_STEP;
					
					//check if off screen
					if (frogY > GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP) {
						frogY -= GameProperties.FROG_STEP;
						
					}
					
					this.setSpriteY(frogY);
					System.out.println(this.getSpriteY()+"nextY");//TEST STATEMENT
					
					// code to fix log-to-land jump
					//
					/*
					 * The purpose of the next series of nested Ifs is to make sure the frog stays within the divisible-by-50 game grid when 
					 * it makes a jump from a log to the land. The logs' continuous motion can bring a frog - which moves
					 * in discrete steps of 50px - to an x value that is not divisible by 50; 
					 * this means the frog's usual land boundaries (the walls) don't behave as they should because of its unaccounted-for x-coordinate offset.
					 * 
					 * All branches have been tested and proven successful with the coverage tool and some println statements.
					 *
					//
					 */
					//RECORD VALUE OF NEW Y AFTER PROCESSING THE KEY EVENT TO GET NEW Y POSITION
					int nextY = this.getSpriteY();
					//depending on the x-coord of the frog as it is jumping off the log, it will snap into the nearest discrete grid x-coordinate on land.
					if (nextY > GameProperties.TRACK_8_BASE && prevY == GameProperties.TRACK_8_BASE) {
						int logToLandFrogX = this.getSpriteX(); 
						//IF statements to snap frog back into predictable game grid when it arrives on land from river
						if(logToLandFrogX < 25) {
							System.out.println("less than 25");
							frogX = 0;									
						} else if (logToLandFrogX >= 25 && logToLandFrogX < 75) {
							System.out.println("greater than 25 less than 50");
							frogX = 50;
						} else if (logToLandFrogX >= 75 && logToLandFrogX < 125) {
							System.out.println("greater than 75 less than 1125");
							frogX = 100;			
						} else if (logToLandFrogX >= 125 && logToLandFrogX < 175) {
							System.out.println("greater than 125 less than 175");
							frogX = 150;			
						} else if (logToLandFrogX >= 175 && logToLandFrogX < 225) {
							System.out.println("greater than 175 less than 225");
							frogX = 200;			
						} else if (logToLandFrogX >= 225 && logToLandFrogX < 275) {
							System.out.println("greater than 225 less than 275");
							frogX = 250;			
						} else if (logToLandFrogX >= 275 && logToLandFrogX < 325) {
							System.out.println("greater than 275 less than 325");
							frogX = 300;			
						} else if (logToLandFrogX >= 325 && logToLandFrogX < 375) {
							System.out.println("greater than 325 less than 375");
							frogX = 350;			
						} else if (logToLandFrogX >= 375 && logToLandFrogX < 425) {
							System.out.println("greater than 375 less than 425");
							frogX = 400;			
						} else if (logToLandFrogX >= 425 && logToLandFrogX < 475) {
							System.out.println("greater than 425less than 475");
							frogX = 450;			
						} else if (logToLandFrogX >= 475 && logToLandFrogX < 525) {
							System.out.println("greater than 475");
							frogX = 500;			
						} else if (logToLandFrogX >= 525 && logToLandFrogX < 575) {
							System.out.println("greater than 525 less than 575");
							frogX = 550;			
						}		
					}												
				
				} else if (e.getKeyCode() == KeyEvent.VK_UP) {
					
					frogY -= GameProperties.FROG_STEP;
					if (frogY < 0 ) {
						frogY = 0;
						
					} 
					
				} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					
					frogX += GameProperties.FROG_STEP;
					if (frogX >= GameProperties.BOARD_WIDTH) {
						frogX -= GameProperties.FROG_STEP;
					
					}
					
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				
					frogX -= GameProperties.FROG_STEP;
					if (frogX < 0) {
						frogX += GameProperties.FROG_STEP;
					}
					System.out.println(this.getSpriteX());
				}
				
				
				//could use setFrogCoords as well
				this.setSpriteX(frogX);				
				
				//test
				System.out.println(this.getSpriteX());
				
				this.setSpriteY(frogY);
				
				FrogLabel.setLocation(this.spriteX, this.spriteY);
				
		}
	}	
}
