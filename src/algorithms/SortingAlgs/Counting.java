package algorithms.SortingAlgs;

/**
 * @author nguye
 * This is my version of Counting Sort.
 * Quite short and efficient implementation.
 * Time complexity: O(n + k) in worst case.
 */
public class Counting {
    /**
     * Attempt to sort the given array
     * @param values: given positive integer array to be sorted
     * @param k: the range of numbers in the specified array (from 0 to k)
     */
    public static void sort(Integer[] values, int k) {
        // Create a "basket" array
        int[] copyRangeArr = new int[k + 1]; // O(k) space

        // Put each number in the given array into the proper baskets
        for (Integer val : values) copyRangeArr[val]++; // n times

        // Sort here
        for (int i = 0, idx = 0; i < copyRangeArr.length; i++) {
            for (int j = copyRangeArr[i]; j > 0; j--) values[idx++] = i;  // n + k times
        }
    }
}
