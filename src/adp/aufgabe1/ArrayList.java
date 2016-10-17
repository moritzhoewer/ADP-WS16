/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1;

import java.util.OptionalInt;

import adp.util.Counter;

/**
 * An implementation for {@link adp.aufgabe1.List List} based on arrays
 *
 * @author Moritz Höwer
 * @version 1.0 - 24.09.2016
 */
public class ArrayList<T> implements List<T> {

    /**
     * initial size of the array
     */
    private static final int START_CAPACITY = 10;

    /**
     * the factor for expanding / shrinking
     */
    private static final double GROWTH_FACTOR = 1.5;

    /**
     * the array used for storing the data in this list
     */
    protected Object[] data;

    /**
     * the number of elements stored
     */
    protected int count;

    /**
     * the counter to collect data about operations
     */
    private Counter counter;

    public ArrayList() {
        this(new Counter());
    }

    public ArrayList(Counter counter) {
        count = 0;
        data = new Object[START_CAPACITY];
        this.counter = counter;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#insert(int, java.lang.Object)
     */
    @Override
    public List<T> insert(int index, T value) {
        if (index < 0 || index > count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        if (count == data.length) {
            // we need a new array
            Object[] temp = new Object[(int) (data.length * GROWTH_FACTOR)];

            // copy elements before the index to new array
            System.arraycopy(data, 0, temp, 0, index);

            // insert new element
            temp[index] = value;

            // copy elements after index
            System.arraycopy(data, index, temp, index + 1, data.length - index);

            // make data point to new array
            data = temp;

            // increase counter
            count++;

            // PERFORMANCE COUNTER
            counter.incrementBy(count);
            // PERFORMANCE COUNTER

        } else {
            if (index == count) {
                // nice! we can just append without needing to copy anything...
                data[index] = value;

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER
            } else {
                // damn, we need to copy
                for (int i = count; i > index; i--) {
                    // copy every element behind index one back, starting at the
                    // back
                    data[i] = data[i - 1];

                    // PERFORMANCE COUNTER
                    counter.increment();
                    // PERFORMANCE COUNTER
                }

                // insert new element
                data[index] = value;

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER
            }

            // increase counter
            count++;
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
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        count--;

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (index == count) {
            // deleting last item - nothing more to do
            if (count < data.length / 2) {
                // free up space again
                Object[] temp = new Object[(int) (data.length / GROWTH_FACTOR)];

                // copy elements to new array
                System.arraycopy(data, 0, temp, 0, count);

                // make data point to new array
                data = temp;

                // PERFORMANCE COUNTR
                counter.incrementBy(count);
                // PERFORMANCE COUNTER
            }
        } else {
            if (count < data.length / 2) {
                // free up space again
                Object[] temp = new Object[(int) (data.length / GROWTH_FACTOR)];

                // copy elements before the index to new array
                System.arraycopy(data, 0, temp, 0, index);

                // copy elements after index
                System.arraycopy(data, index + 1, temp, index, count - index);

                // make data point to new array
                data = temp;

                // PERFORMANCE COUNTR
                counter.incrementBy(count);
                // PERFORMANCE COUNTER
            } else {
                // move all elements behind index forward one
                for (int i = index; i < count; i++) {
                    data[i] = data[i + 1];

                    // PERFORMANCE COUNTER
                    counter.increment();
                    // PERFORMANCE COUNTER
                }
            }
        }

        return this;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#find(java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    @Override
    public OptionalInt find(T value) {
        for (int i = 0; i < count; i++) {

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            if (((T) data[i]).equals(value)) {
                // unchecked cast is safe because I know array has only "T"
                // objects
                return OptionalInt.of(i);
            }
        }
        return OptionalInt.empty();
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe1.List#retrieve(int)
     */
    @SuppressWarnings("unchecked")
    @Override
    public T retrieve(int index) {
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        // this unchecked cast is safe, because I know I only put objects of
        // type T into array
        return (T) data[index];
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
