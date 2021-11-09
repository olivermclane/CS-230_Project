import javax.imageio.ImageIO;
import javax.swing.event.MouseInputListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PlaneSprite extends SpriteSheet
        implements ActionListener, ImageObserver, Runnable, KeyListener, MouseInputListener {
    private static BufferedImage[] sprites1;
    public int planeLife;
    public boolean didPlaneFire = false;
    public List<Missile> missiles;
    private boolean planeHit;
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
    private int maxAmmo = 5;
    private List<Missile> Ammo;
    private List<Missile> Missiles;
    private boolean usingKeyboard;


    public PlaneSprite() {
        loadImage();
        Ammo = new ArrayList<>();
        ammoLoad();
        addKeyListener(this);
        missiles = new ArrayList<>();
        planeLife = 3;

    }
    public void ammoLoad(){

        for(int i = 0; i< maxAmmo; i++){
            Ammo.add(new Missile());
        }


    }
    public List<Missile> ammo(){
        return Ammo;
    }
    @Override
    public void actionPerformed(ActionEvent arg0) {
        // TODO Auto-generated method stub

    }

    public List<Missile> missiles(){
        return missiles;
    }

    public boolean didPlaneFire() {
        return didPlaneFire;
    }

    public void doDrawing(Graphics g) {
        if (!planeRight && !planeLeft) {
            g.drawImage(sprites1[2], getxPosition(), getyPosition(), this);
        }
        else if (planeRight && planeLeft) {
            g.drawImage(sprites1[2], getxPosition(), getyPosition(), this);
        }
        else if (planeRight) {
            moveRight();
            g.drawImage(sprites1[4], getxPosition(), getyPosition(), this);
        }
        else if (planeLeft) {
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

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getxPosition()+3, getyPosition() +15,
                getW() - 3,
                getH() - 53);
    }

    public int getH() {
        return getPlane().getHeight();
    }

    public BufferedImage getPlane() {

        return img1;
    }


    @Override
    public BufferedImage[] getSprites() {
        return sprites1;
    }

    public int getW() {
        return getPlane().getWidth();
    }

    public int getxPosition() {
        return x;
    }

    public void setxPosition(int xPosition) {

        x = xPosition;
    }

    public int getyPosition() {
        return y;
    }

    public void setyPosition(int yPosition) {
        y = yPosition;
    }

    public boolean isDestroyed() {
        return planeDestroyed;
    }


    public void setPlaneDestroyed(boolean planeDestroyed) {
        this.planeDestroyed = planeDestroyed;
    }

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


            missileFired = true;
            didPlaneFire = true;


        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
    public void setPlaneDown(boolean i){
        planeDown = i;
    }
    public void setPlaneUp(boolean i){
        planeUp = i;
    }
    public void setPlaneLeft(boolean i){
        planeLeft = i;
    }
    public void setPlaneRight(boolean i){
        planeRight = i;
    }

    private void loadImage() {
        try {
            BufferedImage originalImg = ImageIO.read(new File("src/Plane-assets/PlaneResize.png"));
            SpriteSheet planeImage = new SpriteSheet(originalImg, 98, 68, 5, 1);
            sprites1 = planeImage.getSprites();
            img1 = sprites1[2];

            int w = img1.getWidth();
            int h = img1.getHeight();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void missileFired(boolean fire) {
       missileFired = fire;
    }
    public boolean didmissileFired() {

            return missileFired;
        }


    @Override
    public void mouseClicked(MouseEvent e) {
        setxPosition(e.getX());
        setyPosition(e.getY());


    }

    @Override
    public void mouseDragged(MouseEvent e) {


    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    //manages the mouse movement of plane sprite
    @Override
    public void mouseMoved(MouseEvent e) {
        if(!usingKeyboard) {
            if(e.getX() < x+24 || e.getX() > x+72){
                if(x < e.getX()-55)
                    planeRight = true;
                if(x > e.getX()-55)
                    planeLeft = true;
            }
                if(y > e.getY()-34)
                    planeUp = true;
                if(y < e.getY()-34)
                    planeDown = true;
        
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        didPlaneFire = false;


    }

    public boolean getKeyboardStatus() {
        return usingKeyboard;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        missileFired = true;
        didPlaneFire = true;
    }

    public void moveDown() {
        if (y >= 680) {
            y = 680;
        } else {
            y += 3;
        }

    }

    public void moveLeft() {
        if (x <= -10) {
            x = -10;
        } else {
            x -= 3;
        }

    }

    public void moveRight() {
        if (x >= 580) {
            x = 580;
        } else {
            x += 3;
        }

    }

    public void moveUp() {
        if (y <= 0) {
            y = 0;
        } else {
            y -= 3;
        }

    }

    public Missile projectile() {
        Missile mis = new Missile();

        return mis;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    public boolean isPlaneHit() {
        return isPlaneHit;
    }

    public void isHit() {
        if (planeLife == 0) {
            this.setVisible(false);
        } else {
            planeLife--;
        }
    }

    public int getLife() {
        return planeLife;
    }

    public boolean isDead() {
        if (planeLife == 0) {
            isPlaneHit = true;
            return true;
        }
        return false;
    }

    public void addPlaneLife(int x) {
        planeLife += x;
    }

    public Rectangle getBounds2() {
        return null;
    }
    
    
}
	

