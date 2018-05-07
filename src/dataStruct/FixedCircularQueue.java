package dataStruct;

import java.util.NoSuchElementException;

public class FixedCircularQueue<T> implements Queue<T> {
    private T[] arr;
    private int size, first, last;

    public FixedCircularQueue(int maxSize) {
        arr = (T[]) new Object[maxSize];
        size = first = last = 0;
    }

    /**
     * Append tbe given object to this Queue
     * @param obj : object to be added
     * @return true if object can be added, false otherwise.
     */
    @Override
    public boolean enqueue(T obj) {
        if (!isFull()) {
            arr[last++] = obj;
            last %= arr.length;
            size++;
            return true;
        }
        return false;
    }

    /**
     * Retrieve and remove the head of this queue
     * @return the object (head) of this queue
     * @throws java.util.NoSuchElementException if queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        else {
            T result = arr[first];
            arr[first++] = null;
            first %= arr.length;
            size--;
            return result;
        }
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Retrieve, but do not remove the head of this queue
     * @return the object (head) of this queue, or null if queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) return null;
        else return arr[first];
    }

    /**
     * @return true if this queue is full
     */
    public boolean isFull() {
        return size == arr.length;
    }
}
