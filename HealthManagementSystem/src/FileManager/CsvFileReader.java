package FileManager;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.ArrayList;

public class CsvFileReader implements dataReader {
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

    @Override
    public List<String[]> readRow(String fileName, String rowIndex) {
        //read row from csv file
        List<String[]> data = new ArrayList<>();
        int row = Integer.parseInt(rowIndex);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return data;
        }
        System.out.println("Reading data from xlsx file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            int currRow = 0;
            String line;
            while ((line = br.readLine()) != null) {
                if(currRow == row) {
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

    @Override
    public List<String[]> readColumn(String fileName, String column) {
        //read column from csv file
        List<String[]> data = new ArrayList<>();
        int col = Integer.parseInt(column);
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("FileManager/Data/" + fileName);
        
        if (inputStream == null) {
            System.out.println("File not found in resources: " + fileName);
            return data;
        }
        System.out.println("Reading data from xlsx file");
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (col < values.length) {
                    data.add(new String[]{values[col]});
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error reading file");
        }

        return data;
    }

}