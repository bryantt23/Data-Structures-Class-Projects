/*  Java Class: AvlNode
    Author: Bryant Tunbutr
    Class: Data Structures
    Date: 12/3/13
    Description:

    I certify that the code below is my own work.

	Exception(s): N/A

 */

public class AvlNode {
	
	public AvlNode leftChildNode;
	public AvlNode rightChildNode;
	public AvlNode parentNode;

	public String keyString;
	public String cityString;
	public String checkInTimeString;

	public int balanceInt;

	public AvlNode(String key, String city, String checkInTime) {
		leftChildNode = rightChildNode = parentNode = null;
		
		balanceInt = 0;

		keyString = key;

		cityString = city;
		checkInTimeString = checkInTime;
	}

	public String toString() {
		return keyString + " " + cityString + " " + checkInTimeString + "\n";
	}

}