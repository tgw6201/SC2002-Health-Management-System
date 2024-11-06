package Inventory;

import FileManager.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class InventoryManagement implements ReplenishmentService, PendingManagement {

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

    @Override
    public void submitReplenishmentRequest(String itemName, int quantity, String requestedBy) {
        UUID requestId = UUID.randomUUID();
        String dateRequested = LocalDate.now().toString();
        List<String> request = Arrays.asList(
                requestId.toString(), itemName, String.valueOf(quantity), requestedBy, dateRequested, "Pending", "", "");
        csvFileWriter.writeRow("Replenishment_Requests.csv", request);
        System.out.println("Replenishment request submitted for " + itemName);
    }

    @Override
    public void updateLowStockThreshold(String itemName, int newThreshold) {
        System.out.println("\nUpdating threshold for " + itemName + "....\n");
        for (int i = 0; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            if (row[0].equalsIgnoreCase(itemName)) {
                row[2] = String.valueOf(newThreshold);
                csvFileWriter.writeData("Medicine_List.csv", i, 2, row[2]);
                System.out.println("Updated threshold for " + itemName + " to " + row[2]);
                return;
            }
        }
        System.out.println("Item not found in inventory: " + itemName);
    }

    @Override
    public boolean restockItemByName(String medicineName, int quantity) {
        for (int i = 0; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            if (row[0].equalsIgnoreCase(medicineName)) {
                int currentQuantity = Integer.parseInt(row[1]);
                row[1] = String.valueOf(currentQuantity + quantity);
                csvFileWriter.writeData("Medicine_List.csv", i, 1, row[1]);
                System.out.println("Restocked " + medicineName + " to " + row[1] + " units.");
                return true;
            }
        }
        System.out.println("Medicine not found: " + medicineName);
        return false;
    }

    @Override
    public boolean restockItemByRequestID(String requestID, String approvedBy) {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);
            if (request[0].equals(requestID) && "Pending".equalsIgnoreCase(request[5])) {

                // Ensure the row has enough elements
                if (request.length < 8) {
                    String[] expandedRequest = new String[8];
                    System.arraycopy(request, 0, expandedRequest, 0, request.length);
                    for (int j = request.length; j < 8; j++) {
                        expandedRequest[j] = ""; // Fill remaining spots with empty strings
                    }
                    request = expandedRequest;
                }

                request[5] = "Approved";
                request[6] = approvedBy;
                request[7] = LocalDate.now().toString();
                csvFileWriter.writeRow("Replenishment_Requests.csv", i, Arrays.asList(request));
                restockItemByName(request[1], Integer.parseInt(request[2]));
                System.out.println("Replenishment request " + requestID + " approved.");
                return true;
            }
        }
        System.out.println("Request ID not found or already processed: " + requestID);
        return false;
    }

    // Method to check if a specific item is low on stock
    public void checkLowStockForItem(String itemName) {
        for (String[] row : inventoryData) {
            if (row[0].equalsIgnoreCase(itemName)) {
                int quantity = Integer.parseInt(row[1]);
                int threshold = Integer.parseInt(row[2]);
                if (quantity <= threshold) {
                    System.out.printf("Warning: Low stock for %s - %d units remaining\n", itemName, quantity);
                }
                return;
            }
        }
        System.out.println("Item not found in inventory for low stock check: " + itemName);
    }

    @Override
    public void viewPendingItems() {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
        System.out.println("\nViewing all pending replenishment requests:\n");
        for (String[] request : requests) {
            if ("Pending".equalsIgnoreCase(request[5])) {
                System.out.println(Arrays.toString(request));
            }
        }
    }

    @Override
    public void handleAllPending(String approvedBy) {
        List<String[]> requests = csvFileReader.readData("Replenishment_Requests.csv");
        for (int i = 0; i < requests.size(); i++) {
            String[] request = requests.get(i);
            if ("Pending".equalsIgnoreCase(request[5])) {

                // Ensure the row has enough elements
                if (request.length < 8) {
                    String[] expandedRequest = new String[8];
                    System.arraycopy(request, 0, expandedRequest, 0, request.length);
                    for (int j = request.length; j < 8; j++) {
                        expandedRequest[j] = ""; // Fill remaining spots with empty strings
                    }
                    request = expandedRequest;
                }

                request[5] = "Approved";
                request[6] = approvedBy;
                request[7] = LocalDate.now().toString();
                csvFileWriter.writeRow("Replenishment_Requests.csv", i, Arrays.asList(request));
                restockItemByName(request[1], Integer.parseInt(request[2]));
                System.out.println("Approved and processed pending request ID: " + request[0]);
            }
        }
        System.out.println("All pending replenishment requests have been processed.");
    }
}
