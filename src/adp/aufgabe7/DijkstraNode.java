/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe7;

/**
 * Extention to Node for Dijkstras Algorithm
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class DijkstraNode<T> extends Node<T>
        implements
            Comparable<DijkstraNode<T>> {

    /**
     * the previous node that forms the end of the shortest path to reach this
     */
    private DijkstraNode<T> previous;

    /**
     * the accumulated weight of this node
     */
    private int weight;

    public DijkstraNode(Node<T> node, int weight) {
        this(node, null, weight);
    }

    public DijkstraNode(Node<T> node, DijkstraNode<T> previous, int weight) {
        super(node.getValue());
        this.previous = previous;
        this.weight = weight;
    }

    /**
     * @return the previous
     */
    public DijkstraNode<T> getPrevious() {
        return previous;
    }

    /**
     * @param previous
     *            the previous to set
     */
    public void setPrevious(DijkstraNode<T> previous) {
        this.previous = previous;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /**
     * @param weight
     *            the weight to set
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(DijkstraNode<T> o) {
        return weight < o.weight ? -1 : 1;
    }

}
