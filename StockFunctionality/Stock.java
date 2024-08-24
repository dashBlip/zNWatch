package StockManagementSystem.StockFunctionality;

import java.io.Serializable;

/**
 * The {@code Stock} class represents a stock with its associated properties and behaviors.
 * It includes details about the stock such as its name, current price, purchase price, and profit.
 * The class also manages the updating of stock prices using a background thread.
 */
public class Stock implements Serializable {

    /** The name of the stock. */
    public String name;

    /** The current price of the stock. */
    public double currentPrice;

    /** The purchase price of the stock. */
    public double purchasePrice;

    /** The profit calculated from the stock based on the current price and quantity. */
    public double profit;

    /** An instance of {@code StockEngine} used for manipulating and retrieving stock prices. */
    public StockEngine st = new StockEngine();

    /**
     * Constructs a {@code Stock} with the specified name and current price.
     * The price is updated in a separate thread to reflect real-time changes.
     *
     * @param name the name of the stock
     * @param currentPrice the current price of the stock
     */
    public Stock(String name, double currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
        st.startManipulator(currentPrice);
        priceSetter();
    }

    /**
     * Constructs a {@code Stock} with the specified name, current price, purchase price, and quantity.
     * The profit is calculated based on the current price and quantity.
     *
     * @param name the name of the stock
     * @param currentPrice the current price of the stock
     * @param purchasePrice the purchase price of the stock
     * @param quantity the quantity of the stock
     */
    public Stock(String name, double currentPrice, double purchasePrice, int quantity) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.purchasePrice = purchasePrice;
        this.profit = (currentPrice * quantity) - purchasePrice;
    }

    /**
     * Starts a background thread to periodically update the stock price.
     * The stock price is updated every 2 seconds based on the {@code StockEngine} instance.
     */
    public void priceSetter() {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                currentPrice = st.priceReturner();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    /**
     * Returns a string representation of the {@code Stock}.
     * The representation includes the stock's name and current price.
     *
     * @return a string representation of the stock
     */
    @Override
    public String toString() {
        return "Stock Name : " + name + "\nStock Price : " + currentPrice + " rs";
    }
}
