/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

/**
 * Extention to Node for Dijkstras Algorithm
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class DijkstraNode<T> extends Node<T> {

	private DijkstraNode<T> previous;

	public DijkstraNode(T value) {
		this(value, null);
	}

	public DijkstraNode(T value, DijkstraNode<T> previous) {
		super(value);
		this.previous = previous;
	}

	/**
	 * @return the previous
	 */
	public DijkstraNode<T> getPrevious() {
		return previous;
	}

	/**
	 * @param previous
	 *            the previous to set
	 */
	public void setPrevious(DijkstraNode<T> previous) {
		this.previous = previous;
	}

}
