/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

import java.io.File;

/**
 * A factory that knows how to generate a Graph from a CSV File
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class CSVGraphFactory implements AbstractGraphFactory<String> {
	/**
	 * The CSV File to read and generate the Graph from
	 */
	private File csv;

	public CSVGraphFactory(String csv) {
		this(new File(csv));
	}

	public CSVGraphFactory(File csv) {
		this.csv = csv;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see adp.aufgabe7.AbstractGraphFactory#buildGraph()
	 */
	@Override
	public Graph<String> buildGraph() {
		// TODO Auto-generated method stub
		return null;
	}

}
