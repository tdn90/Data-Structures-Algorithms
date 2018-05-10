package algorithms.SortingAlgs;

import java.util.Arrays;

/**
 * Second version of Merge Sort
 */
public class Merge2 {
    /**
     * Implementation of MergeSort.
     * @param arr: given array to be sorted
     * @return a sorted array in ascending order
     * Note: Here, as I am self-implementing this mergeSort,
     * I realize I requested some memory storage to create new smaller arrays.
     * which is one draw back of improving time complexity.
     */
    public static Comparable[] sort(Comparable[] arr) {
        // Base case: either empty array or array of length 1, return it.
        if (arr.length < 2) return arr;

        else {
            int mid = arr.length / 2;

            /*
             * Divide the array into two halves
             * Conquer each of them
             */
            Comparable[] firstHalf = sort(Arrays.copyOfRange(arr, 0, mid));

            /*
             * I made two mistakes here on my first try:
             *
             * As the end index of this function is exclusive, I should make it arr.length
             *
             * I put (mid + 1) as starting index at first
             * However, it should be mid, as the firstHalf does not include the mid element
             */
            Comparable[] secondHalf = sort(Arrays.copyOfRange(arr, mid, arr.length));

            // Finally, combine them together
            return merge(firstHalf, secondHalf);
        }
    }

    /**
     * Helper method for mergeSort.
     * Merge two array together so that all elements maintains the ascending order
     * @param arr1: "left half" array
     * @param arr2: "right half" aray
     * @return the combined, sorted array
     */
    private static Comparable[] merge(Comparable[] arr1, Comparable[] arr2){
        int size1 = arr1.length;
        int size2 = arr2.length;

        Comparable[] result = new Comparable[size1 + size2];

        int i = 0;
        int j = 0;
        int k = 0;

        /*
         * Here, try to fill up the resulting array as much as possible
         */
        while (i < size1 && j < size2) {
            if (arr1[i].compareTo(arr2[j]) < 0) result[k++] = arr1[i++];
            else result[k++] = arr2[j++];
        }

        // Fill up the result array with whatever is left of arr1.
        while (i < arr1.length) result[k++] = arr1[i++];

        // Fill up the result array with whatever is left of arr2.
        while (j < arr2.length) result[k++] = arr2[j++];

        return result;
    }
}
