import InformationAccess.InformationAccessManager;
import Logger.FileLogger;
import MedicalRecord.*;

import userLogin.UserLoginServices;

import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.dataReader;
import FileManager.dataWriter;
import Appointment.*;
import Appointment2.*;

public class Testing {
    public static void main(String[] args) {
       
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();    
        
        //MedicalRecord works fine

        //DIP
        //PatientMedicalRecordManager patientService =  new PatientMedicalRecordManager(reader, writer);
        //MedicalRecordManagement patientMedRecord = new MedicalRecordManagement(patientService);
        
        //Check case 1
        //patientRecord.viewMedicalRecord("P1001");

        //Check case 2
        //patientRecord.updateMedicalRecord("P1002", "bobby@gmail.com", "09876543");


        //DoctorMedicalRecordManager doctorService =  new DoctorMedicalRecordManager(reader, writer);
        //MedicalRecordManagement doctorUseRecord = new MedicalRecordManagement(doctorService);
       
        //Check case 9
        //doctorUseRecord.viewMedicalRecord("P1001");

        //Check case 10
        //doctorUseRecord.updateMedicalRecord("P1003", "Heart Attack", "Surgery");



        //AppointmentSlotManager slot = new AppointmentSlotManager(reader, writer);

        //check case 3
        //slot.viewAvailableAppointmentSlots();
        
        AppointmentManager appointment = new AppointmentManager(reader, writer);

        //check case 4 --> work for available and booked
        //appointment.scheduleAppointment("P1001", "D100212600");

        //check case 5 --> work for if available and booked
        appointment.rescheduleAppointment("P1001D100212600", "D100212600");

        //check case 6 --> work 
        //appointment.cancelAppointment("P1001D100111900");

        //check case 7
        //appointment.viewScheduleAppointment("P1002");

        //AppointmentOutcomeManager manager = new AppointmentOutcomeManager(reader, writer);
        //check case 8 --> works
        //manager.viewPastRecords("P1002");
        
        //check case 11 -> work
        //appointment.viewPersonalSchedule("D1001");

        //case 12 -> work for set and update
        //slot.setAppointmentAvailability("D1002111300", "Available");
        //slot.viewAvailableAppointmentSlots();
        //slot.setAppointmentAvailability("D1002","Emily Clarke", "12/7/2024", "6:00-9:00", "available");
        
        //case 13 --> work
        //appointment.acceptDeclineAppointment("P1002D1002111300", "accept");
        
        //case 14 --> work
        //appointment.viewUpcomingAppointments("D1002");
        
        //case 15 --> works
        //manager.recordAppointmentOutcome("Retrieve medication", "Paracetamol", "100", "pending", "eat meds 3 times a day", "P1002D1002111300");

        //case 16 --> works
        //manager.viewOutcomeRecord("P1002P1002D1002111300");

        //case 21 --> should be fine check again
        //appointment.viewAppointmentDetails("P1002D1002111300");
        

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
