
/*  Java Class: 	HuffmanCoding
 Author: 		Bryant Tunbutr
 Class: 			Data Structures II
 Date:			5/14/14
 Description:	This sorts text

 I certify that the code below is my own work.

 Exception(s): N/A

 */

import java.util.*;
import java.io.*;

public class HuffmanCoding {
	public static PriorityQueue<Node> priorityQueue;
	public static HashMap<Character, String> charToCodeHashMap;
	public static HashMap<String, Character> codeToCharHashMap;

	// build the tree based on the frequency of the characters
	public static Node huffman(int n) {
		Node a, b;

		// remove two smallest nodes from PQ, add frequencies & add notes back
		// to PQ
		// repeat until only 1 remaining node which becomes root
		for (int i = 1; i <= n - 1; i++) {
			Node node = new Node();

			// get the two smallest nodes from priority queue
			node.left = a = priorityQueue.poll();
			node.right = b = priorityQueue.poll();

			// add frequencies and add that node back to priority queue
			node.freq = a.freq + b.freq;
			priorityQueue.add(node);
		}

		// last node remaining, the root of Huffman Tree
		return priorityQueue.poll();
	}

	// frequency table for the compression and decompression
	public static void frequencyTable(Node root) {
		charToCodeHashMap = new HashMap<Character, String>();
		codeToCharHashMap = new HashMap<String, Character>();

		postOrderTraversal(root, new String());
	}

	// recursive method
	// adding a zero if going left, one if going right
	// post order traversal from root to leaves
	public static void postOrderTraversal(Node node, String string) {
		if (node == null)
			return;

		postOrderTraversal(node.left, string + "0");
		postOrderTraversal(node.right, string + "1");

		// visit only nodes that have keys
		if (node.letterChar != '\u0000') {

			// put node letters into hashmap
			charToCodeHashMap.put(node.letterChar, string);
			codeToCharHashMap.put(string, node.letterChar);

			System.out.println(node.letterChar + "   " + string);
		}
	}

	// needs already defined dictionary and tree
	public static String compress(String inputString) {
		String compressedString = new String();

		for (int i = 0; i < inputString.length(); i++)
			compressedString = compressedString
					+ charToCodeHashMap.get(inputString.charAt(i));

		return compressedString;
	}

	// needs already defined dictionary and tree
	public static String decompress(String inputString) {
		String tempString = new String();
		String decompressedString = new String();

		for (int i = 0; i < inputString.length(); i++) {
			tempString = tempString + inputString.charAt(i);

			if (codeToCharHashMap.containsKey(tempString)) {
				decompressedString = decompressedString
						+ codeToCharHashMap.get(tempString);
				tempString = new String();
			}
		}
		return decompressedString;
	}

	public static void saveToFile(String compressedString)
			throws FileNotFoundException {
		PrintWriter oFile = new PrintWriter("moneyOutput.txt");

		for (String s : codeToCharHashMap.keySet()) {
			oFile.println(s + " " + codeToCharHashMap.get(s));
		}

		oFile.println("**");
		oFile.println(compressedString);
		oFile.close();
	}
}

class Node {

	public char letterChar;
	public int freq;
	public Node left, right;

	public Node(char l, int f) {
		letterChar = l;
		freq = f;
	}

	public Node() {

	}

	public String toString() {
		return letterChar + " " + freq;
	}
}

// use java.util compare to sort in smallest order
class FrequencyComparator implements Comparator<Node> {

	public int compare(Node a, Node b) {
		int freqA = a.freq;
		int freqB = b.freq;

		return freqA - freqB;
	}
}