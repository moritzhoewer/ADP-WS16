/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 17.09.2016 
 * Aufgabe: Aufgabenblatt 1
 */

package adp.aufgabe1;

import java.util.function.Consumer;

import adp.util.AbstractPerformanceEvaluator;
import adp.util.Counter;

/**
 * Utility class to evaluate the performance of the different list
 * implementations through various tests
 *
 * @author Moritz Höwer
 * @version 2.0 - 17.10.2016
 */
public class ListPerformanceEvaluator extends AbstractPerformanceEvaluator {

    private Counter counter;

    private static final int MIN_SIZE = 10;
    private static final int MAX_SIZE = 100000;

    public ListPerformanceEvaluator() {
        counter = new Counter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.util.AbstractPerformanceEvaluator#performEvaluation()
     */
    @Override
    public void performEvaluation() {
        initMatlab(new String[]{"AL", "DL_AL", "LL"},
                new String[]{"insert_best", "retrieve_best", "delete_best",
                        "find", "size", "concat", "insert_worst",
                        "retrieve_worst", "delete_worst"});
        addKeyToMatlab("size");
        /*
         * System.out.println("Evaluating ArrayList"); for (int i = MIN_SIZE; i
         * <= MAX_SIZE; i *= 10) { System.out.println("Size: " + i); doTests(new
         * ArrayList<>(counter), new ArrayList<>(counter), i);
         * System.out.println(); }
         * 
         * System.out.println("Evaluating WeirdArrayList"); for (int i =
         * MIN_SIZE; i <= MAX_SIZE; i *= 10) { System.out.println("Size: " + i);
         * doTests(new DoubleLinkedArrayList<>(counter), new
         * DoubleLinkedArrayList<>(counter), i); System.out.println(); }
         * 
         * System.out.println("Evaluating LinkedList"); for (int i = MIN_SIZE; i
         * <= MAX_SIZE; i *= 10) { System.out.println("Size: " + i); doTests(new
         * LinkedList<>(counter), new LinkedList<>(counter), i);
         * System.out.println(); }
         * 
         * System.out.println("Done");
         */
        
        printMatlab();

    }

    /**
     * performs the tests on two lists (one for each order of operation -
     * forwards and backwards)
     * 
     * @param list1
     *            first list - forwards operations
     * @param list2
     *            second list - backwards operations
     * @param times
     *            how big are the lists / how often should each action be run
     */
    private void doTests(List<Integer> list1, List<Integer> list2, int times) {
        // insert
        /*
         * System.out.print("Inserting elements (asc): "); long result =
         * evaluateMassAction(i -> list1.insert(0, i), times);
         * System.out.println(result + " operations"); System.out.print(
         * "Inserting elements (desc): "); //result = evaluateMassAction(i ->
         * list2.insert(i, i), times); System.out.println(result + " operations"
         * );
         * 
         * // retrieve System.out.print("Retrieving first element: "); result =
         * evaluateMassAction(i -> list1.retrieve(0), 1);
         * System.out.println(result + " operations"); System.out.print(
         * "Retrieving last element: "); result = evaluateMassAction(i ->
         * list1.retrieve(times - 1), 1); System.out.println(result +
         * " operations");
         */

        /*
         * // size System.out.print("Counting elements: "); result =
         * evaluateMassAction(i -> list1.size(), 1); System.out.println(result +
         * " operations");
         * 
         * // find System.out.print("Finding first element: "); result =
         * evaluateMassAction(i -> list1.find(times - 1), 1);
         * System.out.println(result + " operations");
         * 
         * System.out.print("Finding middle element: "); result =
         * evaluateMassAction(i -> list1.find(times / 2), 1);
         * System.out.println(result + " operations");
         * 
         * System.out.print("Finding last element: "); result =
         * evaluateMassAction(i -> list1.find(0), 1); System.out.println(result
         * + " operations");
         * 
         * // concat System.out.print("Concatinating lists: "); result =
         * evaluateMassAction(i -> list1.concat(list2), 1);
         * System.out.println(result + " operations");
         * 
         * // delete System.out.print("Deleting elements (asc): "); result =
         * evaluateMassAction(i -> list1.delete(0), times);
         * System.out.println(result + " operations");
         * 
         * System.out.print("Deleting elements (desc): "); result =
         * evaluateMassAction(i -> list2.retrieve(times - i - 1), times);
         * System.out.println(result + " operations");
         */
    }

    /**
     * evaluates the function that is passed via a consumer by reseting the
     * counter and then calling it {@code times} amount of times
     * 
     * @param action
     *            the action to be performed
     * @param times
     *            how often should {@code action} be run
     * @return the value of the counter (how many operations were performed on
     *         the list)
     */
    private long evaluateMassAction(Consumer<Integer> action, int times) {
        counter.reset();
        for (int i = 0; i < times; i++) {
            action.accept(i);
        }
        return counter.getCount();
    }

    /**
     * Entry point
     * 
     * @param args
     *            commandline arguments
     */
    public static void main(String[] args) {
        ListPerformanceEvaluator eval = new ListPerformanceEvaluator();
        eval.performEvaluation();
    }

}
