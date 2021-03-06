import java.awt.Rectangle;

public class Sprite {
		//data members 
	protected int spriteX, spriteY;
	protected String spriteName;
	protected int spriteW, spriteH;
	protected Rectangle r;
	//CONSTRUCTORS
	public Sprite() {
		super();
		r = new Rectangle(0,0,0,0);
	}
	
	public Sprite(int spriteX, int spriteY, String spriteName, int spriteW, int spriteH) {
		super();
		this.spriteX = spriteX;
		this.spriteY = spriteY;
		this.spriteName = spriteName;
		this.spriteW = spriteW;
		this.spriteH = spriteH;
		r = new Rectangle(spriteX,spriteY,spriteW,spriteH);
	}

	public int getSpriteX() {
		return spriteX;
	}
	public void setSpriteX(int spriteX) {
		this.spriteX = spriteX;
		r.x = this.spriteX;
	}
	public int getSpriteY() {
		return spriteY;
	}
	public void setSpriteY(int spriteY) {
		this.spriteY = spriteY;
		r.y = this.spriteY;
	}
	public String getSpriteName() {
		return spriteName;
	}
	public void setSpriteName(String spriteName) {
		this.spriteName = spriteName;
	}
	public int getSpriteW() {
		return spriteW;
	}
	public void setSpriteW(int spriteW) {
		this.spriteW = spriteW;
	}
	public int getSpriteH() {
		return spriteH;
	}
	public void setSpriteH(int spriteH) {
		this.spriteH = spriteH;
	}
	
	public Rectangle getRectangle() {
	return r;	
	}
	public void Display() {
		System.out.println("X,Y: " + spriteX + "," + spriteY);
	}
	
}
