package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.UI.UI;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;

/**
 * The Node class represents a node in a data structure that holds a Stock object.
 * It can be used in various data structures such as linked lists or binary trees.
 */
public class Node {

    /**
     * The Stock object associated with this node.
     */
    public Stock stock;

    /**
     * The profit associated with the stock. This is typically calculated as the difference
     * between the current price and the purchase price.
     */
    public double iData;

    /**
     * The price at which the stock was purchased.
     */
    public double purchasedWithPrice;

    /**
     * A reference to the next node in a linked list or similar structure.
     */
    public Node next;

    /**
     * A reference to the left child node in a binary tree structure.
     */
    public Node left;

    /**
     * A reference to the right child node in a binary tree structure.
     */
    public Node right;

    /**
     * Constructs a new Node with the given Stock object.
     *
     * @param x the Stock object to be associated with this node
     */
    public Node(Stock x) {
        iData = x.profit; // Initialize the profit data
        purchasedWithPrice = x.purchasePrice; // Initialize the purchase price
        stock = x; // Assign the Stock object to the node
    }

    /**
     * Displays the details of the stock contained in this node.
     * The display format includes the stock's name, current price, purchase price,
     * and profit/loss. The current price is highlighted in green if it's greater than
     * the purchase price, otherwise in red.
     */
    public void displayNode() {
        System.out.println(UI.TEXT_YELLOW + "-> StockName : " + stock.name
                + (stock.currentPrice > stock.purchasePrice ? UI.TEXT_GREEN : UI.TEXT_RED)
                + "\n   StockCurrentPrice : " + decimalFormat.format(stock.currentPrice)
                + UI.TEXT_RESET + "\n   StockPurchasePrice : " + decimalFormat.format(stock.purchasePrice)
                + "\n   Stock - Profit&Loss : " + decimalFormat.format(iData) + "\n"
        );
    }
}
