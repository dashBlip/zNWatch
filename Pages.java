package StockManagementSystem;

import java.io.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import static StockManagementSystem.InitialPages.con;
import static StockManagementSystem.InitialPages.loginPage;


public class Pages {
    static File file = new File("StockData.txt");

    static Stock[] stocks = new Stock[10];
    public static ResultSet resultSet;
    public static Statement statement;
    public static String userName;
    public static DecimalFormat decimalFormat = new DecimalFormat("0.00");

    public static void menu(String username) throws IOException, SQLException, ClassNotFoundException, InterruptedException, ParseException {
        userName = username;
        statement = con.createStatement();
        resultSet = statement.executeQuery("SELECT FullName FROM UserInfo WHERE Username = '"+username+"'");
        resultSet.next();
        System.out.println("\nWelcome To Dashboard "+resultSet.getString(1).split(" ")[0] + "\n");

        Scanner sc = new Scanner(System.in);
        int choice = 0;
        do{
            System.out.println("-------- Main Menu -------");
            System.out.println("Press 1 : Display Stock");
            System.out.println("Press 2 : Holdings");
            System.out.println("Press 3 : Settings");
            System.out.println("Press 4 : Balance");
            System.out.println("Press 5 : Display Portfolio");
            System.out.println("Press 6 : Exit");
            System.out.print("Please Enter Your Choice : ");

            try{
                choice = sc.nextInt();
            }catch(Exception e){
                System.out.println("Enter Valid Choice !");
                menu(username);
            }


            switch (choice){
                case 1 -> {
                    int choice2;
                    do{
                        System.out.println("\nPress 1 : Buy");
                        System.out.println("Press 2 : Sell");
                        System.out.print("Please Enter Your Choice : ");
                        choice2 = sc.nextInt();

                        System.out.println("-------- Stocks -------");
                        if(choice2 == 1){
                            displayStock();
                        }else if(choice2 == 2){
                            int tmpCounter = holdingsPage();
                            sellPane(tmpCounter);
                        }
                    }while(choice2 != 1 && choice2 != 2);
                }
                case 2 -> {
                    holdingsPage();
                    Thread.sleep(1000);
                }
                case 3 -> {
                    settings();
                }
                case 4 -> {
                    BalancePane();
                }
                case 5 -> {
                    PortfolioManager.portfolio();
                    Thread.sleep(2000);
                }
                default -> {}
            }
        }while(choice != 6);

    }
    public static void BalancePane() throws SQLException, IOException, ParseException, ClassNotFoundException, InterruptedException {

        resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '"+userName+"'");
        resultSet.next();

        System.out.println("\n--> Your Current Balance : "+resultSet.getDouble(1)+" rs\n");

        System.out.println("Press 1 : Add Balance");
        System.out.println("Press 2 : Debit Balance");
        System.out.println("Press 3 : Return");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();

        if(choice == 1){
            addBalance();
        } else if (choice == 2) {
            removeBalance();
        } else if (choice == 3) {
            System.out.println();
        }else {
            System.out.println("Invalid Choice !!\n");
            BalancePane();
        }
    }
    public static void settings() throws SQLException, IOException, ClassNotFoundException, InterruptedException, ParseException {

        Scanner sc = new Scanner(System.in);

        System.out.println("\n------- Settings -------");
        System.out.println("Press 1 : delete Account");
        System.out.println("Press 2 : Edit Password");
        System.out.println("Press 3 : Edit eMail");
        System.out.println("Press 4 : Go Back");
        System.out.print("Please Enter Your Choice : ");

        int choice = 0;
        try {
            choice = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Enter Valid Choice !!");
            settings();
        }


        switch (choice) {
            case 1 -> {
                System.out.println("Please Enter Password For Confirmation : ");
                String pass = sc.next();
                if (pass.equals(InitialPages.passwordForLater)) {
                    statement.execute("DELETE FROM UserInfo WHERE Username = '" + userName + "'");
                    loginPage();
                } else {
                    System.out.println("Invalid Password !! ");
                }
            }

            case 2 -> {
                System.out.println("Please Enter Password For Confirmation : ");
                String pass = sc.next();

                if (pass.equals(InitialPages.passwordForLater)) {

                    System.out.print("Enter New Password : ");
                    String newPass = sc.next();
                    statement.execute("UPDATE UserInfo SET Password = '" + newPass + "' WHERE Username = '" + userName + "'");
                    System.out.println("Password Updated Successfully !!\n");
                    InitialPages.passwordForLater = newPass;

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
            case 4 -> {
                System.out.println();
            }
            default -> {
                System.out.println("Invalid Choice !!");
                settings();
            }
        }
    }

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
            System.out.println("Balance Added SuccessFully\n");

        }catch(InputMismatchException e){
            System.out.println("Please Enter a Valid Amount !!");
            addBalance();
        }
    }

    public static void removeBalance() throws SQLException {

        Scanner sc = new Scanner(System.in);

        resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '"+userName+"'");
        resultSet.next();
        double previousBalance = resultSet.getDouble(1);

        System.out.print("Enter Amount To Remove : ");

        try{

            double balanceToRemove = sc.nextDouble();

            if(balanceToRemove > previousBalance){
                System.out.println("Not Enough Balance !");
            }else{
                statement.execute("UPDATE UserInfo SET Balance = " + (previousBalance - balanceToRemove) +" WHERE Username = '"+userName+"'");
                System.out.println("Balance Debited SuccessFully");
            }

        }catch(InputMismatchException e){
            System.out.println("Please Enter a Valid Amount !!");
            removeBalance();
        }
    }


