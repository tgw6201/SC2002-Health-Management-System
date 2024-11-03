package Appointment;
import java.util.*;
import FileManager.*;

public class AppointmentOutcomeManager {
    private ArrayList<AppointmentOutcomeRecord>appointmentOutcomeList;
    private List<String[]> appointmentOutcomeListCsv; // list of stringarrays --> each string array is an appointmentoutcomerecord/a row in csv

    CsvFileReader csvFileReader = new CsvFileReader();
    CsvFileWriter csvFileWriter = new CsvFileWriter();

    //sync the appointmentOutcomeList with CSV file
    public AppointmentOutcomeManager(){
        appointmentOutcomeListCsv = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
    }

    //create an appointmentOutcomeRecord and add to the appointmentOutcomeList + CSV file
    public void createAppointmentOutcome(String patientID, String appointmentDate, String typeOfService, String prescribedMedication, String prescriptionStatus, String consultationNotes){
        AppointmentOutcomeRecord a = new AppointmentOutcomeRecord(patientID, appointmentDate, typeOfService, prescribedMedication, prescriptionStatus, consultationNotes);
        
        //String typeOfServiceStr = typeOfService.toString();
        //String prescribedMedicationStr = prescribedMedication.toString();

        String[] newRow = {patientID, appointmentDate, typeOfService, prescribedMedication, prescriptionStatus, consultationNotes};
        csvFileWriter.appendRow("ApppointmentOutcomeRecord_List.csv", newRow);
    }
    
    public void viewPastRecords(String patientID){
        for(String[] row : appointmentOutcomeListCsv){// iterates over each String[] (1 row in csv)
            if(row[0].equals(patientID)){ // check if patientID matches
                for(String cell: row){ // iterates over each string value in current row
                    System.out.print(cell + " ");
                }
            }
            System.out.println();
        }
            
    }

    /*public AppointmentOutcomeRecord convertCsvToRecordObj(){

    }*/
}
