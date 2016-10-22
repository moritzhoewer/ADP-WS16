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
import java.util.function.IntUnaryOperator;

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

        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 10) {
            addValueToMatlab("size", i);
        }

        System.out.println("Evaluating ArrayList");
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 10) {
            doTests("AL", new ArrayList<>(counter), new ArrayList<>(counter), i,
                    x -> x, x -> 0);
        }

        System.out.println("\n\nEvaluating LinkedList");
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 10) {
            doTests("LL", new LinkedList<>(counter), new LinkedList<>(counter),
                    i, x -> 0, x -> x);
        }

        System.out.println("\n\nEvaluating DoubleLinkedArrayList");
        for (int i = MIN_SIZE; i <= MAX_SIZE; i *= 10) {
            doTests("DL_AL", new DoubleLinkedArrayList<>(counter),
                    new DoubleLinkedArrayList<>(counter), i, x -> 0,
                    x -> x / 2);
        }

        System.out.println("\n");

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
    private void doTests(String name, List<Integer> list1, List<Integer> list2,
            int times, IntUnaryOperator bestIndex,
            IntUnaryOperator worstIndex) {

        long result;
        System.out.println("====== Size: " + times + " ========");

        // insert
        System.out.print("Inserting elements (best): ");
        result = evaluateMassAction(
                x -> list1.insert(bestIndex.applyAsInt(x), x), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "insert_best", result);

        System.out.print("Inserting elements (worst): ");
        result = evaluateMassAction(
                x -> list2.insert(worstIndex.applyAsInt(x), x), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "insert_worst", result);

        // retrieve
        System.out.print("Retrieving elements (best): ");
        result = evaluateMassAction(
                x -> list1.retrieve(bestIndex.applyAsInt(x)), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "retrieve_best", result);

        System.out.print("Retrieving elements (worst): ");
        result = evaluateMassAction(
                x -> list2.retrieve(worstIndex.applyAsInt(x)), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "retrieve_worst", result);

        // size
        System.out.print("Counting elements: ");
        result = evaluateMassAction(x -> list1.size(), 1);
        System.out.println(result + " operations");
        addValueToMatlab(name, "size", result);

        // find
        System.out.print("Finding element: ");
        result = evaluateMassAction(x -> list1.find(times), 1);
        System.out.println(result + " operations");
        addValueToMatlab(name, "find", result);

        // concat
        System.out.print("Concatinating lists: ");
        result = evaluateMassAction(x -> list1.concat(list2), 1);
        System.out.println(result + " operations");
        addValueToMatlab(name, "concat", result);

        // delete
        // list 1 is double the size it's supposed to be
        // delete everything from list 1
        evaluateMassAction(
                x -> list1.delete(0), list1.size() - 1);
        // insert elements again
        evaluateMassAction(x -> list1.insert(bestIndex.applyAsInt(x), x),
                times);

        System.out.print("Deleting elements (best): ");
        result = evaluateMassAction(
                x -> list1.delete(bestIndex.applyAsInt(times - x - 1)), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "delete_best", result);

        System.out.print("Deleting elements (worst): ");
        result = evaluateMassAction(
                x -> list2.delete(worstIndex.applyAsInt(times - x - 1)), times);
        result /= times;
        System.out.println(result + " operations");
        addValueToMatlab(name, "delete_worst", result);
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
