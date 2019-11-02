import java.awt.Rectangle;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Frog extends Sprite  {

	private JLabel FrogLabel;
	private boolean isFrogAlive = true;
	
	

	public boolean isFrogAlive() {
		return isFrogAlive;
	}

	public void setFrogAlive(boolean isFrogAlive) {
		this.isFrogAlive = isFrogAlive;
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
	
	
	
	public void moveFrog(KeyEvent e) {
		//DoctorLabel.setLocation(0,0);
		int frogX = this.spriteX;
		int frogY = this.spriteY;
		
		if (this.isFrogAlive() ) {
		
				if (e.getKeyCode() == KeyEvent.VK_DOWN) {
					
					frogY += GameProperties.FROG_STEP;
					
					//check if off screen
					if (frogY > GameProperties.BOARD_HEIGHT - GameProperties.FROG_STEP) {
						frogY -= GameProperties.FROG_STEP;
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
					//System.out.println(frogX);
				//this.spriteX = drX;
				//this.spriteY = drY;
				this.setSpriteX(frogX);
				
				
				this.setSpriteY(frogY);
				
				FrogLabel.setLocation(this.spriteX, this.spriteY);
				//System.out.println(this.getRectangle());
				
			}
	}
}
