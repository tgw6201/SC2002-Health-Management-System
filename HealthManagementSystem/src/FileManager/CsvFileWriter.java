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
	public void writeData(String fileName, String rowIndex, String colIndex, String newData) {
		//write data to csv file
        int row = Integer.parseInt(rowIndex);
        int col = Integer.parseInt(colIndex);
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
        
        data.get(row)[col] = newData;

        String absolutePath = new File("").getAbsolutePath() + "\\" + fileName;
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
}
