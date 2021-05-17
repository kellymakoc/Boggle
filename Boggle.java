/**
 *Authors: Kelly Mak, Sarah Cloughley, Rachel Ni
 *Project: Boggle
 *Class: ICS4U, 2021 Spring Semester Period 2
 *Teacher: Mr. Anandarajan
 *Due Date: May 12, 2021
 */
import java.io.*;
import java.util.*;
public class Boggle
{
	//Declaration of Global Variables
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Declaring BufferedReader
	static ArrayList <String> wordlist = new ArrayList<>(); //Declaring String ArrayList "wordlist" to store words from Wordlist file
	static ArrayList <String> allValidWords = new ArrayList<>(); //Declaring String ArrayList for all possible "valid" words on grid
	static ArrayList <String> usedWords = new ArrayList<>(); //Declaring String ArrayList for all "used" words
	static int level; //Declaring Integer "level" for level setting (as chosen by user)
	static int round = 0; //Declaring Integer "round" for number of rounds
	static int MIN_LENGTH; //Declaring Integer "MIN_LENGTH" for minimum length of words
	static int MAX_POINTS; //Declaring Integer "MAX_POINTS" for maximum points of
	static int turn = 0; //Declaring Integer "turn" for number of turns
	static int winner; //Declaring Integer "winner" to determine winner
	static char grid[][] = new char[5][5]; //Declaring Character Array of 5x5 to display and store coordinates
	static char dice[][] = {{'A', 'A', 'A', 'F', 'R', 'S'}, {'A', 'E', 'E', 'G', 'M', 'U'}, {'C', 'E', 'I', 'I', 'L', 'T'}, {'D', 'H', 'H', 'N', 'O', 'T'}, {'F', 'I', 'P', 'R', 'S', 'Y'},
			{'A', 'A', 'E', 'E', 'E', 'E'}, {'A', 'E', 'G', 'M', 'N', 'N'}, {'C', 'E', 'I', 'L', 'P', 'T'}, {'D', 'H', 'L', 'N', 'O', 'R'}, {'G', 'O', 'R', 'R', 'V', 'W'},
			{'A', 'A', 'F', 'I', 'R', 'S'}, {'A', 'F', 'I', 'R', 'S', 'Y'}, {'C', 'E', 'I', 'P', 'S', 'T'}, {'E', 'I', 'I', 'I', 'T', 'T'}, {'H', 'I', 'P', 'R', 'R', 'Y'},
			{'A', 'D', 'E', 'N', 'N', 'N'}, {'B', 'J', 'K', 'Q', 'X', 'Z'}, {'D', 'D', 'L', 'N', 'O', 'R'}, {'E', 'M', 'O', 'T', 'T', 'T'}, {'N', 'O', 'O', 'T', 'U', 'W'},
			{'A', 'E', 'E', 'E', 'E', 'M'}, {'C', 'C', 'N', 'S', 'T', 'W'}, {'D', 'H', 'H', 'L', 'O', 'R'}, {'E', 'N', 'S', 'S', 'S', 'U'}, {'O', 'O', 'O', 'T', 'T', 'U'}}; //Declaring 2D 5x5 Dice Array
	static boolean visited[][] = new boolean[5][5]; //Declaring 5x5 Boolean Array for "visited" letters (del all reset to false when word is found)
	static ArrayList <Integer> scores = new ArrayList<>(); //Declaring String ArrayList for all possible "valid" words on grid

