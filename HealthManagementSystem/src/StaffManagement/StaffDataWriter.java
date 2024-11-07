package StaffManagement;

/**
 * The {@code StaffDataWriter} interface defines a contract for writing staff-related data.
 * Implementing classes should provide a method to save or write staff information to a data source.
 * 
 * <p>
 * The primary method of this interface is {@link #writeStaff(String, String, String, String, int)}, 
 * which saves the provided staff information.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */
public interface StaffDataWriter {

    /**
     * Writes a staff record with the provided details.
     * 
     * @param staffID The unique identifier for the staff member.
     * @param staffName The name of the staff member.
     * @param staffRole The role of the staff member in the organization.
     * @param staffGender The gender of the staff member.
     * @param staffAge The age of the staff member.
     */
    void writeStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge);
}
