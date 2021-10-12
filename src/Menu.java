import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.BorderLayout;
/**
 * 
 */
public class Menu extends JFrame implements ActionListener {

    //name of game
    private JTextField timeFigtherTitle; 

    //the background color
    private static final Color backGC = new Color(46, 94, 135);
    
    public Menu() {
        super("Time Fighters - Menu");
        drawMenu();
    }

    public void drawMenu() {
       
        //basic configs for JFrame Menu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBackground(backGC);
        
        //create Title Panel & Layout by default: Flowlayout 
        JPanel TitlePanel = new JPanel();
        TitlePanel.setBackground(Color.DARK_GRAY);
    
        //temporary placement until I get an Icon
        JLabel TimeIcon = null; // change this in next version
        TitlePanel.add(TimeIcon);

        add(TitlePanel, BorderLayout.NORTH);

        //central panel will contain most elements
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());


        //creating JPanel for the buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.LINE_AXIS));
        buttonsPanel.setBackground(Color.DARK_GRAY);
        centralPanel.add(buttonsPanel, BorderLayout.NORTH);

        //adding game title label
        timeFigtherTitle = new JTextField(" Time Fighters");
        timeFigtherTitle.setForeground(Color.WHITE);
        timeFigtherTitle.setBackground(Color.DARK_GRAY);
        timeFigtherTitle.setHorizontalAlignment(JTextField.CENTER);
        buttonsPanel.add(timeFigtherTitle);

        // Creating play game label
        JLabel playGame = new JLabel("Play Game");
        playGame.setFont(null);
        playGame.setHorizontalAlignment(null);
        playGame.setForeground(null);
        buttonsPanel.add(playGame);
        
        // Creating play game button
        JButton playGButton = new JButton("Play Game");

        // Creating quit game label
        JLabel quitGame = new JLabel("Quit Game");
        quitGame.setFont(null);
        quitGame.setHorizontalAlignment(null);
        quitGame.setForeground(null);
        buttonsPanel.add(quitGame);

        // Creating quit game button
        JButton quitGButton = new JButton("Quit Game");

    }

    public void startGame() {

    }

    public void exitGame() {

    }

    public void actionPerformed(ActionEvent a){

    }

}
