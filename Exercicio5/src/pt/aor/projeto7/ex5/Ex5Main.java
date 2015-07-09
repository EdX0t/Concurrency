/*
 *Write a program that creates two threads. During the execution of the program both
threads should write either “ping” or “pong” to the screen, alternately, as follows:
ping
pong
ping
pong
...
Use the Exchanger class to synchronize the threads.
 */
package pt.aor.projeto7.ex5;

import java.util.concurrent.Exchanger;

/**
 *
 * @author Edgar
 */
public class Ex5Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Exchanger<Integer> exchanger = new Exchanger<>();
        Thread t1 = new ThreadOne(exchanger);
        Thread t2 = new ThreadTwo(exchanger);
        t1.start();
        t2.start();
        
    }
    
}
