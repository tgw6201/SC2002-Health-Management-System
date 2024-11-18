package Inventory;

import FileManager.*;

/**
 * The InventoryManager class provides a simplified interface for managing inventory operations.
 * It wraps around the {@link InventoryManagement} class and delegates calls to its methods.
 *
 * <p>This class allows users to perform inventory-related tasks, such as viewing items, 
 * handling replenishment requests, restocking items, and checking stock levels.</p>
 *
 * <p><b>Example usage:</b></p>
 * <pre>
 * InventoryManagement inventoryManagement = new InventoryManagement(reader, writer);
 * InventoryManager manager = new InventoryManager(inventoryManagement);
 * 
 * manager.viewItems();
 * manager.submitReplenishmentRequest("Paracetamol", 50, "Admin001");
 * boolean restocked = manager.restockItemByName("Ibuprofen", 30);
 * System.out.println("Restocked: " + restocked);
 * </pre>
 *
 * @see InventoryManagement
 * 
 * @author Sia Yi Zhen
 * @version 1.0
 * @since 2024-11-17
 */
public class InventoryManager {

    private final InventoryManagement inventoryManagement;
    private final DataProcessor dataProcessor;

    /**
     * Constructs an InventoryManager with the specified InventoryManagement instance.
     *
     * @param inventoryManagement The {@link InventoryManagement} instance used to delegate operations.
     */
    public InventoryManager(InventoryManagement inventoryManagement, dataReader dataReader, dataWriter dataWriter) {
        dataProcessor = new DataProcessor(dataReader, dataWriter);
        this.inventoryManagement = inventoryManagement;
    }

    /**
     * Displays all items in the inventory by delegating to {@link InventoryManagement#viewItems()}.
     */
    public void viewItems() {
        inventoryManagement.viewItems();
    }

    /**
     * Submits a replenishment request for an item by delegating to 
     * {@link InventoryManagement#submitReplenishmentRequest(String, int, String)}.
     *
     * @param itemName    The name of the item to replenish.
     * @param quantity    The quantity to request.
     * @param requestedBy The ID of the individual submitting the request.
     */
    public void submitReplenishmentRequest(String itemName, int quantity, String requestedBy) {
        inventoryManagement.submitReplenishmentRequest(itemName, quantity, requestedBy);
    }

    /**
     * Updates the low stock threshold for an item by delegating to 
     * {@link InventoryManagement#updateLowStockThreshold(String, int)}.
     *
     * @param itemName     The name of the item.
     * @param newThreshold The new threshold value.
     */
    public void updateLowStockThreshold(String itemName, int newThreshold) {
        inventoryManagement.updateLowStockThreshold(itemName, newThreshold);
    }

    /**
     * Restocks an item by its name by delegating to 
     * {@link InventoryManagement#restockItemByName(String, int)}.
     *
     * @param itemName The name of the item to restock.
     * @param quantity The quantity to add.
     * @return {@code true} if the restocking was successful; {@code false} otherwise.
     */
    public boolean restockItemByName(String itemName, int quantity) {
        return inventoryManagement.restockItemByName(itemName, quantity);
    }

    /**
     * Restocks an item using its replenishment request ID by delegating to 
     * {@link InventoryManagement#restockItemByRequestID(String, String)}.
     *
     * @param requestId  The ID of the replenishment request.
     * @param approvedBy The ID of the individual approving the request.
     * @return {@code true} if the restocking was successful; {@code false} otherwise.
     */
    public boolean restockItemByRequestID(String requestId, String approvedBy) {
        return inventoryManagement.restockItemByRequestID(requestId, approvedBy);
    }

    /**
     * Checks if an item has low stock by delegating to 
     * {@link InventoryManagement#checkLowStockForItem(String)}.
     *
     * @param itemName The name of the item to check.
     */
    public void checkLowStockForItem(String itemName) {
        inventoryManagement.checkLowStockForItem(itemName);
    }

    /**
     * Retrieves the stock level of a specific item by delegating to 
     * {@link InventoryManagement#getStockLevel(String)}.
     *
     * @param medicineName The name of the medicine to check.
     * @return The current stock level of the specified item.
     */
    public int getStockLevel(String medicineName) {
        return inventoryManagement.getStockLevel(medicineName);
    }

    /**
     * Displays all pending replenishment requests by delegating to 
     * {@link InventoryManagement#viewPendingItems()}.
     */
    public void viewPendingItems() {
        inventoryManagement.viewPendingItems();
    }

    /**
     * Handles and processes all pending replenishment requests by delegating to 
     * {@link InventoryManagement#handleAllPending(String)}.
     *
     * @param approvedBy The ID of the individual approving the requests.
     */
    public void handleAllPending(String approvedBy) {
        inventoryManagement.handleAllPending(approvedBy);
    }
}
