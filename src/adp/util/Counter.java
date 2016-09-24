/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.util;

/**
 * Utility class for counting operations
 *
 * @author Moritz Höwer
 * @version 1.0 - 17.09.2016
 */
public class Counter {

	/**
	 * the total count
	 */
	private long count;
	
	public Counter(){
		count = 0;
	}
	
	/**
	 * increments the counter by 1
	 */
	public void increment(){
		count++;
	}
	
	/**
	 * increments the counter by {@code amount} amount
	 * @param amount the amount to add
	 */
	public void incrementBy(int amount){
		count += amount;
	}
	
	/**
	 * resets the counter to 0
	 */
	public void reset(){
		count = 0;
	}
	
	public long getCount(){
		return count;
	}
	
}
