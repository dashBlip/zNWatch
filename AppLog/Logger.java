package StockManagementSystem.AppLog;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {
    public void makeLog(String log) throws IOException {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDateTime = now.format(formatter);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("zNWatch/AppLog/log.txt", true))) {
            writer.write(formattedDateTime+" : "+log);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
