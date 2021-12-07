import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.*;
public class GameJPanel extends JPanel implements Runnable {
    public static PlaneSprite plane;
    public static int highScore;
    private static int gameReplay;
    private static JLabel endScore;
    private final int powerUpRate = 2;
    private final Random puDrop = new Random();
    private final List<String> highScores = new ArrayList<String>();
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
    // private tankEnemySprite tankEnemy;
    // private JLabel health;
    private int healthX = 200;
    private int ammoPlacement = 570;
    private Font retroGame;
    private List<Missile> ammo;
    private int ammoAmount = 650;
    private int ammoReload;
    private int powerRandom;
    private ExplosionSprite enemyExplosion;
    private ExplosionSprite planeExplosion;
    private BigEnemy bigEnemy;
    private SmallEnemy smallEnemy;
    private BigEnemy bigEnemy2;
    private SmallEnemy smallEnemy2;
    private BigEnemy bigEnemy3;
    // public List <Powerup> WeaponUpList = new ArrayList<LifePowerup>();
    private SmallEnemy smallEnemy3;
    private String playerName;

    /**
     *
     */
    public GameJPanel() {
        intiGamePanel();
        gameReplay += 1;
    }

    /**
     *
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
        bigEnemy = new BigEnemy("Enemies.png");
        smallEnemy = new SmallEnemy("smallEnemies.png");
        enemyPlayers.add(bigEnemy);
        enemyPlayers.add(smallEnemy);
        bigEnemy2 = new BigEnemy("Enemies.png");
        smallEnemy2 = new SmallEnemy("smallEnemies.png");
        enemyPlayers.add(bigEnemy);
        enemyPlayers.add(smallEnemy);
        bigEnemy3 = new BigEnemy("Enemies.png");
        smallEnemy3 = new SmallEnemy("smallEnemies.png");
        enemyPlayers.add(bigEnemy2);
        enemyPlayers.add(smallEnemy2);
        enemyPlayers.add(bigEnemy3);
        enemyPlayers.add(smallEnemy3);
        if (endScore == null) {
        } else {
            endScore.setVisible(false);
        }
    }

    @Override
    /**
     * This method is extended from JComponent and carrys out much of the code.
     * In this method we manage most of the operations on the sprite, which includes
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        back1.loadBackground(g);
        powerRandom = puDrop.nextInt(8) + 1;
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
            gameOver = true;
            if (planeExplosion.getExplosionTic() < 8 && explosionCount == 0) {
                planeExplosion.setExpCount(planeExplosion.getExplosionTic());
                planeExplosion.plusExplosionTic();
            }
        }
        if (gameOver && planeExplosion.getExplosionTic() == 8 || enemyPlayers.isEmpty()) {
            setVisible(false);
            for (int i = 0; i < 5; i++) {
                Menu.CentralPanel.remove(Menu.highScoreNames);
            }
            highScore = score;
            playerName = Menu.player;
            endScore = new JLabel(playerName + " " + "Player HighScore:" + " " + (highScore));
            endScore.setFont(Menu.RetroGame);
            endScore.setAlignmentX(Component.TOP_ALIGNMENT);
            endScore.setForeground(Color.DARK_GRAY);
            endScore.setVisible(true);
//            try {
//                File myObj = new File("src/TextFiles/HighScores.txt");
//                Scanner myReader = new Scanner(myObj);
//                while (myReader.hasNextLine()) {
//                    highScores.add(myReader.nextLine());
//                }
//                myReader.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter("src/TextFiles/HighScores.txt"));
                for (JLabel score : Menu.highScoreList) {
                    String playerScore = score.getText();
                    if (Integer.parseInt(playerScore.substring(6, 9)) < highScore) {
                        score.setText(playerName + " : " + highScore);
                        System.out.println("Successfully wrote to the file.");
                        highScore = 0;
                        break;
                    }
                }
                int counter = 0;
                for (JLabel score : Menu.highScoreList) {
                    bw.write(score.getText());
                    counter++;
                    if (counter < 5) {
                        bw.newLine();
                    }
                }
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
//            try {
//                File myObj = new File("src/TextFiles/HighScores.txt");
//                Scanner myReader = new Scanner(myObj);
//                while (myReader.hasNextLine()) {
//                    highScores.add(myReader.nextLine());
//                }
//                myReader.close();
//            } catch (FileNotFoundException e) {
//                System.out.println("An error occurred.");
//                e.printStackTrace();
//            }
            Menu.CentralPanel.add(endScore);
            Menu.CentralPanel.setVisible(true);
            Menu.CentralPanel.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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
                            collided.add(m);
                            back.planeHitsound();
                            if (powerRandom == powerUpRate) {
                                LifeUpList.add(new LifePowerup(enemies));
                            }
                            enemies.setEnemyDestroyed(true);
                            score += 30;
                            break;
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
