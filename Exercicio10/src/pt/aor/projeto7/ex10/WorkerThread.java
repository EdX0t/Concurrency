package pt.aor.projeto7.ex10;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class WorkerThread extends Thread {

    private final MyMonitor monitor;
    private int identifier;

    public WorkerThread(MyMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void run() {

        try {
            System.out.println("Thread with id: " + Thread.currentThread().getId() + " created");
            //locks
            monitor.enter();
            //simulate some work here
            System.out.println("I'm Thread " + Thread.currentThread().getId() + " and i'm doing some work.");
            Thread.sleep((long) (Math.random() * (5000) + 2000));
            System.out.println("I'm Thread " + Thread.currentThread().getId() + " and i finished my work.");

        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, "Thread was interrupted.");
        } finally {
            //unlocks
            monitor.leave();
        }
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getIdentifier() {
        return identifier;
    }

}
