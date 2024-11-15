package Appointment;


/**
 * The AppointmentSlotService interface provides methods for managing
 * appointment slots, including updating availability, creating new slots,
 * and viewing available slots.
 * 
 * <p>
 * This interface defines the core functionalities required for handling 
 * appointment slot-related operations.
 * </p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public interface AppointmentSlotService {

    
    /**
     * Updates the availability of an existing appointment slot based on the slot ID.
     * 
     * @param appointmentSlotID The ID of the appointment slot to update.
     * @param availability      The new availability status (e.g., "Available", "Booked").
     */
    public void setAppointmentAvailability(String appointmentSlotID, String availability);


    /**
     * Creates a new appointment slot for a doctor with the specified details.
     * 
     * @param doctorID        The ID of the doctor.
     * @param doctorName      The name of the doctor.
     * @param appointmentDate The date of the appointment slot.
     * @param appointmentTime The time of the appointment slot.
     * @param availability    The availability status of the slot (e.g., "Available").
     */
    public void setAppointmentAvailability(String doctorID, String doctorName, String appointmentDate, String appointmentTime, String availability);

    
    /**
     * Displays all available appointment slots for patients.
     * Only slots marked as "Available" are displayed.
     */
    public void viewAvailableAppointmentSlots();

}
