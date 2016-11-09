/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * An implementation for a Graph based on an Adjazenzlist
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class AdjazenzlisteGraph<T> implements Graph<T> {

    /**
     * All the nodes in this Graph
     */
    private Map<Node<T>, Set<Connection<T>>> nodes;

    public AdjazenzlisteGraph() {
        nodes = new HashMap<>();
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#insertNode(adp.aufgabe7.Node, java.util.Set)
     */
    @Override
    public void insertNode(Node<T> node) {
        nodes.put(node, new HashSet<>());
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#removeNode(adp.aufgabe7.Node)
     */
    @Override
    public void removeNode(Node<T> node) {
        nodes.remove(node);

        // Remove all Connections that point to node
        nodes.forEach((n, set) -> {
            set.removeIf(c -> c.getEnd().equals(node));
        });
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#insertConnection(adp.aufgabe7.Node,
     * adp.aufgabe7.Node, int)
     */
    @Override
    public void insertConnection(Node<T> start, Node<T> end, int weight) {
        if (!nodes.containsKey(start)) {
            throw new IllegalArgumentException(
                    "Starting Node is not part of the Graph!");
        }
        if (!nodes.containsKey(end)) {
            throw new IllegalArgumentException(
                    "End Node is not part of the Graph!");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must not be negative!");
        }

        // add connection
        nodes.get(start).add(new Connection<>(start, end, weight));

    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#removeConnection(adp.aufgabe7.Node,
     * adp.aufgabe7.Node)
     */
    @Override
    public void removeConnection(Node<T> start, Node<T> end) {
        Connection<T> connection = new Connection<>(start, end, 0);
        Set<?> set = nodes.get(start);
        if (set != null) {
            set.remove(connection);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#getConnectionsFrom(adp.aufgabe7.Node)
     */
    @Override
    public Set<Connection<T>> getConnectionsFrom(Node<T> node) {
        return nodes.get(node);
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.Graph#getAllNodes()
     */
    @Override
    public Set<Node<T>> getAllNodes() {
        return nodes.keySet();
    }

}
