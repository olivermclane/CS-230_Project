import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.ThreadLocalRandom;
/**
 * This is the small enemy and will keep track of small 
 * enemy attribles.
 */
public class SmallEnemy extends EnemySprite
        implements ImageObserver {
    private final boolean isSmallEnemy;
    /**
     * This is the small enemy construcotr. This woll create a
     * small enemy instance and will keep track of teh location
     * and other attributes.
     * @param e this is the image location will be load for the sprite.
     */
    public SmallEnemy(String e) {
        super(e);
        isSmallEnemy = true;
        setX(ThreadLocalRandom.current().nextInt(20, 600));
        setY(ThreadLocalRandom.current().nextInt(10, 400));
    }
    /**
     * This will return the bounds of the enemy sprite's wings.
     * @return the bounds of the enemy sprite wings
     */
    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition(), getyPosition(),
                getW(),
                getH());
    }
    /**
     * This will return the bounds of the eenemy sprite body.
     * @return this will return the enemy sprites body bounds.
     */
    @Override
    public Rectangle getBigBoundsY() {
        return new Rectangle(getxPosition(), getyPosition(),
                getW(),
                getH());
    }
}