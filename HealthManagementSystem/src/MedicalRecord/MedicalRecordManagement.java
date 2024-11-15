package MedicalRecord;

/**
 * The {@code MedicalRecordManagement} class serves as a high-level module for managing medical records.
 * It adheres to the Dependency Inversion Principle (DIP) by depending on the abstraction {@code MedicalRecordService}.
 * This class delegates operations such as viewing and updating medical records to the provided service implementation.
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class MedicalRecordManagement {
    
    private MedicalRecordService medicalRecordService;

    /**
     * Constructs a {@code MedicalRecordManagement} instance with the specified medical record service.
     * 
     * @param service the {@code MedicalRecordService} implementation used for medical record operations
     */
    public MedicalRecordManagement(MedicalRecordService service) {
        medicalRecordService = service;
    }

    /**
     * Delegates the task of viewing a medical record to the medical record service.
     * 
     * @param patientID the unique identifier of the patient whose medical record is to be viewed
     */
    public void viewMedicalRecord(String patientID) {
        medicalRecordService.viewMedicalRecord(patientID);
    }

    /**
     * Delegates the task of updating a medical record to the medical record service.
     * 
     * @param patientID   the unique identifier of the patient whose medical record is to be updated
     * @param information1 the first piece of information to be updated (e.g., diagnosis)
     * @param information2 the second piece of information to be updated (e.g., treatment)
     */
    public void updateMedicalRecord(String patientID, String information1, String information2) {
        medicalRecordService.updateMedicalRecord(patientID, information1, information2);
    }
}
