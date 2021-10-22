


import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SpriteSheet extends JPanel {


	private BufferedImage sprite;
	private BufferedImage[] sprites;
	private int width;
	private int height;
	private int rows;
	private int columns;
	private int xPos;
	private int yPos;

	public SpriteSheet() {

	}

	public SpriteSheet(BufferedImage fullImage, int width, int height, int rows, int columns) {
		this.width = width;
		this.height = height;
		this.rows = rows;
		this.columns = columns;
		this.sprite = fullImage;
		sprites = new BufferedImage[rows * columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				sprites[(i * columns) + j] = sprite.getSubimage(i * width, j * height, width, height);

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

	public BufferedImage getSprite() {
		return sprite;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	

	

	
}