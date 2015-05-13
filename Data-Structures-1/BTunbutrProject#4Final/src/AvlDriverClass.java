/*  Java Class: AvlDriverClass
    Author: Bryant Tunbutr
    Class: Data Structures
    Date: 12/3/13
    Description:

    I certify that the code below is my own work.

	Exception(s): N/A

 */

// to get user input
import java.util.Scanner;

// this is the driver class that runs everything
public class AvlDriverClass {

	public static void main(String[] args) {

		System.out.println("Author:  Bryant Tunbutr");

		AvlTree avlTree = new AvlTree();

		// to get file information
		avlTree.openFile();
		avlTree.readFile();

		boolean exitBool = false;

		// to make user entry continue until they enter exit value
		while (!exitBool) {

			// display user options

			System.out.print("Select an option:" + "\n"
					+ "0. Debug. This displays the airport code at each node,"
					+ "\n" + "but rotated sideways" + "\n"
					+ "1. Search for an airport" + "\n"
					+ "2. Insert a new airport " + "\n"
					+ "3. Delete an airport" + "\n" + "4. List all airports "
					+ "\n" + "5. Exit " + "\n");

			// use user input
			Scanner userInput = new Scanner(System.in);
			char inputChar = userInput.next().charAt(0);

			// switch statements based on user commands
			switch (inputChar) {

			case '0':

				// debug/print starting from root, using recursion
				avlTree.debugAkaDrawTree(avlTree.rootNode, 1);

				break;

			case '1':

				// instructions
				System.out.println("Enter the airport code to be searched for ");

				// make user input upper case
				String airportCodeString = userInput.next().toUpperCase();

				// user input to run find method
				avlTree.find(airportCodeString);

				// print results
				System.out.println("Search time is "
						+ avlTree.getSearchTimeInt() + " milliseconds");

				break;

			case '2':

				// instructions
				System.out
						.println("Enter the airport code, city, check in time");

				// store user input
				// run method, get info by using .next() which allows user to
				// enter on one line

				String airportEntryCodeString = userInput.next().toUpperCase();
				String airportCityString = userInput.next();
				String airportCheckInString = userInput.next();

				// display info
				System.out.println("Insertion time is "
						+ avlTree.getInsertTimeInt() + " milliseconds");

				// run addNode method
				avlTree.addNode(airportEntryCodeString, airportCityString,
						airportCheckInString);

				break;

			case '3':

				// instructions
				System.out.println("Enter the to be deleted airport code ");

				// user input to run remove method
				String airportCodeDeleteString = userInput.next().toUpperCase();

				avlTree.remove(airportCodeDeleteString);

				// display info
				System.out.println("Removal time is "
						+ avlTree.getRemoveTimeInt() + " milliseconds");

				break;

			case '4':

				// run print method
				avlTree.inOrderToPrint(avlTree.rootNode);

				break;

			case '5':

				// change boolean which causes loop to exit & program to close

				exitBool = true;
				break;
			}
		}

	}
}
