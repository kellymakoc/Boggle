/**
 *Authors: Kelly Mak, Sarah Cloughley, Rachel Ni
 *Project: Boggle
 *Class: ICS4U, 2021 Spring Semester Period 2
 *Teacher: Mr. Anandarajan
 *Due Date: May 12, 2021
 */

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

import static javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;

public class GamePlay2 extends JFrame implements ActionListener{
    //Declare
    static ArrayList<String> wordlist = new ArrayList<>(); // String ArrayList "wordlist" to store words from Wordlist file
    static ArrayList<String> allValidWords = new ArrayList<>(); // String ArrayList for all possible "valid" words on grid
    static ArrayList<String> usedWords = new ArrayList<>(); // String ArrayList for all "used" words
    static String[] names = new String[2]; // String array for all players' names
    static int[] score = new int[2]; // integer array for all players' score
    static int option; // integer variable for players' level of difficulty
    long start;// record start time
    static int turn = 0; // switch players' turn
    static int MIN_LENGTH; //Declaring Integer "MIN_LENGTH" for minimum length of words
    static int MAX_POINTS; //Declaring Integer "MAX_POINTS" for maximum points of
    char[][] dice = {{'A', 'A', 'A', 'F', 'R', 'S'}, {'A', 'E', 'E', 'G', 'M', 'U'}, {'C', 'E', 'I', 'I', 'L', 'T'}, {'D', 'H', 'H', 'N', 'O', 'T'}, {'F', 'I', 'P', 'R', 'S', 'Y'},
            {'A', 'A', 'E', 'E', 'E', 'E'}, {'A', 'E', 'G', 'M', 'N', 'N'}, {'C', 'E', 'I', 'L', 'P', 'T'}, {'D', 'H', 'L', 'N', 'O', 'R'}, {'G', 'O', 'R', 'R', 'V', 'W'},
            {'A', 'A', 'F', 'I', 'R', 'S'}, {'A', 'F', 'I', 'R', 'S', 'Y'}, {'C', 'E', 'I', 'P', 'S', 'T'}, {'E', 'I', 'I', 'I', 'T', 'T'}, {'H', 'I', 'P', 'R', 'R', 'Y'},
            {'A', 'D', 'E', 'N', 'N', 'N'}, {'B', 'J', 'K', 'Q', 'X', 'Z'}, {'D', 'D', 'L', 'N', 'O', 'R'}, {'E', 'M', 'O', 'T', 'T', 'T'}, {'N', 'O', 'O', 'T', 'U', 'W'},
            {'A', 'E', 'E', 'E', 'E', 'M'}, {'C', 'C', 'N', 'S', 'T', 'W'}, {'D', 'H', 'H', 'L', 'O', 'R'}, {'E', 'N', 'S', 'S', 'S', 'U'}, {'O', 'O', 'O', 'T', 'T', 'U'}}; //Declaring 2D 5x5 Dice Array
    static boolean[][] visited = new boolean[5][5]; //Declaring 5x5 Boolean Array for "visited" letters (del all reset to false when word is found)
    // top panel (Welcoming panel)
    JPanel intro;
    JLabel welcome;
    JLabel pointsToWin; // show user how many points to win

    //left side panel (players names and scores)
    JPanel Players;
    //player 1 info
    JLabel player1_Name;
    JLabel score1;
    //player 2 info
    JLabel player2_Name;
    JLabel score2;

    // right side panel
    JPanel WordsEntered;
    JLabel listTitle; // used word label for list
    JList foundWords; // found words list
    DefaultListModel listModel; //list to store all foundWords

    //centre panel: grid
    final static int ROW = 5; // set ROW to 5 for the board
    final static int COL = 5;// set COL to 5 for the board
    static ArrayList<ArrayList<Character>> grid; //Declaring Character Array of 5x5 to display and store coordinates
    JPanel gridPanel;
    JLabel[][] gridLabel; // display board

    //bottom panel:textfield,  buttons
    JPanel buttonPanel;
    JPanel lowerButtonPanel;
    JPanel upperButtonPanel;
    JLabel prompt; // show whose turn
    JTextField userInput; // get input from players
    JLabel showUser; // show user if the input is a valid word or not
    JButton reScramble; // rescramble button for a new board by calling fillandDisplayGrid
    JButton back; // back button to go back to Main Menu (Gui.java)
    JButton quit; //quit button for end program

    //config
    JPanel config;
    JComboBox <Integer> minlength; // scroll down box for players to choose minimum length in custom settings
    JTextField maxPointsInput; // get input from players for the maximum points to win

