/**
 * 
 */
package flightGame;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class GameWindow extends JPanel implements ActionListener{
	private Timer timer;
	private PlaneSprite plane;
	public GameWindow() {
		intiGameWindow();
	}
	
	private void intiGameWindow() {
		addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);

        plane = new PlaneSprite();

        timer = new Timer(10, this);
        timer.start();
		
	}
	
	
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }
	
	private void doDrawing(Graphics g) {

        
        
        g.drawImage(plane.getPlane(),plane.getxPosition(),
                plane.getyPosition(), this);
        
       


        
    }
	
	@Override
    public void actionPerformed(ActionEvent e) {
        
        step();
    }
    
    private void step() {
        
        plane.move();
        
        repaint(plane.getxPosition()-1, plane.getyPosition()-1, 
                plane.getW()+2, plane.getH()+2);     
    }  
    
    private class TAdapter extends KeyAdapter {

		@Override
        public void keyReleased(KeyEvent e) {
            plane.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            plane.keyPressed(e);
        }
    }
	
    
//	public static void main(String[] args) {
//
//		 JFrame frame = new JFrame();
//			frame.setSize(700, 700);
//			JPanel panel = new JPanel();
//			panel.setBackground(Color.BLACK);
//			PlaneSprite help = new PlaneSprite();
//			panel.add(help);
//			frame.add(panel);
//			frame.setVisible(true);
//	    }

	
}	   

	
	
			
			





