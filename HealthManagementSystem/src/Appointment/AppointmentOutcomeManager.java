package Appointment;
import java.util.*;
import FileManager.*;

public class AppointmentOutcomeManager {
    //private ArrayList<AppointmentOutcomeRecord>appointmentOutcomeList;
    private List<String[]> appointmentOutcomeListCsv; // list of stringarrays --> each string array is an appointmentoutcomerecord/a row in csv

    CsvFileReader csvFileReader = new CsvFileReader();
    CsvFileWriter csvFileWriter = new CsvFileWriter();

    //sync the appointmentOutcomeList with CSV file
    public AppointmentOutcomeManager(){
        appointmentOutcomeListCsv = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
    }

    //create an appointmentOutcomeRecord and add to the appointmentOutcomeList + CSV file
    public void createAppointmentOutcome(String appointmentOutcomeID, String patientID, String appointmentDate, String typeOfService, String prescribedMedication, String prescriptionStatus, String consultationNotes){
        
        List<String> newRow = new ArrayList<String>();
        newRow.add(appointmentOutcomeID);
        newRow.add(patientID);
        newRow.add(appointmentDate);
        newRow.add(typeOfService);
        newRow.add(prescribedMedication);
        newRow.add(prescriptionStatus);
        newRow.add(consultationNotes);
        csvFileWriter.writeRow("AppointmentOutcomeRecord_List.csv", newRow);
        
        //Add written data into appointmentOutcomeListCsv list
        String[] newdata = { appointmentOutcomeID, patientID, appointmentDate, typeOfService, prescribedMedication, prescriptionStatus, consultationNotes};
        appointmentOutcomeListCsv.add(newdata);
    }
    
    //For patients to view all past records
    public void viewPastRecords(String patientID){

        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescription Status: ",
            "Consultation Notes: "
        };

        for(String[] row : appointmentOutcomeListCsv){// iterates over each String[] (1 row in csv)
            if(row[1].equals(patientID)){ // check if patientID matches
                for(int i = 0; i < row.length; i++){ // iterates over each cell in the row
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
            }
        }
            
    }

    //For viewing a specific appointmentOutcomeRecord
    public void viewOutcomeRecord(String appointmentOutcomeID){

        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescription Status: ",
            "Consultation Notes: "
        };

        for(String[] row : appointmentOutcomeListCsv){// iterates over each String[] (1 row in csv)
            if(row[0].equals(appointmentOutcomeID)){ // check if appointmentOutcomeID matches
                
                for(int i = 0; i < row.length; i++){
                    System.out.println(labels[i] + row[i]);
                }

                System.out.println();
            }
        }


    }


}
