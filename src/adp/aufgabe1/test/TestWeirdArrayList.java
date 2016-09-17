/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe1.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import adp.aufgabe1.List;
import adp.aufgabe1.WeirdArrayList;

/**
 *
 *
 * @author Moritz Höwer
 * @date 17.09.2016
 * @version 1.0
 */
public class TestWeirdArrayList {

	/**
	 * Test method for
	 * {@link adp.aufgabe1.WeirdArrayList#insert(int, java.lang.Object)} and
	 * {@link adp.aufgabe1.WeirdArrayList#retrieve(int)}.
	 */
	@Test
	public void testInsertAndRetrieve() {
		// create list
		WeirdArrayList<Integer> list = new WeirdArrayList<>();

		assertThat("List is not empty", list.size(), is(0));

		// some valuesto insert
		final int VALUE_1 = 100;
		final int VALUE_2 = 200;
		final int VALUE_3 = 300;
		final int VALUE_4 = 400;
		final int VALUE_5 = 500;

		// insert first
		assertThat("Chaining is broken", list.insert(0, VALUE_1), is(list));
		// Array: [VALUE_1]
		// List: [VALUE_1]
		assertThat("List has wrong size", list.size(), is(1));
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_1));

		// insert behind
		list.insert(1, VALUE_2);
		// Array: [VALUE_1, VALUE_2]
		// List: [VALUE_1, VALUE_2]
		assertThat("List has wrong size", list.size(), is(2));
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_1));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_2));

		// insert at front
		list.insert(0, VALUE_3);
		// Array: [VALUE_1, VALUE_2, VALUE_3]
		// List: [VALUE_3, VALUE_1, VALUE_2]
		assertThat("List has wrong size", list.size(), is(3));
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2), is(VALUE_2));

		// insert between 2nd and 3rd (index 2)
		list.insert(2, VALUE_4);
		// Array: [VALUE_1, VALUE_2, VALUE_3, VALUE_4]
		// List: [VALUE_3, VALUE_1, VALUE_4, VALUE_2]
		assertThat("List has wrong size", list.size(), is(4));
		assertThat("List has wrong element at index 0", list.retrieve(0), is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1), is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2), is(VALUE_4));
		assertThat("List has wrong element at index 3", list.retrieve(3), is(VALUE_2));

		// insert 6 more at the front
		for (int i = 0; i < 6; i++) {
			list.insert(0, i);
		}
		// Array: [VALUE_1, VALUE_2, VALUE_3, VALUE_4, 0, 1, 2, 3, 4, 5]
		// List: [5, 4, 3, 2, 1, 0, VALUE_3, VALUE_1, VALUE_4, VALUE_2]
		assertThat("List has wrong size", list.size(), is(10));

		// insert 11th in the middle (index 5)
		list.insert(5, VALUE_5);
		// Array: [VALUE_1, VALUE_2, VALUE_3, VALUE_4, 0, 1, 2, 3, 4, 5,
		// VALUE_5]
		// List: [5, 4, 3, 2, 1, VALUE_5, 0, VALUE_3, VALUE_1, VALUE_4, VALUE_2]
		assertThat("List has wrong size", list.size(), is(11));
		for (int i = 0; i < 5; i++) {
			assertThat("List has wrong element at index " + i, list.retrieve(i), is(5 - i));
		}
		assertThat("List has wrong element at index 5", list.retrieve(5), is(VALUE_5));
		assertThat("List has wrong element at index 6", list.retrieve(6), is(0));
		assertThat("List has wrong element at index 7", list.retrieve(7), is(VALUE_3));
		assertThat("List has wrong element at index 8", list.retrieve(8), is(VALUE_1));
		assertThat("List has wrong element at index 9", list.retrieve(9), is(VALUE_4));
		assertThat("List has wrong element at index 10", list.retrieve(10), is(VALUE_2));
	}

	/**
	 * Test method for {@link adp.aufgabe1.WeirdArrayList#delete(int)}.
	 */
	@Test
	public void testDelete() {
		// create a list
		WeirdArrayList<Integer> list = new WeirdArrayList<>();

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
	}

	/**
	 * Test method for
	 * {@link adp.aufgabe1.WeirdArrayList#find(java.lang.Object)}.
	 */
	@Test
	public void testFind() {
		// create list
		WeirdArrayList<String> list = new WeirdArrayList<>();

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
	 * Test method for
	 * {@link adp.aufgabe1.WeirdArrayList#concat(adp.aufgabe1.List)}.
	 */
	@Test
	public void testConcat() {
		// create a list
		WeirdArrayList<Integer> list1 = new WeirdArrayList<>();
		WeirdArrayList<Integer> list2 = new WeirdArrayList<>();
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
