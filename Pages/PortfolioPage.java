package StockManagementSystem.Pages;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.Structures.BinarySearchTree;
import StockManagementSystem.Structures.PriorityQ;
import StockManagementSystem.UI.UI;

import java.sql.SQLException;

import static StockManagementSystem.Pages.MenuPageOptions.*;

/**
 * The {@code PortfolioPage} class provides methods for displaying and managing
 * the user's stock portfolio within the stock management system. It includes functionality
 * to display the portfolio with current stock information and calculate the total profit and loss.
 */
public class PortfolioPage {

    /**
     * Displays the user's portfolio, showing stocks with non-zero quantities in a priority queue.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void portfolio() throws SQLException {
        System.out.println("\n----------- Your Portfolio ----------");

        // Retrieve stock information from the database where quantity is not zero
        resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '" + userName + "' AND Quantity != 0");

        PriorityQ queue = new PriorityQ();

        // Process each stock entry
        while (resultSet.next()) {
            // Find stock details using the stock name from the database
            Stock stock = stockFinder(resultSet.getString(2));
            // Insert the stock into the priority queue
            queue.insert(new Stock(resultSet.getString(2), stock.currentPrice, resultSet.getDouble(3), resultSet.getInt(4)));
        }

        // Display the stocks in the priority queue
        queue.displayList();
    }

    /**
     * Displays the user's portfolio and calculates the total profit and loss.
     * Stocks are stored in a binary search tree for efficient traversal.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void portfolioPurchase() throws SQLException {
        System.out.println("\n----------- Your Portfolio ----------");

        // Retrieve stock information from the database where quantity is not zero
        resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '" + userName + "' AND Quantity != 0");

        BinarySearchTree binarySearchTree = new BinarySearchTree();

        // Process each stock entry
        while (resultSet.next()) {
            // Find stock details using the stock name from the database
            Stock stock = stockFinder(resultSet.getString(2));
            // Insert the stock into the binary search tree
            binarySearchTree.insert(new Stock(resultSet.getString(2), stock.currentPrice, resultSet.getDouble(3), resultSet.getInt(4)));
        }

        // Perform an in-order traversal of the binary search tree to display the stocks
        binarySearchTree.inOrderTraversal();
        System.out.println("\n");

        // Display the total profit and loss
        UI.CustomTabularDisplay.printTable(new String[]{"No.", "Total P&L"}, new Object[][]{{1, UI.TEXT_RED + decimalFormat.format(binarySearchTree.giveTotalProfit()) + UI.TEXT_RESET}}, 15);
    }
}
