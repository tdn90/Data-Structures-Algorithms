package algorithms.SortingAlgs;

class SortAlgs {
    static void swap(Comparable[] arr, int i, int j) {
        Comparable cur = arr[i];
        arr[i] = arr[j];
        arr[j] = cur;
    }

    static boolean less(Comparable[] arr, int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }
}
