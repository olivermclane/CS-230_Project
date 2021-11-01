import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SmallEnemySprite extends SpriteSheet
        implements ImageObserver {


    public boolean didPlaneFire = false;
    public List<Missile> enemyMissiles;
    private boolean planeHit;
    private boolean enemyDestroyed = false;
    private int x = 100;
    private int y = 250;
    private boolean planeRight = true;
    private boolean planeLeft = false;
    private boolean planeUp;
    private boolean planeDown;
    private boolean missileFired;
    private BufferedImage enemy;
    private int width;
    private int height;
    private boolean isEnemyHit = false;
    private BufferedImage smallEnemy;


    public SmallEnemySprite() {
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


//		if (planeUp) {
//			moveUp();
//		}
//		if (planeDown) {
//			moveDown();
//		}


    }

    public boolean didPlaneFire(boolean x) {
        return didPlaneFire = x;
    }

    public boolean missileFired() {
        return missileFired;
    }

    public Missile[] array() {
        return enemyMissiles.toArray(new Missile[0]);
    }


    @Override
    public Rectangle getBounds() {
        return new Rectangle(getxPosition() + 5, getyPosition(),
                getW(),
                getH());
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

//	public void moveUp() {
//		if (y <= 0) {
//			y = 0;
//		} else {
//			y -= 3;
//		}
//
//	}
//
//	public void moveDown() {
//		if (y >= 680) {
//			y = 680;
//		} else {
//			y += 3;
//		}
//
//	}

    public void setEnemyDestroyed(boolean newplaneDestroyed) {
        enemyDestroyed = newplaneDestroyed;
    }

    public boolean isPlaneHit() {
        return planeHit;
    }

    public void setPlaneHit(boolean newplaneHit) {
        planeHit = newplaneHit;
    }

    public void moveLeft() {
        if (x <= 0) {
            x = 0;
            planeLeft = false;
            planeRight = true;
        } else {
            x -= 2;
            planeLeft = true;
        }

    }

    public void moveRight() {
        if (x >= 700 - getW() - 10) {
            x = 700 - getW() - 10;
            planeLeft = true;
            planeRight = false;
        } else {
            x += 2;
            planeRight = true;
        }

    }

    private void loadImage() {

        try {


            smallEnemy = ImageIO.read(new File("src/Plane-assets/smallEnemies.png"));

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

