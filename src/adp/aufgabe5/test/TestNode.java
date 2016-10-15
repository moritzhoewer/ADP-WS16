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

import org.junit.Test;

import adp.aufgabe5.Node;

/**
 * Tests Node class
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.10.2016
 */
public class TestNode {

    /**
     * Test method for {@link adp.aufgabe5.Node#equals(java.lang.Object)}.
     */
    @Test
    public void testEqualsObject() {
        final int VALUE_1 = 21;
        final int VALUE_2 = 12;

        Node node1 = new Node(VALUE_1);
        Node node2 = new Node(VALUE_2);

        // test not equal
        assertFalse("Shouldn't be equal to an object",
                node1.equals(new Object()));
        assertFalse("node1 shouldn't be equal to node2", node1.equals(node2));

        assertTrue("node1 should be equal to itself", node1.equals(node1));

        node2.setValue(VALUE_1);
        // node1 = VALUE_1 (VALUE_1)
        // node2 = VALUE_1 (VALUE_2)
        assertFalse("node1 should still not be equal to node2",
                node1.equals(node2));

        node2.setSum(VALUE_1);
        // node1 = VALUE_1 (VALUE_1)
        // node2 = VALUE_1 (VALUE_1)
        assertTrue("node1 should be equal to node2", node1.equals(node2));

        Node node3 = new Node(33);

        node1.setLeftChild(node3);
        assertFalse("node1 should no longer be equal to node2",
                node1.equals(node2));

        node2.setLeftChild(node3);
        assertTrue("node1 should be equal to node2", node1.equals(node2));

        node1.setRightChild(node3);
        assertFalse("node1 should no longer be equal to node2",
                node1.equals(node2));

        node2.setRightChild(node3);
        assertTrue("node1 should be equal to node2", node1.equals(node2));
    }

}
