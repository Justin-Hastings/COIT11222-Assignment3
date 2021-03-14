//Programmer: Justin Hastings 12147349
//File: Vowels.java
//Date: October 16 2020
//Purpose: COIT11222 assignment three, task 1
//A GUI application to display the number of vowels and upper-case
//letters of an entered word and the reverse order of the entered word
//in lower case

//Import the required libraries for the GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Vowels extends JFrame
{

	//Constants
	//The app title
	public static final String APP_TITLE = "Vowels";
	//Inital width of the GUI window
	public static final int MAX_WIDTH = 300;
	//Inital height of the GUI window
	public static final int MAX_HEIGHT = 169;
	//Inital text for the labels
	public static final String initialAsterisk = "**************************************************";

	//GUI components
	//Label for entering a word
	private JLabel wordLabel = new JLabel("Enter a word: ");
	//Textbox to enter a word
	private JTextField wordTextField = new JTextField(15);
	//Button to call the method to determine vowels, uppercase letters and reverse of the word
	private JButton vowelsButton = new JButton("Count vowel(s)");
	//Label for displaying the vowels and uppercase statistics
	private JLabel displayStatsLabel = new JLabel(initialAsterisk);
	//Label for displaying the reverse of the word
	private JLabel displayRevWordLabel = new JLabel(initialAsterisk);


	public Vowels()
	{
		//Build GUI
		setLayout(new GridLayout(4, 1));

		//Define the JPanels
		//JPanel for entering words
		JPanel wordPanel = new JPanel(new FlowLayout() );
		//JPanel for the button
		JPanel countBtnPanel = new JPanel(new FlowLayout() );
		//JPanel for the word statistics panel (Vowels & uppercase letters)
		JPanel wordStatsPanel = new JPanel(new FlowLayout() );
		//JPanel for displaying the reverse word
		JPanel revWordPanel = new JPanel(new FlowLayout() );

		//Populate the JPanels
		//Adding the word label and text field to the word JPanel
		wordPanel.add(wordLabel);
		wordPanel.add(wordTextField);

		//Adding the button to its JPanel
		countBtnPanel.add(vowelsButton);

		//Adding the label for displaying stats to its label
		wordStatsPanel.add(displayStatsLabel);

		//Adding the label for displaying the reverse of the word to its label
		revWordPanel.add(displayRevWordLabel);

		//Display the JPanels
		add(wordPanel);
		add(countBtnPanel);
		add(wordStatsPanel);
		add(revWordPanel);

		//Sets the title of the application
		setTitle(APP_TITLE);
		//Sets the size of the application window
		setSize(MAX_WIDTH, MAX_HEIGHT);
		//Sets the initial location of the application window
		setLocation(250, 100);
		//Sets the default close operation of the application window - Close when exit button clicked
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Makes the application window visible
		setVisible(true);

		//Activate the button
		vowelsButton.addActionListener(event -> displayWordData() );

	}

	public static void main(String [] args)
	{
		Vowels appManager = new Vowels();
	}

	//Method for displaying the # vowels & uppercase letters and the reverse of the word
	private void displayWordData ()
	{
		//Gets the word entered by the user
		String enteredWord = wordTextField.getText();
		//To store the word in all lowercase
		String beforeRevWord = enteredWord;
		//Sets the word to all lowercase
		beforeRevWord = beforeRevWord.toLowerCase();
		//Initialises an integer for storing the number of vowels in the word
		int vowels = 0;
		//Initialises an integer for storing the number of uppercase letters in the word
		int upperCase = 0;
		//Initialises a string builder for storing the reverse of the word
		StringBuilder revWord = new StringBuilder("");
		//Initialises a character for storing the current character
		char charX;
		//Initialises a character for storing the current character as a lowercase
		char charZ;

		//For loop for cycling through each character in the word
		for (int i = (enteredWord.length() - 1); i > -1; i--)
		{

			//Sets charZ to the current character in lowercase
			charZ = beforeRevWord.charAt(i);
			//Adds the current character (charX) to the end current reverse of the word (revWord)
			revWord.append(charZ);

			//Checks if the current character is a vowel in any case
			if (enteredWord.charAt(i) == 'a' || enteredWord.charAt(i) == 'A' || enteredWord.charAt(i) == 'e' || enteredWord.charAt(i) == 'E'
				|| enteredWord.charAt(i) == 'i' || enteredWord.charAt(i) == 'I' || enteredWord.charAt(i) == 'o' || enteredWord.charAt(i) == 'O'
				|| enteredWord.charAt(i) == 'u' || enteredWord.charAt(i) == 'U')
			{
				//Increments vowels by 1 if true
				vowels++;
			}

			//Sets charX to the current character in its original case
			charX = enteredWord.charAt(i);

			//Checks if the current character is upper case
			if (Character.isUpperCase(charX))
			{
				//Increments upperCase by 1 if true
				upperCase++;
			}

		}

		//Set the label for displaying the number of vowels and uppercase letters
		displayStatsLabel.setText("It contains " + vowels + " vowel(s) with " + upperCase + " uppercase letter(s)");
		//Set the label for displaying the reverse words
		displayRevWordLabel.setText("The reverse display of the word is " + revWord);

	}

}