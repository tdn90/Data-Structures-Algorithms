package algorithms.SortingAlgs;

import dataStruct.LinkedList;
import dataStruct.Queue;

import java.util.Arrays;

public class Radix {
    public static void sort(int[] arr) {
        int max = findMax(arr);
        int maxDigit = numDigits(max);
        for (int i = 0; i < maxDigit; i++) countingSort(arr, i);
    }

    public static void countingSort(int[] arr, int numDigit) {
        Queue[] baskets = new Queue[10];
        // Initiates all baskets
        for (int m = 0; m < baskets.length; m++) {
            baskets[m] = new LinkedList<>();
        }

        // Place each number in their according "basket" based on their current numDigit
        for (int i = 0; i < arr.length; i++) {
            int curDigit = getNumDigit(arr[i], numDigit);
            baskets[curDigit].enqueue(arr[i]);
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
     * @param numDigit: specified num Digit to retrieve. NumDigit goes from left to right, starts from 0
     * @return the digit number at the specified digit location
     */
    public static int getNumDigit(int num, int numDigit) {
        return (int) (num / (Math.pow(10,numDigit))) % 10;
    }

    /**
     * @param arr: given array
     * Pre-condition: arr.length > 0
     * @return the maximum integer in the array
     */
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    public static int numDigits(int num) {
        if (num == 0) return 1;
        int numDigit = 0;
        while (num > 0) {
            num /= 10;
            numDigit++;
        }
        return numDigit;
    }

    public static boolean correct(int[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            if (arr[i] > arr[i+1]) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] arr = new int[32];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*10000);
        }
        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);
        sort(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println(arr.length);
    }
}
