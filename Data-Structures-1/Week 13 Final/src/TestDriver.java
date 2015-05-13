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
		BinarySearchTreeMap tm = new BinarySearchTreeMap(null, null);

		tm.openFile();
		tm.readFile();

		tm.printAll();
		tm.getSmallestCity();

		tm.eraseAll("LAX");
		tm.printAll();

		tm.searchFor("MIA");

		tm.searchFor("LAX");
	}
}
