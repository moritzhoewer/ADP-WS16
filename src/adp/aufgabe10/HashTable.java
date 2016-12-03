/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 03.12.2016 
 * Aufgabe: Aufgabenblatt 10
 */

package adp.aufgabe10;

import java.util.ArrayList;
import java.util.OptionalInt;
import java.util.List;

import adp.aufgabe2.PrimeFinder;

/**
 * An (almost) generic HashTable that can store generic Data associated with a
 * String as key.
 *
 * @author Moritz Höwer
 * @version 1.0 - 03.12.2016
 */
public class HashTable<T> {
    /**
     * threshold at which to expand the underlying array [0-1]
     */
    private static final double LOADFACTOR_THRESHOLD = 0.8;

    /**
     * the initial capacity of the HashTable.
     *
     * <b>MUST</b> be prime!
     */
    private static final int INITIAL_CAPACITY = 13;

    /**
     * offset is used to limit ASCI code range to sensible characters in Hash
     * function
     */
    private static final int OFFSET = ' ';

    /**
     * base to be used in hash function
     */
    private static final int BASE = 127 - OFFSET;

    /**
     * Member class for storing metadata (key and deleted flag) alongside actual
     * data in the HashTable
     *
     * @author Moritz Höwer
     * @version 1.0 - 03.12.2016
     */
    private class HashTableEntry<X> {
        public String key;
        public X data;
        public boolean deleted;

        public HashTableEntry(String key, X data) {
            this.key = key;
            this.data = data;
            deleted = false;
        }
    }

    /**
     * the HashTable itself
     */
    private HashTableEntry<T>[] data;

    /**
     * cache for the entry count (saved 90% of execution time)
     */
    private int entryCount;

    /**
     * reusing PrimeFinder from Aufgabe 2 to ensure size is always a prime.
     */
    private PrimeFinder pf;

    public HashTable() {
        this(INITIAL_CAPACITY);
    }

    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        entryCount = 0;
        pf = new PrimeFinder();

