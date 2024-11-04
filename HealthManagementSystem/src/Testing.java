import InformationAccess.InformationAccessManager;
import Logger.FileLogger;
import userLogin.UserLoginServices;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;

import Appointment.*;

public class Testing {
    public static void main(String[] args) {
        
        AppointmentOutcomeManager manager = new AppointmentOutcomeManager();
        manager.createAppointmentOutcome("1001AB25","P1001", "25/10/2024", "Retrieve medication", "Paracetamol", "100", "pending", "eat meds 3 times a day");
        manager.createAppointmentOutcome("1001AB27","P1001", "27/10/2024", "X-ray", "NIL", "0", "pending", "1 day mc");
        manager.createAppointmentOutcome("1002BS28","P1002", "28/10/2024", "Check-up", "NIL", "0", "pending", "Nothing wrong");
        
        manager.viewPastRecords("P1001");

        manager.viewOutcomeRecord("1002BS28");
        

        //CsvFileWriter csvFileWriter = new CsvFileWriter();
        //csvFileWriter.writeData("Medicine_List.csv", 2, 1, "50");
        /* 
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        String username;
        String password;
        FileLogger logger;
        UserLoginServices userLoginServices;

        while(choice != 0)
        {
            System.out.println("Login System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Forgot Password");
            System.out.println("0. Exit");
            System.out.println("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1)
            {
                System.out.println("Enter username: ");
                username = sc.nextLine();
                System.out.println("Enter password: ");
                password = sc.nextLine();
                userLoginServices = new UserLoginServices(logger=new FileLogger(username));
                if(userLoginServices.login(username, password))
                {
                    System.out.println("Login successful");
                }
                else
                {
                    System.out.println("Login failed");
                }
                System.out.println("Your role is: " + userLoginServices.getRole());
                System.out.println("Logging out now...");
                userLoginServices.logout();
            }
            else if(choice == 2)
            {
                System.out.println("Register Account");
                System.out.println("Enter username: ");
                username = sc.nextLine();
                System.out.println("Enter password: ");
                password = sc.nextLine();
                userLoginServices = new UserLoginServices(logger=new FileLogger(username));
                if(userLoginServices.register(username, password, "Patient"))
                {
                    System.out.println("Registration successful");
                }
                else
                {
                    System.out.println("Registration failed");
                }
            }
            else if(choice == 3){
                System.out.println("Forgot Password");
                System.out.println("Enter username: ");
                username = sc.nextLine();
                userLoginServices = new UserLoginServices(logger=new FileLogger(username));
                if(userLoginServices.resetPassword(username))
                {
                    System.out.println("Password reset successful");
                }
                else
                {
                    System.out.println("Password reset failed");
                }
            }
        }
        System.out.println("Exiting..."); */
    }

}
