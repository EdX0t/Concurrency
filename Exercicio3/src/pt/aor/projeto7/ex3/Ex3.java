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
    private static int numberOfDoubles;
    private static double[] listOfDoubles;
    private static long zeroTime, splitTime, sequentialTime, threadedTime;

    public static void main(String[] args) {
        zeroTime = System.nanoTime();
        //creates the double array
        numberOfDoubles = Integer.valueOf(args[0]);
        //generates random double values
        listOfDoubles = randomizeDoubles(numberOfDoubles);
        splitTime = (System.nanoTime()-zeroTime)/1000;
        //run threaded program
        runThreadProgram();
        //run sequential program
        runSequentialProgram();
        //write the time conclusions
        writeConclusions();
    }

    private static double[] randomizeDoubles(int numberOfDoubles) {

        double[] list = new double[numberOfDoubles];
        for (int i = 0; i < list.length; i++) {
            list[i] = Math.random() * (100) + 1;
        }
        System.out.println("Random numbers");
        for (int i = 0; i < list.length; i++) {
            System.out.println(list[i] + " ");
        }
        return list;
    }

    private static double calculateAverage(double[] listOfDoubles) {
        double soma = 0;
        for (int i = 0; i < listOfDoubles.length; i++) {
            soma += listOfDoubles[i];
        }
        return (soma / listOfDoubles.length);
    }

    private static void runThreadProgram() {
        long threadStartTime = System.nanoTime();
        //calculate and print average from array
        System.out.println("Average: " + calculateAverage(listOfDoubles));

        //initialize fixed thread pool with executor service
        ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);

        //initialize callables
        Callable minCallable = new MinThread(listOfDoubles);
        Callable maxCallable = new MaxThread(listOfDoubles);
        //submit callables
        Future minimum = executors.submit(minCallable);
        Future maximum = executors.submit(maxCallable);

        //print max and min from Future objects  
        try {
            System.out.println("Maximum: " + maximum.get());
            System.out.println("Minimum: " + minimum.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, "Error getting minimum or maximum values.");
        }
        executors.shutdown();
        threadedTime = (System.nanoTime() - threadStartTime)/1000;
    
    }

    private static void runSequentialProgram() {
        long sequentialStartTime = System.nanoTime();
        //calculate and print average from array
        System.out.println("Average: " + calculateAverage(listOfDoubles));
        //calculate minimum
        double min = listOfDoubles[0];
        for (int i = 1; i < listOfDoubles.length; i++) {
            if (listOfDoubles[i] < min) {
                min = listOfDoubles[i];
            }
        }
        System.out.println("Minimum: " + min);
        //calculate maximum
        double max = listOfDoubles[0];
        for (int i = 1; i < listOfDoubles.length; i++) {
            if (listOfDoubles[i] > max) {
                max = listOfDoubles[i];
            }
        }
        System.out.println("Maximum: " + max);

        sequentialTime = (System.nanoTime() - sequentialStartTime)/1000;
    }

    private static void writeConclusions() {
       System.out.println("Building the array time [microseconds]: "+ splitTime);
       System.out.println("Threaded execution time [microseconds]: " + threadedTime);
       System.out.println("Sequential execution time [microseconds]: " + sequentialTime);
       
       //Threaded time gain percentage for number of doubles argument
       System.out.println("Overall threaded time gain % : "+(threadedTime/sequentialTime)*100);
    }
}
