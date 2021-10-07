/**
 * 
 */
package Time_Fighter;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author dusti
 *
 */
public class GamePlay extends JPanel {

	public static void main(String[] args) {

		GamePlay ex = new GamePlay();

	}

	public GamePlay() {

		initGamePlay();

	}

	public void initGamePlay() {
		JFrame frame = new GameFrame();
		JPanel panel = new GameJPanel();
		frame.add(panel);
		frame.setVisible(true);

	}

}
