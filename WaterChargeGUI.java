//Programmer: Justin Hastings 12147349
//File: WaterChargeGUI.java
//Date: October 16 2020
//Purpose: COIT11222 assignment three, task 2 - WaterChargeGUI
//A GUI application to read in multiple customer names, contact
//numbers and water usages and output the information via a GUI interface.

import java.awt.FlowLayout;
import java.awt.Font;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

//class definition
public class WaterChargeGUI extends JFrame implements ActionListener
{

		//Sets the title
		private static final String MAIN_TITLE = "  Urban Utilities Water Charge App  ";

		//Sets the max allowed entries for the water charge array
		private static final int MAX_NUM = 10;

		//Sets a string for the dashes to be used in the heading and for when displaying the data
	    private static final String TEXT_DASHES = "----------------------------------------------------------------\n";
	    private static final String formatString = "%-19s%-22s%-15s$%-12.2f\n";

		//A boolean variable for checking if the array has any values
	    private static boolean noRecords;

        //GUI components building
        private JLabel nameLabel=new JLabel("Customer name");
        private JLabel phoneLabel=new JLabel("Contact phone");
	    private JLabel waterUsageLabel=new JLabel("Water usage");

	    private JTextField nameField=new JTextField(28);
        private JTextField phoneField=new JTextField(14);
        private JTextField waterUsageField=new JTextField(7);

        private JButton enterButton=new JButton("Enter");   //
	    private JButton displayButton=new JButton("Display");
        private JButton searchButton= new JButton("Search");
        private JButton statisticsButton= new JButton("Statistics");
        private JButton exitButton=new JButton("Exit");
        private JTextArea textArea=new JTextArea(16,70);
        private JScrollPane scrollPane; // scroll pane for the text area

        private WaterCharge [] w = new WaterCharge[MAX_NUM];

        private int currentCustomer=0;//number of customer entered so far

        private static final int FRAME_WIDTH = 480;// window size
        private static final int FRAME_HEIGHT = 430;
        //Using a monospaced font so columns line up
        private Font f=new Font("Monospaced",Font.BOLD,10);

        //Constructor
        public WaterChargeGUI()
        {
           super("  Urban Utilities Water Charge App  ");
           setLayout(new FlowLayout());  //FlowLayout

           add(nameLabel);              //add componts to JFrame
           add(nameField);
           add(phoneLabel);
           add(phoneField);
           add(waterUsageLabel);
           add(waterUsageField);

           scrollPane = new JScrollPane(textArea); 	// add text area to the scroll pane
		   scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // just need vertical scrolling
	       add(scrollPane);

           add(enterButton);
           add(displayButton);
           add(searchButton);
           add(statisticsButton);
           add(exitButton);

           add(textArea);
           enterButton.addActionListener(this);  //listen to event
           displayButton.addActionListener(this);
           searchButton.addActionListener(this);
           statisticsButton.addActionListener(this);
           exitButton.addActionListener(this);
           textArea.setFont(f);
        }

        //event handling method
        public void actionPerformed(ActionEvent e)
        {
			String actionString=e.getActionCommand();
			switch(actionString)
			{
				case "Enter":
				//Calls the enterData() method
					enterData();
					break;
				case "Display":
				//Calls the display() method
					display();
					break;
				case "Search":
				//Calls the search() method
					search();
					break;
				case "Statistics":
				//Calls the statistics() method
					statistics();
					break;
				case "Exit":
				//Calls the exit() method
					exit();
					break;
				default:
					//By default, prints error to console window
					System.out.println("invalid input");
			}
		}

