package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The CsvFileWriter class provides methods to write or modify data in CSV files 
 * located within the project structure. It implements the dataWriter interface, 
 * enabling the writing of individual cells, appending rows, and updating specific rows.
 * 
 * <p>
 * This class assumes that CSV files are stored under the path 
 * "HealthManagementSystem/src/FileManager/Data/" within the project directory.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * CsvFileWriter csvWriter = new CsvFileWriter();
 * csvWriter.writeData("example.csv", 2, 1, "newValue");
 * csvWriter.writeRow("example.csv", List.of("new", "data", "row"));
 * csvWriter.writeRow("example.csv", 3, List.of("updated", "data", "row"));
 * </pre>
 * 
 * 
 * <p>
 * Note: Ensure proper file permissions and that the file exists at the specified path.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class CsvFileWriter implements dataWriter {

    /**
     * Updates a specific cell in the CSV file with new data.
     * 
     * @param fileName The name of the CSV file to update, located in the specified path.
     * @param rowIndex The row index of the cell to update, starting from 0.
     * @param colIndex The column index of the cell to update, starting from 0.
     * @param newData The new data to write into the specified cell.
     * 
     * <p>
     * If the specified cell position is out of bounds, no update will be performed.
     * </p>
     */

	@Override
	public void writeData(String fileName, int rowIndex, int colIndex, String newData) {
		 // Path to the original CSV file
         String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;

         // Read the file data
         List<String[]> data = new ArrayList<>();
         try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
             String line;
             while ((line = br.readLine()) != null) {
                 data.add(line.split(","));
             }
         } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Error reading file");
             return;
         }
         
         // Update the specified cell
         if (rowIndex < data.size() && colIndex < data.get(rowIndex).length) {
             data.get(rowIndex)[colIndex] = newData;
         } else {
             System.out.println("Specified row or column is out of bounds");
             return;
         }
     
         // Write the updated data back to the original file (overwriting it)
         try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
             for (String[] rowValues : data) {
                 bw.write(String.join(",", rowValues));
                 bw.newLine();
             }
             System.out.println("Data successfully updated in the original file: " + absolutePath);
         } catch (IOException e) {
             e.printStackTrace();
             System.out.println("Error writing file");
         }
	}

    /**
     * Appends a new row to the end of the CSV file.
     * 
     * @param fileName The name of the CSV file to update, located in the specified path.
     * @param newData A list of strings representing the new row data to append.
     * 
     * <p>
     * Each string in the list represents a cell in the row, separated by commas in the file.
     * </p>
     */

    public void writeRow(String fileName, List<String> newData) {
        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath, true))) {
            System.out.println("Appending new row to CSV file");
    
            StringBuilder sb = new StringBuilder();
            for (String value : newData) {
                sb.append(value).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            bw.write(sb.toString());
            bw.newLine();
            bw.flush();
            
            System.out.println("Appended row: " + sb.toString()); // Log the row for verification
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }

    /**
     * Replaces an existing row in the CSV file with new data.
     * 
     * @param fileName The name of the CSV file to update, located in the specified path.
     * @param rowIndex The index of the row to replace, starting from 0.
     * @param newData A list of strings representing the new data to insert into the specified row.
     * 
     * <p>
     * If the row index is out of bounds, no update is performed.
     * </p>
     */

    @Override
    public void writeRow(String fileName, int rowIndex, List<String> newData) {
        String absolutePath = new File("").getAbsolutePath() + "/HealthManagementSystem/src/FileManager/Data/" + fileName;
        List<String[]> data = new ArrayList<>();
        System.out.println("Reading data from csv file");
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file");
            return;
        }

        // Check if rowIndex is within bounds
        if (rowIndex >= data.size() || rowIndex < 0) {
            System.out.println("Row index out of bounds");
            return;
        }

        // Update row data
        String[] newRow = newData.toArray(new String[0]);
        data.set(rowIndex, newRow);

        // Write updated data back to the CSV file
        System.out.println("Writing data to csv file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath, false))) { // Use 'false' to overwrite the file
            for (String[] rowValues : data) {
                bw.write(String.join(",", rowValues));
                bw.newLine();
                bw.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }

    /**
     * Removes an existing row in the CSV file with new data.
     * 
     * @param fileName The name of the CSV file to update, located in the specified path.
     * @param rowIndex The index of the row to replace, starting from 0.
     * 
     * <p>
     * If the row index is out of bounds, no update is performed.
     * </p>
     */

    @Override
    public void deleteRow(String fileName, int rowIndex) {
        String absolutePath = new File("").getAbsolutePath() + "/HealthManagementSystem/src/FileManager/Data/" + fileName;
        List<String[]> data = new ArrayList<>();
        System.out.println("Reading data from csv file");
        try (BufferedReader br = new BufferedReader(new FileReader(absolutePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error reading file");
            return;
        }

        // Check if rowIndex is within bounds
        if (rowIndex >= data.size() || rowIndex < 0) {
            System.out.println("Row index out of bounds");
            return;
        }

        // Remove row data
        data.remove(rowIndex);

        // Write updated data back to the CSV file
        System.out.println("Writing data to csv file");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath, false))) { // Use 'false' to overwrite the file
            for (String[] rowValues : data) {
                bw.write(String.join(",", rowValues));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error Deleting row");
        }
    }
}
