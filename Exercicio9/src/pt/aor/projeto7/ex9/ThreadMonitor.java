package pt.aor.projeto7.ex9;

import java.util.PriorityQueue;

/**
 *
 * @author Edgar
 */
public class ThreadMonitor {

    private PriorityQueue queue;
    
    public ThreadMonitor(PriorityQueue queue) {
        this.queue = queue;
    }
    
    public synchronized void enter(WorkerThread runnable) {
        while (queue.peek() != runnable) {
            try {
                wait();
            } catch (InterruptedException e) {
                
            }
        }
        queue.poll();
    }

    public synchronized void leave() {
        notifyAll();
    }

}

