/**
 * 
 */
package Time_Fighter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.Timer;

public class PlaneSprite extends SpriteSheet implements ActionListener, ImageObserver {
	private static BufferedImage[] sprites1;
	private BufferedImage[] bankLeft;
	private BufferedImage[] leftLevelout;
	private BufferedImage[] bankRight;

	private BufferedImage[] rightLevelout;
	private Rectangle bounds;
	private int hitsTaken;
	private int rightMovement;
	private int leftMovement;
	private boolean planeHit;
	private boolean planeDestroyed;
	private BufferedImage[] boom;
	private boolean atRightEdge;
	private boolean atLeftEdge;
	private SpriteSheet planeImage;
	private BufferedImage originalImg;
	private JLabel planeImage1;
	private BufferedImage img;
	private BufferedImage img1;
	private Image img2;
	private Timer timer;
	private int dx;
	private int dy;
	private int x = 210;
	private int y = 500;
	private int w;
	private int h;

	public PlaneSprite() {
		loadImage();

	}

	public void doDrawing(Graphics g) {

		g.drawImage(getPlane(), getxPosition(), getyPosition(), this);

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

	public Rectangle getBounds() {
		return bounds;
	}

	public int getH() {
		return h;
	}

	public int getHitsTaken() {
		return hitsTaken;
	}

	public BufferedImage[] getLeftLevelout() {
		return leftLevelout;
	}

	public int getLeftMovement() {
		return leftMovement;
	}

	public BufferedImage getPlane() {

		return img1;
	}

	public BufferedImage[] getRightLevelout() {
		return rightLevelout;
	}

	public int getRightMovement() {
		return rightMovement;
	}

	public BufferedImage[] getSprites() {
		return sprites1;
	}

	public int getW() {
		return w;
	}

	public int getxPosition() {
		return x;
	}

	public int getyPosition() {
		return y;
	}

	public boolean isAtLeftEdge() {
		return atLeftEdge;
	}

	public boolean isAtRightEdge() {
		return atRightEdge;
	}

	public boolean isPlaneDestroyed() {
		return planeDestroyed;
	}

	public boolean isPlaneHit() {
		return planeHit;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
	}

	public void keyReleased(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	private void loadImage() {
		try {
			originalImg = ImageIO.read(new File("src/Time_Fighter/Plane-assets/Plane3.png"));
			planeImage = new SpriteSheet(originalImg, 200, 134, 5, 1);
			sprites1 = planeImage.getSprites();
			img1 = sprites1[2];

			w = img1.getWidth();
			h = img1.getHeight();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Timer timer = new Timer(8, this);
		timer.start();
	}

	public void move() {
		x += dx;
		y += dy;
		repaint(getxPosition() - 1, getyPosition() - 1, getW() + 2, getH() + 2);
	}

	public void setAtLeftEdge(boolean atLeftEdge) {
		this.atLeftEdge = atLeftEdge;
	}

	public void setAtRightEdge(boolean atRightEdge) {
		this.atRightEdge = atRightEdge;
	}

	public void setBankLeft(BufferedImage[] bankLeft) {
		this.bankLeft = bankLeft;
	}

	public void setBankRight(BufferedImage[] bankRight) {
		this.bankRight = bankRight;
	}

	public void setBoom(BufferedImage[] boom) {
		this.boom = boom;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setHitsTaken(int hitsTaken) {
		this.hitsTaken = hitsTaken;
	}

	public void setLeftLevelout(BufferedImage[] leftLevelout) {
		this.leftLevelout = leftLevelout;
	}

	public void setLeftMovement(int leftMovement) {
		this.leftMovement = leftMovement;
	}

	public void setPlaneDestroyed(boolean planeDestroyed) {
		this.planeDestroyed = planeDestroyed;
	}

	public void setPlaneHit(boolean planeHit) {
		this.planeHit = planeHit;
	}

	public void setRightLevelout(BufferedImage[] rightLevelout) {
		this.rightLevelout = rightLevelout;
	}

	public void setRightMovement(int rightMovement) {
		this.rightMovement = rightMovement;
	}

	public void setxPosition(int xPosition) {
		this.x = xPosition;
	}

}
