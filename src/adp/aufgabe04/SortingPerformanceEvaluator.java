/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 08.10.2016 
 * Aufgabe: Aufgabenblatt 4
 */

package adp.aufgabe04;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import adp.util.AbstractPerformanceEvaluator;

/**
 * Provides methods to evaluate the performance of bubble sort and insertionsort
 * on an ArrayList
 *
 * @author Moritz Höwer, Jesko Treffler
 * @version 1.0 - 11.10.2016
 */
public class SortingPerformanceEvaluator extends AbstractPerformanceEvaluator {

    /**
     * the randomizer for filling the List
     */
    protected Random random;

    /**
     * the seed for the randomizer
     */
    protected long seed;

    /**
     * how often is the list sorted for evaluation
     */
    private static final int NUMBER_OF_SORTS = 100;

    /**
     * maximum list size to test
     */
    private static final int MAX_SIZE = 10000;

    public SortingPerformanceEvaluator(long seed) {
        this.seed = seed;
        random = new Random(seed);
    }

    /**
     * Fills the list with random numbers
     * 
     * @param list
     *            the list to fill
     * @param desiredSize
     *            how many items are to be inserted
     */
    protected void fillListRandomly(SortableArrayList list, int desiredSize) {
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
        long nanosAccumulator = 0;
        long nanosMin = Long.MAX_VALUE;
        long nanosMax = Long.MIN_VALUE;
        long nanoStart, nanoEnd;

        for (int i = 0; i < NUMBER_OF_SORTS; i++) {
            fillListRandomly(list, size);

            nanoStart = System.nanoTime();
            function.run();
            nanoEnd = System.nanoTime();

            long nanos = (nanoEnd - nanoStart);
            nanosMin = Math.min(nanos, nanosMin);
            nanosMax = Math.max(nanos, nanosMax);

            nanosAccumulator += nanos;
        }
        return new long[]{nanosMin, nanosMax,
                nanosAccumulator / NUMBER_OF_SORTS};
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
        for (int i = 1000; i <= MAX_SIZE; i += 1000) {
            // fill List randomly once so array size is already correct
            fillListRandomly(list, i);

            System.out.print("Size " + i + " ...");
            long[] ns = evaluatePerformanceForSize(list, i, function);

            // add result to matlab
            addValueToMatlab(name, "MIN", ns[0]);
            addValueToMatlab(name, "AVG", ns[2]);
            addValueToMatlab(name, "MAX", ns[1]);

            // print result in a nice form
            System.out.format("%s | %s | %s per sorting\n", nanosToHumanReadableString(ns[0]),
                    nanosToHumanReadableString(ns[2]), nanosToHumanReadableString(ns[1]));
        }

        System.out.println("Done");
    }

    /*
     * performs the evaluation
     */
    @Override
    public void performEvaluation() {
        // init Matlab output
        initMatlab(new String[]{"BS", "BS_better", "BS_best", "IS"},
                new String[]{"MIN", "AVG", "MAX"});
        addKeyToMatlab("sizes");

   
        for (int i = 1000; i <= MAX_SIZE; i += 1000) {
            addValueToMatlab("sizes", i);
        }

        SortableArrayList list = new SortableArrayList();

        random = new Random(seed);
        evaluateAlgorithm("BS", list, list::doBubbleSort);

        random = new Random(seed);
        evaluateAlgorithm("BS_better", list, list::doBubbleSortfaster);

        random = new Random(seed);
        evaluateAlgorithm("BS_best", list, list::doBubbleSortEvenFaster);

        random = new Random(seed);
        evaluateAlgorithm("IS", list, list::doInsertionSort);

        // output matlab
        printMatlab();
    }

    /**
     * Program entry point
     * 
     * @param args
     *            comand line args
     */
    public static void main(String[] args) {
        AbstractPerformanceEvaluator eval = new SortingPerformanceEvaluator(
                "123Kartoffelkotze".hashCode());

        eval.performEvaluation();
        /*
         * SortableArrayList list = new SortableArrayList();
         * eval.fillListRandomly(list, 10); System.out.println(list);
         * list.doBubbleSortEvenFaster(); System.out.println(list);
         */
    }

}
