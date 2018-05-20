package algorithms.SortingAlgs;

/**
 * @author Professor Sedgewick, Professor Heneiman
 * Credit goes to two professors above.
 * I just try to re-write this sorting algorithm to best understand it.
 * This is a nice and efficient algorithm, using Divide-And-Conquer technique.
 * Time complexity: O(n * logn) in any case
 */
public class Merge extends SortAlgs{
    private static Comparable[] extra;

    public static void sort(Comparable[] arr) {
        int size = arr.length;
        extra = new Comparable[size];
        for (int i = 0; i < size; i++) extra[i] = arr[i];
        sortHelper(arr,0,size-1);
    }

    private static void sortHelper(Comparable[] arr, int low, int high){
        if (low >= high) return;
        int mid = (low+high)/2;
        sortHelper(arr, low, mid); // sort left recursively
        sortHelper(arr, mid+1, high); // sort right recursively
        merge(arr, low, mid, high); // merge two sorted arrays
    }

    /**
     * "Merge" two sides of given arrays (from low to mid, and from mid to high)
     * After merging complete, the given array is sorted from low to high
     */
    private static void merge(Comparable[] arr, int low, int mid, int high) {
        for (int i = low; i <= high; i++) extra[i] = arr[i];
        int i = low;
        int j = mid+1;
        int k = low;

        while (k <= high) {
            if (i > mid) { // run out of items in the left-half array
                arr[k++] = extra[j++];
            }
            else if (j > high) { // run out of items in the right-half array
                arr[k++] = extra[i++];
            }
            else if (less(extra,i,j)) { // current item in the left array is smaller than the current value in right array
                arr[k++] = extra[i++];
            }
            else { // current item in the right array is smaller than the current value in left array
                arr[k++] = extra[j++];
            }
        }
    }
}
