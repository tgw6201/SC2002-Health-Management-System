package Inventory;

import FileManager.*;

/**
 * The PrescriptionManager class provides a simplified interface for managing prescription-related operations.
 * It acts as a wrapper for the {@link PrescriptionManagement} class, delegating functionality to manage
 * prescriptions, dispense medicines, and handle pending prescription requests.
 *
 * <p>This class simplifies the usage of {@link PrescriptionManagement} by exposing key operations 
 * such as viewing pending items, handling pending requests, and dispensing medicines.</p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 * PrescriptionManagement prescriptionManagement = new PrescriptionManagement(inventoryManagement, reader, writer);
 * PrescriptionManager manager = new PrescriptionManager(prescriptionManagement);
 *
 * manager.viewPendingItems();
 * manager.handleAllPending("Admin123");
 * manager.dispenseItemByAppointmentID("appointment001");
 * boolean dispensed = manager.dispenseItemByMedicineName("Paracetamol", 30);
 * System.out.println("Medicine dispensed: " + dispensed);
 * manager.showAllAppointments();
 * </pre>
 *
 * @see PrescriptionManagement
 * 
 * @author  Sia Yi Zhen
 * @version  1.0
 * @since 2024-11-17
 */
public class PrescriptionManager {

    private final PrescriptionManagement prescriptionManagement;
    private final DataProcessor dataProcessor;

    /**
     * Constructs a PrescriptionManager with the specified PrescriptionManagement instance.
     *
     * @param prescriptionManagement The {@link PrescriptionManagement} instance used to delegate operations.
     */
    public PrescriptionManager(PrescriptionManagement prescriptionManagement, dataReader dataReader, dataWriter dataWriter) {
        dataProcessor = new DataProcessor(dataReader, dataWriter);
        this.prescriptionManagement = prescriptionManagement;
    }

    /**
     * Displays all pending prescriptions by delegating to {@link PrescriptionManagement#viewPendingItems()}.
     */
    public void viewPendingItems() {
        prescriptionManagement.viewPendingItems();
    }

    /**
     * Handles and processes all pending prescriptions by delegating to 
     * {@link PrescriptionManagement#handleAllPending(String)}.
     *
     * @param approvedBy The ID of the individual approving the prescriptions.
     */
    public void handleAllPending(String approvedBy) {
        prescriptionManagement.handleAllPending(approvedBy);
    }

    /**
     * Dispenses a prescription item based on the appointment ID by delegating to 
     * {@link PrescriptionManagement#dispenseItemByAppointmentID(String)}.
     *
     * @param appointmentID The unique identifier for the appointment to dispense.
     */
    public void dispenseItemByAppointmentID(String appointmentID) {
        prescriptionManagement.dispenseItemByAppointmentID(appointmentID);
    }

    /**
     * Dispenses a specified quantity of a medicine by its name by delegating to 
     * {@link PrescriptionManagement#dispenseItemByMedicineName(String, int)}.
     *
     * @param medicineName The name of the medicine to dispense.
     * @param quantity     The quantity of the medicine to dispense.
     * @return {@code true} if the medicine was successfully dispensed; {@code false} otherwise.
     */
    public boolean dispenseItemByMedicineName(String medicineName, int quantity) {
        return prescriptionManagement.dispenseItemByMedicineName(medicineName, quantity);
    }

    /**
     * Displays all appointment outcome records by delegating to {@link PrescriptionManagement#showAllAppointments()}.
     */
    public void showAllAppointments() {
        prescriptionManagement.showAllAppointments();
    }
}
