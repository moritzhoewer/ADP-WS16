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
	 * the factor to multiply current array size with when expanding
	 */
	private static final int GROWTH_FACTOR = 10;

	/**
	 * array for data storage
	 */
	private Object[] data;

	/**
	 * stores how many elements are in the list
	 */
	private int count;

	/**
	 * the counter to collect data about operations
	 */
	private Counter counter;

	public DoubleLinkedArrayList() {
		this(new Counter());
	}

	public DoubleLinkedArrayList(Counter counter) {
		count = 0;
		data = new Object[START_CAPACITY];
		data[0] = new NodeWithPositions<T>();
		this.counter = counter;
	}

	/**
	 * helper method for getting {@link NodeWithPositions} from
	 * {@code Object[]}
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
		// find last
		int currentIndex = count / 2;
		NodeWithPositions<T> current = get(currentIndex);

		while (!current.isStopElement()) {
			currentIndex = current.getNextIndex();
			current = get(currentIndex);

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER
		}

		// track back to listIndex
		for (int i = count - 1; i > listIndex; i--) {
			currentIndex = current.getPreviousIndex();
			current = get(currentIndex);

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER
		}

		return currentIndex;
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
			throw new IndexOutOfBoundsException("Index must be between 0 and " + count);
		}
		if (count == data.length) {
			// we need a new array
			Object[] temp = new Object[data.length * GROWTH_FACTOR];

			// copy elements to new array
			System.arraycopy(data, 0, temp, 0, data.length);

			data = temp;

			// PERFORMANCE COUNTER
			counter.incrementBy(count);
			// PERFORMANCE COUNTER

		}
		// insert new value
		NodeWithPositions<T> newElement;

		if (count == 0) {
			// this is the first element
			newElement = new NodeWithPositions<>(value, -1, -1);
		} else if (index == count) {
			// get last element
			int indexOfLastElement = listIndexToArrayPosition(count - 1);
			NodeWithPositions<T> lastElement = get(indexOfLastElement);

			// last element is now not last anymore and points to new element
			lastElement.setNextIndex(count);

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER

			// create new element pointing back at previous last element
			newElement = new NodeWithPositions<>(value, indexOfLastElement, -1);

		} else {
			// get element currently at my position (will be moved behind me)
			int indexOfCurrentElementAtMyPosition = listIndexToArrayPosition(index);
			NodeWithPositions<T> currentElementAtMyPosition = get(indexOfCurrentElementAtMyPosition);

			// get the index of the element before me
			int indexOfElementBeforeMe = currentElementAtMyPosition.getPreviousIndex();

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER

			// create new element in between the two existing ones
			newElement = new NodeWithPositions<>(value, indexOfElementBeforeMe, indexOfCurrentElementAtMyPosition);

			// change pointers
			currentElementAtMyPosition.setPreviousIndex(count);

			if (indexOfElementBeforeMe != -1) {
				// I am not at the beginning of the list
				get(indexOfElementBeforeMe).setNextIndex(count);
			}
		}

		// PERFORMANCE COUNTER
		counter.increment();
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
		if (index < 0 || index >= count) {
			// index is invalid
			throw new IndexOutOfBoundsException("Index must be between 0 and " + count);
		}

		// get array position of element to be deleted
		int arrayIndexToDelete = listIndexToArrayPosition(index);

		// link element before and after
		NodeWithPositions<T> toBeDeleted = get(arrayIndexToDelete);
		if (toBeDeleted.getPreviousIndex() != -1) {
			get(toBeDeleted.getPreviousIndex()).setNextIndex(toBeDeleted.getNextIndex());
		}
		if (toBeDeleted.getNextIndex() != -1) {
			get(toBeDeleted.getNextIndex()).setPreviousIndex(toBeDeleted.getPreviousIndex());
		}

		// move all elements behind that index forward one
		for (int i = arrayIndexToDelete; i < count - 1; i++) {
			data[i] = data[i + 1];

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER
		}

		// fix the count
		count--;

		// go through all elements and correct their pointers
		// everything that pointed to something bigger than arrayIndexToDelete
		// has to be changed to point one element further forward
		for (int i = 0; i < count; i++) {
			NodeWithPositions<T> current = get(i);
			if (current.getPreviousIndex() > arrayIndexToDelete) {
				current.setPreviousIndex(current.getPreviousIndex() - 1);
			}
			if (current.getNextIndex() > arrayIndexToDelete) {
				current.setNextIndex(current.getNextIndex() - 1);
			}

			// PERFORMANCE COUNTER
			counter.increment();
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
		if (count == 0) {
			// list is empty
			return OptionalInt.empty();
		}

		// get first element
		int currentIndex = listIndexToArrayPosition(0);
		NodeWithPositions<T> current = get(currentIndex);
		if (current.getValue().equals(value)) {
			return OptionalInt.of(currentIndex);
		} else {
			while (!current.isStopElement()) {

				// PERFORMANCE COUNTER
				counter.increment();
				// PERFORMANCE COUNTER

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
			throw new IndexOutOfBoundsException("Index must be between 0 and " + count);
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
		DoubleLinkedArrayList<T> newList = new DoubleLinkedArrayList<>(counter);

		// append all elements from this list
		for (int i = 0; i < size(); i++) {
			newList.insert(i, retrieve(i));
		}

		// append all elements from the other list
		for (int i = 0; i < other.size(); i++) {
			newList.insert(size() + i, other.retrieve(i));
		}

		return newList;
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
