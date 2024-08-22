package StockManagementSystem.StockFunctionality;

import java.io.Serializable;

public class Stock implements Serializable {
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
    public String name;
    public double currentPrice;

    public double purchasePrice;
    public double profit ;

    @Override
    public String toString() {
        return "Stock Name : "+name+"\nStock Price : "+ currentPrice +" rs";
    }
}

