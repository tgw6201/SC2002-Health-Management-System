package InformationAccess;
import java.util.ArrayList;
import java.util.List;

import FileManager.CsvFileReader;

public class InformationAccessManager implements InformationAccess{
    private ArrayList<String> invalidAccessArray;


    public InformationAccessManager(String role) {
        this.invalidAccessArray = new ArrayList<String>();
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> invalidAccess = csvFileReader.readData("Information_Access.csv");
        for(String[] access : invalidAccess) {
            if(access[0].equals(role)) {
                for(int i = 1; i < access.length; i++) {
                    invalidAccessArray.add(access[i]);
                }
            }
        }
    }

    public boolean checkInformationAccess(String variable) {
        for(String invalidAccess : invalidAccessArray) {
            if(variable.equals(invalidAccess)) {
                return false;
            }
        }
        return true;
    }
    
}
