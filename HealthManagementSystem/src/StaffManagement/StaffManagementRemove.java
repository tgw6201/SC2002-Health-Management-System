package StaffManagement;

/**
 * The {@code StaffManagementRemove} interface defines the contract for removing staff records 
 * from the hospital management system. Implementing classes should provide the logic for 
 * removing a staff member based on their unique staff ID.
 * 
 * <p>
 * The primary method in this interface is {@link #removeStaff(String)} which removes a staff member 
 * from the system using the provided staff ID.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */

public interface StaffManagementRemove {

    /**
     * Removes a staff member from the system.
     * 
     * @param staffID The unique identifier for the staff member to be removed.
     * @return {@code true} if the staff member was successfully removed, 
     *         {@code false} if the removal failed (e.g., if the staff ID was not found).
     */
    boolean removeStaff(String staffID);
}
