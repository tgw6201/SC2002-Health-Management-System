package Appointment;

import java.util.List;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;

public class MedicalRecordManager {

    private List<String[]> medicalRecordListCsv;

    //should this be private (attribute)?
    CsvFileReader csvFileReader = new CsvFileReader();
    CsvFileWriter csvFileWriter = new CsvFileWriter();     

    public MedicalRecordManager(){
        medicalRecordListCsv = csvFileReader.readData("MedicalRecord_List.csv");
    }

    //view Medical Record --> patient use + doctor 

    public void viewMedicalRecord(String patientID){

        medicalRecordListCsv = csvFileReader.readData("MedicalRecord_List.csv");
        
        //labels for formatting 
        String[] labels = {
            "Patient ID: ", 
            "Name: ",
            "Date Of Birth: ",
            "Gender: ",
            "Phone Number: ",
            "Email Address: ",
            "Blood Type: ",
            ""
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

    //update personal information --> patient use
    public void updatePersonalInformation(String patientID, String newEmailAddress, String newContactNumber){
        medicalRecordListCsv = csvFileReader.readData("MedicalRecord_List.csv");

        //i to keep track of the row 
        int i = 0;

        for(String [] row : medicalRecordListCsv){

            //check patientID
            if(row[0].equalsIgnoreCase(patientID)){
                //update email 
                csvFileWriter.writeData("MedicalRecord_List.csv", i, 5, newEmailAddress);
                
                //update contact number
                csvFileWriter.writeData("MedicalRecord_List.csv", i, 4, newContactNumber);
                
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

    public void updatePatientMedicalRecords(String patientID, String newDiagnosis, String newTreatment){
        medicalRecordListCsv = csvFileReader.readData("MedicalRecord_List.csv");

        String newDiagnosisAndTreatment = "    Diagnosis: " + newDiagnosis+ "    " + "Treatment: " + newTreatment;
        
        //i to keep track of row
        int i = 0;
        for(String [] row : medicalRecordListCsv){
            
            if(row[0].equalsIgnoreCase(patientID)){
                //get old record
                String pastDiagnosisandTreatment = row[7];

                //update diagnosisandtreament
                String diagnosisAndTreatment = pastDiagnosisandTreatment + newDiagnosisAndTreatment;
                csvFileWriter.writeData("MedicalRecord_List.csv", i, 7, diagnosisAndTreatment);
                System.out.println("Diagnosis and treament plan has been updated succeessfully.");
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
