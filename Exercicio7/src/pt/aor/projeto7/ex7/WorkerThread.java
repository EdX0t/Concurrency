/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex7;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Edgar
 */
public class WorkerThread implements Runnable {

    private final CyclicBarrier cb;
    private int[] list;
    private int numberOfCycles;

    public WorkerThread(CyclicBarrier cb, int[] list, int numberOfCycles) {
        this.cb = cb;
        this.list = list;
        new Thread(this).start();
        this.numberOfCycles = numberOfCycles;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public void run() {
        while (numberOfCycles > 0) {
            //increments list values by 1
            for (int i = 0; i < list.length; i++) {
                list[i]++;
            }

            try {
                //sleep random time and send message to signal ended work
                Thread.sleep((int) (Math.random() * (3000) + 1000));
                System.out.println("Thread id: " + Thread.currentThread().getId() + " ended cycle.");
                //waits for barrier reached or interruptedexception
                cb.await();
            } catch (InterruptedException | BrokenBarrierException ex) {
                Logger.getLogger(WorkerThread.class.getName()).log(Level.SEVERE, "Thread interrupted while waiting for synchronization.");
            }
            numberOfCycles--;
        }
    }

}
