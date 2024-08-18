package StockManagementSystem;

import java.sql.SQLException;

import static StockManagementSystem.Pages.*;

class Node {

    public Stock stock;
    public double iData;
    public Node next;

    public Node(Stock x) {
        iData = x.profit;
        stock = x;
    }

    public void displayNode() {
        System.out.println("-> StockName : "+stock.name+"\n   StockCurrentPrice : "+ decimalFormat.format(stock.currentPrice)
                + "\n   StockPurchasePrice : "+decimalFormat.format(stock.purchasePrice)
                + "\n   Stock - Profit&Loss : "+decimalFormat.format(iData)+"\n"
        );
    }

}
class LinkList {

    private Node first;

    public LinkList() {
        first = null;
    }

    public double giveTotalProfit(){
        Node temp = first;
        double amountToReturn = 0;

        while(temp != null){
            amountToReturn += temp.stock.profit;
            temp = temp.next;
        }

        return amountToReturn;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insert(Stock x) {
        Node newNode = new Node(x);
        Node previous = null;
        Node current = first;

        while (current != null && x.profit < current.iData) {
            previous = current;
            current = current.next;
        }

        if (previous == null) {
            newNode.next = first;
            first = newNode;
        }

        else {
            previous.next = newNode;
            newNode.next = current;
        }
    }

    public void remove() {
        Node previous = null;
        Node current = first;
        Node temp = current;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        assert previous != null;
        previous.next = null;

    }

    public void display() {
        Node current = first;

        while (current != null) {
            current.displayNode();
            current = current.next;
        }

        System.out.println(" ");
    }

}

class PriorityQ {

    private final LinkList list;

    public PriorityQ() {
        list = new LinkList();
    }
    public void insert(Stock x) {
        list.insert(x);
    }
    public void remove() {
        list.remove();

    }
    public void displayList() {
        list.display();
        System.out.println(" ".repeat(6)+"-".repeat(20));
        System.out.println(" ".repeat(6)+"| Total P&L : " + decimalFormat.format(list.giveTotalProfit()) + " |");
        System.out.println(" ".repeat(6)+"-".repeat(20)+"\n");
    }
}


public class PortfolioManager {
    public static void portfolio() throws SQLException {
        System.out.println("\n----------- Your Portfolio ----------");

        resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '"+userName+"'"+" AND Quantity != 0");

        PriorityQ queue = new PriorityQ();

        int counter = 1;
        while(resultSet.next()){
            Stock stock = stockFinder(resultSet.getString(2));
            queue.insert( new Stock(resultSet.getString(2) , stock.currentPrice , resultSet.getDouble(3)));
        }

        queue.displayList();
    }
}
