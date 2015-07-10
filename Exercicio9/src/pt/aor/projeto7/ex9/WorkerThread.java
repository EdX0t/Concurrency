package pt.aor.projeto7.ex9;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class WorkerThread implements Runnable {

    private final ThreadMonitor monitor;
    private final int priority;
    private final CountDownLatch cdl;

    public WorkerThread(ThreadMonitor monitor, CountDownLatch cdl, int priority) {
        this.monitor = monitor;
        this.priority = priority;
        this.cdl = cdl;
        new Thread(this).start();
    }

    @Override
    public void run() {

        try {
            cdl.await();
            monitor.enter(this);
            System.out.println("Thread " + Thread.currentThread().getId()
                    + " with priority:" + priority + " prints random:"
                    + (int) (Math.random() * (10) + 1));
            Thread.sleep((int) (Math.random() * (2000) + 1000));
            monitor.leave();
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, "Thread was interrupted.");
        }
    }

    public int getPriority() {
        return priority;
    }

}
