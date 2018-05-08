package algorithms.RecursionPractice;

/**
 * Here, I'd solve these simple problems using both
 * recursion and iteration.
 * @author Nguyen
 * Let's see how recursion can make the solution more elegant
 * with the classic recursive problem Tower Of HaNoi
 */
public class RecursiveAlg {
    /**
     * Here is one basic, simple problem that can be solved using recursion
     * Condition: n >= 0
     * Factorial using recursion
     * @param n
     * @return n factorial
     */
    public static int factorial(int n) {
        if (n == 0) return 1;
        else {
            return n * factorial(n - 1);
        }
    }

    /**
     * Factorial using iteration
     * @param n
     * @return n factorial
     */
    public static int factorial2(int n) {
        int result = 1;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Recursion method to determine whether the string is a Palindrome
     * A Palindrome is a string that reads the same backwards and forwards
     * @param s: the string given.
     * @return whether the string given is a palindrome
     */
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.length() <= 1) return true;
        if (s.charAt(0) != s.charAt(s.length() - 1)) return false;
        else {
            return isPalindrome(s.substring(1, s.length() - 1));
        }
    }

    /**
     * Iterative method to determine whether
     * a string is a Palindrome
     * @param s: the string given
     * @return whether the string is a palindrome
     */
    public static boolean isPalindrome2(String s) {
        if (s == null) return false;
        if (s.length() <= 0) return true;
        else {
            int start = 0;
            int end = s.length() - 1;
            while (start <= end) {
                if (s.charAt(start) != s.charAt(end)) return false;
                start ++;
                end --;
            }
            return true;
        }
    }

    /**
     * Recursive algorithm for computing power
     * Using (quite) simple divide-and-conquer method
     * @param base
     * @param power
     * @return the base rises to a power
     */
    public static double power(int base, int power) {
        if (power == 0) return 1;
        else if (power == 1) return base;
        else if (power < 0) {
            return 1.0 / power(base, - power);
        }
        else if (power % 2 == 0) {
            return power(base, power / 2) * power(base, power / 2);
        }
        else {
            return power(base, power - 1) * power(base, 1);
        }
    }

    /**
     * Iterative method for computing power
     * @param base
     * @param power
     * @return the base rises to the given power
     */
    public static double power2(int base, int power) {
        int result = 1;
        for (int i = 0; i < power; i++) {
            result *= base;
        }
        return result;
    }

    /**
     * Use recursion to reverse the phrase inside parenthesis
     * Condition: input with valid parenthesis (open and close)
     * @param s
     * @return a String with the phrase inside parenthesis reverse
     */
    public static String reverseParen(String s) {
        int firstParen = s.indexOf("(");
        if (firstParen < 0) return s;
        else {
            int lastParen = s.lastIndexOf(")");
            return s.substring(0, firstParen)
                    + paren(s.substring(firstParen, lastParen + 1))
                    + s.substring(lastParen + 1);
        }
    }

    /**
     * Helper method for reverseParen
     * Condition: given a string in the format (..(..(..)..)..), reverse anything
     * inside the parenthesis, and nested parenthesis
     * @param s
     * @return a string with phrases inside parenthesis are reversed
     */
    public static String paren(String s) {
        int secondParen = s.indexOf("(", 1);

        if (secondParen < 0) {
            String deal = s.substring(1, s.length() - 1);
            return reverse(deal);
        }
        else {
            int secondCloseParen = s.lastIndexOf(")", s.length() - 2);
            return reverse(s.substring(1, secondParen)
                    + paren(s.substring(secondParen, secondCloseParen + 1))
                    + s.substring(secondCloseParen + 1, s.length() - 1));
        }
    }

    /**
     * Helper method for reverseParen
     * Given a string, reverse it.
     * @param s
     * @return the reverse version of the String
     */
    public static String reverse(String s) {
        String result = "";
        for (int i = s.length() - 1; i >= 0; i--) {
            result += s.charAt(i);
        }
        return result;
    }

    /**
     * Helper method to print out the moves to solve
     * Tower Of Ha Noi
     * @param from
     * @param to
     */
    public static void move(int from, int to) {
        System.out.println("Move from " + from + " to " + to);
    }

    /**
     * Print out all the moves to solve the Tower Of HaNoi
     * Tower Of Hanoi is a classic recursive problem:
     * You are given n disks
     * There are 3 rods
     * Your object is to move all the disks from original rod over to destination rod
     * Rules:
     * You cannot place a larger disk onto a small disk
     * You can only move one disk at a time
     * @param num: number of disks to be moved
     * @param from: rod where the disk is present
     * @param to: rod where the disk will be moved to
     * @condition: from != to, otherwise you don't even have to move
     * @param spare: the remaining rod
     */
    public static void TowerOfHanoi(int num, int from, int to, int spare) {

        /* Base case: this is when you have the top disk -- meaning
         * it is technically standing alone, then move that disk
         * to the destination (above the solved portion).
         */
        if (num == 1) {
            move(from, to);
        }

        else {

            /* First step, imagine moving the top disks (except the bottom one)
             * to your spare rod
             * Recursion Applies here!
             */
            TowerOfHanoi(num - 1, from, spare, to);

			/* Then, once the top disks are moved to the spare rod,
			we have the bottom (largest) disk left at original rod.
			Now move that disk to the destination rod*/
            // Notice that this is basically the base case
            TowerOfHanoi(1, from, to, spare);

            /* Next steps will be to move the other unsolved disks
             * from the spare rod to the destination rod
             */
            // Again, recursion applies here
            TowerOfHanoi(num - 1, spare, to, from);
        }
    }

    /**
     * Main method for testing
     * @param args
     */
    public static void main(String[] args) {
        // Uncomment these lines to check other functions
		/*
		for (int i = 0; i < 10; i ++) {
			System.out.println(factorial(i));
		}

		System.out.println(isPalindrome2("aabbaa"));
		System.out.println(power2(2,10));

		String s = "(abc(cbbac)c)";
		System.out.println(paren(s));
		*/

        TowerOfHanoi(4, 1, 3, 2);
        /* If you want to test this, you can search any website
         * that offers this game on the Internet
         * Here is one link I found:
         * https://www.mathsisfun.com/games/towerofhanoi.html
         */
    }
}
