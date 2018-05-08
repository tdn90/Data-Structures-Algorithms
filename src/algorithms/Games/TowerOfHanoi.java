package algorithms.Games;

import java.util.Iterator;
import java.util.Scanner;

import dataStruct.FixedSizeStack;

/**
 * Standard Tower of Hanoi disk problem (https://en.wikipedia.org/wiki/Tower_of_Hanoi).
 */
public class TowerOfHanoi {

    /** Three separate stacks, each to hold up to four disks. Leave this alone. */
    static FixedSizeStack[] stacks = new FixedSizeStack [] {
            new FixedSizeStack(4), new FixedSizeStack(4), new FixedSizeStack(4)
    };

    /**
     * Output the state in visible form. Modify this method.
     *
     * You need to modify this method so it outputs state as follows:
     *
     * Stack1: 4321
     * Stack2:
     * Stack3:
     *
     * If, given this above state, you make a move from stack1 to stack3, then the output would be:
     *
     * Stack1: 432
     * Stack2:
     * Stack3: 1
     */
    public static void outputState() {
        for(int i = 0; i < 3; i ++) {
            String content = "";

            Iterator<Integer> list = stacks[i].iterator();
            while (list.hasNext()) {
                content = list.next() + content;
            }
            System.out.println("Stack" + (i+1) + ": " + content);
        }
    }

    /**
     * You must write this method. A move is allowed if the disk from the top of
     * stack 'from' is smaller in size than the topmost disk on the top of stack 'to'.
     *
     * @param from   stack containing the topmost disk to move
     * @param to     stack representing the destination disk
     */
    public static boolean move(int from, int to) {
        if (validStack(from) && validStack(to)){
            from--;
            to--;
            // Case 1: attempt to move disc in same stack cannot be done
            if (from == to) return false;

            // Case 2: the "queried" stack is empty, cannot move
            if (stacks[from].isEmpty()) return false;

            Integer topFrom = (Integer) stacks[from].pop();
            // Case 3: the destination stack is empty,
            if (stacks[to].isEmpty()) {
                stacks[to].push(topFrom);
                return true;
            }

            // Case 4: both the "queried" and "destination" stack has at least one disk
            else {
                Integer topTo = (Integer) stacks[to].pop();
                // If move is invalid, return the two disks back to their place
                if (topTo < topFrom) {
                    stacks[from].push(topFrom);
                    stacks[to].push(topTo);
                    return false;
                }

                // Move is valid, proceed!
                // Note: topTo is definitely != topFrom
                else {
                    stacks[to].push(topTo);
                    stacks[to].push(topFrom);
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Helper method to check if the stack passed in is valid
     * @param numStack: # of stack
     * @return: true if the given stack is valid, false otherwise
     */
    private static boolean validStack(int numStack) {
        return numStack >= 1 && numStack <= 3;
    }

    /** You do not need to modify this method. */
    public static void main(String[] args) {
        /* Load up four disks on stack 1, so disk-4 is at the bottom and disk-1 is at the top. */
        stacks[0].push(4);
        stacks[0].push(3);
        stacks[0].push(2);
        stacks[0].push(1);

        int numMoves = 0;
        while (!stacks[0].isEmpty() || !stacks[1].isEmpty()) {
            outputState();
            Scanner input = new Scanner(System.in);
            System.out.println ("Enter two stack numbers A B to move top disk on A to B. You win when all disks are on Stack 3.");
            int from = input.nextInt();
            int to = input.nextInt();

            System.out.println("Moving top disk from stack " + from + " to stack " + to);
            if (!move(from, to)) {
                System.out.println ("That move is not allowed!");
            } else {
                numMoves++;
            }
        }

        System.out.println ("Congratulations! You completed the puzzle in " + numMoves + " moves.");
    }
}
