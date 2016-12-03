/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 *          Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

import java.util.*;

/**
 * Class that hosts Dijkstras algorithm
 *
 * @author Moritz Höwer
 * @version 2.0 - 09.11.2016
 */
public class Dijkstra {

    /**
     * Calculates the shortest paths to all Nodes in the Graph, starting at
     * start using Dijkstras algorithm
     * 
     * @param graph
     *            the Graph to search
     * @param start
     *            the start Node
     * @return A Set of all the Graphs Nodes as DijkstraNodes containing the
     *         shortest path to start
     */
    public static <T> Set<DijkstraNode<T>> dijkstra(Graph<T> graph,
            Node<T> start) {
        SortedArrayList<DijkstraNode<T>> buffer = new SortedArrayList<>();
        Set<DijkstraNode<T>> result = new HashSet<>();

        buffer.insertSorted(new DijkstraNode<>(start, 0));

        while (!buffer.isEmpty()) {
            DijkstraNode<T> shortest = buffer.first();

            for (Connection<T> c : graph.getConnectionsFrom(shortest)) {
                DijkstraNode<T> current = new DijkstraNode<>(c.getEnd(),
                        shortest, shortest.getWeight() + c.getWeight());

                if (result.contains(current)) {
                    // already done
                    continue;
                }

                // check if it is pending
                OptionalInt index = buffer.find(current);
                if (index.isPresent()) {
                    int i = index.getAsInt();
                    if (current.compareTo(buffer.retrieve(i)) < 0) {
                        // current has shorter path
                        buffer.delete(i);
                        buffer.insertSorted(current);
                    }
                } else {
                    buffer.insertSorted(current);
                }

            }
            buffer.delete(buffer.find(shortest).getAsInt());
            result.add(shortest);
        }

        return result;
    }

}
