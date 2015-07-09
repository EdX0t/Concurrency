/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
    private final String ping;
    private int counter;
    private int threadAllowed;

    public ThreadOne(Exchanger<Integer> exchanger) {
        this.ping = "PING";
        this.counter = 10;
        this.exchanger = exchanger;
        this.threadAllowed = 1;
    }

    @Override
    public void run() {

        while (counter > 0) {
            try {

                if (threadAllowed == 1) {
                    System.out.println(ping);
                    counter--;
                }
                threadAllowed = exchanger.exchange(threadAllowed);

            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadTwo.class.getName()).log(Level.SEVERE, "Could not exchange the string.");
            }

        }
    }

}
