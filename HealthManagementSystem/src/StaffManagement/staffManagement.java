package StaffManagement;

/**
 * The staffManagement interface provides the contract for managing hospital staff records.
 * It includes methods for adding, removing, updating, and filtering staff data based on specific categories.
 */

public interface staffManagement {
    /**
     * Adds a new staff member to the staff list.
     * 
     * @param staffID    The unique ID of the staff member.
     * @param staffName  The name of the staff member.
     * @param staffRole  The role of the staff member (e.g., Doctor, Nurse).
     * @param staffGender The gender of the staff member.
     * @param staffAge   The age of the staff member.
     * @return true if the staff member was successfully added, false if the staff ID already exists.
     * @author Tan Guang Wei
     * @version 1.0
     * @since 2024-11-6
     */
    public boolean addStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge);
    /**
     * Removes a staff member from the staff list based on the provided staff ID.
     * 
     * @param staffID The unique ID of the staff member to be removed.
     * @return true if the staff member was successfully removed, false if the staff ID was not found.
     */
    public boolean removeStaff(String staffID);
     /**
     * Updates the information of an existing staff member in the staff list.
     * 
     * @param staffID    The unique ID of the staff member to be updated.
     * @param staffName  The updated name of the staff member.
     * @param staffRole  The updated role of the staff member (e.g., Doctor, Nurse).
     * @param staffGender The updated gender of the staff member.
     * @param staffAge   The updated age of the staff member.
     * @return true if the staff member was successfully updated, false if the staff ID was not found.
     */
    public boolean updateStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge);
    /**
     * Displays a filtered view of staff based on a specific category and type.
     * 
     * @param category The category to filter by (e.g., "role", "gender").
     * @param type The specific value within the category to filter by (e.g., "Doctor", "Male").
     */
    public void filteredView(String category, String type);
}
