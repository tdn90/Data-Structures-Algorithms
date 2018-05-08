package algorithms.SortingAlgs;

public class Counting {
    public static void sort(Integer[] values, int k) {
        int[] copyRangeArr = new int[k + 1]; // O(k) space
        for (Integer val : values) copyRangeArr[val]++; // n times
        for (int i = 0, idx = 0; i < copyRangeArr.length; i++) {
            for (int j = copyRangeArr[i]; j > 0; j--) values[idx++] = i;  // n + k times
        }
    }
}
