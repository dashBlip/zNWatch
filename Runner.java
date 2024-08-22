package StockManagementSystem;


import StockManagementSystem.AppLog.Logger;
import StockManagementSystem.Pages.OnboardingPage;
import StockManagementSystem.Pages.MenuPageOptions;
import StockManagementSystem.UI.TextColors;

import java.io.IOException;

public class Runner extends MenuPageOptions {
    public static void main(String[] args) throws IOException {
        try{
            new Logger().makeLog("Starting Application ...");
            StockLoader();
            OnboardingPage.JDBCConnectionCode();
            OnboardingPage.welcomePage();
            StockSaver();

        }catch(Exception e){
            System.out.print(TextColors.TEXT_RED);
            System.out.println("\n____ Application closed Unexpectedly ____");
            System.out.print(TextColors.TEXT_RESET);
            new Logger().makeLog("Some Error Occurred !");
            e.printStackTrace();
        }finally {
            new Logger().makeLog("Closing Application ...");
        }

    }
}

