package StaffManagement;

/**
 * The {@code StaffManagementUpdate} interface defines the contract for updating staff records 
 * in the hospital management system. Implementing classes should provide the logic for updating 
 * staff information based on the provided staff ID.
 * 
 * <p>
 * The primary method in this interface is {@link #updateStaff(String, String, String, String, int)} 
 * which updates a staff member's details using their unique staff ID.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */
public interface StaffManagementUpdate {

    /**
     * Updates the information of an existing staff member in the system.
     * 
     * @param staffID The unique identifier for the staff member to be updated.
     * @param staffName The updated name of the staff member.
     * @param staffRole The updated role of the staff member.
     * @param staffGender The updated gender of the staff member.
     * @param staffAge The updated age of the staff member.
     * @return {@code true} if the staff member's information was successfully updated, 
     *         {@code false} if the update failed (e.g., if the staff ID was not found).
     */
    boolean updateStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge);
}
