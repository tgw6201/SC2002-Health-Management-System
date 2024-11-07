import Inventory.*;
import StaffManagement.HospitalStaffManagement;
import StaffManagement.StaffManagementAdd;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.LogManager;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.dataReader;
import FileManager.dataWriter;
import InformationAccess.*;
import Logger.*;

public class Testing {
    public static void main(String[] args) {


        /* 
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        HospitalStaffManagement staffManagement = new HospitalStaffManagement(reader, writer);
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while(choice != 5){
            System.out.println("Enter options:");
            System.out.println("1. Add Staff");
            System.out.println("2. Remove Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Filtered View");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1){
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                System.out.println("Enter Staff Name:");
                String staffName = sc.nextLine();
                System.out.println("Enter Staff Role:");
                String staffRole = sc.nextLine();
                System.out.println("Enter Staff gender:");
                String staffGender = sc.nextLine();
                System.out.println("Enter Staff Age:");
                int staffAge = sc.nextInt();
                sc.nextLine();
                staffManagement.addStaff(staffID, staffName, staffRole, staffGender, staffAge);
            }
            else if(choice == 2){
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                staffManagement.removeStaff(staffID);
            }
            else if(choice == 3){
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                System.out.println("Enter Staff Name:");
                String staffName = sc.nextLine();
                System.out.println("Enter Staff Role:");
                String staffRole = sc.nextLine();
                System.out.println("Enter Staff gender:");
                String staffGender = sc.nextLine();
                System.out.println("Enter Staff Age:");
                int staffAge = sc.nextInt();
                sc.nextLine();
                staffManagement.updateStaff(staffID, staffName, staffRole, staffGender, staffAge);
            }
            else if(choice == 4){
                System.out.println("Enter Category:");
                String category = sc.nextLine();
                System.out.println("Enter Type:");
                String type = sc.nextLine();
                staffManagement.filteredView(category, type);
            }
        }
        */
        /* 
        InventoryManagement inventoryManagement = new InventoryManagement();
        PrescriptionManagement prescriptionManagement = new PrescriptionManagement(inventoryManagement);
        */
        //inventoryManagement.viewItems();
        //inventoryManagement.submitReplenishmentRequest("Paracetamol", 10, "P001");
        //inventoryManagement.updateLowStockThreshold("Ibuprofen", 20);
        //inventoryManagement.restockItemByName("Ibuprofen", 10);
        //inventoryManagement.restockItemByRequestID("ec8c329e-205d-4be2-bda9-a28398c10438", "A001");
        //inventoryManagement.checkLowStockForItem("Paracetamol");
        //inventoryManagement.viewPendingItems();
        //inventoryManagement.handleAllPending("A001");

        //prescriptionManagement.viewPendingItems();
        //prescriptionManagement.handleAllPending("P001");
        //prescriptionManagement.dispenseItemByAppointmentID("1001AB25");
        //prescriptionManagement.dispenseItemByMedicineName("Paracetamol", 20);
        //prescriptionManagement.showAllAppointments();

        //CsvFileWriter csvFileWriter = new CsvFileWriter();
        //csvFileWriter.writeData("Medicine_List.csv", "2", "1", "5");
        
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
        */
        
        
        //when patient is accessing information

        /*
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        InformationAccessManager AccessManager = new InformationAccessManager("Patient", reader, writer);
        boolean x = AccessManager.checkInformationAccess("Blood Type");
        System.out.println(x); */
        
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
