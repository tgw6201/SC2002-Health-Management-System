package userLogin;
import Logger.*;

import java.util.ArrayList;
import java.util.List;

import FileManager.*;

/**
 * The UserLoginServices class implements the {@link userLogin} interface, providing user authentication
 * and management functionalities such as login, logout, user registration, password change, and password reset.
 * This class interacts with CSV files to manage user accounts and stores user credentials such as username, password,
 * and role. It also logs user actions such as login, logout, registration, and password changes using a logging mechanism.
 * <p>
 * The service validates user credentials during login, allowing for role-based access control, and facilitates secure password changes or resets.
 * </p>
 *
 * <p>
 * Example usage:
 * <pre>
 *     Logger logger = new ConsoleLogger(); // Or a FileLogger for persistent logs
 *     UserLoginServices loginServices = new UserLoginServices(logger);
 *     loginServices.login("user1", "password123");
 * </pre>
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class UserLoginServices implements userLogin{
    private final Logger logger;
    private String username;
    private String password;
    private boolean validation;
    private String userRole;

    /**
     * Constructs a UserLoginServices instance with a specified logger for logging activities.
     * 
     * @param logger The logger to be used for logging activities (e.g., ConsoleLogger, FileLogger).
     */
    public UserLoginServices(Logger logger) {
        this.logger = logger;
        this.username = "";
        this.password = "";
        this.validation = false;
    }

    /**
     * Logs in the user by checking the provided username and password against stored user credentials.
     * If valid, the user's role is assigned, and the login is logged using the logger.
     * 
     * @param username The username of the user attempting to log in.
     * @param password The password associated with the username.
     * @return true if the login is successful; false if the credentials are invalid.
     */
    @Override
    public boolean login(String username, String password) {
        this.username = username;
        this.password = password;
        //Fetch user credentials from csv file
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> userCredentials = csvFileReader.readData("User_Accounts.csv");
        for(String[] user : userCredentials) {
            if(user[0].equals(username) && user[1].equals(password)) {
                this.validation = true;
                this.userRole = user[2];
                logger.log("User " + username + " has logged in.");
                return true;
            }
        }
        logger.log("User " + username + " has failed to log in.");
        return false;
    }

    /**
     * Logs out the currently logged-in user, clearing their session data and logging the logout event.
     * 
     * @return false after logging the user out (since no data is returned after logout).
     */
    @Override
    public boolean logout() {
        logger.log("User " + username + " has logged out.");
        logger.stopLogging();
        this.validation = false;
        this.username = "";
        this.password = "";
        this.userRole = "";
        return false;
    }

    /**
     * Checks if a user with the given username exists in the stored user data.
     * 
     * @param username The username to be checked.
     * @return true if the user exists; false otherwise.
     */
    private boolean findUser(String username) {
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> userCredentials = csvFileReader.readData("User_Accounts.csv");
        for(String[] user : userCredentials) {
            if(user[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a new user by adding their username, password, and role to the user accounts data.
     * If the username already exists, registration fails, and an appropriate log entry is made.
     * 
     * @param username The username to be registered.
     * @param password The password for the new user.
     * @param role The role of the new user (e.g., "admin", "user").
     * @return true if the user was successfully registered; false if the username already exists.
     */
    @Override
    public boolean register(String username, String password, String role) {
        
        //Check if username already exists
        if(findUser(username)) {
            logger.log("User " + username + " already exists.");
            return false;
        }

        CsvFileWriter csvFileWriter = new CsvFileWriter();
        ArrayList<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        data.add(role);
        csvFileWriter.writeRow("User_Accounts.csv", data);
        Logger regLogger = new FileLogger("Registration");
        regLogger.log("User " + username + " has registered.");
        return true;
    }

     /**
     * Changes the password for an existing user, updating the password in the stored user credentials.
     * If the user does not exist, the password change fails.
     * 
     * @param username The username of the user whose password is to be changed.
     * @param password The new password for the user.
     * @return true if the password was successfully changed; false if the user does not exist.
     */
    @Override
    public boolean changePassword(String username, String password) {
        //Fetch user credentials from excel file
        if(findUser(username) == false) {
            logger.log("User " + username + " does not exist.");
            return false;
        }
        int row = 0;
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> userCredentials = csvFileReader.readData("User_Accounts.csv");
        List<String> updatedData = new ArrayList<>();
        for(String[] user : userCredentials) {
            if(user[0].equals(username)) {
                user[1] = password;
                for(int i = 0; i < 3; i++)
                    updatedData.add(user[i]);
                break;
            }
            row++;
        }

        CsvFileWriter csvFileWriter = new CsvFileWriter();
        csvFileWriter.writeRow("User_Accounts.csv", row ,updatedData);
        logger.log("User " + username + " has changed password.");
        return true;
    }

    /**
     * Resets the password for a user to a default value ("password"). If the user does not exist,
     * the reset fails.
     * 
     * @param username The username of the user whose password is to be reset.
     * @return true if the password was successfully reset; false if the user does not exist.
     */
    @Override
    public boolean resetPassword(String username) {
        if(findUser(username) == false) {
            logger.log("User " + username + " does not exist.");
            return false;
        }
        int row = 0;
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> userCredentials = csvFileReader.readData("User_Accounts.csv");
        List<String> updatedData = new ArrayList<>();
        for(String[] user : userCredentials) {
            if(user[0].equals(username)) {
                user[1] = "password";
                for(int i = 0; i < 3; i++)
                    updatedData.add(user[i]);
                break;
            }
            row++;
        }

        CsvFileWriter csvFileWriter = new CsvFileWriter();
        csvFileWriter.writeRow("User_Accounts.csv", row ,updatedData);
        logger.log("User " + username + " has reset password.");
        return true;
    }

    /**
     * Retrieves the role of the currently logged-in user. If no user is logged in, an empty string is returned.
     * 
     * @return The role of the logged-in user if authenticated; an empty string if no user is logged in.
     */
    @Override
    public String getRole() {
        if(validation == true) {
            return userRole;
        }
        else
            return "";
    }
}
