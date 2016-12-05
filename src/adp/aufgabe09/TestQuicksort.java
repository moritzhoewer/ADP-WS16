/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 05.12.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.aufgabe09;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Random;

import org.junit.Test;

import adp.aufgabe09.ImprovedSortableArrayList.PivotSelection;

/**
 *
 *
 * @author Moritz Höwer
 * @version 1.0 - 05.12.2016
 */
public class TestQuicksort {

    private static final int LIST_SIZE = 1000;
    private static final int TEST_COUNT = 100;

    private void fillListRandomly(ImprovedSortableArrayList list,
            int desiredSize) {
        Random random = new Random();
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

    private void commonCheck(ImprovedSortableArrayList list) {
        for (int i = 0; i < TEST_COUNT; i++) {
            fillListRandomly(list, LIST_SIZE);
            list.doQuicksort();
            verifyCorrect(list);
        }
    }

    private void verifyCorrect(ImprovedSortableArrayList list) {
        for (int i = 0; i < list.size() - 1; i++) {
            assertThat(
                    "Element " + list.retrieve(i) + " at index " + i
                            + " is bigger then the following element "
                            + list.retrieve(i + 1),
                    list.retrieve(i) <= list.retrieve(i + 1), is(true));
        }
    }
    
    @Test
    public void testRight(){
        commonCheck(new ImprovedSortableArrayList(PivotSelection.RIGHT));
    }
    
    @Test
    public void testCenter(){
        commonCheck(new ImprovedSortableArrayList(PivotSelection.CENTER));
    }
    @Test
    public void testRandom(){
        commonCheck(new ImprovedSortableArrayList(PivotSelection.RANDOM));
    }

}
