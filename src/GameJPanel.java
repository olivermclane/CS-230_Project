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
private Missile mis2;
private Rectangle planeArea;
private int enemyCount;
private Missile[] miss2;
private Rectangle misArea2;
private Sound_effects back;

	public GameJPanel() {

		intiGamePanel();
	}

	private void intiGamePanel() {
		setFocusable(true);
		addKeyListener(new TAdapter());
		new Thread(this).start();
		addMouseMotionListener(new MAdapter());
		
		back = new Sound_effects();
		back.backGround();
		back1 = new ScrollingBackground();
		plane = new PlaneSprite();
		plane.missiles.add(new Missile());
		enemy = new EnemySprite();
		enemy.enemyMissiles.add(new Missile());
		
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		back1.loadBackground(g);
		
		
		
		if(!enemy.isEnemyHit())	{
			enemy.doDrawing(g);
			
		}
		else {
			enemy.setVisible(false);
		}
		if(!plane.isPlaneHit())	{
			plane.doDrawing(g);
			
		}
		else {
			plane.setVisible(false);
		}
		enemyArea = enemy.getBounds();
		planeArea = plane.getBounds();
		if (plane.didPlaneFire()) {
			back.missileFired();
			mis = plane.projectile();
			mis.setX(plane.getxPosition()+51);
			mis.setY(plane.getyPosition());
			plane.missiles.add(mis);
			plane.didPlaneFire=false;
			
		}
		if (plane.missileFired()) {
				
				miss = plane.array();
				for(Missile m: miss) {
					
					m.doDrawing1(g);
					misArea = m.getBounds();
					
					if (misArea.intersects(enemyArea)) {
						plane.missiles.remove(m);
						enemy.setisEnemyHit();
					}
					if(m.isOffScreen()) {
						plane.missiles.remove(m);
					}
				}
			}
			
		if (enemyCount == 100) {
			mis2 = plane.projectile();
			mis2.setX2(enemy.getxPosition()+60);
			mis2.setY2(enemy.getyPosition()+150);
			enemy.enemyMissiles.add(mis2);
			enemy.didPlaneFire(true);
		}

		
		
		if (enemy.didPlaneFire) {
			
			miss2 = enemy.array();
			for (Missile m2 : miss2) {
				misArea2 = m2.getBounds();
				planeArea = plane.getBounds();
				m2.doDrawing2(g);
				
				
				if (misArea2.intersects(planeArea)) {
					enemy.enemyMissiles.remove(m2);
					plane.setisPlaneHit();
				}
				if (m2.isOffScreen2()) {
					enemy.enemyMissiles.remove(m2);
				
				}
				
			} 
			
		}
	
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}

	@Override
	public void run() {

		while (true) {
			try {
				repaint();
				Thread.currentThread();
				Thread.sleep(10);
				enemyCount+=1;
				if(enemyCount==200) {
					enemyCount=0;
				}
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
