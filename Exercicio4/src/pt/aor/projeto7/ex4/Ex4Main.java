/*
4. Consider a multi-threaded server and the following division of work: the master thread
receives commands and the remaining worker threads execute these commands. To
simplify, the purpose of the command is to compute the square root of a real number and
print the result to the standard output (screen). You should write the program assuming
that the master thread generates numbers at random, waiting some random time between
two consecutive numbers. The master should use some form of queue to send the tasks to
the workers (e.g., the ArrayBlockingQueue). You should do a print identifying the
worker thread that is responsible for computing the result.
 */

package pt.aor.projeto7.ex4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class Ex4Main {
    
    //thread number
    private static final int numOfThreads = 10;
    //queue size
    private static final int queueSize = 8;
    private static ArrayBlockingQueue<Double> arrayBqueue;
    //task number
    private static final int numOfTasks = 400;

    @SuppressWarnings("SleepWhileInLoop")
    public static void main(String[] args){
    //initialize ArrayBlockingQueue
        arrayBqueue = new ArrayBlockingQueue<>(queueSize);
        
        //initialize new fixed size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
       
        //initialize the threads and submit to pool
        for(int i = 0; i<numOfThreads; i++){
        Runnable thread = new WorkerThread(arrayBqueue);
        executor.submit(thread);
        }
        
        //generates the numbers for computation by worker threads
        for(int n = 0; n<numOfTasks; n++){
            //generates value between 1 and 1000
        Double value = Math.random()*(1000)+1;
        //puts the value in the tail of the ArrayBlockingQueue
            try {
                arrayBqueue.put(value);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ex4Main.class.getName()).log(Level.SEVERE, "Error while putting the value in the queue - Master Thread was interrupted.");
            }
            //thread sleeps for a random amount of time (lets consider 100 to 5000 ms)

            int sleepTime = (int) (Math.random()*(5001-100)+100);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ex4Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    

}
