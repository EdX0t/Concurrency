/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aor.projeto7.ex3;

import java.util.concurrent.Callable;

/**
 *
 * @author Edgar
 */
class MaxThread implements Callable {
 private final double[] list;
    public MaxThread(double[] list) {
        this.list=list;
    }

    @Override
    public Double call() throws Exception {
      
    double max = list[0];
        for(int i=1; i<list.length; i++){
            if(list[i]>=max)
                max=list[i];
      }
        return max;
    }
    
}
