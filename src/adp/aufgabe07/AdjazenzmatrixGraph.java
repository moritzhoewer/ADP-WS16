/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * An Implementation for a Graph based on a Adjazenzmatrix
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class AdjazenzmatrixGraph<T> implements Graph<T> {

    /**
     * All the Nodes in this graph
     */
    private List<Node<T>> nodes;

    /**
     * Matrix of connections
     */
    private int[][] matrix;

    public AdjazenzmatrixGraph() {
        nodes = new ArrayList<>();
        matrix = new int[0][0];
    }

    @Override
    public void insertNode(Node<T> node) {
        if (!nodes.contains(node)) {
            nodes.add(node);

            // create bigger matrix
            int[][] newMatrix = new int[matrix.length + 1][matrix.length + 1];
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    newMatrix[i][j] = matrix[i][j];
                }
            }
            for (int i = 0; i < newMatrix.length; i++) {
                newMatrix[i][newMatrix.length - 1] = -1;
                newMatrix[newMatrix.length - 1][i] = -1;
            }
            matrix = newMatrix;
        }
    }

    @Override
    public void removeNode(Node<T> node) {
        int nodeIndex = nodes.indexOf(node);
        if (nodeIndex >= 0) {
            nodes.remove(nodeIndex);
            
            // create smaller matrix
            int[][] newMatrix = new int[matrix.length - 1][matrix.length - 1];
            
            int correctI = 0;
            for (int i = 0; i < newMatrix.length; i++) {
                if(i == nodeIndex){
                    correctI = 1;
                }
                int correctJ = 0;
                for (int j = 0; j < newMatrix.length; j++) {
                    if(j == nodeIndex){
                        correctJ = 1;
                    }
                    newMatrix[i][j] = matrix[i + correctI][j + correctJ];
                }
            }
            matrix = newMatrix;
        }

    }

    @Override
    public void insertConnection(Node<T> start, Node<T> end, int weight) {
        int startIndex = nodes.indexOf(start);
        int endIndex = nodes.indexOf(end);
        
        if (startIndex < 0) {
            throw new IllegalArgumentException(
                    "Starting Node is not part of the Graph!");
        }
        if (endIndex < 0) {
            throw new IllegalArgumentException(
                    "End Node is not part of the Graph!");
        }
        if (weight < 0) {
            throw new IllegalArgumentException("Weight must not be negative!");
        }

        // add connection
        matrix[startIndex][endIndex] = weight;

    }

    @Override
    public void removeConnection(Node<T> start, Node<T> end) {
        int startIndex = nodes.indexOf(start);
        int endIndex = nodes.indexOf(end);
        if(startIndex >= 0 && endIndex >= 0){
            matrix[startIndex][endIndex] = -1;
        }

    }

    @Override
    public Set<Connection<T>> getConnectionsFrom(Node<T> node) {
        int nodeIndex = nodes.indexOf(node);
        if (nodeIndex < 0) {
            return null;
        }
        Set<Connection<T>> result = new HashSet<>();
        for(int i = 0; i < matrix.length; i++){
            if(matrix[nodeIndex][i] >= 0){
                result.add(new Connection<>(node, nodes.get(i), matrix[nodeIndex][i]));
            }
        }        
        return result;
    }

    @Override
    public Set<Node<T>> getAllNodes() {
        return new HashSet<>(nodes);
    }

}
