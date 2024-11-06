package Inventory;

public interface DispensingService {
    void dispenseItemByAppointmentID(String appointmentOutcomeID);
    boolean dispenseItemByMedicineName(String medicineName, int quantity);
}
