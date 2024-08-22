package StockManagementSystem;


import StockManagementSystem.Pages.InitialPages;
import StockManagementSystem.Pages.Pages;
import StockManagementSystem.UI.TextColors;

public class Runner extends Pages {
    public static void main(String[] args) {
        try{
            StockLoader();
            InitialPages.JDBCConnectionCode();
            InitialPages.welcomePage();
            StockSaver();

        }catch(Exception e){
            System.out.print(TextColors.TEXT_RED);
            System.out.println("\n____ Application closed Unexpectedly ____");
            System.out.print(TextColors.TEXT_RESET);
            e.printStackTrace();
        }

    }
}

