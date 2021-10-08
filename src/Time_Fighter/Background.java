/**
 * 
 */
package Time_Fighter;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import javax.imageio.ImageIO;
public class Background implements ImageObserver {
    private BufferedImage image;
 
    private int x;
    private int y;
	 
	    public Background() {
	        this(0,0);
	    }
	 
	    public Background(int x, int y) {
	        this.x = x;
	        this.y = y;
	 
	        // Try to open the image file background.png
	        try {
	            image = ImageIO.read(new File("src/Plane-assets/back1.png"));
	        }
	        catch (Exception e) { System.out.println(e); }
	 
	    }
	 
	    /**
	     * Method that draws the image onto the Graphics object passed
	     * @param window
	     */
	    public void draw(Graphics g) {
	 
	        // Draw the image onto the Graphics reference
	        g.drawImage(image, getX(), getY(), image.getWidth(), image.getHeight(), this);
	 
	        // Move the x position left for next time
	        this.y += 2;
	 
	        // Check to see if the image has gone off stage left
	        if (this.y >= image.getHeight()-700) {
	 
	            // If it has, line it back up so that its left edge is
	            // lined up to the right side of the other background image
	            this.y = (this.y - image.getHeight() *2);
	        }
	 
	    }
	 
	    public void setX(int x) {
	        this.x = x;
	    }
	    public int getX() {
	        return this.x;
	    }
	    public int getY() {
	        return this.y;
	    }
	    public int getImageWidth() {
	        return image.getWidth();
	        
	    }
	    public int getImageHight() {
	    	return image.getHeight();
	    }
	    
	 
	  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3, int arg4, int arg5) {
		// TODO Auto-generated method stub
		return false;
	}

}
