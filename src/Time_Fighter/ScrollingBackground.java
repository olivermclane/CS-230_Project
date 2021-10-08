/**
 * 
 */
package Time_Fighter;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class ScrollingBackground extends SpriteSheet {

	// Two copies of the background image to scroll
	private Background backOne;
	private Background backTwo;

	public ScrollingBackground() {
		backOne = new Background(0, -700);
		backTwo = new Background(0, -backOne.getImageHight() - 700);
	}

	public void loadBackground(Graphics g) {

		backOne.draw(g);
		backTwo.draw(g);

	}

	
}
