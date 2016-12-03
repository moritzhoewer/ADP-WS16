/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 03.12.2016 
 * Aufgabe: Aufgabenblatt 10
 */

package adp.aufgabe10;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Entry Point for Program
 *
 * @author Moritz Höwer
 * @version 1.0 - 03.12.2016
 */
public class Application {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println("Waiting for start...");
            System.in.read();
            System.out.print("Reading..");
            long tS = System.nanoTime();
            HashTable<List<String>> ht = new LogReader(
                    System.getProperty("user.dir") + File.separator
                            + "test.log").readLog();
            long tE = System.nanoTime();
            System.out.println("done in " + ((tE - tS) / 1000 / 1000) + " ms");
            System.out.println(ht.size());

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
