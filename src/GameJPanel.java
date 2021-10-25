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
import javax.swing.event.MouseInputAdapter;
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
private ExplosionSprite explosion;
private int explosionCount;
private int explosionTic = 0;

	public GameJPanel() {

		intiGamePanel();
	}

	private void intiGamePanel() {
		setFocusable(true);
		addKeyListener(new TAdapter());
		new Thread(this).start();
		addMouseMotionListener(new MAdapter());
		addMouseListener(new MAdapter());
		back = new Sound_effects();
		back.backGround();
		back1 = new ScrollingBackground();
		plane = new PlaneSprite();
		plane.missiles.add(new Missile());
		enemy = new EnemySprite();
		enemy.enemyMissiles.add(new Missile());
		explosion = new ExplosionSprite();
	}

	@Override
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		back1.loadBackground(g);
		explosion.setX(enemy.getxPosition());
		explosion.setY(enemy.getyPosition());
		if(enemy.isEnemyDestroyed()) {
			explosion.doDrawing(g);
			if (explosionTic < 8  && explosionCount == 0) {
				explosion.setExpCount(explosionTic);
				
				
				explosionTic++;
			}
			
		}
		if(explosionTic == 8 && enemy.isEnemyDestroyed()) {
			
			explosion.setVisible(false);
			
		}
		if(!enemy.isEnemyDestroyed())	{
			enemy.doDrawing(g);
			
		}
		else {
			enemy.setEnemyDestroyed(true);
			
			enemy.setVisible(false);
		}
		explosion.setX(plane.getxPosition());
		explosion.setY(plane.getyPosition());
		if(plane.isPlaneHit()) {
			explosion.doDrawing(g);
			if (explosionTic < 8  && explosionCount == 0) {
				explosion.setExpCount(explosionTic);
				
				
				explosionTic++;
			}
			
		}
		if(explosionTic == 8 && enemy.isEnemyDestroyed()) {
			
			explosion.setVisible(false);
			
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
						back.planeHitsound();
						enemy.setEnemyDestroyed(true);
						
					}
					if(m.isOffScreen()) {
						plane.missiles.remove(m);
					}
				}
			}
			
		if (enemyCount == 100 && !enemy.isEnemyDestroyed()) {
			mis2 = plane.projectile();
			mis2.setX2(enemy.getxPosition()+60);
			mis2.setY2(enemy.getyPosition()+150);
			enemy.enemyMissiles.add(mis2);
			back.missileFired();
			enemy.didPlaneFire(true);
		}

		
		
		if (enemy.didPlaneFire ) {
			
			miss2 = enemy.array();
			for (Missile m2 : miss2) {
				
				
				m2.doDrawing2(g);
				misArea2 = m2.getBounds2();
				
				
				
				if (misArea2.intersects(planeArea)) {
					enemy.enemyMissiles.remove(m2);
					back.planeHitsound();
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
				explosionCount+=1;
				if(explosionCount==10) {
					explosionCount=0;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class MAdapter extends MouseInputAdapter implements MouseMotionListener, MouseInputListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			plane.mousePressed(e);
		}

		
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			plane.mouseReleased(e);
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			plane.mouseMoved(e);
		}

	}	

}
