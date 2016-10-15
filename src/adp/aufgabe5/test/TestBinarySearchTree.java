/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.10.2016 
 * Aufgabe: Aufgabenblatt 5
 */

package adp.aufgabe5.test;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe5.BinarySearchTree;
import adp.aufgabe5.Node;

/**
 * Testing the BinarySearchTree implementation
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.10.2016
 */
public class TestBinarySearchTree extends BinarySearchTree {

    public TestBinarySearchTree() {
        super();
    }

    /**
     * Test method for {@link adp.aufgabe5.BinarySearchTree#insert(int)}.
     */
    @Test
    public void testInsert() {
        assertThat("Root should be null", getRoot(), is(nullValue()));

        assertThat("Should be able to insert 5", insert(5), is(true));
        assertThat("Root is wrong", getRoot(), equalTo(new Node(5)));

        assertThat("Should not be able to insert 5 again", insert(5),
                is(false));
        assertThat("Root is wrong", getRoot(), equalTo(new Node(5)));

        assertThat("Should be able to insert 3", insert(3), is(true));
        assertThat("Tree structure is wrong", getRoot().getLeftChild(),
                equalTo(new Node(3)));
        assertThat("Sum is wrong", getRoot().getSum(), is(8));

        assertThat("Should not be able to insert 3 again", insert(3),
                is(false));
        assertThat("Tree structure is wrong", getRoot().getLeftChild(),
                equalTo(new Node(3)));
        assertThat("Sum is wrong", getRoot().getSum(), is(8));

        assertThat("Should be able to insert 8", insert(8), is(true));
        assertThat("Tree structure is wrong", getRoot().getRightChild(),
                equalTo(new Node(8)));
        assertThat("Sum is wrong", getRoot().getSum(), is(16));

        assertThat("Should be able to insert 4", insert(4), is(true));
        assertThat("Tree structure is wrong",
                getRoot().getLeftChild().getRightChild(), equalTo(new Node(4)));
        assertThat("Sum is wrong", getRoot().getLeftChild().getSum(), is(7));
        assertThat("Sum is wrong", getRoot().getSum(), is(20));

    }

    /**
     * Test method for {@link adp.aufgabe5.BinarySearchTree#delete(int)}.
     */
    @Test
    public void testDelete() {
        fail("Not yet implemented");
    }

    /**
     * Test method for
     * {@link adp.aufgabe5.BinarySearchTree#sumBetween(int, int)}.
     */
    @Test
    public void testSumBetween() {
        fail("Not yet implemented");
    }

}
