package tnguyen4.hw5;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Modify this class for problem 1 on HW5.
 */
public class WordLadder {

	/**
	 * Represent the mapping of (uniqueID, 4-letter word)
	 */
	// Map word to integer ID
	private static SeparateChainingHashST<String,Integer> table = new SeparateChainingHashST<>();

	// Map integer ID to word
	private static SeparateChainingHashST<Integer,String> reverse = new SeparateChainingHashST<>();

	/**
	 * Determine if the two same-sized words are off by just a single character.
	 * Pre-condition: both strings have the same size (four in this assignment)
	 */
	private static boolean offByOne(String w1, String w2) {
		int diff = 0;
		for (int i = 0; i < w1.length(); i++) {
			if (w1.charAt(i) != w2.charAt(i)) diff++;
		}
		return diff == 1;
	}

	/**
	 * Main method to execute.
	 * 
	 * From console, enter the start and end of the word ladder.
	 */
	public static void main(String[] args) throws FileNotFoundException {
		// Use this to contain all four-letter words that you find in dictionary
		AVL<String> avl = new AVL<>();
		int count = 0;

		// Create a graph where each node represents a four-letter word.
		// Also construct avl tree of all four-letter words.
		// Note: you will have to copy this file into your project to access it, unless you
		// are already writing your code within the SedgewickAlgorithms4ed project.
		Scanner sc = new Scanner(new File ("words.english.txt"));
		while (sc.hasNext()) {
			String s = sc.next();
			if (s.length() == 4) {
				avl.insert(s);
				table.put(s, count);
				reverse.put(count++, s);
			}
		}
		sc.close();

		// now construct graph, where each node represents a word, and an edge exists between 
		// two nodes if their respective words are off by a single letter.
		Graph g = new Graph(count);
		for (String word1 : avl.keys()) {
			for (String word2 : avl.keys()) {
				if (offByOne(word1, word2)) {
					g.addEdge(table.get(word1),table.get(word2));
				}
			}
		}

		// Ask for user input
		StdOut.println("Enter word to start from (all in lower case):");
		String start = StdIn.readString().toLowerCase();
		// Validate that this is an actual four-letter words in the dictionary
		if (!avl.contains(start)) {
			StdOut.println (start + " is not a valid word in the dictionary.");
			System.exit(-1);
		}

		StdOut.println("Enter word to end at (all in lower case):");
		String end = StdIn.readString().toLowerCase();
		// Validate that this is an actual four-letter words in the dictionary
		if (!avl.contains(end)) {
			StdOut.println (end + " is not a valid word in the dictionary.");
			System.exit(-1);
		}

		int src = table.get(start);
		int dest = table.get(end);

		// Once both words are known to exist in the dictionary, then create a search
		// that finds shortest distance (should it exist) between start and end.
		// be sure to output the words in the word ladder, IN ORDER, from the start to end.

		// Do Breadth-First Search here to determine the shortest path
		boolean[] marked = new boolean[count];
		int[] edgeTo = new int[count];
		Queue<Integer> toVisit = new Queue<>();
		toVisit.enqueue(src);
		marked[src] = true;

		boolean found = false;
		while (!toVisit.isEmpty()) {
			int current = toVisit.dequeue();
			if (current == dest) {  // reached destination. Terminate
				found = true;
				break;
			}
			for (Integer neighbor : g.adj(current)) {
				if (!marked[neighbor]) {
					marked[neighbor] = true;
					edgeTo[neighbor] = current;
					toVisit.enqueue(neighbor);
				}
			}
		}

		if (found) {
			StringBuilder result2 = new StringBuilder();
			for (int cur = dest; cur != src; cur = edgeTo[cur]) {
				result2.insert(0, reverse.get(cur) + " ");
			}
			result2.insert(0, reverse.get(src) + " ");
			System.out.println(result2);
		}
		else System.out.println("No word ladder!");
	}
}
