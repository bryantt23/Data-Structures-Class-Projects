
/*  Java Class: 	TextProcessing
 Author: 		Bryant Tunbutr
 Class: 			Data Structures II
 Date:			5/14/14
 Description:	This sorts text

 I certify that the code below is my own work.

 Exception(s): N/A

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class TextProcessing {
	static String declarationIndepString;
	static int comparisonsInt;

	@SuppressWarnings("static-access")
	public static void main(String[] args) throws FileNotFoundException {

		TextProcessing textProc4 = new TextProcessing();
		Scanner declar4 = new Scanner(new File("usdeclar.txt"));

		String declar4String = "";

		while (declar4.hasNext()) {
			declar4String += '\n' + (declar4.nextLine());
		}

		System.out.println("Searches in the Declaration of Independence");

		System.out.println();
		String pattern4 = "Providence";
		System.out.println("BM search for pattern " + pattern4);
		textProc4.BMmatch(declar4String, pattern4);
		System.out.println(displayIndex(textProc4.BMmatch(declar4String,
				pattern4)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println("KMP search for pattern " + pattern4);
		textProc4.KMPmatch(declar4String, pattern4);
		System.out.println(displayIndex(textProc4.KMPmatch(declar4String,
				pattern4)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println();
		String pattern5 = "Unanimous";
		System.out.println("BM search for pattern " + pattern5);
		textProc4.BMmatch(declar4String, pattern5);
		System.out.println(displayIndex(textProc4.BMmatch(declar4String,
				pattern5)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println("KMP search for pattern " + pattern5);
		textProc4.KMPmatch(declar4String, pattern5);
		System.out.println(displayIndex(textProc4.KMPmatch(declar4String,
				pattern5)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println();
		String pattern6 = "zzzz";
		System.out.println("BM search for pattern " + pattern6);
		System.out.println(displayIndex(textProc4.BMmatch(declar4String,
				pattern6)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println("KMP search for pattern " + pattern6);
		textProc4.KMPmatch(declar4String, pattern6);
		System.out.println(displayIndex(textProc4.KMPmatch(declar4String,
				pattern6)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println();
		String pattern7 = "natural";
		System.out.println("BM search for pattern " + pattern7);
		System.out.println(displayIndex(textProc4.BMmatch(declar4String,
				pattern7)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		System.out.println("KMP search for pattern " + pattern7);
		textProc4.KMPmatch(declar4String, pattern7);
		System.out.println(displayIndex(textProc4.KMPmatch(declar4String,
				pattern7)));
		System.out.println("The number of comparisons was " + comparisonsInt);

		HuffmanCoding huff1 = new HuffmanCoding();

		// scan file & store as String
		String moneyTextFileString = new Scanner(new File("money.txt"))
				.useDelimiter("\\A").next();

		System.out.println();
		System.out.println("The scanned text");
		System.out.println(moneyTextFileString);
		System.out.println();

		// create hashmap<character, frequency> based on input string
		HashMap<Character, Integer> huffmanHashMap = new HashMap<Character, Integer>();

		// count character frequency
		for (int i = 0; i < moneyTextFileString.length(); i++) {
			char currentChar = moneyTextFileString.charAt(i);

			// increment for each instance of the char
			if (huffmanHashMap.containsKey(currentChar))
				huffmanHashMap.put(currentChar,
						huffmanHashMap.get(currentChar) + 1);
			else
				huffmanHashMap.put(currentChar, 1);
		}

		// instantiate priority queue
		// use java.util compare to sort in smallest order
		huff1.priorityQueue = new PriorityQueue<Node>(100,
				new FrequencyComparator());

		int nodesCountInt = 0;

		// iterate through the hashmap and
		// add nodes to priority queue
		for (Character characterChar : huffmanHashMap.keySet()) {
			huff1.priorityQueue.add(new Node(characterChar, huffmanHashMap
					.get(characterChar)));
			nodesCountInt++;
		}

		// identify the root of the tree, the largest
		Node rootNode = huff1.huffman(nodesCountInt);

		System.out.println("The frequency table");

		// build the table for the variable length codes
		huff1.frequencyTable(rootNode);

		System.out.println();
		String compressed = huff1.compress(moneyTextFileString);
		System.out.println("The compressed string");
		System.out.println(compressed);

		System.out.println();
		String decompressed = huff1.decompress(compressed);
		System.out.println("The decompressed string");
		System.out.println(decompressed);

		huff1.saveToFile(compressed);

	}

	/**
	 * Simplified version of the Boyer-Moore (BM) algorithm, which uses only the
	 * looking-glass and character-jump heuristics.
	 * 
	 * @return Index of the beginning of the leftmost substring of the text
	 *         matching the pattern, or -1 if there is no match.
	 */
	public static int BMmatch(String text, String pattern) {
		int[] last = buildLastFunction(pattern);
		int n = text.length();
		int m = pattern.length();
		int i = m - 1;
		if (i > n - 1)
			return -1; // no match if pattern is longer than text

		int j = m - 1;

		comparisonsInt = 0;

		do {
			comparisonsInt++;

			if (pattern.charAt(j) == text.charAt(i))
				if (j == 0)
					return i; // match
				else { // looking-glass heuristic: proceed right-to-left
					i--;
					j--;
				}
			else { // character jump heuristic
				i = i + m - Math.min(j, 1 + last[text.charAt(i)]);
				j = m - 1;
			}
		} while (i <= n - 1);
		return -1; // no match
	}

	public static int[] buildLastFunction(String pattern) {
		int[] last = new int[128]; // assume ASCII character set
		for (int i = 0; i < 128; i++) {
			last[i] = -1; // initialize array
		}
		for (int i = 0; i < pattern.length(); i++) {
			last[pattern.charAt(i)] = i; // implicit cast to integer ASCII code
		}
		return last;
	}

	public static int KMPmatch(String text, String pattern) {

		comparisonsInt = 0;

		int n = text.length();
		int m = pattern.length();
		int[] fail = computeFailFunction(pattern);
		int i = 0;
		int j = 0;
		while (i < n) {
			// comparisonsInt++;

			if (pattern.charAt(j) == text.charAt(i)) {
				comparisonsInt++;
				if (j == m - 1)
					return i - m + 1; // match
				// comparisonsInt++;
				i++;
				j++;
			} else if (j > 0)
				j = fail[j - 1];
			else {
				// comparisonsInt++;
				i++;
			}
		}
		return -1; // no match
	}

	public static int[] computeFailFunction(String pattern) {
		int[] fail = new int[pattern.length()];
		fail[0] = 0;
		int m = pattern.length();
		int j = 0;
		int i = 1;
		while (i < m) {
			// comparisonsInt++;
			if (pattern.charAt(j) == pattern.charAt(i)) { // j + 1 characters
															// match
				comparisonsInt++;
				fail[i] = j + 1;
				i++;
				j++;
			} // j follows a matching prefix
			else if (j > 0) {
				comparisonsInt++;
				j = fail[j - 1];
			} else { // no match
				fail[i] = 0;
				i++;
				// comparisonsInt++;
			}
		}
		return fail;
	}

	public static String displayIndex(int index) {
		String indexDisplay = "";
		if (index == -1) {
			indexDisplay = "The pattern was not found";
		} else {
			indexDisplay = "The pattern was found at index " + index;
		}
		return indexDisplay;

	}
}
