import InformationAccess.InformationAccessManager;
import Logger.FileLogger;
import userLogin.UserLoginServices;

import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Scanner;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;

import Appointment.*;

public class Testing {
    public static void main(String[] args) {
        
        AppointmentOutcomeManager manager = new AppointmentOutcomeManager();
        manager.createAppointmentOutcome("P1001", "25/10/2024", "x-ray", "Paracetamol, Ibuprofen", "pending", "eat meds 3 times a day");
        manager.viewPastRecords("P1001");
        

        //CsvFileWriter csvFileWriter = new CsvFileWriter();
        //csvFileWriter.writeData("Medicine_List.csv", 2, 1, "50");
        /* 
        String username;
        String password;
        Scanner sc = new Scanner(System.in);
        FileLogger logger;
        System.out.println("Hospital Management System");
        System.out.println("Username: ");
        username = sc.nextLine();
        logger = new FileLogger(username+".txt");
        System.out.println("Password: ");
        password = sc.nextLine();
        UserLoginServices userLoginServices = new UserLoginServices(logger);
        boolean login = userLoginServices.login(username, password);
        System.out.println("Login Status:" + login);
        System.out.println("Role: " + userLoginServices.getRole());
        
        
        
        //when patient is accessing information
        InformationAccessManager AccessManager = new InformationAccessManager();
        boolean x = AccessManager.checkInformationAccess("Blood Type");
        System.out.println(x);
        */
        //Testing of CSV File reader

        /*
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> data = csvFileReader.readData("User_Accounts.csv");

        if(data.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }


        List<String[]> column = csvFileReader.readColumn("User_Accounts.csv", "0");
        if(column.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (String[] cell : column) {
            System.out.print(cell[0] + ",");
        } */

    }
}
