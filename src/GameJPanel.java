import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.*;

/**
 * This class takes out most of our game's gameplay. This class will create the
 * enemys and the player sprite. All of our
 */
public class GameJPanel extends JPanel implements Runnable {
    public static PlaneSprite plane;
    public static int highScore;
    private static int gameReplay;
    private static JLabel endScore;
    private final int powerUpRate = 2;
    public JLabel lifeCounter = new JLabel();
    public List<LifePowerup> LifeUpList = new ArrayList<LifePowerup>();
    public boolean gameOver;
    public List<EnemySprite> enemyPlayers = new ArrayList<>();
    public List<ExplosionSprite> enemyExp = new ArrayList<>();
    private int score = 0;
    private String healthpercent = "100%";
    private ScrollingBackground back1;
    private int enemyCount;
    private Sound_effects back;
    private int explosionCount;
    private int healthX = 200;
    private int ammoPlacement = 570;
    private Font retroGame;
    private List<Missile> ammo;
    private int ammoAmount = 650;
    private int ammoReload;
    private int powerRandom;
    private ExplosionSprite enemyExplosion;
    private ExplosionSprite planeExplosion;
    private String playerName;
    private List<List> wavesList = new ArrayList<>();
    private int round = 0;

    /**
     * This is the GameJPanel constructor, when created it will load the waves,
     * and run the initGamePanel.
     */
    public GameJPanel() {
        intiGamePanel();
        loadWave();
        gameReplay += 1;
    }