        // process input data
        public void enterData()
        {

           //Checks the name field isn't null
           if (nameField.getText().compareTo("") == 0)
           {
			   //Outputs an error message if a name isn't entered
			   JOptionPane.showMessageDialog(null, "You must enter a customer name", MAIN_TITLE, JOptionPane.ERROR_MESSAGE);
			   //Resets the focus to the name input field
		   	   nameField.requestFocus();
		   	   //Stops the method
		   	   return;
		   }

		   //Checks the phone number field isn't null
		   if (phoneField.getText().compareTo("") == 0)
		   {
			   //Outputs an error message if a phone number isn't entered
			   JOptionPane.showMessageDialog(null, "You must enter a phone number", MAIN_TITLE, JOptionPane.ERROR_MESSAGE);
		   	   //Resets the focus to the phone number input field
		   	   phoneField.requestFocus();
		   	   //Stops the method
		   	   return;
		   }

		   //Checks the water usage field isn't null
		   if (waterUsageField.getText().compareTo("") == 0)
		   {
			   //Outputs an error message if a phone number isn't entered
			   JOptionPane.showMessageDialog(null, "You must enter a water usage", MAIN_TITLE, JOptionPane.ERROR_MESSAGE);
		   	   //Resets the focus to the water usage input field
		   	   waterUsageField.requestFocus();
		   	   //Stops the method
		   	   return;
		   }

		   //Reads and stores the user input
           String customerName = nameField.getText();
           String phoneNum = phoneField.getText();
           int waterUsage = Integer.parseInt(waterUsageField.getText());

           //Populates the array with the user's input
		   w[currentCustomer] = new WaterCharge(customerName, phoneNum, waterUsage);

		   //Calls the method to display the heading (titles for displaying)
		   this.displayHeading();

		   //Gets the current customer details from the array
		   String currentCustomerName = w[currentCustomer].getName();
		   String currentPhoneNum = w[currentCustomer].getPhoneNum();
		   int currentWaterUsage = w[currentCustomer].getWaterUsage();
		   double charge = w[currentCustomer].calculateCharge();

		   //Displays the data that the user just entered
		   textArea.append(String.format(formatString, currentCustomerName, currentPhoneNum, currentWaterUsage + "KL", charge));




		   //Resets the user input fields
           nameField.setText("");
           phoneField.setText("");
           waterUsageField.setText("");

		   //Returns the focus to the name user input field
           nameField.requestFocus();

		   //Increments the currentCustomer counter
           currentCustomer++;
		}

        // Display all charges
		public void display()
		{
		   //Calls the checkIfRecords() method
		   noRecords = checkIfRecords();
		   if (noRecords)
		   {
			   //Stops the method if there are no records
			   return;
		   }

		   //If there are records:
		   //Calls the display heading method
		   this.displayHeading();

		   //Loops through the array, stopping at the current customer
		   for (int i = 0; i < currentCustomer; i++)
		   {
			    //Retrieves the values from the array
				String disCustomerName = w[i].getName();
				String disPhoneNum = w[i].getPhoneNum();
				int disWaterUsage = w[i].getWaterUsage();
				double disCharge = w[i].calculateCharge();

				//Displays the values from the array
				textArea.append(String.format(formatString, disCustomerName, disPhoneNum, disWaterUsage + "KL", disCharge)); //Fix the format

		   }

		   //Components to meet specification, adds the line of dashes below the values
		   textArea.append(TEXT_DASHES);
		   //Displays the current number of entries
		   textArea.append((currentCustomer) + " entries");

		}

        //search a particular customer booking
	    public void search()
	    {
			//Variable for checking if the user inputed value exists in the array
			boolean doesExist = false;
			//Sets the initial searchIndex as -1, doesn't exist
			int searchIndex = -1;

			//Calls the checkIfRecords() method
            noRecords = checkIfRecords();
		    if (noRecords)
		    {
				//Stops the method if there are no records
				return;
		    }

			//Saves the value entered into the GUI input dialog by the customer as the customer name to search for
			String searchCustomerName = JOptionPane.showInputDialog(null, "Enter a customer name", "Search", JOptionPane.PLAIN_MESSAGE);

			//Loops through the array up to the current customer
			for (int i = 0; i < currentCustomer; i++)
			{
				//Gets the name from the array
				String checkName = w[i].getName();
				//Checks if the customer's entered value for the name is the same as the current array value, being case insensitive
				if (searchCustomerName.equalsIgnoreCase(checkName))
				{
					//Sets doesExist to true
					doesExist = true;
					//Sets the searchIndex to the current index so that the customer's data can be retrieved
					searchIndex = i;
					//Breaks the loop
					break;
				}
			}

			//Runs if the customer is found
			if (doesExist)
			{
				//Gets the data for the customer from the array
				searchCustomerName = w[searchIndex].getName();
				String searchCustomerPhone = w[searchIndex].getPhoneNum();
				int searchWaterUsage = w[searchIndex].getWaterUsage();
				double searchCharge = w[searchIndex].calculateCharge();

				//Calls the displayHeading() method
				this.displayHeading();
				//Inserts message at beginning of text area
				textArea.insert("A customer found:\n\n", 0);
				//Displays the found customer's data
				textArea.append(String.format(formatString, searchCustomerName, searchCustomerPhone, searchWaterUsage + "KL", searchCharge)); //Fix format
			}
			//Runs if the customer isn't found
			else
			{
				 //Reset the text area
				 textArea.setText("");
				 //Display a popup window with error message
				 JOptionPane.showMessageDialog(null, "No such a customer found!", MAIN_TITLE, JOptionPane.ERROR_MESSAGE);
			}

	    }//end of method

