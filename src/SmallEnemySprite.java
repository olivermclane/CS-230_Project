import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class SmallEnemySprite extends SpriteSheet
		implements ActionListener, ImageObserver, Runnable {
	private static BufferedImage[] sprites1;
	private BufferedImage[] bankLeft;
	private BufferedImage[] leftLevelout;
	private BufferedImage[] bankRight;
	private BufferedImage[] rightLevelout;
	private Rectangle bounds;
	private boolean planeHit;
	private boolean enemyDestroyed = false;
	private BufferedImage[] boom;
	private boolean atRightEdge;
	private boolean atLeftEdge;
	private SpriteSheet planeImage;
	private BufferedImage originalImg;
	private BufferedImage img1;
	private int x = 100;
	private int y = 250;
	private int w;
	private int h;
	private boolean planeRight = true;
	private boolean planeLeft = false;
	private boolean planeUp;
	private boolean planeDown;
	public boolean didPlaneFire = false;
	
	public List<Missile> enemyMissiles;
	private Missile mis;
	private boolean missileFired;
	private Missile[] miss;
	private BufferedImage enemy;
	private int width;
	private int height;
	private boolean isEnemyHit =false;
	private BufferedImage smallEnemy;
	
	
	

	public SmallEnemySprite() {
		loadImage();
		
		enemyMissiles = new ArrayList<>();
		
	}
	public void setisEnemyHit() {
		isEnemyHit = true;
	}

	public Missile projectile() {
		mis = new Missile();
		
		return mis;
	}
	public void doDrawing(Graphics g) {

		g.drawImage(getPlane(), getxPosition(), getyPosition(), this);
		if (planeRight) {
			moveRight();
		}
		if (planeLeft) {
			moveLeft();
		}
		
		

//		if (planeUp) {
//			moveUp();
//		}
//		if (planeDown) {
//			moveDown();
//		}
		

	}

	public boolean didPlaneFire(boolean x) {
		return didPlaneFire = x;
	}
	public boolean missileFired() {
		return missileFired;
	}
	 
	public Missile[] array() {
		miss = enemyMissiles.toArray(new Missile[0]);
		return miss;
	}

	public BufferedImage[] getBankLeft() {
		return bankLeft;
	}

	public BufferedImage[] getBankRight() {
		return bankRight;
	}

	public BufferedImage[] getBoom() {
		return boom;
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getxPosition()+5, getyPosition(),
				getW(),
				getH());
	}

	public boolean isEnemyHit() {
		return isEnemyHit ;
	}
	public int getH() {
		return smallEnemy.getHeight();
	}

	public BufferedImage[] getLeftLevelout() {
		return leftLevelout;
	}


	public BufferedImage getPlane() {

		return smallEnemy;
	}

	public BufferedImage[] getRightLevelout() {
		return rightLevelout;
	}

	@Override
	public BufferedImage[] getSprites() {
		return sprites1;
	}

	public int getW() {
		return smallEnemy.getWidth();
	}

	public int getxPosition() {
		return x;
	}

	public int getyPosition() {
		return y;
	}

	public boolean isAtLeftEdge() {
		return getxPosition() <=0;
	}

	public boolean isAtRightEdge() {
		return atRightEdge;
	}

	public boolean isEnemyDestroyed() {
		return enemyDestroyed;
	}

	public boolean isPlaneHit() {
		return planeHit;
	}

	
	public void moveLeft() {
		if (x <= 0) {
			x = 0;
			planeLeft = false;
			planeRight = true;
		} else {
			x -= 2;
			planeLeft = true;
		}

	}

	
	public void moveRight() {
		if (x >= 700-getW()-10) {
			x = 700-getW()-10;
			planeLeft = true;
			planeRight = false;
		} else {
			x += 2;
			planeRight = true;
		}

	}

//	public void moveUp() {
//		if (y <= 0) {
//			y = 0;
//		} else {
//			y -= 3;
//		}
//
//	}
//
//	public void moveDown() {
//		if (y >= 680) {
//			y = 680;
//		} else {
//			y += 3;
//		}
//
//	}

	
		private void loadImage() {
			
				try {

					
					smallEnemy = ImageIO.read(new File("src/Plane-assets/smallEnemies.png"));

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
	


	@Override
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}


	public void setLeftLevelout(BufferedImage[] leftLevelout) {
		this.leftLevelout = leftLevelout;
	}

	

	public void setEnemyDestroyed(boolean newplaneDestroyed) {
		enemyDestroyed = newplaneDestroyed;
	}

	public void setPlaneHit(boolean newplaneHit) {
		planeHit = newplaneHit;
	}


	public void setxPosition(int xPosition) {

		x = xPosition;
	}

	public void setyPosition(int yPosition) {
		y = yPosition;
	}

	@Override
	public void run() {
		
	}
		
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	

	/**
	 * @param atLeftEdge the atLeftEdge to set
	 */
	public void setAtLeftEdge(boolean atLeftEdge) {
		this.atLeftEdge = atLeftEdge;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int newheight) {
		height = newheight;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int newwidth) {
		width = newwidth;
	}


}
