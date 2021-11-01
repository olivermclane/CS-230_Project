import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;


public class Missile implements ImageObserver {
    private int width;
    private int height;
    private BufferedImage missile;
    private int x;
    private int y;

    private BufferedImage enemyMissile;
    private int y2;
    private int x2;

    public Missile() {

        loadImage1();
        loadImage2();
    }

    private void loadImage1() {
        try {

            missile = ImageIO.read(new File("src/Plane-assets/Missile2.png"));


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void loadImage2() {
        try {

            enemyMissile = ImageIO.read(new File("src/Plane-assets/EnemyMissile2.png"));


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public BufferedImage getEnemyMissile() {
        return enemyMissile;
    }

    public boolean isOffScreen() {
        return y < 0;
    }

    public boolean isOffScreen2() {
        return y > 800;
    }

    public void doDrawing1(Graphics r) {

        r.drawImage(getMissile(), getX(), getY(), getWidth(), getHeight(), this);

        y -= 2;
    }

    public void doDrawing2(Graphics s) {
        s.drawImage(getEnemyMissile(), getX2(), getY2(), getWidth2(), getHeight2(), this);

        y2 += 4;
    }

    public int getY() {
        return y;
    }

    public void setY(int newy) {
        y = newy;
    }

    public int getX() {
        return x;
    }

    public void setX(int newx) {
        x = newx;
    }

    public int getWidth() {
        return missile.getWidth();
    }

    public int getHeight() {
        return missile.getHeight();
    }

    public Rectangle getBounds() {

        return new Rectangle(getX(), getY(), getWidth(), getHeight());

    }


    public Rectangle getBounds2() {

        return new Rectangle(getX2(), getY2(), getWidth(), getHeight());

    }

    public BufferedImage getMissile() {
        return missile;
    }


    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        return false;
    }


    public int getY2() {
        return y2;
    }


    public void setY2(int newy2) {
        y2 = newy2;
    }


    public int getX2() {
        return x2;
    }


    public void setX2(int newx2) {
        x2 = newx2;
    }


    public int getWidth2() {
        return getEnemyMissile().getWidth();
    }


    public int getHeight2() {
        return getEnemyMissile().getHeight();
    }

}
