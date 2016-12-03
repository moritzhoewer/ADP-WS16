/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 09.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

import adp.aufgabe01.ArrayList;
import adp.aufgabe01.List;

/**
 * Extends ArrayList from Aufgabe 1 so that elements are sorted upon insertion
 *
 * @author Moritz Höwer
 * @version 1.0 - 09.11.2016
 */
public class SortedArrayList<T extends Comparable<T>> extends ArrayList<T> {

    /**
     * Inserts a value into the List at the correct position
     * 
     * @param value the value to be inserted
     */
    @SuppressWarnings("unchecked")
    public void insertSorted(T value) {
        T current;
        int i;
        for (i = 0; i < count; i++) {
            current = (T) data[i];
            if (current.compareTo(value) > 0) {
                // current is bigger ==> insert before
                break;
            }
        }
        super.insert(i, value);
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.ArrayList#insert(int, java.lang.Object)
     */
    @Override
    public List<T> insert(int index, T value) {
        throw new UnsupportedOperationException(
                "Please use insertSorted(T value)!");
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.ArrayList#concat(adp.aufgabe1.List)
     */
    @Override
    public List<T> concat(List<T> other) {
        // append all elements from the other list
        for (int i = 0; i < other.size(); i++) {
            insertSorted(other.retrieve(i));
        }

        return this;
    }

    /**
     * Returns the first (smallest) element
     * 
     * @return the smallest element
     */
    @SuppressWarnings("unchecked")
    public T first() {
        if (count == 0) {
            throw new IllegalStateException("List is empty!");
        }

        return (T) data[0];
    }

    /**
     * Helper to check for empty List
     * 
     * @return true, if there are no Elements in the List
     */
    public boolean isEmpty() {
        return size() == 0;
    }

}
