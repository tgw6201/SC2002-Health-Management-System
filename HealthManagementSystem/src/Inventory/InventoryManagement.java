package Inventory;

import FileManager.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class InventoryManagement {

    private List<String[]> inventoryData;
    CsvFileWriter csvFileWriter = new CsvFileWriter();
    CsvFileReader csvFileReader = new CsvFileReader();

    public InventoryManagement() {
        // Load all inventory data at initialization
        inventoryData = csvFileReader.readData("Medicine_List.csv");
    }

    // Method to view all items in the inventory
    public void viewItems() {
        System.out.println("\nViewing Medicine Inventory.... \n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            for (String cell : row) {
                System.out.println(cell);
            }
            System.out.println("----------");  // Divider between rows
        }
    }

    // Method to restock items in the inventory
    private boolean restockItems(String medsName, int quantity) {
        System.out.println("\nRestocking " + medsName + "....\n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                if (row[0].equalsIgnoreCase(medsName)) { 
                    int initialQuantity = Integer.parseInt(row[1]);
                    initialQuantity += quantity;
                    row[1] = String.valueOf(initialQuantity);  // Update the quantity in the list
                    System.out.println("Updated quantity of " + medsName + " to " + row[1]);

                    // Update the specific cell in the CSV file
                    csvFileWriter.writeData("Medicine_List.csv", i, 1, row[1]);

                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in quantity field for " + row[0]);
            }
        }
        System.out.println("Medication not found: " + medsName);
        return false;
    }

    // Method to update the threshold of a specific medication
    public boolean updateThreshold(String medsName, int newThreshold) {
        System.out.println("\nUpdating threshold for " + medsName + "....\n");

        // Loop through the inventory data to find the medicine
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                // Check if the medicine name matches
                if (row[0].equalsIgnoreCase(medsName)) {
                    // Update the threshold value in the list
                    row[2] = String.valueOf(newThreshold);
                    System.out.println("Updated threshold for " + medsName + " to " + row[2]);

                    // Write the specific update to the CSV file
                    csvFileWriter.writeData("Medicine_List.csv", i, 2, row[2]);

                    return true; // Successfully updated
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in threshold field for " + row[0]);
            }
        }

        System.out.println("Medication not found: " + medsName);
        return false; // Medicine not found
    }


    // Method to dispense items from the inventory
    public boolean dispenseItems(String medsName, int quantity) {
        System.out.println("\nDispensing " + medsName + "....\n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                if (row[0].equalsIgnoreCase(medsName)) {
                    int initialQuantity = Integer.parseInt(row[1]);
                    if (initialQuantity >= quantity) {
                        initialQuantity -= quantity;
                        row[1] = String.valueOf(initialQuantity);
                        System.out.println("Dispensed " + quantity + " of " + medsName);
                        System.out.println("Updated quantity of " + medsName + " to " + row[1]);

                        // Update the specific cell in the CSV file
                        csvFileWriter.writeData("Medicine_List.csv", i, 1, row[1]);

                        // Check if the dispensed item is low on stock
                        checkLowStockForItem(medsName, initialQuantity, Integer.parseInt(row[2]));

                        return true;
                    } else {
                        System.out.println("Not enough stock to dispense " + quantity + " of " + medsName);
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in quantity field for " + row[0]);
            }
        }
        System.out.println("Medication not found: " + medsName);
        return false;
    }

    // Method to check low stock for all item
    public void checkLowStock() {
        System.out.println("\nChecking stock....\n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                int quantity = Integer.parseInt(row[1]);
                int threshold = Integer.parseInt(row[2]);
                checkLowStockForItem(row[0], quantity, threshold);
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in stock or threshold fields for " + row[0]);
            }
        }
    }

    // Helper method to check if a specific item is low on stock
    private void checkLowStockForItem(String itemName, int quantity, int threshold) {
        if (quantity <= threshold) {
            System.out.printf("Warning: Low stock for %s - %d units remaining\n", itemName, quantity);
        }
    }

    // Method to display all pending prescriptions
    public void displayPendingPrescriptions() {
        List<String[]> prescriptions = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
        System.out.println("\nDisplaying all pending prescriptions:\n");
        
        for (String[] record : prescriptions) {
            if ("Pending".equalsIgnoreCase(record[6])) {  // Assuming column 4 is the status
                System.out.println(Arrays.toString(record));
            }
        }
    }

    // Method to dispense medicine based on appointmentOutcomeID
    public void dispenseByAppointmentId(String appointmentOutcomeID) {
        List<String[]> appointments = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");

        // Find the appointment with the specified ID
        for (int i = 0; i < appointments.size(); i++) {
            String[] appointment = appointments.get(i);

            // Check if the ID matches and the prescription status is "Pending"
            if (appointment[0].equals(appointmentOutcomeID) && "Pending".equalsIgnoreCase(appointment[6])) {
                String medsName = appointment[4]; 
                int quantity = Integer.parseInt(appointment[5]); 

                // Attempt to dispense the medicine
                boolean success = dispenseItems(medsName, quantity);
                if (success) {
                    // Update the status to "Completed"
                    appointment[6] = "Dispensed";

                    // Convert the updated row to a List<String> and overwrite the row in the CSV file
                    List<String> updatedRow = Arrays.asList(appointment);
                    csvFileWriter.writeRow("AppointmentOutcomeRecord_List.csv", i, updatedRow);
                    System.out.println("Medicine dispensed successfully for appointment ID " + appointmentOutcomeID);
                } else {
                    System.out.println("Failed to dispense medicine for appointment ID " + appointmentOutcomeID + ": insufficient stock.");
                }
                return; // Exit after finding and processing the matching appointment
            }
        }
        System.out.println("No pending prescription found with appointment ID " + appointmentOutcomeID);
    }

    // Method to dispense all medicine with pending status in AOR
    public void dispenseAllPending() {
        List<String[]> appointments = csvFileReader.readData("AppointmentOutcomeRecord_List.csv");
    
        // Loop through all appointments and process each pending prescription
        for (int i = 0; i < appointments.size(); i++) {
            String[] appointment = appointments.get(i);
    
            // Check if the prescription status is "Pending"
            if ("Pending".equalsIgnoreCase(appointment[6])) { 
                String appointmentOutcomeID = appointment[0];
                String medsName = appointment[4]; 
                String quantityStr = appointment[5];  
    
                try {
                    int quantity = Integer.parseInt(quantityStr); // Attempt to parse quantity as an integer
    
                    // Attempt to dispense the medicine
                    boolean success = dispenseItems(medsName, quantity);
                    if (success) {
                        // Update the status to "Completed" if dispensing was successful
                        appointment[6] = "Dispensed";
    
                        // Convert the updated row to a List<String> and overwrite the row in the CSV file
                        List<String> updatedRow = Arrays.asList(appointment);
                        csvFileWriter.writeRow("AppointmentOutcomeRecord_List.csv", i, updatedRow);
    
                        System.out.println("Medicine dispensed successfully for appointment ID " + appointmentOutcomeID);
                    } else {
                        System.out.println("Failed to dispense medicine for appointment ID " + appointmentOutcomeID + ": insufficient stock.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Quantity for appointment ID " + appointmentOutcomeID + " is not a valid number: " + quantityStr);
                }
            }
        }
        System.out.println("All pending prescriptions processed.");
    }    


    // Method to submit a replenishment request
    public void submitReplenishmentRequest(String medsName, int quantityRequested, String requestedBy) {
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        String requestId = uuid.toString(); 
        String dateRequested = date.toString();
        List<String> request = Arrays.asList(requestId, medsName, String.valueOf(quantityRequested), requestedBy, dateRequested, "Pending", "", "");
        
        csvFileWriter.writeRow("Replenishment_Requests.csv", request);
        System.out.println("Replenishment request submitted for " + medsName);
    }
    
    // Method for admin to approve specific replenishment request
    public void approveReplenishmentRequest(String requestId, String approvedBy) {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
    
        System.out.println("Searching for request ID: " + requestId);
        
        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);
            
            if (request[0].equals(requestId) && request[5].equals("Pending")) {
                // Ensure the row has enough elements
                if (request.length < 8) {
                    String[] expandedRequest = new String[8];
                    System.arraycopy(request, 0, expandedRequest, 0, request.length);
                    for (int j = request.length; j < 8; j++) {
                        expandedRequest[j] = ""; // Fill remaining spots with empty strings
                    }
                    request = expandedRequest;
                }
    
                // Update values in the row
                request[5] = "Approved";
                request[6] = approvedBy;
                request[7] = java.time.LocalDate.now().toString();
                
                // Replace the modified row back in the requests list
                requests.set(i, request);
                
                // Update the stock in Medicine_List.csv
                restockItems(request[1], Integer.parseInt(request[2]));

                // Write the entire list back to Replenishment_Requests.csv
                List<String> newRequestData = Arrays.asList(request);
                csvFileWriter.writeRow("Replenishment_Requests.csv", i, newRequestData);
                System.out.println("Replenishment request " + requestId + " approved.");
                return;
            }
        }
        System.out.println("Request ID " + requestId + " not found, is not pending, or row format is incorrect.");
    }

    // Method for admin to approve all pending replenishment requests
    public void approveAllPendingRequests(String approvedBy) {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
        boolean foundPending = false;

        System.out.println("Approving all pending replenishment requests...");

        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);

            // Check if the request is pending
            if (request[5].equalsIgnoreCase("Pending")) {
                foundPending = true;

                // Ensure the row has enough elements
                if (request.length < 8) {
                    String[] expandedRequest = new String[8];
                    System.arraycopy(request, 0, expandedRequest, 0, request.length);
                    for (int j = request.length; j < 8; j++) {
                        expandedRequest[j] = ""; // Fill remaining spots with empty strings
                    }
                    request = expandedRequest;
                }

                // Update values in the row
                request[5] = "Approved";
                request[6] = approvedBy;
                request[7] = java.time.LocalDate.now().toString();

                // Replace the modified row back in the requests list
                requests.set(i, request);

                // Update the stock in Medicine_List.csv
                restockItems(request[1], Integer.parseInt(request[2]));
                System.out.println("Approved replenishment request for " + request[0] + "on" + request[1] + " with quantity " + request[2]);
            }
        }

        if (!foundPending) {
            System.out.println("No pending requests found.");
        } else {
            // Write the updated list back to Replenishment_Requests.csv
            for (int i = 0; i < requests.size(); i++) {
                List<String> updatedRequestData = Arrays.asList(requests.get(i));
                csvFileWriter.writeRow("Replenishment_Requests.csv", i, updatedRequestData);
            }
            System.out.println("All pending replenishment requests have been approved.");
        }
    }
}
