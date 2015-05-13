/*  Java Class: BinarySearchTreeMap
    Author: Bryant Tunbutr
    Class: Data Structures
    Date: 12/3/13
    Description:

    I certify that the code below is my own work.

	Exception(s): N/A

 */

import java.util.*;

public class BinarySearchTreeMap {

	// use java collections for binary search tree map
	TreeMap<Integer, String> treeMap = new TreeMap<Integer, String>();

	// store key value
	private int keyInt;

	// store string info
	private String numberString;

	// constructor
	public BinarySearchTreeMap(int keyInt, String numberString) {

		this.keyInt = keyInt;
		this.numberString = numberString;
	}

	// getter
	public int getKey() {
		return keyInt;
	}

	// getter
	public String getNumber() {
		return numberString;
	}

	// to enter information, uses put function that is built in
	public void newEntry(int keyInt, String numberString) {
		treeMap.put(keyInt, numberString);
	}

	// print smallest key using built in firstKey function
	public void getSmallestNumber() {
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

	// use integer value of key to use remove function
	public void eraseAll(int toDeleteInt) {

		System.out.println("Deleting " + toDeleteInt);
		
		treeMap.remove(toDeleteInt);

		System.out.println();
	}

}