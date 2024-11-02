package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvFileWriter implements dataWriter {
	@Override
	public void writeData(String fileName, int rowIndex, int colIndex, String newData) {
		//write data to csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return;
        }
        System.out.println("Writing data to csv file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }
        
        data.get(rowIndex)[colIndex] = newData;

        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
        //String absolutePath = new File("").getAbsolutePath() + "\\FileManager\\Data\\" + fileName;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
            for (String[] rowValues : data) {
                StringBuilder sb = new StringBuilder();
                for (String value : rowValues) {
                    sb.append(value);
                    sb.append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                bw.write(sb.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing file");
        }
	}

    // New method to add a row to the CSV file
    public void appendRow(String fileName, String[] newRow) {
        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
        
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath, true))) { // Open in append mode
            StringBuilder sb = new StringBuilder();
            for (String value : newRow) {
                sb.append(value).append(",");
            }
            sb.deleteCharAt(sb.length() - 1); // Remove the last comma
            bw.write(sb.toString());
            bw.newLine();
            System.out.println("New row added to " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing new row to file");
        }
    }

    // Method to overwrite the file with updated rows (CSV doesn't allow direct in-place editing of specific rows)
    public void overwriteFile(String fileName, List<String[]> data) {
        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
    
        System.out.println("Overwriting file: " + absolutePath);
    
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
            for (String[] rowValues : data) {
    
                StringBuilder sb = new StringBuilder();
                for (String value : rowValues) {
                    sb.append(value).append(",");
                }
                sb.deleteCharAt(sb.length() - 1);
                bw.write(sb.toString());
                bw.newLine();
            }
            System.out.println("File " + fileName + " updated successfully.");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error writing data to file");
        }
    }
}
