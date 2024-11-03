package userLogin;
import Logger.*;

import java.util.ArrayList;
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

    @Override
    public String getRole() {
        if(validation == true) {
            return userRole;
        }
        else
            return "";
    }
}
