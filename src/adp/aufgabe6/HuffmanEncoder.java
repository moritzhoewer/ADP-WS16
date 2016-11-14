/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 26.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Utility for encoding and decoding using {@link HuffmanTree}s
 *
 * @author Moritz Höwer
 * @version 1.0 - 26.10.2016
 */
public class HuffmanEncoder {

    /**
     * reference to the {@link HuffmanTree} with encoding information
     */
    private HuffmanTree encodingTree;

    public HuffmanEncoder() {
        this(null);
    }
    public HuffmanEncoder(HuffmanTree tree) {
        encodingTree = tree;
    }

    /**
     * counts the characters in the text
     * 
     * @param text
     *            the text to analyze
     * @return an ArrayList containing {@link HuffmanSingleCharTuple}s for every
     *         character
     */
    private ArrayList<HuffmanSingleCharTuple> countChars(String text) {
        ArrayList<HuffmanSingleCharTuple> occurrences = new ArrayList<>();

        // convert to array
        char[] chars = text.toCharArray();

        // go through array
        for (char c : chars) {
            HuffmanSingleCharTuple tup = new HuffmanSingleCharTuple(1, c);

            // check if it's the first occurrence or not
            if (occurrences.contains(tup)) {
                occurrences.get(occurrences.indexOf(tup)).addOcurence();
            } else {
                occurrences.add(tup);
            }
        }
        return occurrences;
    }

    /**
     * Generates a {@link HuffmanTree} from the text
     * 
     * @param text
     *            the text to use for generation
     */
    public void generateHuffmanTree(String text) {
        ArrayList<HuffmanSingleCharTuple> occurrences = countChars(text);

        // convert occurrences List to HuffmanTree (with the encoding)
        List<HuffmanTree> sortedList = occurrences.stream() // create stream
                .sorted() // sort by # of occurrences
                .map(HuffmanTree::new) // convert to trees
                .collect(Collectors.toList()); // convert back to list
        
        while(sortedList.size() > 1){
        	HuffmanTree newTree = sortedList.remove(0).combineWith(sortedList.remove(0));
        	int i;
        	for(i = 0; i < sortedList.size(); i++){
        		if (sortedList.get(i).compareTo(newTree) > 0) {
                    // current is bigger ==> insert before
                    break;
                }
        	}
        	sortedList.add(i, newTree);
        }
        
        encodingTree = sortedList.get(0);

    }

    /**
     * encodes a String of text to a string of Bits using the {@link HuffmanTree}
     * 
     * @param text
     *            the text to encode
     * @return the encoded text as a String of Bits
     * @throws IllegalArgumentException
     *             if text contains invalid characters (ones that were not in
     *             the text used to generate the {@link HuffmanTree})
     * @throws IllegalStateException
     *             if the underlying {@link HuffmanTree} is not well formed or
     *             doesn't exist
     */
    public String encode(String text) {
        if (encodingTree == null) {
            throw new IllegalStateException(
                    "Cannot decode, no HuffmanTree is set/generated!");
        }
        // get chars from String
        char[] chars = text.toCharArray();

        // create StringBuilder for code
        StringBuilder codeBuilder = new StringBuilder();

        // encode all the chars
        for (char c : chars) {
            codeBuilder.append(encodingTree.getHuffmanCodeFor(c));
        }

        // return code
        return codeBuilder.toString();
    }

    /**
     * decodes a String of Bits to a String of text
     * 
     * @param code
     *            the String of bits in the encoded form
     * @return the decoded text for the Supplied code
     * @throws IllegalArgumentException
     *             if code contains invalid characters
     * @throws IllegalStateException
     *             if the underlying {@link HuffmanTree} is not well formed or
     *             doesn't exist
     */
    public String decode(String code) {
        if (encodingTree == null) {
            throw new IllegalStateException(
                    "Cannot decode, no HuffmanTree is set/generated!");
        }
        // wrap code in StringBuilder
        StringBuilder decodeBuffer = new StringBuilder(code);

        // create StringBuilder for clear text
        StringBuilder textBuilder = new StringBuilder();

        // while there is more code ==> encode it
        while (decodeBuffer.length() > 0) {
            textBuilder
                    .append(encodingTree.getCharForHuffmanCode(decodeBuffer));
        }
        // return the clear text
        return textBuilder.toString();
    }
}
