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
 * @version 1.0 - 21.09.2016
 */
public class LinkedNode<T> extends Node<T> {

	/**
	 * pointer to the next element
	 */
	private LinkedNode<T> nextElement;

	/**
	 * creates a stop element
	 * 
	 * @param value the value to be stored
	 */
	public LinkedNode(T value) {
		this(value, null);
	}
	
	public LinkedNode(T value, LinkedNode<T> nextElement) {
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

	public LinkedNode<T> getNextElement() {
		return nextElement;
	}

	public void setNextElement(LinkedNode<T> nextElement) {
		this.nextElement = nextElement;
	}
}
