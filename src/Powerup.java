import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * 
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
     * 
     * @param planeBounds
     */
    public void collisionCheck(Rectangle planeBounds) {
        if (planeBounds.intersects(bounds)) {
            isCollected = true;
        }
    }

    /**
     * 
     * @return
     */
    public boolean isLifeP() {
        return isLifePowerup;
    }

    /**
     * 
     * @return
     */
    public boolean isWeapon() {
        return isWeaponUpgrade;
    }

    /**
     * 
     * @return
     */
    public boolean isCollided() {
        return isCollected;
    }

    /**
     * 
     */
    public void getBounds() {
        bounds = new Rectangle(x, y, w, h);
    }
    /**
     * 
     * @param g
     */
    public void draw(Graphics g) {
        movePowerDown();
        g.drawImage(imageLife, x, y, this);
    }

    /**
     *  
     *  
     */
    public void movePowerDown() {
        y += 3;
    }

    /**
     * 
     */
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        return false;
    }
}
