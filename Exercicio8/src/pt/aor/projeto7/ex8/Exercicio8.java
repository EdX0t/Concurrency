package pt.aor.projeto7.ex8;

/**
 *
 * @author Edgar
 */
public class Exercicio8 {

    private static final int numThreads = 10;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //initilizes a monitor instance
        ThreadMonitor monitor = new ThreadMonitor();
        //creates numThreads with the monitor instance
        for (int i = 0; i < numThreads; i++) {
            Runnable thread = new WorkerThread(monitor);
        }
    }

}
