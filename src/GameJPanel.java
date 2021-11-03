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
    private JLabel health;
    private int healthX = 200;
    private int ammoPlacement=570;
    private Font retroGame;
    private List<Missile> ammo;
    private int ammoAmount=650;
    private int ammoReload;

    public GameJPanel() {

        intiGamePanel();
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
private SmallEnemySprite smallEnemy;
public JLabel lifeCounter = new JLabel();
private tankEnemySprite tankEnemy;
public List <LifePowerup> LifeUpList = new ArrayList<LifePowerup>();
//public List <Powerup> WeaponUpList = new ArrayList<LifePowerup>();



	public GameJPanel() {
		
		intiGamePanel();
	}

	private void intiGamePanel() {
		try{
            Font retroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
          	retroGame = retroGame.deriveFont(Font.PLAIN,20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
		      	ge.registerFont(retroGame);
			      lifeCounter.setFont(retroGame);
        }catch(IOException e){
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
        ammo = plane.ammo();
        plane.missiles.add(new Missile());
        enemy = new EnemySprite();
        smallEnemy = new SmallEnemySprite();


        explosion = new ExplosionSprite();
        setDoubleBuffered(true);
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		back1.loadBackground(g);


        for (Missile missile : ammo) {
                missile.setX2(ammoPlacement);
                missile.setY2(25);
                missile.doImageDraw(g);
                if(ammoPlacement<ammoAmount){
                    ammoPlacement+=20;
                }
                else{
                    ammoPlacement=570;
                }

            }



        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString("Health: ", 10,20);
        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString("Ammo: ", 570,20);
        g.setColor(Color.GREEN);
        g.fillRect(10,25,healthX,20);

		explosion.setX(enemy.getxPosition()+50);
		explosion.setY(enemy.getyPosition()+50);
		if(enemy.isEnemyDestroyed()) {
			explosion.doDrawing(g);

			if (explosionTic < 8  && explosionCount == 0) {
				explosion.setExpCount(explosionTic);


				explosionTic++;
			}

		}
		for(LifePowerup p: LifeUpList){
			p.getBounds();
			p.collisionCheck(plane.getBounds());
			if(p.isCollided()){
				p.addLife(plane);
			}else{
				p.draw(g);
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



        Rectangle enemyArea2 = enemy.getBounds2();


		if (plane.didPlaneFire()&&!ammo.isEmpty()) {
			back.missileFired();

            if (!ammo.isEmpty()) {
                ammo.remove(0);
                ammoAmount-=20;
                Missile mis = plane.projectile();
                mis.setX(plane.getxPosition()+51);
                mis.setY(plane.getyPosition());
                plane.missiles.add(mis);
            }

            plane.didPlaneFire=false;

		}
		if (plane.didmissileFired()) {



            List<Missile> miss = plane.missiles();
            if (!miss.isEmpty()) {
                for (Missile m : miss) {

                    m.doDrawing1(g);

                    Rectangle misArea = m.getBounds();
                    Rectangle enemyArea = enemy.getBounds();



					if (misArea.intersects(enemyArea)) {
						plane.missiles.remove(m);
						back.planeHitsound();
						LifeUpList.add(new LifePowerup(enemy));
						System.out.println(enemy.getxPosition());
						System.out.println(enemy.getyPosition());
						enemy.setEnemyDestroyed(true);



                    }


                    if (m.isOffScreen()) {
                        plane.missiles.remove(m);
                    }
                }

            }

        }
        if(ammo.isEmpty()&&ammoReload>=99){
            plane.ammoLoad();
            ammo = plane.ammo();
            ammoPlacement=570;
            ammoAmount=650;
            ammoReload=0;
        }
		if (enemyCount >= 99 && !enemy.isEnemyDestroyed()) {
            Missile mis2 = enemy.projectile();
			mis2.setX2(enemy.getxPosition()+ 90);
			mis2.setY2(enemy.getyPosition()+100);
			enemy.enemyMissiles.add(mis2);
			back.missileFired();
			enemy.didPlaneFire(true);
		}


        if (enemy.didPlaneFire) {

            List<Missile> miss2 = enemy.array();
            for (Missile m2 : miss2) {


                m2.doDrawing2(g);
                Rectangle misArea2 = m2.getBounds2();
                Rectangle planeArea = plane.getBounds();

                if (misArea2.intersects(planeArea)) {
                    enemy.enemyMissiles.remove(m2);
                    back.planeHitsound();
                    plane.isHit();
                    plane.isDead();
                    healthX -=70;
                    break;

                }
                if (m2.isOffScreen2()) {
                    enemy.enemyMissiles.remove(m2);
                    break;
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
                if(ammo != null) {
                    if(ammo.isEmpty()) {
                        ammoReload += 1;
                    }
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
