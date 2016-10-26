/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 23.10.2016 
 * Aufgabe: Aufgabenblatt 6
 */

package adp.aufgabe6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * The application
 *
 * @author Moritz Höwer
 * @version 1.0 - 26.10.2016
 */
public class HuffmanApplication {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // read reftext (introduction to sherlock holmes ~30k chars)
        String ref = readFile("reftext.txt");

        // a text for encoding and decoding
        final String TEXT = "If you give someone a program you will frustrate "
                + "them for a day. If you teach them how to program, you will "
                + "frustrate them for a lifetime. The computing scientist's "
                + "main challenge is not to get confused by the complexities "
                + "of his own making. Beauty is more important in computing "
                + "than anywhere else in technology because software is so "
                + "complicated. Beauty is the ultimate defence against "
                + "complexity.";

        HuffmanEncoder encoder = new HuffmanEncoder();
        // generate tree with reftext
        encoder.generateHuffmanTree(ref);

        String code = encoder.encode(TEXT);
        System.out.println("Code is " + code.length() + " Bit instead of "
                + TEXT.length() * 8 + " Bit when encoded with REFTEXT");
        System.out.println(code);
        System.out.println(encoder.decode(code));

        System.out.println();
        System.out.println(
                "------------------------------------------------------------");
        System.out.println();

        // generate tree with text itself
        encoder.generateHuffmanTree(TEXT);

        code = encoder.encode(TEXT);
        System.out.println("Code is " + code.length() + " Bit instead of "
                + TEXT.length() * 8 + " Bit when encoded with itself");
        System.out.println(code);
        System.out.println(encoder.decode(code));
    }

    /**
     * Reads the entire file specified under filename to a String and returns it
     * 
     * @param filename
     *            the (relative) filename to read
     * @return the contents of the file as a String
     */
    private static String readFile(String filename) {
        // get the File as an InputStream
        InputStream is = HuffmanApplication.class.getResourceAsStream(filename);

        // read it
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(is))) {
            StringBuilder builder = new StringBuilder();

            String s;
            while ((s = reader.readLine()) != null) {
                builder.append(s);
            }

            return builder.toString();
        } catch (IOException e) {
            return "";
        }
    }

}
