package Appointment;

public interface AppointmentSchedulingService {
    public void scheduleAppointment(String patientID, String appointmentSlotID);

    public void rescheduleAppointment(String appointmentID, String newAppointmentSlotID);

    public void cancelAppointment(String appointmentID);

    public void acceptDeclineAppointment(String appointmentID, String decision);
}
