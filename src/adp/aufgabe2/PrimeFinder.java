/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 25.09.2016 
 * Aufgabe: Aufgabenblatt 2
 */

package adp.aufgabe2;

import static java.lang.Math.sqrt;

import java.util.Arrays;

import adp.util.Counter;

/**
 * Contains various algorithms for finding primes
 *
 * @author Moritz Höwer, Jesko Treffler
 * @version 1.1 - 25.09.2016 21:40
 */
public class PrimeFinder {

	/**
	 * for performance analysis
	 */
	private Counter counter;

	public PrimeFinder() {
		this(new Counter());
	}

	public PrimeFinder(Counter counter) {
		this.counter = counter;
	}

	/**
	 * prints the primes stored in a boolean array
	 * 
	 * @param numbers
	 *            the boolean array where the primes are stored
	 */
	public static void printPrimes(boolean[] numbers) {
		// counter
		int count = 0;
		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i]) {
				System.out.format("%2d, ", i);

				count++;

				// break line every 10 primes
				if (count % 10 == 0) {
					System.out.println();
				}
			}
		}
		System.out.println();
		System.out.println("In total " + count + " primes were found.");
	}

	/**
	 * finds all primes below {@code limit} using brute force method.
	 * 
	 * {@code limit} must be greater than or equal to 2
	 * 
	 * @param limit
	 *            the upper limit
	 * 
	 * @return the boolean array containing the primes
	 */
	public boolean[] bruteForcePrimesBelow(int limit) {
		// check if limit is valid
		if (limit < 2) {
			throw new IllegalArgumentException(
					"Limit is too low! - should be at least 2");
		}
		// create array to store primes
		boolean[] primes = new boolean[limit];

		// assume all numbers are prime (true)
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;

		// PERFORMANCE ANALYSIS
		counter.incrementBy(limit * 3 + 2); // fill
		counter.incrementBy(2); // assignments
		// PERFORMANCE ANALYSIS

		for (int i = 2; i < limit; i++) {
			for (int j = 2; j < limit; j++) {
				if ((i % j) == 0) {
					if ((i != j)) {
						primes[i] = false;
						// PERFORMANCE ANALYSIS
						counter.increment(); // assignment
						// PERFORMANCE ANALYSIS
					}
					// PERFORMANCE ANALYSIS
					counter.increment(); // if
					// PERFORMANCE ANALYSIS
				}
				// PERFORMANCE ANALYSIS
				counter.incrementBy(2); // if-modulo
				counter.incrementBy(3); // j-loop cycle
				// PERFORMANCE ANALYSIS
			}
			// PERFORMANCE ANALYSIS
			counter.incrementBy(2); // j-loop overhead
			counter.incrementBy(3); // i-loop cycle
			// PERFORMANCE ANALYSIS
		}
		// PERFORMANCE ANALYSIS
		counter.incrementBy(2); // i-loop overhead
		// PERFORMANCE ANALYSIS

		return primes;

	}

	/**
	 * finds all primes below {@code limit} using an improved brute force
	 * method.
	 * 
	 * {@code limit} must be greater than or equal to 2
	 * 
	 * @param limit
	 *            the upper limit
	 * 
	 * @return the boolean array containing the primes
	 */
	public boolean[] improvedBruteForcePrimesBelow(int limit) {
		// check if limit is valid
		if (limit < 2) {
			throw new IllegalArgumentException(
					"Limit is too low! - should be at least 2");
		}
		// create array to store primes
		boolean[] primes = new boolean[limit];

		// assume all numbers are prime (true)
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;

		// PERFORMANCE ANALYSIS
		counter.incrementBy(limit * 3 + 2); // fill
		counter.incrementBy(2); // assignments
		// PERFORMANCE ANALYSIS

		for (int i = 3; i < limit; i++) {
			if ((i % 2) == 0) {
				primes[i] = false;
				// PERFORMANCE ANALYSIS
				counter.increment(); // assignment
				// PERFORMANCE ANALYSIS
			}
			// PERFORMANCE ANALYSIS
			counter.incrementBy(2); // if-modulo
			// PERFORMANCE ANALYSIS

			for (int j = 3; j <= (int) sqrt(i); j += 2) {
				if ((i % j) == 0) {
					if ((i != j)) {
						primes[i] = false;
						// PERFORMANCE ANALYSIS
						counter.increment(); // assignment
						// PERFORMANCE ANALYSIS
					}
					// PERFORMANCE ANALYSIS
					counter.increment(); // if
					// PERFORMANCE ANALYSIS
				}
				// PERFORMANCE ANALYSIS
				counter.incrementBy(2); // if-modulo
				counter.incrementBy(3); // j-loop cycle
				// PERFORMANCE ANALYSIS
			}
			// PERFORMANCE ANALYSIS
			counter.incrementBy(2); // j-loop overhead
			counter.incrementBy(3); // i-loop cycle
			// PERFORMANCE ANALYSIS
		}
		// PERFORMANCE ANALYSIS
		counter.incrementBy(2); // i-loop overhead
		// PERFORMANCE ANALYSIS

		return primes;

	}
	
	/**
	 * finds all primes below {@code limit} using the Sieve of Eratosthenes
	 * 
	 * 
	 * {@code limit} must be greater than or equal to 2
	 * 
	 * @param limit
	 *            the upper limit
	 * 
	 * @return the boolean array containing the primes
	 */
	public boolean[] EratosthenesPrimeFinder(int limit) {
		
		// check if limit is valid
		if (limit < 2) {
			throw new IllegalArgumentException(
					"Limit is too low! - should be at least 2");
		}
		// create array to store primes
		boolean[] primes = new boolean[limit];
		
		// assume all numbers are prime (true)
		Arrays.fill(primes, true);
		primes[0] = false;
		primes[1] = false;
		for (int PrimeIndex=2;PrimeIndex<sqrt(limit);PrimeIndex++){
			counter.incrementBy(2); //Vergleichsoperation und Ikrementierung
			
			for(int NotPrimes=PrimeIndex*2;NotPrimes<limit;NotPrimes+=PrimeIndex){
				primes[NotPrimes]=false;
				counter.incrementBy(2);
			}
		}
		return primes;		
	}
}