package dataStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author nguye
 *
 * This is an interesting assignment for Algorithms (CS2223)
 *
 * This data type offers Bag-like behavior with the added constraint that it tries
 * to minimize space by keeping track of the count of each item in the bag.
 *
 * Find the definition of MultiSet on Wikipedia (https://en.wikipedia.org/wiki/Multiset)
 *
 * In all of the performance specifications, N refers to the total number of items
 * in the MultiSet while U refers to the total number of unique items.
 *
 * @param <Item>
 */
public class MultiSet<Item extends Comparable<Item>> {
    private int N;
    private int U;
    private Node head;
    private Node tail;

    /** You must use this Node class as part of a LinkedList to store the MultiSet items. Do not modify this class. */
    class Node {
        private Item   item;
        private int    count;
        private Node   next;

        // This is only here for testing
        public String toString() {
            return item.toString() + ", count: " + count;
        }
    }

    /** Create an empty MultiSet. */
    public MultiSet () {
        N = 0;
        U = 0;
        head = null;
        tail = null;
    }

    /**
     * Initialize the MultiSet to contain the unique elements found in the initial list.
     *
     * Performance is allowed to be dependent on N*N, where N is the number of total items in initial.
     */
    public MultiSet(Item [] initial) {
        for (Item anInitial : initial) { // O(N)
            this.add(anInitial);  // O(N)
        }
    }

    /**
     * Return the number of items in the MultiSet.
     *
     * Performance must be independent of the number of items in the MultiSet, or ~ 1.
     */
    public int size() {
        return N;
    }

    /**
     * Determines equality with another MultiSet objects.
     *
     * Assume U=number of unique items in self while UO=number of unique items in other.
     *
     * Performance must be linearly dependent upon min(U1,U2)
     */
    public boolean identical (MultiSet<Item> other) {
        if (other == null) return false;
        if (this.N == other.N && this.U == other.U) {
            if (N == 0) return true;
            else {
                Node cur1 = this.head;
                Node cur2 = other.head;
                while (cur1 != null && cur2 != null) {
                    if (! (cur1.item.equals(cur2.item) && cur1.count == cur2.count)) {
                        return false;
                    }
                    cur1 = cur1.next;
                    cur2 = cur2.next;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * Return an array that contains the items from the MultiSet.
     *
     * Performance must be linearly dependent on N.
     */
    public Item[] toArray() {
        Item[] result = (Item[]) new Comparable[N];
        int i = 0;
        Node cur = head;
        while (cur != null) {
            int count = cur.count;
            for (int j = 0; j < count; j++) {
                result[i++] = cur.item;
            }
            cur = cur.next;
        }
        return result;
    }

    /**
     * Add an item to the MultiSet.
     *
     * Performance must be no worse than linearly dependent on N.
     */
    public boolean add(Item it) {
        return add(it, 1);
    }

    private boolean add(Item it, int amt) {
        if (head == null) { 	// Add to an empty set
            head = tail = makeNode(it, amt);
            U++;
        }
        else {
            int cmpTail = it.compareTo(tail.item);
            // Add after tail
            if (cmpTail > 0) {
                tail.next = makeNode(it, amt);
                tail = tail.next;
                U++;
                N += amt;
                return true;
            }
            // Add right at tail
            else if (cmpTail == 0) {
                tail.count+=amt;
                N += amt;
                return true;
            }
            int cmpHead = it.compareTo(head.item);
            // Add right at head
            if (cmpHead == 0) head.count += amt;
                // Add right before head
            else if (cmpHead < 0) {
                Node newNode = makeNode(it, amt);
                newNode.next = head;
                head = newNode;
                U++;
            }
            // Insert in middle
            else {
                Node cur = head;
                while (cur.next != null) {
                    Node next = cur.next;
                    int cmp2 = it.compareTo(next.item);
                    if (cmp2 <= 0) {
                        if (cmp2 == 0) {
                            next.count += amt;
                            N+=amt;
                            return true;
                        }
                        else {
                            Node newNode = makeNode(it,amt);
                            newNode.next = next;
                            cur.next = newNode;
                            U++;
                            N += amt;
                            return true;
                        }
                    }
                    else cur = next;
                }
            }
        }
        N += amt;
        return true;
    }

    /**
     * Helper method to create a node of a given amount
     * Technically a constructor for Node
     * @param it: the item used to create the node
     * @param amt: amount of item to be create
     * @return a new node created, with the given item and amount
     */
    private Node makeNode(Item it, int amt) {
        Node newNode = new Node();
        newNode.item = it;
        newNode.count = amt;
        return newNode;
    }

    /**
     * Helper method for multiplicity
     * Pre-condition: N >= U > 0
     * Performance: O(U)
     * @param it: item to check whether to be duplicated
     * @return the duplicate Node, or null if Item is not in this MultiSet
     */
    private Node getDuplicate(Item it) {
        Node cur = head;
        while (cur != null) {
            if (cur.item.equals(it)) return cur;
            cur = cur.next;
        }
        return null;
    }

    /**
     * Remove an item from the MultiSet; return false if not in the MultiSet to
     * begin with, otherwise returns true on success.
     *
     * Performance must be no worse than linearly dependent on N.
     */
    public boolean remove (Item it) {
        if (N == 0) return false; // Case 1: empty, return false
        else if (N == 1) { // Case 2: only one element
            if (head.item.equals(it)) {
                head = tail = null;
                N--; U--;
                return true;
            }
            else return false;
        }
        else { // Case 3: at least 2 elements
            if (it.compareTo(head.item) < 0 || it.compareTo(tail.item) > 0) return false;
            if (head.item.equals(it)) { // Remove head
                if (head.count == 1) {
                    head = head.next;
                    U--;
                }
                else head.count--;
                N--;
                return true;
            }
            Node cur = head;
            while (cur.next != null) {
                Node nextNode = cur.next;
                if (nextNode.item.equals(it)) { // Found item to be removed
                    // Only appear once, remove completely
                    if (nextNode.count == 1) {
                        cur.next = nextNode.next;
                        U--;
                    }
                    // Appear more than once, decrement count
                    else {
                        nextNode.count--;
                    }
                    N--;
                    return true;
                }
                cur = cur.next;
            }
            return false;
        }
    }

    /**
     * Returns the number of times item appears in the MultiSet.
     *
     * If returns 0, then the item is not contained within this MultiSet.
     *
     * Performance must be no worse than linearly dependent on U.
     */
    public int multiplicity (Item it) {
        Node check = getDuplicate(it);
        if (check == null) return 0;
        else return check.count;
    }

    /**
     * Determine whether this MultiSet includes other MultiSet.
     *
     * A MultiSet A includes a MultiSet B when: for all elements x in B with multiplicity mB(x), the
     * multiplicity mA(x) in A is >= mB(x).
     *
     * In degenerate case:
     *   1. If this is empty, false is always returned.
     *   2. If this is non-empty and other is empty, true is returned.
     *
     * Performance must be linearly dependent on U + UO where U is the number of unique items in this
     * and UO is the number of unique items in other.
     */
    public boolean includes(MultiSet<Item> other) {
        if (N == 0) return false;
        else if (other.N == 0) return true;
        else if (U < other.U) return false;
        else {
            Node thisCur = this.head;
            Node otherCur = other.head;
            while (otherCur != null) {
                while (thisCur != null && thisCur.item.compareTo(otherCur.item) < 0) {
                    thisCur = thisCur.next;
                }
                if (thisCur == null || thisCur.item.compareTo(otherCur.item) > 0) return false;
                else {
                    if (thisCur.item.compareTo(otherCur.item) == 0) {
                        if (thisCur.count < otherCur.count) return false;
                    }
                    otherCur = otherCur.next;
                }
            }
            return true;
        }
    }

    /**
     * Return a MultiSet which represents intersection with existing MultiSet.
     *
     * Performance must be linearly dependent on the number of unique items in both MultiSet
     * objects, or in other words ~ U + UO where U is the number of items in this and MO
     * is the number of items in other.
     *
     * Consider definition of intersect on wikipedia page as to be the correct logic:
     *
     * This is challenging.
     */
    public MultiSet<Item> intersects(MultiSet<Item> other) {
        MultiSet<Item> result = new MultiSet<>();
        Node thisCur = this.head;
        Node otherCur = other.head;
        while (otherCur != null && thisCur != null) {
            while (thisCur != null && thisCur.item.compareTo(otherCur.item) < 0) {
                thisCur = thisCur.next;
            }
            if (thisCur != null && thisCur.item.compareTo(otherCur.item) == 0) {
                int amount = thisCur.count <= otherCur.count ? thisCur.count : otherCur.count;
                result.add(thisCur.item, amount);
            }
            otherCur = otherCur.next;
        }
        return result;
    }

    /**
     * Return a MultiSet which represents union with existing MultiSet.
     *
     * Performance must be linearly dependent on the number of items in both MultiSet
     * objects, or in other words ~ UO + U where UO is the number of unique items in other and U
     * is the number of unique items in this MultiSet.
     *
     * Consider definition of intersect on wikipedia page as to be the correct logic:
     *
     * This is challenging.
     */
    public MultiSet<Item> union(MultiSet<Item> other) {
        MultiSet<Item> result = new MultiSet<>();
        Node thisCur = this.head;
        Node otherCur = other.head;

        while (thisCur != null || otherCur != null) {
            if (thisCur == null) {
                result.add(otherCur.item, otherCur.count);
                otherCur = otherCur.next;
            }
            else if (otherCur == null) {
                result.add(thisCur.item, thisCur.count);
                thisCur = thisCur.next;
            }
            else {
                int cmp = otherCur.item.compareTo(thisCur.item);
                if (cmp == 0) {
                    int count = otherCur.count >= thisCur.count ? otherCur.count : thisCur.count;
                    result.add(thisCur.item, count);
                    thisCur = thisCur.next;
                    otherCur = otherCur.next;
                }
                else if (cmp < 0) {
                    result.add(otherCur.item, otherCur.count);
                    otherCur = otherCur.next;
                }
                else {
                    result.add(thisCur.item, thisCur.count);
                    thisCur = thisCur.next;
                }
            }
        }
        return result;
    }

    // This method is only here for testing
    public void printSet() {
        if (head == null) {
            System.out.println("This set is empty");
            return;
        }
        Node cur = head;
        System.out.println(cur.toString());
        while (cur.next != null) {
            cur = cur.next;
            System.out.println(cur.toString());
        }
    }

    // BONUS QUESTION

    /**
     * Implementing iterator for MultiSet
     * @return a multiset iterator
     */
    public Iterator<Item> iterator() {
        return new MultiSetIterator();
    }

    class MultiSetIterator implements Iterator<Item> {
        private Node current;
        private int count;

        public MultiSetIterator() {
            current = head;
            if (current != null) {
                count = current.count;
            }
        }
        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        public boolean hasNext() {
            return current != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Item next() {
            if (current == null) throw new NoSuchElementException("No more element to iterate");
            Item cur = current.item;
            count--;
            if (count == 0) {
                current = current.next;
                if (current != null) {
                    count = current.count;
                }
            }
            return cur;
        }
    }
}
