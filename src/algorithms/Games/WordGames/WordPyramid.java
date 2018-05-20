package algorithms.Games.WordGames;

import dataStruct.ChainingHashMap;
import dataStruct.LinkedList;
import dataStruct.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Fun word pyramid game 
public class WordPyramid {
    // Map word to integer ID
    private static ChainingHashMap<String,Integer> table = new ChainingHashMap<>();

    // Map integer ID to word
    private static ChainingHashMap<Integer,String> reverse = new ChainingHashMap<>();

    public static void main(String[] args) throws FileNotFoundException {
        playWordPyrapid(7);
    }

    /**
     * Play the game here!!!
     * @param g: passed in graph
     */
    private static void readWord(Graph g){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the word to start (lower case): ");
        String in = input.next();

        if (table.get(in) == null) {
            System.out.println("No word pyramid available.");
            return;
        }

        if (in.length() == 1) System.out.println(in);

        int src = table.get(in);
        boolean[] marked = new boolean[g.V()];
        int[] edgeTo = new int[g.V()];
        Queue<Integer> toVisit = new LinkedList<>();
        int dest = -1;

        toVisit.enqueue(src);
        marked[src] = true;

        boolean found = false;
        while (!toVisit.isEmpty()) {
            int current = toVisit.dequeue();
            for (int neighbor : g.adj(current)) {
                if (!marked[neighbor]) {
                    String nb = reverse.get(neighbor);
                    // only mark and add edge if the neighbor string length is shorter than current string length.
                    if (nb.length() < reverse.get(current).length()) {
                        marked[neighbor] = true;
                        edgeTo[neighbor] = current;
                        if (reverse.get(neighbor).length() == 1) {
                            dest = neighbor;
                            found = true;
                            break;
                        }
                        toVisit.enqueue(neighbor);
                    }
                }
            }
        }

        if (found) {
            StringBuilder result2 = new StringBuilder();
            for (int cur = dest; cur != src; cur = edgeTo[cur]) {
                result2.insert(0, reverse.get(cur) + "\n");
            }
            result2.insert(0, reverse.get(src) + "\n");
            System.out.println(result2);
        }
        else System.out.println("No word pyramid available");
    }

    private static void playWordPyrapid(int maxInLength) throws FileNotFoundException {
        // Create an array of Queue, where each Queue present a list of elements of the same size
        LinkedList[] sizeArr = new LinkedList[maxInLength];
        for (int n = 0; n < sizeArr.length; n++) sizeArr[n] = new LinkedList();
        int count = 0;

        Scanner sc = new Scanner(new File ("words.english.txt"));
        // Read input file from dictionary
        // O(n) where n is number of words in dictionary
        while (sc.hasNext()) {
            String s = sc.next();
            if (s.length() <= maxInLength && s.contains("a")) {
                table.put(s, count);
                reverse.put(count, s);
                sizeArr[s.length()-1].enqueue(s);
                count++;
            }
        }

        // Create graph
        Graph g = new Graph(count);

        // Attempt to add all edges
        // Expensive operation here
        // Can only say quadratic performance (though looks cubic or even n^4)
        for (int i = 0; i < sizeArr.length-1; i++) {
            LinkedList cur1 = sizeArr[i];
            LinkedList cur2 = sizeArr[i+1];
            for (Object ob1 : cur1) {
                for (Object ob2 : cur2) {
                    String str1 = (String) ob1;
                    String str2 = (String) ob2;
                    if (checkAdjacent(str1, str2)) g.addEdge(table.get(str1),table.get(str2));
                }
            }
        }

        readWord(g);
        printGap();

        // Loop to replay. Avoid re-computing the graph several times which is expensive.
        Scanner userContinue = new Scanner(System.in);
        System.out.println("Play again? (y/n)");
        String in = userContinue.next();
        in = in.toLowerCase();
        while (!in.equals("n")) {
            readWord(g);
            printGap();
            userContinue = new Scanner(System.in);
            System.out.println("Play again? (y/n)");
            in = userContinue.next();
            in = in.toLowerCase();
        }
        userContinue.close();
        System.out.println("Thanks for playing!");
    }

    // Just here to make the output look better
    private static void printGap() {
        System.out.println();
        System.out.println("---------------------------------");
        System.out.println();
    }

    /**
     * Determine if the two words are off by one character
     * This method is O(n+1) where n is the string length of smaller element
     * O(n) space complexity
     * @param str1: first string (shorter string --- length n)
     * @param str2: second string (larger string --- length n+1)
     * @return: true if the two words are off by one character
     */
    private static boolean checkAdjacent(String str1, String str2) {
        Map<Character, Integer> trackMap = new HashMap<>();
        for (int c2 = 0; c2 < str2.length(); c2++) {
            char cur2 = str2.charAt(c2);
            trackMap.merge(cur2, 1, (a, b) -> a + b);
        }
        for (int c1 = 0; c1 < str1.length(); c1++) {
            char cur1 = str1.charAt(c1);
            if (trackMap.get(cur1) == null) return false;
            else {
                if (trackMap.get(cur1) == 1) trackMap.remove(cur1);
                else trackMap.put(cur1, trackMap.get(cur1)-1);
            }
        }
        return true;
    }
}
