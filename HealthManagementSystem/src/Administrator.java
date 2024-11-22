
import Appointment.AppointmentManager;
import FileManager.*;
import Inventory.*;
import Logger.*;
import StaffManagement.*;
import java.util.Scanner;

/**
 * The Administrator class provides the functionality for an administrator to manage various operations 
 * in a hospital system, including staff management, appointments, medication inventory, and replenishment requests.
 * The class interacts with various submodules like AppointmentManager, InventoryManager, PrescriptionManager, 
 * and StaffManagement to execute its functionalities.
 * 
 * @author Peter Loh
 * @version 1.0
 * @since 2024-11-6
 */
public class Administrator
{
    private String userID;
    private String userRole;
    private Logger logger;
    
    //Object pointer initialization
    private Scanner sc = new Scanner(System.in);
    private DataProcessor dataProcessor;
    private final InventoryManagement inventoryManagement;
    private final PrescriptionManagement prescriptionManagement;
    private final InventoryManager  inventoryManager;
    private final PrescriptionManager prescriptionManager;
    private hospitalStaffManagement hospitalStaffManagement;
    private AppointmentManager appointment;
        
    /**
     * Constructor to initialize an Administrator with necessary dependencies.
     * @param userID The unique identifier for the administrator.
     * @param userRole The role of the user (e.g., "Administrator").
     * @param logger The Logger instance for logging activities.
     * @param reader DataReader instance to handle reading of data.
     * @param writer DataWriter instance to handle writing of data.
     */
    Administrator(String userID, String userRole, Logger logger, dataReader reader, dataWriter writer)
    {
        this.userID = userID;
        this.userRole = userRole;
        this.logger = logger;
        this.dataProcessor= new DataProcessor(reader,writer);
        this.inventoryManagement = new InventoryManagement(reader, writer);
        this.prescriptionManagement = new PrescriptionManagement(inventoryManagement, reader, writer);
        this.inventoryManager = new InventoryManager(inventoryManagement, inventoryManagement, reader, writer);
        this.prescriptionManager = new PrescriptionManager(prescriptionManagement, prescriptionManagement, reader, writer);
        this.hospitalStaffManagement = new hospitalStaffManagement(reader,writer);
        this.appointment = new AppointmentManager(reader,writer);
    }

