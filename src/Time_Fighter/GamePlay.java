/**
 * 
 */
package Time_Fighter;

import java.awt.AWTException;
import java.awt.Robot;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * @author dusti
 *
 */
public class GamePlay extends GameJPanel {

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	GamePlay ex = new GamePlay();  
            }
        });
    }
		

	

	public GamePlay() {

		initGamePlay();

	}

	public void initGamePlay() {
		JFrame frame = new GameFrame();
		JPanel panel = new GameJPanel();
		try {
			Robot robot = new Robot();
			robot.mouseMove(frame.getX()+210+48,frame.getY()+500+34);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame.add(panel);
		frame.setVisible(true);

	}

}
