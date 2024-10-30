import java.util.ArrayList;

public class InformationAccessManager implements InformationAccess{
    private ArrayList<String> invalidAccessArray;


    public InformationAccessManager() {
        this.invalidAccessArray = new ArrayList<String>();
        this.invalidAccessArray.add("Blood Type");
        this.invalidAccessArray.add("Past Diagnoses");
        this.invalidAccessArray.add("Treatment");
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
