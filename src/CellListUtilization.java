// -----------------------------------------------------
// Assignment 4
// Part: 2
// Written by: Laurent (40020483)
// This assignment is meant practice the use of LinkedList
// Read through a list of cellphone information and create
// a LinkedList with that information
// -----------------------------------------------------

import java.util.ArrayList;
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
		CellList testingCellList = new CellList();

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
		boolean isContinue = true;

		int searchCounter = 1;
		while (isContinue)
		{
			// Prompt for a serial number to search for
			System.out.println("\n\n" +
					"Serial number search #" + searchCounter + "\n" +
					"========================\n");
			System.out.println("Input a serial number to search for. Enter 0 to exit.");
			long serialNumber = promptUserForSerialNumbersToSearchFor();
			debug_printNumber(isDebug, serialNumber);

			if (serialNumber == 0)
			{
				System.out.println("You've searched " + searchCounter + " time(s).");
				isContinue = false;
			}
			else {

				// Searching for that serial number in the list
				cellphonesFromFile.searchFor(serialNumber);
			}

			searchCounter++;
		}


		// TODO: Create some objects to test constructors and methods for CellList

		// 				 ==== End of Program ====
		//		 |										 |
		//      \|/										\|/
		//		 V										 v
		// Close the Scanner
		Instance.closeKeyInput();
		fileManip.closeFiles();
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
	 * @param isDebug     boolean if should print
	 * @param number long to be printed
	 */
	private static void debug_printNumber(boolean isDebug, long number) {
		if (isDebug)
		{
			System.out.println("Debug:\tPrinting serial number: " + number);
		}
	}
}
