package FileManager;

import java.util.List;

/**
 * The dataWriter interface defines methods for writing data to a CSV file.
 * Implementations of this interface provide functionality to modify specific cells,
 * update specific rows, or append rows to a CSV file.
 *
 * <p>
 * This interface can be implemented to handle data writing operations for CSV
 * files stored locally or within specific locations in a project.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public interface dataWriter {
    /**
     * Writes data to a specific cell in the CSV file.
     * 
     * @param fileName The name of the CSV file to write to.
     * @param row The row index of the cell to write, where the first row is 0.
     * @param column The column index of the cell to write, where the first column is 0.
     * @param data The new data to write into the specified cell.
     * 
     * <p>
     * This method will locate the specified cell in the file and update its contents
     * with the new data provided. If the specified row or column is out of bounds, 
     * the implementation should handle it appropriately.
     * </p>
     */
    public void writeData(String fileName, int row, int column, String data);
    
    /**
     * Writes or updates an entire row in the CSV file.
     * 
     * @param fileName The name of the CSV file to write to.
     * @param row The row index to update or write data to, where the first row is 0.
     * @param data A list of strings representing the new row data to be written.
     *             Each element in the list represents a cell in the row.
     * 
     * <p>
     * If the specified row index exists, this method will overwrite the entire row with 
     * the new data. If it does not exist or is out of bounds, the implementation may 
     * handle it by appending or ignoring, depending on the intended behavior.
     * </p>
     */
    public void writeRow(String fileName, int row, List<String> data);

    /**
     * Appends a new row to the end of the CSV file.
     * 
     * @param fileName The name of the CSV file to write to.
     * @param data A list of strings representing the new row data to be appended.
     *             Each element in the list represents a cell in the row.
     */
    public void writeRow(String fileName, List<String> data);

    /**
     * Deletes an entire row in the CSV file.
     * 
     * @param fileName The name of the CSV file to write to.
     * @param row The row index to update or write data to, where the first row is 0.
     * 
     * <p>
     * If the specified row index exists, this method will delete the entire row. If it does not exist or is out of bounds, the implementation may 
     * handle it by appending or ignoring, depending on the intended behavior.
     * </p>
     */
    public void deleteRow(String fileName, int row);
}
