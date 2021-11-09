import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
public class Powerup implements ImageObserver {
    //instance variables for x,y,width, and height
    protected int x;
    protected int y;
    protected int w;
    protected int h;
    protected BufferedImage imageLife;
    protected boolean isWeaponUpgrade;
    protected boolean isLifePowerup;
    //protected boolean isDamageMuliplier;
    //collision variables
    protected boolean isCollected = false;
    protected Rectangle bounds;
    //Check collision between plane and powerup
    public void collisionCheck(Rectangle planeBounds) {
        if (planeBounds.intersects(bounds)) {
            isCollected = true;
        }
    }
    //returns type of powerup
    public boolean isLifeP() {
        return isLifePowerup;
    }
    public boolean isWeapon() {
        return isWeaponUpgrade;
    }
    //collsion variable return
    public boolean isCollided() {
        return isCollected;
    }
    //returns bounds of powerup
    public void getBounds() {
        bounds = new Rectangle(x, y, w, h);
    }
    //drawing powerup
    public void draw(Graphics g) {
        movePowerDown();
        g.drawImage(imageLife, x, y, this);
    }
    //moves powerup down
    public void movePowerDown() {
        y += 3;
    }
    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        // TODO Auto-generated method stub
        return false;
    }
}
