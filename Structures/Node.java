package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.UI.UI;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;

public class Node {

    public Stock stock;
    public double iData;
    public double purchasedWithPrice;
    public Node next;
    public Node left;
    public Node right;

    public Node(Stock x) {
        iData = x.profit;
        purchasedWithPrice = x.purchasePrice;
        stock = x;
    }

    public void displayNode() {
        System.out.println(UI.TEXT_YELLOW +"-> StockName : " + stock.name + (stock.currentPrice > stock.purchasePrice ? UI.TEXT_GREEN : UI.TEXT_RED)
                +"\n   StockCurrentPrice : " + decimalFormat.format(stock.currentPrice)
                + UI.TEXT_RESET + "\n   StockPurchasePrice : " + decimalFormat.format(stock.purchasePrice)
                + "\n   Stock - Profit&Loss : " + decimalFormat.format(iData) + "\n"
        );
    }

}
