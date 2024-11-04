package Inventory;

public interface InventoryOperations {
    void viewItems();
    boolean restockItems(String medsName, int quantity);
    boolean dispenseItems(String medsName, int quantity);
    void checkLowStock();
    void submitReplenishmentRequest(String medsName, int quantityRequested, String requestedBy);
    void approveReplenishmentRequest(String requestId, String approvedBy);
}
