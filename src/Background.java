import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;

/**
 * This class takes care of loading the background image.
 * When you create a instance background will pull and image file.
 */
public class Background implements ImageObserver {
    private final int x;
    private BufferedImage image;
    private int y;

    public Background() {
        this(0, 0);
    }

    /**
     * This is a background object
     * and places an image covering the screen.
     * 
     * @param x this is the horizontal placement of the background
     * @param y this is the vertial placement of the backgound
     */
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

    /**
     * This method takes care of drawing he image on the canvas.
     * When called it will paint the background image onto the canvas.
     * 
     * @param g this is what the image will be painted on
     */
    public void draw(Graphics g) {
        // Draw the image onto the Graphics reference
        g.drawImage(image, getX() - 25, getY() + 1475, image.getWidth(), image.getHeight(), this);
        this.y += 2;
        if (this.y >= image.getHeight() - 1475) {
            g.dispose();
            this.y = (this.y - image.getHeight() * 2);
        }
    }

    /**
     * Method will reaturn the locaiton of the x location
     * 
     * @return the x coordinate
     */
    private int getX() {
        return this.x;
    }

    /**
     * 
     * @return the y coordinate location
     */
    private int getY() {
        return this.y;
    }

    /**
     * Return the width of the image
     * 
     * @return this will return a int for the width of the iamge
     */
    public int getImageWidth() {
        return image.getWidth();
    }

    /**
     * Returns the height of the iamge
     * 
     * @return this will return a int for the height of the image
     */
    public int getImageHeight() {
        return image.getHeight();
    }

    @Override
    /**
     * Unused Method -- required for imageObserver
     */
    public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
        // TODO Auto-generated method stub
        return false;
    }
}
