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
 * @version 1.0 - 24.09.2016
 */
public class LinkedList<T> implements List<T> {

	/**
	 * reference to the first element
	 */
	private LinkedNode<T> first;

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
		if (index < 0) {
			// index is invalid
			throw new IndexOutOfBoundsException("Index is invalid!");
		}
		// insert first element
		if (first == null) {
			if (index == 0) {
				first = new LinkedNode<T>(value);

				// update size
				count++;

				// PERFORMANCE COUNTER
				counter.increment();
				// PERFORMANCE COUNTER

			} else {
				// index is invalid
				throw new IndexOutOfBoundsException("Index is invalid!");
			}
		} else if (index == 0) {
			// insert as first element
			first = new LinkedNode<T>(value, first);

			// update size
			count++;

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER

		} else {
			LinkedNode<T> current = first;

			// traverse list until we get to the element before the desired
			// index
			while (index > 1) {
				if (current.isStopElement()) {
					// index is invalid
					throw new IndexOutOfBoundsException("Index is invalid");
				}
				current = current.getNextNode();
				index--;

				// PERFORMANCE COUNTER
				counter.increment();
				// PERFORMANCE COUNTER

			}

			// insert
			current.setNextNode(
					new LinkedNode<T>(value, current.getNextNode()));

			// update size
			count++;

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER
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
		if (index < 0 || first == null) {
			// index is invalid
			throw new IndexOutOfBoundsException("Index is invalid!");
		}

		if (index == 0) {
			// delete first
			first = first.getNextNode();

			// update size
			count--;

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER
		} else {
			if (first.isStopElement() && index != 0) {
				// index is invalid
				throw new IndexOutOfBoundsException("Index is invalid!");
			}

			LinkedNode<T> current = first;
			// traverse to the index before
			while (index > 1) {
				if (current.getNextNode().isStopElement()) {
					// index is invalid
					throw new IndexOutOfBoundsException("Index is invalid!");
				}
				current = current.getNextNode();
				index--;

				// PERFORMANCE COUNTER
				counter.increment();
				// PERFORMANCE COUNTER
			}
			current.setNextNode(current.getNextNode().getNextNode());

			// update size
			count--;

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
		if (first == null) {
			return OptionalInt.empty();
		}

		if (first.getValue().equals(value)) {
			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER

			return OptionalInt.of(0);
		} else {
			LinkedNode<T> current = first;
			// traverse to the end
			int index = 0;
			while (!current.isStopElement()) {
				// PERFORMANCE COUNTER
				counter.increment();
				// PERFORMANCE COUNTER

				if (current.getValue().equals(value)) {
					return OptionalInt.of(index);
				}
				current = current.getNextNode();
				index++;
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
		if (index < 0 || first == null) {
			// invalid index
			throw new IndexOutOfBoundsException("Index is invalid!");
		}
		LinkedNode<T> current = first;
		// traverse list to position index
		while (index > 0) {
			if (current.isStopElement()) {
				// invalid index
				throw new IndexOutOfBoundsException("Index is invalid!");
			}
			current = current.getNextNode();
			index--;

			// PERFORMANCE COUNTER
			counter.increment();
			// PERFORMANCE COUNTER

		}

		// PERFORMANCE COUNTER
		counter.increment();
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
