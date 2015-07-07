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
package aor.projeto7.ex3;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class Ex3 {
    private static final int numberOfThreads = 2;
    private static int numberOfDoubles;
    private static double[] listOfDoubles;
    public static void main(String[] args) {
  
            numberOfDoubles = Integer.valueOf(args[0]);
            listOfDoubles = randomizeDoubles(numberOfDoubles);
            //print average from array
            System.out.println("Average: "+ calculateAverage(listOfDoubles));
            
            //initializa fixed thread pool with executor service
            ExecutorService executors = Executors.newFixedThreadPool(numberOfThreads);
            
            //initialize callables
            Callable minCallable = new MinThread(listOfDoubles);
            Callable maxCallable = new MaxThread(listOfDoubles);
            //submit callables
            Future minimum = executors.submit(minCallable);
            Future maximum = executors.submit(maxCallable);
            
            //print max and min from Future objects  
        try {
            System.out.println("Maximum: "+maximum.get());
            System.out.println("Minimum: "+minimum.get());
        } catch (InterruptedException | ExecutionException ex) {
            Logger.getLogger(Ex3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    private static double[] randomizeDoubles(int numberOfDoubles) {
     
        double[] list = new double[numberOfDoubles];
        for(int i=0; i<list.length; i++){
        list[i] = Math.random()*(100)+1;
        }
        System.out.println("Random numbers");
        for(int i=0; i<list.length; i++){
        System.out.println(list[i]+" ");
        }
        return list;
    }

    private static double calculateAverage(double[] listOfDoubles) {
        double soma = 0;
        for(int i=0; i<listOfDoubles.length; i++){
            soma+=listOfDoubles[i];
      }
        return (soma/listOfDoubles.length);
    }
}
