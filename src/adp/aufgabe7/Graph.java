/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.util.Set;

/**
 * Interface for a Graph
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public interface Graph<T> {

	/**
	 * Inserts a new Node into the Graph
	 * 
	 * @param node
	 *            the node to insert
	 * @param neighbours
	 *            the neighbours of the node
	 */
	void insertNode(Node<T> node, Set<Node<T>> neighbours);

	/**
	 * Removes a Node from the Graph
	 * 
	 * @param node
	 *            the Node to remove
	 */
	void removeNode(Node<T> node);

	/**
	 * Retrieves a Set of all the neighbours of the specified Node
	 * 
	 * @param node
	 *            the Node to check
	 * @return a Set of the neighbours of the Node
	 */
	Set<Node<T>> getNeighbours(Node<T> node);

	/**
	 * Retrieves a Set of all the Nodes in the Graph
	 * 
	 * @return all the Nodes in the Graph
	 */
	Set<Node<T>> getAllNodes();
}
