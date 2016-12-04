/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 04.12.2016 
 * Aufgabe: Aufgabenblatt 10
 */

package adp.aufgabe10;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

/**
 * Main GUI class for displaying IP addresses and corresponding entries.
 *
 * @author Moritz Höwer
 * @version 1.0 - 04.12.2016
 */
public class MainGUI implements Initializable {

    /**
     * ListView to show IPs
     */
    @FXML
    private ListView<String> lstIps;

    /**
     * ListView to show entries corresponding to the selected IP
     */
    @FXML
    private ListView<String> lstEntries;

    /**
     * hacky singleton because I don't understand how this stupid FXML shit is
     * supposed to work
     */
    private static MainGUI instance = null;

    /**
     * hacky singleton because I don't understand how this stupid FXML shit is
     * supposed to work
     * 
     * @throws IllegalStateException
     *             on the second call to the constructor
     */
    public MainGUI() {
        if (instance != null) {
            throw new IllegalStateException("There can only be one "
                    + getClass().getSimpleName() + "-Controller !");
        }
        instance = this;
    }

    /**
     * hacky singleton because I don't understand how this stupid FXML shit is
     * supposed to work
     * 
     * @return the first and only instance of MainGUI
     * @throws IllegalStateException
     *             if no MainGUI has been constructed
     */
    public static MainGUI getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                    "Not initialized yet - Please load FXML first!");
        }
        return instance;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javafx.fxml.Initializable#initialize(java.net.URL,
     * java.util.ResourceBundle)
     */
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // what am I supposed to do here? I don't know

    }

    /**
     * Initialize GUI
     * 
     * @param controller
     *            the controller
     * @param primaryStage
     *            the primary stage
     * @param root
     *            the root for the scene graph (constructed by FXML loader)
     */
    public void initAndShowGUI(Controller controller, Stage primaryStage,
            Parent root) {
        lstIps.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        lstIps.getSelectionModel().selectedItemProperty()
                .addListener((ov, old, nw) -> {
                    controller.handleIPSelected(nw);
                });

        Scene scene = new Scene(root, 900, 400);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.setTitle("Awesome WebLog Viewer");
        primaryStage.show();
    }

    /**
     * Display a list of ips.
     * 
     * @param ipList
     *            the list to display
     */
    public void displayIPs(List<String> ipList) {
        lstIps.getItems().setAll(ipList);
    }

    /**
     * Display a list of entries
     * 
     * @param entryList
     *            the list to display
     */
    public void displayEntries(List<String> entryList) {
        lstEntries.getItems().setAll(entryList);
    }

}
