// -----------------------------------------------------
// Assignment 4
// Part: 2
// Written by: Laurent (40020483)
// This assignment is meant practice the use of LinkedList
// Read through a list of cellphone information and create
// a LinkedList with that information
// -----------------------------------------------------

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Names and ID: Laurent Lao (40020483)
 * COMP249
 * Assignment #4 Part 2
 * Due Date: April 8 2019
 * Driver Class
 */
public class CellListUtilization {

	/**
	 * Instance variables
	 */
	static class Instance {

		static Scanner keyIn;

		/**
		 * Creates a keyIn Scanner
		 */
		static void createKeyInput() {
			keyIn = new Scanner(System.in);
		}

		/**
		 * Closes the keyIn Scanner
		 */
		static void closeKeyInput() {
			keyIn.close();
		}
	}

	/**
	 * Main method
	 *
	 * @param args unused
	 */
	public static void main(String[] args) {

		// Start code here
		boolean isDebug = true; // manually toggle to see debug messages

		CellList cellphonesFromFile = new CellList();
		CellList testingCellList    = new CellList();

		// Open Scanner for Cell_Info.txt
		final String inputFileName = "Cell_Info.txt";
		FileManip    fileManip     = new FileManip();
		fileManip.initializeReader(inputFileName);
		Scanner reader = fileManip.getInputReader();

		// Read inputs from the CellList class and put into a list
		System.out.println("\n\nAdding " + inputFileName + " cellphones into linked list.");
		int parseCounter = 0;
		while (reader.hasNextLine())
		{
			System.out.println("\n\nParsing cellphone...");
			CellPhone cellPhoneFromLine = parseCellphone(reader);

			System.out.println("Checking serial number...");
			// Only add if the serial number doesn't exist
			if (!cellphonesFromFile.contains(cellPhoneFromLine.getSerialNumber()))
			{
				System.out.println("Adding to the list...");
				cellphonesFromFile.add(cellPhoneFromLine);
			}
			else
			{
				System.out.println("Duplicate entry: ignoring...");
			}
		}

		// Show content of list
		cellphonesFromFile.showContents();

		// Get serial numbers from user and search if it's there
		Instance.createKeyInput();
		boolean isEndProgram = false;

		while (!isEndProgram)
		{
			switch (displayMenu())
			{
				case 1:// Implementation for serial searching
					System.out.println("Running serial search...");
					searchForSerialNumber(isDebug, cellphonesFromFile);
					break;
				case 2:
					System.out.println("Running demo of CellPhone methods...");
					demoOfCellPhoneMethods(cellphonesFromFile);
					break;
				case 3:
					System.out.println("Running demo of CellList methods...");
					demoOfCellListMethods(cellphonesFromFile, testingCellList);
					break;
				case 4:
					System.out.println("\nWarning: will crash the program.");
					crash_insertAtIndex(cellphonesFromFile, 100);
					break;
				case 5:
					System.out.println("\nWarning: will crash the program.");
					crash_deleteFromIndex(cellphonesFromFile, 100);
					break;
				case 6:
					System.out.println("\nWarning: will crash the program.");
					error_replaceAtIndex(cellphonesFromFile, 100);
					break;
				case 7:
					System.out.println("\nWarning: will create an error for the program.");
					break;
				case 0:
					System.out.println("Ending program...");
					isEndProgram = true;
					break;
				default:
					System.out.println("No such menu option. Please retry again.");
					break;
			}
		}

		// 				 ==== End of Program ====
		//		 |										 |
		//      \|/										\|/
		//		 V										 v
		// Close the Scanner
		Instance.closeKeyInput();
		fileManip.closeFiles();
		System.out.println("Program has ended");
	}

