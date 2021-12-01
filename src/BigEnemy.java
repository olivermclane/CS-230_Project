import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;
/**
 * This is the BigEnemy class and is a subclass to 
 * EnemySpirte. BigEnemy is a type of enemy and 
 * uses a seperate image than other enemys.
 */
public class BigEnemy extends EnemySprite {
    /**
     * This is the constructor for the BigEnemy
     * and require a image file location for it too draw.
     * 
     * @param e the location of the image file
     */
    public BigEnemy(String e) {
        super(e);
        isBigEnemy = true;
        setX(ThreadLocalRandom.current().nextInt(20, 600));
        setY(ThreadLocalRandom.current().nextInt(10, 400));
    }

    
    /**
     * This gets the horizontal bounds for the wings of the plane
     * @return returns the horizontal bounds of the BigEnemy
     */
    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition() + 74, getyPosition(),
                getW() - 73,
                getH() - 14);
    }
    /**
     * This gets the vertical bounds for the body of the plane.
     * @return returns the vertical bounds
     */
    @Override
    public Rectangle getBigBoundsY() {
        return new Rectangle(getxPosition() + 3, getyPosition() + 74,
                getW() - 3,
                getH() - 104);
    }
}

