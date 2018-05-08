package dataStruct;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FixedSizeStack<T> implements Stack<T>{
    private T[] arr;
    private int nextPos;

    public FixedSizeStack(int size) {
        arr = (T[]) new Object[size];
        nextPos = 0;
    }

    /**
     * Test if stack is empty
     *
     * @return true if this Stack is empty, false otherwise.
     */
    @Override
    public boolean isEmpty() {
        return nextPos == 0;
    }

    /**
     * Looks at top of the Stack without removing it.
     *
     * @return the top of this Stack; return null if Stack is empty
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) throw new EmptyStackException();
        else return arr[nextPos-1];
    }

    /**
     * Retrieves and removes the object on top of this Stack
     *
     * @return the object on the top of this Stack upon its removal
     * @throws EmptyStackException if the stack is empty
     */
    @Override
    public T pop() {
        if (isEmpty()) throw new EmptyStackException();
        else {
            T result = peek();
            arr[--nextPos] = null;
            return result;
        }
    }

    /**
     * Pushes an element on top of this Stack
     *
     * @param obj : object to be pushed on stack
     * @return true if an object can be pushed into stack; false otherwise
     */
    @Override
    public boolean push(T obj) {
        if (isFull()) return false;
        else {
            arr[nextPos++] = obj;
            return true;
        }
    }

    /**
     * Check if the stack is full
     * @return true if the stack is full; false otherwise
     */
    public boolean isFull() {
        return nextPos == arr.length;
    }

    public Iterator iterator() {
        return new StackIterator();
    }

    private class StackIterator<T> implements Iterator<T> {
        private int currentPos;

        public StackIterator() {
            currentPos = nextPos-1;
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
            return currentPos >= 0;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public T next() {
            return (T) arr[currentPos--];
        }
    }
}
