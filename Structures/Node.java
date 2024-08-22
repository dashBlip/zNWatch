package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;

import static StockManagementSystem.Pages.Pages.decimalFormat;

public class Node {

    public Stock stock;
    public double iData;
    public Node next;

    public Node(Stock x) {
        iData = x.profit;
        stock = x;
    }

    public void displayNode() {
        System.out.println("-> StockName : " + stock.name + "\n   StockCurrentPrice : " + decimalFormat.format(stock.currentPrice)
                + "\n   StockPurchasePrice : " + decimalFormat.format(stock.purchasePrice)
                + "\n   Stock - Profit&Loss : " + decimalFormat.format(iData) + "\n"
        );
    }

}
