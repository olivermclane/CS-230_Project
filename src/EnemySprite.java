import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This is the parent class for BigEnemy and SmallEnemy.
 * This holds shared methods and shared variables.
 */
public class EnemySprite extends SpriteSheet implements ImageObserver {
    private final Color heathBar = Color.GREEN;
    private final int moveSpeedY = ThreadLocalRandom.current().nextInt(1, 3);
    public boolean didPlaneFire = false;
    public List<Missile> enemyMissiles;
    public boolean isBigEnemy;
    public boolean isSmallEnemy;
    private int x;
    private boolean planeRight;
    private boolean planeLeft = true;
    private boolean planeUp = true;
    private boolean planeDown;
    private BufferedImage enemy;
    private boolean enemyDestroyed;
    private int y;
    private int moveSpeedX = ThreadLocalRandom.current().nextInt(1, 4);

    /**
     * This is the default constructor for
     * a parent class of BigEnemy and SmallEnemy
     */
    public EnemySprite() {
    }

    /**
     * This is the parent consturctor and will set the image of
     * the sprite.
     * 
     * @param file this is the location of the image file for the sprite
     */
    public EnemySprite(String file) {
        loadImage(file);
        enemyMissiles = new ArrayList<>();
    }

    /**
     * Tis is the missle for the enemy.
     * 
     * @return a new missle for the enemy
     */
    public Missile projectile() {
        return new Missile();
    }

    /**
     * This method will draw the enemysprite on the canas
     * and move it around the canvas.
     * 
     * @param g this is the canvas for the enemysprite to get drawn on.
     */
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

    /**
     * THis method takes care of settings location of the missle location.
     * If the enemy fires it sets a x location for the missle.
     * 
     * @param x is a true or false value for it the eney fired.
     * @return reutnrs the location of the missles to shoot.
     */
    public boolean didPlaneFire(boolean x) {
        return didPlaneFire = x;
    }

    /**
     * This retunrs the List of missles controled by the enemy.
     * 
     * @return a list of missles fired by the enemy
     */
    public List<Missile> array() {
        return enemyMissiles;
    }

    /**
     * 
     * 
     * @return
     */
    public BufferedImage getPlane() {
        return enemy;
    }

    /**
     * This method will return the height of the sprite.
     * 
     * @return returns the height of the enemy sprite.
     */
    public int getH() {
        return enemy.getHeight();
    }

    /**
     * This method will return the width of the sprite.
     * 
     * @return reutnrs the width of the enemy sprite.
     */
    public int getW() {
        return enemy.getWidth();
    }

    /**
     * This method will return the x point of the enemy sprite.
     * 
     * @return returns the x location of the sprite.
     */
    public int getxPosition() {
        return x;
    }

    /**
     * This methods will return the y point of the enemy sprite.
     * 
     * @return returns the y location of the sprite.
     */
    public int getyPosition() {
        return y;
    }

    /**
     * This is the parents class shared method for getting
     * the bounds for the wings of the sprite.
     * 
     * @return the bounds of the wings of the enemy sprite.
     */
    public Rectangle getBigBoundsX() {
        return null;
    }

    /**
     * This is the parents class shared method for getting
     * the bounds of the body of the eneny sprite.
     * 
     * @return the bounds of the body of the enemy sprite.
     */
    public Rectangle getBigBoundsY() {
        return null;
    }

    /**
     * This will return if the enemy sprite was destoryed.
     * 
     * @return a boolean for if the sprite was destoryed/true or
     *         still alive/false.
     */
    public boolean isEnemyDestroyed() {
        return enemyDestroyed;
    }

    /**
     * This method allow you to set if the plane has been
     * destoryed.
     * 
     * @param b this is the booolean passed in to set if
     *          it was hit or not.
     */
    public void setEnemyDestroyed(boolean b) {
        enemyDestroyed = b;
    }

    /**
     * This will set the speed of the x axis movement of the
     * sprite.
     * 
     * @param moveX the number of x cords it moves per action.
     */
    public void setMoveSpeedX(int moveX) {
        moveSpeedX = moveX;
    }

    /**
     * This will move the the enemy sprite to the left, using
     * the moveSpeed variable it move it by that number.
     */
    public void moveLeft() {
        if (x <= 2) {
            x = 2;
            planeLeft = false;
            planeRight = true;
        } else {
            x -= moveSpeedX;
        }
    }

    /**
     * This method will move the enemy sprite to the right.
     */
    public void moveRight() {
        if (x >= 700 - getW() - 10) {
            x = 700 - getW() - 10;
            planeRight = false;
            planeLeft = true;
        } else {
            x += moveSpeedX;
        }
    }

    /**
     * This will move the enemy sprite up, using the y speed
     * it will move the sprite across the plane.
     */
    public void moveUp() {
        if (y <= 0) {
            y = 0;
            planeDown = true;
            planeUp = false;
        } else {
            y -= moveSpeedY;
        }
    }

    /**
     * This will move the enemy sprite down, using the y speed
     * it will move the sprite across the plane.
     * 
     */
    public void moveDown() {
        if (y >= 400) {
            y = 400;
            planeDown = false;
            planeUp = true;
        } else {
            y += moveSpeedY;
        }
    }

    /**
     * This method will set the X cordinate.
     * 
     * @param x this will be the number set for the x cord.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * This method will set the Y cordinate.
     * 
     * @param y this will be th enumber set for the y cord.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * This method is used to load the image of the enemy sprite.
     * Using the file location it will grab the image and load it onto
     * the canvas.
     * 
     * @param file this is the file location of the image.
     */
    public void loadImage(String file) {
        try {
            enemy = ImageIO.read(new File("src/Plane-assets/" + file));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
