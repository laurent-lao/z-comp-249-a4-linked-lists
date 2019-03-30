import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CellListUtilization {

	public static void main(String[] args) {
		// Start code here
		boolean isDebug = true; // manually toggle to see debug messages

		// TODO: Creates two empty lists from the CellList class

		// Open Scanner for Cell_Info.txt
		String    inputFileName = "Cell_Info.txt";
		FileManip fileManip     = new FileManip();
		fileManip.initializeReader(inputFileName);
		Scanner reader = fileManip.getInputReader();

		// Get new serial numbers from user
		ArrayList<String> serialNumbers = promptUserSerialNumbers(5);
		debug_printList(isDebug, serialNumbers);


		// ==== End of Program ====
		// Close the Scanner
		fileManip.closeFiles();
	}

	/**
	 * Prompts the user for serial numbers
	 *
	 * @param numberOfSerialNumbers represents an int for the number of serial numbers to request
	 *
	 * @return a list of Strings containing some serial numbers
	 */
	public static ArrayList<String> promptUserSerialNumbers(int numberOfSerialNumbers) {
		ArrayList<String> serialNumbersList = new ArrayList<>(numberOfSerialNumbers);
		Scanner           keyIn             = new Scanner(System.in);

		System.out.println("\n\nRequesting " + numberOfSerialNumbers + " serials...");
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
