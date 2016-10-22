/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1;

import java.util.OptionalInt;

import adp.util.Counter;

/**
 * An implementation for a list based on linked objects
 *
 * @author Moritz Höwer
 * @version 1.1 - 22.10.2016
 */
public class LinkedList<T> implements List<T> {

    /**
     * reference to the first element
     */
    protected LinkedNode<T> first;

    /**
     * the counter to collect data about operations
     */
    private Counter counter;

    /**
     * caches the size of the list
     */
    private int count;

    public LinkedList() {
        this(new Counter());
    }

    public LinkedList(Counter counter) {
        first = null;
        this.counter = counter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#insert(int, java.lang.Object)
     */
    @Override
    public List<T> insert(int index, T value) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (index < 0) {
            // index is invalid
            throw new IndexOutOfBoundsException("Index is invalid!");
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        // insert first element
        if (first == null) {

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            if (index == 0) {
                first = new LinkedNode<T>(value);

                // update size
                count++;

                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER

            } else {
                // index is invalid
                throw new IndexOutOfBoundsException("Index is invalid!");
            }
        } else {
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            if (index == 0) {
                // insert as first element
                first = new LinkedNode<T>(value, first);

                // update size
                count++;

                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER

            } else {

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER

                LinkedNode<T> current = first;

                // traverse list until we get to the element before the desired
                // index
                while (index > 1) {

                    // PERFORMANCE COUNTER
                    counter.increment();
                    // PERFORMANCE COUNTER

                    if (current.isStopElement()) {
                        // index is invalid
                        throw new IndexOutOfBoundsException("Index is invalid");
                    }
                    current = current.getNextNode();
                    index--;

                    // PERFORMANCE COUNTER
                    counter.incrementBy(2);
                    // PERFORMANCE COUNTER

                }
                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER

                // insert
                current.setNextNode(
                        new LinkedNode<T>(value, current.getNextNode()));

                // update size
                count++;

                // PERFORMANCE COUNTER
                counter.incrementBy(3);
                // PERFORMANCE COUNTER
            }
        }
        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#delete(int)
     */
    @Override
    public List<T> delete(int index) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (index < 0 || first == null) {
            // index is invalid
            throw new IndexOutOfBoundsException("Index is invalid!");
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (index == 0) {
            // delete first
            first = first.getNextNode();

            // update size
            count--;

            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER
        } else {
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
            if (first.isStopElement() && index != 0) {
                // index is invalid
                throw new IndexOutOfBoundsException("Index is invalid!");
            }

            LinkedNode<T> current = first;
            // traverse to the index before
            while (index > 1) {

                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER

                if (current.getNextNode().isStopElement()) {
                    // index is invalid
                    throw new IndexOutOfBoundsException("Index is invalid!");
                }
                current = current.getNextNode();
                index--;

                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            current.setNextNode(current.getNextNode().getNextNode());

            // update size
            count--;

            // PERFORMANCE COUNTER
            counter.incrementBy(4);
            // PERFORMANCE COUNTER
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#find(java.lang.Object)
     */
    @Override
    public OptionalInt find(T value) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        if (first == null) {
            return OptionalInt.empty();
        }
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        if (first.getValue().equals(value)) {

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            return OptionalInt.of(0);
        } else {
            LinkedNode<T> current = first;
            // traverse to the end
            int index = 0;

            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

            while (!current.isStopElement()) {
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER

                if (current.getValue().equals(value)) {
                    // PERFORMANCE COUNTER
                    counter.increment();
                    // PERFORMANCE COUNTER
                    return OptionalInt.of(index);
                }
                current = current.getNextNode();
                index++;
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
        }

        return OptionalInt.empty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#retrieve(int)
     */
    @Override
    public T retrieve(int index) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (index < 0 || first == null) {
            // invalid index
            throw new IndexOutOfBoundsException("Index is invalid!");
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        LinkedNode<T> current = first;
        // traverse list to position index
        while (index > 0) {
            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

            if (current.isStopElement()) {
                // invalid index
                throw new IndexOutOfBoundsException("Index is invalid!");
            }
            current = current.getNextNode();
            index--;

            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

        }

        // PERFORMANCE COUNTER
        counter.incrementBy(2);
        // PERFORMANCE COUNTER

        return current.getValue();

    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#concat(adp.aufgabe1.List)
     */
    @Override
    public List<T> concat(List<T> other) {
        // append all elements from the other list
        for (int i = 0; i < other.size(); i++) {
            insert(size(), other.retrieve(i));
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#size()
     */
    @Override
    public int size() {

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        return count;
    }

}
