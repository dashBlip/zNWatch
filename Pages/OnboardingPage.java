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


public class OnboardingPage extends MenuPageOptions {
    public static Connection con ;
    static String passwordForLater;
    public static void welcomePage() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        UI.printTOP(UI.TEXT_BLUE);

        System.out.println(" ".repeat(4)+"Terms & Conditions\n"+" ".repeat(4)+"_".repeat(19));
        System.out.print(UI.TEXT_GREEN);
        System.out.println(UI.TEXT_RED+"-> he/she must be 18 years of age or older."+UI.TEXT_GREEN);
        System.out.println("-> Use the Platform in accordance with any Applicable Laws," +
                " regulations or generally accepted practices or guidelines");
        System.out.println("-> Shall not attempt to gain unauthorized access to any feature on the Platform.");
        System.out.println("-> Shall not violate the Terms and Conditions contained herein or elsewhere.\n");
        System.out.println(UI.TEXT_RESET);

        System.out.println("Press 1 to Accept");
        System.out.println("Press 2 to Decline");
        System.out.print("Please Enter Your Choice : ");
        int choice = 0;

        try{
            choice = sc.nextInt();
        }catch (Exception exception){
            System.out.println("Please Enter Valid choice\n");
            welcomePage();
        }


        if(choice == 1){
            new UI().printMsgWithProgressBar("");
            RedirectionPage();
        }else if(choice == 2){
            System.out.println(UI.TEXT_GREEN+"Thank You for using zNWatch !"+UI.TEXT_RESET);
        }else{
            System.out.println(UI.TEXT_RED+"Please Enter Valid Choice"+UI.TEXT_RESET);
            welcomePage();
        }

    }



    public static void RedirectionPage() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        System.out.println("\nPress 1 : To Register");
        System.out.println("Press 2 : To Login");
        System.out.print("Enter Your Choice : ");

        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid Choice !!");
            RedirectionPage();
        }


        if (choice == 1) {
            new Logger().makeLog("Registering new user");
            registerPane();
        } else if (choice == 2) {
            loginPane();
        } else {
            System.out.println("Please enter valid choice !");
            RedirectionPage();
        }
    }

    private static boolean checkPhoneNumber(long phoneNum){
        String s = "" + phoneNum;
        return s.length() == 10 ;
    }
    public static void JDBCConnectionCode() throws SQLException, ClassNotFoundException {
        String driverName = "com.mysql.cj.jdbc.Driver";
        String dburl = "jdbc:mysql://localhost:3306/StockManagement";
        String dbuser = "root";
        String dbpass = "";

        Class.forName(driverName);
        con = DriverManager.getConnection(dburl,dbuser,dbpass);
    }

    public static void registerPane() throws SQLException, ClassNotFoundException, IOException, InterruptedException, ParseException {
        //---------------------------- JDBC Connection Verification -------------//
        if(con == null){
            JDBCConnectionCode();
        }
        //------------------------------------ main code -------------------------//

        Scanner sc = new Scanner(System.in);

        System.out.println("\nPlease Enter Your Details : ");
        System.out.print("Enter Your Full Name : ");
        String name = sc.nextLine();

        System.out.print("Enter Date of Birth (dd/mm/yyyy) Format : ");

        String datestamp = sc.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{
            LocalDate date = LocalDate.parse(datestamp, formatter);
        }catch (DateTimeParseException e){
            System.out.println("Invalid Date !!");
            registerPane();
        }


        long phoneNum;
        do {
            System.out.print("Enter Your Phone number : ");
            phoneNum = sc.nextLong();
        }while(!checkPhoneNumber(phoneNum));

        System.out.print("Enter Your eMail : ");
        String eMail = sc.next();

        System.out.print("Enter Your PanNumber : ");
        String panNum = sc.next();

        System.out.print("Please Enter Password : ");
        String pass = sc.next();

        String username = name.split(" ")[0] + datestamp.split("/")[0];

        System.out.println("\n-> Please Remember Your Details !");
        System.out.println("Your Generated Username is " + username);
        System.out.println("and Your Entered Password is " + pass+ " \n ");

        System.out.println("Dear "+name.split(" ")[0]+" Your account has been successfully created !");



        //----------------- Adding customer To Database -------------------//

        String SQLCode = "INSERT INTO UserInfo values (?,?,?,?,?,?,?,?)";

        PreparedStatement statement = con.prepareStatement(SQLCode);
        statement.setString(1,name);
        statement.setString(2,datestamp);
        statement.setLong(3,phoneNum);
        statement.setString(4,eMail);
        statement.setString(5,panNum);
        statement.setString(6,username);
        statement.setString(7,pass);
        statement.setDouble(8,0);

        int i = statement.executeUpdate();
        passwordForLater = pass;
        menu(username);

    }

    public static void loginPane() throws SQLException, ClassNotFoundException, InterruptedException, IOException, ParseException {
        //------------------- JDBC Connection Verification ------------------//
        if(con == null){
            JDBCConnectionCode();
        }

        //--------------------- Login Pane Code ---------------------------//

        Scanner sc = new Scanner(System.in);
        System.out.print("\nPlease Enter Your Username : ");
        String usrName = sc.next();
        System.out.print("Please Enter Your Password : ");
        String pass = sc.next();

        String SQLCommand = "SELECT Password FROM UserInfo WHERE Username = '"+usrName+"'";
        Statement statement = con.prepareStatement(SQLCommand);

        ResultSet resultSet = statement.executeQuery(SQLCommand);
        boolean toAllow = true;

        resultSet.next();

        String pswd = null;
        try{
            pswd = resultSet.getString(1);
        }catch (SQLException exception){
            toAllow = false;
        }

        if(toAllow){
            if(pswd.equals(pass)){
                passwordForLater = pass;
                new Logger().makeLog("Login as "+usrName);
                menu(usrName);
            }else{
                System.out.println("\nWrong Password ! Redirecting to Login Page Again ...");
                Thread.sleep(2000);
                loginPane();
            }

        }else{
            System.out.println("\nAccount Not Found ! Please Create One First ");
            System.out.println("Redirecting to Registration ...");
            Thread.sleep(2000);
            registerPane();
        }
    }

}
