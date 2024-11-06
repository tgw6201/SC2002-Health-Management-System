package Inventory;

import FileManager.*;
import java.util.Arrays;
import java.util.List;

public class PrescriptionManagement implements DispensingService, PendingManagement {

    private final InventoryManagement inventoryManagement;
    CsvFileWriter csvFileWriter = new CsvFileWriter();
    CsvFileReader csvFileReader = new CsvFileReader();

    public PrescriptionManagement(InventoryManagement inventoryManagement) {
        this.inventoryManagement = inventoryManagement;
    }

    @Override
    public void viewPendingItems() {
        List<String[]> prescriptions = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
        System.out.println("\nDisplaying all pending prescriptions:\n");
        for (String[] record : prescriptions) {
            if ("Pending".equalsIgnoreCase(record[6])) {
                System.out.println(Arrays.toString(record));
            }
        }
    }

    @Override
    public void handleAllPending(String approvedBy) {
        List<String[]> prescriptions = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
        for (int i = 0; i < prescriptions.size(); i++) {
            String[] prescription = prescriptions.get(i);
            if ("Pending".equalsIgnoreCase(prescription[6])) {
                dispenseItemByMedicineName(prescription[4], Integer.parseInt(prescription[5]));
                prescription[6] = "Dispensed";
                csvFileWriter.writeRow("AppointmentOutcomeRecord_List.csv", i, Arrays.asList(prescription));
                System.out.println("Processed pending prescription for ID: " + prescription[0]);
            }
        }
    }

    @Override
    public void dispenseItemByAppointmentID(String appointmentID) {
        List<String[]> prescriptions = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
        for (int i = 0; i < prescriptions.size(); i++) {
            String[] prescription = prescriptions.get(i);
            if (prescription[0].equals(appointmentID) && "Pending".equalsIgnoreCase(prescription[6])) {
                boolean success = dispenseItemByMedicineName(prescription[4], Integer.parseInt(prescription[5]));
                if (success) {
                    prescription[6] = "Dispensed";
                    csvFileWriter.writeRow("AppointmentOutcomeRecord_List.csv", i, Arrays.asList(prescription));
                    System.out.println("Medicine dispensed successfully for appointment ID " + appointmentID);
                } else {
                    System.out.println("Failed to dispense medicine for appointment ID " + appointmentID);
                }
                return;
            }
        }
        System.out.println("No pending prescription found with appointment ID " + appointmentID);
    }

    @Override
    public boolean dispenseItemByMedicineName(String medicineName, int quantity) {
        boolean success = inventoryManagement.restockItemByName(medicineName, -quantity);
        if (success) {
            inventoryManagement.checkLowStockForItem(medicineName); // Check if stock is low after dispensing
        }
        return success;
    }

    // Method to display all records in AppointmentOutcomeRecord_List.csv
    public void showAllAppointments() {
        List<String[]> appointments = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
        System.out.println("\nDisplaying all appointment outcome records:\n");

        for (String[] record : appointments) {
            System.out.println(Arrays.toString(record));
        }
    }
}
