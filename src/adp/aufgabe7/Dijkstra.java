/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.util.*;

/**
 * Class that hosts Dijkstras algorithm
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
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
     * @return A Set of all the Graphs Nodes as DijkstraNodes containgin the
     *         shortest path to start
     */
    public static <T> Set<DijkstraNode<T>> dijkstra(Graph<T> graph,
            Node<T> start) {
        SortedSet<DijkstraNode<T>> buffer = new TreeSet<>();
        Set<DijkstraNode<T>> result = new HashSet<>();

        buffer.add(new DijkstraNode<>(start, 0));

        while (!buffer.isEmpty()) {
            DijkstraNode<T> shortest = buffer.first();
            boolean isDone = true;

            for (Connection<T> c : graph.getConnectionsFrom(shortest)) {
                DijkstraNode<T> current = new DijkstraNode<>(c.getEnd(),
                        shortest, shortest.getWeight() + c.getWeight());
                
                if (result.contains(current)) {
                    // already done
                    continue;
                }
                
                // check if it is pending
                Iterator<DijkstraNode<T>> it = buffer.iterator();

                boolean doInsert = true;

                while (it.hasNext()) {
                    DijkstraNode<T> temp = it.next();
                    if (current.equals(temp)) {
                        if (current.compareTo(temp) < 0) {
                            // current is shorter
                            // ==> remove temp and quit loop
                            it.remove();
                            break;
                        } else {
                            // current is longer
                            // ==> throw it away
                            doInsert = false;
                            break;
                        }
                    }
                }
                if (doInsert) {
                    // insert current
                    buffer.add(current);
                    isDone = false;
                }
            }
            if (isDone) {
                buffer.removeIf(e -> e.equals(shortest));
                result.add(shortest);
            }
        }

        return result;
    }

}
