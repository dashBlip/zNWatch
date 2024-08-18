package StockManagementSystem;

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


class Stock implements Serializable {
    public Stock(String name, double currentPrice) {
        this.name = name;
        this.currentPrice = currentPrice;
        st.startManipulator(currentPrice);
        priceSetter();
    }

    public Stock(String name, double currentPrice, double purchasePrice) {
        this.name = name;
        this.currentPrice = currentPrice;
        this.purchasePrice = purchasePrice;
        this.profit = currentPrice - purchasePrice;
    }

    StockEngine st = new StockEngine();

    public void priceSetter(){
        Thread thread = new Thread(()->{
            while(true){
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
    String name;
    public double currentPrice;

    double purchasePrice;
    double profit ;

    @Override
    public String toString() {
        return "Stock Name : "+name+"\nStock Price : "+ currentPrice +" rs";
    }
}

