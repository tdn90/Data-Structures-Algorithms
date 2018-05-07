package dataStruct;

import java.util.Iterator;

public interface List<T> {
    /**
     * Appends the specified object to the end of the list
     * @param obj: object to be added
     * @return true
     */
    boolean add(T obj);

    /**
     * Inserts the given object at a specific index of the list
     * @param idx: index specified
     * @param obj: object to be added
     * @throws IndexOutOfBoundsException if index is out of range
     */
    void add(int idx, T obj);

    /**
     * Removes all objects in the list
     */
    void clear();

    /**
     * Check if this list contains the given element
     * @param obj: specified objects to be found
     * @return true if this list contains the given object, false otherwise
     */
    boolean contains(Object obj);

    /**
     * Retrieve the object at the specified index
     * @param idx: specified index
     * @return the object at the given index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    T get(int idx);

    /**
     *
     * @param obj: given object to find
     * @return the index of the given object. Return -1 if object is
     * not in the list.
     */
    int indexOf(Object obj);

    /**
     * Check if list is empty
     * @return true if this list contains no objects; false otherwise
     */
    boolean isEmpty();

    /**
     * @return an iterator over all elements in this list
     * from index 0 to end
     */
    Iterator<T> iterator();

    /**
     * Removes an element at the given index
     * @param idx: given index
     * @return the object at the given index before removal
     * @throws IndexOutOfBoundsException if index is out of range
     */
    T remove(int idx);

    /**
     * Remove the specified object
     * @param obj: given object to be removed
     * @return true if remove successfully, false otherwise
     */
    boolean remove(Object obj);

    /**
     * @return the size of this list
     */
    int size();
}
