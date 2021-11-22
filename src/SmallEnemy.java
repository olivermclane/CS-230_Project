import java.awt.*;
import java.awt.image.ImageObserver;
public class SmallEnemy extends EnemySprite
        implements ImageObserver {
    private final boolean isSmallEnemy;

    public SmallEnemy(String e) {
        super(e);
        isSmallEnemy = true;
        setX(300);
        setY(400);
    }

    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition() + 74, getyPosition(),
                getW(),
                getH());
    }

    @Override
    public Rectangle getBigBoundsY() {
        return new Rectangle(getxPosition() + 3, getyPosition() + 74,
                getW() - 3,
                getH() - 104);
    }
}