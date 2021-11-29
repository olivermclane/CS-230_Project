import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
public class EnemySprite extends SpriteSheet
        implements ImageObserver {
    private final Color heathBar = Color.GREEN;
    private final int moveSpeedY = ThreadLocalRandom.current().nextInt(1, 3);
    public boolean didPlaneFire = false;
    public List<Missile> enemyMissiles;
    public boolean isBigEnemy;
    public boolean isSmallEnemy;
    private boolean planeHit;
    private int x;
    private boolean planeRight;
    private boolean planeLeft = true;
    private boolean planeUp = true;
    private boolean planeDown;
    private BufferedImage enemy;
    private boolean enemyDestroyed;
    private boolean missileFired;
    private int y;
    private int moveSpeedX = ThreadLocalRandom.current().nextInt(1, 4);
    private int maxAmmo;

    public EnemySprite() {
    }

    public EnemySprite(String file) {
        loadImage(file);
        enemyMissiles = new ArrayList<>();
    }

    public void setisEnemyHit() {
        boolean isEnemyHit = true;
    }

    public Missile projectile() {
        return new Missile();
    }

    public void doDrawing(Graphics g) {
        g.setColor(heathBar);
        g.fillRect(getxPosition() + enemy.getWidth() / 4, getyPosition() - 15, enemy.getWidth() / 2, 5);
        if (!planeRight && !planeLeft) {
            g.drawImage(enemy, getxPosition(), getyPosition(), this);
        }
        if (planeRight) {
            moveRight();
            g.drawImage(enemy, getxPosition(), getyPosition(), this);
        }
        if (planeLeft) {
            moveLeft();
            g.drawImage(enemy, getxPosition(), getyPosition(), this);
        }
        if (planeUp) {
            moveUp();
            g.drawImage(enemy, getxPosition(), getyPosition(), this);
        }
        if (planeDown) {
            moveDown();
            g.drawImage(enemy, getxPosition(), getyPosition(), this);
        }
    }

    public boolean didPlaneFire(boolean x) {
        return didPlaneFire = x;
    }

    public List<Missile> array() {
        return enemyMissiles;
    }

    public BufferedImage getPlane() {
        return enemy;
    }

    public int getH() {
        return enemy.getHeight();
    }

    public int getW() {
        return enemy.getWidth();
    }

    public int getxPosition() {
        return x;
    }

    public int getyPosition() {
        return y;
    }

    public Rectangle getBigBoundsX() {
        return null;
    }

    public Rectangle getBigBoundsY() {
        return null;
    }

    public boolean isEnemyDestroyed() {
        return enemyDestroyed;
    }

    public void setEnemyDestroyed(boolean b) {
        enemyDestroyed = b;
    }

    public void setMoveSpeedX(int moveX) {
        moveSpeedX = moveX;
    }

    public void moveLeft() {
        if (x <= 2) {
            x = 2;
            planeLeft = false;
            planeRight = true;
        } else {
            x -= moveSpeedX;
        }
    }

    public void moveUp() {
        if (y <= 0) {
            y = 0;
            planeDown = true;
            planeUp = false;
        } else {
            y -= moveSpeedY;
        }
    }

    public void moveDown() {
        if (y >= 400) {
            y = 400;
            planeDown = false;
            planeUp = true;
        } else {
            y += moveSpeedY;
        }
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveRight() {
        if (x >= 700 - getW() - 10) {
            x = 700 - getW() - 10;
            planeRight = false;
            planeLeft = true;
        } else {
            x += moveSpeedX;
        }
    }

    void loadImage(String file) {
        try {
            enemy = ImageIO.read(new File("src/Plane-assets/" + file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
