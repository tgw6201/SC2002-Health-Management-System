package Inventory;

public interface InventoryOperations {
    void viewItems();
    boolean restockItems(String medsName, int quantity);
    boolean dispenseItems(String medsName, int quantity);
    void checkLowStock();
    void submitRequest(String medsName, int quantityRequested, String requestedBy);
    void approveRequest(String requestId, String approvedBy);
}