    /**
     * Displays the main menu for the administrator to choose various options for managing hospital systems.
     */
    public void menu() {
        logger.log("Administrator " + userID + " logged in");//once enter, log user's login
        int choice = -1;
        while (choice != 5) { // Loop until the administrator chooses to log out
            System.out.println("Administrator Menu: ");
            System.out.println("1. View and Manage Hospital Staff");
            System.out.println("2. View Appointments details");
            System.out.println("3. View and Manage Medication Inventory");
            System.out.println("4. Approve Replenishment Requests");
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
                    viewAndManageStaff();
                    break;
                case 2:
                    viewAppointmentsDetails();
                    break;
                case 3:
                    viewAndManageInventory();
                    break;
                case 4:
                    approveReplenishmentRequests();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    /**
     * Manages the hospital staff, allowing the administrator to add, remove, update, or filter staff members.
     */
    private void viewAndManageStaff() {
        logger.log("User entered staff management menu");
        int choice = -1;
        while (choice != 5) {
            System.out.println("1. Add Staff");
            System.out.println("2. Remove Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Filtered View of Staff");
            System.out.println("5. Back to Main Menu");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }

            switch (choice) {
                case 1:
                    addStaff();
                    break;
                case 2:
                    removeStaff();
                    break;
                case 3:
                    updateStaff();
                    break;
                case 4:
                    filteredView();
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    /**
     * Adds a new staff member to the hospital staff management system.
     */
    private void addStaff() {
        System.out.println("Enter Staff ID:");
        String staffID = sc.nextLine();
        System.out.println("Enter Staff Name:");
        String staffName = sc.nextLine();
        System.out.println("Enter Staff Role:");
        String staffRole = sc.nextLine();
        System.out.println("Enter Staff Gender:");
        String staffGender = sc.nextLine();
        System.out.println("Enter Staff Age:");
        
        int staffAge;
        try {
            staffAge = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid age.");
            return; // Exit the method if invalid age input
        }
        hospitalStaffManagement.addStaff(staffID, staffName, staffRole, staffGender, staffAge);
        logger.log("User added a staff");
    }
    /**
     * Removes a staff member from the hospital staff management system.
     */
    private void removeStaff() {
        System.out.println("Enter Staff ID to remove:");
        String staffID = sc.nextLine();
        hospitalStaffManagement.removeStaff(staffID);
        logger.log("User removed staff");
    }
    /**
     * Updates the details of a staff member in the hospital staff management system.
     */
    private void updateStaff() {
        System.out.println("Enter Staff ID to update:");
        String staffID = sc.nextLine();
        System.out.println("Enter New Staff Name:");
        String staffName = sc.nextLine();
        System.out.println("Enter New Staff Role:");
        String staffRole = sc.nextLine();
        System.out.println("Enter New Staff Gender:");
        String staffGender = sc.nextLine();
        System.out.println("Enter New Staff Age:");
        
        int staffAge;
        try {
            staffAge = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid age.");
            return;
        }

        hospitalStaffManagement.updateStaff(staffID, staffName, staffRole, staffGender, staffAge);
        logger.log("User updated staff particulars");
    }
    /**
     * Displays a filtered view of staff members based on category and type.
     */
    private void filteredView() {
        System.out.println("Enter Category to Filter By (e.g., role, gender):");
        String category = sc.nextLine();
        System.out.println("Enter Specific Type to Filter (e.g., Doctor, Male):");
        String type = sc.nextLine();

        hospitalStaffManagement.filteredView(category, type);
        logger.log("User viewed staff members with a filter");
    }
    /**
     * Views all appointment details by accessing the AppointmentManager and PrescriptionManager systems.
     */
    private void viewAppointmentsDetails() {
        System.out.println("Viewing all appointment details...");
        appointment.showAppointmentCsv();
        prescriptionManager.showAllAppointments();
        logger.log("User viewed apppointment details");
    }
    /**
     * Manages the medication inventory, including viewing items, checking low stock, updating thresholds, 
     * and restocking items.
     */
    private void viewAndManageInventory() {
        logger.log("User entered inventory management menu");
        int choice = -1;
        while (choice != 5) {
            System.out.println("1. View Inventory Items");
            System.out.println("2. Check Low Stock");
            System.out.println("3. Update Low Stock Threshold");
            System.out.println("4. Restock Item");
            System.out.println("5. Back to Main Menu");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 5.");
                continue;
            }
            switch (choice) {
                case 1:
                    inventoryManager.viewItems();
                    break;
                case 2:
                    System.out.println("Enter Item Name to check stock:");
                    String itemName = sc.nextLine();
                    inventoryManager.checkLowStockForItem(itemName);
                    break;
                case 3:
                    System.out.println("Enter Item Name to update threshold:");
                    String itemNameThreshold = sc.nextLine();
                    System.out.println("Enter New Threshold:");
                    int threshold;
                    try {
                        threshold = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid threshold.");
                        continue;
                    }
                    inventoryManager.updateLowStockThreshold(itemNameThreshold, threshold);
                    break;
                case 4:
                    System.out.println("Enter Medicine Name to restock:");
                    String medicineName = sc.nextLine();
                    System.out.println("Enter Quantity to Add:");
                    int quantity;
                    try {
                        quantity = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid quantity.");
                        continue;
                    }
                    inventoryManager.restockItemByName(medicineName, quantity);
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    /**
     * Approves replenishment requests for inventory items by calling appropriate methods from InventoryManager.
     */
    private void approveReplenishmentRequests() {
        logger.log("User entered replenishment approval menu");
        int choice = -1;
        while (choice != 3) {
            System.out.println("1. Approve Specific Replenishment Request");
            System.out.println("2. Approve All Pending Requests");
            System.out.println("3. Back to Main Menu");

            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 3.");
                continue;
            }
            switch (choice) {
                case 1:
                    System.out.println("Enter Request ID to Approve:");
                    String requestID = sc.nextLine();
                    System.out.println("Enter Approver ID:");
                    String approvedBy = sc.nextLine();
                    inventoryManager.restockItemByRequestID(requestID, approvedBy);
                    break;
                case 2:
                    System.out.println("Enter Approver ID:");
                    String approvedByAll = sc.nextLine();
                    inventoryManager.handleAllPending(approvedByAll);
                    break;
                case 3:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}