import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameJPanel extends JPanel implements Runnable {


    public PlaneSprite plane;
    public JLabel lifeCounter = new JLabel();
    private ScrollingBackground back1;

    private EnemySprite enemy;
    private int enemyCount;
    private Sound_effects back;

    private int explosionCount;
    private int explosionTic = 0;
    private SmallEnemySprite smallEnemy;
    // private tankEnemySprite tankEnemy;
    // private JLabel health;
    private int healthX = 200;
    private int ammoPlacement=570;
    private Font retroGame;
    private List<Missile> ammo;
	public List<LifePowerup> LifeUpList = new ArrayList<LifePowerup>();
    private int ammoAmount=650;
    private int ammoReload;
    private final int powerUpRate = 2;
    private int powerRandom;
    private Random puDrop = new Random();

    private boolean allowDrop;
    private ExplosionSprite enemyExplosion;
    private ExplosionSprite smallEnemyExplosion;
    private ExplosionSprite planeExplosion;
    public boolean gameOver; 
    

    //public List <Powerup> WeaponUpList = new ArrayList<LifePowerup>();
    public GameJPanel() {

        intiGamePanel();
    }

    private void intiGamePanel() {
        try {
            retroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
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
        ammo = plane.ammo();
        plane.missiles.add(new Missile());
        enemy = new EnemySprite();
        smallEnemy = new SmallEnemySprite();

        smallEnemyExplosion = new ExplosionSprite();
        planeExplosion = new ExplosionSprite();
        enemyExplosion = new ExplosionSprite();
        setDoubleBuffered(true);
    }

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		back1.loadBackground(g);
        powerRandom = puDrop.nextInt(8)+1;

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

        if(!enemy.isEnemyDestroyed())	{
            enemy.doDrawing(g);

        }
        else {
            enemy.setEnemyDestroyed(true);

            enemy.setVisible(false);

        }
		if(enemy.isEnemyDestroyed()) {
            enemyExplosion.setX(enemy.getxPosition()+50);
            enemyExplosion.setY(enemy.getyPosition()+50);
            enemyExplosion.doDrawing(g);

			if (enemyExplosion.getExplosionTic() < 8  && explosionCount == 0) {
                enemyExplosion.setExpCount(enemyExplosion.getExplosionTic());


				enemyExplosion.plusExplosionTic();
			}

		}
        if(enemyExplosion.getExplosionTic() == 8 && enemy.isEnemyDestroyed()) {

            enemyExplosion.setVisible(false);
            enemyExplosion.plusExplosionTic();
        }
        if(!smallEnemy.isEnemyDestroyed())	{
            smallEnemy.doDrawing(g);

        }
        else {
            smallEnemy.setEnemyDestroyed(true);

            smallEnemy.setVisible(false);
        }
        if(smallEnemy.isEnemyDestroyed()) {
            smallEnemyExplosion.setX(smallEnemy.getxPosition()+3);
            smallEnemyExplosion.setY(smallEnemy.getyPosition()+3);
            smallEnemyExplosion.doDrawing(g);

            if (smallEnemyExplosion.getExplosionTic() < 8  && explosionCount == 0) {
                smallEnemyExplosion.setExpCount(smallEnemyExplosion.getExplosionTic());
                smallEnemyExplosion.plusExplosionTic();


            }

        }
        if(smallEnemyExplosion.getExplosionTic() == 8 && smallEnemy.isEnemyDestroyed()) {

            smallEnemyExplosion.setVisible(false);
            smallEnemyExplosion.plusExplosionTic();

        }
		for(LifePowerup p: LifeUpList){
            if(p.isLifePowerup){
                p.getBounds();
                p.collisionCheck(plane.getBounds());

                if(p.isCollided()){
                    p.addLife(plane);
                    if(healthX<200) {
                        healthX += 70;
                    }
                } else{
                    p.draw(g);
                }
            }
			
		}


        if(!plane.isPlaneHit())	{
            plane.doDrawing(g);

        }
        else {
            plane.isHit();
        }


		if(plane.isPlaneHit() && plane.isDead()) {

            planeExplosion.setX(plane.getxPosition());
            planeExplosion.setY(plane.getyPosition());
            planeExplosion.doDrawing(g);
            gameOver = true;

			if (planeExplosion.getExplosionTic() < 8  && explosionCount == 0) {
                planeExplosion.setExpCount(planeExplosion.getExplosionTic());

                
				planeExplosion.plusExplosionTic();
			}
        }
        if(gameOver && planeExplosion.getExplosionTic()==8){

            setVisible(false);

            Menu.CentralPanel.setVisible(true);
            Menu.CentralPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));

            System.out.println("Game Exit");

            gameOver = false;

        }

		
		if(planeExplosion.getExplosionTic() == 8 && plane.isDead()) {

            planeExplosion.plusExplosionTic();
			planeExplosion.setVisible(false);

		}



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

                    if(!enemy.isEnemyDestroyed()) {
                        Rectangle enemyArea = enemy.getBounds();
                        Rectangle enemyArea2 = enemy.getBounds2();

                        if (misArea.intersects(enemyArea)||misArea.intersects(enemyArea2)) {
                            plane.missiles.remove(m);
                            back.planeHitsound();
                            if (powerRandom == powerUpRate) {
                                LifeUpList.add(new LifePowerup(enemy));
                            }
                            enemy.setEnemyDestroyed(true);
                            break;
                        }
                    }
                    Rectangle smallEnemyArea = smallEnemy.getBounds();
                    if (misArea.intersects(smallEnemyArea)) {
                        plane.missiles.remove(m);
                        back.planeHitsound();
                        smallEnemy.setEnemyDestroyed(true);
                        break;

                    }

                    if (m.isOffScreen()) {
                        plane.missiles.remove(m);
                        break;
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
        if(!plane.getKeyboardStatus()){
            plane.setPlaneDown(false);
            plane.setPlaneRight(false);
            plane.setPlaneUp(false);
            plane.setPlaneLeft(false);
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

    private class MAdapter extends MouseInputAdapter {

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
