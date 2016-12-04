package adp.aufgabe10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import adp.util.AbstractPerformanceEvaluator;

public class HashAccessPerformanceEvaluator
        extends
            AbstractPerformanceEvaluator {

    private LogGenerator generator;
    private LogReader reader;

    public HashAccessPerformanceEvaluator() {
        String filename = System.getProperty("user.dir") + File.separator
                + "perf.log";
        generator = new LogGenerator(filename);
        reader = new LogReader(filename);
    }

    private void evaluate(int size) {
        try {
            addValueToMatlab("size", size);
            generator.generateAndWriteLines(size);

            System.out.print("Reading...");
            HashTable<List<String>> ht = reader.readLog();
            System.out.println("done");
            List<String> keys = ht.getKeys();

            double avgAccessTimeNS = keys.stream()
                    .mapToLong(key -> getNSForAction(() -> ht.get(key)))
                    .average().getAsDouble();
            addValueToMatlab("time", (long)avgAccessTimeNS);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private long getNSForAction(Runnable action) {
        long t1 = System.nanoTime();
        action.run();
        return System.nanoTime() - t1;

    }

    @Override
    public void performEvaluation() {
        addKeyToMatlab("size");
        addKeyToMatlab("time");
        
        evaluate(100);
        evaluate(1000);
        evaluate(10000);
        evaluate(100000);
        evaluate(250000);
        evaluate(500000);
        evaluate(750000);
        evaluate(1000000);

        printMatlab();
    }
    
    public static void main(String[] args) {
        new HashAccessPerformanceEvaluator().performEvaluation();
    }

}