    /**
     * This is the GamePanel initalization, this will set up all the fonts
     * and other options surroung our game such as enemies and player. Then the
     * method will
     * set up some of the JPanel settings.
     */
    private void intiGamePanel() {
        try {
            retroGame = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
            retroGame = retroGame.deriveFont(Font.PLAIN, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(retroGame);
            lifeCounter.setFont(retroGame);
            gameReplay += 1;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        setDoubleBuffered(true);
        setFocusable(true);
        addKeyListener(new TAdapter());
        new Thread(this).start();
        addMouseMotionListener(new MAdapter());
        addMouseListener(new MAdapter());
        back = new Sound_effects();
        if (gameReplay > 1) {
        } else {
            back.backGround();
        }
        back1 = new ScrollingBackground();
        plane = new PlaneSprite();
        ammo = plane.ammo();
        planeExplosion = new ExplosionSprite();
        enemyExplosion = new ExplosionSprite();
        if (endScore == null) {
        } else {
            endScore.setVisible(false);
        }
    }

    @Override
    /**
     * This method is extended from JComponent and carrys out much of the code.
     * In this method we manage most of the operations on the sprite, which includes
     * collision, loading waves, combat methods, and changing enemy and player
     * properties.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        back1.loadBackground(g);
        if (enemyPlayers.isEmpty()) {
            round++;
            if (round > 6) {
                setVisible(false);
                Menu.CentralPanel.setVisible(true);
                Menu.CentralPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                if (score > highScore) {
                    highScore = score;
                    playerName = Menu.player;
                }
                endScore = new JLabel(playerName + " " + "HighScore:" + " " + (highScore));
                endScore.setFont(Menu.RetroGame);
                endScore.setAlignmentX(Component.TOP_ALIGNMENT);
                endScore.setForeground(Color.DARK_GRAY);
                endScore.setVisible(true);
                Menu.CentralPanel.add(endScore);
                gameOver = false;
            } else {
                List<String> currentWave;
                try {
                    currentWave = wavesList.get(ThreadLocalRandom.current().nextInt(0, wavesList.size()));
                    System.out.println(ThreadLocalRandom.current().nextInt(0, wavesList.size()));
                    for (String e : currentWave) {
                        String[] enemy = e.split(",", 3);
                        if (enemy[0].equals("Big Enemy")) {
                            enemyPlayers.add(new BigEnemy(enemy[1], Integer.parseInt(enemy[2])));
                        }
                        if (enemy[0].equals("Small Enemy")) {
                            enemyPlayers.add(new SmallEnemy(enemy[1], Integer.parseInt(enemy[2])));
                        }

                    }
                } catch (Exception e) {
                    System.out.println("Failed to load wave: Line 127");
                    System.out.println(wavesList.size());
                    System.exit(-1);
                }
            }
        }
        for (Missile missile : ammo) {
            missile.setX2(ammoPlacement);
            missile.setY2(25);
            missile.doImageDraw(g);
            if (ammoPlacement < ammoAmount) {
                ammoPlacement += 20;
            } else {
                ammoPlacement = 570;
            }
        }
        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString("Health: ", 10, 20);
        g.setColor(Color.green);
        g.setFont(retroGame);
        g.drawString(healthpercent, 100, 20);
        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString("SCORE:", 300, 20);
        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString(String.valueOf(score), 400, 20);
        g.setColor(Color.white);
        g.setFont(retroGame);
        g.drawString("Ammo: ", 570, 20);
        g.setColor(Color.GREEN);
        g.fillRect(10, 25, healthX, 20);
        for (EnemySprite enemies : enemyPlayers) {
            if (enemyExplosion.getExplosionTic() >= 8 && enemies.isEnemyDestroyed()) {
                enemyExplosion.setVisible(false);
                enemyPlayers.remove(enemies);
                enemyExplosion.resetExplosionTic();
                break;
            }
            if (!enemies.isEnemyDestroyed() && !enemyPlayers.isEmpty()) {
                enemies.doDrawing(g);
            } else {
                enemyExplosion.setX(enemies.getxPosition() + (enemies.getW() / 2) - (enemyExplosion.getWidth() / 2));
                enemyExplosion.setY(enemies.getyPosition() + (enemies.getH() / 2) - (enemyExplosion.getHeight() / 2));
                enemyExplosion.doDrawing(g);
                if (enemyExplosion.getExplosionTic() < 8 && explosionCount == 0) {
                    enemyExplosion.setExpCount(enemyExplosion.getExplosionTic());
                    enemyExplosion.plusExplosionTic();
                }
            }
        }
        for (LifePowerup p : LifeUpList) {
            if (p.isLifePowerup) {
                p.getBounds();
                p.collisionCheck(plane.getBounds());
                if (p.isCollided()) {
                    p.addLife(plane);
                    if (healthX < 200) {
                        healthX += 50;
                        if (healthX == 200) {
                            healthpercent = "100%";
                        }
                        if (healthX == 150) {
                            healthpercent = "75%";
                        }
                        if (healthX == 100) {
                            healthpercent = "50%";
                        }
                        if (healthX == 50) {
                            healthpercent = "25%";
                        }
                        if (healthX == 0) {
                            healthpercent = "0%";
                        }
                    }
                } else {
                    p.draw(g);
                }
            }
        }
        if (!plane.isPlaneHit()) {
            plane.doDrawing(g);
        } else {
            plane.isHit();
        }
        if (plane.isPlaneHit() && plane.isDead()) {
            planeExplosion.setX(plane.getxPosition());
            planeExplosion.setY(plane.getyPosition());
            planeExplosion.doDrawing(g);
            if (planeExplosion.getExplosionTic() < 8 && explosionCount == 0) {
                planeExplosion.setExpCount(planeExplosion.getExplosionTic());
                planeExplosion.plusExplosionTic();
            }
            gameOver = true;
        }
        if (gameOver && planeExplosion.getExplosionTic() == 8) {
            setVisible(false);
            Menu.CentralPanel.setVisible(true);
            Menu.CentralPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            if (score > highScore) {
                highScore = score;
                playerName = Menu.player;
            }
            endScore = new JLabel(playerName + " " + "HighScore:" + " " + (highScore));
            endScore.setFont(Menu.RetroGame);
            endScore.setAlignmentX(Component.TOP_ALIGNMENT);
            endScore.setForeground(Color.DARK_GRAY);
            endScore.setVisible(true);
            Menu.CentralPanel.add(endScore);
            gameOver = false;
        }
        if (planeExplosion.getExplosionTic() == 8 && plane.isDead()) {
            planeExplosion.plusExplosionTic();
            planeExplosion.setVisible(false);
        }
        if (plane.didPlaneFire() && !ammo.isEmpty()) {
            back.missileFired();
            if (!ammo.isEmpty()) {
                Missile mis = ammo.remove(0);
                ammoAmount -= 20;
                mis.setX(plane.getxPosition() + 51);
                mis.setY(plane.getyPosition());
                plane.missiles.add(mis);
            }
            plane.didPlaneFire = false;
        }
        if (plane.didmissileFired()) {
            List<Missile> miss = plane.missiles();
            if (!miss.isEmpty()) {
                Set<Missile> collided = new HashSet<>();
                for (Missile m : miss) {
                    m.doDrawing1(g);
                    Rectangle misArea = m.getBounds();
                    for (EnemySprite enemies : enemyPlayers) {
                        if (enemies.isEnemyDestroyed()) {
                            continue;
                        }
                        Rectangle enemyArea = enemies.getBigBoundsX();
                        Rectangle enemyArea2 = enemies.getBigBoundsY();
                        if (misArea.intersects(enemyArea) || misArea.intersects(enemyArea2)) {
                            if (enemies.getLife() <= 0) {
                                collided.add(m);
                                back.planeHitsound();
                                if (powerRandom == powerUpRate) {
                                    LifeUpList.add(new LifePowerup(enemies));
                                }
                                enemies.setEnemyDestroyed(true);
                                score += 30;
                                break;
                            } else if (enemies.getLife() > 0) {
                                enemies.substractLife();
                            }
                        }
                    }
                }
                for (Missile m : collided) {
                    plane.missiles.remove(m);
                }
            }
        }
        if (ammo.isEmpty() && ammoReload >= 99) {
            plane.ammoLoad();
            ammo = plane.ammo();
            ammoPlacement = 570;
            ammoAmount = 650;
            ammoReload = 0;
        }
        for (EnemySprite enemies : enemyPlayers) {
            if (enemyCount >= 99 && !enemies.isEnemyDestroyed()) {
                Missile mis2 = enemies.projectile();
                mis2.setX2(enemies.getxPosition() + enemies.getW() / 2);
                mis2.setY2(enemies.getyPosition() + enemies.getH() - 2);
                enemies.enemyMissiles.add(mis2);
                back.missileFired();
                enemies.didPlaneFire(true);
            }
            if (enemies.didPlaneFire) {
                for (Missile m : enemies.array()) {
                    m.doDrawing2(g);
                    Rectangle misArea2 = m.getBounds2();
                    Rectangle planeArea = plane.getBounds();
                    if (misArea2.intersects(planeArea)) {
                        enemies.enemyMissiles.remove(m);
                        back.planeHitsound();
                        plane.isHit();
                        plane.isDead();
                        healthX -= 50;
                        if (healthX == 150) {
                            healthpercent = "75%";
                        }
                        if (healthX == 100) {
                            healthpercent = "50%";
                        }
                        if (healthX == 50) {
                            healthpercent = "25%";
                        }
                        if (healthX == 0) {
                            healthpercent = "0%";
                            gameOver = true;
                        }
                        break;
                    }
                    if (m.isOffScreen2()) {
                        enemies.enemyMissiles.remove(m);
                        break;
                    }
                }
            }
        }
        if (!plane.getKeyboardStatus()) {
            plane.setPlaneDown(false);
            plane.setPlaneRight(false);
            plane.setPlaneUp(false);
            plane.setPlaneLeft(false);
        }
        g.dispose();
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * This is our run method for GameJPanel, this will keep track of the framerate
     * and will repaint our images on the canvas.
     */
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
                if (ammo != null) {
                    if (ammo.isEmpty()) {
                        ammoReload += 1;
                    }
                }
                repaint();
                nextTime = System.currentTimeMillis() + frameRate;
            }
        }
    }

    /**
     * This method will read in the wave.txt and load
     * in the waves for the game. Method will pull the file in
     * and read each line one by one and will break it up into arraylists.
     */
    private void loadWave() {
        File waveFile = new File("src/wave.txt");
        try {
            Scanner wave = new Scanner(waveFile);
            List<String> addWave = new ArrayList<>();
            while (wave.hasNextLine()) {
                String data = wave.nextLine();
                // new wave
                if (data.equals("WAVE")) {
                    wavesList.add(addWave);
                    for (String e : addWave) {
                        System.out.println(e);
                    }
                    addWave = new ArrayList<>();
                    continue;
                } else if (!data.equals("WAVE")) {
                    addWave.add(data);

                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    /**
     * This TAdapter class tracks of our keey presses and will funnel them into the
     * PlaneSprite class
     * methods.
     */
    private class TAdapter extends KeyAdapter {

        /**
         * Invoked when a key is pressed
         * 
         * @param e the key being pressed
         */
        @Override
        public void keyPressed(KeyEvent e) {
            plane.keyPressed(e);
        }

        /**
         * Invoked when a key is being released
         * 
         * @param e the key being released
         */
        @Override
        public void keyReleased(KeyEvent e) {
            plane.keyReleased(e);
        }
    }

    /**
     * This class with manage the mouse presses and mouse
     * released as well as gathering the mousemove events.
     */
    private class MAdapter extends MouseInputAdapter {

        /**
         * This method will pass a Mouseevent to the planeclass to handle shooting.
         * 
         * @param e the mousebutton being pressed(left or right)
         */
        public void mousePressed(MouseEvent e) {
            plane.mousePressed(e);
        }

        /**
         * This method will pass a Mouseevent to the will help prevent the PlaneSprite
         * mass shooting.
         * 
         * @param e the mousebutton being released(left or right)
         */
        public void mouseReleased(MouseEvent e) {
            plane.mouseReleased(e);
        }

        /**
         * This method will pass a Mouseevent that will allow the player to move
         * their PlaneSprite using the mouse.
         * 
         * @param e the mouse being moved
         */
        @Override
        public void mouseMoved(MouseEvent e) {
            plane.mouseMoved(e);
        }
    }
}
