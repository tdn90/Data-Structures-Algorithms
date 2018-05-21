package dataStruct;

/**
 * @author nguye
 * First In First Out (FIFO) data structure
 * @param <T>
 */
public interface Queue<T> {
    /**
     * Append tbe given object to this Queue
     * @param obj: object to be added
     * @return true if object can be added, false otherwise.
     */
    boolean enqueue(T obj);

    /**
     * Retrieve and remove the head of this queue
     * @return the object (head) of this queue
     */
    T dequeue();

    boolean isEmpty();

    /**
     * Retrieve, but do not remove the head of this queue
     * @return the object (head) of this queue, or null is queue is empty
     */
    T peek();
}
