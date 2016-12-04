/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 04.12.2016 
 * Aufgabe: Aufgabenblatt 10
 */

package adp.aufgabe10;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * Controller for the IP Application.
 *
 * @author Moritz Höwer
 * @version 1.0 - 04.12.2016
 */
public class Controller {

    private MainGUI mainGUI;
    private HashTable<List<String>> hashTable;

    public Controller(Stage primaryStage, Parent root) {
        mainGUI = MainGUI.getInstance();
        mainGUI.initAndShowGUI(this, primaryStage, root);

        // disable GUI while reading to indicate it's not operational yet
        root.setDisable(true);
        readAndDisplayLogFile();
        root.setDisable(false);
    }

    /**
     * Retrieves the entries corresponding to the IP from the HashTable
     * 
     * @param ip
     *            the IP to look up
     */
    public void handleIPSelected(String ip) {
        List<String> entries = hashTable.get(ip);
        mainGUI.displayEntries(entries);
    }

    /**
     * Reads the log file and displays it on the GUI
     */
    private void readAndDisplayLogFile() {
        try {
            hashTable = new LogReader(System.getProperty("user.dir")
                    + File.separator + "test.log").readLog();

            List<String> ips = hashTable.getKeys();
            // ips.sort(null);
            mainGUI.displayIPs(ips);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
