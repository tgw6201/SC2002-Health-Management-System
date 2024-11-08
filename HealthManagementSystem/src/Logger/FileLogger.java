package Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

/**
 * The FileLogger class implements the Logger interface and provides functionality for logging
 * messages to a file. Each log message is written with a timestamp in the format "yyyy/MM/dd HH:mm:ss".
 * <p>
 * The log messages are appended to a log file. If the specified log file doesn't exist, it will be created.
 * The log directory is located at `src/Logger/logs/` within the project directory.
 * </p>
 * <p>
 * Example usage:
 * <pre>
 *     FileLogger logger = new FileLogger("logfile");
 *     logger.log("This is a log message.");
 *     logger.stopLogging();
 * </pre>
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class FileLogger implements Logger {
    private BufferedWriter writer;
    private String fileName;
    private String dirName;

    /**
     * Starts a new FileLogger session that logs to the specified file.
     * <p>
     * The file will be created inside the `src/Logger/logs/` directory. If the directory doesn't exist,
     * it will be created automatically.
     * </p>
     * 
     * @param fileName The name of the log file (without extension).
     *                 The extension `.txt` will be added automatically.
     */

    public FileLogger(String fileName) {
        this.fileName = fileName + ".txt";
        try {
            Path currPath = Paths.get("").toAbsolutePath();
            System.out.println("Current Path: " + currPath.toString());
            this.dirName = currPath.toString() + "\\HealthManagementSystem\\src\\Logger\\logs\\"; // Path to the logs directory
            File logDir = new File(dirName);
            if (!logDir.exists()) {
                logDir.mkdirs(); // Create the logs directory if it doesn't exist
            }
            File logFile = new File(logDir, this.fileName); 
            writer = new BufferedWriter(new FileWriter(logFile, true));
            System.out.println("Logging to file: " + logFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Stops logging and closes the log file writer.
     * <p>
     * Once logging is stopped, no further log messages can be written until a new instance of FileLogger is created.
     * </p>
     */
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

    /**
     * Logs a message to the log file, appending the current timestamp to the message.
     * 
     * @param message The message to log. 
     *                The timestamp will be appended to this message in the log file.
     * @throws IllegalStateException if the logger is not started.
     */
    public void log(String message) {
        try {
            if (writer == null) {
                throw new IllegalStateException("Logger is not started");
            }
            String currentTime = getCurrentTime();
            writer.write(message + " @:" + currentTime);
            writer.newLine(); 
            writer.flush(); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets the current time formatted as "yyyy/MM/dd HH:mm:ss".
     * 
     * @return A string representing the current date and time in the specified format.
     */
    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(java.time.LocalDateTime.now());
    }
}
