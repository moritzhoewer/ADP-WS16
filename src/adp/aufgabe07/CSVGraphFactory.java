/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 07.11.2016 
 * Aufgabe: Aufgabenblatt 7
 */

package adp.aufgabe07;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A factory that knows how to generate a Graph from a CSV File
 *
 * @author Moritz Höwer
 * @version 1.0 - 07.11.2016
 */
public class CSVGraphFactory implements AbstractGraphFactory<String> {
    /**
     * The CSV File to read and generate the Graph from
     */
    private File csv;

    public CSVGraphFactory(String csv) {
        this(new File(csv));
    }

    public CSVGraphFactory(File csv) {
        this.csv = csv;
        if (!csv.exists()) {
            throw new IllegalArgumentException(new FileNotFoundException(
                    "Please verify the File exists!"));
        } else {
            if (!csv.canRead()) {
                throw new IllegalArgumentException(
                        "Don't have reading permissions for File!");
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see adp.aufgabe7.AbstractGraphFactory#buildGraph()
     */
    @Override
    public Graph<String> buildGraph() {
        Graph<String> result = new AdjazenzlisteGraph<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(csv))) {
            List<String[]> lines = reader.lines().map(s -> s.split(","))
                    .collect(Collectors.toList());

            lines.forEach(l -> result.insertNode(new Node<>(l[0].trim())));
            lines.forEach(l -> {
                Node<String> start = new Node<>(l[0].trim());
                for(int i = 1; i < l.length; i++){
                    Node<String> end = new Node<>(lines.get(i - 1)[0].trim());
                    int weight = Integer.parseInt(l[i]);
                    result.insertConnection(start, end, weight);
                }
            });

            return result;
        } catch (IOException e) {
            return null;
        }
    }

}
