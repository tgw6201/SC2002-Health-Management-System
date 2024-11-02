package userLogin;
import Logger.*;

import java.util.List;

import FileManager.*;

public class UserLoginServices implements userLogin{
    private final Logger logger;
    private String username;
    private String password;
    private boolean validation;
    private String userRole;

    public UserLoginServices(Logger logger) {
        this.logger = logger;
        this.username = "";
        this.password = "";
        this.validation = false;
    }

    @Override
    public boolean login(String username, String password) {
        this.username = username;
        this.password = password;
        //Fetch user credentials from excel file
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

    //WIP
    @Override
    public boolean logout() {
        logger.log("User " + username + " has logged out.");
        this.validation = false;
        return false;
    }

    //WIP
    @Override
    public boolean register(String username, String password) {
        return false;
    }

    //WIP
    @Override
    public boolean changePassword(String username, String password) {
        return false;
    }

    //WIP
    @Override
    public boolean resetPassword(String username) {
        return false;
    }

    @Override
    public String getRole() {
        if(validation == true) {
            return userRole;
        }
        else
            return "";
    }
}