	/////////////////////////////////////////////////////////
	/**
	 *Main Method
	 *This method is the main method
	 *
	 *@param
	 *@arg n/a
	 */
	public static void main(String[] args) throws IOException
	{
		//Program Description and User Prompt (del)GUI this later
		System.out.println("Welcome to the word search! You'll be able to type in a string and we'll let you know if it exists in the randomized grid and if it's an actual word!");

		readWordlist(); //Call method "readWordList"

		boolean doneProgram = false; //Declare and initialize boolean "doneProgram" to false (rep) reset board

		//User Prompt to choose a Game Mode
		System.out.println("Please enter a number to choose a gamemode: \n"
				+ "1. 1 player (default)\n"
				+ "2. 1 player vs AI \n"
				+ "3. 2 players \n"
				+ "4. 2 players vs AI");

		int option = Integer.parseInt(br.readLine()); //Declare and initialize integer "option" to user input

		//Initiating if/else for player option 2: 1 player vs AI
		if (option == 2) // 1 player vs AI
		{
			SetUpLevels(); //Call method "SetUpLevels"
			System.out.println("Please enter your name: ");
			String name = br.readLine();
			int score[] = {0,0};
			String userInput;
			while (true) {
				//Step 1: get input from user
				if (turn == 1) //AI's turn
				{
					System.out.println("AI's turn.");
					userInput = getWord();
					System.out.println(userInput);
				}
				else //player's turn
				{
					System.out.println();
					System.out.println(name + "'s turn. Please enter a word.");
					userInput = br.readLine().toUpperCase();
					if (userInput.equalsIgnoreCase("d!")){

					}
					if (userInput.equalsIgnoreCase("r!")){

					}
				}
				//Step 2: validate it, change score accordingly
				if (checkUserInput(userInput))
				{
					score[turn] += userInput.length();
				}
				if(turn == 1)
				{
					System.out.println("AI's score: " + score[turn]);
					System.out.println();
				}
				else
				{
					System.out.println(name + "'s score: " + score[turn]);
					System.out.println();
				}

				//Step 3: check if max score is reached
				if (score[turn] >= MAX_POINTS)
				{
					winner = turn;
					if(turn == 2)
					{
						System.out.println("The AI has won!");
					}
					else
					{
						System.out.println(name + " has won!");
					}
					break;
				}
				//Step 4: if the max score is not yet reached, switch to the next player
				turn = 1 - turn;
			}
		}
		else if (option == 3) //2 players
		{
			SetUpLevels();
			String [] names = new String [2];
			for (int i = 0; i < names.length; i++){
				System.out.println("Player "+ (i+1) + ": Please enter your name: ");
				names[i]= br.readLine();
			}
			int score[] = {0, 0};
			while (true)
			{System.out.println();
				System.out.println(names[turn] + "'s turn.");

				//Step 1: get input from user
				String userInput = br.readLine().toUpperCase();

				//Step 2: validate it, change score accordingly
				if (checkUserInput(userInput))
				{
					score[turn] += userInput.length();
				}

				System.out.println(names[turn] + "'s score: " + score[turn]);
				System.out.println();

				//Step 3: check if max score is reached
				if (score[turn] >= MAX_POINTS)
				{
					winner = turn;
					System.out.println(names[turn] + " has won!");
					break;
				}

				//Step 4: if the max score is not yet reached, switch to the next player
				turn = 1- turn;
			}
		}
		else if (option == 4) // 2 players vs AI
		{
			SetUpLevels();
			String [] three_players = {"", "", "AI"};
			for (int i = 0; i < three_players.length -1; i++){
				System.out.println("Player "+ (i+1) + ": Please enter your name: ");
				three_players[i] = br.readLine();
			}
			int score [] = {0,0,0};
			String userInput;
			while (true)
			{
				//Step 1: get input
				if (turn == 2) // AI turn
				{
					System.out.println("AI's turn");
					userInput = getWord();
					System.out.println(userInput);
				}
				else // player's turn
				{
					System.out.println();
					System.out.println(three_players[turn] + "'s turn.");
					userInput = br.readLine().toUpperCase();
				}

				//Step 2: validate it, change score accordingly
				if (checkUserInput(userInput))
				{
					score[turn] += userInput.length();
				}
				if(turn == 2)
				{
					System.out.println("AI's score: " + score[turn]);
					System.out.println();
				}
				else
				{
					System.out.println(three_players[turn] + "'s score: " + score[turn]);
					System.out.println();
				}

				//Step 3: check if max score is reached
				if (score[turn] >= MAX_POINTS)
				{
					winner = turn;
					if(turn == 2)
					{
						System.out.println("The AI has won!");
					}
					else
					{
						System.out.println(three_players[turn] + " has won!");
					}
					break;
				}

				//Step 4: if the max score is not yet reached, switch to the next player
				if (turn != 2)
				{
					turn += 1;
				}
				else
				{
					turn = 0;
				}
			}
		}

		else // 1 player (default mode)
		{
			SetUpLevels();
			int score = 0;
			while (!doneProgram)
			{
				boolean doneScramble = false;
				while (!doneScramble)
				{
					System.out.println("Please enter a string you'd like to search for. Enter 'r!' to rescramble the grid. Enter 'd!' to exit the program.");
					String userInput = br.readLine().toUpperCase();
					//if the user inputs rescramble!, break out of the scramble loop which re-calls fillAndDisplayGrid
					if (userInput.equals("R!"))
					{
						doneScramble = true;
					}
					//if the user enters done!, break out of all loops and end the program
					else if (userInput.equals("D!")){
						doneScramble = true;
						doneProgram = true;
					}
					//re-prompt if they didn't input anything
					else if (userInput.isEmpty())
					{
					}
					//the user wants to search for their entered word, call the findDirection function with their input
					else
					{
						if (checkUserInput(userInput))
						{
							score += userInput.length();
						}

						System.out.println("Score: " + score);
						System.out.println();
					}
					if(score >= MAX_POINTS)
					{
						System.out.println("You've won!");
						doneProgram = true;
						doneScramble = true;
						break;
					}
				}

			}
			System.out.println("Thank you for using this program! Please come again.");
		}
	}

