package StockManagementSystem;

public interface UserInterface {

    String TEXT_RESET = "\u001B[0m";
    String TEXT_BLUE = "\u001B[34m";
    String TEXT_GREEN = "\u001B[32m";
    String TEXT_RED = "\u001B[31m";
    String TEXT_MAGNETA = "\u001B[35m";

    void printMsgWithProgressBar(String sMsg);

}

class UI implements UserInterface{

    public static class CustomTabularDisplay {

        public static void printTable(String[] headers, Object[][] data, int columnWidth) {
            int numberOfColumns = headers.length;

            // Print header
            printSeparator(numberOfColumns, columnWidth);
            printHeader(headers, columnWidth);
            printSeparator(numberOfColumns, columnWidth);

            // Print data rows
            for (Object[] row : data) {
                printDataRow(row, columnWidth);
                printSeparator(numberOfColumns, columnWidth); // Line between records
            }
        }

        private static void printHeader(String[] headers, int columnWidth) {
            System.out.print("|");
            for (String header : headers) {
                System.out.printf("%-" + (columnWidth - 1) + "s", header);
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
            for (Object cell : row) {
                if(cell == null){
                    cell = "-";
                }
                System.out.printf("%-" + (columnWidth - 1) + "s", cell.toString());
                System.out.print("|");
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

            System.out.print("\r" + progressBarWithBuffer);

            try {
                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
            currentTask++;
        }
    }
}
