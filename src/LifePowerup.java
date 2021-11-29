import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
/**
 * 
 */
public class LifePowerup extends Powerup {
    public boolean lifeAdded;
    /**
     * 
     * @param e
     */
    public LifePowerup(EnemySprite e) {
        super();
        loadImage();
        isLifePowerup = true;
        x = e.getxPosition();
        y = e.getyPosition();
    }
    /**
     * 
     */
    public void loadImage() {
        try {
            imageLife = ImageIO.read(new File("src/Plane-assets/LifePowerup.png"));
            w = imageLife.getWidth();
            h = imageLife.getHeight();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 
     * @param p
     */
    public void addLife(PlaneSprite p) {
        if (!lifeAdded) {
            p.addPlaneLife(1);
            lifeAdded = true;
        }
    }
}
