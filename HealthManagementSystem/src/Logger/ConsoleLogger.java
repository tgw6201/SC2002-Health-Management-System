package Logger;

/**
 * The ConsoleLogger class implements the Logger interface, providing logging functionality 
 * that outputs messages to the console.
 * 
 * <p>
 * This class is intended for scenarios where logging information is required to be printed 
 * directly to the standard output (console). The `log` method prints the message with a 
 * "Console Logger" prefix, and the `stopLogging` method prints a message indicating that 
 * logging has been stopped.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class ConsoleLogger implements Logger {
    /**
     * Logs a message to the console with a "Console Logger" prefix.
     * 
     * @param message The message to log.
     */
    @Override
    public void log(String message) {
        System.out.println("Console Logger: " + message);
    }

    /**
     * Stops logging by printing a message indicating the stop of logging.
     * This method could be used to signal the end of a logging session or process.
     */
    @Override
    public void stopLogging() {
        System.out.println("Console Logger stopped.");
    }
}
