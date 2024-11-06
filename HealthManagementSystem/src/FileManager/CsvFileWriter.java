package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter implements dataWriter {
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
            
            System.out.println("Appended row: " + sb.toString()); // Log the row for verification
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }

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
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
    }
}
