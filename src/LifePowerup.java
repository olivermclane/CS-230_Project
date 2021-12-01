import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * This is the LifePowerUp and is the subclass to the powerup.
 */
public class LifePowerup extends Powerup {
    public boolean lifeAdded;

    /**
     * This is the consturctor for the Life Power Up. This constructor
     * will up the super variables and set its x and y locations.
     * 
     * @param e this will pull in the enemy sprite that killed
     *          we will use this for locationk, ectera.
     */
    public LifePowerup(EnemySprite e) {
        super();
        loadImage();
        isLifePowerup = true;
        x = e.getxPosition();
        y = e.getyPosition();
    }

    /**
     * This method is used to load the image of the powerup.
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
     * This method will added life to the player when the powerup clides with
     * the player.
     * 
     * @param p This will take in the player and add life to the player object.
     */
    public void addLife(PlaneSprite p) {
        if (!lifeAdded) {
            p.addPlaneLife(1);
            lifeAdded = true;
        }
    }
}
