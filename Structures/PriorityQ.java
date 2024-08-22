package StockManagementSystem.Structures;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.UI.UI;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;

public class PriorityQ {

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
        System.out.println(" ".repeat(6) + "-".repeat(20));
        if (list.giveTotalProfit() > 0) {
            System.out.println(" ".repeat(6) + "| Total P&L : " + UI.TEXT_GREEN + decimalFormat.format(list.giveTotalProfit()) + UI.TEXT_RESET + " |");
        } else {
            System.out.println(" ".repeat(6) + "| Total P&L : " + UI.TEXT_RED + decimalFormat.format(list.giveTotalProfit()) + UI.TEXT_RESET + " |");
        }
        System.out.println(" ".repeat(6) + "-".repeat(20) + "\n");
    }
}
