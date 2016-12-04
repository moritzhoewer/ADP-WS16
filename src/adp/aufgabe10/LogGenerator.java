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
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Generates a fake Weblog
 *
 * @author Moritz Höwer
 * @version 1.0 - 03.12.2016
 */
public class LogGenerator {

    /**
     * highest number possible for part of an IP address
     */
    private static final int IP_NUMBER_MAX = 1000;

    /**
     * the higher this number, the higher the chances of generating a new IP
     */
    private static final int PROBABILITY_OF_REGENERATE = 3;

    /**
     * how many chars are displayed as progressbar on the console
     */
    private static final int PROGRESSBAR_CHARCOUNT = 50;

    /**
     * a few requests so the log is not too too boring
     */
    private static final String[] REQUESTS = {"GET /index.php",
            "GET /header.jpg", "GET /footer.jpg",
            "GET /images/my_cool_cat.png"};

    // Members ----------------------------------------------------------------
    /**
     * stores all generated ips for reuse
     */
    private ArrayList<String> ips;

    /**
     * the name of the file to write
     */
    private String filename;

    /**
     * the RNG for randomizing output
     */
    private Random rand;

    /**
     * for outputting date
     */
    LocalDateTime date;

    public LogGenerator(String filename) {
        ips = new ArrayList<>();
        this.filename = filename;
        rand = new Random();
        date = LocalDateTime.now();
    }

    /**
     * Program entry point
     * 
     * @param args
     *            command line args
     * @throws FileNotFoundException
     *             if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {
        new LogGenerator(
                System.getProperty("user.dir") + File.separator + "test.log")
                        .generateLog();
    }

    /**
     * generate a log
     * 
     * @throws FileNotFoundException
     *             if the file cannot be found
     */
    public void generateLog() throws FileNotFoundException {
        System.out.print("Please enter how many entries you want: ");
        Scanner scanner = new Scanner(System.in);
        int lines = scanner.nextInt();
        scanner.close();

        if (lines < 0) {
            return;
        } else {
            generateAndWriteLines(lines);
        }
    }

    /**
     * generate lines for the log and write them to a file
     * 
     * @param lines
     *            how many liones to write
     * @throws FileNotFoundException
     *             if the file is not found
     */
    public void generateAndWriteLines(int lines) throws FileNotFoundException {
        File logFile = new File(filename);
        PrintStream ps = new PrintStream(logFile);
        ips.clear();

        // prepare progress bar
        int progress = 0;
        int lastProgress = 0;
        System.out.println("Writing " + lines + "...");
        System.out.print("[");
        for (int i = 0; i < PROGRESSBAR_CHARCOUNT; i++) {
            System.out.print(" ");
        }
        System.out.print("]\n ");

        for (int i = 1; i <= lines; i++) {

            // update progress bar on console
            progress = (int) (i * PROGRESSBAR_CHARCOUNT / lines);
            if (progress > lastProgress) {
                lastProgress = progress;
                System.out.print("^");
            }

            String logLine = String.format("%s - [%s] %s", getIP(),
                    date.toString(), REQUESTS[rand.nextInt(REQUESTS.length)]);
            ps.println(logLine);
        }
        System.out.println("\nDone.");
        System.out.println("Generated " + ips.size() + " IP adresses");
        ps.close();
    }

    /**
     * Provides a new IP (randomly generates a new one or reuses an existing
     * one)
     * 
     * @return an IP between 0.0.0.0 and 999.999.999.999 (as specified in
     *         IP_NUMBER_MAX)
     */
    private String getIP() {
        if (rand.nextInt(PROBABILITY_OF_REGENERATE) == 0 && !ips.isEmpty()) {
            return ips.get(rand.nextInt(ips.size()));
        } else {
            String ip = String.format("%d.%d.%d.%d",
                    rand.nextInt(IP_NUMBER_MAX), rand.nextInt(IP_NUMBER_MAX),
                    rand.nextInt(IP_NUMBER_MAX), rand.nextInt(IP_NUMBER_MAX));
            ips.add(ip);
            return ip;
        }
    }

}
