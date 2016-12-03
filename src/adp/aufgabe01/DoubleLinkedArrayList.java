/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe01;

import java.util.OptionalInt;

import adp.util.Counter;

/**
 * An implementation for a double linked list based on an array
 *
 * @author Moritz Höwer
 * @version 1.1 - 22.10.2016
 */
public class DoubleLinkedArrayList<T> implements List<T> {

    /**
     * initial size of the array
     */
    private static final int START_CAPACITY = 10;

    /**
     * the factor for expanding / shrinking
     */
    private static final double GROWTH_FACTOR = 1.5;

    /**
     * array for data storage
     */
    private Object[] data;

    /**
     * stores how many elements are in the list
     */
    private int count;

    /**
     * the index of the first element
     */
    private int indexOfFirst;

    /**
     * the index of the last element
     */
    private int indexOfLast;

    /**
     * the counter to collect data about operations
     */
    private Counter counter;

    public DoubleLinkedArrayList() {
        this(new Counter());
    }

    public DoubleLinkedArrayList(Counter counter) {
        count = 0;
        indexOfFirst = -1;
        indexOfLast = -1;
        data = new Object[START_CAPACITY];
        data[0] = new NodeWithPositions<T>();
        this.counter = counter;
    }

    /**
     * helper method for getting {@link NodeWithPositions} from {@code Object[]}
     * 
     * @param index
     *            the index to retrieve
     * @return the element at that index
     */
    @SuppressWarnings("unchecked")
    private NodeWithPositions<T> get(int index) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        // unchecked cast is safe, because I know what's in data
        return (NodeWithPositions<T>) data[index];
    }

    /**
     * helper for converting list index to position in array
     * 
     * @param listIndex
     *            the index in list ordering
     * @return the index in array ordering
     */
    private int listIndexToArrayPosition(int listIndex) {
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        if (listIndex > count / 2) {
            // search from the back

            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

            // get last
            NodeWithPositions<T> current = get(indexOfLast);

            int currentIndex = indexOfLast;

            // go backwards to list index
            for (int i = count - 1; i > listIndex; i--) {
                currentIndex = current.getPreviousIndex();
                current = get(currentIndex);
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

            return currentIndex;
        } else {
            // search from the front

            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER

            // get first
            NodeWithPositions<T> current = get(indexOfFirst);

            int currentIndex = indexOfFirst;

            // go through elements to list index
            for (int i = 0; i < listIndex; i++) {
                currentIndex = current.getNextIndex();
                current = get(currentIndex);
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER
            return currentIndex;
        }

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

        if (index < 0 || index > count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (count == data.length) {

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            // we need a new array
            Object[] temp = new Object[(int) (data.length * GROWTH_FACTOR)];

            // copy elements to new array
            System.arraycopy(data, 0, temp, 0, data.length);

            data = temp;

            // PERFORMANCE COUNTER
            counter.incrementBy(count * 3 + 3);
            // PERFORMANCE COUNTER

        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        // insert new value
        NodeWithPositions<T> newElement;

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER

        if (count == 0) {
            // PERFORMANCE COUNTER
            counter.incrementBy(3);
            // PERFORMANCE COUNTER

            // this is the first element
            newElement = new NodeWithPositions<>(value, -1, -1);

            // update internal pointers
            indexOfFirst = 0;
            indexOfLast = 0;
        } else {
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
            if (index == count) {
                // get last element
                NodeWithPositions<T> lastElement = get(indexOfLast);

                // last element is now not last anymore and points to new
                // element
                lastElement.setNextIndex(count);

                // create new element pointing back at previous last element
                newElement = new NodeWithPositions<>(value, indexOfLast, -1);

                // update internal pointer
                indexOfLast = count;

                // PERFORMANCE COUNTER
                counter.incrementBy(3);
                // PERFORMANCE COUNTER

            } else {
                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER
                if (index == 0) {
                    // get first element
                    NodeWithPositions<T> firstElement = get(indexOfFirst);

                    // last element is now not last anymore and points to new
                    // element
                    firstElement.setPreviousIndex(count);

                    // create new element pointing back at previous last element
                    newElement = new NodeWithPositions<>(value, -1,
                            indexOfFirst);

                    // update internal pointer
                    indexOfFirst = count;
                    // PERFORMANCE COUNTER
                    counter.incrementBy(3);
                    // PERFORMANCE COUNTER

                } else {
                    // get element currently at my position (will be moved
                    // behind me)
                    int indexOfCurrentElementAtMyPosition = listIndexToArrayPosition(
                            index);
                    NodeWithPositions<T> currentElementAtMyPosition = get(
                            indexOfCurrentElementAtMyPosition);

                    // get the index of the element before me
                    int indexOfElementBeforeMe = currentElementAtMyPosition
                            .getPreviousIndex();

                    // create new element in between the two existing ones
                    newElement = new NodeWithPositions<>(value,
                            indexOfElementBeforeMe,
                            indexOfCurrentElementAtMyPosition);

                    // change pointers
                    currentElementAtMyPosition.setPreviousIndex(count);
                    get(indexOfElementBeforeMe).setNextIndex(count);

                    // PERFORMANCE COUNTER
                    counter.incrementBy(4);
                    // PERFORMANCE COUNTER
                }
            }
        }

        // PERFORMANCE COUNTER
        counter.incrementBy(2);
        // PERFORMANCE COUNTER

        data[count] = newElement;
        count++;
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
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        int arrayIndexToDelete = listIndexToArrayPosition(index);

        NodeWithPositions<T> toBeDeleted = get(arrayIndexToDelete);

        // remove by linking elements before and after
        if (toBeDeleted.getPreviousIndex() == -1) {
            // this was logically the first one
            // so new first is now the one after this
            indexOfFirst = toBeDeleted.getNextIndex();

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
        } else {
            // PERFORMANCE COUNTER
            counter.incrementBy(3);
            // PERFORMANCE COUNTER

            get(toBeDeleted.getPreviousIndex())
                    .setNextIndex(toBeDeleted.getNextIndex());
        }
        if (toBeDeleted.getNextIndex() == -1) {
            // this was logically the last one
            // so new last is now the one before this
            indexOfLast = toBeDeleted.getPreviousIndex();

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
        } else {
            // PERFORMANCE COUNTER
            counter.incrementBy(3);
            // PERFORMANCE COUNTER

            get(toBeDeleted.getNextIndex())
                    .setPreviousIndex(toBeDeleted.getPreviousIndex());
        }

        // PERFORMANCE COUNTER
        counter.incrementBy(3);
        // PERFORMANCE COUNTER

        if (arrayIndexToDelete != count - 1) {
            // we didn't delete the actual last one in the array
            // move last element to position that was freed
            NodeWithPositions<T> last = get(count - 1);
            data[arrayIndexToDelete] = last;
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
            // if the last array element was actually the last element in the
            // list as well
            if (indexOfLast == count - 1) {
                // correct last element pointer
                indexOfLast = arrayIndexToDelete;

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER
            }

            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
            // if the last array element was actually the first element in the
            // list as well
            if (indexOfFirst == count - 1) {
                // correct last element pointer
                indexOfFirst = arrayIndexToDelete;

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER
            }

            // fix pointers
            if (last.getPreviousIndex() != -1) {
                get(last.getPreviousIndex()).setNextIndex(arrayIndexToDelete);
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            if (last.getNextIndex() != -1) {
                get(last.getNextIndex()).setPreviousIndex(arrayIndexToDelete);
                // PERFORMANCE COUNTER
                counter.incrementBy(2);
                // PERFORMANCE COUNTER
            }
            // PERFORMANCE COUNTER
            counter.incrementBy(2);
            // PERFORMANCE COUNTER
        }
        
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        // fix the count
        count--;

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        if (count < data.length / 2) {
            // free up space again
            Object[] temp = new Object[(int) (data.length / GROWTH_FACTOR)];

            // copy elements to new array
            System.arraycopy(data, 0, temp, 0, count);

            // make data point to new array
            data = temp;

            // PERFORMANCE COUNTER
            counter.incrementBy(count * 3 + 3);
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
        if (count == 0) {
            // list is empty
            return OptionalInt.empty();
        }

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        // get first element
        int currentIndex = indexOfFirst;
        NodeWithPositions<T> current = get(currentIndex);

        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
        if (current.getValue().equals(value)) {
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
            return OptionalInt.of(currentIndex);
        } else {
            while (!current.isStopElement()) {

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER

                currentIndex = current.getNextIndex();
                current = get(currentIndex);

                // PERFORMANCE COUNTER
                counter.increment();
                // PERFORMANCE COUNTER

                if (current.getValue().equals(value)) {
                    // PERFORMANCE COUNTER
                    counter.increment();
                    // PERFORMANCE COUNTER
                    return OptionalInt.of(currentIndex);
                }
            }
            // PERFORMANCE COUNTER
            counter.increment();
            // PERFORMANCE COUNTER
        }
        // PERFORMANCE COUNTER
        counter.increment();
        // PERFORMANCE COUNTER
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
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        return get(listIndexToArrayPosition(index)).getValue();
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
