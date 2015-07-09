/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex6.B;

import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class ResultsThread implements Runnable{
    private final CountDownLatch cdl;
    private int[] list;
    public ResultsThread(CountDownLatch cdl, int[] list) {
        this.cdl=cdl;
        this.list=list;
    }

    @Override
    public void run() {
       cdl.countDown();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ResultsThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        //prints the 4 thread results
            System.out.println("Results:");
            for (int i = 0; i < list.length; i++) {
                System.out.print(list[i] + ", ");
            }
    }
    
    
    
}
