package pt.aor.projeto7.ex8;

/**
 *
 * @author Edgar
 */
public class ThreadMonitor {

    private int currentNumber;
    private int numberGiven;

    public ThreadMonitor() {
        currentNumber = 1;
        numberGiven = 1;
    }
    
    //assigns ticket number to thread
    public synchronized void enter() {
        int threadNumber = numberGiven;
        numberGiven++;
        while (threadNumber != currentNumber) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }

    }
    //thread increments current ticket number
    public synchronized void leave() {
        currentNumber++;
        notifyAll();
    }

    public int getCurrentNumber() {
        return currentNumber;
    }

}