        // ugly because generics
        data = (HashTableEntry<T>[]) new HashTableEntry[capacity];
    }

    /**
     * Stores a value (associated with a key) in this HashTable.
     * 
     * Calling put twice with the same key (same as in key1.equals(key2) ==
     * true) results in the first value being overwritten by the second one.
     * 
     * @param key
     *            the key used for referencing the value
     * @param value
     *            the value to be stored
     */
    public void put(String key, T value) {
        // check if we need to enlarge
        if (getLoadFactor() > LOADFACTOR_THRESHOLD) {
            enlargeHashTable();
        }
        int index = getIndexForKey(key);

        // if put resulted in the insertion of a new key, then update entries
        // counter
        if (data[index] == null || data[index].deleted) {
            entryCount++;
        }

        // insert or update value
        data[index] = new HashTableEntry<>(key, value);
    }

    /**
     * Returns the size of this HashTable (how many elements are stored).
     * 
     * @return the number of keys / values stored
     */
    public int size() {
        return entryCount;
    }

    /**
     * Goes through the HashTable and returns a List that contains all the keys
     * in no particular order.
     * 
     * @return List of all keys
     */
    public List<String> getKeys() {
        List<String> keys = new ArrayList<>();
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && !data[i].deleted) {
                keys.add(data[i].key);
            }
        }
        return keys;
    }

    /**
     * Checks whether a given key is stored in the HashTable.
     * 
     * @param key
     *            the key to check
     * @return true, if key is stored in the HashTable
     */
    public boolean containsKey(String key) {
        return indexOf(key).isPresent();
    }

    /**
     * Retrieves the value associated with key from the HashTable.
     * 
     * Key must be part of the HashTable, {@link HashTable#containsKey(String)}
     * can be used to check beforehand.
     * 
     * @param key
     *            the key to look up.
     * @return the value associated with the key
     * @throws IllegalArgumentException
     *             if key is not stored in the HashTable.
     */
    public T get(String key) {
        int index = indexOf(key).orElseThrow(IllegalArgumentException::new);
        return data[index].data;
    }

    /**
     * Increases the size of the HashTable.
     * 
     * Finds the first prime that is at least 1.5 times as large as the current
     * size and creates a new HashTable with that capacity. Then all values from
     * the current HashTable are inserted into the new one. Finally, the
     * HashTable takes over the state (data Array and entryCount) of the other
     * HashTable which is then left to be Garbage collected.
     */
    private void enlargeHashTable() {
        // Find new capacity as prime
        int newCapacity = data.length * 3 / 2;
        if (newCapacity % 2 == 0) {
            // ensure newCapacity is odd
            newCapacity++;
        }
        while (!pf.isPrime(newCapacity)) {
            newCapacity += 2;
        }

        // create temporary HashTable
        HashTable<T> temp = new HashTable<>(newCapacity);
        // rehash all values
        for (int i = 0; i < data.length; i++) {
            if (data[i] != null && !data[i].deleted) {
                temp.put(data[i].key, data[i].data);
            }
        }

        // "take over" temps state
        data = temp.data;
        entryCount = temp.entryCount; // should not have changed, but just in
                                      // case
    }

    /**
     * Generates a hash from the String.
     * 
     * Every character has value BASE^(position in String). BASE is basically
     * the 7 bit ASCII code, but values 0 - 32 (SPACE) are cut off, because they
     * are control characters. The characters value is then multiplied by the
     * offset from SPACE.
     * 
     * @param value
     *            the String to hash
     * @return the hash of the String (mapped to fit within the boundaries of
     *         the HashTable)
     */
    private int hash(String value) {
        int result = 0;
        double base = 1; // continually update base to avoid using costly
                         // call to Math.pow() all the time
        for (int i = 0; i < value.length(); i++) {
            result += (int) (base * (value.charAt(i) - OFFSET));
            base *= BASE;
        }
        return Math.abs(result % data.length);
    }

    /**
     * Returns probe jump width based on a hash, mapped to be at most 2/3 of the
     * length of the HashTable.
     * 
     * Eliminating the call to hash() in this function saved a ton of execution
     * time. Usually the probe is calculated based on the key, but by caching
     * the hash outside this function, it's much faster.
     * 
     * @param hash
     *            the hash to calculate probe based on
     * @return the probe step width for the given hash
     */
    private int probeHash(int hash) {
        return 1 + (hash % (data.length * 2 / 3));
    }

    /**
     * Calculates the load factor (occupied / capacity).
     * 
     * @return the load factor [0-1]
     */
    private double getLoadFactor() {
        return size() / (double) data.length;
    }

    /**
     * Checks if there was a hash collision.
     * 
     * @param index
     *            the index to check
     * @param key
     *            the key to check against
     * @return true, if index is not supposed to be pointed to by key
     */
    private boolean checkHashCollisionOn(int index, String key) {
        return data[index] != null && !data[index].deleted
                && !data[index].key.equals(key);
    }

    /**
     * Returns the index in the HashTable where the value associated with key is
     * stored or supposed to be stored.
     * 
     * @param key
     *            the key to check
     * @return the index key points to in the HashTable.
     */
    private int getIndexForKey(String key) {
        int index = hash(key);
        int hash = index; // cache original hash for probeHash()
        while (checkHashCollisionOn(index, key)) {
            index = (index + probeHash(hash)) % data.length;
        }
        return index;
    }

    /**
     * Returns an OptionalInt that contains the index pointed to by key, if one
     * such index exists.
     * 
     * @param key
     *            the key to check.
     * @return empty Optional if key doesn't exist in HashTable, otherwise index
     *         pointed to by key wrapped in an Optional.
     */
    private OptionalInt indexOf(String key) {
        int index = getIndexForKey(key);

        if (data[index] == null || data[index].deleted) {
            return OptionalInt.empty();
        } else {
            return OptionalInt.of(index);
        }
    }
}
