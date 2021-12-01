import javax.swing.*;
import java.awt.image.BufferedImage;

/**
 * This class will take a image or sprite sheet and break it up
 * into an array.
 */
public class SpriteSheet extends JPanel {
    private BufferedImage[] sprites;
    private int width;
    private int height;

    /**
     * This is out default constructor for sprite sheet.
     * 
     * @deprecated cant be used.
     */
    public SpriteSheet() {
    }

    /**
     * This method will take a spritesheet image and break it up
     * into a array of images.
     * 
     * @param fullImage The sprite sheet that needs to broken up
     * @param width     the width of the image
     * @param height    the height of the image
     * @param rows      how many rows does the sprite sheet have
     * @param columns   how many columns will the sprite sheet have
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
     * This will return the height for the spritessheet.
     * 
     * @return the height of the sheet.
     */
    @Override
    public int getHeight() {
        return height;
    }

    /**
     * This will return the width for the spritesheet
     * 
     * @return the width of the sheet
     */
    @Override
    public int getWidth() {
        return width;
    }

    /**
     * This will return the sprite sheet broken up into
     * several different images and return them in a array.
     * 
     * @return this method will return a array of images from the sprite sheet.
     */
    public BufferedImage[] getSprites() {
        return sprites;
    }
}