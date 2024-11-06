package StaffManagement;

/**
 * The {@code StaffManagementAdd} interface defines the contract for adding new staff records 
 * to the hospital management system. Implementing classes should provide the logic for adding 
 * staff details such as ID, name, role, gender, and age.
 * 
 * <p>
 * The primary method in this interface is {@link #addStaff(String, String, String, String, int)} 
 * which adds a new staff member to the system based on the provided attributes.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */

public interface StaffManagementAdd {
    /**
     * Adds a new staff member to the system.
     * 
     * @param staffID The unique identifier for the staff member.
     * @param staffName The name of the staff member.
     * @param staffRole The role or job title of the staff member.
     * @param staffGender The gender of the staff member.
     * @param staffAge The age of the staff member.
     * @return {@code true} if the staff member was successfully added, 
     *         {@code false} if the addition failed (e.g., due to duplicate ID).
     */
    boolean addStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge);
}
