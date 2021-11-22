import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.concurrent.ThreadLocalRandom;
public class BigEnemy extends EnemySprite
        implements ImageObserver {
    private final boolean isBigEnemy;

    public BigEnemy(String e) {
        super(e);
        isBigEnemy = true;
        setX(ThreadLocalRandom.current().nextInt(20, 600));
        setY(ThreadLocalRandom.current().nextInt(10, 400));
    }

    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition() + 74, getyPosition(),
                getW() - 73,
                getH() - 14);
    }

    @Override
    public Rectangle getBigBoundsY() {
        return new Rectangle(getxPosition() + 3, getyPosition() + 74,
                getW() - 3,
                getH() - 104);
    }
}

