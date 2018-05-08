package algorithms.RecursionPractice;

import java.util.Arrays;
/**
 * Another common example of divide-and-conquer
 * @author nguye
 * Efficient Algorithm for finding an item in a given array
 * BinarySearch algorithm's time complexity: O(log(n))
 * Much faster compared to linear search's time complexity: O(n)
 * Memory Efficiency: O(1)
 */
public class BinarySearch {
    /**
     * Main method for testing
     * @param args
     */
    public static void main(String[] args) {
        int[] arr = new int[1000];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = 5*i;
        }

        boolean result = true;
        for (int i = 0; i < arr.length; i ++) {
            if (Arrays.binarySearch(arr, 5*i) != search2(arr, 5*i)) {
                result = false;
            }
        }

        System.out.println(search2(arr, 3));
        System.out.println(result);
    }

    /**
     * Linear Search Algorithm
     * This is not an efficient one for searching
     * @param arr
     * @param num
     * @return the index of the number in the given array
     * @return -1 if the number is not the the array
     */
    public static int search(int[] arr, int num) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == num) return i;
        }
        return -1;
    }

    /**
     * Finding the number in the given array using binary search
     * Solved by recursion
     * @param arr
     * @param num
     * @param start
     * @param end
     * @return the index of the number in the given array
     * @return -1 if the number is not the the array
     */
    public static int search(int[] arr, int num, int start, int end) {
        if (start > end) {
            return -1;
        }
        else {
            int mid = (start + end) / 2;

            if (arr[mid] == num) {
                return mid;
            }
            else if (num < arr[mid]) {
                return search(arr, num, start, mid - 1);
            }
            else {
                return search(arr, num, mid + 1, end);
            }
        }
    }

    /**
     * Another version of binary search using iterative method
     * @param arr
     * @param num
     * @return the index of the number in the given array
     * @return -1 if the number is not the the array
     */
    public static int search2(int[] arr, int num){
        int start = 0;
        int end = arr.length - 1;
        int mid;

        while (start <= end) {
            mid = (start + end) / 2;

            if (arr[mid] == num) return mid;
            else if (num > arr[mid]) {
                start = mid + 1;
            }
            else {
                end = mid - 1;
            }
        }
        return -1;
    }
}
