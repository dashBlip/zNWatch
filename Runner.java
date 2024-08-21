package StockManagementSystem;


public class Runner extends Pages{
    public static void main(String[] args) {
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