	public static int displayMenu() {
		System.out.println("\n\nHere are the available options for this program.");
		System.out.println("[1]\t" + "Search for a serial number");
		System.out.println("[2]\t" + "Test CellPhone Methods");
		System.out.println("[3]\t" + "Test CellNode Methods");
		System.out.println("[4]\t" + "Crash: InsertAtIndex out of bounds");
		System.out.println("[5]\t" + "Crash: DeleteFromIndex out of bounds");
		System.out.println("[6]\t" + "Error: ReplaceAtIndex out of bounds");
		System.out.println("[7]\t" + "");
		System.out.println("[0]\t" + "Quit Program");

		boolean isContinue = true;
		while (isContinue)
		{
			try
			{
				System.out.print("\nPlease select an option: ");
				return Instance.keyIn.nextInt();
			} catch (InputMismatchException e)
			{
				System.out.println("You did not enter an integer. Try again.");
				// Garbage the inputs
				Instance.keyIn.nextLine();
			}
		}

		return 0;
	}

	public static void demoOfCellPhoneMethods(CellList cellphoneFromFiles) {
		// Default constructor
		System.out.println("Creating a default Cellphone...");
		CellPhone cellphone1 = new CellPhone();
		System.out.println(cellphone1);
		System.out.print("\n");

		// Parameterized constructor
		System.out.println("Creating parameterized Cellphone...");
		CellPhone cellphone2 = new CellPhone(420420, "President's Choice", 299, 2018);
		System.out.println(cellphone2);
		System.out.print("\n");

		// Use copy constructor
		System.out.println("Creating Cellphone using Copy Constructor...");
		CellPhone cellphone3 = new CellPhone(cellphone2, 710710);
		System.out.println(cellphone3);
		System.out.print("\n");

		// Clone
		System.out.println("Creating Cellphone using .clone()");
		CellPhone cellphone4 = cellphone3.clone();
		System.out.println(cellphone4);
		System.out.print("\n");

		// Get serial, brand, price, year
		System.out.println("Accessors testing");
		System.out.println("Get Serial: " + cellphone3.getSerialNumber());
		System.out.println("Get Brand: " + cellphone3.getBrand());
		System.out.println("Get Price: " + cellphone3.getPrice());
		System.out.println("Get Year: " + cellphone3.getYear());
		System.out.print("\n");

		// Set serial, brand, price, year
		System.out.println("Mutators testing");
		System.out.println("Change Serial to: 300");
		cellphone3.setSerialNumber(300);
		System.out.println("Change Brand to: LOL");
		cellphone3.setBrand("LOL");
		System.out.println("Change Price to: 10.00");
		cellphone3.setPrice(10.00);
		System.out.println("Change Year to: 1991");
		cellphone3.setYear(1991);
		System.out.println(cellphone3);
		System.out.print("\n");
	}

	public static void demoOfCellListMethods(CellList cellphoneFromFiles, CellList otherList) {
		// Copying the list of cellphones
		System.out.println("Deleting at start on empty list...");
		otherList.deleteFromStart();
		System.out.println("Copying the list of cellphones from the files (Deep copy using CellList copy constructor)...");
		otherList = new CellList(cellphoneFromFiles);
		System.out.println("Showing original list...");
		cellphoneFromFiles.showContents();
		System.out.println("Showing copy list...");
		otherList.showContents();
		System.out.println("Checking for equality: " + cellphoneFromFiles.equals(otherList));
		System.out.print("\n");

		// Modifying the new copy list and the original list
		System.out.println("Deleting node at index 3 on copy cellphone list...");
		otherList.deleteFromIndex(3);
		System.out.println("Creating a new cellphone of sn: 420, brand hello, price 21.99, year 2019.");
		CellPhone cellPhone = new CellPhone(420, "Hello", 21.99, 2019);
		System.out.println("Showing cellphone: " + cellPhone);
		System.out.println("Replacing original cellphone list cellphone at index 3 for new cellphone...");
		cellphoneFromFiles.replaceAtIndex(cellPhone, 3);

		// Showing the two list
		System.out.println("Showing the two lists...");
		cellphoneFromFiles.showContents();
		otherList.showContents();
		System.out.print("\n");

		System.out.println("Add to start of list...");
		cellphoneFromFiles.addToStart(cellPhone);
		cellphoneFromFiles.showContents();
		System.out.print("\n");

		System.out.println("Insert at Index 3");
		cellphoneFromFiles.insertAtIndex(cellPhone, 4);
		cellphoneFromFiles.showContents();
		System.out.print("\n");

		System.out.println("Delete from Index 3");
		cellphoneFromFiles.deleteFromIndex(3);
		cellphoneFromFiles.showContents();
		System.out.print("\n");

		System.out.println("Delete from start");
		cellphoneFromFiles.deleteFromStart();
		cellphoneFromFiles.showContents();
		System.out.print("\n");

		System.out.println("Replace at Index");
		cellphoneFromFiles.replaceAtIndex(cellPhone, 2);
		cellphoneFromFiles.showContents();
		System.out.print("\n");
	}

