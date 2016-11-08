/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.util.HashSet;
import java.util.Set;

/**
 * An Implementation for a Graph based on a Adjazenzmatrix
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class AdjazenzmatrixGraph<T> implements Graph<T> {

	/**
	 * All the Nodes in this graph
	 */
	private Set<Node<T>> nodes;

	/**
	 * Matrix of connections
	 */
	private boolean[][] matrix;

	public AdjazenzmatrixGraph() {
		nodes = new HashSet<>();
		matrix = new boolean[0][0];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adp.aufgabe7.Graph#insertNode(adp.aufgabe7.Node, java.util.List)
	 */
	@Override
	public void insertNode(Node<T> node, Set<Node<T>> neighbours) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adp.aufgabe7.Graph#removeNode(adp.aufgabe7.Node)
	 */
	@Override
	public void removeNode(Node<T> node) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adp.aufgabe7.Graph#getNeighbours(adp.aufgabe7.Node)
	 */
	@Override
	public Set<Node<T>> getNeighbours(Node<T> node) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adp.aufgabe7.Graph#getAllNodes()
	 */
	@Override
	public Set<Node<T>> getAllNodes() {
		return nodes;
	}

}
