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

        if (list.giveTotalProfit() > 0) {
            UI.CustomTabularDisplay.printTable(new String[]{"No.","Total P&L"},new Object[][]{{1,UI.TEXT_RED + decimalFormat.format(list.giveTotalProfit())
                    + UI.TEXT_RESET}},15);
        } else {
            UI.CustomTabularDisplay.printTable(new String[]{"No","Total P&L"},new Object[][]{{1,UI.TEXT_RED + decimalFormat.format(list.giveTotalProfit())
                    + UI.TEXT_RESET}},15);
        }
    }
}
