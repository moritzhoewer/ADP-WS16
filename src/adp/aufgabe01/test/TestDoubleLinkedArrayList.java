/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe01.test;

import org.junit.Test;

import adp.aufgabe01.DoubleLinkedArrayList;

/**
 *
 *
 * @author Moritz Höwer 
 * @version 2.0 - 24.09.2016
 */
public class TestDoubleLinkedArrayList extends BasicListTests {

	/**
	 * Test method for
	 * {@link adp.aufgabe01.DoubleLinkedArrayList#insert(int, java.lang.Object)}.
	 */
	@Test
	public void testInsert() {
		testInsert(new DoubleLinkedArrayList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.DoubleLinkedArrayList#delete(int)}.
	 */
	@Test
	public void testDelete() {
		testDelete(new DoubleLinkedArrayList<>());
	}

	/**
	 * Test method for
	 * {@link adp.aufgabe01.DoubleLinkedArrayList#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		testFind(new DoubleLinkedArrayList<>());
	}

	/**
	 * Test method for
	 * {@link adp.aufgabe01.DoubleLinkedArrayList#concat(adp.aufgabe01.List)}.
	 */
	@Test
	public void testConcat() {
		testConcat(new DoubleLinkedArrayList<>(), new DoubleLinkedArrayList<>());
	}

	/**
	 * Test method for {@link adp.aufgabe01.DoubleLinkedArrayList#retrieve(int)}.
	 */
	@Test
	public void testRetrieve() {
		testRetrieve(new DoubleLinkedArrayList<>());	
	}
	
	/**
	 * Test method for {@link adp.aufgabe01.DoubleLinkedArrayList#size()}.
	 */
	@Test
	public void testSize() {
		testSize(new DoubleLinkedArrayList<>());	
	}

}
