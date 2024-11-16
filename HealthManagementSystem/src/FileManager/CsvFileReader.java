package FileManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * The CsvFileReader class provides methods to read data from CSV files located
 * in the resources folder. It implements the dataReader interface, allowing 
 * the reading of entire data sets, specific rows, or specific columns from a CSV file.
 * 
 * <p>
 * This class assumes that CSV files are stored under the path 
 * "FileManager/Data/" within the project resources.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     CsvFileReader csvReader = new CsvFileReader();
 *     List<String[]> allData = csvReader.readData("example.csv");
 *     List<String[]> row = csvReader.readRow("example.csv", 2);
 *     List<String[]> column = csvReader.readColumn("example.csv", 1);
 * </pre>
 * </p>
 * 
 * <p>
 * Note: Ensure the CSV files are properly formatted and located under the expected path.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class CsvFileReader implements dataReader {
    /**
     * Reads the entire contents of the specified CSV file.
     * 
     * @param fileName The name of the CSV file to read, located in the resources folder.
     * @return A list of String arrays, where each array represents a line of data
     *         in the CSV file, split by commas. If the file is not found or an error
     *         occurs, an empty list is returned.
     */
    @Override
    public List<String[]> readData(String fileName) {
        //read data from csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return data;
        }
        System.out.println("Reading data from csv file");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;

            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }

        return data;
    }

    /**
     * Reads a specific row from the specified CSV file.
     * 
     * @param fileName The name of the CSV file to read, located in the resources folder.
     * @param rowIndex The index of the row to read, where the first row is 0.
     * @return A list containing a single String array that represents the requested row's
     *         data, split by commas. If the row is out of bounds, an empty list is returned.
     */

    @Override
    public List<String[]> readRow(String fileName, int rowIndex) {
        //read row from csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return data;
        }
        System.out.println("Reading data from csv file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int currRow = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if(currRow == rowIndex) {
                    String[] values = line.split(",");
                    data.add(values);
                    break;
                }
                currRow++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }

        return data;
    }

     /**
     * Reads a specific column from the specified CSV file.
     * 
     * @param fileName The name of the CSV file to read, located in the resources folder.
     * @param column The index of the column to read, where the first column is 0.
     * @return A list of String arrays, each containing a single element from the specified
     *         column of the CSV file. If the column index is out of bounds, an empty list
     *         is returned.
     */

    @Override
    public List<String[]> readColumn(String fileName, int column) {
        //read column from csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return data;
        }
        System.out.println("Reading data from csv file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (column < values.length) {
                    data.add(new String[]{values[column]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }

        return data;
    }

}
