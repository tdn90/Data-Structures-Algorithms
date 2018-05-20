package dataStruct;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ChainingHashMap<K,V> {
    private Chain<K,V>[] map;
    private final int DEFAULT_MAP_SIZE = 8;
    private int size; // represent the number of key-value pairs.

    /**
     * Create a new hash map with initial size 8
     */
    public ChainingHashMap() {
        map = new Chain[DEFAULT_MAP_SIZE];
        for (int i = 0; i < map.length; i++) map[i] = new Chain<>();
        size = 0;
    }

    /**
     * Refresh the whole hash map
     */
    public void clear() {
        map = new Chain[DEFAULT_MAP_SIZE];
        for (int i = 0; i < map.length; i++) map[i] = new Chain<>();
        size = 0;
    }

    /**
     * Check whether this map contains a specified key
     * @param key: given key to find
     * @return true if key is in the map, false otherwise
     */
    public boolean containsKey(Object key) {
        return get(key) != null;
    }

    /**
     * Check to see whether this map contains a specific value
     * @param val: value to be found
     * @return true if this map contains the given value, false otherwise
     */
    public boolean containsValue(Object val) {
        for (int i = 0; i < map.length; i++) {
            Iterator it = map[i].iterator();
            while (it.hasNext()) {
                Chain.Entry cur = (Chain.Entry) it.next();
                if (cur.getValue().equals(val)) return true;
            }
        }
        return false;
    }

    /**
     * @param key: given key to be found
     * @return the value mapped to the specified key
     */
    public V get(Object key) {
        int idx = getIdx(key);
        return map[idx].retrieveVal(key);
    }

    /**
     * Remove the very first instance of Entry that has the given key in the map
     * @param key: specified key to be removed
     * @return the value being removed mapped with the given key
     */
    public V remove(Object key) {
        int idx = getIdx(key);
        V result = map[idx].del(key);
        if (result != null) size--;
        return result;
    }

    /**
     * Attempt to map the key to a specified value
     * @param key : key added
     * @param val : value attached to key
     */
    public void put (K key, V val) {
        int idx = getIdx(key);
        if (idx >= map.length) {
            System.out.println("Error: Key is " + key.toString() + " and value is " + val.toString());
            return;
        }
        if (map[idx].containsKey(key)) map[idx].add(key,val);
        else {
            map[idx].add(key,val);
            size++;
            if (map[idx].size() >= 8) growArr();
        }
    }

    /**
     * Find the proper slot to put the new key
     * @param key: given key to find slot
     * @return the proper index in map that fit the key
     */
    private int getIdx(Object key) {
        return Math.abs(key.hashCode() % map.length);
    }

    /**
     * Grow the underlying array (hash table) and re-assign every item into its new place
     */
    private void growArr() {
        Chain<K,V>[] newMap;
        int newSize = map.length*2;
        newMap = new Chain[newSize];
        for (int i = 0; i < newSize; i++) newMap[i] = new Chain<>();
        for (int i = 0; i < map.length; i++) {
            Iterator it = map[i].iterator();
            while (it.hasNext()) {
                Chain.Entry cur = (Chain.Entry) it.next();
                int idx = Math.abs(cur.getKey().hashCode() % newMap.length);
                newMap[idx].add((K)cur.getKey(),(V)cur.getValue());
            }
        }
        map = newMap;
    }

    public int size(){
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printTable() {
        for (int i = 0; i < map.length; i++) {
            System.out.println("Slot " + i + ": ");
            Iterator it = map[i].iterator();
            while (it.hasNext()) {
                Chain.Entry cur = (Chain.Entry) it.next();
                System.out.println("Key: " + cur.getKey() + "; Value: " + cur.getValue());
            }
        }
    }
}
