package StockManagementSystem.Pages;

import StockManagementSystem.AppLog.Logger;
import StockManagementSystem.UI.UI;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The {@code OnboardingPage} class handles user onboarding processes including
 * registration, login, and terms acceptance for the stock management system.
 * It provides methods for user interactions such as displaying the welcome page,
 * redirecting users to registration or login, and handling database connections.
 */
public class OnboardingPage extends MenuPageOptions {

    public static Connection con;
    static String passwordForLater;

    /**
     * Displays the welcome page with terms and conditions and handles user acceptance or decline.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws IOException if an I/O error occurs
     * @throws ParseException if there is an error parsing dates
     */
    public static void welcomePage() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        UI.printTOP(UI.TEXT_BLUE);

        System.out.println(" ".repeat(4) + "Terms & Conditions\n" + " ".repeat(4) + "_".repeat(19));
        System.out.print(UI.TEXT_GREEN);
        System.out.println(UI.TEXT_RED + "-> He/she must be 18 years of age or older." + UI.TEXT_GREEN);
        System.out.println("-> Use the platform in accordance with any applicable laws, regulations, or generally accepted practices or guidelines.");
        System.out.println("-> Shall not attempt to gain unauthorized access to any feature on the platform.");
        System.out.println("-> Shall not violate the Terms and Conditions contained herein or elsewhere.\n");
        System.out.println(UI.TEXT_RESET);

        System.out.println("Press 1 to Accept");
        System.out.println("Press 2 to Decline");
        System.out.print("Please Enter Your Choice : ");
        int choice;

        try {
            choice = sc.nextInt();
        } catch (Exception exception) {
            System.out.println("Please Enter a Valid Choice\n");
            welcomePage();
            return; // Ensures the method exits after recursive call
        }

        if (choice == 1) {
            new UI().printMsgWithProgressBar("");
            RedirectionPage();
        } else if (choice == 2) {
            System.out.println(UI.TEXT_GREEN + "Thank You for using zNWatch!" + UI.TEXT_RESET);
        } else {
            System.out.println(UI.TEXT_RED + "Please Enter a Valid Choice" + UI.TEXT_RESET);
            welcomePage();
        }
    }

    /**
     * Displays the redirection page where users can choose to register or log in.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws IOException if an I/O error occurs
     * @throws ParseException if there is an error parsing dates
     */
    public static void RedirectionPage() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPress 1 : To Register");
        System.out.println("Press 2 : To Login");
        System.out.print("Enter Your Choice : ");

        int choice;

        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Choice !!");
            RedirectionPage();
            return; // Ensures the method exits after recursive call
        }

        if (choice == 1) {
            new Logger().makeLog("Registering new user");
            registerPane();
        } else if (choice == 2) {
            loginPane();
        } else {
            System.out.println("Please enter a valid choice!");
            RedirectionPage();
        }
    }

    /**
     * Checks if the provided phone number is valid based on length.
     *
     * @param phoneNum the phone number to check
     * @return {@code true} if the phone number is valid (10 digits), {@code false} otherwise
     */
    private static boolean checkPhoneNumber(long phoneNum) {
        String s = "" + phoneNum;
        return s.length() == 10;
    }

    /**
     * Establishes a connection to the MySQL database.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     */
    public static void JDBCConnectionCode() throws SQLException, ClassNotFoundException {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/StockManagement";
        String dbuser = "root";
        String dbpass = "";

        Class.forName(driverName);
        con = DriverManager.getConnection(dburl, dbuser, dbpass);
    }

    /**
     * Handles user registration by collecting user details and saving them to the database.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     * @throws IOException if an I/O error occurs
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws ParseException if there is an error parsing dates
     */
    public static void registerPane() throws SQLException, ClassNotFoundException, IOException, InterruptedException, ParseException {
        // Verify JDBC Connection
        if (con == null) {
            JDBCConnectionCode();
        }

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPlease Enter Your Details : ");
        System.out.print("Enter Your Full Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Date of Birth (dd/mm/yyyy) Format : ");
        String datestamp = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(datestamp, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid Date !!");
            registerPane();
            return; // Ensures the method exits after recursive call
        }

        long phoneNum;
        do {
            System.out.print("Enter Your Phone Number : ");
            phoneNum = sc.nextLong();
        } while (!checkPhoneNumber(phoneNum));

        System.out.print("Enter Your eMail : ");
        String eMail = sc.next();

        System.out.print("Enter Your PAN Number : ");
        String panNum = sc.next();

        System.out.print("Please Enter Password : ");
        String pass = sc.next();

        String username = name.split(" ")[0] + datestamp.split("/")[0];

        System.out.println("\n-> Please Remember Your Details !");
        System.out.println("Your Generated Username is " + username);
        System.out.println("And Your Entered Password is " + pass + " \n ");

        System.out.println("Dear " + name.split(" ")[0] + ", Your account has been successfully created!");

        // Add customer to database
        String SQLCode = "INSERT INTO UserInfo VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = con.prepareStatement(SQLCode);
        statement.setString(1, name);
        statement.setString(2, datestamp);
        statement.setLong(3, phoneNum);
        statement.setString(4, eMail);
        statement.setString(5, panNum);
        statement.setString(6, username);
        statement.setString(7, pass);
        statement.setDouble(8, 0);

        int i = statement.executeUpdate();
        passwordForLater = pass;
        menu(username);
    }

    /**
     * Handles user login by validating the entered username and password.
     *
     * @throws SQLException if a database access error occurs
     * @throws ClassNotFoundException if the JDBC driver class is not found
     * @throws InterruptedException if the thread is interrupted while sleeping
     * @throws IOException if an I/O error occurs
     * @throws ParseException if there is an error parsing dates
     */
    public static void loginPane() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        // Verify JDBC Connection
        if (con == null) {
            JDBCConnectionCode();
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease Enter Your Username : ");
        String usrName = sc.next();
        System.out.print("Please Enter Your Password : ");
        String pass = sc.next();

        String SQLCommand = "SELECT Password FROM UserInfo WHERE Username = '" + usrName + "'";
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery(SQLCommand);

        boolean toAllow = true;
        String pswd = null;

        try {
            resultSet.next();
            pswd = resultSet.getString(1);
        } catch (SQLException exception) {
            toAllow = false;
        }

        if (toAllow) {
            if (pswd.equals(pass)) {
                passwordForLater = pass;
                new Logger().makeLog("Login as " + usrName);
                menu(usrName);
            } else {
                System.out.println("\nWrong Password! Redirecting to Login Page Again...");
                Thread.sleep(2000);
                loginPane();
            }
        } else {
            System.out.println("\nAccount Not Found! Please Create One First.");
            System.out.println("Redirecting to Registration...");
            Thread.sleep(2000);
            registerPane();
        }
    }
}