    /**
     * setup GUI and methods
     * @throws IOException
     */
    public GamePlay2() throws IOException {
        start = Calendar.getInstance().getTimeInMillis();// get start time
        readFile();// call readFile method
        //set frame
        setTitle("Two Players");
        setSize(1000, 600);
        setLayout(new BorderLayout(1, 1));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // close window when 'x' is pressed

        //setup panels
        setupBogglePanel();
        setupPlayers();
        setupLevels();
        setIntroPanel();
        setupFoundWords();
        setupButtonPanel();

        try {
            new Welcome();
        } catch (UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
        // add all panels to JFrame
        add(intro, BorderLayout.PAGE_START); // located on the top of the Frame
        add(Players, BorderLayout.LINE_START); // located on the left side of the Frame
        add(WordsEntered, BorderLayout.LINE_END); // located on the right side
        add(gridPanel, BorderLayout.CENTER); // located in the centre
        add(buttonPanel, BorderLayout.PAGE_END); // located at the bottom
    }

    /**
     * setupLevels is used to prompt user and ask user for level of difficulty choice (easy, medium, hard, custom). And set MAX_POINTS and MIN_LENGTH to pass to getAllValidWords method
     * to generate all the valid words on the grid and in the wordlist.
     */
    public void setupLevels(){
        String [] levels = {"Easy","Medium","Hard","Custom"}; // String array for the difficulty choices
        ImageIcon apple = new ImageIcon("src/apple_icon.png"); // import icon for showOption Dialog
        apple = new ImageIcon(apple.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH)); // set size
        int option = JOptionPane.showOptionDialog(null, "Please choose a difficulty level.",
                "Click a button",JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, apple,
                levels, levels[0]); // pop up window for getting input from user
        if (option == 3){ //custom
            setupConfigPanel();
            ImageIcon book2 = new ImageIcon("src/book_icon_2.png"); // import icon for showOption Dialog
            book2 = new ImageIcon(book2.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
            int result = JOptionPane.showConfirmDialog(null, config,"Custom Configuration", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, book2);
            if (result == JOptionPane.OK_OPTION){ // if user press 'ok' button
                MIN_LENGTH = (int) minlength.getSelectedItem(); // get MIN_LENGTH from scroll down box
                getAllValidWords();// call method
                try{
                    Integer.parseInt(maxPointsInput.getText()); // get user input for MAX_POINTS
                    if (Integer.parseInt(maxPointsInput.getText()) > 0){ // if user enter a value larger than 0
                        MAX_POINTS = Integer.parseInt(maxPointsInput.getText()); // set the value to MAX_POINTS
                    }
                    else if (maxPointsInput.getText() == null){
                        MAX_POINTS = totalScore() * 1/20; // call totalScore method and get value for MAX_POINTS
                    }
                    else{ // if user's input is less than 0
                        MAX_POINTS = totalScore() * 1/20; // call totalScore method and get value for MAX_POINTS
                    }
                } catch (NumberFormatException e){ // if user enter a non-integer value
                    MAX_POINTS = totalScore() * 1/20;
                }
            }
        }

        else if (option == 2){ //hard
            MIN_LENGTH = 4; // set MIN_LENGTH to 4
            getAllValidWords(); // call method
            MAX_POINTS = totalScore() * 1/12; // set MAX_POINTS
            if (MAX_POINTS > 100){ // if MAX_POINTS is larger than 100
                MAX_POINTS = 100; // set value to 100
            }
        }
        else if (option == 1){ //medium
            MIN_LENGTH = 3; // set MIN_LENGTH to 3
            getAllValidWords(); // call method
            MAX_POINTS = totalScore() * 1/20; // set MAX_POINTS
            if (MAX_POINTS > 50){ // if value is larger than 50
                MAX_POINTS = 50;// set value to 50
            }
        }
        else if (option == 0){ //easy
            MIN_LENGTH = 2;// set MIN_LENGTH to 2
            getAllValidWords();// call method
            MAX_POINTS = totalScore() * 1/32;// set MAX_POINTS
            if (MAX_POINTS > 25){// if value is larger than 25
                MAX_POINTS = 25;// set value to 25
            }
        }
    }

    /**
     * setupConfigPanel is used to store the option choices and prompt user for inputs
     */
    public void setupConfigPanel(){
        config = new JPanel(new GridLayout(0,2,2,2));
        Integer [] length = {2,3,4,5,6,7}; // store for minimum length choices
        minlength = new JComboBox<>(length); // set a scroll down box
        maxPointsInput = new JTextField("");// set textfield for user input

        // add to config panel
        config.add(new JLabel("Minimum length of word: "));
        config.add(minlength);
        config.add(new JLabel("Points to win: "));
        config.add(maxPointsInput);
    }

    /**
     * setIntroPanel is used to set up the panel and JComponent for welcome and show max points to win
     */
    public void setIntroPanel() {
        intro = new JPanel(new FlowLayout());
        welcome = new JLabel("Welcome to Boggle, " + names[1] + "!");
        pointsToWin = new JLabel(" You need " + MAX_POINTS + " points to win the game. Good Luck!");
        intro.add(welcome);
        intro.add(pointsToWin);
    }

    /**
     * setupBogglePanel is used to show Boggle gameboard
     */
    public void setupBogglePanel() {
        gridPanel = new JPanel(new GridLayout(ROW, COL));
        gridPanel.setMinimumSize(new Dimension(500, 500));
        gridPanel.setPreferredSize(new Dimension(500, 500));
        gridLabel = new JLabel[ROW][COL];
        grid = new ArrayList<ArrayList<Character>>(); // set grid as 2d arraylist
        grid = fillandDisplayGrid(dice);
        //fill in the grid
        for (int r = 0; r < ROW; r++) {
            for (int c = 0; c < COL; c++) {
                JLabel charLabel = new JLabel(grid.get(r).get(c).toString());
                gridLabel[r][c] = charLabel;
                gridPanel.add(charLabel);
            }
        }
    }

    /**
     * setupPLayers is used to get user input for players' names.
     */
    public void setupPlayers() {
        Players = new JPanel(new FlowLayout());
        //set JLabels for names and scores
        player1_Name = new JLabel("");
        score1 = new JLabel("'s score: " + score[0]);
        player2_Name = new JLabel("");
        score2 = new JLabel("'s score: " + score[1]);

        // get images for dialogs
        ImageIcon lightbulb = new ImageIcon("src/lightbulb_icon.png"); // import icon for showOption Dialog
        lightbulb = new ImageIcon(lightbulb.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
        ImageIcon fish = new ImageIcon("src/fish_icon.png"); // import icon for showOption Dialog
        fish = new ImageIcon(fish.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));

        // get Player 1's name
        String name1 = (String) JOptionPane.showInputDialog(null,"Player 1, please enter your name:","Enter Name",JOptionPane.PLAIN_MESSAGE,lightbulb, null, null);
        if (name1 == null || name1.length() < 1) { // if user's input is an invalid input
            name1 = "Player 1";
        }
        //set name and store to array
        player1_Name.setText(name1);
        names[0] = name1;
        // get Player 2's name
        String name2 = (String) JOptionPane.showInputDialog(null,"Player 2, please enter your name:","Enter Name",JOptionPane.PLAIN_MESSAGE,fish, null, null);
        if (name2 == null || name2.length() < 1){// if user's input is an invalid input
            name2 = "Player 2";
        }
        //set name and store to array
        player2_Name.setText(name2);
        names[1] = name2;
        Players.add(player1_Name);
        Players.add(score1);
        Players.add(player2_Name);
        Players.add(score2);
    }

    /**
     * setupFoundWords is used to show user the words entered.
     */
    public void setupFoundWords() {
        WordsEntered = new JPanel();
        WordsEntered.setPreferredSize(new Dimension(100,600));
        listTitle = new JLabel("Used Words:"); // show user what the list is
        //create a list for showing all the words entered
        listModel = new DefaultListModel();
        foundWords = new JList <String>(listModel);
        JScrollPane scroll = new JScrollPane(); // get scrollpane for the scrollbar
        scroll.getViewport().setView(foundWords); // add list to scrollpane
        scroll.setVerticalScrollBarPolicy(VERTICAL_SCROLLBAR_ALWAYS);// vertical scroll bar always appear
        foundWords.setLayoutOrientation(JList.VERTICAL);// words will go vertically in order
        // add to panel
        WordsEntered.add(listTitle);
        WordsEntered.add(scroll);
    }

    /**
     * setupButtonPanel is used for prompting user where to press buttons and setup buttons for end game, go back to menu and rescramble
     */
    public void setupButtonPanel() {
        buttonPanel = new JPanel(new BorderLayout(1,1));
        upperButtonPanel = new JPanel(new FlowLayout()); // use to store prompts
        lowerButtonPanel = new JPanel(new FlowLayout()); // used to store buttons

        // set JComponents for prompts and inputs
        prompt = new JLabel(names[0]+"'s turn. Please press enter to continue.");
        userInput = new JTextField("", 20);
        showUser = new JLabel();
        reScramble = new JButton("Rescramble");
        back = new JButton("Main Menu");
        quit = new JButton("Quit");

        userInput.addActionListener(this); // get inputs from user and pass to ActionPerform
        reScramble.addActionListener(e ->{
            grid = fillandDisplayGrid(dice);
            for (int r = 0; r < ROW; r++) {
                for (int c = 0; c < COL; c++) {
                    gridLabel[r][c].setText(grid.get(r).get(c).toString());
                }
            }
            getAllValidWords();
        }); // rescramble board and update it, along with calling getAllValidWords()
        back.addActionListener(e -> {
            usedWords.clear();
            allValidWords.clear();
            for (int i = 0; i < score.length; i++){
                names[i] = null;
                score[i] = 0;
            }
            turn = 0;
            this.dispose();
        }); // clear all lists, turns and players' info and close window
        quit.addActionListener(e -> {
            System.exit(0);
        }); // close window

        // add to panel
        upperButtonPanel.add(prompt);
        upperButtonPanel.add(userInput);
        upperButtonPanel.add(showUser);

        lowerButtonPanel.add(reScramble);
        lowerButtonPanel.add(back);
        lowerButtonPanel.add(quit);
        // set panels location
        buttonPanel.add(upperButtonPanel, BorderLayout.PAGE_START);
        buttonPanel.add(lowerButtonPanel,BorderLayout.PAGE_END);
    }
    /**
     * actionPerformed is used to get user's input from JTextField and check if it is a valid word and prompt user the results and scores
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        // clear all JLabel
        showUser.setText("");
        String word = userInput.getText().toUpperCase();// get user input and set to all upper case
        if (word.isEmpty()) { // if user input is empty
            try {
                new EnterWord();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }
            showUser.setText("Please enter a word: "); // prompt user
            userInput.setText("");
        }
        else if (word.length() < MIN_LENGTH){ // if word is less than the minimum length
            try {
                new Short();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }
            showUser.setText("This word is too short. Minimum length is " + MIN_LENGTH);
        }
        else { //if word fits with the requirement (minimum length)
            if (checkUserInput(word)) { // call method and check if the word is on the board and textfile
                if (!usedWords.contains(word)) { // if the word has not been found
                    usedWords.add(word);
                    score[turn] += word.length();
                    try {
                        new Valid();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    showUser.setText("VALID, congratulations!");
                    //update used_word panel
                    listModel.clear();
                    for (String w : usedWords) {
                        listModel.addElement(w);
                    }
                } else { // if the word has been found
                    try {
                        new FoundWord();
                    } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                        unsupportedAudioFileException.printStackTrace();
                    }
                    showUser.setText("This word has been found.");
                }
            } else { // if it is not a valid word
                try {
                    new InvalidAudio();
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                    unsupportedAudioFileException.printStackTrace();
                }
                showUser.setText("INVALID, try again!  This word is not on the list.");
            }
            userInput.setText("");
        }
        if (score[turn] >= MAX_POINTS) { // check if user reach to the max points
            try {
                new endGame();
            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            }
            long end = Calendar.getInstance().getTimeInMillis(); // get end time
            long result = (end - start)/ 1000; // get running time
            ImageIcon book = new ImageIcon("src/book_icon.png"); // import icon for showOption Dialog
            book = new ImageIcon(book.getImage().getScaledInstance(50,50,Image.SCALE_SMOOTH));
            if (result >= 60){ // set to __mins __ secs
                int min = (int) result/60;
                int sec = (int) result%60;
                JOptionPane.showMessageDialog(null,names[turn] + ", you've won!" + " This game took " + min + "m " + sec + "s. \n"
                        + "(: Thank you for playing, hope you had fun! :)", "Winner",JOptionPane.INFORMATION_MESSAGE, book ); // show pop up window
            }
            else{ // if time is less than 1 min
                JOptionPane.showMessageDialog(null, names[turn] + ", you've won!" + " This game took " + result + "s. \n"
                        + "(: Thank you for playing, hope you had fun! :)", "Winner",JOptionPane.INFORMATION_MESSAGE, book); // pop up window for farewell
            }
            // clear all lists and players' info
            usedWords.clear();
            allValidWords.clear();
            for (int i = 0; i < score.length; i++){
                names[i] = null;
                score[i] = 0;
            }
            dispose();
        }
        turn = 1- turn; //swich turns
        prompt.setText(names[turn] + "'s turn. Please enter a word:"); // prompt user whose turn
        score1.setText("'s score: " + score[0]);
        score2.setText("'s score: " + score[1]);
    }

    /**
     * Method readWordList - main commenting on console version
     * This method gets all words from wordlist.txt
     * @throws FileNotFoundException
     */
    public static void readFile() throws IOException {
        Scanner sc = new Scanner(new FileReader("/Users/kellymak/Documents/java12/Boggle/wordlist.txt"));
        while (sc.hasNext()){
            wordlist.add(sc.next().trim().toUpperCase());
        }
        sc.close();
        wordlist.sort(Comparator.comparing(String::length));
    }

    /**
     * fillAndDisplayGrid Method - main commenting on console version
     * Fills the board with dice elements
     * Generates random orders from the die
     * @param
     * @return n/a
     */
    public static ArrayList<ArrayList<Character>> fillandDisplayGrid(char [][] dice){
        grid.clear();
        Collections.shuffle(Arrays.asList(dice));
        for (int i = 0; i < ROW; i++){
            ArrayList<Character> row = new ArrayList<Character>();
            for (int j = 0; j < COL; j++){
                int value = i * 5 + j;
                row.add(dice[value][(int) (Math.random() * 6)]);
            }
            grid.add(row);
        }
        allValidWords.clear();
        return grid;
    }

    /**
     * getAllValidWords Method - main commenting on console version
     * This method will find all the possible "valid" words on the grid
     * It will search through all words stored in ArrayList "wordlist"
     * @param
     * @return n/a
     */
    public static void getAllValidWords(){
        allValidWords.clear();
        for (String value : wordlist) {
            boolean result = firstLetter(value);
            if (result) {
                allValidWords.add(value);
            }
            for (int v = 0; v < visited.length; v++) {
                for (int c = 0; c < visited[0].length; c++) {
                    visited[v][c] = false;
                }
            }
        }
        allValidWords.removeIf(s -> s.length() < MIN_LENGTH);
    }

    /**
     * Method allValidWords - main commenting on console version
     * Calculates a running total of all possible points to be scored
     * Returns Integer "MAX_POINTS" to method "SetUpLevels"
     * @return MAX_POINTS
     */
    public static int totalScore()
    {
        for (String word: allValidWords)
        {
            MAX_POINTS += word.length();
        }
        return MAX_POINTS;
    }

    /**
     * firstLetter Method - main commenting on console version
     * Setting the "root letter" that AI will iterate around
     * Receives input from method "allValidWords"
     * @param word
     * @return wordFound
     */
    public static boolean firstLetter(String word) {
        boolean wordFound = false;
        for(int i = 0; (i < ROW) && (!wordFound); i++)
        {
            for(int j = 0; (j < COL) && (!wordFound); j++)
            {
                if(grid.get(i).get(j) == word.charAt(0))
                {
                    wordFound = findWord( word, i, j, 0);
                    if(wordFound)
                    {
                        break;
                    }
                }
            }
        }
        return wordFound;
    }

    /**
     * Method findWord - main commenting on console version
     * Recursive method that iterates around a "root letter" to search for words
     * Receives input from method "firstLetter" for the word, first root letter's coordinates, and "0"
     * Returns to method "firstLetter"
     * @param word, i, j, position
     * @return wordFound
     */
    public static boolean findWord( String word, int i, int j, int position) {
        boolean wordFound = false;
        if(grid.get(i).get(j) == word.charAt(position))
        {
            visited[i][j] = true;
            if(position + 1 == word.length())
            {
                return wordFound = true;
            }
            for(int x = i-1; (x <= i+1) && (!wordFound); x++)
            {
                for(int y = j-1; (y <= j+1) && (!wordFound); y++)
                {
                    if(x < 0 || y < 0 || x > ROW - 1 || y > COL - 1)
                    {
                    }
                    else if(!visited[x][y])
                    {
                        wordFound = findWord(word, x, y, position + 1);
                        if(wordFound)
                        {
                            break;
                        }
                    }
                }
            }
        }
        return wordFound;
    }

    /**
     * checkUserInput will return a boolean variable whether user's input is a valid and found word.
     * @param word pass by actionPerformed to check if it is a valid word
     * @return a boolean variable
     */
    public static boolean checkUserInput(String word){
        return allValidWords.contains(word);		// check if allValidWords arraylist contains the word
    }
}