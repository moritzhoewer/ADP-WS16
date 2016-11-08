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
 * A Node in a Graph
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class Node<T> {

	/**
	 * the value wrapped in this node
	 */
	private final T value;

	/**
	 * the weight of this node
	 */
	private int weight;

	public Node(T value) {
		this.value = value;
		weight = 0;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight
	 *            the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	/**
	 * @return the value
	 */
	public T getValue() {
		return value;
	}

}
