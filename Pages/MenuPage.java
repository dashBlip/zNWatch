package StockManagementSystem.Pages;

import StockManagementSystem.UI.UI;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;
import static StockManagementSystem.Pages.OnboardingPage.con;

public class MenuPage {
    public static ResultSet resultSet;
    public static Statement statement;
    public static String userName;

    public static void menu(String username) throws IOException, SQLException, ClassNotFoundException, InterruptedException, ParseException {
        userName = username;
        statement = con.createStatement();
        resultSet = statement.executeQuery("SELECT FullName FROM UserInfo WHERE Username = '" + username + "'");
        resultSet.next();
        System.out.println("\nWelcome To Dashboard " + resultSet.getString(1).split(" ")[0] + "\n");

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do {
            System.out.println(UI.TEXT_BLUE + "-------- Main Menu -------" + UI.TEXT_RESET);
            System.out.println("Press 1 : Display Stock");
            System.out.println("Press 2 : Holdings");
            System.out.println("Press 3 : Settings");
            System.out.println("Press 4 : Balance");
            System.out.println("Press 5 : Display Portfolio");
            System.out.println("Press 6 : Display All Users Data");
            System.out.println(UI.TEXT_RED + "Press 7 : Exit" + UI.TEXT_RESET);
            System.out.print("Please Enter Your Choice : ");

            try {
                choice = sc.nextInt();
            } catch (Exception e) {
                System.out.println("Enter Valid Choice !");
                MenuPage.menu(username);
            }


            switch (choice) {
                case 1 -> {
                    int choice2;
                    do {
                        System.out.println(UI.TEXT_GREEN + "\nPress 1 : Buy");
                        System.out.println(UI.TEXT_RED + "Press 2 : Sell" + UI.TEXT_RESET);
                        System.out.print("Please Enter Your Choice : ");
                        choice2 = sc.nextInt();

                        System.out.println(" ".repeat(19) + UI.TEXT_YELLOW + "Stocks" + UI.TEXT_RESET);
                        if (choice2 == 1) {
                            MenuPage.displayStock();
                        } else if (choice2 == 2) {
                            int tmpCounter = MenuPage.holdingsPage();
                            MenuPage.sellPane(tmpCounter);
                        }
                    } while (choice2 != 1 && choice2 != 2);
                }
                case 2 -> {
                    System.out.println("-----------= Holdings =-----------");
                    MenuPage.holdingsPage();
                    Thread.sleep(1000);
                }
                case 3 -> {
                    MenuPageOptions.settings();
                }
                case 4 -> {
                    MenuPageOptions.BalancePane();
                }
                case 5 -> {
                    PortfolioPage.portfolio();
                    Thread.sleep(2000);
                }
                case 6 -> {
                    MenuPageOptions.displayView();
                }
                default -> {
                }
            }
        } while (choice != 7);

    }

    public static int holdingsPage() throws SQLException {

        resultSet = statement.executeQuery("SELECT DISTINCT * FROM StockInfo WHERE Username = '" + userName + "'" + " AND Quantity != 0");

        int counter = 1;
        while (resultSet.next()) {
            System.out.println((counter++) + " -> Stock Name : " + UI.TEXT_YELLOW + resultSet.getString(2)
                    + UI.TEXT_GREEN +"\n     Current Price : "
                    + ( Double.parseDouble(MenuPageOptions.decimalFormat.format(Objects.requireNonNull(MenuPageOptions.stockFinder(resultSet.getString(2))).currentPrice))*resultSet.getInt(4))
                    + UI.TEXT_RESET +"\n     Purchased at : " + MenuPageOptions.decimalFormat.format(resultSet.getDouble(3))
                    + "\n     Quantity Available : " + resultSet.getInt(4)
                    + "\n     Date of Purchase : " + resultSet.getString(5) + "\n"
            );
        }
        return counter - 1;
    }

    public static void sellPane(int counter) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rsTrash = st.executeQuery("SELECT COUNT(*) FROM ( SELECT DISTINCT * FROM StockInfo WHERE Username = '"+userName+"' ) AS distinct_rows; ");
        rsTrash.next();

