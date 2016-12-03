/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */
package adp.aufgabe01.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe01.LinkedNode;

/**
 * Test for the {@link LinkedNode} class.
 *
 * @author Moritz Höwer
 * @version 1.0 - 21.09.2016
 */
public class TestLinkedNode {

	/**
	 * Test method for {@link adp.aufgabe01.LinkedNode#isStopElement()}.
	 */
	@Test
	public void testIsStopElement() {
		LinkedNode<String> elem1 = new LinkedNode<>("Hallo");
		LinkedNode<String> elem2 = new LinkedNode<>("Hallo", elem1);

		assertThat("Should be a stop element", elem1.isStopElement(), is(true));
		assertThat("Should not be a stop element", elem2.isStopElement(), is(false));
	}

}
