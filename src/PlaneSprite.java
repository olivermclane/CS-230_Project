/**
 *
 */

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

public class PlaneSprite extends SpriteSheet
		implements ActionListener, ImageObserver, Runnable, KeyListener, MouseInputListener {
	private static BufferedImage[] sprites1;
	private BufferedImage[] bankLeft;
	private BufferedImage[] leftLevelout;
	private BufferedImage[] bankRight;
	private BufferedImage[] rightLevelout;
	private Rectangle bounds;
	private boolean planeHit;
	private boolean planeDestroyed;
	private BufferedImage[] boom;
	private boolean atRightEdge;
	private boolean atLeftEdge;
	private SpriteSheet planeImage;
	private BufferedImage originalImg;
	private BufferedImage img1;
	private int x = 350;
	private int y = 600;
	private int w;
	private int h;
	private boolean planeRight;
	private boolean planeLeft;
	private boolean planeUp;
	private boolean planeDown;
	public boolean didPlaneFire = false;
	
	public List<Missile> missiles;
	private Missile mis;
	private boolean missileFired;
	private Missile[] miss;
	private boolean isPlaneHit= false;
	
	
	

	public PlaneSprite() {
		loadImage();
		new Thread(this).start();
		addKeyListener(this);
		missiles = new ArrayList<>();
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public Missile[] array() {
		miss = missiles.toArray(new Missile[0]);
		return miss;
	}

	public boolean didPlaneFire() {
		return didPlaneFire;
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

	@Override
	public Rectangle getBounds() {
		return new Rectangle(getxPosition()+5, getyPosition(),
				getPlane().getWidth()-8,
				getPlane().getHeight()-100);
	}

	public int getH() {
		return h;
	}

	public BufferedImage[] getLeftLevelout() {
		return leftLevelout;
	}

	public BufferedImage getPlane() {

		return img1;
	}


	public BufferedImage[] getRightLevelout() {
		return rightLevelout;
	}

	@Override
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
		return getxPosition() <=0;
	}

	public boolean isAtRightEdge() {
		return atRightEdge;
	}

	public boolean isPlaneDestroyed() {
		return planeDestroyed;
	}

	

	@Override
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
		if (e.getKeyCode() == KeyEvent.VK_A) {
			
			didPlaneFire = false;

		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
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
		if (e.getKeyCode() == KeyEvent.VK_A) {
			
			
			missileFired = true;
			didPlaneFire = true;
			
			
			
			

		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

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

	public boolean missileFired() {
		return missileFired;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setxPosition(e.getX());
		setyPosition(e.getY());
		

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setxPosition(e.getX());
		
		setyPosition(e.getY());
		

	}
		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}


	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseMoved(MouseEvent e) {

		setxPosition(e.getX()-55);
		setyPosition(e.getY()-34);

		
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		setxPosition(e.getX());
		setyPosition(e.getY());
		

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}


	public void moveDown() {
		if (y >= 680) {
			y = 680;
		} else {
			y += 3;
		}

	}

	public void moveLeft() {
		if (x <= -10) {
			x = -10;
		} else {
			x -= 3;
		}

	}

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

	public Missile projectile() {
		mis = new Missile();
		
		return mis;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @param atLeftEdge the atLeftEdge to set
	 */
	public void setAtLeftEdge(boolean atLeftEdge) {
		this.atLeftEdge = atLeftEdge;
	}

	@Override
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void setLeftLevelout(BufferedImage[] leftLevelout) {
		this.leftLevelout = leftLevelout;
	}

	public void setPlaneDestroyed(boolean planeDestroyed) {
		this.planeDestroyed = planeDestroyed;
	}

	public boolean isPlaneHit() {
		return isPlaneHit;
	}
	public void setPlaneHit(boolean newplaneHit) {
		planeHit = true;
	}

	public void setxPosition(int xPosition) {

		x = xPosition;
	}

	

	public void setyPosition(int yPosition) {
		y = yPosition;
	}

	/**
	 * 
	 */
	public void setisPlaneHit() {
		isPlaneHit = true;
		// TODO Auto-generated method stub
		
	}	
}
	

