package algorithms.SortingAlgs;

/**
 * @author nguye
 * This is my written version of Heap Sort.
 * Make use of heap features (sink, swim / heapify)
 * Time complexity: O(n * logn) in worst case
 */
public class HeapSort extends SortAlgs{
    /**
     * Attempt to sort the given array
     * @param arr: given array to be sorted
     */
    public static void sort(Comparable[] arr) {
        // Turn the array from index 1 to arr.length-1 into a heap
        for (int i = arr.length/2; i > 0; i--) {
            sink(arr,i,arr.length-1);
        }

        // Continuously swap the maximum element to the end and reduce the problem size by 1
        for (int i = arr.length-1; i > 1; i--) {
            exch(arr,i,1);
            sink(arr,1,i);
        }

        // Done sort the array from 1 to arr.length-1, now "insert" the first element in
        // Basically use insertion sort principle here
        int k = 0;
        Comparable first = arr[0];
        while (k < arr.length-1 && arr[k+1].compareTo(first) < 0 ) {
            arr[k] = arr[k+1];
            k++;
        }
        arr[k] = first;
    }

    /**
     * Attempt to restore the heap characteristic
     * @param arr: given array
     * @param idx: index of the element to swim up
     */
    private static void swim(Comparable[] arr, int idx) {
        while (idx > 1 && morePrior(arr, idx, idx/2)) {
            exch(arr, idx,idx/2);
            idx /= 2;
        }
    }

    /**
     * Attempt to restore the heap characteristic
     * @param arr: given array
     * @param idx: index of the element to sink down
     */
    private static void sink(Comparable[] arr, int idx, int N) {
        int bigBro = morePriorChild(arr, idx, N);
        while (bigBro > 0 && morePrior(arr, bigBro, idx)) {
            exch(arr, bigBro,idx);
            idx = bigBro;
            bigBro = morePriorChild(arr, idx, N);
        }
    }

    /**
     * Simply a helper method for exchanging values between 2 indices
     */
    private static void exch(Comparable[] arr, int i, int j) {
        Comparable cur = arr[i];
        arr[i] = arr[j];
        arr[j] = cur;
    }

    /**
     * Locate the more prioritized child of an element at a specific index
     * @param idx: location of the current (parent) element
     * @return the index of the more prioritized child of an element at a specific index.
     * Return -1 if there is no children
     */
    private static int morePriorChild(Comparable[] arr, int idx, int N) {
        int leftIdx = 2 * idx;
        if (leftIdx >= N) return -1;
        int rightIdx = leftIdx + 1;
        if (rightIdx >= N) return leftIdx;
        else return morePrior(arr, leftIdx, rightIdx) ? leftIdx : rightIdx;
    }

    /**
     * Helper method to check priorities
     */
    private static boolean morePrior(Comparable[] arr, int a, int b) {
        return arr[a].compareTo(arr[b]) >= 0;
    }
}