    public static Stock stockFinder(String stockName){
        for (Stock stock : stocks) {
            if (stock.name.equals(stockName)) {
                return stock;
            }
        }
        return new Stock("null",0);
    }

    public static int holdingsPage() throws SQLException {
        System.out.println("------- Your Holdings -------");

        resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '"+userName+"'"+" AND Quantity != 0");

        int counter = 1;
        while(resultSet.next()){
            System.out.println((counter++) + " -> Stock Name :"+ resultSet.getString(2)
                    + "\n     Current Price : "
                    + decimalFormat.format(Objects.requireNonNull(stockFinder(resultSet.getString(2))).currentPrice)
                    + "\n     Purchased at : " + decimalFormat.format(resultSet.getDouble(3))
                    + "\n     Quantity Available : " + resultSet.getInt(4)
                    + "\n     Date of Purchase : "+ resultSet.getString(5)+"\n"
            );
        }
        return counter - 1;
    }

    public static void sellPane(int counter) throws SQLException {

        System.out.print("Enter The Stock Number You Want To Sell : ");
        Scanner sc = new Scanner(System.in);

        try{

            int choice = sc.nextInt();

            if(choice > counter){

                System.out.println("Invalid Choice !! \n");
                sellPane(counter);

            }else{

                System.out.print("\nEnter Quantity : ");
                int quantity = sc.nextInt();

                Statement statement1 = con.createStatement();
                ResultSet resultSet1 = statement1.executeQuery("SELECT * FROM StockInfo WHERE Username = '"+userName+"' AND Quantity != 0");
                while(choice-- > 0){
                    resultSet1.next();
                }
                String stockName = stockFinder(resultSet1.getString(2)).name;
                double sellPrice = stockFinder(resultSet1.getString(2)).currentPrice;

                if(quantity > resultSet1.getInt(4)){
                    System.out.println("Entered Value is More Than Stocks You Hold ...");
                    holdingsPage();
                    sellPane(counter);
                }else{
                    int nowQuantity = resultSet1.getInt(4) - quantity;
                    resultSet = statement.executeQuery("SELECT * FROM StockInfo WHERE Username = '"+userName+
                            "' AND StockName = '"+resultSet1.getString(2)+"'"
                    );

                    statement.execute("UPDATE StockInfo SET Quantity = "+nowQuantity+" WHERE Username = '"+userName+
                            "' AND StockName = '"+resultSet1.getString(2)+"'"
                    );

                    resultSet = statement.executeQuery("SELECT Balance FROM UserInfo WHERE Username = '"+userName+"'");
                    resultSet.next();
                    double nowBalance = resultSet.getDouble(1) + (sellPrice * quantity);

                    statement.execute("UPDATE UserInfo SET Balance = "+nowBalance+" WHERE Username = '"+userName+"'");

                    System.out.println("Stock Name : "+stockName+" And Amount : "+sellPrice);
                    System.out.println("Amount Credited And Stock Sold !\n");
                }
            }

        }catch(InputMismatchException e){
            System.out.println("Please Enter A valid Input !!");
            sellPane(counter);
        }

    }

    public static void StockLoader() throws IOException, ClassNotFoundException {
        if(file.exists()){
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            stocks = (Stock[]) ois.readObject();
            ois.close();
        }else{

            stocks[0] = new Stock("JioFinance",340);
            stocks[1] = new Stock("MTNL",73);
            stocks[2] = new Stock("HDFC Bank",35.25);
            stocks[3] = new Stock("Suzlon",55);
            stocks[4] = new Stock("Reliance",600);
            stocks[5] = new Stock("HAL",1000);
            stocks[6] = new Stock("TCS",95);
            stocks[7] = new Stock("Wipro",250);
            stocks[8] = new Stock("BDL",3000);
            stocks[9] = new Stock("Yes Bank",10000);
        }
    }
    public static void StockSaver() throws IOException {
        if(!file.exists()){
            file.createNewFile();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(stocks);
            oos.close();
        }else{
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(stocks);
            oos.close();
        }
    }
    public static void displayStock() throws SQLException {

        Scanner sc = new Scanner(System.in);


        for (int i = 0; i < 10; i++) {
            System.out.print(i+1+" : "+stocks[i]+"\n\n");
        }

        System.out.println("\nEnter The Stock Number You Want To Buy : ");
        int choice2 = sc.nextInt();

        if(choice2 <= 10 && choice2 > 0){
            double amountDebitStock = stocks[choice2 - 1].currentPrice;

            System.out.println("Now Enter The Quantity of Stocks To Buy :");
            int qty = sc.nextInt();

            double amtToDeduce = qty * amountDebitStock;

            System.out.println("\nTotal Amount is : "+(amtToDeduce));

            String SQLCommand = "Select Balance FROM UserInfo WHERE Username = '"+userName+"'\n\n";
            resultSet = statement.executeQuery(SQLCommand);
            resultSet.next();
            double initialAmount = resultSet.getDouble(1);
            double deducedAmount = initialAmount - amtToDeduce;

            if (deducedAmount < 0){
                System.out.println("Not Enough Balance !\n");
            }else {
                statement.execute("UPDATE UserInfo SET Balance = "+deducedAmount+" WHERE Username = '"+userName+"'");


                String SQLCommand2 = "INSERT INTO StockInfo VALUES ('"+userName+"' , '"+stocks[choice2 - 1].name+"', "+amtToDeduce
                        + " , "+qty+" , '"+(LocalDate.now())+"')";

                statement.execute(SQLCommand2);
                System.out.println("Transaction Done SuccessFully "+userName);
            }
        }else{
            System.out.println("Invalid Choice !!\n");
            displayStock();
        }


    }
}
