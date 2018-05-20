package algorithms.SortingAlgs;

/**
 * @author nguye
 * This is my written version of Bubble Sort.
 * Probably the worst sorting algorithm commonly known.
 * Time complexity: O(n^2) in worst case
 */
public class Bubble extends SortAlgs{
    public static void sort(Comparable[] arr) {
        boolean sorted = false;
        while (!sorted) {
            sorted = true;
            for (int i = 0; i < arr.length - 1; i++) {
                if (less(arr,i+1,i)) {
                    swap(arr, i, i + 1);
                    sorted = false;
                }
            }
        }
    }
}
