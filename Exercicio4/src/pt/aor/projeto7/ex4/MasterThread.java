/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pt.aor.projeto7.ex4;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ed
 */
public class MasterThread implements Runnable{

    private final ArrayBlockingQueue<Double> queue;
     //task number
    private final int numOfTasks;
    
    public MasterThread(ArrayBlockingQueue queue, int numTasks){
        this.queue = queue;
        this.numOfTasks = numTasks;
    }

    @Override
    public void run() {
               //generates the numbers for computation by worker threads
        for (int n = 0; n < numOfTasks; n++) {
            //generates value between 1 and 1000
            Double value = Math.random() * (1000) + 1;
            //puts the value in the tail of the ArrayBlockingQueue
            try {
                queue.put(value);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ex4Main.class.getName()).log(Level.SEVERE, "Error while putting the value in the queue - Master Thread was interrupted.");
            }
            //thread sleeps for a random amount of time (lets consider 100 to 2000 ms)
            int sleepTime = (int) ((1001 - 100) * Math.random() + 100);
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException ex) {
                Logger.getLogger(Ex4Main.class.getName()).log(Level.SEVERE, "Thread was interrupted while sleeping.");
            }
        }
    }
}
