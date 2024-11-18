package Inventory;

import FileManager.*;

/**
 * The PrescriptionManager class provides a streamlined interface for managing prescription-related operations
 * through the {@link DispensingService} and {@link PendingManagement} interfaces.
 *
 * <p>This class enables users to view pending prescriptions, handle pending requests, 
 * and dispense medicines using high-level operations.</p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 * dataReader reader = new CsvFileReader();
 * dataWriter writer = new CsvFileWriter();
 * InventoryManagement inventoryManagement = new InventoryManagement(reader, writer);
 * PrescriptionManagement prescriptionManagement = new PrescriptionManagement(inventoryManagement, reader, writer);
 * PrescriptionManager manager = new PrescriptionManager(prescriptionManagement, prescriptionManagement, reader, writer);
 *
 * manager.viewPendingItems();
 * manager.handleAllPending("Admin123");
 * manager.dispenseItemByAppointmentID("appointment001");
 * boolean dispensed = manager.dispenseItemByMedicineName("Paracetamol", 30);
 * System.out.println("Medicine dispensed: " + dispensed);
 * </pre>
 *
 * @see DispensingService
 * @see PendingManagement
 * @see DataProcessor
 * @see dataReader
 * @see dataWriter
 * 
 * @author Sia Yi Zhen
 * @version 1.1
 * @since 2024-11-18
 */
public class PrescriptionManager {

    private final DispensingService dispensingService;
    private final PendingManagement pendingManagement;
    private final DataProcessor dataProcessor;

    /**
     * Constructs a PrescriptionManager with the specified service interfaces and file processing utilities.
     *
     * @param dispensingService An instance of {@link DispensingService} to handle dispensing tasks.
     * @param pendingManagement An instance of {@link PendingManagement} to manage pending prescriptions.
     * @param dataReader        A {@link dataReader} instance for reading prescription data.
     * @param dataWriter        A {@link dataWriter} instance for writing prescription data.
     */
    public PrescriptionManager(DispensingService dispensingService, PendingManagement pendingManagement, dataReader dataReader, dataWriter dataWriter) {
        this.dispensingService = dispensingService;
        this.pendingManagement = pendingManagement;
        this.dataProcessor = new DataProcessor(dataReader, dataWriter);
    }

    /**
     * Displays all pending prescriptions using {@link PendingManagement#viewPendingItems()}.
     */
    public void viewPendingItems() {
        pendingManagement.viewPendingItems();
    }

    /**
     * Processes all pending prescriptions using {@link PendingManagement#handleAllPending(String)}.
     *
     * @param approvedBy The ID of the individual approving the prescriptions.
     */
    public void handleAllPending(String approvedBy) {
        pendingManagement.handleAllPending(approvedBy);
    }

    /**
     * Dispenses a prescription based on the appointment ID using {@link DispensingService#dispenseItemByAppointmentID(String)}.
     *
     * @param appointmentID The unique identifier for the appointment to dispense.
     */
    public void dispenseItemByAppointmentID(String appointmentID) {
        dispensingService.dispenseItemByAppointmentID(appointmentID);
    }

    /**
     * Dispenses a specified quantity of medicine by its name using {@link DispensingService#dispenseItemByMedicineName(String, int)}.
     *
     * @param medicineName The name of the medicine to dispense.
     * @param quantity     The quantity of the medicine to dispense.
     * @return {@code true} if the medicine was successfully dispensed; {@code false} otherwise.
     */
    public boolean dispenseItemByMedicineName(String medicineName, int quantity) {
        return dispensingService.dispenseItemByMedicineName(medicineName, quantity);
    }

    /**
     * Displays all appointment outcome records. This method utilizes the {@link DispensingService#showAllAppointments()}
     * to access and display all appointment records.
     *
     */
    public void showAllAppointments() {
        dispensingService.showAllAppointments();
    }
}
