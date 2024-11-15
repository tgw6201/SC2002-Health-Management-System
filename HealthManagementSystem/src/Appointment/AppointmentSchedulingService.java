package Appointment;


/**
 * The AppointmentSchedulingService interface provides methods for scheduling,
 * rescheduling, canceling, and managing appointments.
 * 
 * <p>
 * This interface defines core functionalities required for handling appointments,
 * including scheduling new appointments, modifying existing ones, and managing 
 * appointment status changes.
 * </p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public interface AppointmentSchedulingService {

    /**
     * Schedules a new appointment for a patient based on the specified slot ID.
     * 
     * @param patientID The ID of the patient.
     * @param appointmentSlotID The ID of the appointment slot to be booked.
     */
    public void scheduleAppointment(String patientID, String appointmentSlotID);


    /**
     * Reschedules an existing appointment to a new slot.
     * 
     * @param appointmentID The ID of the appointment to be rescheduled.
     * @param newAppointmentSlotID The ID of the new appointment slot.
     */
    public void rescheduleAppointment(String appointmentID, String newAppointmentSlotID);
    
    
    /**
     * Cancels an existing appointment.
     * 
     * @param appointmentID The ID of the appointment to be canceled.
     */
    public void cancelAppointment(String appointmentID);

    
    /**
     * Allows a doctor to accept or decline an appointment.
     * 
     * @param appointmentID The ID of the appointment.
     * @param decision The decision of the doctor ("Accept" or "Decline").
     */
    public void acceptDeclineAppointment(String appointmentID, String decision);
}
