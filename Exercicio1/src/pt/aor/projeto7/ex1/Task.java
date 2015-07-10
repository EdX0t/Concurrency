package pt.aor.projeto7.ex1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class Task implements Runnable {

    private final int numTask;

    public Task(int numTasks) {
        this.numTask = numTasks;
    }

    @Override
    public void run() {
        System.out.println("Hello World, i am thread number " + Thread.currentThread().getId()
                + " running task number " + numTask);
        try {
            //simulate some aditional work to distinguish threads
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(Task.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
