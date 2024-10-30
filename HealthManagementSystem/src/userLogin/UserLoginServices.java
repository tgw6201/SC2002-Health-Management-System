package userLogin;
import Logger.*;

public class UserLoginServices implements userLogin{
    private final Logger logger;
    private String username;
    private String password;

    public UserLoginServices(Logger logger) {
        this.logger = logger;
        this.username = "";
        this.password = "";
    }

    @Override
    public boolean login(String username, String password) {
        this.username = username;
        this.password = password;
        //Fetch user credentials from excel file
        if (username.equals("admin") && password.equals("admin")) {
            logger.log("User " + username + " has logged in.");
            return true;
        }
        logger.log("User " + username + " has failed to log in.");
        return false;
    }

    @Override
    public boolean logout() {
        logger.log("User " + username + " has logged out.");
        return false;
    }

    @Override
    public boolean register(String username, String password) {
        return false;
    }

    @Override
    public boolean changePassword(String username, String password) {
        return false;
    }

    @Override
    public boolean resetPassword(String username) {
        return false;
    }

    @Override
    public String getRole() {
        return "role";
    }
}
