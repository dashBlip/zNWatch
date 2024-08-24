package StockManagementSystem.UI;

/**
 * The {@code TextColors} interface defines a set of ANSI escape codes for text styling and color formatting,
 * as well as methods for displaying messages with a progress bar.
 * <p>
 * This interface is used to standardize text color and style throughout the UI of the Stock Management System.
 * </p>
 */
public interface TextColors {

    /** ANSI escape code to reset text formatting to default. */
    String TEXT_RESET = "\u001B[0m";

    /** ANSI escape code for blue text color. */
    String TEXT_BLUE = "\u001B[34m";

    /** ANSI escape code for green text color. */
    String TEXT_GREEN = "\u001B[32m";

    /** ANSI escape code for red text color. */
    String TEXT_RED = "\u001B[31m";

    /** ANSI escape code for yellow text color. */
    String TEXT_YELLOW = "\u001B[33m";

    /** ANSI escape code for bold and italic text. */
    String FONT = "\u001B[1;3m";

    /** Symbol for horizontal lines. */
    String HORIZONTAL = "━";

    /** Symbol for vertical lines. */
    String VERTICAL = "┃";

    /** Symbol for the top-left corner of a box. */
    String TOP_LEFT = "┏";

    /** Symbol for the top-right corner of a box. */
    String TOP_RIGHT = "┓";

    /** Symbol for the bottom-left corner of a box. */
    String BOTTOM_LEFT = "┗";

    /** Symbol for the bottom-right corner of a box. */
    String BOTTOM_RIGHT = "┛";

    /** Symbol for the intersection of lines. */
    String CROSS = "┻";

    /**
     * Displays a message with a progress bar.
     *
     * @param sMsg the message to be displayed alongside the progress bar
     */
    void printMsgWithProgressBar(String sMsg);
}