	public static void crash_insertAtIndex(CellList cellphonesFromFile, int index) {

		System.out.println("Create a cellphone to insert at index " + index);
		System.out.println("Creating a new cellphone of sn: 420, brand hello, price 21.99, year 2019.");
		CellPhone cellPhone = new CellPhone(420, "Hello", 21.99, 2019);

		cellphonesFromFile.insertAtIndex(cellPhone, index);

	}

	public static void crash_deleteFromIndex(CellList cellphonesFromFile, int index) {
		System.out.println("Deleting node at index " + index);
		cellphonesFromFile.deleteFromIndex(index);
	}

	public static void error_replaceAtIndex(CellList cellphonesFromFile, int index) {
		System.out.println("Create a cellphone to replace at index " + index);
		System.out.println("Creating a new cellphone of sn: 420, brand hello, price 21.99, year 2019.");
		CellPhone cellPhone = new CellPhone(420, "Hello", 21.99, 2019);

		cellphonesFromFile.replaceAtIndex(cellPhone, index);
	}

	public static void searchForSerialNumber(boolean isDebug, CellList cellphonesFromFile) {
		boolean isContinue    = true;
		int     searchCounter = 0;
		while (isContinue)
		{
			// Prompt for a serial number to search for
			System.out.println("\n\n" +
					"Serial number search #" + (searchCounter + 1) + "\n" +
					"========================\n");
			System.out.println("Input a serial number to search for. Enter 0 to exit.");
			long serialNumber = promptUserForSerialNumbersToSearchFor();
			debug_printNumber(isDebug, serialNumber);

			if (serialNumber == 0)
			{
				System.out.println("You've searched " + searchCounter + " time(s).");
				isContinue = false;
			}
			else
			{

				// Searching for that serial number in the list
				cellphonesFromFile.searchFor(serialNumber);
			}

			searchCounter++;
		}
	}

	public static CellPhone parseCellphone(Scanner reader) {
		String          line          = reader.nextLine();
		StringTokenizer tokenizer     = new StringTokenizer(line);
		String[]        cellphoneInfo = new String[4];

		int index = 0;
		while (tokenizer.hasMoreTokens())
		{
			cellphoneInfo[index] = tokenizer.nextToken();
			index++;
		}
		return new CellPhone(Long.parseLong(cellphoneInfo[0]), cellphoneInfo[1], Double.parseDouble(cellphoneInfo[2]), Integer.parseInt(cellphoneInfo[3]));
	}


	public static long promptUserForSerialNumbersToSearchFor() {
		long    serialNumber = 0;
		Scanner keyIn        = Instance.keyIn;

		boolean isCorrectSerialNumber = false;

		do
		{
			System.out.print("Please enter a serial number : ");

			try
			{
				// Input checks: if not a Long, throws exception
				serialNumber = keyIn.nextLong();
				isCorrectSerialNumber = true;

			} catch (InputMismatchException e)
			{
				System.out.println("The serial number is invalid. \n" +
						"Please make sure the serial number is only numbers.\n" +
						"Try again... \n");

				// Clean superficial inputs
				if (keyIn.hasNextLine())
				{
					keyIn.nextLine();
				}
			}
		} while (!isCorrectSerialNumber);

		return serialNumber;
	}

	// 							=== Debugging methods ===

	/**
	 * Debug: prints a given number
	 *
	 * @param isDebug boolean if should print
	 * @param number  long to be printed
	 */
	private static void debug_printNumber(boolean isDebug, long number) {
		if (isDebug)
		{
			System.out.println("Debug:\tPrinting serial number: " + number);
		}
	}
}
