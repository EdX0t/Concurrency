/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex7;

/**
 *
 * @author Edgar
 */
public class SynchonizationThread implements Runnable {

    private final int[] list;

    public SynchonizationThread(int[] list) {
        this.list = list;
    }

    @Override
    public void run() {
        //prints the current list values
        System.out.println("All threads were synchronized, printing current list values..");
        for (int i = 0; i < list.length; i++) {
            System.out.print(list[i] + " ");
        }
        System.out.print("\n");
    }

}
