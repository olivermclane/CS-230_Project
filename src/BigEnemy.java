import java.awt.*;
import java.awt.image.ImageObserver;

public class BigEnemy extends EnemySprite
        implements ImageObserver {

    public BigEnemy(String e) {
        super(e);
        isBigEnemy = true;

    }

    @Override
    public Rectangle getBigBoundsX() {
        return new Rectangle(getxPosition() + 74, getyPosition(),
                getW() - 73,
                getH() - 14);
    }

    @Override
    public Rectangle getBigBoundsY(){
        return new Rectangle(getxPosition()+3 , getyPosition()+74,
                getW()-3,
                getH()- 104);
    }

}
