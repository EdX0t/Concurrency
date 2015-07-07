package pt.aor.projeto7.ex2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Ed
 */
public class ThreadExercicio2 extends Thread {

    private final Semaphore[] semaphore;
    private final int index;

    //construtor
    public ThreadExercicio2(Semaphore[] semaphore, int index) {
        this.semaphore = semaphore;
        this.index = index;

    }

    @Override
    public void run() {
        try {
            semaphore[index].acquire();
            String message = "I am thread number: " + (Thread.currentThread().getId());
            System.out.println(message);

        } catch (InterruptedException ie) {

        } finally {
            //release the semaphore index by the order
            if ((index + 1) % 2 == 0) {
                if (index == 0) {
                    semaphore[index + 3].release();
                } else if (index + 1 <= semaphore.length) {
                    semaphore[index - 1].release();
                }
            } else if ((index + 1) % 2 == 1 && index + 3 < semaphore.length) {
                semaphore[index + 3].release();
            }

        }
    }

}
