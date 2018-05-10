package algorithms.SortingAlgs;

public class Quick2 {
    public static void sort(Comparable[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    /**
     * Recursive quickSort
     * @param arr: array to be sorted
     * @param start: start index to sort
     * @param end: end index to sort
     */
    private static void quickSortHelper(Comparable[] arr, int start, int end) {
        if (start < end) {
            int pivot = partition(arr, start, end);

            // Recursively sort the sub-elements from the left of the pivot
            quickSortHelper(arr, start, pivot - 1);

            // Recursively sort the sub-elements from the right of the pivot
            quickSortHelper(arr, pivot + 1, end);
        }
    }


    /**
     * Helper method for QuickSort
     * Partitioning the array:
     * Step 1: pick the pivot (in this case the pivot is the end element)
     * Step 2: use that pivot and put every number smaller than it to its left,
     * and every numbers larger than it to its right.
     * Step 3: return the pivot final position
     * @param arr: array given
     * @param start: starting index point
     * @param end: ending index point
     * @return the pivot's final position in the array
     */
    private static int partition(Comparable[] arr, int start, int end) {
        // I'll choose the ending element as my pivot
        Comparable pivot = arr[end];

        // This variable will be used as an imaginary pivot (the pivot wall)
        int i = start - 1;

        // This variable will loop through the whole array
        int j = start;

        while (j < end) {
            // When we reach any element less than the pivot,
            // Swap it with the wall element
            // Then move the wall towards 1
            if (arr[j].compareTo(pivot) <= 0) {
                i++;
                swap(arr, i , j);
            }
            j++;
        }

        // Swap the element next to the wall with the pivot
        // This basically means taking the pivot and replace
        // the imaginary pivot
        swap(arr, i + 1, end);

        return i + 1;
    }

    /**
     * Helper method for swapping numbers in the array
     * Condition: arr.length > 0; i < arr.length; j < arr.length
     * @param arr: array given
     * @param i: an index of the element we want to swap
     * @param j: another index of the element we want to swap
     */
    private static void swap(Comparable[] arr, int i, int j) {
        Comparable current = arr[i];
        arr[i] = arr[j];
        arr[j] = current;
    }
}
