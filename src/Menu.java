import java.awt.GridBagLayout;
import java.awt.Point;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.AWTException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.awt.FontFormatException;


/**
 * 
 */

public class Menu extends GameJPanel implements ActionListener {

    //name of game
    private JLabel timeFigtherTitle; 

    //the background color
    private static final Color backGC = new Color(88, 148, 173);
    
    public Menu() {
        drawMenu();
    }

    public void drawMenu() {
        //creating the gameFrame
        JFrame menu = new JFrame("Time Fighters -- Main Menu");
        
        //creating my buttons
        JButton quitGButton = new JButton("  Quit Game   ");
        JButton playGButton = new JButton("Play Game");



        //creating Jlabel Title
        timeFigtherTitle = new JLabel("Time Fighters");


        //creating font and appending it to the compents
        Font RetroGame;
        try{
            RetroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
            RetroGame = RetroGame.deriveFont(Font.PLAIN,20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment(); 
            ge.registerFont(RetroGame);
            playGButton.setFont(RetroGame);
            quitGButton.setFont(RetroGame);
            timeFigtherTitle.setFont(RetroGame);
        }catch(IOException e){
            e.printStackTrace();
        }catch(FontFormatException e){
            e.printStackTrace();
        }
        

        //basic configs for JFrame Menu
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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
        timeFigtherTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        timeFigtherTitle.setForeground(Color.DARK_GRAY);
        timeFigtherTitle.setVisible(true);
        CentralPanel.add(timeFigtherTitle, menuLayout);
        
        

        // Creating play game button 
        playGButton.setForeground(Color.WHITE);
        playGButton.setBackground(backGC);
        playGButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                startGame(menu);
                playGButton.setVisible(false);
                quitGButton.setVisible(false);
            }
        });
        playGButton.setBorderPainted(false);
        playGButton.setFocusPainted(false);
        playGButton.setContentAreaFilled(false);
        buttonsPanel.add(playGButton, menuLayout);


        // Creating quit game button 
        quitGButton.setForeground(Color.WHITE);
        quitGButton.setBackground(backGC);
        quitGButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                exitGame();
            }
        });
        quitGButton.setBorderPainted(false);
        quitGButton.setFocusPainted(false);
        quitGButton.setContentAreaFilled(false);
        buttonsPanel.add(quitGButton, menuLayout);

        menuLayout.weighty = 1;

        CentralPanel.add(buttonsPanel, menuLayout);
        menu.add(CentralPanel);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        
    }

    //start game method
    public void startGame(JFrame j) {
        JPanel panel = new GameJPanel();
		

		j.add(panel);
		try {
			Robot robot = new Robot();
			robot.mouseMove(j.getX() + 350 , j.getY() + 700);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		j.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
				new ImageIcon("src/Plane-assets/blackCursor.png").getImage(), new Point(0, 0), "custom cursor"));
		j.setVisible(true);
    }

    //exit game method
    public void exitGame() {
        System.exit(0);
    }

    public void actionPerformed(ActionEvent a){

    }

    //main 
    public static void main(String[] args) {
        
            
                new Menu();			
            }
		
       
    }

