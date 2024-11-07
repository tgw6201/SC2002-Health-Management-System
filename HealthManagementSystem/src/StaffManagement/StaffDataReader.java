package StaffManagement;

import java.util.List;

/**
 * The {@code StaffDataReader} interface defines a contract for reading staff-related data.
 * Implementing classes should provide a method for retrieving the staff data, typically from a file or database.
 * 
 * <p>
 * The primary method of this interface is {@link #readStaff()}, which returns a list of staff data in a format
 * suitable for further processing, such as arrays of strings where each array represents a staff record.
 * </p>
 * 
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-06
 */

public interface StaffDataReader {

    /**
     * Reads the staff data and returns it as a list of string arrays, where each array represents a staff record.
     * 
     * <p>
     * Implementing classes should provide logic to retrieve the staff data from an appropriate data source, such as 
     * a file or database.
     * </p>
     *
     * @return A list of string arrays, where each array represents a staff record (with fields separated by commas, for example).
     *         If no data is available, an empty list may be returned.
     */
    List<String[]> readStaff();
}
