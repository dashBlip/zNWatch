package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.UI.UI;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;

/**
 * The {@code PriorityQ} class represents a priority queue implemented using a linked list.
 * It manages {@link Stock} objects, keeping them sorted by profit in ascending order.
 */
public class PriorityQ {

    /** The linked list used to store stocks in the priority queue. */
    private final LinkList list;

    /**
     * Constructs an empty {@code PriorityQ}.
     */
    public PriorityQ() {
        list = new LinkList();
    }

    /**
     * Inserts a {@link Stock} into the priority queue. The stock is added to the linked list
     * while maintaining the list's order based on the stock's profit.
     *
     * @param x the {@link Stock} to be inserted into the queue
     */
    public void insert(Stock x) {
        list.insert(x);
    }

    /**
     * Removes the stock with the lowest profit from the priority queue.
     * The removal is performed from the linked list, which maintains the order of stocks.
     */
    public void remove() {
        list.remove();
    }

    /**
     * Displays all the stocks in the priority queue, along with the total profit.
     * The display format is handled by {@link UI.CustomTabularDisplay#printTable(String[], Object[][], int)}.
     */
    public void displayList() {
        list.display();

        double totalProfit = list.giveTotalProfit();
        String formattedProfit = decimalFormat.format(totalProfit);

        UI.CustomTabularDisplay.printTable(
                new String[]{"No.", "Total P&L"},
                new Object[][]{{1, UI.TEXT_RED + formattedProfit + UI.TEXT_RESET}},
                15
        );
    }
}
