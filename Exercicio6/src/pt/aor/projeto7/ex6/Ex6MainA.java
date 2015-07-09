/*
 *6 - a) Use the CountDownLatch class to achieve the following synchronization among threads:
 4 different threads execute a for cycle in parallel. There is a fifth thread waiting for
 these 4 threads to finish, to gather and use the four results. These results could reside in
 shared memory, but you may simply use a Thread.sleep() to simulate the work of
 these threads.
 b) Now, you should also ensure that the four worker threads do not start computation
 before the other (single) thread signals them to start their cycle, using, again, the
 CountDownLatch class.
 */
package pt.aor.projeto7.ex6;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class Ex6MainA {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        int[] listOfValues = new int[4];
        //a) using main thread as waiting thread
        //initialize the CountDownLatch with 4 count
        CountDownLatch cdl = new CountDownLatch(4);
        //Creates the 4 threads
        for (int i = 0; i < 4; i++) {
            Thread n = new Thread(new WorkerThread(cdl, listOfValues, i));
            n.start();
        }

        try {
            //waits for the 4 threads result  
            cdl.await();
            //prints the 4 thread results
            System.out.println("Results:");
            for (int i = 0; i < listOfValues.length; i++) {
                System.out.print(listOfValues[i] + ", ");
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Ex6MainA.class.getName()).log(Level.SEVERE, "Thread interrupted while waiting for other threads to complete.");
        }

    }

}
