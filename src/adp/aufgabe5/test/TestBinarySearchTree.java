/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.10.2016 
 * Aufgabe: Aufgabenblatt 5
 */

package adp.aufgabe5.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Random;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import adp.aufgabe5.BinarySearchTree;
import adp.aufgabe5.Node;

/**
 * Testing the BinarySearchTree implementation
 *
 * @author Moritz Höwer
 * @version 1.1 - 17.10.2016
 */
public class TestBinarySearchTree extends BinarySearchTree {

    /**
     * the size of the tree
     */
    private static final int RANDOM_TEST_TREESIZE = 100;

    /**
     * the size of the tree
     */
    private static final int RANDOM_MAX_VALUE = 100;

    /**
     * how many tests to perform on each tree
     */
    private static final int RANDOM_TESTS_PER_TREE = 10;

    /**
     * how many random trees to generate
     * 
     * multiply with RANDOM_TESTS_PER_TREE for total test count
     */
    private static final int RANDOM_TESTS_CYCLES = 50;

    public TestBinarySearchTree() {
        super();
    }

    /**
     * Test method for {@link adp.aufgabe5.BinarySearchTree#insert(int)}.
     */
    @Test
    public void testInsert() {
        assertThat("Root should be null", root, is(nullValue()));

        assertThat("Should be able to insert 5", insert(5), is(true));
        assertThat("Root is wrong", root, equalTo(new Node(5)));

        assertThat("Should not be able to insert 5 again", insert(5),
                is(false));
        assertThat("Root is wrong", root, equalTo(new Node(5)));

        assertThat("Should be able to insert 3", insert(3), is(true));
        assertThat("Tree structure is wrong", root.getLeftChild(),
                equalTo(new Node(3)));
        assertThat("Sum is wrong", root.getSum(), is(8));

        assertThat("Should not be able to insert 3 again", insert(3),
                is(false));
        assertThat("Tree structure is wrong", root.getLeftChild(),
                equalTo(new Node(3)));
        assertThat("Sum is wrong", root.getSum(), is(8));

        assertThat("Should be able to insert 8", insert(8), is(true));
        assertThat("Tree structure is wrong", root.getRightChild(),
                equalTo(new Node(8)));
        assertThat("Sum is wrong", root.getSum(), is(16));

        assertThat("Should be able to insert 4", insert(4), is(true));
        assertThat("Tree structure is wrong",
                root.getLeftChild().getRightChild(), equalTo(new Node(4)));
        assertThat("Sum is wrong", root.getLeftChild().getSum(), is(7));
        assertThat("Sum is wrong", root.getSum(), is(20));

    }

    /**
     * Test method for
     * {@link adp.aufgabe5.BinarySearchTree#sumBetween(int, int)}.
     */
    @Test
    public void testSumBetween() {
        try {
            sumBetween(10, 9);
            fail("Expected an IllegalArgumentException for lower > upper");
        } catch (IllegalArgumentException e) {
            // this is the expected behavior
        }

        // perform tests with random trees
        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i < RANDOM_TESTS_CYCLES; i++) {
            ArrayList<Integer> ref = createRandomTree(random);

            for (int j = 0; j < RANDOM_TESTS_PER_TREE; j++) {
                testRandomSum(random, ref);
            }
        }
    }

    /**
     * Generates a Tree with RANDOM_TEST_TREESIZE random Integers. Also stores
     * those in an ArrayList for later reference...
     * 
     * @param random
     *            the RNG to use
     * @return the refference ArrayList - <b>SIDE EFFECT</b> the tree is changed
     *         to contain those elements
     */
    private ArrayList<Integer> createRandomTree(Random random) {
        // clear Tree
        root = null;

        ArrayList<Integer> refference = new ArrayList<>();

        // insert RANDOM_TEST_TREESIZE Integers into the Tree and reference
        // using Stream
        random.ints(0, RANDOM_MAX_VALUE).distinct().limit(RANDOM_TEST_TREESIZE)
                .forEach(i -> {
                    insert(i);
                    refference.add(i);
                });
        return refference;
    }

    /**
     * Calculates the sum of all nodes between a random upper and lower limit.
     * It does it by using the tree first and then comparing the esult to the
     * sum of all values in reference that are between the limits
     * 
     * @param random
     *            the RNG to use
     * @param forComparisson
     *            the ArrayList that contains all the Nodes values for reference
     */
    private void testRandomSum(Random random,
            ArrayList<Integer> forComparisson) {
        // create random upper and lower limits
        int upper = random.nextInt(RANDOM_MAX_VALUE) + 1; // avoid 0
        int lower = random.nextInt(upper);

        int sumTree = sumBetween(lower, upper);
        int sumRefference = forComparisson.stream().mapToInt(Integer::intValue)
                .filter(i -> {
                    return i <= upper && i >= lower;
                }).sum();
        
        assertThat("Sum doesn't match...", sumTree, equalTo(sumRefference));

    }

}
