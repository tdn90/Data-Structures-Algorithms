package algorithms.SortingAlgs;
import dataStruct.LinkedList;
import dataStruct.Queue;

/**
 * @author nguye
 * This is my version of Radix Sort, effectively using Queue as "baskets"
 * This is a very effcient sorting algorithms, though only work on integer arrays.
 * It uses the principle of Counting Sort to appropriately put each elements in the right order.
 * Time-complexity: O(n*k) where n is arr.length, k is the number of digits of the max element.
 */
public class Radix {
    public static void sort(int[] arr) {
        int max = findMax(arr);
        int maxDigit = numDigits(max);
        for (int i = 0; i < maxDigit; i++) countingSort(arr, i);
    }

    /**
     * Do Counting Sort on the digit specified
     * @param arr: given array to be sorted according to specified digit
     * @param numDigit: the position of digit from right to left, starting from 0.
     */
    private static void countingSort(int[] arr, int numDigit) {
        Queue[] baskets = new Queue[10];
        // Initiates all baskets
        for (int m = 0; m < baskets.length; m++) {
            baskets[m] = new LinkedList<>();
        }

        // Place each number in their according "basket" based on their current numDigit
        for (int anArr : arr) {
            int curDigit = getNumDigit(anArr, numDigit);
            baskets[curDigit].enqueue(anArr);
        }

        // Copy those numbers from baskets back to original array
        for (int k = 0, idx = 0; k < 10 && idx < arr.length; k++) {
            while (!baskets[k].isEmpty()) {
                arr[idx++] = (int) baskets[k].dequeue();
            }
        }
    }

    /**
     * @param num: given number
     * @param numDigit: specified num Digit to retrieve. NumDigit goes from right to left, starts from 0
     * @return the digit number at the specified digit location
     */
    private static int getNumDigit(int num, int numDigit) {
        return (int) (num / (Math.pow(10,numDigit))) % 10;
    }

    /**
     * @param arr: given array
     * Pre-condition: arr.length > 0
     * @return the maximum integer in the array
     */
    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    /**
     * Attempts to find the number of digit of the given number
     * @param num: given number
     * @return: the number of digits of specified number
     */
    private static int numDigits(int num) {
        if (num == 0) return 1;
        int numDigit = 0;
        while (num > 0) {
            num /= 10;
            numDigit++;
        }
        return numDigit;
    }
}
