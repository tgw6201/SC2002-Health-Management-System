package MedicalRecord;

import FileManager.*;

import java.util.*;

public class PatientMedicalRecordManager implements MedicalRecordService{

    private List<String[]> medicalRecordListCsv;
    private DataProcessor dataProcessor;

    public PatientMedicalRecordManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
        medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");
    }

    //view Medical Record --> patient use
    public void viewMedicalRecord(String patientID){

        medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");
        
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
            "Doctor ID: ",
            "Doctor In Charge: "
        };

        
        //print out medical record details

        for(String [] row : medicalRecordListCsv){
            
            //check patientID
            if(row[0].equalsIgnoreCase(patientID)){
                //verify system display Medical Record
                System.out.println("Medical Record: ");
                for(int i = 0; i<row.length; i++){
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
                return;               
            }

        }
        //If patientID don't match medical record
        System.out.println("Medical Record cannot be found. Key in Patient ID again.");

    }

    public void updateInformation(String patientID, String newEmailAddress, String newContactNumber){
        medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

        //i to keep track of the row 
        int i = 0;

        for(String [] row : medicalRecordListCsv){

            //check patientID
            if(row[0].equalsIgnoreCase(patientID)){
                //update email 
                dataProcessor.writeData("MedicalRecord_List.csv", i, 5, newEmailAddress);
                
                //update contact number
                dataProcessor.writeData("MedicalRecord_List.csv", i, 4, newContactNumber);
                
                System.out.println("contact information has been updated successfully");
                System.out.println();
                return;
            }
            i++;

        }
        //if cannot find the MedicalRecord based on patientID
        System.out.println("Medical Record cannot be found. Key in Patient ID again.");
        System.out.println();    
    }

}
