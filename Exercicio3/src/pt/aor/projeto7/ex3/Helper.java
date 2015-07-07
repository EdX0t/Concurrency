/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.aor.projeto7.ex3;

/**
 *
 * @author Ed
 */
public class Helper {
    
    
    public Helper() {
       
    }
    
    public double[] createArray(int numberOfDoubles){
      double[] list = new double[numberOfDoubles];
        for (int i = 0; i < list.length; i++) {
            list[i] = Math.random() * (100) + 1;
        }
        return list;
    }
    
    public double calculateAverage(double[] listOfDoubles) {
        double soma = 0;
        for (int i = 0; i < listOfDoubles.length; i++) {
            soma += listOfDoubles[i];
        }
        return (soma / listOfDoubles.length);
    }

}
