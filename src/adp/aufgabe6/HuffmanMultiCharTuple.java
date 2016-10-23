/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 22.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * A Huffman tuple consists of one or more characters and the amount of times they're present
 *
 * @author Moritz Höwer
 * @version 1.0 - 22.10.2016
 */
public class HuffmanMultiCharTuple implements Comparable<HuffmanMultiCharTuple> {
    /**
     * the characters
     */
    private Set<Character> characters;

    /**
     * the amount of times the characters are present
     */
    private int times;

    public HuffmanMultiCharTuple() {
        this(0);
    }
    
    public HuffmanMultiCharTuple(int times, Character...chars){
        this.times = times;
        characters = new HashSet<>(Arrays.asList(chars));
    }
    
    public HuffmanMultiCharTuple(int times, Set<Character> charSet){
        this.times = times;
        characters = new HashSet<>(charSet);
    }

    /**
     * @return the times
     */
    public int getTimes() {
        return times;
    }

    /**
     * @param times
     *            the times to set
     */
    public void setTimes(int times) {
        this.times = times;
    }

    /**
     * adds another occurrence to this
     */
    public void addOcurence() {
        times++;
    }

    /**
     * @return the character set
     */
    public Set<Character> getCharacters() {
        return characters;
    }
    
    /**
     * Adds a character to the set
     * 
     * @param c the character to add
     */
    public void addCharacter(char c){
        characters.add(c);
    }
    
    /**
     * does this Tuple contain the character
     * 
     * @param c the character to look for
     * @return true if the character is in the set
     */
    public boolean hasCharacter(char c){
        return characters.contains(c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        return characters.hashCode();
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof HuffmanMultiCharTuple)){
            return false;
        }
        HuffmanMultiCharTuple other = (HuffmanMultiCharTuple) obj;
        return characters == other.characters;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "(" + characters + " -> " + times + "x)";
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    public int compareTo(HuffmanMultiCharTuple o) {
        return new Integer(times).compareTo(o.times);
    }

}
