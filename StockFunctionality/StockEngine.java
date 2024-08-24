package StockManagementSystem.StockFunctionality;

import java.io.Serializable;
import java.util.Random;

/**
 * The {@code StockEngine} class simulates the fluctuation of stock prices.
 * It provides methods to start a price manipulation process and retrieve the current stock price.
 */
public class StockEngine implements Serializable {

    /** The current stock price being simulated. */
    private final double[] d = {0};

    /**
     * Starts a background thread that simulates stock price fluctuations.
     * The stock price is adjusted randomly every 3 seconds.
     *
     * @param value the initial stock price to start with
     */
    public void startManipulator(double value) {
        d[0] = value;

        Thread t1 = new Thread(() -> {
            Random rnd = new Random();
            while (true) {
                boolean rndBool = rnd.nextBoolean();
                double rndDouble = rnd.nextDouble() * 2; // Generate a random double between 0.0 and 2.0

                try {
                    Thread.sleep(3000); // Wait for 3 seconds
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                if (rndBool) {
                    d[0] += rndDouble; // Increase the price
                } else {
                    d[0] -= rndDouble; // Decrease the price
                }
            }
        });

        t1.setDaemon(true); // Ensures the thread does not prevent the application from exiting
        t1.start();
    }

    /**
     * Returns the current simulated stock price.
     *
     * @return the current stock price
     */
    public double priceReturner() {
        return d[0];
    }
}
