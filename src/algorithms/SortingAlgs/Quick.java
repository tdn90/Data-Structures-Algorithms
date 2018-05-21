package algorithms.SortingAlgs;

/**
 * @author Professor Sedgewick, Professor Heneiman
 * Credit goes to two professors above.
 * I just try to re-write this sorting algorithm to best understand it.
 * This is a nice and efficient algorithm, using Divide-And-Conquer technique.
 * Practically much more efficient than Merge Sort.
 * Time complexity: O(n * logn) in any case
 */
public class Quick extends SortAlgs{
    public static void sort(Comparable[] arr) {
        shuffle(arr);
        sortHelper(arr,0,arr.length-1);
    }

    /**
     * Attempt to randomly shuffle the whole array to achieve random order
     */
    public static void shuffle(Comparable[] arr) {
        int size = arr.length;
        for (int i = 0; i < size; i++) {
            int idx = (int) (Math.random()*size);
            swap(arr,i,idx);
        }
    }

    private static void sortHelper(Comparable[] arr, int low, int high) {
        if (low >= high) return;
        int pivotPos = partition(arr, low, high);
        sortHelper(arr, low, pivotPos-1); // sort array on the left side of the pivot
        sortHelper(arr, pivotPos+1, high); // sort array on the right side of the pivot
    }

    /**
     * Attempt to find the pivot position.
     * Upon method completion, two conditions are true:
     * All elements on the left half of the pivot is less than the pivot element
     * All elements on the right half of the pivot is greater than the pivot element
     * @param arr: given array
     * @param low: start index to find pivot
     * @param high: end index to find pivot
     * @return the pivot position
     */
    private static int partition(Comparable[] arr, int low, int high) {
        // pivot is arr[low]
        int leftCur = low;
        int rightCur = high+1;

        // Basically run until two cursors cross
        while (true) {
            // Attempt to find the element that is larger than the pivot on the "left side"
            while (less(arr,++leftCur,low)) {
                if (leftCur == high) break;
            }

            // Attempt to find the element that is smaller than the pivot on the "right side"
            while (less(arr,low,--rightCur)) {
                if (rightCur == low) break;
            }

            // If two cursors cross, then break
            if (leftCur >= rightCur) break;

            // Otherwise, pivot will still logically be in between two cursors, swap those two cursors
            swap(arr,leftCur,rightCur);
        }
        // Put pivot into its correct position
        swap(arr,low,rightCur);
        return rightCur;
    }
}
