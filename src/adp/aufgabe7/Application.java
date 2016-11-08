/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.11.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe7;

import java.util.Set;

/**
 * Application
 *
 * @author Moritz Höwer
 * @version 1.0 - 08.11.2016
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DijkstraPerformanceEvaluator("Keinen Bock mehr".hashCode())
                .performEvaluation();
        /*
         * AbstractGraphFactory<Integer> factory = new RandomGraphFactory(10,
         * "Hallo".hashCode());
         * 
         * Graph<Integer> graph = factory.buildGraph();
         * 
         * graph.getAllNodes().forEach(n -> { System.out.print(n + " ==> ");
         * graph.getConnectionsFrom(n).forEach(c -> {
         * System.out.print(c.getEnd() + "(" + c.getWeight() + ") "); });
         * System.out.println(); });
         * 
         * System.out.println(); System.out.println();
         * 
         * Set<DijkstraNode<Integer>> set = Dijkstra.dijkstra(graph, new
         * Node<>(0)); set.forEach(Application::printNode);
         */

        /*
         * System.out.println("\nREMOVING NODE\n");
         * 
         * Node<Integer> toRemove = new Node<>(5); graph.removeNode(toRemove);
         * 
         * graph.getAllNodes().forEach(n -> { System.out.print(n + " ==> ");
         * graph.getConnectionsFrom(n).forEach(c -> {
         * System.out.print(c.getEnd() + "(" + c.getWeight() + ") "); });
         * System.out.println(); });
         * 
         * System.out.println("\nREMOVING CON\n");
         * 
         * Connection<Integer> cToRemove = new Connection<>(new Node<>(0), new
         * Node<>(9), 0); graph.removeConnection(cToRemove);
         * 
         * graph.getAllNodes().forEach(n -> { System.out.print(n + " ==> ");
         * graph.getConnectionsFrom(n).forEach(c -> {
         * System.out.print(c.getEnd() + "(" + c.getWeight() + ") "); });
         * System.out.println(); });
         */
    }

    private static void printNode(DijkstraNode<?> node) {
        System.out.print(node.getValue() + " (" + node.getWeight() + ")");
        while (node.getPrevious() != null) {
            node = node.getPrevious();
            System.out.print(
                    " ==> " + node.getValue() + " (" + node.getWeight() + ")");
        }
        System.out.println();
    }

}
