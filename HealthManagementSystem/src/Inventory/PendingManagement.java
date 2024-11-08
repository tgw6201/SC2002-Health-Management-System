package Inventory;

/**
 * The PendingManagement interface defines methods for managing items with a pending status.
 * 
 * <p>
 * This interface includes methods to view all items marked as pending and to process these 
 * items for approval. Implementations of this interface should handle updates to item status 
 * and log details of the approval process.
 * </p>
 * 
 * @author Sia Yi Zhen
 * @version 1.0
 * @since 2024-11-5
 */

public interface PendingManagement {
     /**
     * Displays or retrieves all items that are currently pending approval.
     * Implementing classes should provide a mechanism to display or return
     * the details of each pending item.
     */
    void viewPendingItems();
    /**
     * Processes and approve all items marked as pending.
     * The method updates each item’s status to indicate approval and records
     * the ID of the individual who approved the items.
     * 
     * @param approvedBy The ID of the individual responsible for approving the pending items.
     */
    void handleAllPending(String approvedBy);
}
