package FileManager;
import java.util.List;


/**
 * The DataProcessor class provides methods for reading, writing, updating, and deleting 
 * data from CSV files. It acts as an intermediary between the data reader and writer 
 * to handle file operations such as counting records, modifying rows, and updating columns.
 * 
 * <p>
 * This class requires an instance of a dataReader and a dataWriter to perform the operations.
 * It provides high-level methods that simplify the process of interacting with data files.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * dataReader reader = new CsvFileReader();
 * dataWriter writer = new CsvFileWriter();
 * DataProcessor processor = new DataProcessor(reader, writer);
 * processor.countRecords("example.csv"); // Counts records in the file
 * processor.writeData("example.csv", 2, 1, "New Data"); // Writes data to a specific cell
 * processor.deleteRow("example.csv", 3); // Deletes the 4th row
 * </pre>
 * 
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class DataProcessor {
    private final dataReader reader;
    private final dataWriter writer;

    /**
     * Constructs a DataProcessor with the specified reader and writer.
     * 
     * @param reader The dataReader instance used to read data from files.
     * @param writer The dataWriter instance used to write and modify data in files.
     */

    public DataProcessor(dataReader reader, dataWriter writer) {
        this.reader = reader;
        this.writer = writer;
    }

    /**
     * Counts the number of records (rows) in the specified CSV file.
     * 
     * @param fileName The name of the CSV file to read.
     * @return The number of records in the file.
     */

    public int countRecords(String fileName) {
        List<String[]> data = reader.readData(fileName);
        return data.size();
    }

    /**
     * Writes new data to a specific cell in the CSV file, identified by the row and column indexes.
     * 
     * @param fileName The name of the CSV file to modify.
     * @param rowIndex The index of the row to modify.
     * @param colIndex The index of the column to modify.
     * @param newData The new data to write in the specified cell.
     */

    public void writeData(String fileName, int rowIndex, int colIndex, String newData) {
        writer.writeData(fileName, rowIndex, colIndex, newData);
    }

    /**
     * Writes new data to a specific row in the CSV file, identified by the row index.
     * 
     * @param fileName The name of the CSV file to modify.
     * @param rowIndex The index of the row to modify.
     * @param data A list of new data values to write to the specified row.
     */

    public void writeRow(String fileName, int rowIndex, List<String> data) {
        writer.writeRow(fileName, rowIndex, data);
    }

    /**
     * Writes new data to the CSV file, adding it as a new row.
     * 
     * @param fileName The name of the CSV file to modify.
     * @param data A list of data values to add as a new row in the file.
     */

    public void writeRow(String fileName, List<String> data) {
        writer.writeRow(fileName, data);
    }

    /**
     * Deletes a row from the specified CSV file.
     * 
     * @param fileName The name of the CSV file to modify.
     * @param rowIndex The index of the row to delete.
     */

    public void deleteRow(String fileName, int rowIndex) {
        writer.deleteRow(fileName, rowIndex);
    }

    /**
     * Reads all data from the specified CSV file.
     * 
     * @param fileName The name of the CSV file to read.
     * @return A list of string arrays, where each array represents a row in the file.
     */

    public List<String[]> readData(String fileName) {
        return reader.readData(fileName);
    }

    /**
     * Reads a specific row from the specified CSV file, identified by the row index.
     * 
     * @param fileName The name of the CSV file to read.
     * @param rowIndex The index of the row to read.
     * @return A list containing a single string array representing the data of the requested row.
     */

    public List<String[]> readRow(String fileName, int rowIndex) {
        return reader.readRow(fileName, rowIndex);
    }

     /**
     * Updates a specific row in the CSV file, identified by the row index, with new data.
     * 
     * @param fileName The name of the CSV file to modify.
     * @param rowIndex The index of the row to update.
     * @param data A list of new data values to update in the specified row.
     */

    public void updateRow(String fileName, int rowIndex, List<String> data) {
        writer.writeRow(fileName, rowIndex, data);
    }
}
