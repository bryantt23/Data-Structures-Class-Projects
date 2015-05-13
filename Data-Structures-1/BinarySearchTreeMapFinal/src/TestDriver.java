/*  Java Class: TestDriver
    Author: Bryant Tunbutr
    Class: Data Structures
    Date: 12/3/13
    Description:

    I certify that the code below is my own work.

	Exception(s): N/A

 */

public class TestDriver {

	public static void main(String[] args) {

		System.out.println("Author:  Bryant Tunbutr");
		System.out.println();

		// constructor
		BinarySearchTreeMap tm = new BinarySearchTreeMap(0, null);

		tm.newEntry(21, "twenty one blackjack");
		tm.newEntry(1, "won one");
		tm.newEntry(31, "thirty one years old");
		tm.newEntry(100, "100 points in a game");
		tm.newEntry(65, "retirement age");

		tm.printAll();

		tm.eraseAll(65);
		tm.printAll();

		tm.getSmallestNumber();
	}
}
