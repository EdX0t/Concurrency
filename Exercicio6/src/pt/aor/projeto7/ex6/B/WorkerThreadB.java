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
public class WorkerThreadB implements Runnable{
    private final int sleepTime;
    private final int n = 5;
    private final CountDownLatch cdlatch;
    private final int[] list;
    private final int threadNumber;

    public WorkerThreadB(CountDownLatch cdlatch, int[] list, int threadNumber) {
        sleepTime = (int) (Math.random() * (2000) + 1000);
        this.cdlatch = cdlatch;
        this.list = list;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        try {
            System.out.println("Thread "+threadNumber+" is waiting for signal to start");
            cdlatch.await();
            int returnValue = (int) (Math.random() * (9) + 1);
    
            Thread.sleep(sleepTime);
            list[threadNumber] = returnValue;
            System.out.println("Thread number " + threadNumber + " computed number "+returnValue);
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThreadB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
