package Appointment;


/**
 * The ViewAppointment interface provides methods for viewing appointments 
 * and schedules for both patients and doctors.
 * 
 * <p>
 * This interface defines functionalities to view scheduled appointments, 
 * personal schedules for doctors, upcoming appointments, and detailed 
 * information about all appointments.
 * </p>
 * 
 * <p>
 * Implementing classes should provide the logic to interact with appointment
 * data and display relevant information to users.
 * </p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public interface ViewAppointment {


    /**
     * Views the scheduled appointments for a specific patient.
     * 
     * @param patientID The ID of the patient whose appointments are to be viewed.
     */
    public void viewScheduleAppointment(String patientID);


    /**
     * Views the personal schedule of a specific doctor.
     * 
     * @param doctorID The ID of the doctor whose schedule is to be viewed.
     */
    public void viewPersonalSchedule(String doctorID);


    /**
     * Views all upcoming confirmed appointments for a specific doctor.
     * 
     * @param doctorID The ID of the doctor whose upcoming appointments are to be viewed.
     */   
    public void viewUpcomingAppointments(String DoctorID);


    /**
     * Views detailed information about all appointments, including their statuses.
     */
    public void viewAppointmentDetails();
     
}
