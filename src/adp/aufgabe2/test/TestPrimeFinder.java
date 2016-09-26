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
 * @author Moritz Höwer, Jesko Treffler
 * @version 1.1 - 26.09.2016
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
	
	/**
	 * Test method for
	 * {@link adp.aufgabe2.PrimeFinder#findUsingEratosthenesBelow(int)}.
	 */
	@Test
	public void testEratosthenesPrimeFinder() {
		PrimeFinder pf = new PrimeFinder();
		testPrimeFindingAlgorithm(pf::findUsingEratosthenesBelow);
	}
	
	/**
	 * Test method for
	 * {@link adp.aufgabe2.PrimeFinder#isPrime(int)}.
	 */
	@Test
	public void testIsPrime() {
		PrimeFinder pf = new PrimeFinder();
		
		try {
			pf.isPrime(-1);
			fail("Expected an IllegalArgumentExcpetion here");
		} catch (IllegalArgumentException ex) {
			// this is the expected outcome
		}

		assertThat("0 should not be prime!", pf.isPrime(0), is(false));
		assertThat("1 should not be prime!", pf.isPrime(1), is(false));
		assertThat("2 should be a prime!", pf.isPrime(2), is(true));
		assertThat("3 should be a prime!", pf.isPrime(3), is(true));
		assertThat("4 should not be prime!", pf.isPrime(4), is(false));
		assertThat("5 should be a prime!", pf.isPrime(5), is(true));
		assertThat("997 should be a prime!", pf.isPrime(997), is(true));
		assertThat("1000 should not be prime!", pf.isPrime(1000), is(false));
	}
}
