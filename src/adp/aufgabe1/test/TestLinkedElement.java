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

import adp.aufgabe1.LinkedElement;

/**
 * Test for the {@link LinkedElement} class.
 *
 * @author Moritz Höwer
 * @version 1.0 - 17.09.2016
 */
public class TestLinkedElement {

	/**
	 * Test method for {@link adp.aufgabe1.LinkedElement#isStopElement()}.
	 */
	@Test
	public void testIsStopElement() {
		LinkedElement<String> elem1 = new LinkedElement<>("Hallo");
		LinkedElement<String> elem2 = new LinkedElement<>("Hallo", elem1);

		assertThat("Should be a stop element", elem1.isStopElement(), is(true));
		assertThat("Should not be a stop element", elem2.isStopElement(), is(false));
	}

}
