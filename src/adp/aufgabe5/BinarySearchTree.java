/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.10.2016 
 * Aufgabe: Aufgabenblatt 5
 */

package adp.aufgabe5;

/**
 * A BinarySearchTree that has a method to sum up all the elements of the tree
 * between two supplied numbers
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.10.2016
 */
public class BinarySearchTree {
    /**
     * the root of the tree
     */
    private Node root;

    public BinarySearchTree(int roootValue) {
        root = new Node(roootValue);
    }

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Inserts a new value into the tree
     * 
     * @param value
     *            the value to insert
     * @return true if the value was inserted correctly, false if it already
     *         existed
     */
    public boolean insert(int value) {
        if (root == null) {
            // create root if it didn't exist yet
            root = new Node(value);
            return true;
        } else {
            // go through the tree to find the place to insert at
            return doInsert(root, value);
        }
    }

    /**
     * Recursively traverses the tree to find a place to insert value
     * 
     * @param node
     *            current root of subtree to look at
     * @param value
     *            the value to insert
     * @return false if element already present
     */
    private boolean doInsert(Node node, int value) {
        if (value == node.getValue()) {
            return false;
        }

        boolean result = true;

        if (value < node.getValue()) {
            // go to the left
            if (node.getLeftChild() == null) {
                // cool, already at the edge
                node.setLeftChild(new Node(value));
            } else {
                result = doInsert(node.getLeftChild(), value);
            }
        } else {
            // go to the right
            if (node.getRightChild() == null) {
                // cool, already at the edge
                node.setRightChild(new Node(value));
            } else {
                result = doInsert(node.getRightChild(), value);
            }
        }

        if (result) {
            // Update sum because element was successfully inserted
            node.addToSum(value);
        }
        return result;
    }

    /**
     * Removes a value from the tree
     * 
     * @param value
     *            the value to remove
     * @return false if the vlaue did not exist in the tree
     */
    public boolean delete(int value) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /**
     * Calculates the sum of all elements in this tree between upper and lower
     * 
     * @param lower
     *            the lower boundary (included)
     * @param uppper
     *            the upper boundary (included)
     * @return the sum of all the values in the tree between upper and lower
     */
    public int sumBetween(int lower, int uppper) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
