package StockManagementSystem.UI;

public class UI implements TextColors {

    static int width = 50;  // Adjust as needed
    static String title = "WELCOME TO zNWatch";
    static int padding = (width - title.length()) / 2;
    static String spaces = " ".repeat(padding);


    public static void printTOP(String s) {

        System.out.println(s + TOP_LEFT + HORIZONTAL.repeat(width) + TOP_RIGHT + TEXT_RESET);
        System.out.println(s + VERTICAL + spaces + TEXT_YELLOW + title + s + spaces + VERTICAL + TEXT_RESET);
        System.out.println(s + BOTTOM_LEFT + HORIZONTAL.repeat(width) + BOTTOM_RIGHT + TEXT_RESET);
        System.out.println();

    }

    public static void printBOTTOM(String s){
        System.out.println();
        System.out.println(s + BOTTOM_LEFT + HORIZONTAL.repeat(width) + BOTTOM_RIGHT + TEXT_RESET);
    }

    public static class CustomTabularDisplay {

        public static void printTable(String[] headers, Object[][] data, int columnWidth) {
            int numberOfColumns = headers.length;

            printSeparator(numberOfColumns, columnWidth);
            printHeader(headers, columnWidth);
            printSeparator(numberOfColumns, columnWidth);

            for (Object[] row : data) {
                printDataRow(row, columnWidth);
                printSeparator(numberOfColumns, columnWidth);
            }
        }

        private static void printHeader(String[] headers, int columnWidth) {
            System.out.print("|");
            for (String header : headers) {
                System.out.print(UI.TEXT_RED);
                System.out.print(UI.FONT);
                System.out.printf("%-" + (columnWidth - 1) + "s", header);
                System.out.print(UI.TEXT_RESET);
                System.out.print("|");
            }
            System.out.println();
        }

        private static void printSeparator(int numberOfColumns, int columnWidth) {
            System.out.print("+");
            for (int i = 0; i < numberOfColumns; i++) {
                for (int j = 0; j < columnWidth; j++) {
                    System.out.print("-");
                }
                System.out.print("+");
            }
            System.out.println();
        }

        private static void printDataRow(Object[] row, int columnWidth) {
            System.out.print("|");
            boolean counter = true;
            for (Object cell : row) {
                if(cell == null){
                    cell = "-";
                }
                if (counter){
                    System.out.print(UI.TEXT_RED);
                    System.out.print(UI.FONT);
                }else {
                    System.out.print(UI.TEXT_BLUE);
                }
                System.out.printf("%-" + (columnWidth - 1) + "s",cell.toString());
                System.out.print(UI.TEXT_RESET);
                System.out.print("|");
                counter = false;
            }
            System.out.println();
        }
    }



    public void printMsgWithProgressBar(String sMsg){
        int totalTasks = 100;
        int currentTask = 0;
        int progressBarWidth = 41;

        System.out.println(sMsg);

        StringBuffer progressStringBuffer = new StringBuffer();
        progressStringBuffer.append(" ".repeat(progressBarWidth));

        while (currentTask <= totalTasks) {
            int progress = (int) (currentTask / (float) totalTasks * progressBarWidth);
            StringBuffer progressBarBuffer = new StringBuffer(progressBarWidth + 10);

            progressBarBuffer.append('[');

            for (int i = 0; i < progressBarWidth; i++) {
                if (i < progress) {
                    progressBarBuffer.append('=');
                } else if (i == progress) {
                    progressBarBuffer.append('>');
                } else {
                    progressBarBuffer.append(' ');
                }
            }

            progressBarBuffer.append("] ").append(progress * 100 / progressBarWidth).append('%');
            String progressBarWithBuffer = progressBarBuffer.toString();
            System.out.print(UI.TEXT_YELLOW);
            System.out.print("\r" + progressBarWithBuffer);
            System.out.print(UI.TEXT_RESET);

            try {
                Thread.sleep(15);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentTask++;
        }
    }
}

