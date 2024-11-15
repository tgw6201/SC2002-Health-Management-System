package StaffManagement;

/**
 * The {@code StaffFilter} interface defines a contract for filtering staff data.
 * Implementing classes should provide a method to apply a filtering condition on staff records.
 * 
 * <p>
 * The primary method of this interface is {@link #filteredView(String, String)}, which evaluates whether 
 * a given staff record meets a specific condition based on the provided filtering criteria.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */
public interface StaffFilter {

     /**
     * Applies a filtering condition to a staff record.
     * 
     * <p>
     * This method allows filtering staff data based on specific categories and types.
     * Implementing classes should define how to handle the filtering based on the 
     * provided criteria.
     * </p>
     *
     * @param category The category by which the staff record will be filtered (e.g., role, department).
     * @param type The type or value to match against within the given category (e.g., specific role, department name)..
     */
    void filteredView(String category, String type);
}
