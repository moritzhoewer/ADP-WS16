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
	private int[][] matrix;

	public AdjazenzmatrixGraph() {
		nodes = new HashSet<>();
		matrix = new int[0][0];
	}

    @Override
    public void insertNode(Node<T> node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeNode(Node<T> node) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertConnection(Connection<T> connection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void insertConnection(Node<T> start, Node<T> end, int weight) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeConnection(Connection<T> connection) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void removeConnection(Node<T> start, Node<T> end) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public Set<Connection<T>> getConnectionsFrom(Node<T> node) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Node<T>> getAllNodes() {
        return nodes;
    }
	
	
}
