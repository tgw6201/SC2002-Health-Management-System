package Appointment;

public interface ViewAppointment {

    public void viewScheduleAppointment(String patientID);

    public void viewPersonalSchedule(String doctorID);

    public void viewUpcomingAppointments(String DoctorID);

    public void viewAppointmentDetails(String appointmentID);
     
}
