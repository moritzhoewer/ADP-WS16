/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1;

/**
 * Abstract base class for wrapping list elements
 *
 * @author Moritz Höwer
 * @date 17.09.2016
 * @version 1.0
 */
public class Element<T> {

	/**
	 * the value wrapped by this element
	 */
	private final T value;

	public Element(T value) {
		this.value = value;
	}

	public T getValue() {
		return value;
	}
}
