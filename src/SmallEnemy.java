import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.ThreadLocalRandom;
public class SmallEnemy extends EnemySprite
        implements ImageObserver {
    private final boolean isSmallEnemy;

    public SmallEnemy(String e) {
        super(e);
        isSmallEnemy = true;
        setX(ThreadLocalRandom.current().nextInt(20, 600));
        setY(ThreadLocalRandom.current().nextInt(10, 400));
    }

    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition(), getyPosition(),
                getW(),
                getH());
    }

    @Override
    public Rectangle getBigBoundsY() {
        return new Rectangle(getxPosition(), getyPosition(),
                getW(),
                getH());
    }
}