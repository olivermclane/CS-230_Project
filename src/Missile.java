
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
	private int width2;
	private int height2;
	private BufferedImage missile;
	private int x;
	private int y;
	
	private BufferedImage enemyMissile;
	private int y2;
	private int x2;
	public Missile() {

		loadImage1();
		loadImage2();
	}

	private void loadImage1() {
		try {

			missile = ImageIO.read(new File("src/Plane-assets/Missile2.png"));
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	private void loadImage2() {
		try {

			enemyMissile = ImageIO.read(new File("src/Plane-assets/EnemyMissile2.png"));
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public BufferedImage getEnemyMissile() {
		return enemyMissile;
	}

	public boolean isOffScreen() {
		if (y < 0) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isOffScreen2() {
		if (y > 800) {
			return true;
		} else {
			return false;
		}
	}

	public void doDrawing1(Graphics r) {

		r.drawImage(getMissile(), getX(), getY(), getWidth(), getHeight(), this);

		y -= 2;
	}
	public void doDrawing2(Graphics s) {
		s.drawImage(getEnemyMissile(), getX2(), getY2(), getWidth2(), getHeight2(), this);

		y2 +=4 ;
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
		return missile.getWidth();
	}

	public int getHeight() {
		return missile.getHeight();
	}
	
	public Rectangle getBounds() {
		
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
		
	}
	
	public Rectangle getBounds2() {
		
		return new Rectangle(getX2(), getY2(), getWidth(), getHeight());
		
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

	/**
	 * @return the y2
	 */
	public int getY2() {
		return y2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(int newy2) {
		y2 = newy2;
	}

	/**
	 * @return the x2
	 */
	public int getX2() {
		return x2;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(int newx2) {
		x2 = newx2;
	}

	/**
	 * @return the width2
	 */
	public int getWidth2() {
		return getEnemyMissile().getWidth();
	}

	/**
	 * @param width2 the width2 to set
	 */
	public void setWidth2(int width2) {
		this.width2 = width2;
	}

	/**
	 * @return the height2
	 */
	public int getHeight2() {
		return getEnemyMissile().getHeight();
	}

	/**
	 * @param height2 the height2 to set
	 */
	public void setHeight2(int height2) {
		this.height2 = height2;
	}

}
