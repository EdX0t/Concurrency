package pt.aor.projeto7.ex7;

import java.util.concurrent.CyclicBarrier;

/**
 *
 * @author Edgar
 */
public class Ex7Main {

    private static final int threadNumber = 4;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int[] list = new int[5];
        CyclicBarrier cb = new CyclicBarrier(threadNumber, new SynchonizationThread(list));
        for (int i = 0; i < threadNumber; i++) {
            WorkerThread workerThread = new WorkerThread(cb, list, 3);
        }
    }
}
