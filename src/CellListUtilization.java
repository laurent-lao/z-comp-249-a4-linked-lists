import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CellListUtilization {

    public static void main(String[] args) {
        // Start code here

        // TODO: Creates two empty lists from the CellList class

		// Read from
		FileManip fileManip = new FileManip();
		fileManip.initializeReader("Cell_Info.txt");
		Scanner reader = fileManip.getInputReader();
    }
}
