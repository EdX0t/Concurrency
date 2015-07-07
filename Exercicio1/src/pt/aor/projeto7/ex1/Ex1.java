/*
 * 1. Write a program that creates a pool of 2 threads to run 8 tasks. Each task should write the
sentence “Hello World, I am thread x running task y”, where x is the identifier of the thread,
and y the identifier of the task. You must use the newFixedThreadPool() method of the
Executors class.
 */
package pt.aor.projeto7.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Ed
 */
public class Ex1 {
    private static final int numberOfTasks = 8;
    private static final int numberOfThreads = 2;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads);
        for(int i=1;  i<=numberOfTasks; i++){
       Runnable task = new Task(i);
       executor.execute(task);
       }
       executor.shutdown();
       
        try {
           executor.awaitTermination(6, TimeUnit.SECONDS);
           System.out.println("All threads are terminated.");
                    } catch (InterruptedException ex) {
            Logger.getLogger(Ex1.class.getName()).log(Level.SEVERE, "Could not execute all the threads.");
        }
       
    }
    
}
