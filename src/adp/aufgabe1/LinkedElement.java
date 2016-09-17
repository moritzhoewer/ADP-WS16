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
 * Wraps a value with information about preceeding and following element
 *
 * @author Moritz Höwer
 * @date 17.09.2016
 * @version 1.0
 */
public class LinkedElement<T> extends Element<T> {

	/**
	 * pointer to the next element
	 */
	private LinkedElement<T> nextElement;

	/**
	 * creates a stop element
	 */
	public LinkedElement(T value) {
		this(value, null);
	}
	
	public LinkedElement(T value, LinkedElement<T> nextElement) {
		super(value);
		this.nextElement = nextElement;
	}

	/**
	 * checks whether this element is a stop element
	 * 
	 * @return whether or not this is a stop element
	 */
	public boolean isStopElement() {
		// stop element means there is nothing behind this
		return nextElement == null;
	}

	public LinkedElement<T> getNextElement() {
		return nextElement;
	}

	public void setNextElement(LinkedElement<T> nextElement) {
		this.nextElement = nextElement;
	}
}
