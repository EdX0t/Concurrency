/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex6;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class WorkerThread implements Runnable {

    private final int sleepTime;
    private final int n = 5;
    private final CountDownLatch cdlatch;
    private final int[] list;
    private final int threadNumber;

    public WorkerThread(CountDownLatch cdlatch, int[] list, int threadNumber) {
        sleepTime = (int) (Math.random() * (3000) + 1000);
        this.cdlatch = cdlatch;
        this.list = list;
        this.threadNumber = threadNumber;
    }

    @Override
    public void run() {
        int returnValue = (int) (Math.random() * (9) + 1);
        try {
            Thread.sleep(sleepTime);
            list[threadNumber] = returnValue;
        } catch (InterruptedException ex) {
            Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Thread number " + Thread.currentThread().getId() + " computed number "+returnValue);
        cdlatch.countDown();
    }

}
