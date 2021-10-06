
package flightGame;

import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class SpriteSheet extends JPanel {

	public static void main(String[] args) {

	}
	public BufferedImage sprite;
	public BufferedImage[] sprites;
	int width;
	int height;
	int rows;
	int columns;
	int xPos;

	int yPos;

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

	public void actionPerformed(ActionEvent e) {

	}

	public int getColumns() {
		return columns;
	}

	public int getHeight() {
		return height;
	}

	public int getRows() {
		return rows;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public int getWidth() {
		return width;
	}

	public int getxPos() {
		return xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

}