/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.10.2016 
 * Aufgabe: Aufgabenblatt 4
 */

package adp.aufgabe4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Provides methods to evaluate the performance of bubble sort and insertionsort
 * on an ArrayList
 *
 * @author Moritz Höwer
 * @version 1.0 - 08.10.2016
 */
public class SortingPerformanceEvaluator {

	/**
	 * the Randomizer for filling the List
	 */
	private Random random;

	/**
	 * how often is the list sorted for evaluation
	 */
	private static final int NUMBER_OF_SORTS = 100;

	/**
	 * maximum list size to test
	 */
	private static final int MAX_SIZE = 10000-1;

	/**
	 * stores the values to generate input for matlab
	 */
	private HashMap<String, ArrayList<Long>> forMatlab;

	public SortingPerformanceEvaluator(long seed) {
		random = new Random(seed);
		forMatlab = new HashMap<>();
	}

	/**
	 * Fills the list with random numbers
	 * 
	 * @param list
	 *            the list to fill
	 * @param desiredSize
	 *            how many items are to be inserted
	 */
	private void fillListRandomly(SortableArrayList list, int desiredSize) {
		list.empty();
		int i = 0;
		int n = 0;

		while (n < desiredSize) {
			// decide whether current i should be added
			if (random.nextBoolean()) {
				int index = random.nextInt(n + 1);
				list.insert(index, i);
				n++;
			}
			i++;
		}

	}

	/**
	 * evaluates perfomance by running {@code function} {@code NUMBER_OF_SORTS}
	 * times on {@code list} with a list size of {@code size}.
	 * 
	 * @param list
	 *            the list to perform function on
	 * @param size
	 *            the size of the list
	 * @param function
	 *            the function to perform
	 * @return ns per operation
	 */
	private long[] evaluatePerformanceForSize(SortableArrayList list, int size,
			Runnable function) {
		
		// counters for ns
		long nanosAccumulator= 0;
		long nanosMin = Long.MAX_VALUE;
		long nanosMax = Long.MIN_VALUE;
		long nanoStart, nanoEnd;
		
		for (int i = 0; i < NUMBER_OF_SORTS; i++) {
			fillListRandomly(list, size);
			
			nanoStart = System.nanoTime();
			function.run();
			nanoEnd = System.nanoTime();
			
			long nanos = (nanoEnd - nanoStart);
			nanosMin = Math.min(nanos,  nanosMin);
			nanosMax = Math.max(nanos,  nanosMax);
			
			nanosAccumulator += nanos;
		}
		return new long[]{nanosMin, nanosMax, nanosAccumulator / NUMBER_OF_SORTS};
	}

	/**
	 * Evaluates {@code function} on {@code list}
	 * 
	 * @param name
	 *            the name of the function for HashMap
	 * @param list
	 *            the list to performs operations in
	 * @param function
	 *            the function to perform
	 */
	private void evaluateAlgorithm(String name, SortableArrayList list,
			Runnable function) {
		System.out.println("Evaluating " + name);
		for (int i = 10; i <= MAX_SIZE; i *= 10) {
			// fill List randomly once so array size is already correct
			fillListRandomly(list, i);

			System.out.print("Size " + i + " ...");
			long[] ns = evaluatePerformanceForSize(list, i, function);
			
			// add result to matlab
			forMatlab.get(name + "_min").add(ns[0]);
			forMatlab.get(name + "_max").add(ns[1]);
			forMatlab.get(name + "_avg").add(ns[2]);
			
			// print result in a nice form
			System.out.format("%s | %s | %s per sorting\n", makeNice(ns[0]), makeNice(ns[2]), makeNice(ns[1]));
		}

		System.out.println("Done");
	}

	/**
	 * performs the evaluation
	 */
	private void performEvaluation() {
		// init HashMap
		forMatlab.put("sizes", new ArrayList<>());
		
		forMatlab.put("BS_min", new ArrayList<>());
		forMatlab.put("BS_max", new ArrayList<>());
		forMatlab.put("BS_avg", new ArrayList<>());
		
		forMatlab.put("IS_min", new ArrayList<>());
		forMatlab.put("IS_max", new ArrayList<>());
		forMatlab.put("IS_avg", new ArrayList<>());
		
		for (int i = 10; i <= MAX_SIZE; i *= 10) {
			forMatlab.get("sizes").add(new Long(i));
		}
		
		SortableArrayList list = new SortableArrayList();

		evaluateAlgorithm("BS", list, list::doBubbleSort);
		evaluateAlgorithm("IS", list, list::doInsertionSort);
		
		// output matlab
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
	 * Converts the nanoseconds to the most appropriate unit
	 * 
	 * @param nanos
	 *            number of nanoseconds
	 * @return String representation in ns, µs or ms
	 */
	private String makeNice(long nanos) {
		if (nanos < 1000) {
			return nanos + " ns";
		} else {
			double us = nanos / 1000.0;
			if (us < 1000) {
				return String.format("%.2f µs", us);
			} else {
				double ms = us / 1000;
				return String.format("%.2f ms", ms);
			}
		}
	}

	/**
	 * Program entry point
	 * 
	 * @param args
	 *            comand line args
	 */
	public static void main(String[] args) {
		SortingPerformanceEvaluator eval = new SortingPerformanceEvaluator(
				"123Kartoffelbrei".hashCode());
		
		eval.performEvaluation();

	}

}