       //statistics method definition
       public void statistics()
       {
		   //Calls the checkIfRecords() method
           noRecords = checkIfRecords();
		   if (noRecords)
		   {
			    //Stops the method if there are no records
		   		return;
		   }

		   //defines variables for determining statistical data
		   double totalCharge = 0;
		   double avgCharge = 0;

		   double currentCharge = 0;

		   String maxChargeName = null;
		   //Set to $0 becuase no charge can be less than $120 (for service fee)
		   double maxChargePrice = 0;

		   String minChargeName = null;
		   //Set to a large price so its nearly impossible for a price not to be lower than it
		   double minChargePrice = 1000000000;

		   //Loops through the array
		   for (int i = 0; i < currentCustomer; i++)
		   {
			   //Retrieves the current charge from the array
			   currentCharge = w[i].calculateCharge();
			   //Adds the current charge to the total charge
			   totalCharge += currentCharge;

			   //Checks if the current charge is greater than the current max charge
			   if (currentCharge > maxChargePrice)
			   {
				   //If so, sets the max charge name and charge to the current values
					maxChargePrice = currentCharge;
					maxChargeName = w[i].getName();
			   }
			   //If only one customer, max price will also be the min price
			   if (currentCharge < minChargePrice)
			   {
				   //If so, sets the min charge name and charge to the current values
				   minChargePrice = currentCharge;
				   minChargeName = w[i].getName();
			   }

		   }

		   //calculate the average charge
		   avgCharge = (totalCharge / currentCustomer);

		   //Call display method
		   this.display();

		   String statisticsFormat = "%s$%.2f%s\n";

		   //Displays the statistics data
		   textArea.append(String.format("\n\n%s$%.2f\n", "The average charge of all customers is ", avgCharge));
		   textArea.append(String.format(statisticsFormat, "The maximum charge is: ", maxChargePrice, " from " + maxChargeName));
		   textArea.append(String.format(statisticsFormat, "The minimum charge is: ", minChargePrice, " from " + minChargeName));

	   }

       // exit the app
	   public static void exit()
	   {
		   	//Popup window with exit message
			JOptionPane.showMessageDialog(null, "Thank you for using the Urban Utilities Water Usage Charge App", MAIN_TITLE, JOptionPane.PLAIN_MESSAGE);
			//Closes the GUI window
			System.exit(0);

	   }

       //helper method to display heading
       private void displayHeading()
       {
		    //Sets the title and dashed line
            textArea.setText(String.format("%-19s%-19s%-19s%7s\n", "Customer name", "Contact phone", "Water usage", "Charge"));
            textArea.append(TEXT_DASHES);
	   }

       //main method
       public static void main(String[] args)
       {
		   //Creates a new WaterChargeGUI JFrame object
		   JFrame frame = new WaterChargeGUI();
		   //Sets the default action of the GUI window close button to doing nothing when clicked
		   frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		   //Sets the size of the window
		   frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		   //Makes the window visible
		   frame.setVisible(true);
		   //Doesn't allow the window to be resized by the user
		   frame.setResizable(false);

		   // run the exit method when the JFrame (cross) close button is clicked
		   //Listener for when the GUI Window close button is clicked and overrides the default closing method
		   frame.addWindowListener(new WindowAdapter() {
			   @Override
			   public void windowClosing(WindowEvent e) {
				   //Calls the exit() method
				   exit();
			   }
		   });

	   }

	   //method to check if there are no records
	   private boolean checkIfRecords()
	   {
		    //Variable for storing if a record is found
			boolean noRecords = false;

			//Checks if current customer = 0 (no customers entered yet)
			if (currentCustomer == 0)
			{
			   //If so, displays popup GUI error message
			   JOptionPane.showMessageDialog(null, "No customer entered", MAIN_TITLE, JOptionPane.ERROR_MESSAGE);
			   //Sets noRecords to true, to stop other methods that retrieve values from the array from running with no array values
			   noRecords = true;
			}
			//Returns the value of noRecords
			return noRecords;
	   }

}// end of class definition
