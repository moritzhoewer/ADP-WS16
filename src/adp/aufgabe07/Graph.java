/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

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
     */
    void insertNode(Node<T> node);

    /**
     * Removes a Node from the Graph
     * 
     * @param node
     *            the Node to remove
     */
    void removeNode(Node<T> node);

    /**
     * Inserts a new connection into the Graph.
     * 
     * start and end Nodes must be part of the Graph and weight must be positive
     * or zero
     * 
     * @param start
     *            the start of the connection
     * @param end
     *            the end of the connection
     * @param weight
     *            the weight of the connection
     * @throws IllegalArgumentException
     *             if start or end are not part of the Graph, or if weight is
     *             negative
     */
    void insertConnection(Node<T> start, Node<T> end, int weight);


    /**
     * Removes a connection from the Graph.
     * 
     * errors are silently ignored
     * 
     * @param start
     *            the start of the connection
     * @param end
     *            the end of the connection
     */
    void removeConnection(Node<T> start, Node<T> end);

    /**
     * Retrieves a Set of all connections to the neighbours of the specified
     * Node
     * 
     * @param node
     *            the Node to check
     * @return a Set of the neighbours of the Node
     */
    Set<Connection<T>> getConnectionsFrom(Node<T> node);

    /**
     * Retrieves a Set of all the Nodes in the Graph
     * 
     * @return all the Nodes in the Graph
     */
    Set<Node<T>> getAllNodes();
}
