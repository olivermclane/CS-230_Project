import javax.swing.*;
import java.awt.image.BufferedImage;

public class SpriteSheet extends JPanel {


    private BufferedImage[] sprites;
    private int width;
    private int height;


    public SpriteSheet() {

    }

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


    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }


    public BufferedImage[] getSprites() {
        return sprites;
    }


}