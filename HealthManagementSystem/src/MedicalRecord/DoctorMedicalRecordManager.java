package MedicalRecord;

import java.util.List;
import FileManager.*;

/**
 * The {@code DoctorMedicalRecordManager} class provides methods for viewing and updating 
 * medical records in the system. This class utilizes a {@code DataProcessor} to handle 
 * reading and writing of medical records from/to a CSV file.
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class DoctorMedicalRecordManager implements MedicalRecordService {

    private DataProcessor dataProcessor;

    /**
     * Constructs a {@code DoctorMedicalRecordManager} with the specified data reader and writer.
     * 
     * @param reader the {@code dataReader} used to read medical records
     * @param writer the {@code dataWriter} used to write medical records
     */
    public DoctorMedicalRecordManager(dataReader reader, dataWriter writer) {
        dataProcessor = new DataProcessor(reader, writer);
    }

    /**
     * Displays the medical record details for a patient specified by the patient ID.
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
                // Display medical record
                System.out.println("Medical Record: ");
                for (int i = 0; i < row.length; i++) {
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
                return;
            }
        }
        // If patientID does not match any medical record
        System.out.println("Medical Record cannot be found. Key in Patient ID again.");
    }

    /**
     * Updates the medical record for a patient with a new diagnosis and treatment plan.
     * 
     * @param patientID     the unique identifier of the patient whose medical record is to be updated
     * @param newDiagnosis  the new diagnosis for the patient
     * @param newTreatment  the new treatment plan for the patient
     */
    public void updateMedicalRecord(String patientID, String newDiagnosis, String newTreatment) {
        List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

        String newDiagnosisAndTreatment = "    Diagnosis: " + newDiagnosis + "    " + "Treatment: " + newTreatment;
        
        // Track row index
        int i = 0;
        for (String[] row : medicalRecordListCsv) {
            if (row[0].equalsIgnoreCase(patientID)) {
                // Get old record
                String pastDiagnosisAndTreatment = row[7];

                // Update diagnosis and treatment
                String diagnosisAndTreatment = pastDiagnosisAndTreatment + newDiagnosisAndTreatment;
                dataProcessor.writeData("MedicalRecord_List.csv", i, 7, diagnosisAndTreatment);
                System.out.println("Diagnosis and treatment plan has been updated successfully.");
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
