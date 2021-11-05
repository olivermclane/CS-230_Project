import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameJPanel extends JPanel implements Runnable {


    public PlaneSprite plane;
    public JLabel lifeCounter = new JLabel();
    private ScrollingBackground back1;

    private int enemyCount;
    private Sound_effects back;
    private ExplosionSprite explosion;
    private int explosionCount;
    private int explosionTic = 0;

    private int healthX = 200;
    private int ammoPlacement=570;
    private Font retroGame;
    private List<Missile> ammo;
    private int ammoAmount=650;
    private int ammoReload;
    public List <LifePowerup> LifeUpList = new ArrayList<LifePowerup>();
    //public List <Powerup> WeaponUpList = new ArrayList<LifePowerup>();
    public List <BigEnemy> enemyPlayers = new ArrayList<>();
    private BigEnemy bigEnemy;
    private Rectangle enemyArea;
    private Rectangle enemyArea2;
    private boolean resetGame = false;


    public GameJPanel() {

        intiGamePanel();
    }
    public boolean getResetGame(){
        return resetGame;
    }
	private void intiGamePanel() {
		try{
            retroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
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
        bigEnemy = new BigEnemy("Enemies.png");

        enemyPlayers.add(bigEnemy);


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
        for (EnemySprite enemy: enemyPlayers) {
            if(!enemy.isEnemyDestroyed())	{
                enemy.doDrawing(g);

            }
            else {
                enemy.setEnemyDestroyed(true);
            }
        }
        explosion.setX(bigEnemy.getxPosition()+50);
		explosion.setY(bigEnemy.getyPosition()+50);
		if(bigEnemy.isEnemyDestroyed()) {
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
                LifeUpList.remove(p);
                if(healthX<200) {
                    healthX += 70;
                }
                break;
            }else{
				p.draw(g);
			}
			
		}
		if(explosionTic == 8 && bigEnemy.isEnemyDestroyed()) {

			explosion.setVisible(false);

		}



		explosion.setX(plane.getxPosition());
		explosion.setY(plane.getyPosition());
		if(plane.isPlaneHit() && plane.isDead()) {
			explosion.doDrawing(g);
            resetGame = true;
			if (explosionTic < 8  && explosionCount == 0) {
				explosion.setExpCount(explosionTic);


				explosionTic++;
			}

		}
        if(resetGame&&explosionTic>7){
            Menu.panel.setVisible(false);

            new Menu();
            resetGame = false;
        }
		if(explosionTic == 8 && bigEnemy.isEnemyDestroyed()) {

			explosion.setVisible(false);

		}
		if(!plane.isPlaneHit())	{
			plane.doDrawing(g);

		}
		else {
			plane.isHit();
		}






		if (plane.didPlaneFire()&&!ammo.isEmpty()) {
			back.missileFired();

            if (!ammo.isEmpty()) {
                Missile mis = ammo.remove(0);
                ammoAmount-=20;
                mis.setX(plane.getxPosition()+51);
                mis.setY(plane.getyPosition());
                plane.missiles.add(mis);
            }

            plane.didPlaneFire=false;

		}
		if (plane.didmissileFired()) {



            List<Missile> miss = plane.missilesFired();
            if (!miss.isEmpty()) {
                for (Missile m : miss) {

                    m.doDrawing1(g);

                    Rectangle misArea = m.getBounds();

                    for (BigEnemy enemy: enemyPlayers) {



                            enemyArea = enemy.getBigBoundsX();
                            enemyArea2 = enemy.getBigBoundsY();
                            if (misArea.intersects(enemyArea) || misArea.intersects(enemyArea2)) {
                                plane.missiles.remove(m);
                                back.planeHitsound();
                                LifeUpList.add(new LifePowerup(enemy));
                                System.out.println(enemy.getxPosition());
                                System.out.println(enemy.getyPosition());
                                enemy.setEnemyDestroyed(true);
                                break;


                            }


                            if (m.isOffScreen()) {
                                plane.missiles.remove(m);
                                break;
                            }
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
		if (enemyCount >= 99 && !bigEnemy.isEnemyDestroyed()) {
            Missile mis2 = bigEnemy.projectile();
			mis2.setX2(bigEnemy.getxPosition()+ 90);
			mis2.setY2(bigEnemy.getyPosition()+100);
			bigEnemy.enemyMissiles.add(mis2);
			back.missileFired();
			bigEnemy.didPlaneFire(true);
		}


        if (bigEnemy.didPlaneFire) {

            List<Missile> miss2 = bigEnemy.array();
            for (Missile m2 : miss2) {


                m2.doDrawing2(g);
                Rectangle misArea2 = m2.getBounds2();
                Rectangle planeArea = plane.getBounds();

                if (misArea2.intersects(planeArea)) {
                    bigEnemy.enemyMissiles.remove(m2);
                    back.planeHitsound();
                    plane.isHit();
                    plane.isDead();
                    healthX -=70;
                    break;

                }
                if (m2.isOffScreen2()) {
                    bigEnemy.enemyMissiles.remove(m2);
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
