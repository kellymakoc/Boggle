import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * This is for the Main Menu in GUI.
 */
public class Gui extends JFrame {
    //intro panel
    JPanel introPanel;
    JPanel upperIntroPanel;
    JPanel lowerIntroPanel;
    //JComponents for welcome, rules and information
    JLabel welcome;
    JButton rules;
    String rulesText;
    JButton info;
    String infoText;

    //photo panel
    JPanel photoPanel;
    //JComponents for the image
    Image dimg;
    JLabel picLabel;

    // Main Menu (choose options and level)
    JPanel menuPanel;
    JPanel upperMenuPanel;
    JPanel lowerMenuPanel;
    JLabel prompt_options;
    //Buttons for Game Play Options
    JButton OnePlayer;
    JButton TwoPlayer;
    JButton oneVscomp;
    JButton twoVscomp;

    /**
     * This is to set up the JFrame and panels that need to be added to it.
     * @throws IOException
     */
    public Gui() throws IOException{
        //set frame
        setTitle("Boggle");
        setSize(650, 600);
        setResizable(false);
        setLayout(new GridLayout(3,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// terminate the program when 'x' is pressed

        //set panels
        createMenu();
        setupIntroPanel();
        setupPhotoPanel();

        // add panels to JFrame
        add(introPanel);
        add(photoPanel);
        add(menuPanel);
        setVisible(true);
    }

    /**
     * This method is to create the Main Menu. It has 1 player, 2 players,
     * 1 player vs Ai and 2 players vs Ai game Mode.
     * It will show a new window for the game when a button is pressed.
     */
    public void createMenu(){
        //setup panels
        menuPanel = new JPanel(new BorderLayout(1,1));
        upperMenuPanel = new JPanel(new FlowLayout());
        lowerMenuPanel = new JPanel(new FlowLayout());

        // prompt user what to press to choose the game modes
        prompt_options =  new JLabel("To begin, please select a game mode.");
        OnePlayer = new JButton("Single Player");
        TwoPlayer = new JButton("Two Players");
        oneVscomp = new JButton("One Player vs Computer");
        twoVscomp = new JButton("Two Players vs Computer");

        // if OnePlayer button is pressed
        OnePlayer.addActionListener(e -> {
            GamePlay1 showgame1 = null;
            try {
                showgame1 = new GamePlay1(); // call GamePlay1 class
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            showgame1.setVisible(true); // show it on the screen
        });

        // if TwoPlayer button is pressed
        TwoPlayer.addActionListener(e -> {
            GamePlay2 showgame2 = null;
            try {
                showgame2 = new GamePlay2();// call GamePlay2 class
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            showgame2.setVisible(true);// show it on the screen
        });

        // if oneVscomp button is pressed
        oneVscomp.addActionListener(e -> {
            GamePlay3 showgame3 = null;
            try{
                showgame3 = new GamePlay3();// call GamePlay3 class
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            showgame3.setVisible(true);// show it on the screen
        });

        // if twoVscomp is pressed
        twoVscomp.addActionListener(e -> {
            GamePlay4 showgame4 = null;
            try{
                showgame4 = new GamePlay4();// call GamePlay4 class
            } catch (IOException ioException){
                ioException.printStackTrace();
            }
            showgame4.setVisible(true);// show it on the screen
        });

        // add buttons and label to panels
        upperMenuPanel.add(prompt_options);
        lowerMenuPanel.add(OnePlayer);
        lowerMenuPanel.add(TwoPlayer);
        lowerMenuPanel.add(oneVscomp);
        lowerMenuPanel.add(twoVscomp);

        //add to panel
        menuPanel.add(upperMenuPanel, BorderLayout.PAGE_START);
        menuPanel.add(lowerMenuPanel,BorderLayout.CENTER);
    }

    /**
     * This method is to set up the introduction panel where it shows the welcome label,
     * and the instructions for starting a game.
     */
    public void setupIntroPanel(){
        //setup panels
        introPanel = new JPanel(new BorderLayout(1,1));
        upperIntroPanel = new JPanel(new FlowLayout());
        lowerIntroPanel = new JPanel(new FlowLayout());

        //prompt user with welcome, rules and information of the game
        welcome = new JLabel("Welcome to Boggle!");
        rules = new JButton("Boggle Rules");
        ImageIcon apple = new ImageIcon("/Users/kellymak/Downloads/apple_icon.png"); // import icon for showOption Dialog
        apple = new ImageIcon(apple.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        ImageIcon lightbulb = new ImageIcon("/Users/kellymak/Downloads/lightbulb_icon.png"); // import icon for showOption Dialog
        lightbulb = new ImageIcon(lightbulb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));

        rulesText = "The game of Boggle is played on a 5 by 5 square grid of random letters made from 25 unique dice. \n" +
                "The object is to find words formed on the board by contiguous sequences of letters. \n" +
                "Letters are considered to be touching if they are horizontally, vertically, or diagonally adjacent. \n" +
                "Words can contain duplicate letters, but a single letter on the board may not appear twice in a single word. \n" +
                "Each letter in a found, valid word is worth one point. \n" +
                "The goal of a singleplayer game is to reach a set number of points as fast as possible. \n" +
                "The goal of a two player or player vs computer game is to reach a set number of points before the other player/computer.";
        info = new JButton("Helpful Information");
        infoText = "GAME MODES: \n" +
                "Single Player Mode: there is only one player in the game. The player has to acheive a certain score in order to win. \n" +
                "Two Players Mode: two players are in the game. The players are playing against each other and whoever gets to the win threshold first wins. \n" +
                "One Player vs Computer Mode: there is one player and a computer playing against each other. \n" +
                "Two Players vs Computer Mode: there are two players and a computer playing against each other. \n" +
                "\n" +
                "LEVEL OF DIFFICULTY: \n" +
                "This sets the minimum length of word to be entered, the minimum points needed to win the game, and the AI difficulty.\n"+
                "Easy:  minimum length of 2 letters, 1/32 of the maximum points to win, computer selects shortest words. \n" +
                "Medium: minimum length of 3 letters, 1/20 of the maximum points to win, computer selects words randomly.\n" +
                "Hard: minimum length of 4 letters, 1/12 of the maximum points to win, computer selects longest words.\n" +
                "Custom: player chooses custom minimum length, minimum points to win, and computer difficulty. \n" +
                "\n" +
                "IN GAME BUTTONS: \n" +
                "Rescramble: 'shakes up' the board without resetting the current game in progress (points, names, settings, etc. remain). \n" +
                "Main Menu: closes the game window, redirecting the user back to the main menu window. \n" +
                "Quit: closes all windows and ends the program. \n" +
                "\n" +
                "OTHER: \n" +
                "Please press enter to continue when it is the computer's turn.";
        ImageIcon finalApple = apple;
        rules.addActionListener(e -> JOptionPane.showMessageDialog(null, rulesText,"Rules",JOptionPane.PLAIN_MESSAGE, finalApple)); // if rules button is pressed, show the rules in a pop up window
        ImageIcon finalLightbulb = lightbulb;
        info.addActionListener(e -> JOptionPane.showMessageDialog(null, infoText,"Information",JOptionPane.PLAIN_MESSAGE, finalLightbulb));// if info button is pressed, show the information in a pop up window

        //add to panel
        upperIntroPanel.add(welcome);
        lowerIntroPanel.add(rules);
        lowerIntroPanel.add(info);

        introPanel.add(upperIntroPanel, BorderLayout.PAGE_START);
        introPanel.add(lowerIntroPanel,BorderLayout.CENTER);
    }

    /**
     * setupPhotoPanel is used to show the image on Main Menu.
     * @throws IOException
     */
    public void setupPhotoPanel() throws IOException{
        //set up panel
        photoPanel = new JPanel(new FlowLayout());
        photoPanel.setSize(150, 150);
        // get image from computer
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("/Users/kellymak/Documents/java12/Boggle/Boggle_Icon.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        // scale image to preferred size
        dimg = img.getScaledInstance(photoPanel.getWidth(), photoPanel.getHeight(), Image.SCALE_SMOOTH);
        picLabel = new JLabel(new ImageIcon(dimg)); // add image to label
        photoPanel.add(picLabel); // add to panel
    }

    /**
     * Main method calls the Gui class for the Main Menu interface
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        new Gui();
    }
}