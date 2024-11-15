package Logger;

/**
 * Manages logging operations by delegating them to a specific {@link Logger} instance.
 * Provides functionality to log messages and stop logging.
 * 
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * Logger fileLogger = new FileLogger("MyLogs");
 * LoggingManager loggingManager = new LoggingManager(fileLogger);
 * loggingManager.log("This is a log message.");
 * loggingManager.stopLogging();
 * </pre>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class LoggingManager {
    private final Logger logger;

    /**
     * Constructs a {@code LoggingManager} with a specified {@link Logger}.
     * 
     * @param logger The logger instance to manage.
     */
    public LoggingManager(Logger logger) {
        this.logger = logger;
    }
    /**
     * Logs a message using the managed {@link Logger}.
     * 
     * @param message The message to log.
     */
    public void log(String message) {
        logger.log(message);
    }
    /**
     * Stops logging operations on the managed {@link Logger}.
     */
    public void stopLogging() {
        logger.stopLogging();
    }
}
