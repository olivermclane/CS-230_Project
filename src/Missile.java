/**
 * 
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



/**
 * @author dusti
 *
 */


public class Missile extends PlaneSprite {
	private int width;
	private int height;
	
	private Missile shoot;
	private BufferedImage missile;
	int x ;
	int y;
	
	public Missile(int planex,int planey) {
		x = planex - 55;
		y = planey - 70;
		loadImage1();
		
		
	}

		private void loadImage1() {
			try {

				missile = ImageIO.read(new File("src/Plane-assets/Missile2.png"));
				width = missile.getWidth();
				height = missile.getHeight();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void doDrawing1(Graphics r) {
			
				r.drawImage(missile, getX(),getY(),missile.getWidth(),
						missile.getHeight(), this);
				
				y -= 2;
			}

		
		

		public int getX() {
			return x;
		}

	
		

		public int getY() {
			return y;
		}

	

}
