package StockManagementSystem.Pages;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.Structures.PriorityQ;

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
}
