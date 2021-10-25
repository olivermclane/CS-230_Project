import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * 
 */

/**
 * @author dusti
 *
 */
public class ExplosionSprite extends SpriteSheet implements ImageObserver, Runnable {
	
	private BufferedImage originalImg;
	private SpriteSheet explosionImages;
	private BufferedImage[] explosionSprites;
	private BufferedImage oneExplosion;
	private int w;
	private int h;
	private int x;
	private int y;
	private BufferedImage explosionEffect;
	private int expCount = 0;

	public int getExpCount() {
		return expCount;
	}

	public void setExpCount(int newexpCount) {
		this.expCount = newexpCount;
	}

	ExplosionSprite(){
		loadImage();
	}
	
	private void loadImage() {
		try {
			originalImg = ImageIO.read(new File("src/Plane-assets/explosionSprite.png"));
			explosionImages = new SpriteSheet(originalImg, 82, 85, 4, 2);
			explosionSprites = explosionImages.getSprites();
			oneExplosion = explosionSprites[2];

			setW(oneExplosion.getWidth());
			setH(oneExplosion.getHeight());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public BufferedImage[] getExplosions() {
		return explosionSprites;
	}
	
	public void doDrawing(Graphics g) {
		
			explosionEffect = explosionSprites[getExpCount()];
			g.drawImage(explosionEffect, getX(), getY(), this);
			
			
		}
		
	
	
	
	public int getW() {
		return w;
	}

	/**
	 * @param w the w to set
	 */
	public void setW(int w) {
		this.w = w;
	}

	/**
	 * @return the h
	 */
	public int getH() {
		return h;
	}

	/**
	 * @param h the h to set
	 */
	public void setH(int h) {
		this.h = h;
	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}


		
}


