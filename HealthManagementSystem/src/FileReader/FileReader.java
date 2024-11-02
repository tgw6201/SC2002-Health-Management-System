package FileReader;

import java.util.List;

public interface FileReader {
    public List<String[]> readData(String fileName);
    public List<String[]> readRow(String fileName, String row);
    public List<String[]> readColumn(String fileName, String column);
}
