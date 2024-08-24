package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;

/**
 * The {@code LinkList} class represents a singly linked list for storing {@link Stock} objects.
 * The list is maintained in ascending order based on the profit of the stocks.
 */
class LinkList {

    /** The first node of the linked list. */
    private Node first;

    /**
     * Constructs an empty {@code LinkList}.
     */
    public LinkList() {
        first = null;
    }

    /**
     * Calculates the total profit of all stocks in the linked list.
     *
     * @return the total profit of all stocks
     */
    public double giveTotalProfit() {
        Node temp = first;
        double amountToReturn = 0;

        while (temp != null) {
            amountToReturn += temp.stock.profit;
            temp = temp.next;
        }

        return amountToReturn;
    }

    /**
     * Checks whether the linked list is empty.
     *
     * @return {@code true} if the list is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Inserts a {@link Stock} into the linked list, maintaining the list in ascending order
     * based on the stock's profit.
     *
     * @param x the {@link Stock} to be inserted
     */
    public void insert(Stock x) {
        Node newNode = new Node(x);
        Node previous = null;
        Node current = first;

        while (current != null && x.profit < current.stock.profit) {
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

    /**
     * Removes the last {@link Node} from the linked list.
     * If the list is empty, no action is taken.
     */
    public void remove() {
        if (first == null) {
            return; // List is empty, nothing to remove
        }

        Node previous = null;
        Node current = first;

        while (current.next != null) {
            previous = current;
            current = current.next;
        }

        if (previous != null) {
            previous.next = null; // Remove the last node
        } else {
            first = null; // List had only one node
        }
    }

    /**
     * Displays the {@link Stock} objects in the linked list.
     */
    public void display() {
        Node current = first;
        while (current != null) {
            current.displayNode();
            current = current.next;
        }

        System.out.println(" ");
    }
}
