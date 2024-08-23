package StockManagementSystem.Pages;

import StockManagementSystem.UI.UI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import static StockManagementSystem.Pages.MenuPageOptions.decimalFormat;
import static StockManagementSystem.Pages.OnboardingPage.con;


public class AdminMenuPage {
    private void displayView() throws SQLException {
        ResultSet resultSet = null;
        Statement statement = con.createStatement();

        resultSet = statement.executeQuery("SELECT COUNT(*) FROM AllDataView");
        resultSet.next();
        int rows = resultSet.getInt(1);

        resultSet = statement.executeQuery("SELECT * FROM AllDataView");

        Object[][] dataArray = new Object[rows][];
        int counter = 0;
        while (resultSet.next()){
            dataArray[counter++] = new Object[]{counter,resultSet.getString(1),resultSet.getString(2)
                    , resultSet.getString(3), resultSet.getLong(4), resultSet.getString(5)
                    , resultSet.getString(6), decimalFormat.format(resultSet.getDouble(7)), resultSet.getString(8)
                    , decimalFormat.format(resultSet.getDouble(9)), resultSet.getInt(10), resultSet.getString(11)
                    , resultSet.getString(12), resultSet.getString(13), resultSet.getString(14)
                    , resultSet.getString(15), resultSet.getString(16)
            };
        }
        String[] HEADERS = {"Sr.No","UsrName","Name","DOB","Phone","Email","PAN","Balance","Stock","SPrice","Quantity","StockPD"
                ,"OldPass", "NewPass", "OldEmail", "NewEmail", "DeletePass"};
        UI.CustomTabularDisplay.printTable(HEADERS, dataArray, 14);
        System.out.println("\n");
    }
    public void adminMenu() {
        UI.printTOP(UI.TEXT_BLUE);
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println(UI.TEXT_BLUE + "-------- Main Menu -------" + UI.TEXT_RESET);
            System.out.println("PRESS 1 : View All User Data");
            System.out.println(UI.TEXT_RED + "PRESS 2 : EXIT" + UI.TEXT_RESET);
            System.out.print("\nEnter Your Choice : ");

            try{
                choice = sc.nextInt();

                if (choice == 1){
                    displayView();
                }else{
                    System.out.println(UI.TEXT_RED + "Enter Valid choice !!" + UI.TEXT_RESET);
                }

            }catch (Exception e){
                System.out.println(UI.TEXT_RED + "Please Enter valid Value !!" + UI.TEXT_RESET);
                e.printStackTrace();
            }

        }while (choice != 2);
    }
}
