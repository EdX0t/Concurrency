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
    private static final int numOfTasks = 50;

    public static void main(String[] args) {
        //initialize ArrayBlockingQueue
        arrayBqueue = new ArrayBlockingQueue<>(queueSize);

        //initialize new fixed size thread pool
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);

        //initialize Master thread and submit to pool
        Runnable master = new MasterThread(arrayBqueue, numOfTasks);
        executor.submit(master);
        //initialize the worker threads and submit to pool
        for (int i = 0; i < numOfThreads - 1; i++) {
            Runnable thread = new WorkerThread(arrayBqueue);
            executor.submit(thread);
        }

        executor.shutdown();
    }

}
