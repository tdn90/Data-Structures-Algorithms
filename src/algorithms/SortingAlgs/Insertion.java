package algorithms.SortingAlgs;

public class Insertion{
    public static void sort(Comparable[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i;
            Comparable cur = arr[i];
            while (j >= 1 && cur.compareTo(arr[j-1]) < 0) {
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = cur;
        }
    }
}
