/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 23.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

import java.util.ArrayList;

/**
 * The application
 *
 * @author Moritz Höwer
 * @version 1.0 - 23.10.2016
 */
public class HuffmanApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        final String TEXT = "MISSISIPPI";

        // missing analysis => putting dummy code
        ArrayList<HuffmanSingleCharTuple> occurrences = new ArrayList<>();
        occurrences.add(new HuffmanSingleCharTuple(1, 'M'));
        occurrences.add(new HuffmanSingleCharTuple(2, 'P'));
        occurrences.add(new HuffmanSingleCharTuple(4, 'I'));
        occurrences.add(new HuffmanSingleCharTuple(4, 'S'));

        // convert occurrences List to HuffmanTree (with the encoding)
        // @Jesko - wärste mal zu der Streams Vorlesung von Jenke gekommen ;)
        // die sind nämlich ziemlich aweseome
        HuffmanTree encodingTree = occurrences.stream()
                .sorted() // sort by # of occurrences
                .map(HuffmanTree::new) // convert to trees
                .reduce(new HuffmanTree(), HuffmanTree::combineWith);

        // DEBUG: print out encoding table
        System.out.println(encodingTree);
        TEXT.chars().distinct().forEach(i -> {
            char c = (char) i;
            System.out.println(c + " -> " + encodingTree.getHuffmanCodeFor(c));
        });

        // missing: print encoded Text

    }

}
