/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */
package adp.aufgabe1.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe1.NodeWithPositions;

/**
 * Test for the {@link NodeWithPositions} class.
 *
 * @author Moritz Höwer
 * @version 1.0 - 21.09.2016
 */
public class TestNodeWithPositions {

	/**
	 * Test method for {@link adp.aufgabe1.NodeWithPositions#isStopElement()}.
	 */
	@Test
	public void testIsStopElement() {
		NodeWithPositions<String> elem1 = new NodeWithPositions<>("Hallo", 0, 0);
		NodeWithPositions<String> elem2 = new NodeWithPositions<>();

		assertThat("Should not be a stop element", elem1.isStopElement(), is(false));
		assertThat("Should be a stop element", elem2.isStopElement(), is(true));
	}

}
