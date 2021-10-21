import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Robot;
import java.awt.AWTException;



/**
 * 
 */

public class Menu extends GameJPanel implements ActionListener {

    //name of game
    private JLabel timeFigtherTitle; 

    //the background color
    private static final Color backGC = new Color(46, 94, 135);
    
    public Menu() {
        drawMenu();
    }

    public void drawMenu() {
        JFrame menu = new JFrame("Time Fighters -- Main Menu");
        //basic configs for JFrame Menu
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);
        menu.setSize(700,800);
        menu.setBackground(backGC);

        //create Title Panel & Layout by default: Flowlayout 
        JPanel CentralPanel = new JPanel();     
        CentralPanel.setBorder(new EmptyBorder(10,10,10,10)); 
        CentralPanel.setLayout(new GridBagLayout());  
        CentralPanel.setBackground(backGC);
    
        GridBagConstraints menuLayout = new GridBagConstraints();
        menuLayout.gridwidth = GridBagConstraints.REMAINDER;
        menuLayout.anchor = GridBagConstraints.NORTH;

        
        //temporary placement until I get an Icon
        // JLabel TimeIcon = null; // change this in next version
        // TitlePanel.add(TimeIcon);

        menuLayout.anchor = GridBagConstraints.CENTER;
        menuLayout.fill = GridBagConstraints.HORIZONTAL;


        //creating JPanel for the buttons
        JPanel buttonsPanel = new JPanel(new GridBagLayout());
        buttonsPanel.setLayout(new GridBagLayout());
        buttonsPanel.setBackground(backGC);
        buttonsPanel.setSize(700, 200);

        //adding game title label
        timeFigtherTitle = new JLabel("Time Fighters");
        timeFigtherTitle.setFont(new Font("Verdana", Font.PLAIN, 18));
        timeFigtherTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeFigtherTitle.setForeground(Color.DARK_GRAY);
        timeFigtherTitle.setVisible(true);
        CentralPanel.add(timeFigtherTitle);
        
        //creating my buttons
        JButton quitGButton = new JButton("Quit Game");
        JButton playGButton = new JButton("Play Game");

        // Creating play game button 
        playGButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        playGButton.setForeground(Color.WHITE);
        playGButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGame(menu);
                playGButton.setVisible(false);
                quitGButton.setVisible(false);
            }
        }); 
        playGButton.setBorderPainted(false);
        buttonsPanel.add(playGButton, menuLayout);


        // Creating quit game button 
        quitGButton.setFont(new Font("Verdana", Font.PLAIN, 18));
        quitGButton.setForeground(Color.WHITE);
        quitGButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                exitGame();
            }
        });
        quitGButton.setBorderPainted(false);
        buttonsPanel.add(quitGButton, menuLayout);

        menuLayout.weighty = 1;

        CentralPanel.add(buttonsPanel, menuLayout);
        menu.add(CentralPanel);
        menu.setVisible(true);

    }

    public void startGame(JFrame j) {
        JPanel panel = new GameJPanel();
		try {
			Robot robot = new Robot();
			robot.mouseMove(this.getX() + 210 + 48, this.getY() + 500 + 34);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		j.add(panel);
		j.setVisible(true);
    }

    public void exitGame() {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent a){

    }
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Menu();			
            }
		});
       
    }
}
