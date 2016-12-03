/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */
package adp.aufgabe07;

/**
 * Interface for a Factory that can build Graphs
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public interface AbstractGraphFactory<T> {
	/**
	 * Builds a Graph
	 * 
	 * @return the Graph
	 */
	public Graph<T> buildGraph();
}
