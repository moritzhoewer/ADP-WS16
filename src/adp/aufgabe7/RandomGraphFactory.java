/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.util.Random;

/**
 * A Factory that knows how to construct a random Graph
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class RandomGraphFactory implements AbstractGraphFactory<Integer> {

    /**
     * the maximum weight of random connections
     */
    private static final int MAX_WEIGHT = 20; 
    
	/**
	 * the size of the Graph to be generated
	 */
	private int size;
	
	/**
	 * the to use when generating the Graph
	 */
	private long seed;
	
	public RandomGraphFactory(int size, long seed) {
		this.size = size;
		this.seed = seed;
	}



	/* (non-Javadoc)
	 * @see adp.aufgabe7.AbstractGraphFactory#buildGraph()
	 */
	@Override
	public Graph<Integer> buildGraph() {
		Graph<Integer> result = new AdjazenzlisteGraph<>();
		Random rand = new Random(seed);
		
		for (int i = 0; i < size; i++) {
            result.insertNode(new Node<>(i));
        }
		
		result.getAllNodes().forEach(n -> {
		    result.getAllNodes().forEach(n2 -> {
		        /*if(rand.nextBoolean()){*/
		            result.insertConnection(n, n2, rand.nextInt(MAX_WEIGHT));
		        /*}*/
		    });
		    
		});
		
		return result;
	}

}
