package StockManagementSystem.UI;

public interface TextColors {

    String TEXT_RESET = "\u001B[0m";
    String TEXT_BLUE = "\u001B[34m";
    String TEXT_GREEN = "\u001B[32m";
    String TEXT_RED = "\u001B[31m";
    String TEXT_YELLOW = "\u001B[33m";
    String FONT = "\u001B[1;3m";

    void printMsgWithProgressBar(String sMsg);

}

