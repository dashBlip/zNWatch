package StockManagementSystem.Pages;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.Structures.BinarySearchTree;
import StockManagementSystem.Structures.PriorityQ;
import StockManagementSystem.UI.UI;

import java.sql.SQLException;

import static StockManagementSystem.Pages.MenuPageOptions.*;


public class PortfolioPage {
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

    public static void portfolioPurchase() throws SQLException {
        System.out.println("\n----------- Your Portfolio ----------");

        resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '"+userName+"'"+" AND Quantity != 0");

        BinarySearchTree binarySearchTree = new BinarySearchTree();

        int counter = 1;
        while(resultSet.next()){
            Stock stock = stockFinder(resultSet.getString(2));
            binarySearchTree.insert( new Stock(resultSet.getString(2) , stock.currentPrice , resultSet.getDouble(3)));
        }

        binarySearchTree.inOrderTraversal();
        System.out.println("\n");
        UI.CustomTabularDisplay.printTable(new String[]{"No.","Total P&L"},new Object[][]{{1,UI.TEXT_RED + decimalFormat.format(binarySearchTree.giveTotalProfit())
                + UI.TEXT_RESET}},15);

    }
}
