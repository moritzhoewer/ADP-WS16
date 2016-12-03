/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

/**
 * Represents a weighted connection in the Graph
 *
 * @author Moritz Höwer
 * @version 1.0 - 08.11.2016
 */
public class Connection<T> {

    /**
     * starting point of the connection
     */
    private final Node<T> start;

    /**
     * end point of the Node
     */
    private final Node<T> end;

    /**
     * weight (e.g. distance) of this connection
     */
    private final int weight;

    public Connection(Node<T> start, Node<T> end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    /**
     * @return the start
     */
    public Node<T> getStart() {
        return start;
    }

    /**
     * @return the end
     */
    public Node<T> getEnd() {
        return end;
    }

    /**
     * @return the weight
     */
    public int getWeight() {
        return weight;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((end == null) ? 0 : end.hashCode());
        result = prime * result + ((start == null) ? 0 : start.hashCode());
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Connection)) {
            return false;
        }
        Connection other = (Connection) obj;
        if (end == null) {
            if (other.end != null) {
                return false;
            }
        } else if (!end.equals(other.end)) {
            return false;
        }
        if (start == null) {
            if (other.start != null) {
                return false;
            }
        } else if (!start.equals(other.start)) {
            return false;
        }
        return true;
    }

}
