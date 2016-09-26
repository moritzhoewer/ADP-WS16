/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 25.09.2016 
 * Aufgabe: Aufgabenblatt 2
 */

package adp.aufgabe2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import adp.util.Counter;

/**
 * Evaluates the performance of the various algorithms
 *
 * @author Moritz Höwer
 * @version 1.0 - 26.09.2016
 */
public class PrimePerformanceEvaluator {
	private Counter counter;

	private static final int MIN_SIZE = 10;
	private static final int MAX_SIZE = 10000;
	
	private HashMap<String, ArrayList<Long>> forMatlab;

	public PrimePerformanceEvaluator() {
		counter = new Counter();
		forMatlab = new HashMap<>();
	}

	/**
	 * performs the evaluation
	 */
	public void performEvaluation() {
		PrimeFinder pf = new PrimeFinder(counter);
		
		//init HashMap
		forMatlab.put("sizes", new ArrayList<>());
		forMatlab.put("BF", new ArrayList<>());
		forMatlab.put("I_BF", new ArrayList<>());
		forMatlab.put("ERAS", new ArrayList<>());

		for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 10) {
			System.out.println("Size: " + i);
			// add size to HashMap for Matlab
			forMatlab.get("sizes").add(new Long(i));
			
			evaluate("BF", pf::bruteForcePrimesBelow, i);
			evaluate("I_BF", pf::improvedBruteForcePrimesBelow, i);
			evaluate("ERAS", pf::findUsingEratosthenesBelow, i);
			
			System.out.println();
		}
		System.out.println("Done");
		
		forMatlab.forEach((s, a) -> {
			System.out.print(s + " = [");
			for(int i = 0; i < a.size(); i++){
				System.out.print(a.get(i));
				if( i != a.size() - 1){
					System.out.print(", ");
				}
			}
			System.out.println("];");
		}); 

	}

	/**
	 * evaluates the function with parameter {@code n}.
	 * 
	 * @param name
	 *            the name for printing and matlab
	 * @param function
	 *            the function to perform
	 * @param n
	 *            parameter for the function
	 */
	private void evaluate(String name, Consumer<Integer> function, int n) {
		System.out.print(name + ": ");
		counter.reset();
		function.accept(n);
		System.out.println(counter.getCount() + " operations");
		
		//add count to HashMap for Matlab
		forMatlab.get(name).add(counter.getCount());

	}

	/**
	 * entry point
	 * 
	 * @param args
	 *            commandline args
	 */
	public static void main(String[] args) {
		PrimePerformanceEvaluator ppe = new PrimePerformanceEvaluator();
		ppe.performEvaluation();
	}
}
