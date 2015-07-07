/*
 *2. Write a program that creates 8 threads and ensures that they run in the following order: 2-
1-4-3-6-5-8-7. Force the threads to print their identifier, to ensure that they behave as
intended.
 */
package pt.aor.projeto7.ex2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Edgar
 */
public class Ex2 {
    private static final int numberOfThreads = 8;
   
    
    public static void main(String[] args){
        //cria semaforos
         Semaphore[] semaphores = new Semaphore[numberOfThreads];
         for(int i=0; i<semaphores.length; i++){
              if(i==1){
            semaphores[i] = new Semaphore(1);
         } else {
            semaphores[i] = new Semaphore(0);
              } 
         }

         //cria threads e passa semaforo
         for(int i=0; i<numberOfThreads; i++){
             ThreadExercicio2 thread = new ThreadExercicio2(semaphores, i);
             thread.start();
    }
    
    }
    
}
