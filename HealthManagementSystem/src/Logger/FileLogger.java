package Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileLogger implements Logger {
    private BufferedWriter writer;
    private String fileName;
    private String dirName;

    public FileLogger(String fileName) {
        this.fileName = fileName;
        try {
            Path currPath = Paths.get("").toAbsolutePath();
            this.dirName = currPath.toString() + "../src/Logger/logs/"; // Path to the logs directory
            File logDir = new File(dirName);
            if (!logDir.exists()) {
                logDir.mkdirs(); // Create the logs directory if it doesn't exist
            }
            File logFile = new File(logDir, fileName); 
            writer = new BufferedWriter(new FileWriter(logFile, true));
            System.out.println("Logging to file: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopLogging() {
        try {
            if (writer != null) {
                writer.close(); 
                System.out.println("Logging stopped.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer = null; 
        }
    }

    public void log(String message) {
        try {
            if (writer == null) {
                throw new IllegalStateException("Logger is not started");
            }
            writer.write(message);
            writer.newLine(); 
            writer.flush(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
