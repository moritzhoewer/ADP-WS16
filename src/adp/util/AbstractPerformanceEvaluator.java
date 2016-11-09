/*
 * Praktikum AD - WS 2016
 * Gruppe:  Jesko Treffler (Jesko.Treffler@haw-hamburg.de),
 * 			Moritz Höwer (Moritz.Hoewer@haw-hamburg.de)
 * 
 * Datum: 11.10.2016 
 * Aufgabe: Aufgabenblatt X
 */

package adp.util;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Abstract superclass for performance evaluators
 *
 * @author Moritz Höwer
 * @version 1.0 - 11.10.2016
 */
public abstract class AbstractPerformanceEvaluator {
    /**
     * stores the values to generate input for matlab
     */
    private HashMap<String, ArrayList<Long>> forMatlab;

    protected AbstractPerformanceEvaluator() {
        forMatlab = new HashMap<>();
    }

    /**
     * Initializes matlab output
     * 
     * @param keys
     *            the primary keys
     * @param subkeys
     *            the subkeys
     */
    protected void initMatlab(String[] keys, String[] subkeys) {
        for (String key : keys) {
            for (String subkey : subkeys) {
                addKeyToMatlab(key + "_" + subkey);
            }
        }
    }

    /**
     * Initializes matlab output
     * 
     * @param keys
     *            the primary keys
     */
    protected void initMatlab(String... keys) {
        for (String key : keys) {
            addKeyToMatlab(key);
        }
    }

    /**
     * Adds a key to the matlab output
     * 
     * @param key
     *            the key to add
     */
    protected void addKeyToMatlab(String key) {
        forMatlab.put(key, new ArrayList<>());
    }

    /**
     * Adds a value to the matlab output
     * 
     * @param key
     *            the key for which the value is to be added
     * @param subkey
     *            the subkey for which to add
     * @param value
     *            the value to be added
     */
    protected void addValueToMatlab(String key, String subkey, long value) {
        addValueToMatlab(key + "_" + subkey, value);
    }

    /**
     * Adds a value to the matlab output
     * 
     * @param key
     *            the key for which the value is to be added
     * @param value
     *            the value to be added
     */
    protected void addValueToMatlab(String key, long value) {
        forMatlab.get(key).add(value);
    }

    /**
     * Prints matlab output
     */
    protected void printMatlab() {
        forMatlab.forEach((s, a) -> {
            System.out.print(s + " = [");
            for (int i = 0; i < a.size(); i++) {
                System.out.print(a.get(i));
                if (i != a.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("];");
        });
    }

    /**
     * Perform evaluation of Tasks
     */
    public abstract void performEvaluation();

    /**
     * Converts the nanoseconds to the most appropriate unit
     * 
     * @param nanos
     *            number of nanoseconds
     * @return String representation in ns, µs or ms
     */
    protected String nanosToHumanReadableString(long nanos) {
        if (nanos < 1000) {
            return nanos + " ns";
        } else {
            double us = nanos / 1000.0;
            if (us < 1000) {
                return String.format("%.2f µs", us);
            } else {
                double ms = us / 1000;
                if (ms < 1000) {
                    return String.format("%.2f ms", ms);
                } else {
                    double s = ms / 1000;
                    return String.format("%.2f s", s);
                }
            }
        }
    }
}
