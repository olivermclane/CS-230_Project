import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class ExplosionSprite extends SpriteSheet{

    private BufferedImage[] explosionSprites;
    private int x;
    private int y;
    private int expCount = 0;
    private int explosionTic = 0;
    ExplosionSprite() {
        loadImage();
    }

    public int getExpCount() {
        return expCount;
    }

    public void setExpCount(int newexpCount) {
        this.expCount = newexpCount;
    }

    private void loadImage() {
        try {
            BufferedImage originalImg = ImageIO.read(new File("src/Plane-assets/explosionSprite.png"));
            SpriteSheet explosionImages = new SpriteSheet(originalImg, 82, 85, 4, 2);
            explosionSprites = explosionImages.getSprites();
            BufferedImage oneExplosion = explosionSprites[2];

            setW(oneExplosion.getWidth());
            setH(oneExplosion.getHeight());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public BufferedImage[] getExplosions() {
        return explosionSprites;
    }

    public void doDrawing(Graphics g) {

        BufferedImage explosionEffect = explosionSprites[getExpCount()];
        g.drawImage(explosionEffect, getX(), getY(), this);


    }
    public int getExplosionTic() {
        return explosionTic;
    }
    public void plusExplosionTic(){
        explosionTic++;
    }

    public void setW(int w) {
    }


    public void setH(int h) {
    }

    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        return false;
    }


    public int getX() {
        return x;
    }


    public void setX(int x) {
        this.x = x;
    }


    public int getY() {
        return y;
    }


    public void setY(int y) {
        this.y = y;
    }


}


