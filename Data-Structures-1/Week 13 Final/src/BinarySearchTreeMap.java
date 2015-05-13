/*  Java Class: BinarySearchTreeMap
    Author: Bryant Tunbutr
    Class: Data Structures
    Date: 12/3/13
    Description:

    I certify that the code below is my own work.

	Exception(s): N/A

 */

import java.io.File;
import java.util.*;

public class BinarySearchTreeMap {

	// use java collections for binary search tree map
	TreeMap<String, String> treeMap = new TreeMap<String, String>();

	// store key value
	private String keyString;

	// store string info
	private String cityString;

	// constructor
	public BinarySearchTreeMap(String keyString, String cityString) {

		this.keyString = keyString;
		this.cityString = cityString;
	}

	// getter
	public String getKey() {
		return keyString;
	}

	// getter
	public String getNumber() {
		return cityString;
	}

	// to enter information, uses put function that is built in
	public void newEntry(String keyString, String cityString) {
		treeMap.put(keyString, cityString);
	}

	// print smallest key using built in firstKey function
	public void getSmallestCity() {
		System.out.println("The smallest key is " + treeMap.firstKey());
	}

	// print everything
	public void printAll() {
		// get a set of the entries
		// entrySet let's you view mappings in the map
		Set treeMapSet = treeMap.entrySet();

		// use iterator to go through treeMapSet
		Iterator i = treeMapSet.iterator();

		System.out.println("Printing all items");
		// display elements
		// while has next item, aka until end of set
		while (i.hasNext()) {

			// print out each entry, iterate through them
			Map.Entry me = (Map.Entry) i.next();

			System.out.print(me.getKey() + ": ");
			System.out.println(me.getValue());
		}
		System.out.println();
	}

	// use string value of key to use remove function
	public void eraseAll(String toDeleteString) {

		System.out.println("Deleting " + toDeleteString);

		treeMap.remove(toDeleteString);

		System.out.println();
	}

	// use string value of key to use find function
	public void searchFor(String toFindString) {

		System.out.println("Searching for " + toFindString + "....");

		if (treeMap.containsKey(toFindString)) {
			System.out.println(toFindString + " is "
					+ treeMap.get(toFindString));
		} else {
			System.out.println(toFindString + " not found");
		}

		System.out.println();

	}


	// scans files
	private Scanner file;

	// opens files, checks to make sure it exists
	public void openFile() {
		try {
			file = new Scanner(new File("p4c.txt"));
		} catch (Exception e) {
			System.out.println("could not find file");
		}

	}

	public void readFile() {
		// while file has something in it
		while (file.hasNext()) {

			String inputAirportCodeString = file.next();
			String inputCityString = file.next();

			newEntry(inputAirportCodeString, inputCityString);
		}

	}

	public void closeFile() {
		file.close();
	}

}