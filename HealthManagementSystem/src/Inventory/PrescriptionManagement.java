package Inventory;

import FileManager.*;
import java.util.Arrays;
import java.util.List;

/**
 * The PrescriptionManagement class handles dispensing and approval of prescriptions.
 * 
 * <p>
 * This class implements both {@link DispensingService} and {@link PendingManagement}
 * interfaces, enabling it to dispense items based on prescription requests and manage
 * pending prescriptions for approval.
 * </p>
 * 
 * <p>
 * Prescription data is managed using {@link dataProcessor},
 * allowing the system to track, dispense, and approve prescriptions as needed.
 * </p>
 * 
 * @see DispensingService
 * @see PendingManagement
 * @see InventoryManagement
 * @see dataProcessor
 * @see dataProcessor
 * 
 * 
 * @author  Sia Yi Zhen
 * @version  1.0
 * @since 2024-11-5
 * 
 */

public class PrescriptionManagement implements DispensingService, PendingManagement {

    private final InventoryManagement inventoryManagement;
    private final DataProcessor dataProcessor;

     /**
     * Constructs a PrescriptionManagement instance that interacts with InventoryManagement 
     * for stock management, linking inventory controls with prescription processing.
     *
     * @param inventoryManagement The InventoryManagement instance used for managing inventory items.
     */
    public PrescriptionManagement(InventoryManagement inventoryManagement, dataReader dataReader, dataWriter dataWriter) {
        dataProcessor = new DataProcessor(dataReader, dataWriter);
        this.inventoryManagement = inventoryManagement;
    }

    /**
     * Displays all pending prescriptions by reading from "AppointmentOutcomeRecord_List.csv".
     */
    @Override
    public void viewPendingItems() {
        List<String[]> prescriptions = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
        System.out.println("\nDisplaying all pending prescriptions:\n");
        for (String[] record : prescriptions) {
            if ("Pending".equalsIgnoreCase(record[6])) {
                System.out.println(Arrays.toString(record));
            }
        }
    }

    /**
     * Processes and approves all pending prescriptions by dispensing the requested items
     * if sufficient stock is available. Each prescription is marked as dispensed if processed.
     * 
     * <p>If the inventory does not have enough stock for a requested item, the dispensing 
     * is skipped for that item, and an insufficient stock message is displayed.</p>
     * 
     * @param approvedBy The name or ID of the individual responsible for approving the prescriptions.
     */
    @Override
    public void handleAllPending(String approvedBy) {
        List<String[]> prescriptions = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
        for (int i = 0; i < prescriptions.size(); i++) {
            String[] prescription = prescriptions.get(i);
            if ("Pending".equalsIgnoreCase(prescription[6])) {
                String medicineName = prescription[4];
                int requestedQuantity = Integer.parseInt(prescription[5]);
                
                // Check if the inventory has enough stock for the requested quantity
                int availableStock = inventoryManagement.getStockLevel(medicineName);
    
                if (availableStock >= requestedQuantity) {
                    // Sufficient stock is available, proceed with dispensing
                    dispenseItemByMedicineName(medicineName, requestedQuantity);
                    prescription[6] = "Dispensed";
                    dataProcessor.writeRow("AppointmentOutcomeRecord_List.csv", i, Arrays.asList(prescription));
                    System.out.println("Processed pending prescription for ID: " + prescription[0]);
                } else {
                    // Insufficient stock, skip or partially dispense
                    System.out.println("Insufficient stock for " + medicineName + ". Requested: " + requestedQuantity + ", Available: " + availableStock);
                }
            }
        }
    }

    /**
     * Dispenses an item based on a unique appointment ID, updating its status to indicate
     * that the item has been dispensed if sufficient stock is available.
     * 
     * <p>If the inventory does not have enough stock for the requested item, the dispensing 
     * is skipped, and an insufficient stock message is displayed.</p>
     * 
     * @param appointmentID The unique identifier for the appointment to dispense an item.
     */
    @Override
    public void dispenseItemByAppointmentID(String appointmentID) {
        List<String[]> prescriptions = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
        for (int i = 0; i < prescriptions.size(); i++) {
            String[] prescription = prescriptions.get(i);
            if (prescription[0].equals(appointmentID) && "Pending".equalsIgnoreCase(prescription[6])) {
                String medicineName = prescription[4];
                int requestedQuantity = Integer.parseInt(prescription[5]);

                // Check if enough stock is available before dispensing
                int availableStock = inventoryManagement.getStockLevel(medicineName);
                if (availableStock >= requestedQuantity) {
                    // Proceed with dispensing if stock is sufficient
                    boolean success = dispenseItemByMedicineName(medicineName, requestedQuantity);
                    if (success) {
                        prescription[6] = "Dispensed";
                        dataProcessor.writeRow("AppointmentOutcomeRecord_List.csv", i, Arrays.asList(prescription));
                        System.out.println("Medicine dispensed successfully for appointment ID " + appointmentID);
                    } else {
                        System.out.println("Failed to dispense medicine for appointment ID " + appointmentID);
                    }
                } else {
                    System.out.println("Insufficient stock for " + medicineName + ". Requested: " + requestedQuantity + ", Available: " + availableStock);
                }
                return;
            }
        }
        System.out.println("No pending prescription found with appointment ID " + appointmentID);
    }

    /**
     * Dispenses a specified quantity of a medicine by its name.
     * Reduces stock in the inventory if sufficient quantity is available, 
     * and checks if the stock is low after dispensing.
     * 
     * <p>If there is not enough stock for the requested quantity, the method does not dispense 
     * any quantity and returns {@code false}.</p>
     * 
     * @param medicineName The name of the medicine to dispense.
     * @param quantity The quantity of the medicine to dispense.
     * @return {@code true} if the item was successfully dispensed; 
     *         {@code false} if there was insufficient stock.
     */
    @Override
    public boolean dispenseItemByMedicineName(String medicineName, int quantity) {
        int availableStock = inventoryManagement.getStockLevel(medicineName);

        // Check if there is sufficient stock before dispensing
        if (availableStock < quantity) {
            System.out.println("Insufficient stock for " + medicineName + ". Requested: " + quantity + ", Available: " + availableStock);
            return false;
        }

        // Proceed with dispensing and check for low stock afterward
        boolean success = inventoryManagement.restockItemByName(medicineName, -quantity);
        if (success) {
            inventoryManagement.checkLowStockForItem(medicineName); // Check if stock is low after dispensing
        }
        return success;
    }

    /**
     * Displays all records from "AppointmentOutcomeRecord_List.csv", showing the outcome of each appointment.
     */
    public void showAllAppointments() {
        List<String[]> appointments = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
        System.out.println("\nDisplaying all appointment outcome records:\n");

        for (String[] record : appointments) {
            System.out.println(Arrays.toString(record));
        }
    }
}
