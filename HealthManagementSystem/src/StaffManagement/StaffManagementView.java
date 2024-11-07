package StaffManagement;

/**
 * The {@code StaffManagementView} interface defines the contract for viewing and filtering 
 * hospital staff records based on specific categories and keywords.
 * Implementing classes should provide the logic for filtering and displaying staff information 
 * according to the specified criteria.
 * 
 * <p>
 * The primary method in this interface is {@link #filteredView(String, String)} which 
 * filters the staff data based on the given category and keyword.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */

public interface StaffManagementView {

    /**
     * Filters and displays staff information based on the specified category and keyword.
     * 
     * @param category The category of staff data to filter (e.g., "Role", "Gender").
     * @param keyword The keyword to filter the category by (e.g., "Doctor", "Male").
     */
    void filteredView(String category, String keyword);
}
