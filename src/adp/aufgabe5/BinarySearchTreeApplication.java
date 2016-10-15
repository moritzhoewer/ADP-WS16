/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 15.10.2016 
 * Aufgabe: Aufgabenblatt 5
 */

package adp.aufgabe5;

/**
 * The application
 *
 * @author Moritz Höwer
 * @version 1.0 - 15.10.2016
 */
public class BinarySearchTreeApplication {

    /**
     * program entry point
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        BinarySearchTree tree = new BinarySearchTree();
        tree.insert(5);
        tree.insert(3);
        tree.insert(3);
        tree.insert(8);
        tree.insert(4);
        
        // Resulting tree is
        //     5 (20)
        //    /      \
        //  3 (7)   8 (8)
        //       \
        //      4 (4)

    }

}
