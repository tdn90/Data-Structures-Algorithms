package dataStruct;

import java.util.EmptyStackException;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This is my self-implemented version of Doubly Linked List
 * @param <T>: Generic data type of objects to be dealt with
 */
public class LinkedList<T> implements List<T>, Queue<T>, Stack<T>{
    private static class Node<T> {
        private T content;
        private Node<T> prev;
        private Node<T> next;

        Node(T content) {
            this.content = content;
            this.prev = null;
            this.next = null;
        }

        T getContent() {return this.content;}

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }


        @Override
        public String toString() {
            return content.toString();
        }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size;

    /**
     * Appends the specified object to the end of the list
     *
     * @param obj : object to be added
     * @return true
     */
    @Override
    public boolean add(T obj) {
        Node<T> newNode = new Node<>(obj);
        if (size == 0) {
            head = newNode;
            tail = head;
        }
        else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
        return true;
    }

    /**
     * Inserts the given object at a specific index of the list
     *
     * @param idx : index specified
     * @param obj : object to be added
     * @throws IndexOutOfBoundsException if index is out of range
     */
    @Override
    public void add(int idx, T obj) {
        // Case 1: index out of bound
        if (idx < 0 || idx > size) {
            throw new IndexOutOfBoundsException();
        }

        // Case 2: add at the end
        else if (idx == size) this.add(obj);

        else {
            Node<T> newNode = new Node<>(obj);
            // Case 3: add to the front
            if (idx == 0) {
                newNode.next = head;
                head.prev = newNode;
                head = newNode;
            }
            // Case 4: Add in the middle
            else {
                Node<T> current = head;
                int currentIndex = 0;
                while (currentIndex < idx) {
                    current = current.next;
                    currentIndex++;
                }
                current.prev.next = newNode;
                newNode.prev = current.prev;
                newNode.next = current;
                current.prev = newNode;
            }
            size++;
        }
    }

    /**
     * Removes all objects in the list
     */
    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    /**
     * Check if this list contains the given element
     *
     * @param obj : specified objects to be found
     * @return true if this list contains the given object, false otherwise
     */
    @Override
    public boolean contains(Object obj) {
        if (obj == null || size == 0) return false;
        else {
            Node<T> current = head;
            while (current != tail) {
                if (current.content.equals(obj)) return true;
                current = current.next;
            }
            return tail.content.equals(obj);
        }
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
        if (idx < 0 || idx >= size) {
            throw new IndexOutOfBoundsException();
        }
        else {
            if (idx == 0) return head.getContent();
            else if (idx == size - 1) return tail.getContent();
            else {
                int currentIndex = 0;
                Node<T> current = head;
                while (currentIndex < idx) {
                    currentIndex++;
                    current = current.next;
                }
                return current.getContent();
            }
        }
    }

    /**
     * @param obj : given object to find
     * @return the index of the given object. Return -1 if object is
     * not in the list.
     */
    @Override
    public int indexOf(Object obj) {
        if (obj == null || size == 0) return -1;
        else {
            Node<T> current = head;
            int currentIndex = 0;
            while (current != tail) {
                if (current.content.equals(obj)) return currentIndex;
                current = current.next;
                currentIndex++;
            }
            if (tail.content.equals(obj)) return currentIndex;
            return -1;
        }
    }


    /**
     * Append tbe given object to this Queue
     *
     * @param obj : object to be added
     * @return true if object can be added, false otherwise.
     */
    @Override
    public boolean enqueue(T obj) {
        return this.add(obj); // add to tail of the list
    }

    /**
     * Retrieve and remove the head of this queue
     *
     * @return the object (head) of this queue
     * @throws java.util.NoSuchElementException if queue is empty
     */
    @Override
    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException();
        else return this.remove(0); // remove head
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
     * Retrieve, but do not remove the head of this queue
     *
     * @return the object (head) of this queue, or null is queue is empty
     */
    @Override
    public T peek() {
        if (isEmpty()) return null;
        else return head.content;
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
            T result;
            // Remove the only element
            if (size == 1) {
                result = head.content;
                head = tail = null;
            }
            else {  // Remove the tail
                result = tail.content;
                tail = tail.prev;
                tail.next = null;
            }
            size--;
            return result;
        }
    }

    /**
     * Pushes an element on top of this Stack
     *
     * @param obj : object to be pushed on stack
     * @return the object itself
     */
    @Override
    public boolean push(T obj) {
        return this.add(obj);
    }


    /**
     * @return an iterator over all elements in this list
     * from index 0 to end
     */
    @Override
    public Iterator<T> iterator() {
        //TODO: implement this
        return null;
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
        if (idx < 0 || idx >= size) throw new IndexOutOfBoundsException();
        else {
            // Case 1: remove head
            if (idx == 0) {
                T result = head.getContent();
                if (size == 1) {
                    head = tail = null;
                }
                else {
                    head.next.prev = null;
                    head = head.next;
                }
                size--;
                return result;
            }
            // Case 2: remove tail
            else if (idx == size - 1) {
                T result = tail.getContent();
                tail.prev.next = null;
                tail = tail.prev;
                size--;
                return result;
            }
            // Case 3: remove middle element
            else {
                int currentIndex = 0;
                Node<T> current = head;
                while (currentIndex < idx) {
                    current = current.next;
                    currentIndex++;
                }
                T result = current.getContent();
                current.prev.next = current.next;
                current.next.prev = current.prev;
                size--;
                return result;
            }
        }
    }

    /**
     * Remove the specified object
     *
     * @param obj : given object to be removed
     * @return true if remove successfully, false otherwise
     */
    @Override
    public boolean remove(Object obj) {
        // Case 1: invalid conditions
        if (obj == null || size == 0) return false;

            // Case 2: Remove the only element in the list
        else if (size == 1) {
            if (head.getContent().equals(obj)) {
                head = tail = null;
                size--;
                return true;
            }
            return false;
        }
        else {
            // Remove head
            if (head.getContent().equals(obj)) {
                head.next.prev = null;
                head = head.next;
                size--;
                return true;
            }
            else {
                Node<T> current = head.next;
                while (current != tail) {
                    // Remove middle
                    if (current.getContent().equals(obj)) {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                        size--;
                        return true;
                    }
                    current = current.next;
                }
                // Remove tail
                if (tail.getContent().equals(obj)) {
                    tail.prev.next = null;
                    tail = tail.prev;
                    size--;
                    return true;
                }
                else {
                    return false;
                }
            }
        }
    }

    /**
     * @return the size of this list
     */
    @Override
    public int size() {
        return size;
    }
}
