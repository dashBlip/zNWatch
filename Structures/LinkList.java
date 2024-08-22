package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;

class LinkList {

    private Node first;

    public LinkList() {
        first = null;
    }

    public double giveTotalProfit() {
        Node temp = first;
        double amountToReturn = 0;

        while (temp != null) {
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
        } else {
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
