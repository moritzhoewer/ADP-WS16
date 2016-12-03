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
 * Wraps a value with information about indices of preceeding and following
 * elements
 *
 * @author Moritz Höwer
 * @version 1.0 - 21.09.2016
 */
public class NodeWithPositions<T> extends Node<T> {

	/**
	 * the index of the ElementWithPosition before this
	 */
	private int previousIndex;

	/**
	 * the index of the after this
	 */
	private int nextIndex;

	/**
	 * creates a stop element
	 */
	public NodeWithPositions() {
		this(null, -1, -1);
	}

	public NodeWithPositions(T value, int previousIndex, int nextIndex) {
		super(value);
		this.previousIndex = previousIndex;
		this.nextIndex = nextIndex;
	}

	/**
	 * checks whether this element is a stop element
	 * 
	 * @return whether or not this is a stop element
	 */
	public boolean isStopElement() {
		// stop element means this is the last element and it doesn't point to a
		// next one
		return nextIndex == -1;
	}

	public int getPreviousIndex() {
		return previousIndex;
	}

	public void setPreviousIndex(int previousIndex) {
		this.previousIndex = previousIndex;
	}

	public int getNextIndex() {
		return nextIndex;
	}

	public void setNextIndex(int nextIndex) {
		this.nextIndex = nextIndex;
	}

}
