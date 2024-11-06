package InformationAccess;

import java.util.ArrayList;
import java.util.List;
import FileManager.*;
/**
 * The InformationAccessManager class manages access permissions for different roles 
 * within a hospital management system. It implements the {@link InformationAccess} 
 * interface and provides functionality to check if a specific variable is accessible 
 * based on the role of the user.
 *
 * <p>
 * This class reads from a CSV file ("Information_Access.csv") that contains 
 * information about restricted access for different roles. The CSV file is expected 
 * to have a format where each row corresponds to a role and the variables that role 
 * is restricted from accessing. These restricted variables are stored in an internal 
 * list, and the class provides methods to check if access to a requested variable 
 * is allowed for the role.
 * </p>
 * 
 * <p>
 * Example CSV format (Information_Access.csv):
 * <pre>
 * Role, restrictedVariable1, restrictedVariable2, ...
 * Doctor, variable1, variable2
 * Patient, variable1, variable1
 * Admin,
 * </pre>
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * InformationAccessManager accessManager = new InformationAccessManager("Patient", reader, writer);
 * boolean isAccessAllowed = accessManager.checkInformationAccess("Blood Test");
 * </pre>
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class InformationAccessManager implements InformationAccess{
    private ArrayList<String> invalidAccessArray;
    private final DataProcessor dataProcessor;
    /**
     * Constructs an InformationAccessManager for a specified role.
     * 
     * <p>
     * This constructor reads the "Information_Access.csv" file to initialize access 
     * restrictions for the given role. It populates the {@code invalidAccessArray} list 
     * with variables that the specified role is not allowed to access.
     * </p>
     * 
     * @param role The role for which to initialize access restrictions. 
     *             This role is used to filter the relevant restrictions from the CSV file.
     * @param reader The dataReader to read from the CSV file.
     * @param writer The dataWriter to write to the CSV file (if needed for future operations).
     */

    public InformationAccessManager(String role, dataReader reader, dataWriter writer) {
        this.invalidAccessArray = new ArrayList<String>();
        this.dataProcessor = new DataProcessor(reader, writer);

        List<String[]> invalidAccess = dataProcessor.readData("Information_Access.csv");
        for(String[] access : invalidAccess) {
            if(access[0].equals(role)) {
                for(int i = 1; i < access.length; i++) {
                    invalidAccessArray.add(access[i]);
                }
            }
        }
    }

     /**
     * Checks if access to a specified variable is allowed for the role associated with this manager.
     * 
     * <p>
     * This method searches the {@code invalidAccessArray} list for the specified variable. 
     * If the variable is found, access is denied. If not found, access is permitted.
     * </p>
     * 
     * @param variable The name of the variable to check for access permissions.
     * @return {@code true} if access to the variable is allowed; 
     *         {@code false} if access is restricted for this role.
     * 
     * @see InformationAccess
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
