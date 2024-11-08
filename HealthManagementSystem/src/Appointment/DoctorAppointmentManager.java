package Appointment;
import java.util.List;
import Appointment2;

import Appointment2.AppointmentSlotManager;
import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;
import FileManager.DataProcessor;
import FileManager.dataReader;
import FileManager.dataWriter;

import java.util.*;

public class DoctorAppointmentManager implements DoctorAppointmentService {

    private DataProcessor dataProcessor;

    public DoctorAppointmentManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
    }

    //Doctor accept or decline appointment
    public void acceptDeclineAppointment(String appointmentID, String decision){
        //Read existing data in csv

        List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
        List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();  

        //intialise
        String appointmentSlotID = "NIL";
        //i is used to keep track of the row that appoinment is in
        int i = 0;
           
        //change appointment Status to confirmed
        if(decision.equalsIgnoreCase("Accept")){
            //find apppointment
            for(String[] row :  appointmentListCsv){
                //check for appointmentID
                if(row[0].equalsIgnoreCase(appointmentID)){
                    //update appointmentStatus
                    dataProcessor.writeData("Appointment_List.csv", i, 3, "Confirmed");
                        
                    //get appointmentSlotID
                    appointmentSlotID = row[6];
                    }
                    i++;
                }
            //update appointmentSlot to booked
            AppointmentSlotManager slot = new AppointmentSlotManager(reader,writer);
            slot.setAppointmentAvailability(appointmentSlotID, "Booked");
        
            //verify appointment status changes to "confirmed"
            System.out.println("Appointment status changed to confirmed");
    
            }
    
    
            //change appointmentStatus to cancelled and appointmentSlot to available
            else{
                //find appointment
                for(String[] row :  appointmentListCsv){
                    //check for appointmentID
                    if(row[0].equalsIgnoreCase(appointmentID)){
                        //update appointmentStatus
                        dataProcessor.writeData("Appointment_List.csv", i, 3, "Cancelled");
    
    
                        //get appointmentSlotID
                        appointmentSlotID = row[6];
                    }
                    i++;
                }
                //update appointmentSlot to available
                AppointmentSlotManager slot = new AppointmentSlotManager(reader,writer);
                slot.setAppointmentAvailability(appointmentSlotID, "Available");
    
    
                //verify “cancelled” when declined
                System.out.println("Appointment status changed to decline");
            }
        }
    


        //doctor view personal schedule
        public void viewPersonalSchedule(String doctorID){
            
            //Read existing data in csv
            List<String[]>appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
            List<String[]>appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
            
            //labels to format printing
            String[] appointmentLabels = {
                "Appointment ID: ",
                "Patient ID: ",
                "Doctor ID: ",
                "Appointment Status: ",
                "Appointment Date: ",
                "Appointment Time: ",
                "Appointment Slot ID: ",
                "Appointment Outcome ID: "
            };

            System.out.println("Upcoming appointments: ");

            //find the appointments that corresponds to doctorID and confirmed
            for(String[] row1 : appointmentListCsv){
                if(row1[2].equalsIgnoreCase(doctorID) && row1[3].equalsIgnoreCase("Confirmed")){
                    for(int i = 0; i < row1.length; i++){
                        System.out.println(appointmentLabels[i] + row1[i]);
                    }
                }
                System.out.println();
            }

            //labels to format printing
            String[] appointmentSlotLabels = {
                "Appointment Slot ID: ",
                "Doctor ID: ",
                "Doctor Name: ",
                "Appointment Date: ",
                "Apppointment Time: ",
                "Availability: "
            };

            System.out.println("Availability Slots: ");
            //find the appointmentSlot that corresponds to the doctorID 
            for(String[] row2 : appointmentSlotListCsv){
                if(row2[1].equalsIgnoreCase(doctorID)){ 
                    for(int i = 0; i<row2.length; i++){
                        System.out.println(appointmentSlotLabels[i] + row2[i]);
                    }
                }
                System.out.println();
            }
            
        }

    //view upcoming confirmed appointments
    public void viewUpcomingAppointments(String DoctorID){
        //Read existing data in csv
        List<String[]>appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
        List<String[]>medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

        //initialization 

        String appointmentDate = "NIL";
        String appointmentTime = "NIL";
        String patientID = "NIL";

        //labels for formatting 
        String[] labels = {
            "Patient ID: ", 
            "Name: ",
            "Date Of Birth: ",
            "Gender: ",
            "Phone Number: ",
            "Email Address: ",
            "Blood Type: ",
            "",
        };


        System.out.println("Upcoming appointments:");
        System.out.println();

        for(String[] row: appointmentListCsv){
            //check if doctorID matches and appointmentStatus = confirmed
            if(row[2].equalsIgnoreCase(DoctorID) && row[3].equalsIgnoreCase("Confirmed")){
                patientID = row[1];
                appointmentDate = row[4];
               appointmentTime = row[5];

                //print out patient details 
                for(String[]row1 : medicalRecordListCsv){
                    if(row1[0].equalsIgnoreCase(patientID)){
                        for(int i=0; i<8; i++){
                            System.out.println(labels[i] + row1[i]);
                        } 
                    }
                }

                System.out.println("Appointment Date: " + appointmentDate);
                System.out.println("Appointment Time: " + appointmentTime);
                System.out.println();
                    
                }
            }

        }

}
