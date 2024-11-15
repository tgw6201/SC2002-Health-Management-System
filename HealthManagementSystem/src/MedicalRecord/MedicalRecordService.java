package MedicalRecord;

/**
 * The {@code MedicalRecordService} interface defines the operations for managing medical records.
 * Both doctors and patients can utilize these operations to view and update medical records.
 * This interface adheres to the Dependency Inversion Principle (DIP) by serving as an abstraction
 * for medical record management.
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public interface MedicalRecordService {

    /**
     * Allows viewing of a medical record for the specified patient ID.
     * This operation can be performed by both doctors and patients.
     * 
     * @param patientID the unique identifier of the patient whose medical record is to be viewed
     */
    public void viewMedicalRecord(String patientID);

    /**
     * Allows updating of a medical record with new information for the specified patient ID.
     * This operation can be performed by both doctors and patients.
     * 
     * @param patientID   the unique identifier of the patient whose medical record is to be updated
     * @param information1 the first piece of information to be updated (e.g., diagnosis or other details)
     * @param information2 the second piece of information to be updated (e.g., treatment or other details)
     */
    public void updateMedicalRecord(String patientID, String information1, String information2);

}

