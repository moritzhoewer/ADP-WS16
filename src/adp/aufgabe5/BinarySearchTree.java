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
     * @throws IllegalArgumentException
     *             if value is negative
     */
    public boolean insert(int value) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    "Only positive numbers can be inserted!");
        }
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

        return root.getSum() - getUpperSumOutside(root, upper)
                - getLowerSumOutside(root, lower);
    }

	/**
	 * Calculates the sum of the lower subtree
	 * 
	 * @param current
	 * @param limit
	 * @return
	 */
	private int getLowerSumOutside(Node current, int limit) {
		if (current == null) {
            // end recursion
            return 0;
        }
		
		if (current.getValue() == limit) {
		    // we need to subtract everything that is smaller
		    if (current.getLeftChild() != null) {
		        return current.getLeftChild().getSum();
		    } else {
		        return 0;
		    }

		} else if (current.getValue() > limit) {
		    // going out further
		    return getLowerSumOutside(current.getLeftChild(), limit);
		} else {
		    // going in
		    // we need to remember that we might be cutting off part of the
		    // tree
		    int toSubtract = current.getValue();
		    if (current.getLeftChild() != null) {
		        toSubtract += current.getLeftChild().getSum();
		    }
		    return toSubtract + getLowerSumOutside(current.getRightChild(), limit);
		}
	}

	/**
	 * Calculates the sum of the upper Subtree
	 * 
	 * @param current
	 * @param limit
	 * @return
	 */
	private int getUpperSumOutside(Node current, int limit) {
		if (current == null) {
            // end recursion
            return 0;
        }
		
		if (current.getValue() == limit) {
		    // we need to subtract everything that is greater
		    if (current.getRightChild() != null) {
		        return current.getRightChild().getSum();
		    } else {
		        return 0;
		    }

		} else if (current.getValue() < limit) {
		    // going out further
		    return getUpperSumOutside(current.getRightChild(), limit);
		} else {
		    // going in
		    // we need to remember that we might be cutting off part of the
		    // tree
		    int toSubtract = current.getValue();
		    if (current.getRightChild() != null) {
		        toSubtract += current.getRightChild().getSum();
		    }
		    return toSubtract + getUpperSumOutside(current.getLeftChild(), limit);
		}
	}
}
