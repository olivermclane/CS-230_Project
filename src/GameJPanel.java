import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.List;

public class GameJPanel extends JPanel implements Runnable {


    public PlaneSprite plane;
    public JLabel lifeCounter = new JLabel();
    private ScrollingBackground back1;

    private EnemySprite enemy;
    private int enemyCount;
    private Sound_effects back;
    private ExplosionSprite explosion;
    private int explosionCount;
    private int explosionTic = 0;
    private SmallEnemySprite smallEnemy;
    private tankEnemySprite tankEnemy;
    public GameJPanel() {

        intiGamePanel();
    }

    private void intiGamePanel() {
        try {
            Font retroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
            retroGame = retroGame.deriveFont(Font.PLAIN, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(retroGame);
            lifeCounter.setFont(retroGame);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }


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
        smallEnemy = new SmallEnemySprite();
        tankEnemy = new tankEnemySprite();

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
		if(!smallEnemy.isEnemyDestroyed())	{
			smallEnemy.doDrawing(g);
			
		}
		else {
			smallEnemy.setEnemyDestroyed(true);
			
			smallEnemy.setVisible(false);
		}
		if(!tankEnemy.isEnemyDestroyed())	{
			tankEnemy.doDrawing(g);
			
		}
		else {
			tankEnemy.setEnemyDestroyed(true);
			
			tankEnemy.setVisible(false);
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
		if(plane.isPlaneHit() && plane.isDead()) {
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
			plane.isHit();
		}
        Rectangle enemyArea = enemy.getBounds();

        Rectangle planeArea = plane.getBounds();
        Rectangle enemyArea2 = enemy.getBounds2();
        Rectangle planeArea2 = plane.getBounds2();

		if (plane.didPlaneFire()) {
			back.missileFired();
            Missile mis = plane.projectile();
			mis.setX(plane.getxPosition()+51);
			mis.setY(plane.getyPosition());
			plane.missiles.add(mis);
			plane.didPlaneFire=false;
			
		}
		if (plane.missileFired()) {

            Missile[] miss = plane.array();
				for(Missile m: miss) {
					
					m.doDrawing1(g);

                    Rectangle misArea = m.getBounds();

				
					
					if (misArea.intersects(enemyArea)||misArea.intersects(enemyArea2)) {
						plane.missiles.remove(m);
						back.planeHitsound();
						enemy.setEnemyDestroyed(true);
						
					}
					if(m.isOffScreen()) {
						plane.missiles.remove(m);
					}
				}
			}
			
		if (enemyCount >= 99 && !enemy.isEnemyDestroyed()) {
            Missile mis2 = plane.projectile();
			mis2.setX2(enemy.getxPosition()+60);
			mis2.setY2(enemy.getyPosition()+150);
			enemy.enemyMissiles.add(mis2);
			back.missileFired();
			enemy.didPlaneFire(true);
		}


        if (enemy.didPlaneFire) {

            List<Missile> miss2 = enemy.array();
            for (Missile m2 : miss2) {


                m2.doDrawing2(g);
                Rectangle misArea2 = m2.getBounds2();


//                if (misArea2.intersects(planeArea)||misArea2.intersects(planeArea2)) {
//                    enemy.enemyMissiles.remove(m2);
//                    back.planeHitsound();
//                    plane.isHit();
//                    plane.isDead();
//                }
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
        int frameRate = 1000 / 40;
        long nextTime = System.currentTimeMillis() + frameRate;

        while (true) {
            if (System.currentTimeMillis() > nextTime) {



                enemyCount += 1;
                if (enemyCount >= 100) {
                    enemyCount = 0;
                }
                explosionCount += 1;
                if (explosionCount == 6) {
                    explosionCount = 0;
                }

                repaint();

                nextTime = System.currentTimeMillis() + frameRate;
            }
        }
    }

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
