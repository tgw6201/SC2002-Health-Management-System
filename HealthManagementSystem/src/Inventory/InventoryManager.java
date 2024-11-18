package Inventory;

import FileManager.*;

/**
 * The {@code InventoryManager} class provides a streamlined interface for managing inventory operations 
 * using the {@link ReplenishmentService} and {@link PendingManagement} interfaces.
 *
 * <p>
 * This class supports common inventory management tasks such as viewing items, submitting replenishment 
 * requests, restocking items, handling pending requests, and checking stock levels. It integrates 
 * file operations through the {@link dataReader} and {@link dataWriter} utilities.
 * </p>
 *
 * <p><b>Example Usage:</b></p>
 * <pre>
 * dataReader reader = new CsvFileReader();
 * dataWriter writer = new CsvFileWriter();
 * InventoryManagement inventoryManagement = new InventoryManagement(reader, writer);
 * InventoryManager manager = new InventoryManager(inventoryManagement, inventoryManagement, reader, writer);
 *
 * manager.viewItems();
 * manager.submitReplenishmentRequest("Paracetamol", 50, "Admin001");
 * boolean restocked = manager.restockItemByName("Ibuprofen", 30);
 * System.out.println("Restocked: " + restocked);
 * </pre>
 * 
 * @see ReplenishmentService
 * @see PendingManagement
 * @see DataProcessor
 * @see dataReader
 * @see dataWriter
 * 
 * @author Sia Yi Zhen
 * @version 1.1
 * @since 2024-11-18
 */

public class InventoryManager {

    private final ReplenishmentService replenishmentService;
    private final PendingManagement pendingManagement;
    private final DataProcessor dataProcessor;

    /**
     * Constructs an InventoryManager with the specified service interfaces and file processing utilities.
     *
     * @param replenishmentService An instance of {@link ReplenishmentService} to handle replenishment tasks.
     * @param pendingManagement    An instance of {@link PendingManagement} to manage pending requests.
     * @param dataReader           A {@link dataReader} instance for reading inventory data.
     * @param dataWriter           A {@link dataWriter} instance for writing inventory data.
     */
    public InventoryManager(ReplenishmentService replenishmentService, PendingManagement pendingManagement, dataReader dataReader, dataWriter dataWriter) {
        this.replenishmentService = replenishmentService;
        this.pendingManagement = pendingManagement;
        this.dataProcessor = new DataProcessor(dataReader, dataWriter);
    }
    /**
     * Displays all items in the inventory by using the {@link ReplenishmentService#viewItems()} method.
     */
    public void viewItems() {
        replenishmentService.viewItems();
    }
    /**
     * Submits a replenishment request for an item through the {@link ReplenishmentService#submitReplenishmentRequest(String, int, String)} method.
     *
     * @param itemName    The name of the item to replenish.
     * @param quantity    The quantity to request.
     * @param requestedBy The ID of the individual submitting the request.
     */
    public void submitReplenishmentRequest(String itemName, int quantity, String requestedBy) {
        replenishmentService.submitReplenishmentRequest(itemName, quantity, requestedBy);
    }
    /**
     * Updates the low stock threshold for an item using {@link ReplenishmentService#updateLowStockThreshold(String, int)}.
     *
     * @param itemName     The name of the item.
     * @param newThreshold The new threshold value.
     */
    public void updateLowStockThreshold(String itemName, int newThreshold) {
        replenishmentService.updateLowStockThreshold(itemName, newThreshold);
    }
    /**
     * Restocks an item by its name using {@link ReplenishmentService#restockItemByName(String, int)}.
     *
     * @param itemName The name of the item to restock.
     * @param quantity The quantity to add.
     * @return {@code true} if the restocking was successful; {@code false} otherwise.
     */
    public boolean restockItemByName(String itemName, int quantity) {
        return replenishmentService.restockItemByName(itemName, quantity);
    }
    /**
     * Restocks an item using its request ID with {@link ReplenishmentService#restockItemByRequestID(String, String)}.
     *
     * @param requestId  The ID of the replenishment request.
     * @param approvedBy The ID of the individual approving the request.
     * @return {@code true} if the restocking was successful; {@code false} otherwise.
     */
    public boolean restockItemByRequestID(String requestId, String approvedBy) {
        return replenishmentService.restockItemByRequestID(requestId, approvedBy);
    }
    /**
     * Displays all pending replenishment requests using {@link PendingManagement#viewPendingItems()}.
     */
    public void viewPendingItems() {
        pendingManagement.viewPendingItems();
    }
    /**
     * Processes all pending replenishment requests using {@link PendingManagement#handleAllPending(String)}.
     *
     * @param approvedBy The ID of the individual approving the requests.
     */
    public void handleAllPending(String approvedBy) {
        pendingManagement.handleAllPending(approvedBy);
    }
    /**
     * Checks whether a specific item is below its low stock threshold.
     * 
     * @param itemName The name of the item to check.
     */
    public void checkLowStockForItem(String itemName){
        replenishmentService.checkLowStockForItem(itemName);
    }
    /**
     * Retrieves the current stock level of a specific item.
     * 
     * @param medicineName The name of the item to retrieve the stock level for.
     * @return The current stock quantity of the specified item.
     */
    public int getStockLevel(String medicineName){
        return replenishmentService.getStockLevel(medicineName);
    }
}