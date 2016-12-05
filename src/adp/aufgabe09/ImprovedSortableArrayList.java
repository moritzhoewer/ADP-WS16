/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 04.12.2016 
 * Aufgabe: Aufgabenblatt 09
 */

package adp.aufgabe09;

import java.util.Random;
import java.util.function.IntBinaryOperator;

import adp.aufgabe04.SortableArrayList;
import adp.util.Counter;

/**
 * Even better Sorting - now featuring QuickSort
 *
 * @author Moritz Höwer, Jesko Treffler
 * @version 1.0 - 04.12.2016
 */
public class ImprovedSortableArrayList extends SortableArrayList {

    public enum PivotSelection {
        RIGHT, CENTER, RANDOM
    }

    private IntBinaryOperator pivotGenerator;

    private Counter counter;

    public ImprovedSortableArrayList(PivotSelection pivotSelection) {
        this(pivotSelection, new Counter());
    }

    public ImprovedSortableArrayList(PivotSelection pivotSelection,
            Counter counter) {
        this.counter = counter;
        switch (pivotSelection) {
            case RIGHT :
                pivotGenerator = (min, max) -> max;
                break;
            case CENTER :
                pivotGenerator = (min, max) -> (min + max) / 2;
                break;
            case RANDOM :
                Random rand = new Random();
                pivotGenerator = (min, max) -> min + rand.nextInt(max - min);
                break;
            default :
                throw new IllegalArgumentException(
                        "Please provide a valid Pivot Selection!");
        }
    }

    public void doQuicksort() {
        // PERFORMANCE EVALUATION
        counter.increment();
        // PERFORMANCE EVALUATION
        quicksort(0, count - 1);
    }

    /**
     * 
     * @param leftIndex
     * @param rightIndex
     */
    private void quicksort(int leftIndex, int rightIndex) {
        // PERFORMANCE EVALUATION
        counter.increment();
        // PERFORMANCE EVALUATION
        if (leftIndex < rightIndex) {
            // PERFORMANCE EVALUATION
            counter.incrementBy(3);
            // PERFORMANCE EVALUATION
            int i = leftIndex;
            int j = rightIndex;
            int pivot = (Integer) data[pivotGenerator.applyAsInt(i, j)];

            // PERFORMANCE EVALUATION
            counter.increment();
            // PERFORMANCE EVALUATION
            while (i <= j) {
                // PERFORMANCE EVALUATION
                counter.increment();
                // PERFORMANCE EVALUATION
                while ((Integer) data[i] < pivot) {
                    // PERFORMANCE EVALUATION
                    counter.increment();
                    // PERFORMANCE EVALUATION
                    i++;
                }
                // PERFORMANCE EVALUATION
                counter.increment();
                // PERFORMANCE EVALUATION
                while ((Integer) data[j] > pivot) {
                    // PERFORMANCE EVALUATION
                    counter.increment();
                    // PERFORMANCE EVALUATION
                    j--;
                }
                // PERFORMANCE EVALUATION
                counter.increment();
                // PERFORMANCE EVALUATION
                if (i <= j) {
                    swap(i, j);
                    // PERFORMANCE EVALUATION
                    counter.incrementBy(2);
                    // PERFORMANCE EVALUATION
                    i++;
                    j--;
                }
            }
            // PERFORMANCE EVALUATION
            counter.incrementBy(2);
            // PERFORMANCE EVALUATION
            quicksort(leftIndex, j);
            quicksort(i, rightIndex);
        }
        /* else: quit recursion */
    }

    /**
     * Swaps Entries at Index1 and Index2 in ArrayList
     * 
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        // PERFORMANCE EVALUATION
        counter.incrementBy(3);
        // PERFORMANCE EVALUATION
        Object tmp;
        tmp = data[index1];
        data[index1] = data[index2];
        data[index2] = tmp;
    }
}
