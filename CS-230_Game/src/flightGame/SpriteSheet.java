
package flightGame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpriteSheet {

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
	public int getxPos() {
		return xPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public int getyPos() {
		return yPos;
	}

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public BufferedImage getSprite() {
		return sprite;
	}

	public void setSprite(BufferedImage sprite) {
		this.sprite = sprite;
	}

	public BufferedImage[] getSprites() {
		return sprites;
	}

	public void setSprites(BufferedImage[] sprites) {
		this.sprites = sprites;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public static void main(String[] args) {

		
	}


	
}