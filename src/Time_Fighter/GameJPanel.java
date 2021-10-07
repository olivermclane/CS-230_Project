/**
 * 
 */
package Testing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

public class GameJPanel extends JPanel implements ActionListener {
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
	PlaneSprite plane = new PlaneSprite();

	private Timer timer;

	public GameJPanel() {

		intiGamePanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		plane.move();
		repaint();
	}

	private void intiGamePanel() {

		setBackground(Color.BLACK);
		setFocusable(true);
		addKeyListener(new TAdapter());
		timer = new Timer(10, this);
		timer.start();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		plane.doDrawing(g);

		Toolkit.getDefaultToolkit().sync();
	}

}
