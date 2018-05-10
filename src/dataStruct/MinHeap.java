package dataStruct;

public class MinHeap<T extends Comparable> extends Heap<T> {
    @Override
    public boolean morePrior(int a, int b) {
        return arr[a].compareTo(arr[b]) < 0;
    }

    public T delMin() {
        return delMaxPrior();
    }
}