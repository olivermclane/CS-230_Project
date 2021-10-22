/**
 *
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.sql.Time;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class GameJPanel extends JPanel implements Runnable {
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			plane.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.keyReleased(e);
		}
	}

	
	
public Runnable runnable;
public Thread t;
private ScrollingBackground back1;
public PlaneSprite plane;
private Missile mis;
private Missile[] array;
private Missile[] miss;
private EnemySprite enemy;
private Rectangle misArea;
private Rectangle enemyArea;

	public GameJPanel() {

		intiGamePanel();
	}

	private void intiGamePanel() {
		setFocusable(true);
		addKeyListener(new TAdapter());
		new Thread(this).start();
		addMouseMotionListener(new MAdapter());
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("src/Plane-assets/blackCursor.png").getImage(), new Point(0, 0), "custom cursor"));
		Sound_effects back = new Sound_effects();
		back.backGround();
		back1 = new ScrollingBackground();
		plane = new PlaneSprite();
		plane.missiles.add(new Missile());
		enemy = new EnemySprite();
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		back1.loadBackground(g);
		
		plane.doDrawing(g);
		if (plane.missileFired()) {
			mis = plane.projectile();
			mis.setX(plane.getxPosition()-54);
			mis.setY(plane.getyPosition()-70);
			plane.missiles.add(mis);
			
			
		}
		if (plane.didPlaneFire()) {
				miss = plane.array();
				for(Missile m: miss) {
					m.doDrawing1(g);
					
				}
			}
		enemy.doDrawing(g);	
//		misArea = mis.getBounds();
//		enemyArea = enemy.getBounds();
//		if (misArea.intersects(enemyArea)) {
//			enemy.setVisible(false);
//		}
		
		
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void run() {

		while (true) {
			try {
				repaint();
				Thread.currentThread();
				Thread.sleep(15);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class MAdapter extends GameFrame implements MouseMotionListener, MouseInputListener {
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}

		@Override
		public void mousePressed(MouseEvent e) {
			plane.mousePressed(e);

		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}

		@Override
		public void mouseDragged(MouseEvent e) {
			plane.mouseDragged(e);

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			plane.mouseMoved(e);



		}

		@Override
		public void mouseClicked(MouseEvent e) {
			plane.mouseClicked(e);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
