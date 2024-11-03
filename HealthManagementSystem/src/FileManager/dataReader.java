package FileManager;

import java.util.List;

public interface dataReader {
    public List<String[]> readData(String fileName);
    public List<String[]> readRow(String fileName, int row);
    public List<String[]> readColumn(String fileName, int column);
}
