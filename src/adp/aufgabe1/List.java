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

/**
 * Interface for a generic List
 * 
 * @author Moritz Höwer
 * @version 1.0 - 24.09.2016
 */
public interface List<T> {
	/**
	 * Inserts a new value into the list at a given index
	 * 
	 * @param index
	 *            the index at which to insert a new value
	 * @param value
	 *            the value to insert
	 * @return {@code this} for chaining
	 * @throws IndexOutOfBoundsException
	 *             when index is invalid
	 */
	public List<T> insert(int index, T value);

	/**
	 * Delete a value at a specific index
	 * 
	 * @param index
	 *            the index at which to delete
	 * @return {@code this} for chaining
	 * @throws IndexOutOfBoundsException
	 *             when index is invalid
	 */
	public List<T> delete(int index);

	/**
	 * Find a value in the list
	 * 
	 * @param value
	 *            the value to be searched for
	 * @return an {@link java.util.OptionalInt OptionalInt} that contains the
	 *         index or is empty if nothing was found
	 */
	public OptionalInt find(T value);

	/**
	 * Retrieves a value from the list
	 * 
	 * @param index
	 *            the index where to retrieve a value from
	 * @return the value
	 * @throws IndexOutOfBoundsException
	 *             when index is invalid
	 */
	public T retrieve(int index);

	/**
	 * Appends the content of another {@code List<T>} to {@code this}
	 * 
	 * @param other
	 *            the {@code List<T>} to be appended
	 * @return {@code this} with the contents of {@code other} appended
	 */
	public List<T> concat(List<T> other);

	/**
	 * Calculates the number of elements in this list
	 * 
	 * @return the number of elements
	 */
	public int size();

}
