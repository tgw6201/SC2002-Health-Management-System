package FileManager;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.File;
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
        System.out.println("Reading data from csv file");
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
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
            System.out.println("Writing data to csv file");
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

    public void writeRow(String fileName,List<String> newData){
        //write data to csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return;
        }
        System.out.println("Reading data from csv file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }
        
        String[] newRow = newData.toArray(new String[0]);
        data.add(newRow);

        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
            System.out.println("Writing data to csv file");
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

    @Override
    public void writeRow(String fileName, int rowIndex, List<String> newData) {
        //write row to csv file
        List<String[]> data = new ArrayList<>();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return;
        }
        System.out.println("Reading data from csv file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }
        
        if(rowIndex >= data.size() || rowIndex < 0) {
            System.out.println("Row index out of bounds");
            return;
        }

        String[] newRow = newData.toArray(new String[0]);
        data.set(rowIndex, newRow);

        String absolutePath = new File("").getAbsolutePath() + "\\HealthManagementSystem\\src\\FileManager\\Data\\" + fileName;
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(absolutePath))) {
            System.out.println("Writing data to csv file");
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

    
}
