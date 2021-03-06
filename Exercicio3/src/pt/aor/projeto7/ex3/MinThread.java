package pt.aor.projeto7.ex3;

import java.util.concurrent.Callable;

/**
 *
 * @author Edgar
 */
public class MinThread implements Callable{
    private final double[] list;
    public MinThread(double[] list) {
        this.list=list;
    }

    @Override
    public Double call() throws Exception {
      
    double min = list[0];
        for(int i=1; i<list.length; i++){
            if(list[i]<=min)
                min=list[i];
      }
        return min;
    }
    
    
}
