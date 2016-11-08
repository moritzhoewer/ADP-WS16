/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.11.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe7;

import adp.util.AbstractPerformanceEvaluator;

/**
 * Performance evaluator for Dijkstra
 *
 * @author Moritz Höwer
 * @version 1.0 - 08.11.2016
 */
public class DijkstraPerformanceEvaluator extends AbstractPerformanceEvaluator {

    /**
     * the maximum size for the Graphs
     */
    private static final int MAX_SIZE = 4000;

    /**
     * the seed for RNG
     */
    private long seed;

    public DijkstraPerformanceEvaluator(long seed) {
        this.seed = seed;
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.util.AbstractPerformanceEvaluator#performEvaluation()
     */
    @Override
    public void performEvaluation() {
        addKeyToMatlab("size");
        addKeyToMatlab("LIST");

        for (int i = 100; i < 1000; i += 100) {
            addValueToMatlab("size", i);

            System.out.print("Building Graph with " + i + " nodes...");
            Graph<Integer> g = new RandomGraphFactory(i, seed).buildGraph();
            System.out.println("done");
            
            System.out.print("Dijkstra...");
            long ts = System.nanoTime();
            Dijkstra.dijkstra(g, new Node<Integer>(0));
            long diff = System.nanoTime() - ts;
            addValueToMatlab("LIST", diff);
            System.out.println("done");
        }
        
        for (int i = 1000; i <= 4000; i += 1000) {
            addValueToMatlab("size", i);

            System.out.print("Building Graph with " + i + " nodes...");
            Graph<Integer> g = new RandomGraphFactory(i, seed).buildGraph();
            System.out.println("done");
            
            System.out.print("Dijkstra...");
            long ts = System.nanoTime();
            Dijkstra.dijkstra(g, new Node<Integer>(0));
            long diff = System.nanoTime() - ts;
            addValueToMatlab("LIST", diff);
            System.out.println("done");
        }
        
        printMatlab();

    }

}
