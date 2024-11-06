package InformationAccess;

import java.util.ArrayList;
import java.util.List;
import FileManager.CsvFileReader;

/**
 * The InformationAccessManager class manages access permissions for the patient role.
 * It implements the {@link InformationAccess} interface to check if access to a specific 
 * variable is permitted based on the role specified at initialization.
 *
 * <p>
 * The class reads from a CSV file ("Information_Access.csv") where access restrictions
 * are defined for different roles. Restricted variables are stored in an internal
 * list, when method is called, it checks if requested variables are allowed.
 * </p>
 * 
 * <p>
 * Example CSV format (Information_Access.csv):
 * <pre>
 * Usage:  
 * @Role refers to the different roles in the hosptial management system (e.g., "Doctor", "Patient", "Admin", "Pharmacist").
 * @variableName checks the resticted variables that the role is not allowed to access.
 * InformationAccessManager AccessManager = new InformationAccessManager("Role");
 * AccessManager.checkInformationAccess("variableName");
 * </pre>
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class InformationAccessManager implements InformationAccess{
    private ArrayList<String> invalidAccessArray;
    /**
     * Constructs an InformationAccessManager for a specified role.
     * 
     * <p>
     * Reads restricted variables for the specified role from "Information_Access.csv" and 
     * populates the {@code invalidAccessArray} list with variables that the role is not 
     * permitted to access.
     * </p>
     * 
     * @param role The role for which to initialize access restrictions. 
     *             Variables restricted for this role are read from the CSV file.
     */

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

     /**
     * Checks if access to the specified variable is allowed for the role of this manager.
     * 
     * @param variable The variable for which to check access permissions.
     * @return {@code true} if access to the variable is allowed; 
     *         {@code false} if access is restricted for this role.
     * 
     * <p>
     * This method searches the {@code invalidAccessArray} for the specified variable. 
     * If found, access is denied; otherwise, access is permitted.
     * </p>
     */
    @Override
    public boolean checkInformationAccess(String variable) {
        for(String invalidAccess : invalidAccessArray) {
            if(variable.equals(invalidAccess)) {
                return false;
            }
        }
        return true;
    }
    
}
