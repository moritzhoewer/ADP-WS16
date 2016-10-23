/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 22.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

/**
 * A Huffman tuple consists of a character and the amount of times it's present
 *
 * @author Moritz Höwer
 * @version 1.0 - 22.10.2016
 */
public class HuffmanSingleCharTuple extends HuffmanMultiCharTuple {
    
    public HuffmanSingleCharTuple(char c) {
        this(0, c);
    }
    
    public HuffmanSingleCharTuple(int times, char c) {
        super(times, c);
    }

    /**
     * @return the character
     */
    public char getCharacter() {
        return getCharacters().iterator().next();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "('" + getCharacter() + "' -> " + getTimes() + "x)";
    }
}
