package FileManager;

import java.util.List;

/**
 * The dataReader interface defines methods for reading data from a CSV file.
 * Implementations of this interface provide functionality to read the entire
 * dataset, a specific row, or a specific column from a CSV file.
 *
 * <p>
 * This interface can be implemented for reading CSV data from various sources
 * or locations within a project.
 * </p>
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public interface dataReader {
    /**
     * Reads the entire contents of the specified CSV file.
     *
     * @param fileName The name of the CSV file to read.
     * @return A list of String arrays, where each array represents a line of data
     *         in the CSV file, split by commas. If the file is not found or an error
     *         occurs, an empty list is returned.
     */
    public List<String[]> readData(String fileName);
    /**
     * Reads a specific row from the specified CSV file.
     *
     * @param fileName The name of the CSV file to read.
     * @param rowIndex The index of the row to read, where the first row is 0.
     * @return A list containing a single String array that represents the requested row's
     *         data, split by commas. If the row is out of bounds, an empty list is returned.
     */
    public List<String[]> readRow(String fileName, int row);
    /**
     * Reads a specific column from the specified CSV file.
     *
     * @param fileName The name of the CSV file to read.
     * @param columnIndex The index of the column to read, where the first column is 0.
     * @return A list of String arrays, each containing a single element from the specified
     *         column of the CSV file. If the column index is out of bounds, an empty list
     *         is returned.
     */
    public List<String[]> readColumn(String fileName, int column);
}
