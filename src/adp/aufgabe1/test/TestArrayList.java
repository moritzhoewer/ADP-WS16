/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1.test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import org.junit.Test;

import adp.aufgabe1.ArrayList;

/**
 * Test for the {@link ArrayList} class.
 *
 * @author Moritz Höwer
 * @version 2.0 - 24.09.2016
 */
public class TestArrayList extends BasicListTests {

    /**
     * Test method for
     * {@link adp.aufgabe1.ArrayList#insert(int, java.lang.Object)}.
     */
    @Test
    public void testInsert() {
        testInsert(new ArrayList<>());
    }

    /**
     * Test method for {@link adp.aufgabe1.ArrayList#delete(int)}.
     */
    @Test
    public void testDelete() {
        testDelete(new ArrayList<>());

        // test shrinking
        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i < 11; i++) {
            list.insert(i, i);
        }
        // [0,1,2,3,4,5,6,7,8,9,10]
        for (int i = 10; i > 6; i--) {
            list.delete(i);
        }
        // [0,1,2,3,4,5,6]
        // list is just about to be shrunk
        list.delete(0);
        // list has now been shrunk back to a length of 10
        assertThat("Size is wrong", list.size(), is(6));
        for(int i = 0; i < 6; i++){
            assertThat("Element at index " + i + " is wrong", list.retrieve(i), is(i + 1));
        }

    }

    /**
     * Test method for {@link adp.aufgabe1.ArrayList#find(java.lang.Object)}.
     */
    @Test
    public void testFind() {
        testFind(new ArrayList<>());
    }

    /**
     * Test method for {@link adp.aufgabe1.ArrayList#retrieve(int)}.
     */
    @Test
    public void testRetrieve() {
        testRetrieve(new ArrayList<>());
    }

    /**
     * Test method for {@link adp.aufgabe1.ArrayList#concat(adp.aufgabe1.List)}.
     */
    @Test
    public void testConcat() {
        testConcat(new ArrayList<>(), new ArrayList<>());
    }

    /**
     * Test method for {@link adp.aufgabe1.ArrayList#size()}.
     */
    @Test
    public void testSize() {
        testSize(new ArrayList<>());
    }

}
