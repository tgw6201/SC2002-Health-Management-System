package Appointment2;
import java.util.*;
import FileManager.*;

public class AppointmentOutcomeManager implements AppointmentOutcomeService{

    private DataProcessor dataProcessor;

    //sync the appointmentOutcomeList with CSV file
    public AppointmentOutcomeManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
    }

    //create an appointmentOutcomeRecord and add to the appointmentOutcomeList + CSV file
    //common variables bet outcome and appointment: patientID, appointmentDate
    //appointmentStatus should be completed each time appointment outcome is recorded
    //appointmentID to track
    //update appointmentOutcomeRecord of appointment
    public void recordAppointmentOutcome(String typeOfService, String prescribedMedication, String prescribedMedicationQuantity, String prescriptionStatus, String consultationNotes, String appointmentID){
        
        List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
        

        //intialization
        String patientID = "NIL";
        String appointmentDate = "NIL";

        //get patientID, appointmentDate from appointment
        //use i to track row
        int i = 0;
        for(String[] row: appointmentListCsv){
            if(row[0].equalsIgnoreCase(appointmentID)){
                patientID = row[1];
                appointmentDate = row[4];

                //create appointmentOutcomeID = patientID + appointmentID
                String appointmentOutcomeID = patientID + appointmentID;
                
                //update appointment Status
                dataProcessor.writeData("Appointment_List.csv", i, 3, "Completed");

                //update AppointmentOutcomeID for appointment
                dataProcessor.writeData("Appointment_List.csv", i, 7, appointmentOutcomeID);

            }
            i++;
        }

        //create appointmentOutcomeID = patientID + appointmentID
        String appointmentOutcomeID = patientID + appointmentID;

        List<String> newRow = new ArrayList<String>();
        newRow.add(appointmentOutcomeID);
        newRow.add(patientID);
        newRow.add(appointmentDate);
        newRow.add(typeOfService);
        newRow.add(prescribedMedication);
        newRow.add(prescribedMedicationQuantity);
        newRow.add(prescriptionStatus);
        newRow.add(consultationNotes);
        newRow.add(appointmentID);
        dataProcessor.writeRow("AppointmentOutcomeRecord_List.csv", newRow);


        //verify that the appointment outcome is recorded(doctor's pov)
        System.out.println("Appointment Outcome recorded");
    }
    
    //For patients to view all past records
    public void viewPastRecords(String patientID){

        List<String[]> appointmentOutcomeListCsv = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
    
        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescribed Medication Quantity: ",
            "Prescription Status: ",
            "Consultation Notes: ",
            "Appointment Outcome ID: "
        };

        for(String[] row : appointmentOutcomeListCsv){// iterates over each String[] (1 row in csv)
            if(row[1].equalsIgnoreCase(patientID)){ // check if patientID matches
                for(int i = 0; i < row.length; i++){ // iterates over each cell in the row
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
            }
        }
            
    }

    //For viewing a specific appointmentOutcomeRecord
    public void viewOutcomeRecord(String appointmentOutcomeID){

        List<String[]> appointmentOutcomeListCsv = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
    

        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescribed Medication Quantity: ",
            "Prescription Status: ",
            "Consultation Notes: ",
            "Appointment Outcome ID: "
        };

        for(String[] row : appointmentOutcomeListCsv){// iterates over each String[] (1 row in csv)
            if((row[0].equalsIgnoreCase(appointmentOutcomeID)) && (!row[0].equalsIgnoreCase("appointmentOutcomeID"))){ // check if appointmentOutcomeID matches and row 0 is the headers
                
                for(int i = 0; i < row.length; i++){
                    System.out.println(labels[i] + row[i]);
                }

                System.out.println();
            }
        }


    }


}
