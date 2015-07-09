package pt.aor.projeto7.ex5;

import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class ThreadTwo extends Thread {

    private final Exchanger<Integer> exchanger;
    private final String pong;
    private int counter;
    private int threadAllowed;

    public ThreadTwo(Exchanger<Integer> exchanger) {
        this.pong = "PONG";
        this.exchanger = exchanger;
        this.counter = 10;
        this.threadAllowed = 0;
    }

    @Override
    public void run() {
        while (counter > 0) {
            try {

                if (threadAllowed == 1) {
                    System.out.println(pong);
                    counter--;
                }
                threadAllowed = exchanger.exchange(threadAllowed);

            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadTwo.class.getName()).log(Level.SEVERE, "Could not exchange the string.");
            }
        }
    }
}
