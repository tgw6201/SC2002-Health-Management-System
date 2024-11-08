package Appointment2;

public interface AppointmentSlotService {

     //update availability --> existing appointmentslot / scheduled appointment
    public void setAppointmentAvailability(String appointmentSlotID, String availability);

    //create an appointmentSlot
    public void setAppointmentAvailability(String doctorID, String doctorName, String appointmentDate, String appointmentTime, String availability);

    //view appointmentslots --> updated to only view available slots
    public void viewAvailableAppointmentSlots();

}
