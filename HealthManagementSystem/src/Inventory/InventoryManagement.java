package Inventory;

import FileManager.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class InventoryManagement {

    private List<String[]> inventoryData;
    CsvFileWriter csvFileWriter = new CsvFileWriter();
    CsvFileReader csvFileReader = new CsvFileReader();

    public InventoryManagement() {
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
    public boolean restockItems(String medsName, int quantity) {
        System.out.println("\nRestocking " + medsName + "....\n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                if (row[0].equalsIgnoreCase(medsName)) { 
                    int initialQuantity = Integer.parseInt(row[1]);
                    initialQuantity += quantity;
                    row[1] = String.valueOf(initialQuantity);  // Update the quantity in the list
                    System.out.println("Updated quantity of " + medsName + " to " + row[1]);

                    // Write the update to the CSV file
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

                        // Write the update to the CSV file
                        csvFileWriter.writeData("Medicine_List.csv", i, 1, row[1]);

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

    // Method to check low stock for each item
    public void checkLowStock() {
        System.out.println("\n Checking stock....\n");
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                int quantity = Integer.parseInt(row[1]);
                int threshold = Integer.parseInt(row[2]);
                if (quantity <= threshold) {
                    System.out.printf("Warning: Low stock for %s - %d units remaining\n", row[0], quantity);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in stock or threshold fields for " + row[0]);
            }
        }
    }

    // Method to submit a replenishment request
    public void submitRequest(String medsName, int quantityRequested, String requestedBy) {
        UUID uuid = UUID.randomUUID();
        LocalDate date = LocalDate.now();
        String requestId = uuid.toString(); 
        String dateRequested = date.toString(); // Implement this method to get the current date
        String[] request = {requestId, medsName, String.valueOf(quantityRequested), requestedBy, dateRequested, "Pending", "", ""};
        
        csvFileWriter.appendRow("Replenishment_Requests.csv", request);
        System.out.println("Replenishment request submitted for " + medsName);
    }
    
    // Method for admin to approve replenishment request
    public void approveRequest(String requestId, String approvedBy) {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
    
        System.out.println("Searching for request ID: " + requestId);
        
        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);
            
            if (request[0].equals(requestId) && request[5].equals("Pending")) {
                // Ensure the row has enough elements (SubmitRequest only have 6 elements)
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
                
                restockItems(request[1], Integer.parseInt(request[2]));
                csvFileWriter.overwriteFile("Replenishment_Requests.csv", requests);
                System.out.println("Replenishment request " + requestId + " approved.");
                return;
            }
        }
        System.out.println("Request ID " + requestId + " not found, is not pending, or row format is incorrect.");
    }
}