	/**
	 * this method is to get all the words from the wordlist.txt
	 * @throws FileNotFoundException
	 */
	private static void readWordlist()

	{
		try
		{
			Scanner sc = new Scanner(new FileReader("/Users/kellymak/Documents/java12/Boggle/wordlist.txt")); //src/wordlist.txt
			while(sc.hasNext())
			{
				wordlist.add(sc.next().trim().toUpperCase());
			}
			sc.close();
		}
		catch (FileNotFoundException e)
		{
			System.out.println(e.getMessage());
		}
		catch (IOException e)
		{
			System.out.println(e.getMessage());
		}
		Collections.sort(wordlist, Comparator.comparing(String::length));
	}

	/**
	 *Method SetUpLevels
	 *Choosing and setting the Level of Difficulty
	 *
	 *@param
	 *@return n/a
	 */
	public static void SetUpLevels() throws IOException
	{
		//Prompting user to choose a Difficulty Level
		System.out.println();
		System.out.println("Please enter a number to choose a level of difficulty: \n"
				+ "1. Easy \n"
				+ "2. Medium \n"
				+ "3. Hard \n"
				+ "4. Custom");
		level = Integer.parseInt(br.readLine()); //Set Integer "Level" to user input
		System.out.println();
		fillAndDisplayGrid(); //Call Method "fillAndDisplayGrid"
		if (level == 3) //level" hard
		{
			MIN_LENGTH = 4;
			allValidWords();
			MAX_POINTS = (totalScore() * 3/8);

		}
		else if (level == 2) // level: medium
		{
			MIN_LENGTH = 3;
			allValidWords();
			Collections.shuffle(allValidWords);
			MAX_POINTS = (totalScore() * 1/8);

		}
		else if (level == 1) //level:easy
		{
			MIN_LENGTH = 2;
			allValidWords();
			MAX_POINTS = (totalScore() * 1/16);

		}
		else //custom
		{
			System.out.println("Please enter the minimum length of word: ");
			MIN_LENGTH = Integer.parseInt(br.readLine());
			allValidWords();
			System.out.println("Please enter the minimum points to win: ");
			MAX_POINTS = Integer.parseInt(br.readLine());
			System.out.println("Please enter a number for level of difficulty for the AI: \n"
					+ "1. Easy \n"
					+ "2. Medium \n"
					+ "3. Hard");
			level = Integer.parseInt(br.readLine());
		}
	}

