/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

/**
 * A Factory that knows how to construct a random Graph
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class RandomGraphFactory implements AbstractGraphFactory<Integer> {

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
		// TODO Auto-generated method stub
		return null;
	}

}
