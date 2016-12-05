package adp.aufgabe10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import adp.util.AbstractPerformanceEvaluator;
import adp.util.Counter;

public class HashAccessPerformanceEvaluator
        extends
            AbstractPerformanceEvaluator {

    private LogGenerator generator;
    private LogReader reader;

    public HashAccessPerformanceEvaluator() {
        /*String filename = System.getProperty("user.dir") + File.separator
                + "perf.log";*/
    	String filename = "C:\\Users\\abz254\\per.log";
        generator = new LogGenerator(filename);
        reader = new LogReader(filename);
    }

    private void evaluate(int size) {
        try {
            addValueToMatlab("size", size);
            generator.generateAndWriteLines(size);

            System.out.print("Reading...");
            HashTable<List<String>> ht = reader.readLog();
            Counter counter = new Counter();
            ht.setCounter(counter);
            System.out.println("done");
            List<String> keys = ht.getKeys();

            keys.forEach((key) -> ht.get(key));
            
            addValueToMatlab("time", counter.getCount() / keys.size());
            addValueToMatlab("load", (long)(ht.getLoadFactor() * 100));

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
        addKeyToMatlab("load");
        
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
