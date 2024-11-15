package Appointment;

/**
 * The AppointmentOutcomeService interface provides methods for recording
 * and viewing appointment outcomes and past records for patients.
 * 
 * <p>
 * This interface is designed to manage the outcome details of appointments,
 * including services provided, medications prescribed, and consultation notes.
 * It also allows for viewing past records and specific outcome records.
 * </p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public interface AppointmentOutcomeService {
    
    
    /**
     * Records the outcome of an appointment, including details about the service provided,
     * prescribed medication, and consultation notes.
     * 
     * @param typeOfService              The type of service provided during the appointment.
     * @param prescribedMedication       The medication prescribed during the appointment.
     * @param prescribedMedicationQuantity The quantity of the prescribed medication.
     * @param prescriptionStatus         The status of the prescription (e.g., "Fulfilled", "Pending").
     * @param consultationNotes          Notes from the consultation.
     * @param appointmentID              The ID of the appointment for which the outcome is being recorded.
     */
    public void recordAppointmentOutcome(String typeOfService, String prescribedMedication, String prescribedMedicationQuantity, String prescriptionStatus, String consultationNotes, String appointmentID);
    

    /**
     * Views all past appointment records for a specific patient.
     * 
     * @param patientID The ID of the patient whose past records are to be viewed.
     */
    public void viewPastRecords(String patientID);


    /**
     * Views the outcome record for a specific appointment outcome ID.
     * 
     * @param appointmentOutcomeID The ID of the appointment outcome record to view.
     */
    public void viewOutcomeRecord(String appointmentOutcomeID);

}
