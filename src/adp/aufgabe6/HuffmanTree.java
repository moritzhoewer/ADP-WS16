/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 22.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

import java.util.HashSet;

/**
 * A BinaryTree for the Huffman code
 *
 * @author Moritz Höwer
 * @version 1.0 - 22.10.2016
 */
public class HuffmanTree {
    /**
     * the value represented by this tree
     */
    private HuffmanMultiCharTuple value;

    /**
     * the left subtree
     */
    private HuffmanTree leftChild;

    /**
     * the right subtree
     */
    private HuffmanTree rightChild;

    public HuffmanTree() {
        this(null, null, null);
    }

    public HuffmanTree(HuffmanMultiCharTuple tupel) {
        this(tupel, null, null);
    }

    public HuffmanTree(HuffmanMultiCharTuple tupel, HuffmanTree left,
            HuffmanTree right) {
        value = tupel;
        leftChild = left;
        rightChild = right;
    }

    /**
     * returns a new Huffman Tree that contains this and other as childs
     * 
     * @param other the tree to combine this with
     * @return a new Tree containing this and other
     */
    public HuffmanTree combineWith(HuffmanTree other){
        // for starting reduce operation of stream
        if(value == null){
            return other;
        }
        
        HuffmanTree left, right;
        if(this.value.compareTo(other.value) < 0){
            left = this;
            right = other;
        } else {
            left = other;
            right = this;
        }
        
        // create parameters for new Tuple
        int newTimes = this.value.getTimes() + other.value.getTimes();
        HashSet<Character> newSet = new HashSet<>(this.value.getCharacters());
        newSet.addAll(other.value.getCharacters());

        // create new tuple
        HuffmanMultiCharTuple newTuple = new HuffmanMultiCharTuple(newTimes, newSet);
        
        // create and return new tree
        return new HuffmanTree(newTuple, left, right);
    }
    
    /**
     * Generates the Huffman code for c from the tree
     * 
     * @param c the character to encode
     * @return the Huffman code for c
     */
    public String getHuffmanCodeFor(char c){
        if(leftChild.value.hasCharacter(c)){
            if(leftChild.isLeaf()){
                // we're at the end ==> quit recursion
                return "0";
            } else {
                return "0" + leftChild.getHuffmanCodeFor(c);
            }
        } else if(rightChild.value.hasCharacter(c)) {
            if(rightChild.isLeaf()){
                // we're at the end ==> quit recursion
                return "1";
            } else {
                return "1" + rightChild.getHuffmanCodeFor(c);
            }
        } else {
            // character not found
            throw new IllegalArgumentException("Can't encode '" + c + "' because it is not in the Tree.");
        }
    }
    
    /**
     * whether this tree/node is at the edge of the tree
     * 
     * @return true if it's a leaf node
     */
    public boolean isLeaf() {
        return leftChild == null || rightChild == null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "I am a tree with " + value;
    }
}
