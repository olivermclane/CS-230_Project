import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
public class tankEnemySprite extends SpriteSheet
        implements ImageObserver {
    public boolean didPlaneFire = false;
    public List<Missile> enemyMissiles;
    private boolean planeHit;
    private boolean enemyDestroyed = false;
    private int x = 100;
    private int y = 0;
    private boolean planeRight;
    private boolean planeLeft;
    private boolean planeUp = false;
    private boolean planeDown = true;
    private boolean missileFired;
    private int width;
    private int height;
    private boolean isEnemyHit = false;
    private BufferedImage smallEnemy;
    public tankEnemySprite() {
        loadImage();
        enemyMissiles = new ArrayList<>();
    }
    public void setisEnemyHit() {
        isEnemyHit = true;
    }
    public Missile projectile() {
        return new Missile();
    }
    public void doDrawing(Graphics g) {
        g.drawImage(getPlane(), getxPosition(), getyPosition(), this);
        if (planeRight) {
            moveRight();
        }
        if (planeLeft) {
            moveLeft();
        }
        if (planeUp) {
            moveUp();
        }
        if (planeDown) {
            moveDown();
        }
    }
    public boolean didPlaneFire(boolean x) {
        return didPlaneFire = x;
    }
    public boolean missileFired() {
        return missileFired;
    }
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getxPosition() + 5, getyPosition(),
                getW() - 8,
                getH() - 100);
    }
    @Override
    public void setBounds(Rectangle bounds) {
    }
    public boolean isEnemyHit() {
        return isEnemyHit;
    }
    public int getH() {
        return smallEnemy.getHeight();
    }
    public BufferedImage getPlane() {
        return smallEnemy;
    }
    public int getW() {
        return smallEnemy.getWidth();
    }
    public int getxPosition() {
        return x;
    }
    public void setxPosition(int xPosition) {
        x = xPosition;
    }
    public int getyPosition() {
        return y;
    }
    public void setyPosition(int yPosition) {
        y = yPosition;
    }
    public boolean isEnemyDestroyed() {
        return enemyDestroyed;
    }
    public void setEnemyDestroyed(boolean newplaneDestroyed) {
        enemyDestroyed = newplaneDestroyed;
    }
    public boolean isPlaneHit() {
        return planeHit;
    }
    public void moveLeft() {
        if (x <= -10) {
            x = -10;
//			planeLeft = true;
//			planeRight = false;
        } else {
            x -= 2;
//			planeLeft = true;
        }
    }
    public void moveRight() {
        if (x >= 580) {
            x = 580;
//			planeLeft = false;
//			planeRight = true;
        } else {
            x += 2;
//			planeRight = true;
        }
    }
    public void moveUp() {
        if (y < 0) {
            y = 0;
            planeUp = false;
            planeDown = true;
        } else {
            y -= 0;
            planeDown = true;
        }
    }
    public void moveDown() {
        if (y >= 680) {
            y = 680;
            planeUp = true;
            planeDown = false;
        } else {
            y += 1;
            planeUp = true;
        }
    }
    private void loadImage() {
        try {
            smallEnemy = ImageIO.read(new File("src/Plane-assets/groundEnemies.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }
}
