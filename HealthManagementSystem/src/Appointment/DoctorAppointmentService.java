package Appointment;

public interface DoctorAppointmentService {

    //Accept or decline appointment
    public void acceptDeclineAppointment(String appointmentID, String decision);

    //view personal schedule
    public void viewPersonalSchedule(String doctorID);

    //view upcoming confirmed appointments
    public void viewUpcomingAppointments(String DoctorID);
    
}
