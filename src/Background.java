import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

public class Background implements ImageObserver {
    private final int x;
    private BufferedImage image;
    private int y;

    public Background() {
        this(0, 0);
    }

    public Background(int x, int y) {
        this.x = x;
        this.y = y;

        // Try to open the image file background.png
        try {
            image = ImageIO.read(new File("src/Plane-assets/background.png"));
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    public void draw(Graphics g) {

        // Draw the image onto the Graphics reference
        g.drawImage(image, getX() - 25, getY() + 1475, image.getWidth(), image.getHeight(), this);


        this.y += 2;


        if (this.y >= image.getHeight() - 1475) {
            g.dispose();

            this.y = (this.y - image.getHeight() * 2);
        }

    }


    private int getX() {
        return this.x;
    }

    private int getY() {
        return this.y;
    }

    public int getImageWidth() {
        return image.getWidth();

    }

    public int getImageHeight() {
        return image.getHeight();
    }

    @Override
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        return false;
    }


}
