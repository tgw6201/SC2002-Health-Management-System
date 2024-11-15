package StaffManagement;

import FileManager.DataProcessor;
import FileManager.dataReader;
import FileManager.dataWriter;
import java.util.List;

/**
 * The hospitalStaffManagement class implements the StaffManagementAdd, StaffManagementUpdate,
 * StaffManagementRemove, and StaffManagementView interfaces, providing functionality for managing
 * hospital staff records. It allows for adding, removing, updating, and viewing staff information
 * with filtering capabilities.
 *
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */
public class hospitalStaffManagement implements StaffManagementAdd, StaffManagementUpdate, StaffManagementRemove, StaffManagementView, StaffFilter {
    private final DataProcessor dataProcessor;

    /**
     * Constructor for the hospitalStaffManagement class.
     * 
     */
    public hospitalStaffManagement(dataReader dataReader, dataWriter dataWriter) {
        this.dataProcessor = new DataProcessor(dataReader, dataWriter);
    }

    /**
     * Adds a new staff member to the staff list. Checks if the staff ID already exists before adding.
     *
     * @param staffID    The ID of the staff member.
     * @param staffName  The name of the staff member.
     * @param staffRole  The role of the staff member (e.g., Doctor, Nurse).
     * @param staffGender The gender of the staff member.
     * @param staffAge   The age of the staff member.
     * @return true if the staff was successfully added, false if the ID already exists.
     */
    @Override
    public boolean addStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge) {
        int checkID = getStaffByIndex(staffID);
        if (checkID != -1) {
            System.out.println("Staff ID already exists. Please enter a unique Staff ID.");
            return false;
        }
        try {
            // Prepare staff data
            List<String> staffData = List.of(staffID, staffName, staffRole, staffGender, String.valueOf(staffAge));
            // Add staff using DataProcessor
            dataProcessor.writeRow("Staff_List.csv", staffData);
            // Prepare staff account information
            List<String> accountDetail = List.of(staffID, "password", staffRole);
            // Add account using DataProcessor
            dataProcessor.writeRow("User_Accounts.csv", accountDetail);
            return true;
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
            return false;
        }
    }

    /**
     * Removes a staff member from the staff list based on the given staff ID.
     *
     * @param staffID The ID of the staff member to remove.
     * @return true if the staff member was successfully removed, false if the staff ID was not found.
     */
    @Override
    public boolean removeStaff(String staffID) {
        int staffIndex = getStaffByIndex(staffID);
        if (staffIndex == -1) {
            System.out.println("Staff not found.");
            return false;
        }
        int accountIndex = getAccountByIndex(staffID);
        if (accountIndex == -1){
            System.out.println("Account not found.");
            return false;
        } 
        else {
            // Delete staff row using DataProcessor
            dataProcessor.deleteRow("Staff_List.csv", staffIndex);
            dataProcessor.deleteRow("User_Accounts.csv",accountIndex);
            return true;
        }
    }

    /**
     * Updates the information of an existing staff member in the staff list.
     *
     * @param staffID    The ID of the staff member to update.
     * @param staffName  The updated name of the staff member.
     * @param staffRole  The updated role of the staff member.
     * @param staffGender The updated gender of the staff member.
     * @param staffAge   The updated age of the staff member.
     * @return true if the staff member was successfully updated, false if the staff ID was not found.
     */
    @Override
    public boolean updateStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge) {
        int staffIndex = getStaffByIndex(staffID);
        if (staffIndex == -1) {
            System.out.println("Staff not found.");
            return false;
        } else {
            // Prepare updated staff data
            List<String> updatedStaffData = List.of(staffID, staffName, staffRole, staffGender, String.valueOf(staffAge));
            // Update staff using DataProcessor
            dataProcessor.updateRow("Staff_List.csv", staffIndex, updatedStaffData);
            return true;
        }
    }

    /**
     * Displays a filtered list of staff based on a specific category and type.
     * For example, this could display all staff with a specific role or gender.
     *
     * @param category The category to filter by (e.g., "role", "gender").
     * @param type The specific value within the category to filter by (e.g., "Doctor", "Male").
     */
    @Override
    public void filteredView(String category, String type) {
        int filterIndex = getFilterIndex(category);
        if (filterIndex == -1) {
            System.out.println("Category not found.");
            return;
        }
        List<String[]> data = dataProcessor.readData("Staff_List.csv");
        String[] dataCategory = data.get(0); // Assuming first row is header
        System.out.println("Data Size: " + data.size());
        for (int i = 1; i < data.size(); i++) {
            if (data.get(i)[filterIndex].toLowerCase().equals(type.toLowerCase())) {
                for (int j = 0; j < dataCategory.length; j++) {
                    System.out.print(dataCategory[j] + ": " + data.get(i)[j] + " ");
                }
                System.out.println();
            }
        }
    }

    /**
     * Helper method to get staff by ID
     *
     * @param staffID The ID of the staff member to search for.
     * @return The index of the staff member in the list, or -1 if not found.
     */
    private int getStaffByIndex(String staffID) {
        List<String[]> data = dataProcessor.readData("Staff_List.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(staffID)) {
                return i;
            }
        }
        return -1;
    }
    /**
     * Helper method to get account index using ID
     *
     * @param staffID The ID of the staff member to search for.
     * @return The index of the staff member in the account list, or -1 if not found.
     */
    private int getAccountByIndex(String staffID) {
        List<String[]> data = dataProcessor.readData("User_Accounts.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(staffID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Helper method to get filter index by category.
     *
     * @param filter The category name to search for (e.g., "role", "gender").
     * @return The index of the category in the header row, or -1 if not found.
     */
    private int getFilterIndex(String filter) {
        List<String[]> data = dataProcessor.readData("Staff_List.csv");
        String[] dataCategory = data.get(0);
        for (int i = 0; i < dataCategory.length; i++) {
            if (dataCategory[i].toLowerCase().equals(filter.toLowerCase())) {
                return i;
            }
        }
        return -1;
    }
}
