package algorithms.SortingAlgs;

import java.util.Arrays;

public class Merge extends SortAlgs{
    private static Comparable[] extra;

    public static void sort(Comparable[] arr) {
        int size = arr.length;
        extra = new Comparable[size];
        for (int i = 0; i < size; i++) extra[i] = arr[i];
        sortHelper(arr,0,size-1);
    }

    public static void sortHelper(Comparable[] arr, int low, int high){
        if (low >= high) return;
        int mid = (low+high)/2;
        sortHelper(arr, low, mid);
        sortHelper(arr, mid+1, high);
        merge(arr, low, mid, high);
    }

    public static void merge(Comparable[] arr, int low, int mid, int high) {
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

    public static void main(String[] args) {
        Integer[] arr = new Integer[8];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random()*100);
        }
        System.out.println(Arrays.deepToString(arr));
        sort(arr);
        System.out.println(Arrays.deepToString(arr));
    }
}
