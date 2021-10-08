/**
 * 
 */
package Time_Fighter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.Timer;
import javax.swing.event.MouseInputListener;

public class PlaneSprite extends SpriteSheet
		implements ActionListener, ImageObserver, Runnable, KeyListener, MouseInputListener {
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
	private boolean planeRight;
	private boolean planeLeft;
	private boolean planeUp;
	private boolean planeDown;

	public PlaneSprite() {
		loadImage();
		new Thread(this).start();
		addKeyListener(this);
	}

	@Override
	public void run() {

		while (true) {
			try {
				repaint();
				Thread.currentThread().sleep(8);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	public void doDrawing(Graphics g) {

		g.drawImage(getPlane(), getxPosition(), getyPosition(), this);
		if (planeRight) {
			moveRight();
		}
		if (planeLeft) {
			moveLeft();
		}

		if (planeUp) {
			moveUp();
		}
		if (planeDown) {
			moveDown();
		}

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
		if (getxPosition() <= 0) {

			return true;
		}
		return false;
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
		// if keys are pressed the move that direction
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			planeRight = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			planeLeft = true;

		}

		if (e.getKeyCode() == KeyEvent.VK_UP) {
			planeUp = true;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			planeDown = true;

		}
	}

	public void moveLeft() {
		if (x <= -10) {
			x = -10;
		} else {
			x -= 3;
		}

	}

	// same as player 1
	public void moveRight() {
		if (x >= 580) {
			x = 580;
		} else {
			x += 3;
		}

	}

	public void moveUp() {
		if (y <= 0) {
			y = 0;
		} else {
			y -= 3;
		}

	}

	public void moveDown() {
		if (y >= 680) {
			y = 680;
		} else {
			y += 3;
		}

	}

	public void keyReleased(KeyEvent e) {
		// when keys are released then stop moving
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			planeRight = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			planeLeft = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			planeUp = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			planeDown = false;

		}
	}

	private void loadImage() {
		try {
			originalImg = ImageIO.read(new File("src/Plane-assets/PlaneResize.png"));
			planeImage = new SpriteSheet(originalImg, 96, 68, 5, 1);
			sprites1 = planeImage.getSprites();
			img1 = sprites1[2];

			w = img1.getWidth();
			h = img1.getHeight();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
	public void setyPosition(int yPosition) {
		this.y = yPosition;
	}

	
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	
	public void mouseClicked(MouseEvent e) {
		this.setxPosition(e.getX());
		this.setyPosition(e.getY());
		System.out.println("test");
		
	}

	
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mousePressed(MouseEvent e) {
		this.setxPosition(e.getX());
		this.setyPosition(e.getY());
		System.out.println("test");
		
	}

	
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
	public void mouseDragged(MouseEvent e) {
		this.setxPosition(e.getX());
		this.setyPosition(e.getY());
		System.out.println("test");
		
	}

	
	public void mouseMoved(MouseEvent e) {
		
		this.setxPosition(e.getX());
		this.setyPosition(e.getY());
		System.out.println("test");
		
	}
}
	