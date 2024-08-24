package StockManagementSystem.Pages;

import StockManagementSystem.UI.UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;
import static StockManagementSystem.Pages.OnboardingPage.con;

/**
 * The AdminMenuPage class provides the functionality for the admin menu of the stock management system.
 * It allows the admin to view all user data and exit the menu.
 */
public class AdminMenuPage {

    /**
     * Displays all user data in a tabular format.
     * Retrieves data from the "AllDataView" table and prints it using a custom tabular display.
     *
     * @throws SQLException if a database access error occurs or the SQL query is incorrect
     */
    private void displayView() throws SQLException {
        ResultSet resultSet = null;
        Statement statement = con.createStatement();

        // Execute query to get the count of rows in the view
        resultSet = statement.executeQuery("SELECT COUNT(*) FROM AllDataView");
        resultSet.next();
        int rows = resultSet.getInt(1);

        // Execute query to get all data from the view
        resultSet = statement.executeQuery("SELECT * FROM AllDataView");

        // Initialize a 2D array to hold the data
        Object[][] dataArray = new Object[rows][];
        int counter = 0;

        // Iterate through the result set and populate the dataArray
        while (resultSet.next()){
            dataArray[counter++] = new Object[]{
                    counter,
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getLong(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    decimalFormat.format(resultSet.getDouble(7)),
                    resultSet.getString(8),
                    decimalFormat.format(resultSet.getDouble(9)),
                    resultSet.getInt(10),
                    resultSet.getString(11),
                    resultSet.getString(12),
                    resultSet.getString(13),
                    resultSet.getString(14),
                    resultSet.getString(15),
                    resultSet.getString(16)
            };
        }

        // Define headers for the tabular display
        String[] HEADERS = {
                "Sr.No", "UsrName", "Name", "DOB", "Phone", "Email", "PAN", "Balance",
                "Stock", "SPrice", "Quantity", "StockPD", "OldPass", "NewPass",
                "OldEmail", "NewEmail", "DeletePass"
        };

        // Print the table using the custom tabular display utility
        UI.CustomTabularDisplay.printTable(HEADERS, dataArray, 14);
        System.out.println("\n");
    }

    /**
     * Displays the admin menu and handles user input.
     * Allows the admin to choose between viewing all user data or exiting the menu.
     */
    public void adminMenu() {
        UI.printTOP(UI.TEXT_BLUE);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            // Display the menu options
            System.out.println(UI.TEXT_BLUE + "-------- Main Menu -------" + UI.TEXT_RESET);
            System.out.println("PRESS 1 : View All User Data");
            System.out.println(UI.TEXT_RED + "PRESS 2 : EXIT" + UI.TEXT_RESET);
            System.out.print("\nEnter Your Choice : ");

            try {
                // Read and handle user input
                choice = sc.nextInt();

                if (choice == 1){
                    displayView(); // Display all user data
                } else if (choice != 2) {
                    System.out.println(UI.TEXT_RED + "Enter Valid choice !!" + UI.TEXT_RESET);
                }

            } catch (Exception e) {
                System.out.println(UI.TEXT_RED + "Please Enter valid Value !!" + UI.TEXT_RESET);
                e.printStackTrace();
            }

        } while (choice != 2); // Exit the menu when choice is 2
    }
}
