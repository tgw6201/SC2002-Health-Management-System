package userLogin;
import FileManager.*;
import Logger.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements {@link userLogin} interface for user authentication, registration, and password management.
 * Logs user actions such as login, logout, and registration.
 * 
 * <p>
 * Example usage:
 * </p>
 * <pre>
 * dataReader reader = new CsvFileReader();
 * dataWriter writer = new CsvFileWriter();
 * UserLoginServices loginServices = new UserLoginServices(reader, writer);
 * loginServices.login("P1001", "password");
 * </pre>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class UserLoginServices implements userLogin{
    private String username;
    private String password;
    private boolean validation;
    private String userRole;
    private final DataProcessor dataProcessor;

    /**
     * Constructs a {@code UserLoginServices} instance with data reader and writer.
     * 
     * @param reader Data reader for user data.
     * @param writer Data writer for saving user data.
     */

    public UserLoginServices(dataReader reader, dataWriter writer) {
        this.username = "";
        this.password = "";
        this.validation = false;
        this.dataProcessor = new DataProcessor(reader, writer);
    }

     /**
     * Validates user credentials for login.
     * 
     * @param username The username.
     * @param password The password.
     * @return {@code true} if valid, otherwise {@code false}.
     */
    @Override
    public boolean login(String username, String password) {
        this.username = username;
        this.password = password;
        //Fetch user credentials from csv file

        List<String[]> userCredentials = dataProcessor.readData("User_Accounts.csv");
        for(String[] user : userCredentials) {
            if(user[0].equals(username) && user[1].equals(password)) {
                this.validation = true;
                this.userRole = user[2];
                return true;
            }
        }
        return false;
    }

    /**
     * Logs out the user.
     * 
     * @return {@code false} (no data returned after logout).
     */
    @Override
    public boolean logout() {
        this.validation = false;
        this.username = "";
        this.password = "";
        this.userRole = "";
        return false;
    }

    /**
     * Checks if a user exists.
     * 
     * @param username The username.
     * @return {@code true} if user exists, otherwise {@code false}.
     */
    private boolean findUser(String username) {
        List<String[]> userCredentials = dataProcessor.readData("User_Accounts.csv");
        for(String[] user : userCredentials) {
            if(user[0].equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Registers a new user.
     * 
     * @param username The username.
     * @param password The password.
     * @param role The user role.
     * @return {@code true} if registered, otherwise {@code false}.
     */
    @Override
    public boolean register(String username, String password, String role) {
        
        //Check if username already exists
        if(findUser(username)) {
            return false;
        }
        ArrayList<String> data = new ArrayList<>();
        data.add(username);
        data.add(password);
        data.add(role);
        dataProcessor.writeRow("User_Accounts.csv", data);
        Logger regLogger = new FileLogger("Registration");
        regLogger.log("User " + username + " has registered.");
        return true;
    }

     /**
     * Changes an existing user's password.
     * 
     * @param username The username.
     * @param password The new password.
     * @return {@code true} if changed, otherwise {@code false}.
     */
    @Override
    public boolean changePassword(String username, String password) {
        //Fetch user credentials from excel file
        if(findUser(username) == false) {
            return false;
        }
        int row = 0;
        List<String[]> userCredentials = dataProcessor.readData("User_Accounts.csv");
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
        dataProcessor.writeRow("User_Accounts.csv", row ,updatedData);
        return true;
    }

    /**
     * Resets a user's password to the default value.
     * 
     * @param username The username.
     * @return {@code true} if reset, otherwise {@code false}.
     */
    @Override
    public boolean resetPassword(String username) {
        if(findUser(username) == false) {
            return false;
        }
        int row = 0;
        List<String[]> userCredentials = dataProcessor.readData("User_Accounts.csv");
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

        dataProcessor.writeRow("User_Accounts.csv", row ,updatedData);
        return true;
    }

     /**
     * Retrieves the role of the currently logged-in user.
     * 
     * @return The user's role if logged in; otherwise an empty string.
     */
    @Override
    public String getRole() {
        if(validation == true) {
            return userRole;
        }
        else
            return "";
    }
    /**
     * Retrieves the ID of the currently logged-in user.
     * 
     * @return The user's ID (username).
     */
    public String getID()
    {
        return this.username;
    }
}
