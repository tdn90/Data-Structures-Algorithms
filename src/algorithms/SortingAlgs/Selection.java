package algorithms.SortingAlgs;

/**
 * @author nguye
 * The most common implementation of selection sort.
 * Time-complexity: O(n^2)
 */
public class Selection extends SortAlgs {
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j < arr.length; j++) {
                if (less(arr,j,minIndex)) minIndex = j;
            }
            swap(arr,i,minIndex);
        }
    }
}
