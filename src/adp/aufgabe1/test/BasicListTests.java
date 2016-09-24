/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 24.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import adp.aufgabe1.List;

/**
 * A base class for simplifying the tests
 *
 * @author Moritz Höwer
 * @version 1.0 - 24.09.2016
 */
public class BasicListTests {
	/**
	 * Test method for
	 * {@link adp.aufgabe1.List#insert(int, java.lang.Object)} and
	 */
	public void testInsert(List<Integer> list) {
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
		assertThat("List has wrong element at index 0", list.retrieve(0),
				is(VALUE_1));

		// insert second error
		try {
			list.insert(2, 2);
			fail("IndexOutOfBoundsException should have been thrown for index 2");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert behind
		list.insert(1, VALUE_2);
		// [VALUE_1, VALUE_2]
		assertThat("List has wrong element at index 0", list.retrieve(0),
				is(VALUE_1));
		assertThat("List has wrong element at index 1", list.retrieve(1),
				is(VALUE_2));

		// insert before
		list.insert(0, VALUE_3);
		// [VALUE_3, VALUE_1, VALUE_2]
		assertThat("List has wrong element at index 0", list.retrieve(0),
				is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1),
				is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2),
				is(VALUE_2));

		// insert some more at the end
		list.insert(3, VALUE_4);
		// [VALUE_3, VALUE_1, VALUE_2, VALUE_4]
		assertThat("List has wrong element at index 0", list.retrieve(0),
				is(VALUE_3));
		assertThat("List has wrong element at index 1", list.retrieve(1),
				is(VALUE_1));
		assertThat("List has wrong element at index 2", list.retrieve(2),
				is(VALUE_2));
		assertThat("List has wrong element at index 3", list.retrieve(3),
				is(VALUE_4));

		list.insert(0, null);
		assertThat("Element at 0 should be null", list.retrieve(0) == null,
				is(true));
	}

	/**
	 * Test method for {@link adp.aufgabe1.List#delete(int)}.
	 */
	public void testDelete(List<Integer> list) {
		// delete on empty list
		try {
			list.delete(0);
			fail("IndexOutOfBoundsException should have been thrown for empty list");
		} catch (IndexOutOfBoundsException e) {
		}

		// insert some data
		list.insert(0, 0).insert(1, 1).insert(2, 2);
		// [0,1,2]
		assertThat("List does not contain correct number of elements",
				list.size(), is(3));

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
		assertThat("List does not contain correct number of elements",
				list.size(), is(2));
		assertThat("List did not store the correct element at index 0",
				list.retrieve(0), is(0));
		assertThat("List did not store the correct element at index 1",
				list.retrieve(1), is(1));

		// delete first element
		list.delete(0);
		// [1]
		assertThat("List did not store the correct element at index 0",
				list.retrieve(0), is(1));

		// delete last element so list is empty
		list.delete(0);
		// []
		assertThat("List should be empty", list.size(), is(0));
	}

	/**
	 * Test method for {@link adp.aufgabe1.List#find(java.lang.Object)}.
	 */
	public void testFind(List<String> list) {
		// some values to be inserted
		final String VALUE_1 = "Hallo";
		final String VALUE_2 = "Welt";
		final String VALUE_3 = "Moritz";

		assertTrue("Find on empty list should be empty",
				!list.find("").isPresent());

		// insert values
		list.insert(0, VALUE_1).insert(0, VALUE_2).insert(0, VALUE_3);

		assertTrue("Object should not have been found",
				!list.find("").isPresent());

		assertTrue("Object should have been found",
				list.find(VALUE_2).isPresent());
		assertThat("Object should be at a different index",
				list.find(VALUE_2).getAsInt(), is(1));
	}

	/**
	 * Test method for {@link adp.aufgabe1.List#concat(adp.aufgabe1.List)}
	 * .
	 */
	public void testConcat(List<Integer> list1, List<Integer> list2) {
		// fill lists
		for (int i = 0; i < 10; i++) {
			list1.insert(i, i);
			list2.insert(i, 10 * i);
		}

		assertThat("First list doesn't have the correct size", list1.size(),
				is(10));
		assertThat("Second list doesn't have the correct size", list2.size(),
				is(10));

		assertThat("Chaining is broken!", list1.concat(list2), is(list1));
		assertThat("New lists size is wrong", list1.size(), is(20));

		for (int i = 0; i < 10; i++) {
			assertThat("Element at index " + i + " is wrong", list1.retrieve(i),
					is(i));
		}
		for (int i = 10; i < 20; i++) {
			assertThat("Element at index " + i + " is wrong", list1.retrieve(i),
					is((i - 10) * 10));
		}
	}

	/**
	 * Test method for {@link adp.aufgabe1.List#size()}.
	 */
	public void testSize(List<Integer> list) {
		assertThat("List should be empty", list.size(), is(0));

		list.insert(0, 0);
		assertThat("List has wrong size", list.size(), is(1));

		list.insert(1, 1);
		assertThat("List has wrong size", list.size(), is(2));

		list.delete(0);
		assertThat("List has wrong size", list.size(), is(1));
	}

	/**
	 * Test method for {@link adp.aufgabe1.List#retrieve(int)}.
	 */
	public void testRetrieve(List<Integer> list) {
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

		assertThat("List did not store the correct element at index 0",
				list.retrieve(0), is(VALUE));
	}
}
