/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 25.09.2016 
 * Aufgabe: Aufgabenblatt 2
 */

package adp.aufgabe02;

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

		for (int i = 4; i < limit; i++) {
			if ((i % 2) == 0) {
				primes[i] = false;
				// PERFORMANCE ANALYSIS
				counter.increment(); // assignment
				// PERFORMANCE ANALYSIS
				
				continue;
			}
			// PERFORMANCE ANALYSIS
			counter.incrementBy(2); // if-modulo
			// PERFORMANCE ANALYSIS

			for (int j = 3; j <= sqrt(i); j += 2) {
				if ((i % j) == 0) {
					if ((i != j)) {
						primes[i] = false;
						// PERFORMANCE ANALYSIS
						counter.increment(); // assignment
						// PERFORMANCE ANALYSIS
						break;
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
	public boolean[] findUsingEratosthenesBelow(int limit) {
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

		for (int primeIndex = 2; primeIndex < sqrt(limit); primeIndex++) {

			if (primes[primeIndex]) {
				for (int notPrimes = primeIndex* 2; notPrimes < limit; notPrimes += primeIndex) {

					primes[notPrimes] = false;

					// PERFORMANCE ANALYSIS
					counter.increment(); // assignment
					counter.incrementBy(3); // notPrimes-loop cycle
					// PERFORMANCE ANALYSIS
				}
				// PERFORMANCE ANALYSIS
				counter.incrementBy(2); // notPrimes-loop overhead
				// PERFORMANCE ANALYSIS
			}
			// PERFORMANCE ANALYSIS
			counter.increment(); // if
			counter.incrementBy(3); // primeIndex-loop cycle
			// PERFORMANCE ANALYSIS

		}
		// PERFORMANCE ANALYSIS
		counter.incrementBy(2); // primeIndex-loop overhead
		// PERFORMANCE ANALYSIS

		return primes;
	}

	/**
	 * tests whether the supplied number is a prime
	 * 
	 * {@code number} must be positive or 0
	 * 
	 * @param number
	 *            the number to test
	 * @return whether {@code number} is a test
	 */
	public boolean isPrime(int number) {
		// number is invalid
		if (number < 0) {
			throw new IllegalArgumentException("Number must be positive or 0!");
		}

		//easy cases first
		
		// PERFORMANCE ANALYSIS
		counter.increment(); // if
		// PERFORMANCE ANALYSIS
		if (number < 2) {
			return false;
		}
		
		// PERFORMANCE ANALYSIS
		counter.increment(); // if
		// PERFORMANCE ANALYSIS
		if (number == 2) {
			return true;
		}
		
		// now we need to be more generic

		// PERFORMANCE ANALYSIS
		counter.incrementBy(2); // if with modulo
		// PERFORMANCE ANALYSIS

		// check if it's divisible by 2
		if ((number % 2) == 0) {
			return false;

		}

		// PERFORMANCE ANALYSIS
		counter.incrementBy(2); // for-overhead
		// PERFORMANCE ANALYSIS

		// check for divisibility by odd numbers up to the squareroot
		for (int i = 3; i <= sqrt(number); i += 2) {

			// PERFORMANCE ANALYSIS
			counter.incrementBy(2); // if with modulo
			// PERFORMANCE ANALYSIS

			if ((number % i) == 0) {
				return false;
			}

			// PERFORMANCE ANALYSIS
			counter.incrementBy(3); // for cycle
			// PERFORMANCE ANALYSIS

		}

		// not divisible ==> prime
		return true;

	}
}
