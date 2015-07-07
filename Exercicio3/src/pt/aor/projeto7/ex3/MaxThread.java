package pt.aor.projeto7.ex3;

import java.util.concurrent.Callable;

/**
 *
 * @author Ed
 */
class MaxThread implements Callable {

    private final double[] list;

    public MaxThread(double[] list) {
        this.list = list;
    }

    @Override
    public Double call() throws Exception {

        double max = list[0];
        for (int i = 1; i < list.length; i++) {
            if (list[i] >= max) {
                max = list[i];
            }
        }
        return max;
    }

}
