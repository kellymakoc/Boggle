
/**
*Authors: Kelly Mak, Sarah Cloughley, Rachel Ni
*Project: Boggle
*Class: ICS4U, 2021 Spring Semester Period 2
*Teacher: Mr. Anandarajan
*Due Date: May 12, 2021
*/
import java.io.*;
import java.util.*;
import java.util.TimerTask;

class AITurn extends TimerTask{
	public void run()
	{
		System.out.println("20 seconds are up");
		System.out.println("AI's turn.");
		String userInput = BoggleTransferComments.getWord();
		System.out.println(userInput);

		if (BoggleTransferComments.checkUserInput(userInput))
				{
					//score[turn] += userInput.length();
				}
		//System.out.println("AI's score: " + score[turn]);
		System.out.println();
	}
}

//class playerTwoTurn extends TimerTask{
	//System.out.println("20 seconds are up");
	//System.out.println(""); // copy from player 2
//}

public class BoggleTransferComments
{
	//Declaration of Global Variables
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //Declaring BufferedReader
	static ArrayList <String> wordlist = new ArrayList<>(); //Declaring String ArrayList "wordlist" to store words from Wordlist file
	static ArrayList <String> allValidWords = new ArrayList<>(); //Declaring String ArrayList for all possible "valid" words on grid
	static ArrayList <String> usedWords = new ArrayList<>(); //Declaring String ArrayList for all "used" words
	static int level; //Declaring Integer "level" for level setting (as chosen by user)
	static int round = 0; //Declaring Integer "round" for number of rounds
	static int MIN_LENGTH; //Declaring Integer "MIN_LENGTH" for minimum length of words
	static int MAX_POINTS; //Declaring Integer "MAX_POINTS" for maximum number of points required to win
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
	static int start; //Declaring Integer "start" to hold starting time of stopwatch
	static int score[] = {0,0}; //Initialize Array "score" to keep track of scores

	/////////////////////////////////////////////////////////
	/**
	*Main Method
	*This method is the main method
	*
	*@param
	*@return
    */
	public static void main(String[] args) throws IOException
	{
		//Program Description and User Prompt *Redo after GUI
		System.out.println("Welcome to the word search! You'll be able to type in a string and we'll let you know if it exists in the randomized grid and if it's an actual word!");

		readWordlist(); //Call method "readWordList" to import wordlist.txt file

		boolean doneProgram = false; //Initialize boolean "doneProgram" to false - resets the game board **CHECK

		//Prompt User to choose a Game Mode
		System.out.println("Please enter a number to choose a gamemode: \n"
				+ "1. 1 player (default)\n"
				+ "2. 1 player vs AI \n"
				+ "3. 2 players \n"
				+ "4. 2 players vs AI");

		int option = Integer.parseInt(br.readLine()); //Initialize integer "option" to user input

		AITurn switchToAI = new AITurn(); //timer stuff
		//playerTwoTurn switchToPlayerTwo = new playerTwoTurn();
		Timer timer = new Timer();

		//[If/else] Different game mode options
		if (option == 2) // 1 player vs AI
		{
			SetUpLevels(); //Call method "SetUpLevels" to choose a level of difficulty

			//Prompt user to enter their name
			System.out.println("Please enter your name: ");
			String name = br.readLine(); //Set String "name" to user input - their name

			//static int score[] = {0,0}; //Initialize Array "score" to keep track of scores

			String userInput; //Declare String "userInput" to store user game inputs

			//While loop: NOTE: CONTINUE COMMENTING THIS PART AFTER GUI IS DONE
			while (true)
			{
				//[If/else] STEP ONE - Get input from user
				if (turn == 1) //AI's turn
				{
					//switchToAI();
				}

				else //player's turn
				{
					//timer.schedule(switchTime, 3000); //Automatically switches to AI turn after timer ends

					System.out.println();
					System.out.println(name + "'s turn. Please enter a word.");

					userInput = br.readLine().toUpperCase();

					if (userInput.equalsIgnoreCase("d!"))
					{
					}
					if (userInput.equalsIgnoreCase("r!"))
					{
					}
					else
					{
						break;
					}
				}//end of player's turn

				//Step 2: validate it, change score accordingly
				//if (checkUserInput(userInput))
				{
					//score[turn] += userInput.length();
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
				//Step 4: if the max score is not yet reached or turn is over, switch to the next player
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
			{
				//timer.schedule(switchTime, 3000);

				System.out.println();
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
					//timer.schedule(switchTime, 3000);

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
			/*
			SetUpLevels(option);
			int score = 0;
			while (true)
			{
				System.out.println("Please enter a word you'd like to search for or enter 'r!' to restart the program.");
				String userInput = br.readLine().toUpperCase();
				if(userInput.equalsIgnoreCase("r!"))
				{
					main(null);
				}
				if (checkUserInput(userInput))
				{
					score += userInput.length();
				}
				System.out.println("Score: " + score);
				System.out.println();

				if(score >= MAX_POINTS)
				{
					System.out.println("You've won! Thank you for playing, enter 'r!' to play again.");
					userInput = br.readLine().toUpperCase();
					if(userInput.equalsIgnoreCase("r!"))
					{
						main(null);
					}
					break;
				}
			}
			*/

			SetUpLevels();
			int score = 0;
			start = (int) System.currentTimeMillis(); //Start timer
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
						int timeElapsed = stopWatch();
						System.out.println("Your time was " + timeElapsed + "s");
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
						int timeElapsed = stopWatch();
						System.out.println("You've won! Your time is " + timeElapsed + "s");
						doneProgram = true;
						doneScramble = true;
						break;
					}
				}

			}
			int timeElapsed = stopWatch();
			System.out.println("Thank you for using this program! Your time was "
					+ timeElapsed + "s. Please come again.");
		}
	}//end Main Method

