import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.DataProcessor;
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
            DataProcessor processor = new DataProcessor(reader, writer); 
            
            String username;
            String password;
            Scanner sc = new Scanner(System.in);
            Logger logger;
            System.out.println("Hospital Management System");
            System.out.println("Username: ");
            username = sc.nextLine();
            logger = new FileLogger(username); //removed .txt
            System.out.println("Password: ");
            password = sc.nextLine();
            UserLoginServices userLoginServices = new UserLoginServices(reader,writer);
            boolean login = userLoginServices.login(username, password);
            System.out.println("Login Status:" + login);
            System.out.println("Role: " + userLoginServices.getRole());
            
            //upcast role to user
            try {
                // Load the class dynamically
                Class<?> myClass = Class.forName(userLoginServices.getRole());
    
                // Instantiate the class
                Object you = myClass.getDeclaredConstructor().newInstance();
    
                // Use reflection to call the display method
                myClass.getMethod("menu").invoke(you);
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found: " + e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
        }
}
}
