package MedicalRecord;

import java.util.List;

import FileManager.*;

public class DoctorMedicalRecordManager implements MedicalRecordService{

    private DataProcessor dataProcessor;

    public DoctorMedicalRecordManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
    }

     //view Medical Record --> doctor use
     public void viewMedicalRecord(String patientID){

        List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");
        
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

    //updatePatientMedicalRecords
    public void updateMedicalRecord(String patientID, String newDiagnosis, String newTreatment){
        List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

        String newDiagnosisAndTreatment = "    Diagnosis: " + newDiagnosis+ "    " + "Treatment: " + newTreatment;
        
        //i to keep track of row
        int i = 0;
        for(String [] row : medicalRecordListCsv){
            
            if(row[0].equalsIgnoreCase(patientID)){
                //get old record
                String pastDiagnosisandTreatment = row[7];

                //update diagnosisandtreament
                String diagnosisAndTreatment = pastDiagnosisandTreatment + newDiagnosisAndTreatment;
                dataProcessor.writeData("MedicalRecord_List.csv", i, 7, diagnosisAndTreatment);
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