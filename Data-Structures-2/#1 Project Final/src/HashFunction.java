import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class HashFunction
{
	String[] theArray;
	static int arraySize;
	int itemsInArray = 0;

	double loadFactor;
	int totalCollisions = 0;

	int itemsWithCollisions = 0;
	int collisionsForOneItem;
	int mostCollisionsForOneItem = 0;

	int numberOfClusters = 0;
	int cluster = 0;
	int largestCluster = 0;

	public static void main(String[] args) throws FileNotFoundException
	{
		// get file info, loop through & store as String array
		Scanner scanner = new Scanner(new File("p1largeBryKey10000.txt"));
		String[] arrString = new String[10000];

		int i = 0;
		while (scanner.hasNextInt())
		{
			arrString[i++] = Integer.toString(scanner.nextInt());
		}

		// Linear Probing

		// load factors of 0.25, 0.5, 0.75, and 0.9
		// 4, 2, 1.25, and 10/9
		System.out.println("This is linear probing with load factor .25");
		HashFunction file1 = new HashFunction(10000);
		file1.arraySizeToPrime(arrString.length, .25);
		file1.hashFunction(arrString, file1.theArray);
		file1.displayTheStack();
		System.out.println();

		System.out.println("This is linear probing with load factor .5");
		HashFunction file2 = new HashFunction(10000);
		file2.arraySizeToPrime(arrString.length, 0.5);
		file2.hashFunction(arrString, file2.theArray);
		file2.displayTheStack();
		System.out.println();

		System.out.println("This is linear probing with load factor .75");
		HashFunction file3 = new HashFunction(10000);
		file3.arraySizeToPrime(arrString.length, .75);
		file3.hashFunction(arrString, file3.theArray);
		file3.displayTheStack();
		System.out.println();

		System.out.println("This is linear probing with load factor .9");
		HashFunction file4 = new HashFunction(10000);
		file4.arraySizeToPrime(arrString.length, .9);
		file4.hashFunction(arrString, file4.theArray);
		file4.displayTheStack();
		System.out.println();

		System.out.println("Search for 277");
		file4.findKey("277");
		System.out.println("Search for 52");
		file4.findKey("52");
		System.out.println("Search for 44550");
		file4.findKey("44550");
		System.out.println("Search for 450");
		file4.findKey("450");
		System.out.println("Search for 91736");
		file4.findKey("91736");
		System.out.println("Remove 91736");
		file4.removeKey("91736");
		System.out.println("Search for 91736");
		file4.findKey("91736");
		System.out.println();

		// DOUBLE HASHING
		System.out.println("This is double hashing with load factor .25");
		HashFunction file5 = new HashFunction(10000);
		file5.arraySizeToPrime(arrString.length, .25);
		file5.doubleHashFunc(arrString, file5.theArray);
		file5.displayTheStack();
		System.out.println();

		System.out.println("Search for 277");
		file5.findKey("277");
		System.out.println("Search for 52");
		file5.findKey("52");
		System.out.println("Search for 44550");
		file5.findKey("44550");
		System.out.println("Search for 450");
		file5.findKey("450");
		System.out.println("Search for 91736");
		file5.findKey("91736");
		System.out.println("Remove 91736");
		file5.removeKey("91736");
		System.out.println("Search for 91736");
		file5.findKey("450");
		System.out.println();

		System.out.println("This is double hashing with load factor .5");
		HashFunction file6 = new HashFunction(10000);
		file6.arraySizeToPrime(arrString.length, .5);
		file6.doubleHashFunc(arrString, file6.theArray);
		file6.displayTheStack();
		System.out.println();

		System.out.println("This is double hashing with load factor .75");
		HashFunction file7 = new HashFunction(10000);
		file7.arraySizeToPrime(arrString.length, .75);
		file7.doubleHashFunc(arrString, file7.theArray);
		file7.displayTheStack();
		System.out.println();

		System.out.println("This is double hashing with load factor .9");
		HashFunction file8 = new HashFunction(10000);
		file8.arraySizeToPrime(arrString.length, .9);
		file8.doubleHashFunc(arrString, file8.theArray);
		file8.displayTheStack();
		System.out.println();


		// CHAIN HASHING

		System.out.println("This is Chain Hashing with load factor .25");
		HashFunction file9 = new HashFunction(10000);
		file9.arraySizeToPrime(arrString.length, .25);
		file9.chainHashFunc(arrString, file9.theArray);
		System.out.println();

		System.out.println("This is Chain Hashing with load factor .5");
		HashFunction file10 = new HashFunction(10000);
		file10.arraySizeToPrime(arrString.length, .5);
		file10.chainHashFunc(arrString, file10.theArray);
		System.out.println();

		System.out.println("This is Chain Hashing with load factor .75");
		HashFunction file11 = new HashFunction(10000);
		file11.arraySizeToPrime(arrString.length, .75);
		file11.chainHashFunc(arrString, file11.theArray);
		System.out.println();

		System.out.println("This is Chain Hashing with load factor .9");
		HashFunction file12 = new HashFunction(10000);
		file12.arraySizeToPrime(arrString.length, .9);
		file12.chainHashFunc(arrString, file12.theArray);
		System.out.println();

	}

	// this reverse the String for input & retrieval
	public static String reverseString(String string)
	{
		String input = String.valueOf(string);
		String result = "";
		for (int i = input.length() - 1; i >= 0; i--)
		{
			result = result + input.charAt(i);
		}
		return result;
	}

	// check if array size is prime
	public boolean isPrime(int number)
	{
		// primes cannot be even
		if (number % 2 == 0)
			return false;

		// odd numbers check
		for (int i = 3; i * i <= number; i += 2)
		{
			if (number % i == 0)
				return false;
		}
		// is prime because made past even & odd check
		return true;
	}

	// for array size

	// get number of array size, check if it is prime
	// if is not, get next prime
	public int getNextPrime(int numToCheck)
	{
		for (int i = numToCheck; true; i++)
		{
			// increment until prime number is returned
			if (isPrime(i))
				return i;
		}
	}

	// increase array size to prime number
	public void arraySizeToPrime(int minArraySize, double loadFactor)
	{
		// load factors of 0.25, 0.5, 0.75, and 0.9
		// 4, 2, 1.25, and 10/9
		int newArraySize = 0;

		// take load factor then invert, then get next prime that follows
		if (loadFactor == .25)
		{
			newArraySize = getNextPrime((int) (minArraySize * (1 / loadFactor)));
		} else if (loadFactor == 0.5)
		{
			newArraySize = getNextPrime((int) (minArraySize * (1 / loadFactor)));
		} else if (loadFactor == 0.75)
		{
			newArraySize = getNextPrime((int) (minArraySize * (1 / loadFactor)));
		} else if (loadFactor == .9)
		{
			newArraySize = getNextPrime((int) (minArraySize * (1 / loadFactor)));
		}

		updateArray(newArraySize);
	}

	// same array but fill in empty spaces with -1
	public void updateArray(int newArraySize)
	{
		String[] newArray = removeArrSpaces(theArray);

		// update array size based off previous load factor & prime methods
		theArray = new String[newArraySize];

		arraySize = newArraySize;

		// minus 1 in array
		minusOneToArray();

		// update array with values
		hashFunction(newArray, theArray);
	}

	// remove array empty spaces
	public String[] removeArrSpaces(String[] arrayToClean)
	{
		ArrayList<String> stringList = new ArrayList<String>();

		// enhanced for loop, if it's not empty add it to String ArrayList
		for (String theString : arrayToClean)
			if (!theString.equals("-1"))
				stringList.add(theString);

		return stringList.toArray(new String[stringList.size()]);
	}

	// double hash functions here
	public void doubleHashFunc(String[] stringsForArray, String[] theArray)
	{
		// loop through size of array
		for (int n = 0; n < stringsForArray.length; n++)
		{
			// element value
			String elementValue = stringsForArray[n];

			// index is element value % array size
			int arrayIndex = Integer.parseInt(elementValue) % arraySize;

			// h '(k) = q- (kmod q),

			// after collision use this step size with prime number 7
			int stepSize = 7 - (Integer.parseInt(elementValue) % 7);

			// if it is not empty there is a collision
			if (theArray[arrayIndex] != "-1")
			{
				itemsWithCollisions++;
			}

			// while loop to find empty space
			while (theArray[arrayIndex] != "-1")
			{
				// find index using step size
				arrayIndex += stepSize;

				// to return to beginning of array if reach end
				arrayIndex %= arraySize;

				// increment total collisions & collisions for this particular
				// item
				totalCollisions++;
				collisionsForOneItem++;
			}

			// store as reverseString
			theArray[arrayIndex] = reverseString(elementValue);

			// track the most collisions for item
			if (collisionsForOneItem > mostCollisionsForOneItem)
			{
				mostCollisionsForOneItem = collisionsForOneItem;
			}

			// reset collisions for the next item
			collisionsForOneItem = 0;
		}
		// find largest cluster
		largestCluster(theArray);
	}

	public String findDblHashKey(String key)
	{
		// key is same as creating step
		int arrayIndexHash = Integer.parseInt(key) % arraySize;

		// Find the keys original step distance
		int stepDistance = 7 - (Integer.parseInt(key) % 7);

		// while not empty
		while (theArray[arrayIndexHash] != "-1")
		{
			// found so return
			if (theArray[arrayIndexHash].equals(reverseString(key)))
			{
				System.out.println(key + " was found in index "
						+ arrayIndexHash);

				return theArray[arrayIndexHash];
			}
			// increment by stepDistance
			arrayIndexHash += stepDistance;

			arrayIndexHash %= arraySize;
		}
		System.out.println("not found");
		return null;
	}

	public void largestCluster(String[] theArray)
	{
		// did first one, start on second - which is index 1
		for (int i = 0; i < theArray.length; i++)
		{
			if (i + 1 < theArray.length)
			{
				if (theArray[i] == "-1" && theArray[i + 1] != "-1")
				{
					numberOfClusters++;
				}
			}
			// determine if still in cluster
			if (theArray[i] != "-1")
			{
				// increment
				cluster++;

				// if new record
				if (cluster > largestCluster)
				{
					largestCluster = cluster;
				}
			} 
			else
			{ 
				cluster = 0;
//				reset cluster
			} 
		} 
	}

//	count clusters
	public void numberOfClusters(String[] theArray)
	{
//	if it is empty & followed by value, it is a cluster	
		for (int i = 0; i < theArray.length; i++)
		{
			if (i + 1 < theArray.length)
			{
				if (theArray[i] == "-1" && theArray[i + 1] != "-1")
				{
//					increment for each cluster
					numberOfClusters++;
				}
			}
		}
	}

//	linear probing functions
	public void hashFunction(String[] stringsForArray, String[] theArray)
	{
		for (int n = 0; n < stringsForArray.length; n++)
		{
			String elementValue = stringsForArray[n];

			// index is value % array size
			int arrayIndex = Integer.parseInt(elementValue) % arraySize;

//			increment collisions
			if (theArray[arrayIndex] != "-1")
			{
				itemsWithCollisions++;
			}

//			increment when space is occupied
			while (theArray[arrayIndex] != "-1")
			{
				++arrayIndex;

				totalCollisions++;
				collisionsForOneItem++;

				arrayIndex %= arraySize;
			}
			theArray[arrayIndex] = reverseString(elementValue);

			if (collisionsForOneItem > mostCollisionsForOneItem)
			{
				mostCollisionsForOneItem = collisionsForOneItem;
			}
			collisionsForOneItem = 0;
		}
		largestCluster(theArray);
	}


//	remove value
	public void removeKey(String key)
	{
		boolean found = false;

//		find using original way of storing
		int arrayIndexHash = Integer.parseInt(key) % arraySize;

		while (theArray[arrayIndexHash] != "-1")
		{
			if (theArray[arrayIndexHash].equals(reverseString(key)))
			{
//				return because equal
				System.out.println(key + " was found in index "
						+ arrayIndexHash + " will be removed");

				theArray[arrayIndexHash] = "-1";

				found = true;
			}
//			increment because while loop
			++arrayIndexHash;

			arrayIndexHash %= arraySize;
		}
		if (!found)
		{
			System.out.println("not found");
		}
	}

	// Returns the value stored in the Hash Table

	public String findKey(String key)
	{
		// same as what we did to store info! % arraySize
		int arrayIndexHash = Integer.parseInt(key) % arraySize;

		while (theArray[arrayIndexHash] != "-1")
		{
			if (theArray[arrayIndexHash].equals(reverseString(key)))
			{
				System.out.println(key + " was found in index "
						+ arrayIndexHash);

				return theArray[arrayIndexHash];
			}

			// next index
			++arrayIndexHash;

			arrayIndexHash %= arraySize;
		}
		System.out.println("not found");
		// key not found
		return null;
	}

	HashFunction(int size)
	{
		arraySize = size;

		theArray = new String[size];

		// -1 for each empty space in array
		minusOneToArray();
	}

	public void minusOneToArray()
	{
		Arrays.fill(theArray, "-1");
	}

	public void displayTheStack()
	{
		/*
		 * // to print out info
		 * 
		 * int increment = 0; int numberOfRows = (arraySize / 10) + 1;
		 * 
		 * for (int m = 0; m < numberOfRows; m++) { increment += 10;
		 * 
		 * for (int n = 0; n < 71; n++) System.out.print("-");
		 * 
		 * System.out.println();
		 * 
		 * for (int n = increment - 10; n < increment; n++) {
		 * System.out.format("| %3s " + " ", n); }
		 * 
		 * System.out.println("|");
		 * 
		 * for (int n = 0; n < 71; n++) System.out.print("-");
		 * 
		 * System.out.println();
		 * 
		 * for (int n = increment - 10; n < increment; n++) {
		 * 
		 * if (n >= arraySize) System.out.print("|      ");
		 * 
		 * else if (theArray[n].equals("-1")) System.out.print("|      ");
		 * 
		 * else System.out .print(String.format("| %3s " + " ", theArray[n])); }
		 * 
		 * System.out.println("|");
		 * 
		 * for (int n = 0; n < 71; n++) System.out.print("-");
		 * 
		 * System.out.println(); }
		 */

		System.out.println("Table Size is " + arraySize);

		System.out.println("Average number of probes is "
				+ (float) totalCollisions / itemsWithCollisions);

		System.out.println("Maximum number of probes for worst case is "
				+ mostCollisionsForOneItem);

		System.out.println("Number of clusters is " + numberOfClusters);
		System.out.println("Size of largest cluster is " + largestCluster);
	}

	public void chainHashFunc(String[] stringsForArray, String[] theArray)
	{
		ChainHashMap<String, String> m = new ChainHashMap<String, String>(
				arraySize);
		
		
		// Fill the buckets with empty Linked lists
		for (int i = 0; i < stringsForArray.length; i++)
		{
			String newElementVal = stringsForArray[i];

			// Create an index to store the value in by taking
			// the modulus
			int arrayIndex = Integer.parseInt(newElementVal) % arraySize;
			theArray[arrayIndex] = reverseString(newElementVal);

			String key = Integer.toString(arrayIndex);
			String value = theArray[arrayIndex];
			m.put(key, value);					
		}	
		
		int clusterTotal = m.clusters;
		int maxProbe = m.maxProbes;
		
		System.out.println("Table Size is " + arraySize);
		System.out.println("The number of clusters is " + clusterTotal);
		System.out.println("The largest cluster is " + maxProbe);
	}
}
