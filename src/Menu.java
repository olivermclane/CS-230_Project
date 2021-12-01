import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
/**
 * This is the creation of the menu for the game.
 * When you called the menu it will create and JFrame
 * and Panel for the exiting and quiting the game.
 */
public class Menu implements ActionListener {
    //the background color
    private static final Color backGC = new Color(88, 148, 173);
    public static JPanel CentralPanel;
    public static JPanel panel;
    public static JFrame menu;
    public static JButton playGButton;
    public static JButton quitGButton;
    public static JPanel ButtonPanel;
    public static Menu drawMenu;
    public static JFrame j;
    public static Font RetroGame;
    public static String player;

    /**
     * This is the constructor for the menu, when called,
     * it will draw the menu. 
     */
    public Menu() {
        drawMenu();
    }

    /**
     * This is the main method of the code and will start the game
     * by creating the menu.
     * @param args this is unused in our code.
     */
    public static void main(String[] args) {
        new Menu();
    }

    /**
     * This method will end the process
     */
    public static void exitGame() {
        System.exit(0);
    }

    /**
     *  This method is used to draw the menu. In this buttons are created
     *  and organized in layouts. When this is called yur will create a 
     *  Time FitherX menu.333
     */
    public void drawMenu() {
        //creating the gameFrame
        JFrame menu = new JFrame("TimeX Fighters -- Main Menu");
        //creating my buttons
        JButton quitGButton = new JButton("  Quit Game   ");
        JButton playGButton = new JButton("Play Game");
        //creating Jlabel Title
        //name of game
        JLabel timeFigtherTitle = new JLabel("TimeX Fighters");
        //creating font and appending it to the compents
        try {
            RetroGame = Font.createFont(Font.TRUETYPE_FONT, getClass().getClassLoader().getResourceAsStream("Font/Retro Gaming.ttf"));
            RetroGame = RetroGame.deriveFont(Font.PLAIN, 20);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(RetroGame);
            playGButton.setFont(RetroGame);
            quitGButton.setFont(RetroGame);
            timeFigtherTitle.setFont(RetroGame);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        //basic configs for JFrame Menu
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setSize(700, 800);
        menu.setBackground(backGC);
        //create Title Panel & Layout by default: Flowlayout
        CentralPanel = new JPanel();
        CentralPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        CentralPanel.setLayout(new GridBagLayout());
        CentralPanel.setBackground(backGC);
        GridBagConstraints menuLayout = new GridBagConstraints();
        menuLayout.gridwidth = GridBagConstraints.REMAINDER;
        menuLayout.anchor = GridBagConstraints.NORTH;
        //temporary placement until I get an Icon
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
        JTextField name = new JTextField();
        JButton playerName = new JButton("<html><center><h2>Enter Name<br />To Play</h2></center></html>");
        playerName.setBackground(backGC);
        playerName.setBorderPainted(false);
        playerName.setFocusPainted(false);
        playerName.setHorizontalAlignment(SwingConstants.CENTER);
        playerName.setAlignmentX(Component.CENTER_ALIGNMENT);
        playerName.setForeground(Color.DARK_GRAY);
        playerName.setVisible(true);
        name.setAlignmentX(Component.CENTER_ALIGNMENT);
        name.setForeground(Color.DARK_GRAY);
        name.setVisible(true);
        playerName.setFont(RetroGame);
        CentralPanel.add(playerName, menuLayout);
        CentralPanel.add(name, menuLayout);
        playerName.addActionListener(new ActionListener() {
            /**
             * 3This will allow the play game button to be pressed, and will lock
             * the players name.
             * @param e this is the action of the button being pressed.
             */
            public void actionPerformed(ActionEvent e) {
                player = name.getText();
                name.setText("");
                playGButton.setEnabled(true);
            }
        });
        // Creating play game button
        playGButton.setForeground(Color.WHITE);
        playGButton.setBackground(backGC);
        playGButton.addActionListener(new ActionListener() {
            /**
             * This is the action event for the start game button gets
             * pressed.
             * @param ActionEvent this is the action event for getting pressed.
             */
            public void actionPerformed(ActionEvent e) {
                startGame(menu);
                CentralPanel.setVisible(false);
            }
        });
        playGButton.setBorderPainted(false);
        playGButton.setFocusPainted(false);
        buttonsPanel.add(playGButton, menuLayout);
        // Creating quit game button
        quitGButton.setForeground(Color.WHITE);
        quitGButton.setBackground(backGC);
        quitGButton.addActionListener(new ActionListener() {
            /**
             * This will call the quit game method.
             * @param e this is the action event for the button press
             */
            public void actionPerformed(ActionEvent e) {
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
        playGButton.setEnabled(false);
    }

    /**
     * THis method will create a instance of the GameJPanel
     * and start the game. This will also start a mouse tracer.
     * @param j This is the main frame for graphic to shown on.
     */
    public void startGame(JFrame j) {
        panel = new GameJPanel();
        j.add(panel);
        j.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                new ImageIcon("src/Plane-assets/blackCursor.png").getImage(), new Point(0, 0), "custom cursor"));
        try {
            Robot robot = new Robot();
            robot.mouseMove(j.getX() + GameJPanel.plane.getxPosition(), j.getY() + GameJPanel.plane.getyPosition());
        } catch (AWTException e) {
            e.printStackTrace();
        }
        j.setVisible(true);
    }
    
    /**
     * Unused Method -- required for ActionListener
     */
    public void actionPerformed(ActionEvent a) {
    }
}
