/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1;

import java.util.OptionalInt;

import adp.util.Counter;

/**
 * An implementation for a double linked list based on an array
 *
 * @author Moritz Höwer
 * @version 1.0 - 21.09.2016
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

        if (listIndex > count / 2) {
            // search from the back
            // get last
            NodeWithPositions<T> current = get(indexOfLast);

            int currentIndex = indexOfLast;

            // go backwards to list index
            for (int i = count - 1; i > listIndex; i--) {
                currentIndex = current.getPreviousIndex();
                current = get(currentIndex);
            }

            return currentIndex;
        } else {
            // search from the front
            // get first
            NodeWithPositions<T> current = get(indexOfFirst);

            int currentIndex = indexOfFirst;

            // go through elements to list index
            for (int i = 0; i < listIndex; i++) {
                currentIndex = current.getNextIndex();
                current = get(currentIndex);
            }

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
        if (index < 0 || index > count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }
        if (count == data.length) {
            // we need a new array
            Object[] temp = new Object[(int) (data.length * GROWTH_FACTOR)];

            // copy elements to new array
            System.arraycopy(data, 0, temp, 0, data.length);

            data = temp;

        }
        // insert new value
        NodeWithPositions<T> newElement;

        if (count == 0) {
            // this is the first element
            newElement = new NodeWithPositions<>(value, -1, -1);

            // update internal pointers
            indexOfFirst = 0;
            indexOfLast = 0;
        } else if (index == count) {
            // get last element
            NodeWithPositions<T> lastElement = get(indexOfLast);

            // last element is now not last anymore and points to new element
            lastElement.setNextIndex(count);

            // create new element pointing back at previous last element
            newElement = new NodeWithPositions<>(value, indexOfLast, -1);

            // update internal pointer
            indexOfLast = count;

        } else if (index == 0) {
            // get first element
            NodeWithPositions<T> firstElement = get(indexOfFirst);

            // last element is now not last anymore and points to new element
            firstElement.setPreviousIndex(count);

            // create new element pointing back at previous last element
            newElement = new NodeWithPositions<>(value, -1, indexOfFirst);

            // update internal pointer
            indexOfFirst = count;
        } else {
            // get element currently at my position (will be moved behind me)
            int indexOfCurrentElementAtMyPosition = listIndexToArrayPosition(
                    index);
            NodeWithPositions<T> currentElementAtMyPosition = get(
                    indexOfCurrentElementAtMyPosition);

            // get the index of the element before me
            int indexOfElementBeforeMe = currentElementAtMyPosition
                    .getPreviousIndex();

            // create new element in between the two existing ones
            newElement = new NodeWithPositions<>(value, indexOfElementBeforeMe,
                    indexOfCurrentElementAtMyPosition);

            // change pointers
            currentElementAtMyPosition.setPreviousIndex(count);
            get(indexOfElementBeforeMe).setNextIndex(count);
        }

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
        if (index < 0 || index >= count) {
            // index is invalid
            throw new IndexOutOfBoundsException(
                    "Index must be between 0 and " + count);
        }

        if (index == count - 1) {
            // delete last
            NodeWithPositions<T> toBeDeleted = get(indexOfLast);

            indexOfLast = toBeDeleted.getPreviousIndex();
            if (indexOfLast == -1) {
                // last element was deleted
                indexOfFirst = -1;
            } else {
                // unlink from element before
                get(toBeDeleted.getPreviousIndex()).setNextIndex(-1);
            }
        } else {
            int arrayIndexToDelete;
            if (index == 0) {
                // delete first
                arrayIndexToDelete = indexOfFirst;
                NodeWithPositions<T> toBeDeleted = get(arrayIndexToDelete);

                indexOfFirst = toBeDeleted.getNextIndex();
                if (indexOfFirst == -1) {
                    // last element was deleted
                    indexOfLast = -1;

                    // quit the method
                    count--;
                    return this;
                } else {
                    // unlink from element behind
                    get(toBeDeleted.getNextIndex()).setPreviousIndex(-1);
                }
            } else {
                // get array position of element to be deleted
                arrayIndexToDelete = listIndexToArrayPosition(index);

                // link element before and after
                NodeWithPositions<T> toBeDeleted = get(arrayIndexToDelete);

                // fix pointers
                get(toBeDeleted.getPreviousIndex())
                        .setNextIndex(toBeDeleted.getNextIndex());

                get(toBeDeleted.getNextIndex())
                        .setPreviousIndex(toBeDeleted.getPreviousIndex());

            }
            // move last element to position that was freed
            NodeWithPositions<T> last = get(count - 1);
            data[arrayIndexToDelete] = last;

            // if the last array element was actually the last element in the
            // list as well
            if (indexOfLast == count - 1) {
                // correct last element pointer
                indexOfLast = arrayIndexToDelete;
            }
            // if the last array element was actually the first element in the
            // list as well
            if (indexOfFirst == count - 1) {
                // correct last element pointer
                indexOfFirst = arrayIndexToDelete;
            }

            // fix pointers
            if (last.getPreviousIndex() != -1) {
                get(last.getPreviousIndex()).setNextIndex(arrayIndexToDelete);
            }
            if (last.getNextIndex() != -1) {
                get(last.getNextIndex()).setPreviousIndex(arrayIndexToDelete);
            }

        }
        // fix the count
        count--;

        if (count < data.length / 2) {
            // free up space again
            Object[] temp = new Object[(int) (data.length / GROWTH_FACTOR)];

            // copy elements to new array
            System.arraycopy(data, 0, temp, 0, count);

            // make data point to new array
            data = temp;
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
        if (count == 0) {
            // list is empty
            return OptionalInt.empty();
        }

        // get first element
        int currentIndex = indexOfFirst;
        NodeWithPositions<T> current = get(currentIndex);
        if (current.getValue().equals(value)) {
            return OptionalInt.of(currentIndex);
        } else {
            while (!current.isStopElement()) {

                currentIndex = current.getNextIndex();
                current = get(currentIndex);

                if (current.getValue().equals(value)) {
                    return OptionalInt.of(currentIndex);
                }
            }
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
        return count;
    }

}
