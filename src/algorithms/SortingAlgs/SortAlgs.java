package algorithms.SortingAlgs;

/**
 * @author nguye
 * This is a parent class that provides some common utils for sorting
 */
class SortAlgs {
    /**
     * Exchange elements between two given indices in the given array
     */
    static void swap(Comparable[] arr, int i, int j) {
        Comparable cur = arr[i];
        arr[i] = arr[j];
        arr[j] = cur;
    }

    /**
     * Compare between two items
     */
    static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }
}
