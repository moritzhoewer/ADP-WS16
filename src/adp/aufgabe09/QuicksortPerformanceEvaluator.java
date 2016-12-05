/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 05.12.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe09;

import adp.aufgabe04.SortingPerformanceEvaluator;
import adp.aufgabe09.ImprovedSortableArrayList.PivotSelection;
import adp.util.Counter;

/**
 *
 *
 * @author Moritz Höwer
 * @version 1.0 - 05.12.2016
 */
public class QuicksortPerformanceEvaluator extends SortingPerformanceEvaluator {

    private static final int MAX_SIZE = 100000;

    private Counter counter;

    public QuicksortPerformanceEvaluator(long seed) {
        super(seed);
        counter = new Counter();
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.util.AbstractPerformanceEvaluator#performEvaluation()
     */
    @Override
    public void performEvaluation() {
        addKeyToMatlab("size");
        for (int i = 30; i <= MAX_SIZE; i *= 10) {
            addValueToMatlab("size", i);
        }
        for (PivotSelection sel : PivotSelection.values()) {
            System.out.println("\nEvaluating " + sel.toString());
            addKeyToMatlab(sel + "_rand");
            addKeyToMatlab(sel + "_rev");
            addKeyToMatlab(sel + "_maxmiddle");
            evaluate(sel);
        }
        System.out.println("\n");
        printMatlab();

    }

    private void evaluate(PivotSelection sel) {
        ImprovedSortableArrayList list = new ImprovedSortableArrayList(sel,
                counter);

        for (int i = 35; i <= MAX_SIZE; i *= 10) {
            randomly(sel, list, i);
            reverseOrder(sel, list, i);
            maxMiddle(sel, list, i);
        }
    }

    private void maxMiddle(PivotSelection sel,
            ImprovedSortableArrayList list, int size) {
        System.out.print("Size (maxmiddle): " + size + " -> ");
        for (int i = 0; i < size; i++) {
            list.insert(i / 2, size - i);
        }
        counter.reset();
        list.doQuicksort();
        addValueToMatlab(sel + "_maxmiddle", counter.getCount());
        System.out.println(counter.getCount());

    }

    private void reverseOrder(PivotSelection sel,
            ImprovedSortableArrayList list, int size) {
        System.out.print("Size (rev): " + size + " -> ");
        for (int i = 0; i < size; i++) {
            list.insert(i, size - i);
        }
        counter.reset();
        list.doQuicksort();
        addValueToMatlab(sel + "_rev", counter.getCount());
        System.out.println(counter.getCount());

    }

    private void randomly(PivotSelection sel, ImprovedSortableArrayList list,
            int size) {
        System.out.print("Size (rand): " + size + " -> ");
        fillListRandomly(list, size);
        counter.reset();
        list.doQuicksort();
        addValueToMatlab(sel + "_rand", counter.getCount());
        System.out.println(counter.getCount());
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        QuicksortPerformanceEvaluator pe = new QuicksortPerformanceEvaluator(
                "Bla bla bla".hashCode());
        pe.performEvaluation();
    }
}
