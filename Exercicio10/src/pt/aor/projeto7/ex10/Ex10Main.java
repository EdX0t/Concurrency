package pt.aor.projeto7.ex10;
/**
 *
 * @author Edgar
 */
public class Ex10Main {

    private static final int nThreads = 10;

    public static void main(String[] args) {
        //creates my monitor
        MyMonitor monitor = new MyMonitor();
        
        //creates threads and assigns them the monitor for critial region access
      
        for(int i = 0; i<nThreads; i++){
        WorkerThread t = new WorkerThread(monitor);
        t.start();
        }
       
   
    }

}