	/**
	 * Method readWordList
	 * This method gets all words from wordlist.txt
	 * @throws FileNotFoundException
	 */
	private static void readWordlist()
	{
		//[Try/catch] Reading from wordlist.txt file and catching FileNotFound and IO Exceptions
		try
		{
			Scanner sc = new Scanner(new FileReader("C:/Users/RCG/Desktop/wordlist.txt")); //Scanning from src/wordlist.txt

			//[While Loop] Adding all words from wordlist.txt to ArrayList "wordlist"
			while(sc.hasNext()) //Iteration continues as long as document hasn't ended
			{
				wordlist.add(sc.next().trim().toUpperCase()); //Ensuring all words are uppercase
			}
			sc.close(); //Close Scanner
		} //end Try/catch

		catch (FileNotFoundException e) //Catch "FileNotFound" exception
		{
			System.out.println(e.getMessage());
		}

		catch (IOException e) //Catch "IO" Exception
		{
			System.out.println(e.getMessage());
		}

		Collections.sort(wordlist, Comparator.comparing(String::length)); //Sorting words in wordlist by length

	} //End method readWordList

	/**
	*Method SetUpLevels
	*Choosing and setting the Level of Difficulty
	*
	*@param
	*@return
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

		//[If/else] Setting level parameters according to user input
		if (level == 3) //Player inputs "3": hard level
		{
			MIN_LENGTH = 4; //Setting minimum length of accepted words to 4 letters
			allValidWords(); //Call method "allValidWords" to create an ArrayList of all valid words with "MIN_LENGTH"
			MAX_POINTS = (totalScore() * 3/8); //Setting integer "MAX_POINTS" as "Total Score"*3/8

		}
		else if (level == 2) //Player inputs "2": medium level
		{
			MIN_LENGTH = 3; //Setting minimum length of accepted words to 4 letters
			allValidWords(); //Call method "allValidWords" to create an ArrayList of all valid words with "MIN_LENGTH"
			Collections.shuffle(allValidWords); //Shuffle ArrayList "allValidWords"
			MAX_POINTS = (totalScore() * 1/8); //Setting integer "MAX_POINTS" as "Total Score"*1/8

		}
		else if (level == 1) //Player inputs "1":easy level
		{
			MIN_LENGTH = 2; //Setting minimum length of accepted words to 2 letters
			allValidWords(); //Call method "allValidWords" to create an ArrayList of all valid words with "MIN_LENGTH"
			MAX_POINTS = (totalScore() * 1/16); //Setting integer "MAX_POINTS" as "Total Score"*1/16

		}
		else //Player inputs anything other than "1" "2" or "3": custom level (del) Ask how this works or if it's incomplete
		{
			//Prompt user to enter a custom Minimum Word Length
			System.out.println("Please enter the minimum length of word: ");
			MIN_LENGTH = Integer.parseInt(br.readLine()); //Store user input to integer "MIN-LENGTH"
			allValidWords(); //Call method "allValidWords" to create an ArrayList of all valid words with "MIN_LENGTH"

			//Prompt user to enter a custom Maximum Number of points to win
			System.out.println("Please enter the minimum points to win: ");
			MAX_POINTS = Integer.parseInt(br.readLine()); //Store user input to integer "MAX_POINTS"

			//Prompt user to choose a difficulty level
			System.out.println("Please enter a number for level of difficulty for the AI: \n"
					+ "1. Easy \n"
					+ "2. Medium \n"
					+ "3. Hard");
			level = Integer.parseInt(br.readLine()); //Store user input to integer "level"
		}
	} //End of method "SetUpLevels"

	/**
	 * Method getWord
	 * Returns String "word" to Method "Main"
	 * ****FINISH COMMENTING THIS LATER
	 * @return word
	 */
	public static String getWord()
	{
		String word = null; //Initialize String "word"

		round++; //Add 1 to Integer "round"

		//[If/else] Count number of remaining words based on on Integer "level" number
		if (level == 3) //level: hard
		{
			word =  allValidWords.get(allValidWords.size()-round);
		}
		else if (level == 2) //level: medium
		{
			word = allValidWords.get((int) (Math.random() * allValidWords.size()));
		}
		else //
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
	 * @return
	 */
	public static void fillAndDisplayGrid()
	{
		Collections.shuffle(Arrays.asList(dice)); //Shuffles "dice" in array "dicelist:

		//[Dbl For loop] to display letters in a grid
		for (int i = 0; i < grid.length; i++) //Rows
		{
			for (int j = 0; j < grid[0].length; j++) //Columns
			{
				int value = i * 5 + j; //Declaring integer "value" to determine the nth dice
				grid[i][j] = dice[value][(int) (Math.random() * 6)]; //Set char Array to randomized dice ArrayList value
				System.out.print(grid[i][j] + " "); //Display dice values
			}
			System.out.println();
		} //end for loop

		allValidWords.clear(); //Clear method "allValidWords" (del) why do we need to clear it?

	} //End of method "fillAndDisplayGrid"

	/**
	 * allValidWords Method
	 * This method will find all the possible "valid" words on the grid
	 * It will search through all words stored in ArrayList "wordlist"
	 * @param
	 * @return
	 */
	public static void allValidWords()
	{
		//[For Loop] Iterating through ArrayList "wordlist" to eventually determine an ArrayList of "allValidWords"
		for (int i = 0; i < wordlist.size(); i++) //Iterating through all words on ArrayList "wordlist"
		{
			//[If/else] Checking if word on wordlist is valid by sending it to method "firstLetter"
			if ((wordlist.get(i)).length() >= 0) //As long as word from wordlist has a length greater or equal to 0
			{
				boolean result = firstLetter(wordlist.get(i)); //Initialize boolean "result" to determine if the word exists on the grid

				//[If/else] Adds word to array "wordlist" if returned boolean "result" is true
				if (result)
				{
					allValidWords.add(wordlist.get(i)); //Adding the "valid word" from ArrayList "wordlist" to "allValidWords"
				}

				//[Dbl For loop] Resetting Array "visited" values to false before the for loop iterates to the next word
				for (int v = 0; v < visited.length; v++) //rows
				{
					for (int c = 0; c < visited[0].length; c++) //columns
					{
						visited[v][c] = false; //Setting Array "visited" values to false
					}
				} //end Double For loop
			} //end If/else
		} //end For loop

		Iterator <String> itr = allValidWords.iterator(); //What is this 0-0

		//While loop: Iterate through all items in ArrayList "allValidWords"
		while(itr.hasNext())
		{
			//[If/else] Remove all words on ArrayList "allValidWords" with less than integer "MIN_LENGTH" letters
			if (itr.next().length() < MIN_LENGTH)
			{
				itr.remove();
			}
		}//end While loop
		//System.out.println(allValidWords);
	}//End method "allValidWords"

	/**
	 * firstLetter Method
	 * Setting the "root letter" that AI will iterate around
	 * Receives input from method "allValidWords"
	 * @param word
	 * @return wordFound
	 */
	public static boolean firstLetter(String word)
	{
		boolean wordFound = false; //Initialize boolean "wordFound"

		//[Dbl For loop] Searching through the grid for the first letter of the word
		for(int i = 0; (i < grid.length) && (!wordFound); i++) //As long as i is less than char Array "grid" and Boolean "wordFound" is false
		{
			for(int j = 0; (j < grid[0].length) && (!wordFound); j++) //(del) why is the [0] part necessary?
			{
				//[If/else] If the first letter (root) is on the grid, send it to method "findWord"
				if(grid[i][j] == word.charAt(0))
				{
					wordFound = findWord(word, i, j, 0); //Set boolean wordFound to result of method "findWord"

					//[If/else] end process if boolean "wordFound" is true
					if(wordFound)
					{
						break; //End processes
					}
				}
			}
		} //end Double For loop

		return wordFound;
	} //End method firstLetter

	/**
	 * Method findWord
	 * Recursive method that iterates around a "root letter" to search for words
	 * Receives input from method "firstLetter" for the word, first root letter's coordinates, and "0"
	 * Returns to method "firstLetter"
	 * @param word, i, j, position
	 * @return wordFound
	 */
	public static boolean findWord(String word, int i, int j, int position)
	{
		boolean wordFound = false; //set boolean "wordFound" to false

		//[If/else] Iterating around the "root" letter
		if(grid[i][j] == word.charAt(position)) //If the letter on the grid's coordinates equal to the first letter
		{
			visited[i][j] = true; //Set coordinates of boolean Array "visited" to true, indicating the coordinate has been used

			//[If/else] Creating the Recursion base case: Setting wordFound as true once word.length has been reached
			if(position + 1 == word.length()) //If word length has been reached
			{
				return wordFound = true; //Return boolean "wordFound" as true
			} //end If/else

			//[Dbl For loop] Iterating around the root letter as long as boolean "wordFound" is false
			for(int x = i-1; (x <= i+1) && (!wordFound); x++) //rows
			{
				for(int y = j-1; (y <= j+1) && (!wordFound); y++)//columns
				{
					//[If/else] Checking the letters around the root letter in the grid
					if(x == 0 && y == 0) //Do not check the "middle position"
					{
					}
					else if(x < 0 || y < 0 || x > grid.length - 1 || y > grid.length - 1) //Do not check "out of bounds" coordinates
					{
					}
					else if(!visited[x][y]) //If word in grid has not yet been visited, check it
					{
						wordFound = findWord(word, x, y, position + 1); //Set boolean "wordFound" to method "findWord" (recursive)

						//[If/else] wordFound is true
						if(wordFound)
						{
							break; //End process
						} //end If/else
					}
				}
			} //End Dbl for loop
		} //End of if/else

		return wordFound; //return boolean "wordFound" to method firstLetter

	} //End of method findWord

	/**
	 * Method allValidWords
	 * Calculates a running total of all possible points to be scored
	 * Returns Integer "MAX_POINTS" to method "SetUpLevels"
	 * @return MAX_POINTS
	 */
	public static int totalScore()
	{
		//For loop: Iterating through ArrayList "allValidWords"
		for (String word: allValidWords)
		{
			MAX_POINTS += word.length(); //Add points to Integer "MAX_POINTS" according to length of words in ArrayList "allValidWords"
		} //end For loop

		return MAX_POINTS; //Return Integer "MAX_POINTS"  to method "SetUpLevels"
	} //End of method totalScore

	/**
	 * GUI stuff so I'll comment later
	 * 	// add duplicated word check, scores
	 * @param userInput
	 * @return
	 */
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
	} //End method checkUserInput

	/**
	 * Method stopWatch
	 * This method will count the amount of time that has passed
	 * Default Game Mode (1 player): Timer counts up to see how long they have taken
	 * Multiplayer Mode (>1 player): Timer gives 20s per player
	 */
	public static int stopWatch()
	{
		int now = (int) System.currentTimeMillis(); //Current time
		return (int) (now-start)/1000;
	} //End Method stopWatchDefault
}