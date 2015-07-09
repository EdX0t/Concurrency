/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex6.B;

import java.util.concurrent.CountDownLatch;

/**
 *
 * @author Edgar
 */
public class Ex6MainB {
     public static void main(String[] args) {
        
        //initializes the list of 4 values
        int[] listOfValues = new int[4];

        //initialize the CountDownLatch with 4 count
        CountDownLatch cdl = new CountDownLatch(1);
        //B) creates the waiting thread before the worker threads
        Thread waitingThread = new Thread(new ResultsThread(cdl, listOfValues));
        waitingThread.start();
        //Creates the 4 worker threads
        for (int i = 0; i < 4; i++) {
            Thread n = new Thread(new WorkerThreadB(cdl, listOfValues, i));
            n.start();
        }

    }
}
