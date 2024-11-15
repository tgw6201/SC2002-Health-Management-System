package MedicalRecord;

import FileManager.*;
import java.util.*;

/**
 * The {@code PatientMedicalRecordManager} class provides functionality for patients to view 
 * and update their medical records. This class interacts with a {@code DataProcessor} to 
 * handle reading and writing operations on the medical record CSV file.
 * 
 * <p>Adheres to the {@code MedicalRecordService} interface to ensure a consistent 
 * approach to managing medical records.</p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class PatientMedicalRecordManager implements MedicalRecordService {

    private DataProcessor dataProcessor;

    /**
     * Constructs a {@code PatientMedicalRecordManager} with the specified data reader and writer.
     * 
     * @param reader the {@code dataReader} used to read medical records
     * @param writer the {@code dataWriter} used to write medical records
     */
    public PatientMedicalRecordManager(dataReader reader, dataWriter writer) {
        dataProcessor = new DataProcessor(reader, writer);
    }

    /**
     * Allows a patient to view their medical record by specifying their patient ID.
     * 
     * @param patientID the unique identifier of the patient whose medical record is to be viewed
     */
    public void viewMedicalRecord(String patientID) {
        List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");
        
        // Labels for formatting
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

        // Print out medical record details
        for (String[] row : medicalRecordListCsv) {
            // Check patientID
            if (row[0].equalsIgnoreCase(patientID)) {
                System.out.println("Medical Record: ");
                for (int i = 0; i < row.length; i++) {
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
                return;               
            }
        }
        // If patientID doesn't match any record
        System.out.println("Medical Record cannot be found. Key in Patient ID again.");
    }

    /**
     * Allows a patient to update their personal contact information in the medical record.
     * 
     * @param patientID       the unique identifier of the patient whose information is to be updated
     * @param newEmailAddress the new email address of the patient
     * @param newContactNumber the new contact number of the patient
     */
    public void updateMedicalRecord(String patientID, String newEmailAddress, String newContactNumber) {
        List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

        // Row index tracker
        int i = 0;

        for (String[] row : medicalRecordListCsv) {
            // Check patientID
            if (row[0].equalsIgnoreCase(patientID)) {
                // Update email address
                dataProcessor.writeData("MedicalRecord_List.csv", i, 5, newEmailAddress);
                
                // Update contact number
                dataProcessor.writeData("MedicalRecord_List.csv", i, 4, newContactNumber);
                
                System.out.println("Contact information has been updated successfully.");
                System.out.println();
                return;
            }
            i++;
        }
        // If medical record cannot be found
        System.out.println("Medical Record cannot be found. Key in Patient ID again.");
        System.out.println();
    }
}
