package Inventory;

import FileManager.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * The InventoryManagement class provides comprehensive functionality for managing 
 * inventory items, including viewing, submitting replenishment requests, restocking, 
 * and handling pending requests.
 * 
 * <p>
 * This class implements both {@link ReplenishmentService} and {@link PendingManagement}
 * interfaces, allowing it to manage items that are pending approval, as well as items
 * that need restocking or have low stock thresholds.
 * </p>
 * 
 * <p>
 * It interacts with {@link dataProcessor} to manage inventory 
 * data and store replenishment requests. This class can be extended to support additional
 * inventory-related functionalities.
 * </p>
 * 
 * <p><b>Note:</b> Ensure "Medicine_List.csv" and "Replenishment_Requests.csv" files are 
 * available for correct functionality.</p>
 * 
 * @see ReplenishmentService
 * @see PendingManagement
 * @see dataProcessor
 * @see dataProcessor
 * 
 * @author Sia Yi Zhen
 * @version 1.0
 * @since 2024-11-6
 */

public class InventoryManagement implements ReplenishmentService, PendingManagement {

    private final List<String[]> inventoryData;
    private final DataProcessor dataProcessor;
    /**
     * Constructs an InventoryManagement instance and loads all inventory data 
     * from the "Medicine_List.csv" file at initialization.
     */
    public InventoryManagement(dataReader dataReader, dataWriter dataWriter) {
        // Load all inventory data at initialization
        dataProcessor = new DataProcessor(dataReader, dataWriter);
        inventoryData = dataProcessor.readData("Medicine_List.csv");
    }

    /**
     * Displays all items in the inventory by reading from "Medicine_List.csv".
     */
    @Override
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

    /**
     * Submits a new replenishment request, specifying the item name, requested quantity,
     * and requester’s ID. Generates a unique request ID and sets the initial status as "Pending".
     * 
     * @param itemName   The name of the item to replenish.
     * @param quantity   The quantity requested.
     * @param requestedBy The ID of the individual who requested the replenishment.
     */
    @Override
    public void submitReplenishmentRequest(String itemName, int quantity, String requestedBy) {
        UUID requestId = UUID.randomUUID();
        String dateRequested = LocalDate.now().toString();
        List<String> request = Arrays.asList(
                requestId.toString(), itemName, String.valueOf(quantity), requestedBy, dateRequested, "Pending", "", "");
        dataProcessor.writeRow("Replenishment_Requests.csv", request);
        System.out.println("Replenishment request submitted for " + itemName);
    }

    /**
     * Updates the low stock threshold for a specified item.
     * 
     * @param itemName     The name of the item whose threshold is being updated.
     * @param newThreshold The new low stock threshold for the item.
     */
    @Override
    public void updateLowStockThreshold(String itemName, int newThreshold) {
        System.out.println("\nUpdating threshold for " + itemName + "....\n");
        for (int i = 0; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            if (row[0].equalsIgnoreCase(itemName)) {
                row[2] = String.valueOf(newThreshold);
                dataProcessor.writeData("Medicine_List.csv", i, 2, row[2]);
                System.out.println("Updated threshold for " + itemName + " to " + row[2]);
                return;
            }
        }
        System.out.println("Item not found in inventory: " + itemName);
    }

     /**
     * Restocks a specified quantity of a medicine by its name.
     * 
     * @param medicineName The name of the medicine to restock.
     * @param quantity     The quantity to add to the current stock.
     * @return {@code true} if the medicine was restocked successfully; 
     *         {@code false} if the medicine was not found in the inventory.
     */
    @Override
    public boolean restockItemByName(String medicineName, int quantity) {
        for (int i = 0; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            if (row[0].equalsIgnoreCase(medicineName)) {
                int currentQuantity = Integer.parseInt(row[1]);
                row[1] = String.valueOf(currentQuantity + quantity);
                dataProcessor.writeData("Medicine_List.csv", i, 1, row[1]);
                System.out.println("Restocked " + medicineName + " to " + row[1] + " units.");
                return true;
            }
        }
        System.out.println("Medicine not found: " + medicineName);
        return false;
    }

    /**
     * Approves a replenishment request by its ID and updates the inventory stock.
     * 
     * @param requestID  The unique identifier for the replenishment request.
     * @param approvedBy The ID of the person approving the request.
     * @return {@code true} if successfully processed; 
     *         {@code false} if not found or already processed.
     */
    @Override
    public boolean restockItemByRequestID(String requestID, String approvedBy) {
        List<String[]> requests = dataProcessor.readData("Replenishment_Requests.csv");
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
                dataProcessor.writeRow("Replenishment_Requests.csv", i, Arrays.asList(request));
                restockItemByName(request[1], Integer.parseInt(request[2]));
                System.out.println("Replenishment request " + requestID + " approved.");
                return true;
            }
        }
        System.out.println("Request ID not found or already processed: " + requestID);
        return false;
    }

    /**
     * Checks if a specific item has low stock based on its threshold.
     * 
     * @param itemName The name of the item to check.
     */
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

    /**
     * Retrieves the current stock level for a specified medicine.
     * 
     * <p>This method searches the inventory data for the specified medicine and returns
     * its current quantity in stock. If the medicine is not found, it returns 0 to indicate
     * no stock available.</p>
     * 
     * @param medicineName The name of the medicine to check the stock level for.
     * @return The current stock level of the specified medicine, or {@code 0} if the medicine is not found.
     */
    public int getStockLevel(String medicineName) {
        for (String[] row : inventoryData) {
            if (row[0].equalsIgnoreCase(medicineName)) {
                return Integer.parseInt(row[1]);  // Returns the current quantity in stock
            }
        }
        return 0;  // If item not found, return 0 to indicate no stock available
    }

    /**
     * Displays all pending replenishment requests by reading from "Replenishment_Requests.csv".
     */
    @Override
    public void viewPendingItems() {
        List<String[]> requests = dataProcessor.readData("Replenishment_Requests.csv");
        System.out.println("\nViewing all pending replenishment requests:\n");
        for (String[] request : requests) {
            if ("Pending".equalsIgnoreCase(request[5])) {
                System.out.println(Arrays.toString(request));
            }
        }
    }

    /**
     * Processes and approves all pending replenishment requests, updating the stock levels 
     * and marking each request as approved by the specified individual.
     * 
     * @param approvedBy The ID of the individual approving the pending requests.
     */
    @Override
    public void handleAllPending(String approvedBy) {
        List<String[]> requests = dataProcessor.readData("Replenishment_Requests.csv");
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
                dataProcessor.writeRow("Replenishment_Requests.csv", i, Arrays.asList(request));
                restockItemByName(request[1], Integer.parseInt(request[2]));
                System.out.println("Approved and processed pending request ID: " + request[0]);
            }
        }
        System.out.println("All pending replenishment requests have been processed.");
    }
}
