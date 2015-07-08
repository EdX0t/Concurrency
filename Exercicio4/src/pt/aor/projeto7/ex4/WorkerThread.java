package pt.aor.projeto7.ex4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class WorkerThread implements Runnable {

    private final ArrayBlockingQueue<Double> arrayQueue;

    public WorkerThread(ArrayBlockingQueue<Double> queue) {
        this.arrayQueue = queue;
    }

    @Override
    public void run() {
   
        while (true) {
            try {
                Double value = arrayQueue.poll(5, TimeUnit.SECONDS);
                if(value==null)
                    break;
                System.out.println("Thread number " + Thread.currentThread().getId() + " calculated sqrt of " + value);
                System.out.println("Result: " + Math.sqrt(value));

            } catch (InterruptedException ex) {
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, "Could not get value from queue.");
            }
        }
    }

}
