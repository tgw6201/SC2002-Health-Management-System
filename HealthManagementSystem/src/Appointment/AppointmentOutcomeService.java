package Appointment;

public interface AppointmentOutcomeService {
    
    public void recordAppointmentOutcome(String typeOfService, String prescribedMedication, String prescribedMedicationQuantity, String prescriptionStatus, String consultationNotes, String appointmentID);
    
    public void viewPastRecords(String patientID);
    
    public void viewOutcomeRecord(String appointmentOutcomeID);

}