	public static String getWord()
	{
		String word = null;
		round++;
		if (level == 3) //level: hard
		{
			word =  allValidWords.get(allValidWords.size()-round);
		}
		else if (level == 2) //level: medium
		{
			word = allValidWords.get((int) (Math.random() * allValidWords.size()));
		}
		else
		{
			word = allValidWords.get(round);
		}
		return word;
	}

	/**
	 * fillAndDisplayGrid Method
	 * Fills the board with dice elements
	 * Generates random orders from the die
	 * @param 
	 * @return n/a
	 */
	public static void fillAndDisplayGrid()
	{
		Collections.shuffle(Arrays.asList(dice)); //Shuffles dicelist

		//Initiating for loop to display grid's letters
		for (int i = 0; i < grid.length; i++) //Rows
		{
			for (int j = 0; j < grid[0].length; j++) //Columns
			{
				int value = i * 5 + j; //Declaring integer "value" to determine x coordinate of dice
				grid[i][j] = dice[value][(int) (Math.random() * 6)]; // dice 1, value of it
				System.out.print(grid[i][j] + " ");
			}
			System.out.println();
		}
		allValidWords.clear();
	}

	/**
	 * This method is to find all the possible valid words from the grid.
	 * it will go thru all the words in the wordlist and search if it is on the grid
	 */
	public static void allValidWords()
	{
		for (int i = 0; i < wordlist.size(); i++)
		{
			if ((wordlist.get(i)).length() >= 0)
			{
				boolean result = firstLetter(wordlist.get(i));
				if (result)
				{
					allValidWords.add(wordlist.get(i));
				}
				for (int v = 0; v < visited.length; v++)
				{
					for (int c = 0; c < visited[0].length; c++)
					{
						visited[v][c] = false;
					}
				}
			}
		}

		Iterator <String> itr = allValidWords.iterator();
		while(itr.hasNext())
		{
			if (itr.next().length() < MIN_LENGTH)
			{
				itr.remove();
			}
		}
		//System.out.println(allValidWords);
	}

	public static boolean firstLetter(String word)
	{
		boolean wordFound = false;
		for(int i = 0; (i < grid.length) && (!wordFound); i++)
		{
			for(int j = 0; (j < grid[0].length) && (!wordFound); j++)
			{
				if(grid[i][j] == word.charAt(0))
				{
					wordFound = findWord(word, i, j, 0);
					if(wordFound)
					{
						break;
					}
				}
			}
		}
		return wordFound;
	}

	public static boolean findWord(String word, int i, int j, int position)
	{
		boolean wordFound = false;
		if(grid[i][j] == word.charAt(position))
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
					if(x == 0 && y == 0)
					{
					}
					else if(x < 0 || y < 0 || x > grid.length - 1 || y > grid.length - 1)
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

	public static int totalScore()
	{
		for (String word: allValidWords)
		{
			MAX_POINTS += word.length();
		}
		return MAX_POINTS;
	}

	// add duplicated word check, scores
	public static boolean checkUserInput(String userInput)
	{
		if (allValidWords.contains(userInput))
		{
			if (!usedWords.contains(userInput))
			{
				usedWords.add(userInput);
				System.out.println("VALID, congratulations!");
				System.out.println(allValidWords);
				return true;
			}
			else
			{
				System.out.println("This word has been used before.");
				System.out.println(usedWords);
				return false;
			}

		}
		else
		{
			System.out.println("INVALID, try again!");
			return false;
		}
	}
}