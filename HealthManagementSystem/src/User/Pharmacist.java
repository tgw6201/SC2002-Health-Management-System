package User;

import Appointment.AppointmentOutcomeManager;
import Inventory.InventoryManagement;
import Inventory.PrescriptionManagement;
import java.util.Scanner;

public class Pharmacist {
    private final AppointmentOutcomeManager appointmentOutcomeManager;
    private final InventoryManagement inventoryManagement;
    private final PrescriptionManagement prescriptionManagement;
    private final Scanner sc;
    private final String pharmacistID;

    // Constructor to initialize Pharmacist with associated managers, services, and pharmacist ID
    public Pharmacist(String pharmacistID, AppointmentOutcomeManager appointmentOutcomeManager, 
                      InventoryManagement inventoryManagement, PrescriptionManagement prescriptionManagement) {
        this.pharmacistID = pharmacistID;
        this.appointmentOutcomeManager = appointmentOutcomeManager;
        this.inventoryManagement = inventoryManagement;
        this.prescriptionManagement = prescriptionManagement; // Initialize prescriptionManagement
        this.sc = new Scanner(System.in); // Optionally pass a Scanner for testing
    }

    // Main menu for pharmacist to select different options
    public void Menu() {
        int choice = -1;
        while (choice != 5) { // Loop until the pharmacist chooses to log out
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Logout");

            try {
                choice = Integer.parseInt(sc.nextLine().trim()); // Parse choice as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue; // Skip this iteration and prompt again
            }

            // Execute the selected option
            switch (choice) {
                case 1:
                    viewAppointmentOutcomeRecord();
                    break;
                case 2:
                    updatePrescriptionStatus();
                    break;
                case 3:
                    viewMedicationInventory();
                    break;
                case 4:
                    submitReplenishmentRequest();
                    break;
                case 5:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // View a specific appointment outcome record
    private void viewAppointmentOutcomeRecord() {
        System.out.println("Enter Appointment Outcome ID to view:");
        String appointmentOutcomeID = sc.nextLine();
        appointmentOutcomeManager.viewOutcomeRecord(appointmentOutcomeID); // Calls AppointmentOutcomeManager to retrieve records
    }

    // Update prescription status or dispense items
    private void updatePrescriptionStatus() {
        System.out.println("Choose dispensing method:");
        System.out.println("1. Dispense by Appointment Outcome ID");
        System.out.println("2. Dispense by Medicine Name");
        System.out.println("3. Handle All Pending Prescriptions");

        int dispenseOption;
        try {
            dispenseOption = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter 1, 2 or 3.");
            return;
        }

        switch (dispenseOption) {
            case 1:
                dispenseItemByAppointmentID();
                break;
            case 2:
                dispenseItemByMedicineName();
                break;
            case 3:
                handleAllPending();
                break;
            default:
                System.out.println("Invalid dispensing option. Please enter 1 or 2.");
        }
    }

    // Dispense item based on an appointment outcome ID
    private void dispenseItemByAppointmentID() {
        System.out.println("Enter Appointment Outcome ID for dispensing:");
        String appointmentOutcomeID = sc.nextLine();
        prescriptionManagement.dispenseItemByAppointmentID(appointmentOutcomeID); // Calls InventoryManagement to dispense item by appointment ID
    }

    // Dispense a specified quantity of a medicine by its name
    private void dispenseItemByMedicineName() {
        System.out.println("Enter Medicine Name to dispense:");
        String medicineName = sc.nextLine();

        System.out.println("Enter quantity to dispense:");
        int quantity;
        try {
            quantity = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for quantity.");
            return;
        }

        prescriptionManagement.dispenseItemByMedicineName(medicineName, quantity); // Calls InventoryManagement to dispense by medicine name
    }

    private void handleAllPending(){
        prescriptionManagement.handleAllPending(pharmacistID);
    }


    // View the current medication inventory
    private void viewMedicationInventory() {
        inventoryManagement.viewItems(); // Calls InventoryManagement to display inventory items
    }

    // Submit a request to replenish medication inventory
    private void submitReplenishmentRequest() {
        System.out.println("Enter the name of the item to replenish:");
        String itemName = sc.nextLine();

        System.out.println("Enter the quantity to request:");
        int quantity;
        try {
            quantity = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number for quantity.");
            return;
        }

        inventoryManagement.submitReplenishmentRequest(itemName, quantity, pharmacistID); // Calls InventoryManagement to submit request
    }
}
