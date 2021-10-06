/**
 * 
 */
package flightGame;

import javax.swing.JFrame;

/**
 * @author dusti
 *
 */
public class GameFrame extends JFrame {

	public GameFrame() {
		initFrame();

	}

	private void initFrame() {
		setTitle("Test");
		setSize(700, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}
}
