import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnemySprite extends SpriteSheet
        implements ImageObserver {

    public boolean didPlaneFire = false;
    public List<Missile> enemyMissiles;
    private boolean planeHit;
    private int x;
    private boolean planeRight;
    private boolean planeLeft;
    private boolean planeUp;
    private boolean planeDown;
    private BufferedImage enemy;
    private boolean enemyDestroyed;
    private boolean missileFired;
    private int y;
    private int moveSpeedX;
    private int maxAmmo;
    public boolean isBigEnemy;
    public boolean isSmallEnemy;

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

//		if (!planeRight && !planeLeft) {
//        g.drawImage(enemy, getxPosition(), getyPosition(), this);
//		}
//        if (planeRight) {
//            moveRight();
			g.drawImage(enemy, getxPosition(), getyPosition(), this);
//        }
//        if (planeLeft) {
//            moveLeft();
//			g.drawImage(enemy, getxPosition(), getyPosition(), this);
//        }


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


    public List<Missile> array() {
        return enemyMissiles;
    }


    public BufferedImage getPlane() {

        return enemy;
    }
    public int getH() {
        return getHeight();
    }

    public int getW() {
        return enemy.getWidth();
    }

    public int getxPosition() {
        return x;
    }

    public int getyPosition() {
        ;
        return y;
    }

    public Rectangle getBigBoundsX() {
        return null;
    }


    public Rectangle getBigBoundsY(){
        return null;
    }
	public boolean isEnemyDestroyed() {
		return enemyDestroyed;
	}

    public void setEnemyDestroyed(boolean b) {
        enemyDestroyed = b;
    }

    public void setMoveSpeedX(int moveX){
        moveSpeedX = moveX;
    }
    public void moveLeft() {
        if (x <= 2) {
            x = 2;
        } else {
            x -= moveSpeedX;

        }

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

    public void moveRight() {
        if (x >= 700 - getW() - 10) {
            x = 700 - getW() - 10;
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
