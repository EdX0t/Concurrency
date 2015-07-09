package pt.aor.projeto7.ex5;

import java.util.concurrent.Exchanger;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class ThreadOne extends Thread {

    private final Exchanger<Integer> exchanger;
    private final String print;
    private int counter;
    private int threadAllowed;

    public ThreadOne(Exchanger<Integer> exchanger, String print, int flag) {
        this.print = print;
        this.counter = 10;
        this.exchanger = exchanger;
        this.threadAllowed = flag;
    }

    @Override
    public void run() {

        while (counter > 0) {
            try {
                if (threadAllowed == 1) {
                    System.out.println(print);
                }
                threadAllowed = exchanger.exchange(threadAllowed);

            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadOne.class.getName()).log(Level.SEVERE, "Could not exchange the string.");
            }
            counter--;
        }
    }

}
