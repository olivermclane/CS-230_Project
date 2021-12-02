import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.*;

/**
 * This is the PlaneSprite class and will manage the PlaneSprite properties.
 * This class can move the plane, shoot missles, draw the plane, etc.
 */
public class PlaneSprite extends SpriteSheet
        implements ActionListener, Runnable, KeyListener, MouseInputListener {
    private static BufferedImage[] sprites1;
    private final int maxAmmo = 5;
    private final List<Missile> Ammo;
    public int planeLife;
    public boolean didPlaneFire = false;
    public List<Missile> missiles;
    private boolean planeDestroyed;
    private BufferedImage img1;
    private int x = 350;
    private int y = 600;
    private boolean planeRight;
    private boolean planeLeft;
    private boolean planeUp;
    private boolean planeDown;
    private boolean missileFired;
    private boolean isPlaneHit = false;
    private boolean usingKeyboard;

    /**
     * This is the constructor for
     * creating a plane instance for the player.
     */
    public PlaneSprite() {
        loadImage();
        Ammo = new ArrayList<>();
        ammoLoad();
        addKeyListener(this);
        missiles = new ArrayList<>();
        planeLife = 4;
    }

    /**
     * This will load 5 shots into the PlaneSprite missle launcher.
     */
    public void ammoLoad() {
        for (int i = 0; i < maxAmmo; i++) {
            Ammo.add(new Missile());
        }
    }

    /**
     * This method will return a List of the PlaneSprites current ammo.
     * 
     * @return
     */
    public List<Missile> ammo() {
        return Ammo;
    }

    /**
     * Invoke when action is used.
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * @return
     */
    public List<Missile> missiles() {
        return missiles;
    }

    /**
     * This will return the boolean for whether or not the plane fired.
     * 
     * @return a boolean for if the planefired
     */
    public boolean didPlaneFire() {
        return didPlaneFire;
    }

    /**
     * This method keeps is in charge of drawing the PlaneSprite on the canvas.
     * This method will draw the proper image for the plane movement based on the
     * proper
     * combination.
     * 
     * @param g is the canvas for images and sprite to be drawn on.
     */
    public void doDrawing(Graphics g) {
        if (!planeRight && !planeLeft) {
            g.drawImage(sprites1[2], getxPosition(), getyPosition(), this);
        } else if (planeRight && planeLeft) {
            g.drawImage(sprites1[2], getxPosition(), getyPosition(), this);
        } else if (planeRight) {
            moveRight();
            g.drawImage(sprites1[4], getxPosition(), getyPosition(), this);
        } else if (planeLeft) {
            moveLeft();
            g.drawImage(sprites1[0], getxPosition(), getyPosition(), this);
        }
        if (planeUp) {
            moveUp();
        }
        if (planeDown) {
            moveDown();
        }
    }

    /**
     * This will return the bounds of the PlaneSprite used for collision.
     * 
     * @return the bounds of the PlaneSprite
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getxPosition() + 3, getyPosition() + 15, getW() - 3, getH() - 53);
    }

    /**
     * This method will return the width of the PlaneSprite.
     * 
     * @return the width of the PlaneSprite.
     */
    public int getW() {
        return getPlane().getWidth();
    }

    /**
     * This will return the height of the PlaneSprite based
     * on the height of the image.
     * 
     * @return the height of the PlaneSprite
     */
    public int getH() {
        return getPlane().getHeight();
    }

    /**
     * This will return the current image of the plane.
     * 
     * @return the image of the sprite.
     */
    public BufferedImage getPlane() {
        return img1;
    }

    /**
     * This method will return all the sprites associated with the
     * PlaneSprite's movement.
     * 
     * @return a array of all the sprites
     */
    @Override
    public BufferedImage[] getSprites() {
        return sprites1;
    }

    /**
     * This method will return the current x coordinate of the PlaneSprite.
     * 
     * @return the x cordinate for the PlaneSprite
     */
    public int getxPosition() {
        return x;
    }

    /**
     * This method is used to set the x coordinate of the PlaneSprite.
     * 
     * @param xPosition the x coordinate you want to for the PlaneSprite.
     */
    public void setxPosition(int xPosition) {
        x = xPosition;
    }

    /**
     * This method will return the y coordinate of the PlaneSprite.
     * 
     * @return the x coordinate of PlaneSprite
     */
    public int getyPosition() {
        return y;
    }

    /**
     * This method is used to set the y coordinate of the PlaneSprite.
     * 
     * @param yPosition the y coordinate you want to set for the PlaneSprite.
     */
    public void setyPosition(int yPosition) {
        y = yPosition;
    }

    /**
     * This method will return a boolean for whether or not the PlaneSprite
     * has been destoryed.
     * 
     * @return a boolean for ig the PlaneSprite has been destoryed.
     */
    public boolean isDestroyed() {
        return planeDestroyed;
    }

    /**
     * This method will set if the PlaneSprite has been destoryed
     * using a boolean.
     * 
     * @param planeDestroyed a boolean for whether or not the plane is destoryed
     */
    public void setPlaneDestroyed(boolean planeDestroyed) {
        this.planeDestroyed = planeDestroyed;
    }

    /**
     * This method will check for key being pressed on the keyboard,
     * and using the keyEvent code it will set the PlaneSprite movement
     * variables.
     * 
     * @param e the key that was pressed.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // if keys are pressed the move that direction
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            planeRight = true;
            usingKeyboard = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            planeLeft = true;
            usingKeyboard = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            planeUp = true;
            usingKeyboard = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            planeDown = true;
            usingKeyboard = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            didPlaneFire = false;
        }
    }

    /**
     * This is used to manage key released events. This will reset the
     * PlaneSpirte variables aftered the key pressed event.
     * 
     * @param e the key released.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            planeRight = false;
            usingKeyboard = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            planeLeft = false;
            usingKeyboard = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            planeUp = false;
            usingKeyboard = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            planeDown = false;
            usingKeyboard = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            if (Ammo.isEmpty()) {
            } else {
                missileFired = true;
                didPlaneFire = true;
            }
        }
    }

    /**
     * Abstact method -- Inherited from Abstract Class
     * 
     * @deprecated not in use
     */
    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * 
     * @param i
     */
    public void setPlaneDown(boolean i) {
        planeDown = i;
    }

    /**
     * 
     * @param i
     */
    public void setPlaneUp(boolean i) {
        planeUp = i;
    }

    /**
     * 
     * @param i
     */
    public void setPlaneLeft(boolean i) {
        planeLeft = i;
    }

    /**
     * 
     * @param i
     */
    public void setPlaneRight(boolean i) {
        planeRight = i;
    }

    /**
     * 
     */
    private void loadImage() {
        try {
            BufferedImage originalImg = ImageIO.read(new File("src/Plane-assets/PlaneResize.png"));
            SpriteSheet planeImage = new SpriteSheet(originalImg, 98, 68, 5, 1);
            sprites1 = planeImage.getSprites();
            img1 = sprites1[2];
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 
     * @param fire
     */
    public void missileFired(boolean fire) {
        missileFired = fire;
    }

    /**
     * 
     * @return
     */
    public boolean didmissileFired() {
        return missileFired;
    }

    /**
     * 
     * 
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        setxPosition(e.getX());
        setyPosition(e.getY());
    }

    /**
     * Abstact method -- Inherited from Abstract Class
     * 
     * @deprecated not in use
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    }

    /**
     * Abstact method -- Inherited from Abstract Class
     * 
     * @deprecated not in use
     */
    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    /**
     * Abstact method -- Inherited from Abstract Class
     * 
     * @deprecated not in use
     */
    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    
    // manages the mouse movement of plane sprite
    /**
     * 
     * This method will manage the mouse movement and allows
     * for a little bit of wiggling in the mouse before moving the 
     * PlaneSprite.
     * 
     * @param e the MouseEvent for being moved.
     */
    @Override
    public void mouseMoved(MouseEvent e) {
        if (!usingKeyboard) {
            if (e.getX() < x + 24 || e.getX() > x + 72) {
                if (x < e.getX() - 55)
                    planeRight = true;
                if (x > e.getX() - 55)
                    planeLeft = true;
            }
            if (y > e.getY() - 34)
                planeUp = true;
            if (y < e.getY() - 34)
                planeDown = true;
        }
    }

    /**
     * This method will set the fire to false, this is crucial 
     * in the in preventing the PlaneSprite from firing several 
     * bullet per frame.
     * 
     * @param e the MouseEvent for being pressed.
     */
    @Override
    public void mousePressed(MouseEvent e) {
        didPlaneFire = false;
    }

    /**
     * This method lets the game know that the user is using the 
     * keyboard and flips the PlaneSprite variable.
     * 
     * @return a boolean for if the keyboard is in use.
     */
    public boolean getKeyboardStatus() {
        return usingKeyboard;
    }

    /**
     * This method will allow the plane to fire a missle
     * on release of the mosue button.
     * 
     * @param e the MouseEvent for mousebutton released.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        missileFired = true;
        didPlaneFire = true;
    }

    /**
     * This method will move the PlaneSprite image on the canvas
     * down on the y plane from it current location by 3.
     */
    public void moveDown() {
        if (y >= 680) {
            y = 680;
        } else {
            y += 3;
        }
    }

    /**
     * This method will move the PlaneSprite image on the canvas
     * over to the left by 3 on the x plane.
     */
    public void moveLeft() {
        if (x <= -10) {
            x = -10;
        } else {
            x -= 3;
        }
    }
    /**
     * This method will move the PlaneSprite image on the canvas
     * over to the right by 3 on the x plane.
     */
    public void moveRight() {
        if (x >= 580) {
            x = 580;
        } else {
            x += 3;
        }
    }

    /**
     * This method will move the PlaneSprite iamge on the canvas
     * up by 3 on the y plane.
     */
    public void moveUp() {
        if (y <= 500) {
            y = 500;
        } else {
            y -= 3;
        }
    }

    /**
     * This method is used to remove a missle from the list 
     * when the missle is fired.
     * 
     * @return the missle from the ammo list that was just "fired".
     */
    public Missile projectile() {
        Missile mis = Ammo.remove(0);
        return mis;
    }

    /**
     * Abstact method -- Inherited from Abstract Class
     * 
     * @deprecated not in use
     */
    @Override
    public void run() {
        // TODO Auto-generated method stub
    }

    /**
     * This method will return whether for not the plane has been hit.
     * 
     * @return a boolean of the status of if the plane as been hit.
     */
    public boolean isPlaneHit() {
        return isPlaneHit;
    }

    /**
     * This method will lower the PlaneSprite's health if it's been hit.
     */
    public void isHit() {
        if (planeLife == 0) {
            this.setVisible(false);
        } else {
            planeLife--;
        }
    }

    /**
     * This method will return the current life of the PlaneSprite
     * 
     * @return the current life of the PlaneSprite
     */
    public int getLife() {
        return planeLife;
    }

    /**
     * This will return true if the PlaneSprite life is 0 other
     *  wise it will return false.
     * 
     * @return  whether or not the plane is dead
     */
    public boolean isDead() {
        if (planeLife == 0) {
            isPlaneHit = true;
            return true;
        }
        return false;
    }

    /**
     * This method will add life to the PlaneSprite.
     * 
     * @param x this is the number of live you want to add to the PlaneSprite.
     */
    public void addPlaneLife(int x) {
        planeLife += x;
    }

    /**
     * 
     * @return
     */
    public Rectangle getBounds2() {
        return null;
    }
}
