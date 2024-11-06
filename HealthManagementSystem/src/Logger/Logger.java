package Logger;

/**
 * The Logger interface defines the basic structure for logging messages to a destination.
 * Any class implementing this interface must provide the functionality to log messages
 * and stop the logging process.
 * <p>
 * This interface is intended to be used as a contract for different types of logging mechanisms,
 * such as logging to the console, to a file, or to a remote server.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     Logger logger = new ConsoleLogger();  // or FileLogger, etc.
 *     logger.log("This is a log message.");
 *     logger.stopLogging();
 * </pre>
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */
public interface Logger {
    /**
     * Logs a message to the specified destination.
     * 
     * @param message The message to be logged. The exact format and destination of the log
     *                depend on the implementation of the Logger.
     */
    void log(String message);
     /**
     * Stops the logging process and releases any resources associated with logging.
     * <p>
     * After calling this method, no further log messages can be logged unless the logging
     * process is started again.
     * </p>
     */
    void stopLogging();
}
