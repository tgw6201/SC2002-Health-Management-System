import java.util.Scanner;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.dataReader;
import FileManager.dataWriter;

public class Testing {
    public static void main(String[] args) {
        
       //MedicalRecord works fine
       //MedicalRecordManager medical = new MedicalRecordManager();
       
       //Check case 1 and 9
       //medical.viewMedicalRecord("P1001");
       
       //Check case 2
       //medical.updatePersonalInformation("P1002", "bobby@gmail.com", "09876543");
       
       //Check case 10
       //medical.updatePatientMedicalRecords("P1003", "Heart Attack", "Surgery");
       


        //AppointmentSlotManager slot = new AppointmentSlotManager();

        //check case 3
        //slot.viewAvailableAppointmentSlots();

        
        //AppointmentManager appointment = new AppointmentManager();

        //check case 4 --> work for available and booked
        //appointment.scheduleAppointment("P1001", "D100111900");

        //check case 5 --> work for if available and booked
        //appointment.rescheduleAppointment("P1002D1002111300", "D100111900");

        //check case 6 --> work 
        //appointment.cancelAppointment("P1002D1002111300");

        //check case 7
        //appointment.viewScheduleAppointment("P1002");

        //AppointmentOutcomeManager manager = new AppointmentOutcomeManager();
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
        
        //case 14 --> not done yet
        
        //case 15 --> works
        //manager.recordAppointmentOutcome("Retrieve medication", "Paracetamol", "100", "pending", "eat meds 3 times a day", "P1002D1002111300");

        //case 16 --> works
        //manager.viewOutcomeRecord("P1002P1002D1002111300");

        //case 21 --> should be fine check again
        //appointment.viewAppointmentDetails("P1002D1002111300");
        
        //ppointment.acceptDeclineAppointment(null, null);
        //manager.createAppointmentOutcome("1001AB25","P1001", "25/10/2024", "Retrieve medication", "Paracetamol", "100", "pending", "eat meds 3 times a day");
        //manager.createAppointmentOutcome("1001AB27","P1001", "27/10/2024", "X-ray", "NIL", "0", "pending", "1 day mc");
        //manager.createAppointmentOutcome("1002BS28","P1002", "28/10/2024", "Check-up", "NIL", "0", "pending", "Nothing wrong");
        
        //manager.viewPastRecords("P1001");

        //manager.viewOutcomeRecord("P100125");

        //slot.setAppointmentAvailability("D1001", "John Smith", "11/5/2024", "9:00 - 10:00", "Available");
        //slot.setAppointmentAvailability("D1002", "Emily Clarke", "11/6/2024", "13:00 - 14:00", "Booked");
        //slot.setAppointmentAvailability("D100111900", "Booked");
        //slot.viewAvailableAppointmentSlots();

        //AppointmentManager appointment = new AppointmentManager();
        //appointment.scheduleAppointment("P1001", "D100111900");
        //appointment.scheduleAppointment("P1002", "D1002111300");
        //appointment.scheduleAppointment("P1002", "D100111900");

        //appointment.rescheduleAppointment("P1002D1002111300", "D100111900");
        //appointment.cancelAppointment("P1002D1002111300");
        //appointment.acceptDeclineAppointment("P1002D1002111300", "Decline");
        
        //appointment.viewScheduleAppointment("P1002");

        //appointment.viewPersonalSchedule("D1002");



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

        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        hospitalStaffManagement staffManagement = new hospitalStaffManagement(reader, writer);
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
    }

}
