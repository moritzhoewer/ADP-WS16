/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.11.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe7;

import java.time.LocalTime;
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
        //performanceAnalysis();
        //handCheckDijkstra();
        csvCheckDijkstra();
    	/*System.out.println(LocalTime.now() + " ==> Start Building");
    	Graph<?> g = new RandomGraphFactory(10000, 87654334).buildGraph();
    	System.out.println(LocalTime.now() + " ==> Done Building");*/
    }

    /**
     * check Dijkstra with CSV
     */
    private static void csvCheckDijkstra() {
        AbstractGraphFactory<String> factory = new CSVGraphFactory(
                Application.class.getResource("stadtentfernungen_dt.csv")
                        .getFile());

        Graph<String> graph = factory.buildGraph();
        
        graph.getAllNodes().forEach(n -> {
            System.out.print(n + " ==> ");
            graph.getConnectionsFrom(n).forEach(c -> {
                System.out.print(c.getEnd() + "(" + c.getWeight() + ") ");
            });
            System.out.println();
        });

        System.out.println();
        System.out.println();

        Set<DijkstraNode<String>> set = Dijkstra.dijkstra(graph,
                new Node<>("Hamburg"));
        set.forEach(Application::printNode);
    }

    /**
     * check Dijkstra by hand with a small Graph
     */
    private static void handCheckDijkstra() {
         AbstractGraphFactory<Integer> factory = new RandomGraphFactory(10,
         "Hallo".hashCode());
        
         Graph<Integer> graph = factory.buildGraph();

        graph.getAllNodes().forEach(n -> {
            System.out.print(n + " ==> ");
            graph.getConnectionsFrom(n).forEach(c -> {
                System.out.print(c.getEnd() + "(" + c.getWeight() + ") ");
            });
            System.out.println();
        });

        System.out.println();
        System.out.println();

        // graph.removeConnection(new Node<>(5), new Node<>(6));
        // graph.removeNode(new Node<>(4));
        //
        // graph.getAllNodes().forEach(n -> {
        // System.out.print(n + " ==> ");
        // graph.getConnectionsFrom(n).forEach(c -> {
        // System.out.print(c.getEnd() + "(" + c.getWeight() + ") ");
        // });
        // System.out.println();
        // });

        // Set<DijkstraNode<Integer>> set = Dijkstra.dijkstra(graph,
        // new Node<>(0));
        // set.forEach(Application::printNode);

        Set<DijkstraNode<Integer>> set = Dijkstra.dijkstra(graph,
                new Node<>(0));
        set.forEach(Application::printNode);
    }

    /**
     * do performance analysis
     */
    private static void performanceAnalysis() {
        new DijkstraPerformanceEvaluator("Keinen Bock mehr".hashCode())
                .performEvaluation();
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
