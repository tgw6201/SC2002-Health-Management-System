package Inventory;

/**
 * The {@code ReplenishmentService} interface defines methods for managing inventory replenishment operations.
 * 
 * <p>
 * This interface includes functionality for submitting replenishment requests, updating stock thresholds,
 * restocking items either by their names or replenishment request IDs, and checking stock levels. It is
 * designed to support inventory control systems by providing a structured way to maintain adequate stock levels.
 * </p>
 * 
 * <p>
 * Implementations of this interface should handle the inventory update process, ensuring accuracy in stock 
 * management and addressing low stock situations effectively.
 * </p>
 * 
 * @see InventoryManagement
 * @see PendingManagement
 * 
 * @author Sia Yi Zhen
 * @version 1.0
 * @since 2024-11-5
 */

public interface ReplenishmentService {

     /**
     * Displays all items in the inventory with their current stock levels.
     */
    void viewItems();
    /**
     * Submits a new replenishment request for an item.
     * This request includes the item name, quantity requested, and the ID of the individual 
     * who made the request. The request is initially set to "Pending" status.
     * 
     * @param itemName   The name of the item to be replenished.
     * @param quantity   The quantity requested for replenishment.
     * @param requestedBy The ID of the person submitting the replenishment request.
     */
    void submitReplenishmentRequest(String itemName, int quantity, String requestedBy);
    /**
     * Updates the low stock threshold for a specific item in the inventory.
     * This threshold determines when an item is considered low on stock.
     * 
     * @param itemName     The name of the item for which the low stock threshold is to be updated.
     * @param newThreshold The new low stock threshold quantity for the item.
     */
    void updateLowStockThreshold(String itemName, int newThreshold);
    /**
     * Restocks an item by the specified quantity.
     * This method directly increases the current stock of an item by the given quantity.
     * 
     * @param medicineName The name of the item to be restocked.
     * @param quantity     The quantity to add to the current stock level.
     * @return {@code true} if the item was successfully restocked; 
     *         {@code false} if the item was not found in the inventory.
     */
    boolean restockItemByName(String medicineName, int quantity);
    /**
     * Approves a replenishment request based on the unique request ID and updates the inventory.
     * The request’s status is changed to "Approved," and the approval is recorded with the 
     * approving individual's ID.
     * 
     * @param requestID   The unique identifier of the replenishment request.
     * @param approvedBy  The ID of the individual approving the request.
     * @return {@code true} if the request was successfully processed; 
     *         {@code false} if the request was not found or has already been processed.
     */
    boolean restockItemByRequestID(String requestID, String approvedBy);
    /**
     * Checks if a specific item in the inventory is below its low stock threshold.
     * 
     * @param itemName The name of the item to check.
     */
    void checkLowStockForItem(String itemName);
    /**
     * Retrieves the current stock level of a specific item in the inventory.
     * 
     * @param medicineName The name of the item to retrieve the stock level for.
     * @return The current stock quantity of the specified item.
     */
    int getStockLevel(String medicineName);
}
