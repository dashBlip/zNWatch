package StockManagementSystem.StockFunctionality;

import java.io.Serializable;
import java.util.Random;

public class StockEngine implements Serializable {
    private final double[] d = {0};

    public void startManipulator(double value) {
        d[0] = value;

        Thread t1 = new Thread(() -> {

            while (true) {
                Random rnd = new Random();
                boolean rndBool = rnd.nextBoolean();
                double rndDouble = rnd.nextDouble(2);

                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (rndBool) {
                    d[0] += rndDouble;
                } else {
                    d[0] -= rndDouble;
                }
            }
        });

        t1.setDaemon(true);
        t1.start();
    }

    public double priceReturner() {
        return d[0];
    }
}


