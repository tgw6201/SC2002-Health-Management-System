package User;

import StaffManagement.hospitalStaffManagement;
import Inventory.InventoryManagement;
import Inventory.PrescriptionManagement;
import FileManager.dataReader;
import FileManager.dataWriter;
import java.util.Scanner;

public class Administrator {
    // Instances for managing hospital staff, prescriptions, and inventory
    private final hospitalStaffManagement hospitalStaffManagement;
    private final PrescriptionManagement prescriptionManagement;
    private final InventoryManagement inventoryManagement;
    private final Scanner sc;

    // Constructor to initialize Administrator with necessary instances
    public Administrator(hospitalStaffManagement hospitalStaffManagement, PrescriptionManagement prescriptionManagement, InventoryManagement inventoryManagement) {
        this.hospitalStaffManagement = hospitalStaffManagement;
        this.prescriptionManagement = prescriptionManagement;
        this.inventoryManagement = inventoryManagement;
        this.sc = new Scanner(System.in);
    }

    // Main menu for administrator to select different options
    public void Menu() {
        int choice = -1;
        while (choice != 5) { // Loop until the administrator chooses to log out
            System.out.println("Administrator Menu:");
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
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // View and manage hospital staff
    private void viewAndManageStaff() {
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

    // Adds a new staff member
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
    }

    // Removes a staff member
    private void removeStaff() {
        System.out.println("Enter Staff ID to remove:");
        String staffID = sc.nextLine();
        hospitalStaffManagement.removeStaff(staffID);
    }

    // Updates a staff member's details
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
    }

    // Views staff members with a filter
    private void filteredView() {
        System.out.println("Enter Category to Filter By (e.g., role, gender):");
        String category = sc.nextLine();
        System.out.println("Enter Specific Type to Filter (e.g., Doctor, Male):");
        String type = sc.nextLine();

        hospitalStaffManagement.filteredView(category, type);
    }

    // View appointment details by calling a method from PrescriptionManagement
    private void viewAppointmentsDetails() {
        System.out.println("Viewing all appointment details...");
        prescriptionManagement.showAllAppointments();
    }

    // View and manage medication inventory
    private void viewAndManageInventory() {
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
                    inventoryManagement.viewItems();
                    break;
                case 2:
                    System.out.println("Enter Item Name to check stock:");
                    String itemName = sc.nextLine();
                    inventoryManagement.checkLowStockForItem(itemName);
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
                    inventoryManagement.updateLowStockThreshold(itemNameThreshold, threshold);
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
                    inventoryManagement.restockItemByName(medicineName, quantity);
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // Approve replenishment requests by calling methods from InventoryManagement
    private void approveReplenishmentRequests() {
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
                    inventoryManagement.restockItemByRequestID(requestID, approvedBy);
                    break;
                case 2:
                    System.out.println("Enter Approver ID:");
                    String approvedByAll = sc.nextLine();
                    inventoryManagement.handleAllPending(approvedByAll);
                    break;
                case 3:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
