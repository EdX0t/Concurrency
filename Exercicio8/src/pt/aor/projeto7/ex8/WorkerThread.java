package pt.aor.projeto7.ex8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class WorkerThread implements Runnable {

    private final ThreadMonitor monitor;

    public WorkerThread(ThreadMonitor monitor) {
        this.monitor = monitor;
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {
            monitor.enter();
            System.out.println("Thread number " + monitor.getCurrentNumber() + " prints a random number " + (int) (Math.random() * (10) + 1));
            Thread.sleep((int) (Math.random() * (2000) + 1000));
            monitor.leave();
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, "Thread was interrupted.");
        }
    }

}
