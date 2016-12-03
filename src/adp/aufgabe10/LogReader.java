/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 03.12.2016 
 * Aufgabe: Aufgabenblatt 10
 */

package adp.aufgabe10;

import java.io.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Reads a Weblog to a HashTable
 *
 * @author Moritz Höwer
 * @version 1.0 - 03.12.2016
 */
public class LogReader {

    /**
     * the name of the LogFile to read
     */
    private String filename;

    public LogReader(String filename) {
        this.filename = filename;
    }
    
    /**
     * Reads the Weblog specified in constructor and stores it in a HashTable
     * 
     * @return the HashTable containing all the Files
     * @throws IOException
     */
    public HashTable<List<String>> readLog() throws IOException {
        File logFile = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(logFile));

        HashTable<List<String>> ht = new HashTable<>();
        
        br.lines().forEach(line -> {
            String[] parts = line.split(" - ");
            String ip = parts[0];
            String data = parts[1];
            
            // check if IP already exists
            if (ht.containsKey(ip)) {
                ht.get(ip).add(data);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(data);
                ht.put(ip, list);
            }
        });
        
        br.close();
        return ht;
    }
}
