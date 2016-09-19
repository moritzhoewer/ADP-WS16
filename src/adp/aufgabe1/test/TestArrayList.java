/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1.test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe1.ArrayList;
import adp.aufgabe1.List;

/**
 * Test for the {@link ArrayList} class.
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.09.2016
 */
public class TestArrayList {

	/**
	 * Test method for
	 * {@link adp.aufgabe1.ArrayList#insert(int, java.lang.Object)} and
	 * {@link adp.aufgabe1.ArrayList#size()}.
	 */
	@Test
	public void testInsertAndSize() {
		// create a list
		ArrayList<Integer> list = new ArrayList<>();

		assertThat("List is not empty", list.size(), is(0));

		// some values to be inserted
		final int VALUE_1 = 100;
		final int VALUE_2 = 200;
		final int VALUE_3 = 300;

		// test invalid indices
		try {
			list.insert(-1, -1);
			fail("IndexOutOfBoundsException should have been thrown for index -1");
		} catch (IndexOutOfBoundsException e) {
		}

		try {
			list.insert(1, 1);
			fail("IndexOutOfBoundsException should have been thrown for index 1");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert first value and test chaining capabilities
		assertThat("Chaining not possible!", list.insert(0, VALUE_1), is(list));

		// [VALUE_1]
		assertThat("List does not contain correct number of elements", list.size(), is(1));
		assertThat("List did not store the correct element at index 0", list.retrieve(0), is(VALUE_1));

		// insert second value before first
		list.insert(0, VALUE_2);

		// [VALUE_2, VALUE_1]
		assertThat("List does not contain correct number of elements", list.size(), is(2));
		assertThat("List did not store the correct element at index 0", list.retrieve(0), is(VALUE_2));
		assertThat("List did not store the correct element at index 1", list.retrieve(1), is(VALUE_1));

		// insert 8 elements (initial array is now full
		for (int i = 0; i < 8; i++) {
			list.insert(0, i);
		}
		assertThat("List does not contain correct number of elements", list.size(), is(10));

		// insert 11th element in the middle (index 5)
		list.insert(5, VALUE_3);
		assertThat("List does not contain correct number of elements", list.size(), is(11));

		// [7, 6, 5, 4, 3, VALUE_3, 2, 1, 0, VALUE_2, VALUE_1]
		for (int i = 0; i < 5; i++) {
			assertThat("List did not store the correct element at index " + i, list.retrieve(i), is(7 - i));
		}

		assertThat("List did not store the correct element at index 5", list.retrieve(5), is(VALUE_3));

		for (int i = 6; i < 9; i++) {
			assertThat("List did not store the correct element at index " + i, list.retrieve(i), is(7 - i + 1));
		}

		assertThat("List did not store the correct element at index 9", list.retrieve(9), is(VALUE_2));
		assertThat("List did not store the correct element at index 10", list.retrieve(10), is(VALUE_1));
		
		list.insert(0, null);
		assertThat("Element at 0 should be null", list.retrieve(0) == null, is(true));
	}

	/**
	 * Test method for {@link adp.aufgabe1.ArrayList#delete(int)} and
	 * {@link adp.aufgabe1.ArrayList#size()}.
	 */
	@Test
	public void testDeleteAndSize() {
		// create a list
		ArrayList<Integer> list = new ArrayList<>();

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
	 * Test method for {@link adp.aufgabe1.ArrayList#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		// create a list
		ArrayList<String> list = new ArrayList<>();

		// some values to be inserted
		final String VALUE_1 = "Hallo";
		final String VALUE_2 = "Welt";
		final String VALUE_3 = "Moritz";

		assertTrue("Find on empty list should be empty", !list.find("").isPresent());

		// insert values
		list.insert(0, VALUE_1).insert(1, VALUE_2).insert(2, VALUE_3);

		assertTrue("Object should not have been found", !list.find("").isPresent());

		assertTrue("Object should have been found", list.find(VALUE_2).isPresent());
		assertThat("Object should be at a different index", list.find(VALUE_2).getAsInt(), is(1));

	}

	/**
	 * Test method for {@link adp.aufgabe1.ArrayList#retrieve(int)}.
	 */
	@Test
	public void testRetrieve() {
		// create a list
		ArrayList<Integer> list = new ArrayList<>();

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

	/**
	 * Test method for {@link adp.aufgabe1.ArrayList#concat(adp.aufgabe1.List)}
	 * and {@link adp.aufgabe1.ArrayList#size()}.
	 */
	@Test
	public void testConcatAndSize() {
		// create a list
		ArrayList<Integer> list1 = new ArrayList<>();
		ArrayList<Integer> list2 = new ArrayList<>();
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

}
