import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CellListUtilization {

	/**
	 * Instance variables
	 */
	static class Instance{

		static Scanner keyIn;

		/**
		 * Creates a keyIn Scanner
		 */
		static void createKeyInput()
		{
			keyIn = new Scanner(System.in);
		}

		/**
		 * Closes the keyIn Scanner
		 */
		static void closeKeyInput()
		{
			keyIn.close();
		}
	}

	/**
	 * Main method
	 * @param args unused
	 */
	public static void main(String[] args) {
		// Start code here
		boolean isDebug = true; // manually toggle to see debug messages

		// TODO: Creates two empty lists from the CellList class

		// Open Scanner for Cell_Info.txt
		String    inputFileName = "Cell_Info.txt";
		FileManip fileManip     = new FileManip();
		fileManip.initializeReader(inputFileName);
		Scanner reader = fileManip.getInputReader();

		// TODO: Read inputs from the CellList class

		// Get new serial numbers from user
		Instance.createKeyInput();
		ArrayList<String> serialNumbers = promptUserForSerialNumbersToSearchFor(promptUserForHowManySerialsToSearchFor());
		debug_printList(isDebug, serialNumbers);

		// Search
		// ==== End of Program ====
		// Close the Scanner
		Instance.closeKeyInput();
		fileManip.closeFiles();
	}

	public static int promptUserForHowManySerialsToSearchFor() {
		Scanner keyIn = Instance.keyIn;
		boolean isCorrect             = false;
		int     numberOfSerialNumbers = 0;

		do
		{
			try
			{
				System.out.print("How many serials would you like to search for?: ");
				numberOfSerialNumbers = keyIn.nextInt();
				isCorrect = true;

			} catch (InputMismatchException e)
			{
				System.out.println("This is not an integer. Try again...\n\n");
				// Clean superficial inputs
				if (keyIn.hasNextLine())
				{
					keyIn.nextLine();
				}
			}

		} while (!isCorrect);

		return numberOfSerialNumbers;
	}

	/**
	 * Prompts the user for serial numbers
	 *
	 * @param numberOfSerialNumbers represents an int for the number of serial numbers to request
	 *
	 * @return a list of Strings containing some serial numbers
	 */
	public static ArrayList<String> promptUserForSerialNumbersToSearchFor(int numberOfSerialNumbers) {
		ArrayList<String> serialNumbersList = new ArrayList<>(numberOfSerialNumbers);
		Scanner           keyIn             = Instance.keyIn;

		System.out.println("\n\nRequesting " + numberOfSerialNumbers + " serials to search for...");
		for (int i = 0; i < numberOfSerialNumbers; i++)
		{
			boolean isCorrectSerialNumber = false;

			do
			{
				System.out.print("Please enter serial number #" + (i + 1) + ": ");

				try
				{
					// Input checks: if not a Long, throws exception
					Long userInput = keyIn.nextLong();
					isCorrectSerialNumber = true;

					// Cast the Long into a String and add into the list
					serialNumbersList.add(userInput.toString());
				} catch (InputMismatchException e)
				{
					System.out.println("The serial number is invalid. \n" +
							"Please make sure the serial number is only numbers.\n" +
							"Try again... \n");
				} finally
				{
					// Clean Scanner for superficials inputs
					keyIn.nextLine();
				}
			} while (!isCorrectSerialNumber);
		}

		return serialNumbersList;
	}

	// 							=== Debugging methods ===

	/**
	 * Debug: prints a given list
	 *
	 * @param isDebug     boolean if should print
	 * @param listToPrint list to be printed
	 */
	private static void debug_printList(boolean isDebug, ArrayList<String> listToPrint) {
		if (isDebug)
		{
			System.out.println("Debug:\tPrinting serial numbers");
			for (String serials : listToPrint)
			{
				System.out.println(serials);
			}
		}
	}
}
