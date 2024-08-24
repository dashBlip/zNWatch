package StockManagementSystem.Pages;

import StockManagementSystem.StockFunctionality.Stock;
import StockManagementSystem.UI.UI;

import java.io.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

import static StockManagementSystem.Pages.OnboardingPage.RedirectionPage;

/**
 * The {@code MenuPageOptions} class provides additional functionalities and options
 * for the stock management system, including balance management, user settings,
 * and stock management operations.
 */
public class MenuPageOptions extends MenuPage {

    static File file = new File("zNWatch/StockFunctionality/StockData.txt");
    static Stock[] stocks = new Stock[10];
    public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    /**
     * Displays the balance pane where users can view their current balance,
     * credit or debit their balance, or return to the previous menu.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void BalancePane() throws SQLException {
        resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '" + userName + "'");
        resultSet.next();

        System.out.println(UI.TEXT_YELLOW + "\n--> Your Current Balance : " + decimalFormat.format(resultSet.getDouble(1)) + " rs\n" + UI.TEXT_RESET);
        System.out.println("Press 1 : " + UI.TEXT_GREEN + "Credit Balance" + UI.TEXT_RESET);
        System.out.println("Press 2 : " + UI.TEXT_RED + "Debit Balance" + UI.TEXT_RESET);
        System.out.println("Press 3 : Return");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        switch (choice) {
            case 1 -> addBalance();
            case 2 -> removeBalance();
            case 3 -> System.out.println();
            default -> {
                System.out.println("Invalid Choice !!\n");
                BalancePane();
            }
        }
    }

    /**
     * Provides options to update user settings such as deleting an account,
     * changing the password, updating email, or logging out.
     *
     * @throws SQLException if a database access error occurs
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws ParseException if there is an error parsing dates
     */
    public static void settings() throws SQLException, IOException, ClassNotFoundException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println(UI.TEXT_YELLOW + "\n------- Settings -------" + UI.TEXT_RESET);
        System.out.println(UI.TEXT_RED + "Press 1 : Delete Account" + UI.TEXT_RESET);
        System.out.println("Press 2 : Edit Password");
        System.out.println("Press 3 : Edit eMail");
        System.out.println("Press 4 : Go Back");
        System.out.println(UI.TEXT_YELLOW + "Press 5 : Log Out" + UI.TEXT_RESET);
        System.out.print("Please Enter Your Choice : ");

        int choice = 0;
        try {
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    System.out.println("Please Enter Password For Confirmation : ");
                    String pass = sc.next();
                    if (pass.equals(OnboardingPage.passwordForLater)) {
                        statement.execute("DELETE FROM UserInfo WHERE Username = '" + userName + "'");
                        RedirectionPage();
                    } else {
                        System.out.println("Invalid Password !! ");
                    }
                }
                case 2 -> {
                    System.out.println("Please Enter Password For Confirmation : ");
                    String pass = sc.next();
                    if (pass.equals(OnboardingPage.passwordForLater)) {
                        System.out.print("Enter New Password : ");
                        String newPass = sc.next();
                        statement.execute("UPDATE UserInfo SET Password = '" + newPass + "' WHERE Username = '" + userName + "'");
                        System.out.println("Password Updated Successfully !!\n");
                        OnboardingPage.passwordForLater = newPass;
                    } else {
                        System.out.println("Invalid Password !! ");
                    }
                }
                case 3 -> {
                    System.out.print("Enter New eMail : ");
                    String newEmail = sc.next();
                    statement.execute("UPDATE UserInfo SET eMail = '" + newEmail + "' WHERE Username = '" + userName + "'");
                    System.out.println("eMail Updated Successfully !!\n");
                }
                case 4 -> System.out.println();
                case 5 -> {
                    OnboardingPage.loginPane();
                    System.exit(0);
                }
                default -> {
                    System.out.println("Invalid Choice !!");
                    settings();
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter Valid Choice !!");
            settings();
        }
    }

    /**
     * Adds a specified amount to the user's balance.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void addBalance() throws SQLException {
        Scanner sc = new Scanner(System.in);
        resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '" + userName + "'");
        resultSet.next();
        double previousBalance = resultSet.getDouble(1);

        System.out.print("Enter Amount To Add : ");
        double balanceToAdd = 0;
        try {
            balanceToAdd = sc.nextDouble();
            statement.execute("UPDATE UserInfo SET Balance = " + (previousBalance + balanceToAdd) + " WHERE Username = '" + userName + "'");
            System.out.println("Balance Added Successfully\n");
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Amount !!");
            addBalance();
        }
    }

    /**
     * Removes a specified amount from the user's balance.
     *
     * @throws SQLException if a database access error occurs
     */
    public static void removeBalance() throws SQLException {
        Scanner sc = new Scanner(System.in);
        resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '" + userName + "'");
        resultSet.next();
        double previousBalance = resultSet.getDouble(1);

        System.out.print("Enter Amount To Remove : ");
        try {
            double balanceToRemove = sc.nextDouble();
            if (balanceToRemove > previousBalance) {
                System.out.println("Not Enough Balance !");
            } else {
                statement.execute("UPDATE UserInfo SET Balance = " + (previousBalance - balanceToRemove) + " WHERE Username = '" + userName + "'");
                System.out.println("Balance Debited Successfully");
            }
        } catch (InputMismatchException e) {
            System.out.println("Please Enter a Valid Amount !!");
            removeBalance();
        }
    }

    /**
     * Finds and returns a {@link Stock} object based on the stock name.
     *
     * @param stockName the name of the stock to find
     * @return the {@link Stock} object with the matching name, or a new stock with default values if not found
     */
    public static Stock stockFinder(String stockName) {
        for (Stock stock : stocks) {
            if (stock.name.equals(stockName)) {
                return stock;
            }
        }
        return new Stock("null", 0);
    }

    /**
     * Loads stock data from a file. If the file does not exist, initializes with default stock values.
     *
     * @throws IOException if an I/O error occurs
     * @throws ClassNotFoundException if the class of a serialized object cannot be found
     */
    public static void StockLoader() throws IOException, ClassNotFoundException {
        if (file.exists()) {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            stocks = (Stock[]) ois.readObject();
            for (Stock stock : stocks) {
                stock.st.startManipulator(stock.currentPrice);
                stock.priceSetter();
            }
            ois.close();
        } else {
            stocks[0] = new Stock("JioFinance", 340);
            stocks[1] = new Stock("MTNL", 73);
            stocks[2] = new Stock("HDFC Bank", 35.25);
            stocks[3] = new Stock("Suzlon", 55);
            stocks[4] = new Stock("Reliance", 600);
            stocks[5] = new Stock("HAL", 1000);
            stocks[6] = new Stock("TCS", 95);
            stocks[7] = new Stock("Wipro", 250);
            stocks[8] = new Stock("BDL", 3000);
            stocks[9] = new Stock("Yes Bank", 10000);
        }
    }

    /**
     * Saves the current stock data to a file.
     *
     * @throws IOException if an I/O error occurs
     */
    public static void StockSaver() throws IOException {
        if (!file.exists()) {
            file.createNewFile();
        }
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(stocks);
        oos.close();
    }

    /**
     * Displays a table of stocks including their serial number, name, and current price.
     */
    static void displayStockTable() {
        Object[][] ob = new Object[10][];
        for (int i = 0; i < 10; i++) {
            ob[i] = new Object[]{i + 1, stocks[i].name, decimalFormat.format(stocks[i].currentPrice)};
        }
        UI.CustomTabularDisplay.printTable(new String[]{"Sr.No", "StockName", "Price"}, ob, 14);
    }
}
