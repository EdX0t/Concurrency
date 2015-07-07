/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex1;

/**
 *
 * @author Edgar
 */
public class Task implements Runnable{

    private final int numTask;
    public Task(int numTasks){
        this.numTask = numTasks;
    }
    
    @Override
    public void run() {
      System.out.println("Hello World, i am thread number "+Thread.currentThread().getId()
      +" running task number "+numTask);
   
    }
    
}
