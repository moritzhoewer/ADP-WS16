/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe01;

/**
 * Abstract base class for wrapping list elements
 *
 * @author Moritz Höwer
 * @version 1.0 - 21.09.2016
 */
public class Node<T> {

	/**
	 * the value wrapped by this element
	 */
	private final T value;

	public Node(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
