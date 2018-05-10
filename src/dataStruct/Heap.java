package dataStruct;

public abstract class Heap<T extends Comparable> {
    protected Comparable[] arr;
    private int size;
    private static final int DEFAULT_SIZE = 8;
    private static final double LOAD_FACTOR = 0.75;
    private static final double SHRINK_FACTOR = 0.25;

    public Heap() {
        arr = new Comparable[DEFAULT_SIZE+1];
        size = 0;
    }

    public Heap(int initSize) {
        arr = new Comparable[initSize+1];
        size = 0;
    }

    public void insert(T element) {
        arr[++size] = element;
        swim(size);

        // When the item is added to last index of array, double the size
        if (size == arr.length-1) growArr();
    }

    T delMaxPrior() {
        exch(1, size);
        T max = (T) arr[size];
        arr[size--] = null;
        sink(1);
        if (size == arr.length/4 - 1) sinkArr();
        return max;
    }

    private void sink(int idx) {
        int bigBro = morePriorChild(idx);
        while (bigBro > 0 && morePrior(bigBro, idx)) {
            exch(bigBro,idx);
            idx = bigBro;
            bigBro = morePriorChild(idx);
        }
    }

    /**
     * Locate the more prioritized child of an element at a specific index
     * @param idx: location of the current (parent) element
     * @return the index of the more prioritized child of an element at a specific index.
     * Return -1 if there is no children
     */
    private int morePriorChild(int idx) {
        int leftIdx = 2*idx;
        if (leftIdx > size) return -1;
        int rightIdx = leftIdx+1;
        if (rightIdx > size) return leftIdx;
        else return morePrior(leftIdx,rightIdx) ? leftIdx : rightIdx;
    }

    private void swim(int idx) {
        while (idx > 1 && morePrior(idx, idx/2)) {
            exch(idx,idx/2);
            idx /= 2;
        }
    }

    /**
     * Helper method to support dynamically-sized array
     */
    private void growArr() {
        Comparable[] newArr = new Comparable[arr.length*2];
        for (int i = 1; i <= size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    /**
     * Helper method to support dynamically-sized array
     */
    private void sinkArr() {
        T[] newArr = (T[]) new Comparable[arr.length/2];
        for (int i = 1; i <= size; i++) {
            newArr[i] = (T) arr[i];
        }
        arr = newArr;
    }

    private void exch(int i, int j) {
        Comparable cur = arr[i];
        arr[i] = arr[j];
        arr[j] = cur;
    }

    public abstract boolean morePrior(int a, int b);

    boolean isHeap(int idx) {
        if (2*idx > size) return true;
        else if (2*idx+1 > size) return morePrior(idx,2*idx);
        else return morePrior(idx,2*idx) && morePrior(idx,2*idx+1) && isHeap(2*idx) && isHeap(2*idx+1);
    }
}
