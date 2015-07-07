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
public class Ex3Sequential {

    private static int numberOfDoubles;
    private static double[] listOfDoubles;
    private static long zeroTime, splitTime, sequentialTime;

    public static void main(String[] args) {
        //initial reference time
        zeroTime = System.nanoTime();
        
        //creates the double array
        numberOfDoubles = Integer.valueOf(args[0]);
        
        //creates instance of helper class
        Helper helper = new Helper();
        
        //generates doubles array
       listOfDoubles = helper.createArray(numberOfDoubles);
      
       //calculate the average
       double avg = helper.calculateAverage(listOfDoubles);
       
       //calculate split time
       splitTime = (System.nanoTime() - zeroTime) / 1000;

        //calculate minimum
        double min = listOfDoubles[0];
        for (int i = 1; i < listOfDoubles.length; i++) {
            if (listOfDoubles[i] < min) {
                min = listOfDoubles[i];
            }
        }
        System.out.println("Minimum: " + min);
        //calculate maximum
        double max = listOfDoubles[0];
        for (int i = 1; i < listOfDoubles.length; i++) {
            if (listOfDoubles[i] > max) {
                max = listOfDoubles[i];
            }
        }
        System.out.println("Maximum: " + max);

        sequentialTime = (System.nanoTime() - zeroTime) / 1000;
        
        //prints results
        System.out.println("Average: "+avg);
        System.out.println("Minimum: "+min);
        System.out.println("Maximum: "+max);
        System.out.println("Total sequential execution time [microseconds]: "+sequentialTime);
        
    }

}
