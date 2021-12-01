import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * This class is the parent class for life powerups and
 * weapon powerups. This is used to stored share methods
 * across all powerups.
 */
public class Powerup implements ImageObserver {
    // instance variables for x,y,width, and height
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected BufferedImage imageLife;
    protected boolean isWeaponUpgrade;
    protected boolean isLifePowerup;
    protected boolean isCollected = false;
    protected Rectangle bounds;

    /**
     * This is the method that will keep track of power up collision wiht the
     * player.
     * 
     * @param planeBounds this is the bounds of the plane to check collision with.
     */
    public void collisionCheck(Rectangle planeBounds) {
        if (planeBounds.intersects(bounds)) {
            isCollected = true;
        }
    }

    /**
     * This is a boolean to tell if its a life or weapon power up.
     * 
     * @return return the life powerup boolean
     */
    public boolean isLifeP() {
        return isLifePowerup;
    }

    /**
     * This is a future feature -- Devoloping
     * 
     * @return retunr the weapon powerup boolean
     */
    public boolean isWeapon() {
        return isWeaponUpgrade;
    }

    /**
     * This method will return the sprite collided boolean.
     * 
     * @return if the powerup has been collided with.
     */
    public boolean isCollided() {
        return isCollected;
    }

    /**
     * This method will set the bounds of the powerups sprite.
     */
    public void getBounds() {
        bounds = new Rectangle(x, y, w, h);
    }

    /**
     * This method draws the on the canvas and moves it down.
     * 
     * @param g
     */
    public void draw(Graphics g) {
        movePowerDown();
        g.drawImage(imageLife, x, y, this);
    }

    /**
     * This method will move the powerup down 3 points on the plane.
     * 
     */
    public void movePowerDown() {
        y += 3;
    }

    /**
     * This method is unused but required by ImageObsever super.
     */
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        return false;
    }
}
