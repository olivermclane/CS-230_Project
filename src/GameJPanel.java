/**
 *
 */

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

public class GameJPanel extends JPanel implements Runnable {
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
		addMouseMotionListener(new MAdapter());
		setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("src/Plane-assets/blackCursor.png").getImage(), new Point(0, 0), "custom cursor"));
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
				Thread.currentThread();
				Thread.sleep(15);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private class MAdapter extends GameFrame implements MouseMotionListener, MouseInputListener {
//		@Override
//		public void mouseClicked(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseEntered(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}
//
//		@Override
//		public void mouseExited(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}

		@Override
		public void mousePressed(MouseEvent e) {
			plane.mousePressed(e);

		}
//
//		@Override
//		public void mouseReleased(MouseEvent e) {
//			// TODO Auto-generated method stub
//
//		}

		@Override
		public void mouseDragged(MouseEvent e) {
			plane.mouseDragged(e);

		}

		@Override
		public void mouseMoved(MouseEvent e) {
			plane.mouseMoved(e);



		}

		@Override
		public void mouseClicked(MouseEvent e) {
			plane.mouseClicked(e);

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

}
