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
 * @version 1.1 - 17.10.2016
 */
public class BinarySearchTree {
    /**
     * the root of the tree
     */
    protected Node root;

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
     * Calculates the sum of all elements in this tree between upper and lower
     * 
     * @param lower
     *            the lower boundary (included)
     * @param upper
     *            the upper boundary (included)
     * @return the sum of all the values in the tree between upper and lower
     * @throws IllegalArgumentException
     *             if lower is bigger than upper
     */
    public int sumBetween(int lower, int upper) {
        if (lower > upper) {
            throw new IllegalArgumentException(
                    "Lower boundary must not be bigger than upper boundary!");
        }
        
        return root.getSum() - getSumOutside(root, upper, true)
                - getSumOutside(root, lower, false);
    }

    /**
     * calculates the sum of the part of the tree that is outside the boundary
     * 
     * @param root the root of the subtree
     * @param limit the limit against which to check
     * @param isUpperLimit is limit the upper or the lower limit
     * @return the sum of all nodes in the tree outside of the boundary
     */
    private int getSumOutside(Node root, int limit, boolean isUpperLimit) {
        if (root == null) {
            // end recursion
            return 0;
        }

        if (isUpperLimit) {
            if (root.getValue() == limit) {
                // we need to subtract everything that is greater
                if (root.getRightChild() != null) {
                    return root.getRightChild().getSum();
                } else {
                    return 0;
                }

            } else if (root.getValue() < limit) {
                // going out further
                return getSumOutside(root.getRightChild(), limit, isUpperLimit);
            } else {
                // going in
                // we need to remember that we might be cutting off part of the
                // tree
                int toSubtract = root.getValue();
                if (root.getRightChild() != null) {
                    toSubtract += root.getRightChild().getSum();
                }
                return toSubtract + getSumOutside(root.getLeftChild(), limit,
                        isUpperLimit);
            }
        } else {
            if (root.getValue() == limit) {
                // we need to subtract everything that is smaller
                if (root.getLeftChild() != null) {
                    return root.getLeftChild().getSum();
                } else {
                    return 0;
                }

            } else if (root.getValue() > limit) {
                // going out further
                return getSumOutside(root.getLeftChild(), limit, isUpperLimit);
            } else {
                // going in
                // we need to remember that we might be cutting off part of the
                // tree
                int toSubtract = root.getValue();
                if (root.getLeftChild() != null) {
                    toSubtract += root.getLeftChild().getSum();
                }
                return toSubtract + getSumOutside(root.getRightChild(), limit,
                        isUpperLimit);
            }
        }
    }
}
