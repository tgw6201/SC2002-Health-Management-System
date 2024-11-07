package Logger;

public class LoggingManager {
    private final Logger logger;

    public LoggingManager(Logger logger) {
        this.logger = logger;
    }
    public void log(String message) {
        logger.log(message);
    }
    public void stopLogging() {
        logger.stopLogging();
    }
}
