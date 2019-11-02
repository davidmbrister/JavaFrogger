import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/* The only thing that makes this different than the Car is it's runnable, i.e. 
 * the way the frog interacts with it, which makes this code a bit redundant.
 * To make it less redundant should I .. extract the Runnable interfaces and use one Object class for 
 * both cars and logs.?????
 *
 * */
 
public class Log extends Car implements Runnable{
	//CLASS MEMBERS
		//variable speed to be determined through constructor because I will have two types of cars
		private float speed;
		private Thread thread;
		private JLabel FrogLabel, LogLabel;
		private Frog frog;
		
		public Log() {
			super(0, 0, 1, "pinkCar.png", 80, 50);
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
		
		public void startCar() {
			thread = new Thread(this, "Car");
			thread.start();
		}
}
