package StockManagementSystem;

import java.io.IOException;
import java.sql.SQLException;

public class Runner extends Pages{
    public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException, InterruptedException {
        try{
            StockLoader();
            InitialPages.JDBCConnectionCode();
            InitialPages.welcomePage();
            StockSaver();

        }catch(Exception e){
            System.out.print(UserInterface.TEXT_RED);
            System.out.println("\n____ Application closed Unexpectedly ____");
            System.out.print(UserInterface.TEXT_RESET);
            e.printStackTrace();
        }

    }
}

