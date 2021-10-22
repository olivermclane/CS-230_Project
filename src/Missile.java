
/**
 * 
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @author dusti
 *
 */

public class Missile implements ImageObserver{
	private int width;
	private int height;
	private BufferedImage missile;
	private int x;
	private int y;

	public Missile() {

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

	public boolean isOffScreen() {
		if (y < 0) {
			return true;
		} else {
			return false;
		}
	}

	public void doDrawing1(Graphics r) {

		r.drawImage(getMissile(), getX(), getY(), width, height, this);

		y -= 2;
	}

	public void setX(int newx) {
		x = newx;
	}

	public void setY(int newy) {
		y = newy;
	}

	public int getY() {
		return y;
	}

	public int getX() {
		return x;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public Rectangle getBounds() {
		System.out.println(x+" "+ y + " " + width + " " + height);
		return new Rectangle(x, y, width, height);
	}

	public BufferedImage getMissile() {
		return missile;
	}

	public void setMissile(BufferedImage missile) {
		this.missile = missile;
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
