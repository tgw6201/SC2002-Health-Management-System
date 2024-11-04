package FileManager;

import java.util.List;

public interface dataWriter {
    public void writeData(String fileName, int row, int column, String data);
    public void writeRow(String fileName, int row, List<String> data);
}
