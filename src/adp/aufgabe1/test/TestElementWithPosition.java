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

import adp.aufgabe1.ElementWithPosition;

/**
 * Test for the {@link ElementWithPosition} class.
 *
 * @author Moritz Höwer
 * @date 17.09.2016
 * @version 1.0
 */
public class TestElementWithPosition {

	/**
	 * Test method for {@link adp.aufgabe1.ElementWithPosition#isStopElement()}.
	 */
	@Test
	public void testIsStopElement() {
		ElementWithPosition<String> elem1 = new ElementWithPosition<>("Hallo", 0, 0);
		ElementWithPosition<String> elem2 = new ElementWithPosition<>();

		assertThat("Should not be a stop element", elem1.isStopElement(), is(false));
		assertThat("Should be a stop element", elem2.isStopElement(), is(true));
	}

}
