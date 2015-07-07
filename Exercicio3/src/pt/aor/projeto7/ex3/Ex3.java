/*
 * Use the ExecutorService and the Future interfaces to compute the following functions
 over a random vector with a varying number of doubles: average, minimum, maximum.
 The number of doubles is an argument given to the program from the command line. You
 should write the program as follows: the main thread computes the average and launches
 two threads to find the minimum and the maximum. In the end, the program should output
 the results to the screen. Compare the times against a sequential program that does the
 same thing. Prepare a plot that shows the time the computation takes in both versions,
 versus the number of doubles in the computation. NOTE: you must include the time to
 launch the tasks in the parallel version time.
 */
package pt.aor.projeto7.ex3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class Ex3 {

    private static final int numberOfThreads = 2;
    private static  int numberOfDoubles;
    private static double[] listOfDoubles;
    private static double avg, min, max;
    private static long zeroTime, splitTime, threadedTime;

    public static void main(String[] args) {

        //gets initial reference time
        zeroTime = System.nanoTime();

        //parses the argument number
         numberOfDoubles = Integer.valueOf(args[0]);

        //create Helper class instance
        Helper helper = new Helper();

        //generates random double values array
        listOfDoubles = helper.createArray(numberOfDoubles);

        //calculate average
        avg = helper.calculateAverage(listOfDoubles);

        //calculate split time
        splitTime = (System.nanoTime() - zeroTime) / 1000;

        //run the threads for max and min
        runThreadProgram();

    }

    private static void runThreadProgram() {
        long threadStartTime = System.nanoTime();

        //initialize fixed thread pool with executor service
        ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);

        //initialize callables
        Callable minCallable = new MinThread(listOfDoubles);
        Callable maxCallable = new MaxThread(listOfDoubles);
        //submit callables
        Future minimum = executors.submit(minCallable);
        Future maximum = executors.submit(maxCallable);

        //get max and min from Future objects  
        try {
            max = (Double) maximum.get();
            min = (Double) minimum.get();
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, "Error getting minimum or maximum values.");
        }
        executors.shutdown();
        threadedTime = (System.nanoTime() - threadStartTime) / 1000;
        
         //prints results
        System.out.println("Average: "+avg);
        System.out.println("Minimum: "+min);
        System.out.println("Maximum: "+max);
        System.out.println("Total Threaded execution time: "+threadedTime);
    }
}
