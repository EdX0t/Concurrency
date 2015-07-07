package pt.aor.projeto7.ex1;

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

    }

}
