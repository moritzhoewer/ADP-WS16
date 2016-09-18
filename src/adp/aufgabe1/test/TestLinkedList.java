/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 18.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe1.LinkedList;
import adp.aufgabe1.List;

/**
 *
 *
 * @author Moritz Höwer
 * @date 18.09.2016
 * @version 1.0
 */
/**
 *
 *
 * @author Moritz Höwer
 * @date 18.09.2016
 * @version 1.0
 */
public class TestLinkedList {

	/**
	 * Test method for
	 * {@link adp.aufgabe1.LinkedList#insert(int, java.lang.Object)} and
	 * {@link adp.aufgabe1.LinkedList#retrieve(int)}.
	 */
	@Test
	public void testInsertAndRetrieve() {
		LinkedList<Integer> list = new LinkedList<>();

		// some values
		final int VALUE_1 = 10;
		final int VALUE_2 = 20;
		final int VALUE_3 = 30;
		final int VALUE_4 = 40;

		// negative index
		try {
			list.insert(-1, -1);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert first error
		try {
			list.insert(1, 1);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert first and check chaining
		assertThat("Chaining is broken", list.insert(0, VALUE_1), is(list));
		// [VALUE_1]
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_1));

		// insert second error
		try {
			list.insert(2, 2);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert behind
		list.insert(1, VALUE_2);
		// [VALUE_1, VALUE_2]
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_1));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_2));

		// insert before
		list.insert(0, VALUE_3);
		// [VALUE_3, VALUE_1, VALUE_2]
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2), is(VALUE_2));

		// insert some more at the end
		list.insert(3, VALUE_4);
		// [VALUE_3, VALUE_1, VALUE_2, VALUE_4]
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2), is(VALUE_2));
		assertThat("List has wrong element at index 3", list.retrieve(3), is(VALUE_4));
	}

	/**
	 * Test method for {@link adp.aufgabe1.LinkedList#delete(int)}.
	 */
	@Test
	public void testDelete() {
		// create list
		LinkedList<Integer> list = new LinkedList<>();

		// delete on empty list
		try {
			list.delete(0);
			fail("IndexOutOfBoundsException should have been thrown for empty list");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert some data
		list.insert(0, 0).insert(1, 1).insert(2, 2);
		// [0,1,2]
		assertThat("List does not contain correct number of elements", list.size(), is(3));

		// test invalid indices
		try {
			list.delete(-1);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			list.delete(3);
			fail("IndexOutOfBoundsException should have been thrown for index 3");
		} catch (IndexOutOfBoundsException e) {
		}

		// delete last value and test chaining capabilities
		assertThat("Chaining not possible!", list.delete(2), is(list));
		// [0,1]
		assertThat("List does not contain correct number of elements", list.size(), is(2));
		assertThat("List did not store the correct element at index 0", list.retrieve(0), is(0));
		assertThat("List did not store the correct element at index 1", list.retrieve(1), is(1));

		// delete first element
		list.delete(0);
		// [1]
		assertThat("List did not store the correct element at index 0", list.retrieve(0), is(1));

		// delete last element so list is empty
		list.delete(0);
		// []
		assertThat("List should be empty", list.size(), is(0));
	}

	/**
	 * Test method for {@link adp.aufgabe1.LinkedList#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		// create list
		LinkedList<String> list = new LinkedList<>();

		// some values to be inserted
		final String VALUE_1 = "Hallo";
		final String VALUE_2 = "Welt";
		final String VALUE_3 = "Moritz";

		assertTrue("Find on empty list should be empty", !list.find("").isPresent());

		// insert values
		list.insert(0, VALUE_1).insert(0, VALUE_2).insert(0, VALUE_3);

		assertTrue("Object should not have been found", !list.find("").isPresent());

		assertTrue("Object should have been found", list.find(VALUE_2).isPresent());
		assertThat("Object should be at a different index", list.find(VALUE_2).getAsInt(), is(1));
	}

	/**
	 * Test method for {@link adp.aufgabe1.LinkedList#concat(adp.aufgabe1.List)}
	 * .
	 */
	@Test
	public void testConcat() {
		// create list
		LinkedList<Integer> list1 = new LinkedList<>();
		LinkedList<Integer> list2 = new LinkedList<>();
		List<Integer> list3;

		// fill lists
		for (int i = 0; i < 10; i++) {
			list1.insert(i, i);
			list2.insert(i, 10 * i);
		}

		assertThat("First list doesn't have the correct size", list1.size(), is(10));
		assertThat("Second list doesn't have the correct size", list2.size(), is(10));

		list3 = list1.concat(list2);
		assertThat("New lists size is wrong", list3.size(), is(20));

		for (int i = 0; i < 10; i++) {
			assertThat("Element at index " + i + " is wrong", list3.retrieve(i), is(i));
		}
		for (int i = 10; i < 20; i++) {
			assertThat("Element at index " + i + " is wrong", list3.retrieve(i), is((i - 10) * 10));
		}
	}

	/**
	 * Test method for {@link adp.aufgabe1.LinkedList#size()}.
	 */
	@Test
	public void testSize() {
		// create list
		LinkedList<Integer> list = new LinkedList<>();

		assertThat("List should be empty", list.size(), is(0));

		list.insert(0, 0);
		assertThat("List has wrong size", list.size(), is(1));

		list.insert(1, 1);
		assertThat("List has wrong size", list.size(), is(2));

		list.delete(0);
		assertThat("List has wrong size", list.size(), is(1));
	}

	/**
	 * Test method for {@link adp.aufgabe1.LinkedList#retrieve(int)}.
	 */
	@Test
	public void testRetrieve() {
		// create list
		LinkedList<Integer> list = new LinkedList<>();

		// a value to be inserted
		final int VALUE = 100;

		// retrieve on empty list
		try {
			list.retrieve(0);
			fail("IndexOutOfBoundsException should have been thrown for empty list");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert value
		list.insert(0, VALUE);

		// test invalid indices
		try {
			list.retrieve(-1);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			list.retrieve(1);
			fail("IndexOutOfBoundsException should have been thrown for index 1");
		} catch (IndexOutOfBoundsException e) {
		}

		assertThat("List did not store the correct element at index 0", list.retrieve(0), is(VALUE));
	}

}
