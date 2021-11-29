import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * 
 */
public class SpriteSheet extends JPanel {
    private BufferedImage[] sprites;
    private int width;
    private int height;

    /**
     * 
     */
    public SpriteSheet() {
    }
    
    /**
     * 
     * @param fullImage
     * @param width
     * @param height
     * @param rows
     * @param columns
     */
    public SpriteSheet(BufferedImage fullImage, int width, int height, int rows, int columns) {
        this.width = width;
        this.height = height;
        sprites = new BufferedImage[rows * columns];
        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                sprites[(i * columns) + j] = fullImage.getSubimage(j * width, i * height, width, height);
            }
        }
    }

    /**
     * 
     * @return
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * 
     * @return
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * 
     * @return
     */
    public BufferedImage[] getSprites() {
        return sprites;
    }
}