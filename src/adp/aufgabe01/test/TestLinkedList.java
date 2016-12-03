/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe01.test;

import org.junit.Test;

import adp.aufgabe01.LinkedList;

/**
 * performs tests on LinkedList
 *
 * @author Moritz Höwer
 * @version 2.0 - 24.09.2016
 */
public class TestLinkedList extends BasicListTests {

	/**
	 * Test method for
	 * {@link adp.aufgabe01.LinkedList#insert(int, java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		testInsert(new LinkedList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.LinkedList#delete(int)}.
	 */
	@Test
	public void testDelete() {
		testDelete(new LinkedList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.LinkedList#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		testFind(new LinkedList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.LinkedList#concat(adp.aufgabe01.List)}
	 * .
	 */
	@Test
	public void testConcat() {
		testConcat(new LinkedList<>(), new LinkedList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.LinkedList#size()}.
	 */
	@Test
	public void testSize() {
		testSize(new LinkedList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.LinkedList#retrieve(int)}.
	 */
	@Test
	public void testRetrieve() {
		testRetrieve(new LinkedList<>());
	}

}
