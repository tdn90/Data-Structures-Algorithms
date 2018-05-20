package dataStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Chain<K, V> {
    class Entry {
        private K key;
        private V val;
        private Entry next;

        Entry(K key, V val) {
            this.key = key;
            this.val = val;
            next = null;
        }

        K getKey() {
            return key;
        }

        V getValue() {
            return val;
        }

        public String toStr() {
            return "Key :" + key.toString() + "\t\t" + "Val: " + val.toString();
        }
    }


    private Entry head;
    private int size;

    int size() {
        return size;
    }

    void add(K key, V val) {
        if (head == null) head = new Entry(key,val);
        else {
            Entry cursor = head;
            while (cursor != null) {
                if (cursor.key.equals(key)) {
                    cursor.val = val;
                    return;
                }
                else cursor = cursor.next;
            }
            Entry newNode = new Entry(key,val);
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    boolean containsKey(K key) {
        return retrieveVal(key) != null;
    }

    V retrieveVal(Object key) {
        Entry cursor = head;
        while (cursor != null) {
            if (cursor.key.equals(key)) return cursor.val;
            else cursor = cursor.next;
        }
        return null;
    }

    boolean containsVal(Object val) {
        Entry cursor = head;
        while (cursor != null) {
            if (cursor.val.equals(val)) return true;
            else cursor = cursor.next;
        }
        return false;
    }

    V del(Object key) {
        if (head == null) return null;
        else if (head.key.equals(key)) {
            V result = head.val;
            head = head.next;
            size--;
            return result;
        }
        Entry cursor = head;
        while (cursor.next != null) {
            if (cursor.next.equals(key)) {
                V result = cursor.next.val;
                cursor.next = cursor.next.next;
                size--;
                return result;
            }
            cursor = cursor.next;
        }
        return null;
    }

    Iterator<Entry> iterator() {
        return new ChainIterator();
    }

    class ChainIterator implements Iterator<Entry> {
        Entry nextNode;

        ChainIterator() {
            nextNode = head;
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
            return nextNode != null;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Entry next() {
            if (!hasNext()) throw new NoSuchElementException();
            else {
                Entry result = nextNode;
                nextNode = nextNode.next;
                return result;
            }
        }
    }
}
