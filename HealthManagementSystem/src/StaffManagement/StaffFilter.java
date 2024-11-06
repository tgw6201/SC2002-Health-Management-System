package StaffManagement;

/**
 * The {@code StaffFilter} interface defines a contract for filtering staff data.
 * Implementing classes should provide a method to apply a filtering condition on staff data.
 * 
 * <p>
 * The primary method of this interface is {@link #filter(String[])} which evaluates whether 
 * a given staff record meets a specific condition.
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
     * @param staffData An array of strings representing the staff record. 
     *                  The array contains staff information like ID, name, role, etc.
     * @return {@code true} if the staff record meets the filter condition, 
     *         {@code false} otherwise.
     */
    boolean filter(String[] staffData);
}
