package StaffManagement;
import java.util.List;

import FileManager.CsvFileWriter;
import FileManager.CsvFileReader;

/**
 * The hospitalStaffManagement class implements the staffManagement interface and 
 * provides functionality for managing hospital staff records.
 * It allows for adding, removing, updating, and viewing staff information with 
 * filtering capabilities.
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public class hospitalStaffManagement implements staffManagement{
    private String staffID;
    private String staffName;
    private String staffRole;
    private String staffGender;
    private int staffAge;

    /**
     * Constructor for the hospitalStaffManagement class.
     * Initializes the staff attributes to empty values or default values.
     */

    public hospitalStaffManagement() {
        this.staffID = "";
        this.staffName = "";
        this.staffRole = "";
        this.staffGender = "";
        this.staffAge = 0;
    }

    /**
     * Adds a new staff member to the staff list.
     * Checks if the staff ID already exists before adding.
     * 
     * @param staffID    The ID of the staff member.
     * @param staffName  The name of the staff member.
     * @param staffRole  The role of the staff member (e.g., Doctor, Nurse).
     * @param staffGender The gender of the staff member.
     * @param staffAge   The age of the staff member.
     * @return true if the staff was successfully added, false if the ID already exists.
     */
    @Override
    public boolean addStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge) {
        int checkID = getStaffByIndex(staffID);
        if(checkID != -1)
        {
            System.out.println("Staff ID already exists");
            return false;
        }
        CsvFileWriter csvFileWriter = new CsvFileWriter();
        try {
            csvFileWriter.writeRow("Staff_List.csv", List.of(staffID, staffName, staffRole, staffGender, String.valueOf(staffAge)));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error adding staff");
            return false;
        }
    }

    /**
     * Removes a staff member from the staff list based on the given staff ID.
     * 
     * @param staffID The ID of the staff member to remove.
     * @return true if the staff member was successfully removed, false if the staff ID was not found.
     */
    @Override
    public boolean removeStaff(String staffID) {
        int staffIndex = getStaffByIndex(staffID);
        if(staffIndex == -1)
        {
            System.out.println("Staff not found");
            return false;
        }
        else
        {
            CsvFileWriter csvFileWriter = new CsvFileWriter();
            csvFileWriter.deleteRow("Staff_List.csv", staffIndex);
            return true;
        }
    }

    /**
     * Updates the information of an existing staff member in the staff list.
     * 
     * @param staffID    The ID of the staff member to update.
     * @param staffName  The updated name of the staff member.
     * @param staffRole  The updated role of the staff member.
     * @param staffGender The updated gender of the staff member.
     * @param staffAge   The updated age of the staff member.
     * @return true if the staff member was successfully updated, false if the staff ID was not found.
     */
    @Override
    public boolean updateStaff(String staffID, String staffName, String staffRole, String staffGender, int staffAge) {
        int staffIndex = getStaffByIndex(staffID);
        if(staffIndex == -1)
        {
            System.out.println("Staff not found");
            return false;
        }
        else
        {
            CsvFileWriter csvFileWriter = new CsvFileWriter();
            csvFileWriter.writeRow("Staff_List.csv", staffIndex, List.of(staffID, staffName, staffRole, staffGender, String.valueOf(staffAge)));
            return true;
        }
    }

    /**
     * Displays a filtered list of staff based on a specific category and type.
     * For example, this could display all staff with a specific role or gender.
     * 
     * @param category The category to filter by (e.g., "role", "gender").
     * @param type The specific value within the category to filter by (e.g., "Doctor", "Male").
     */
    @Override
    public void filteredView(String category, String type) {
        int filterIndex = getFilterIndex(category);
        if(filterIndex == -1)
        {
            System.out.println("Category not found");
            return;
        }

        List<String[]> data = new CsvFileReader().readData("Staff_List.csv");
        String[] dataCategory = data.get(0);
        System.out.println("Data Size: " + data.size());
        for(int i = 1; i < data.size()-1; i++)
        {
            if(data.get(i)[filterIndex].toLowerCase().equals(type.toLowerCase())){
                for(int j = 0; j < dataCategory.length; j++)
                {
                    System.out.print(dataCategory[j] + ": " + data.get(i)[j] + " ");
                }
                System.out.println();
            }
        }
        
    }

    /**
     * Retrieves the index of the staff record based on the provided staff ID.
     * 
     * @param staffID The ID of the staff member to search for.
     * @return The index of the staff member in the list, or -1 if not found.
     */
    private int getStaffByIndex(String staffID) {
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> data = csvFileReader.readData("Staff_List.csv");
        for (int i = 0; i < data.size(); i++) {
            if (data.get(i)[0].equals(staffID)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Retrieves the index of a category in the header row based on the provided filter.
     * 
     * @param filter The category name to search for (e.g., "role", "gender").
     * @return The index of the category in the header row, or -1 if not found.
     */
    private int getFilterIndex(String filter) {
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> data = csvFileReader.readRow("Staff_List.csv", 0);
        String[] dataCategory = data.get(0);
        for(int i = 0; i < dataCategory.length; i++)
        {
            if(dataCategory[i].toLowerCase().equals(filter.toLowerCase()))
            {
                return i;
            }
        }
        return -1;
    }

}
