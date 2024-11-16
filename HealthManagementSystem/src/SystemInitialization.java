
import FileManager.*;
import Logger.*;
import java.util.Scanner;
import userLogin.*;

/**
 * The SystemInitialization class serves as the entry point for the 
 * Hospital Management System. It handles the user login process, 
 * manages user roles dynamically, and provides the main application 
 * flow.
 *
 * The system uses various components:
 * - FileManager: For reading and writing CSV files.
 * - Logger: For logging user activities.
 * - userLogin: For managing user login credentials and roles.
 *
 * This class demonstrates dynamic role-based functionality using Java reflection.
 * 
 * @author Peter Loh
 * @version 1.0
 * @since 2024-11-6
 */

public class SystemInitialization {
    /**
     * The main method serves as the entry point for the Hospital Management System.
     * It handles user login, role management, and dynamic invocation of role-based functionality.
     *
     * @param args Command-line arguments (not used in this application).
     */
        public static void main(String[] args) 
        {
            //Login Process
            dataReader reader = new CsvFileReader();
            dataWriter writer = new CsvFileWriter();
            
            String username;
            String password;
            String defaultPassword = "password";
            Scanner sc = new Scanner(System.in);
            Logger logger;

            //Assumes Role is properly spelled in User_Accounts
            //Main application Loop
            while(true)
            {
            System.out.println("Hospital Management System");
            System.out.println("Username: ");
            username = sc.nextLine();
            logger = new FileLogger(username);
            System.out.println("Password: ");
            password = sc.nextLine();


            UserLoginServices userLoginServices = new UserLoginServices(reader,writer);
            boolean login = userLoginServices.login(username, password);
            if (login == false) 
            {
                userLoginServices.logout();
                System.out.println("Error, incorrect login credentials");
                continue;
            }
            if (password.equals(defaultPassword))// if default password, change password
            {
                System.out.println("Default password detected; input new password");
                password = sc.nextLine();
                userLoginServices.changePassword(username, password);
                userLoginServices.logout();
                //continue;
            }
            System.out.println("Login Status:" + login);
            System.out.println("Role: " + userLoginServices.getRole());
            
            //Role-based functionality using reflection
            try 
            {
                // Load the class dynamically based on user role
                Class<?> myClass = Class.forName(userLoginServices.getRole());
                // Define constructor parameter types
                Class<?>[] parameterTypes = {String.class, String.class, Logger.class, dataReader.class, dataWriter.class};
                // Get the constructor that matches the parameter types
                var constructor = myClass.getDeclaredConstructor(parameterTypes);
                // Instantiate the class with role-specific parameters
                Object you = constructor.newInstance(username, userLoginServices.getRole(), logger, reader, writer);
    
                // Use reflection to invoke the menu method
                myClass.getMethod("menu").invoke(you);
            } catch (ClassNotFoundException e) {
                //System.out.println("User not found: " + e.getMessage());
            } catch (Exception e) 
            {
                e.printStackTrace();
            }
            System.out.println("Logging out...");
            logger.log(userLoginServices.getRole()+" "+ userLoginServices.getID() + " logged out");
            logger.stopLogging();
            userLoginServices.logout();
            }
        }
}
