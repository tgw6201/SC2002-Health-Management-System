package Inventory;

/**
 * The ReplenishmentService interface provides methods for managing inventory replenishment.
 * 
 * <p>
 * This interface includes methods for submitting replenishment requests, updating stock thresholds,
 * and restocking items either by item name or by specific replenishment request IDs. Implementations 
 * of this interface should handle the inventory update process and manage stock quantities based on 
 * these operations.
 * </p>
 * 
 * <p>
 * This interface is designed to support systems that require inventory control and restocking
 * to maintain stock levels.
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

    void checkLowStockForItem(String itemName);

    int getStockLevel(String medicineName);
}
