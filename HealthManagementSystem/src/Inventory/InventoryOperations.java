package Inventory;

public interface InventoryOperations {
    void viewItems();
    boolean restockItems(String medsName, int quantity);
    boolean dispenseItems(String medsName, int quantity);
}
