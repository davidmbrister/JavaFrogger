import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frog extends Sprite {

	private JLabel FrogLabel;
	private boolean isFrogAlive = true;
	private boolean attached = false; //initially the frog is nowhere close to being attached to a log
	private Thread t;
	
	
    public boolean isFrogAttached() {
    	return attached;
    }
    //is the frog on a log
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

	public Frog() {
		super(GameProperties.BOARD_WIDTH/2,GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP, "frogAlive.png",50,50);	
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
		
		if (this.isFrogAlive() ) {
		
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					
					frogY += GameProperties.FROG_STEP;
					
					//check if off screen
					if (frogY > GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP) {
						frogY -= GameProperties.FROG_STEP;
						//
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
					
				}
					
				this.setSpriteX(frogX);				
				
				this.setSpriteY(frogY);
				
				FrogLabel.setLocation(this.spriteX, this.spriteY);
				
		}
	}
	
}
