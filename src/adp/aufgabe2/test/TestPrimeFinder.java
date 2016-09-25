/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 25.09.2016 
 * Aufgabe: Aufgabenblatt 2
 */

package adp.aufgabe2.test;

import static org.junit.Assert.*;

import java.util.function.Function;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe2.PrimeFinder;

/**
 * test class for PrimeFinder algorithms
 *
 * @author Moritz Höwer
 * @version 1.0 - 25.09.2016
 */
public class TestPrimeFinder {

	/**
	 * counts the primes in a boolean[]
	 * 
	 * @param primes
	 *            the boolean[] containing the primes
	 * @return the number of primes in {@code primes}
	 */
	private int countPrimes(boolean[] primes) {
		int count = 0;
		for (boolean b : primes) {
			if (b) {
				count++;
			}
		}
		return count;
	}

	private void testPrimeFindingAlgorithm(
			Function<Integer, boolean[]> function) {
		boolean[] result;

		final boolean[] PRIMES_BELOW_10 = {false, false, true, true, false,
				true, false, true, false, false};

		try {
			function.apply(1);
			fail("Expected an IllegalArgumentExcpetion here");
		} catch (IllegalArgumentException ex) {
			// this is the expected outcome
		}

		result = function.apply(10);
		assertThat("Wrong amount of primes found", countPrimes(result), is(4));
		assertThat("Primes found are wrong", result, is(PRIMES_BELOW_10));

		result = function.apply(100);
		assertThat("Wrong amount of primes found", countPrimes(result), is(25));

		result = function.apply(1000);
		assertThat("Wrong amount of primes found", countPrimes(result), is(168));
	}

	/**
	 * Test method for
	 * {@link adp.aufgabe2.PrimeFinder#bruteForcePrimesBelow(int)}.
	 */
	@Test
	public void testBruteForcePrimesBelow() {
		PrimeFinder pf = new PrimeFinder();
		testPrimeFindingAlgorithm(pf::bruteForcePrimesBelow);
	}
	
	/**
	 * Test method for
	 * {@link adp.aufgabe2.PrimeFinder#improvedBruteForcePrimesBelow(int)}.
	 */
	@Test
	public void testImprovedBruteForcePrimesBelow() {
		PrimeFinder pf = new PrimeFinder();
		testPrimeFindingAlgorithm(pf::improvedBruteForcePrimesBelow);
	}
}
