
import Appointment.*;
import FileManager.dataReader;
import FileManager.dataWriter;
import Inventory.*;
import Logger.*;
import java.util.Scanner;

/**
 * Represents a Pharmacist in the medical management system.
 * Provides functionality for viewing and updating prescriptions, managing medication inventory,
 * and handling appointment outcome records.
 * Utilizes various managers for appointment, inventory, and prescription handling.
 *
 * @author Tee Yu Xuan
 * @version 1.0
 * @since 2024-11-6
 */
public class Pharmacist {
    private final String userID;
    private final String userRole;
    private final Logger logger;
    private final dataReader reader;
    private final dataWriter writer;
    private final Scanner sc = new Scanner(System.in);
    private final AppointmentOutcomeManager appointmentOutcomeManager;
    private final InventoryManagement inventoryManagement;
    private final PrescriptionManagement prescriptionManagement;
    private final InventoryManager  inventoryManager;
    private final PrescriptionManager prescriptionManager;

    /**
     * Constructs a Pharmacist object with the specified user information and data managers.
     *
     * @param userID   the unique identifier for the pharmacist.
     * @param userRole the role of the user (expected to be "Pharmacist").
     * @param logger   the logging utility for recording actions.
     * @param reader   the data reader for retrieving information.
     * @param writer   the data writer for saving information.
     */
    public Pharmacist(String userID, String userRole, Logger logger, dataReader reader, dataWriter writer) {
        this.userID = userID;
        this.userRole = userRole;
        this.logger = logger;
        this.reader = reader;
        this.writer = writer;

        this.appointmentOutcomeManager = new AppointmentOutcomeManager(reader, writer);
        this.inventoryManagement = new InventoryManagement(reader, writer);
        this.prescriptionManagement = new PrescriptionManagement(inventoryManagement, reader, writer);
        this.inventoryManager = new InventoryManager(inventoryManagement, reader, writer);
        this.prescriptionManager = new PrescriptionManager(prescriptionManagement, reader, writer);
    }

    /**
     * Displays the main menu for the pharmacist to perform various tasks.
     * Logs the pharmacist's actions and processes menu choices in a loop.
     */
    public void menu() {
        logger.log("Pharmacist " + userID + " logged in");
        int choice = -1;
        while (choice != 6) {
            System.out.println("Pharmacist Menu:");
            System.out.println("1. View Appointment Outcome Record");
            System.out.println("2. Update Prescription Status");
            System.out.println("3. View Medication Inventory");
            System.out.println("4. Submit Replenishment Request");
            System.out.println("5. Dispense Medication Directly (No Appointment Required)");
            System.out.println("6. Logout");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                continue;
            }

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
                    dispenseItemByMedicineName();
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    /**
     * Views a specific appointment outcome record.
     * Allows the pharmacist to choose between viewing a specific record or all records.
     */
    private void viewAppointmentOutcomeRecord() {
        System.out.println("Choose viewing method");
        System.out.println("1. View Appointment Outcome Record by Appointment Outcome ID");
        System.out.println("2. View All Appointment Outcome Record");

        int viewingOption;
        try {
            viewingOption = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            return;
        }

        switch (viewingOption) {
            case 1:
                System.out.println("Enter Appointment Outcome ID to view:");
                String appointmentOutcomeID = sc.nextLine();
                logger.log("Pharmacist " + userID + " viewed appointment outcome record by ID: " + appointmentOutcomeID);
                appointmentOutcomeManager.viewOutcomeRecord(appointmentOutcomeID);
                break;
            case 2:
                logger.log("Pharmacist " + userID + " viewed all appointment outcome records");
                prescriptionManager.showAllAppointments();
                break;
            default:
                System.out.println("Invalid option. Please enter 1 or 2.");
        }
    }

    /**
     * Updates the prescription status or dispenses items based on appointment outcome records.
     */
    private void updatePrescriptionStatus() {
        System.out.println("Choose dispensing method:");
        System.out.println("1. Dispense by Appointment Outcome ID");
        System.out.println("2. View All Pending Prescriptions and Dispense");

        int dispenseOption;
        try {
            dispenseOption = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter 1 or 2.");
            return;
        }

        switch (dispenseOption) {
            case 1:
                dispenseItemByAppointmentID();
                break;
            case 2:
                logger.log("Pharmacist " + userID + " viewed all pending prescriptions before dispensing");
                prescriptionManager.viewPendingItems();

                System.out.println("Do you want to dispense all pending items? (yes/no): ");
                String confirmation = sc.nextLine().trim().toLowerCase();

                if ("yes".equals(confirmation)) {
                    prescriptionManager.handleAllPending(userID);
                    logger.log("Pharmacist " + userID + " dispensed all pending prescriptions");
                }
                break;
            default:
                System.out.println("Invalid dispensing option. Please enter 1 or 2.");
        }
    }

    /**
     * Dispenses an item based on an appointment outcome ID.
     * Prompts the pharmacist for the appointment outcome ID and dispenses the corresponding items.
     */
    private void dispenseItemByAppointmentID() {
        System.out.println("Enter Appointment Outcome ID for dispensing:");
        String appointmentOutcomeID = sc.nextLine();
        logger.log("Pharmacist " + userID + " dispensing item by appointment outcome ID: " + appointmentOutcomeID);
        prescriptionManager.dispenseItemByAppointmentID(appointmentOutcomeID);
    }

    /**
     * Dispenses a specified quantity of a medicine by its name.
     * Prompts the pharmacist for the medicine name and quantity to dispense.
     */
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

        logger.log("Pharmacist " + userID + " dispensing " + quantity + " of " + medicineName);
        prescriptionManager.dispenseItemByMedicineName(medicineName, quantity);
    }

    /**
     * Views the current medication inventory.
     * Logs the action and retrieves the inventory from the inventory management system.
     */
    private void viewMedicationInventory() {
        logger.log("Pharmacist " + userID + " viewed medication inventory");
        inventoryManager.viewItems();
    }

    /**
     * Submits a request to replenish medication inventory.
     * Prompts the pharmacist for the item name and quantity to request.
     */
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

        logger.log("Pharmacist " + userID + " submitted replenishment request for " + quantity + " of " + itemName);
        inventoryManager.submitReplenishmentRequest(itemName, quantity, userID);
    }
}