        int rows = rsTrash.getInt(1);
        if(rows == 0){
            System.out.println(UI.TEXT_RED+"NO DATA HERE !!"+UI.TEXT_RESET);
            return;
        }

        System.out.print("Enter The Stock Number You Want To Sell : ");
        Scanner sc = new Scanner(System.in);

        try {

            int choice = sc.nextInt();

            if (choice > counter) {

                System.out.println("Invalid Choice !! \n");
                MenuPage.sellPane(counter);

            } else {

                System.out.print("\nEnter Quantity : ");
                int quantity = sc.nextInt();

                Statement statement1 = con.createStatement();

                ResultSet resultSet1 = statement1.executeQuery("SELECT DISTINCT * FROM StockInfo WHERE Username = '" + userName + "' AND Quantity != 0");


                while (choice-- > 0) {
                    resultSet1.next();
                }
                String stockName = MenuPageOptions.stockFinder(resultSet1.getString(2)).name;
                double sellPrice = MenuPageOptions.stockFinder(resultSet1.getString(2)).currentPrice;

                if (quantity > resultSet1.getInt(4)) {
                    System.out.println("Entered Value is More Than Stocks You Hold ...");
                    MenuPage.holdingsPage();
                    MenuPage.sellPane(counter);
                } else {
                    int nowQuantity = resultSet1.getInt(4) - quantity;
                    resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '" + userName +
                            "' AND StockName = '" + resultSet1.getString(2) + "'"
                    );

                    statement.execute("UPDATE StockInfo SET Quantity = " + nowQuantity + " WHERE Username = '" + userName +
                            "' AND StockName = '" + resultSet1.getString(2) + "'"
                    );

                    resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '" + userName + "'");
                    resultSet.next();
                    double nowBalance = resultSet.getDouble(1) + (sellPrice * quantity);

                    statement.execute("UPDATE UserInfo SET Balance = " + nowBalance + " WHERE Username = '" + userName + "'");

                    System.out.println(UI.TEXT_GREEN+"-> Stock Name : " + stockName + " And Amount : " + sellPrice);
                    System.out.println(UI.FONT+"-> Amount Credited And Stock Sold !\n"+UI.TEXT_RESET);
                }
            }

        } catch (InputMismatchException e) {
            System.out.println("Please Enter A valid Input !!");
            MenuPage.sellPane(counter);
        }

    }

    public static void displayStock() throws SQLException {

        Scanner sc = new Scanner(System.in);

        MenuPageOptions.displayStockTable();

        System.out.println("\nEnter The Stock Number You Want To Buy : ");
        int choice2 = sc.nextInt();

        if (choice2 <= 10 && choice2 > 0) {
            double amountDebitStock = MenuPageOptions.stocks[choice2 - 1].currentPrice;

            System.out.println("Now Enter The Quantity of Stocks To Buy :");
            int qty = sc.nextInt();

            double amtToDeduce = qty * amountDebitStock;

            System.out.println(UI.TEXT_YELLOW+"\n-> Total Amount is : " + decimalFormat.format(amtToDeduce) + UI.TEXT_RESET);

            String SQLCommand = "Select Balance FROM UserInfo WHERE Username = '" + userName + "'\n\n";
            resultSet = statement.executeQuery(SQLCommand);
            resultSet.next();
            double initialAmount = resultSet.getDouble(1);
            double deducedAmount = initialAmount - amtToDeduce;

            if (deducedAmount < 0) {
                System.out.println(UI.TEXT_RED+"Not Enough Balance !\n"+UI.TEXT_RESET);
            } else {
                statement.execute("UPDATE UserInfo SET Balance = " + deducedAmount + " WHERE Username = '" + userName + "'");


                String SQLCommand2 = "INSERT INTO StockInfo VALUES ('" + userName + "' , '" + MenuPageOptions.stocks[choice2 - 1].name + "', " + amtToDeduce
                        + " , " + qty + " , '" + (LocalDate.now()) + "')";

                statement.execute(SQLCommand2);
                System.out.println(UI.TEXT_GREEN + "-> Transaction Done SuccessFully " + userName+"\n" + UI.TEXT_RESET);
            }
        } else {
            System.out.println("Invalid Choice !!\n");
            MenuPage.displayStock();
        }


    }
}
