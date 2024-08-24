package StockManagementSystem.AppLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The Logger class is responsible for writing log messages to a log file.
 * Each log entry is timestamped with the current date and time.
 */
public class Logger {

    /**
     * Writes a log message to the log file with a timestamp.
     * The log entry is appended to the file if it already exists.
     *
     * @param log the log message to be written to the file
     * @throws IOException if an I/O error occurs while writing to the file
     */
    public void makeLog(String log) throws IOException {
        // Get the current date and time
        LocalDateTime now = LocalDateTime.now();

        // Define the format for the date and time
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Format the current date and time
        String formattedDateTime = now.format(formatter);

        // Open the log file in append mode
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("zNWatch/AppLog/log.txt", true))) {
            // Write the timestamp and log message to the file
            writer.write(formattedDateTime + " : " + log);
            // Insert a new line after the log entry
            writer.newLine();
        } catch (IOException e) {
            // Print the stack trace if an error occurs
            e.printStackTrace();
        }
    }
}
