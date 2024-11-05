package Inventory;

public interface ReplenishmentService {
    void submitReplenishmentRequest(String itemName, int quantity, String requestedBy);
    void updateLowStockThreshold(String itemName, int newThreshold);
    boolean restockItemByName(String medicineName, int quantity);
    boolean restockItemByRequestID(String requestID, String approvedBy);
}
