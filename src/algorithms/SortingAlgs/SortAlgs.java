package algorithms.SortingAlgs;

public class SortAlgs {
    public static void swap(Comparable[] arr, int i, int j) {
        Comparable cur = arr[i];
        arr[i] = arr[j];
        arr[j] = cur;
    }

    public static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }
}
