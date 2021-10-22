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
	private boolean didPlaneFire = false;
	
	public List<Missile> missiles;
	private Missile mis;
	private boolean missileFired;
	private Missile[] miss;
	
	
	

	public PlaneSprite() {
		loadImage();
		new Thread(this).start();
		addKeyListener(this);
		missiles = new ArrayList<>();
		
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

		if (planeUp) {
			moveUp();
		}
		if (planeDown) {
			moveDown();
		}
		

	}

	public boolean didPlaneFire() {
		return didPlaneFire;
	}
	public boolean missileFired() {
		return missileFired;
	}
	 
	public Missile[] array() {
		miss = missiles.toArray(new Missile[0]);
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
		return new Rectangle(getxPosition(), getyPosition(),
				getH(),
				getH());
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

	public boolean isPlaneHit() {
		return planeHit;
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
			
			didPlaneFire = true;
			missileFired = true;
			
			

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

	public void moveDown() {
		if (y >= 680) {
			y = 680;
		} else {
			y += 3;
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
			
			missileFired = false;
			
			
			

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

	public void setPlaneHit(boolean planeHit) {
		this.planeHit = planeHit;
	}


	public void setxPosition(int xPosition) {

		x = xPosition;
	}

	public void setyPosition(int yPosition) {
		y = yPosition;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mousePressed(MouseEvent e) {
		setxPosition(e.getX());
		setyPosition(e.getY());
		

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		setxPosition(e.getX());
		
		setyPosition(e.getY());
		

	}

	@Override
	public void mouseMoved(MouseEvent e) {

		setxPosition(e.getX()-55);
		setyPosition(e.getY()-34);

		
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		
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

//	public class Missile extends PlaneSprite {
//		private BufferedImage originalImg1;
//		private SpriteSheet planeImage11;
//		private BufferedImage[] sprites11;
//		private BufferedImage img11;
//		private int w1;
//		private int h1;
//		private Missile shoot;
//		public Missile() {
//			loadImage1();
//			
//		}
//
//		private void loadImage1() {
//			try {
//
//				originalImg1 = ImageIO.read(new File("src/Plane-assets/Missile2.png"));
//				w1 = originalImg1.getWidth();
//				h1 = originalImg1.getHeight();
//
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//
//		}
//
//		public void doDrawing1(Graphics r) {
//			if (didPlaneFire) {
//
//				r.drawImage(getOriginalImg1(), x1 - 55, y1 - 70, getOriginalImg1().getWidth(),
//						getOriginalImg1().getHeight(), this);
//				
//				y1 -= 2;
//			}
//
////				// Draw the image onto the Graphics reference
////				g.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), this);
////
////				// Move the x position left for next time
////				this.y += 2;
////
////				// Check to see if the image has gone off stage left
////				if (this.y >= image.getHeight() - 700) {
////
////					// If it has, line it back up so that its left edge is
////					// lined up to the right side of the other background image
////					this.y = (this.y - image.getHeight() * 2);
//			else {
//
//			}
//		}
//
//		public int getX1() {
//			return x1;
//		}
//
//		public void setX1(int x) {
//			x1 = x;
//		}
//
//		public int getY1() {
//			return y1;
//		}
//
//		public void setY1(int y) {
//			y1 = y;
//		}
//
//		public BufferedImage getOriginalImg1() {
//			return originalImg1;
//		}
//
//	}
//
//	@Override

}
