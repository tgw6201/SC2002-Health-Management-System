import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.dataReader;
import FileManager.dataWriter;
import Logger.FileLogger;
import Logger.Logger;
import java.util.Scanner;
import userLogin.UserLoginServices;

public class SystemInitialization {
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
            
            //upcast role to user
            try 
            {
                // Load the class dynamically
                Class<?> myClass = Class.forName(userLoginServices.getRole());
                //Defining constructor parameter types
                Class<?>[] parameterTypes = {String.class, String.class, Logger.class, dataReader.class, dataWriter.class};
                //Constructor that matches parameter
                var constructor = myClass.getDeclaredConstructor(parameterTypes);
                // Instantiate the class
                Object you = constructor.newInstance(username, userLoginServices.getRole(), logger, reader, writer);

                //getDeclaredConstructor(username, userLoginServices.getRole()).newInstance();
    
                // Use reflection to call the display method
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
