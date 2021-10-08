/**
 * 
 */
package Time_Fighter;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author dusti
 *
 */
public class Bullets extends GameJPanel {
	
	private BufferedImage originalImg1;
	private SpriteSheet planeImage11;
	private BufferedImage[] sprites11;
	private BufferedImage img11;
	private int w1;
	private int h1;
	private int x1 = 100;
	private int y1 = 100;

	public int getX1() {
		return x1;
	}

	public void setX1(int x) {
		this.x1 = x;
	}

	public int getY1() {
		return y1;
	}

	public void setY1(int y) {
		this.y1 = y;
	}

	public Bullets() {
		loadImage1();
//		x1= plane.getxPosition();
//		y1= plane.getyPosition();
		}

	private void loadImage1() {
		try {
			originalImg1 = ImageIO.read(new File("src/Plane-assets/Missile2.png"));
//			planeImage11 = new SpriteSheet(originalImg1, 83, 21, 1, 1);
//			sprites11 = planeImage11.getSprites();
//			img11 = sprites11[0];

			w1 = originalImg1.getWidth();
			h1 = originalImg1.getHeight();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public BufferedImage getOriginalImg1() {
		return originalImg1;
	}

	public void setOriginalImg1(BufferedImage originalImg) {
		this.originalImg1 = originalImg;
	}

	public SpriteSheet getPlaneImage1() {
		return planeImage11;
	}

	public void setPlaneImage1(SpriteSheet planeImage) {
		this.planeImage11 = planeImage;
	}

	public BufferedImage[] getSprites11() {
		return sprites11;
	}

	public void setSprites11(BufferedImage[] sprites1) {
		this.sprites11 = sprites1;
	}

	public BufferedImage getImg11() {
		return img11;
	}

	public void setImg11(BufferedImage img1) {
		this.img11 = img1;
	}

	public int getW1() {
		return w1;
	}

	public void setW1(int w) {
		this.w1 = w;
	}

	public int getH1() {
		return h1;
	}

	public void setH1(int h) {
		this.h1 = h;
	}

	public void doDrawing1(Graphics r) {

		r.drawImage(getOriginalImg1(), x1, y1, this);
		System.out.println("BUllets");
	}

}
