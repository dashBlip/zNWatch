package StockManagementSystem.UI;

/**
 * UI provides utility methods for formatting and displaying text in a console-based user interface.
 * It implements the TextColors interface for text color and style formatting.
 */
public class UI implements TextColors {

    static int width = 50;  // Width of the box for displaying text
    static String title = "WELCOME TO zNWatch";  // Title to be displayed
    static int padding = (width - title.length()) / 2;  // Padding to center the title
    static String spaces = " ".repeat(padding);  // Spaces used for padding

    /**
     * Prints the top border of a styled box with the title centered.
     *
     * @param s String to prepend to each line for additional styling
     */
    public static void printTOP(String s) {
        System.out.println(s + TOP_LEFT + HORIZONTAL.repeat(width) + TOP_RIGHT + TEXT_RESET);
        System.out.println(s + VERTICAL + spaces + TEXT_YELLOW + title + s + spaces + VERTICAL + TEXT_RESET);
        System.out.println(s + BOTTOM_LEFT + HORIZONTAL.repeat(width) + BOTTOM_RIGHT + TEXT_RESET);
        System.out.println();
    }

    /**
     * Prints the bottom border of a styled box.
     *
     * @param s String to prepend to each line for additional styling
     */
    public static void printBOTTOM(String s) {
        System.out.println();
        System.out.println(s + BOTTOM_LEFT + HORIZONTAL.repeat(width) + BOTTOM_RIGHT + TEXT_RESET);
    }

    /**
     * Provides utilities for displaying tabular data in a styled table format.
     */
    public static class CustomTabularDisplay {

        /**
         * Prints a table with headers and data.
         *
         * @param headers      The headers of the table
         * @param data         The data rows of the table
         * @param columnWidth  The width of each column
         */
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

        /**
         * Prints the header row of the table.
         *
         * @param headers      The headers to print
         * @param columnWidth  The width of each column
         */
        private static void printHeader(String[] headers, int columnWidth) {
            System.out.print("|");
            for (String header : headers) {
                System.out.print(TEXT_RED + FONT);
                System.out.printf("%-" + (columnWidth - 1) + "s", header);
                System.out.print(TEXT_RESET + "|");
            }
            System.out.println();
        }

        /**
         * Prints a separator line between rows in the table.
         *
         * @param numberOfColumns  The number of columns in the table
         * @param columnWidth      The width of each column
         */
        private static void printSeparator(int numberOfColumns, int columnWidth) {
            System.out.print("+");
            for (int i = 0; i < numberOfColumns; i++) {
                System.out.print("-".repeat(columnWidth) + "+");
            }
            System.out.println();
        }

        /**
         * Prints a single row of data.
         *
         * @param row         The data for the row
         * @param columnWidth The width of each column
         */
        private static void printDataRow(Object[] row, int columnWidth) {
            System.out.print("|");
            boolean useAlternateColor = true;
            for (Object cell : row) {
                if (cell == null) {
                    cell = "-";
                }
                if (useAlternateColor) {
                    System.out.print(TEXT_RED);
                } else {
                    System.out.print(TEXT_BLUE);
                }
                System.out.printf("%-" + (columnWidth - 1) + "s", cell.toString());
                System.out.print(TEXT_RESET + "|");
                useAlternateColor = !useAlternateColor;
            }
            System.out.println();
        }
    }

    /**
     * Prints a progress bar with a message.
     *
     * @param sMsg The message to display alongside the progress bar
     */
    @Override
    public void printMsgWithProgressBar(String sMsg) {
        final int totalTasks = 100;  // Total tasks to complete
        final int progressBarWidth = 41;  // Width of the progress bar

        System.out.println(sMsg);

        for (int currentTask = 0; currentTask <= totalTasks; currentTask++) {
            int progress = (int) (currentTask / (float) totalTasks * progressBarWidth);
            String progressBar = String.format("[%s>%s] %d%%",
                    "=".repeat(progress), " ".repeat(progressBarWidth - progress - 1),
                    progress * 100 / progressBarWidth);

            System.out.print(TEXT_YELLOW + "\r" + progressBar + TEXT_RESET);

            try {
                Thread.sleep(15);  // Simulate work being done
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();  // Restore interrupted status
                System.err.println("Progress bar interrupted");
            }
        }
        System.out.println();  // Move to the next line after progress bar completion
    }
}
