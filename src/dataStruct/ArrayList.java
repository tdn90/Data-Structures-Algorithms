package dataStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is my self-implemented version of Java's / Oracle's ArrayList
 * @param <T>: Generic data type of objects to be dealt with
 */
public class ArrayList<T> implements List<T> {
    private int size;
    private T[] arr;
    private int lastEmptyIndex;

    private static final int DEFAULT_SIZE = 8;
    private static final double LOAD_FACTOR = 0.75;
    private static final double SHRINK_FACTOR = 0.25;

    /**
     * Default constructor: initiating the ArrayList with default size 8 and load_factor 0.75
     */
    public ArrayList() {
        size = 0;
        lastEmptyIndex = 0;
        arr = (T[]) new Object[DEFAULT_SIZE];
    }


    /**
     * Appends the specified object to the end of the list
     * @param obj : object to be added
     * @return true
     */
    @Override
    public boolean add(T obj) {
        add(lastEmptyIndex, obj);
        return true;
    }

    /**
     * Inserts the given object at a specific index of the list
     * @param idx : index specified
     * @param obj : object to be added
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void add(int idx, T obj) {
        if (idx > lastEmptyIndex || idx < 0) {
            throw new ArrayIndexOutOfBoundsException("You entered index " + idx + ", while your list's size is only " + size);
        }
        for (int i = lastEmptyIndex; i > idx; i--) {
            arr[i] = arr[i-1];
        }
        arr[idx] = obj;
        lastEmptyIndex++;
        size++;
        if (size == LOAD_FACTOR * arr.length) growArray();
    }

    /**
     * Grow the underlying array by twice its size when the content reaches the load factor
     */
    private void growArray() {
        int newSize = arr.length * 2;
        T[] result = (T[]) new Object[newSize];
        for (int i = 0; i < size; i++) {
            result[i] = arr[i];
        }
        arr = result;
    }

    /**
     * Removes all objects in the list
     */
    @Override
    public void clear() {
        size = 0;
        lastEmptyIndex = 0;
        arr = (T[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Check if this list contains the given element
     * @param obj : specified objects to be found
     * @return true if this list contains the given object, false otherwise
     */
    @Override
    public boolean contains(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) return true;
        }
        return false;
    }

    /**
     * Retrieve the object at the specified index
     *
     * @param idx : specified index
     * @return the object at the given index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T get(int idx) {
        if (idx < 0 || idx > size - 1) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        else return arr[idx];
    }

    /**
     * @param obj : given object to find
     * @return the index of the given object. Return -1 if object is
     * not in the list.
     */
    @Override
    public int indexOf(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) return i;
        }
        return -1;
    }

    /**
     * Check if list is empty
     *
     * @return true if this list contains no objects; false otherwise
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * @return an iterator over all elements in this list
     * from index 0 to end
     */
    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    private class ArrayListIterator implements Iterator<T>{
        private int curIdx;

        ArrayListIterator() {
            curIdx = 0;
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext() {
            return curIdx < lastEmptyIndex;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                return arr[curIdx++];
            }
        }
    }

    /**
     * Removes an element at the given index
     *
     * @param idx : given index
     * @return the object at the given index before removal
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public T remove(int idx) {
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            T result = arr[idx];
            for (int i = idx; i < size; i++) {
                arr[i] = arr[i + 1];
            }
            size--;
            lastEmptyIndex--;
            if (size <= SHRINK_FACTOR * arr.length) shrinkArray();
            return result;
        }
    }

    /**
     * Shrink the array when appropriate. Effectively reduce the space consumed by 2 times.
     * Condition: size < arr.length/2
     */
    private void shrinkArray() {
        T[] result = (T[]) new Object[arr.length / 2];
        for (int i = 0; i < size; i ++) {
            result[i] = arr[i];
        }
        arr = result;
    }

    /**
     * Remove the specified object
     * @param obj : given object to be removed
     * @return true if remove successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(obj)) {
                for (int j = i; j < size; j++) {
                    arr[j] = arr[j + 1];
                }
                size--;
                lastEmptyIndex--;
                if (size <= SHRINK_FACTOR * arr.length) shrinkArray();
                return true;
            }
        }
        return false;
    }

    /**
     * @return the size of this list
     */
    @Override
    public int size() {
        return this.size;
    }
}