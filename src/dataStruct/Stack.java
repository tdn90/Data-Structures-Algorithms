package dataStruct;

/**
 * @author nguye
 * Last In First Out (LIFO) data structure
 */
public interface Stack<T> {
    /**
     * Test if stack is empty
     * @return true if this Stack is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Looks at top of the Stack without removing it.
     * @return the top of this Stack; return null if Stack is empty
     * @throws java.util.EmptyStackException if the stack is empty
     */
    T peek();

    /**
     * Retrieves and removes the object on top of this Stack
     * @return the object on the top of this Stack upon its removal
     * @throws java.util.EmptyStackException if the stack is empty
     */
    T pop();

    /**
     * Pushes an element on top of this Stack
     * @param obj: object to be pushed on stack
     * @return true if an object can be pushed into stack; false otherwise
     */
    boolean push(T obj);
}
