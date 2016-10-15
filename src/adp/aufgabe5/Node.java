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
 * Represents a node in the {@link BinarySearchTree}
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.10.2016
 */
public class Node {

    /**
     * the integer stored in this Node
     */
    private int value;

    /**
     * the sum of all the elements in this tree
     */
    private int sum;

    /**
     * the left subtree
     */
    private Node leftChild;

    /**
     * the right subtree
     */
    private Node rightChild;

    public Node(int value) {
        this.value = value;
        sum = value;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Adds to the internal sum
     * 
     * @param value
     *            the value to add
     */
    public void addToSum(int value) {
        sum += value;
    }

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the sum
     */
    public int getSum() {
        return sum;
    }

    /**
     * @param sum
     *            the sum to set
     */
    public void setSum(int sum) {
        this.sum = sum;
    }

    /**
     * @return the leftChild
     */
    public Node getLeftChild() {
        return leftChild;
    }

    /**
     * @param leftChild
     *            the leftChild to set
     */
    public void setLeftChild(Node leftChild) {
        this.leftChild = leftChild;
    }

    /**
     * @return the rightChild
     */
    public Node getRightChild() {
        return rightChild;
    }

    /**
     * @param rightChild
     *            the rightChild to set
     */
    public void setRightChild(Node rightChild) {
        this.rightChild = rightChild;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Node) {
            Node otherNode = (Node) other;

            // compare values
            boolean equal = (value == otherNode.value
                    && sum == otherNode.sum);
            
            // compare children
            if(equal && leftChild != null){
                equal = equal && leftChild.equals(otherNode.getLeftChild());
            }
            if(equal && rightChild != null){
                equal = equal && rightChild.equals(otherNode.getRightChild());
            }
            
            return equal;
        }
        return false;
    }
}
