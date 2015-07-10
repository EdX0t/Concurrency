package pt.aor.projeto7.ex9;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Edgar
 */
public class Ex9Main {

    private static final int numOfThreads = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //CountdownLatch for starting threads
        CountDownLatch cdl = new CountDownLatch(1);

        //initializes a PriorityQueue
        PriorityQueue<WorkerThread> priorityQueue = new PriorityQueue<>(new Comparator<WorkerThread>() {

            @Override
            public int compare(WorkerThread o1, WorkerThread o2) {
                if (o1.getPriority() > o2.getPriority()) {
                    return 1;
                } else if (o1.getPriority() < o2.getPriority()) {
                    return -1;
                }
                return 0;
            }
        }
        );
        //initilizes a monitor instance
        ThreadMonitor monitor = new ThreadMonitor(priorityQueue);
        //creates threadsa and adds to queue
        for (int i = 0; i < numOfThreads; i++) {
            WorkerThread thread = new WorkerThread(monitor, cdl, (int) (Math.random() * (5) + 1));
            priorityQueue.offer(thread);
        }

        //starts the threads through countdownlatch
        cdl.countDown();

    }

}
