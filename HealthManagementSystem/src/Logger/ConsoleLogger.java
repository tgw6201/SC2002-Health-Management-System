package Logger;
public class ConsoleLogger implements Logger {
    @Override
    public void log(String message) {
        System.out.println("Console Logger: " + message);
    }

    @Override
    public void stopLogging() {
        System.out.println("Console Logger stopped.");
    }
}
