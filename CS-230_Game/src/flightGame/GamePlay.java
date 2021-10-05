/**
 * 
 */
package flightGame;

import java.awt.EventQueue;

import javax.swing.JFrame;

/**
 * @author dusti
 *
 */
public class GamePlay extends JFrame{

	public GamePlay() {
		initUI();

	}
	
	private void initUI() {
		add(new GameWindow());
		setTitle("Test");
		setSize(700,700);
		setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		JFrame frame = new JFrame();
//		frame.setSize(700, 700);
//		JPanel panel = new JPanel();
//		panel.setBackground(Color.BLACK);
//		PlaneSprite help = new PlaneSprite();
//		panel.add(help);
//		frame.add(panel);
//		frame.setVisible(true);
	    
	}
	public static void main(String[] args) {
		 EventQueue.invokeLater(() -> {
	            GamePlay ex = new GamePlay();
	            ex.setVisible(true);
	        });
	}

}
