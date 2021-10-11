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
	private BufferedImage originalImg1;
	private SpriteSheet planeImage11;
	private BufferedImage[] sprites11;
	private BufferedImage img11;
	private int w1;
	private int h1;
	private Missile shoot;
	public Missile() {
		loadImage1();
		
	}

		private void loadImage1() {
			try {

				originalImg1 = ImageIO.read(new File("src/Plane-assets/Missile2.png"));
				w1 = originalImg1.getWidth();
				h1 = originalImg1.getHeight();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void doDrawing1(Graphics r) {
			if (didPlaneFire) {

				r.drawImage(getOriginalImg1(), x1 - 55, y1 - 70, getOriginalImg1().getWidth(),
						getOriginalImg1().getHeight(), this);
				
				y1 -= 2;
			}

//				// Draw the image onto the Graphics reference
//				g.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), this);
//
//				// Move the x position left for next time
//				this.y += 2;
//
//				// Check to see if the image has gone off stage left
//				if (this.y >= image.getHeight() - 700) {
//
//					// If it has, line it back up so that its left edge is
//					// lined up to the right side of the other background image
//					this.y = (this.y - image.getHeight() * 2);
			else {

			}
		}

		public int getX1() {
			return x1;
		}

		public void setX1(int x) {
			x1 = x;
		}

		public int getY1() {
			return y1;
		}

		public void setY1(int y) {
			y1 = y;
		}

		public BufferedImage getOriginalImg1() {
			return originalImg1;
		}

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
