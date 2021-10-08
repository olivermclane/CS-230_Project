/**
 * 
 */
package Time_Fighter;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;
import javafx.*;
public class GameJPanel extends JPanel implements Runnable{
	private class TAdapter extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			plane.keyPressed(e);
		}

		@Override
		public void keyReleased(KeyEvent e) {
			plane.keyReleased(e);
		}
	}

	ScrollingBackground back1 = new ScrollingBackground();
	PlaneSprite plane = new PlaneSprite();
	public Runnable runnable;
	public Thread t;
	public GameJPanel() {

		intiGamePanel();
	}

	private void intiGamePanel() {

		setFocusable(true);
		addKeyListener(new TAdapter());
		new Thread(this).start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		back1.loadBackground(g);
		plane.doDrawing(g);
		g.dispose();
		Toolkit.getDefaultToolkit().sync();
	}
	@Override
	public void run() {

		while (true) {
			try {
				repaint();
				Thread.currentThread().sleep(15);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}
