package algorithms.SortingAlgs;

/**
 * @author nguye
 * This is my written version of insertion sort.
 * Very efficient in sorting small arrays.
 * Time-complexity: O(n^2) in worst case, O(n) in average case
 */
public class Insertion{
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            Comparable cur = arr[i];
            // Only do half a swap here rather than a full swap
            // Basically "push" the larger items to the right, while finding a good slot for cur
            while (j >= 1 && cur.compareTo(arr[j-1]) < 0) {
                arr[j] = arr[j-1];
                j--;
            }
            // Found a slot, put cur in
            arr[j] = cur;
        }
    }
}
