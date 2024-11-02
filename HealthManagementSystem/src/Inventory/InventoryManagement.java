package Inventory;

import FileManager.*;
import java.util.List;

public class InventoryManagement {

    private List<String[]> inventoryData;
    CsvFileWriter csvFileWriter = new CsvFileWriter();
    CsvFileReader csvFileReader = new CsvFileReader();

    public InventoryManagement() {
        inventoryData = csvFileReader.readData("Medicine_List.csv");
    }

    public void viewItems() {
        // Start from the second row if the first row is a header

        System.out.println("Viewing Medicine Inventory.... \n");

        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            for (String cell : row) {
                System.out.println(cell);
            }
            System.out.println("----------");  // Divider between rows
        }
    }

    public boolean restockItems(String medsName, int quantity) {
        // Start from the second row if the first row is a header

        System.out.println("Restocking " + medsName + "....\n");

        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                if (row[0].equalsIgnoreCase(medsName)) { 
                    int initialQuantity = Integer.parseInt(row[1]);
                    initialQuantity += quantity;
                    row[1] = String.valueOf(initialQuantity);  // Update the quantity in the list
                    System.out.println("Updated quantity of " + medsName + " to " + row[1]);
                    return true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in quantity field for " + row[0]);
            }
        }
        System.out.println("Medication not found: " + medsName);
        return false;
    }

    public boolean dispenseItems(String medsName, int quantity) {

        System.out.println("Dispensing" + medsName + "....\n");

        // Start from the second row if the first row is a header
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                if (row[0].equalsIgnoreCase(medsName)) {
                    int initialQuantity = Integer.parseInt(row[1]);
                    if (initialQuantity >= quantity) {
                        initialQuantity -= quantity;
                        row[1] = String.valueOf(initialQuantity);
                        System.out.println("Dispensed " + quantity + " of " + medsName);
                        System.out.println("Updated quantity of " + medsName + " to " + row[1]);
                        return true;
                    } else {
                        System.out.println("Not enough stock to dispense " + quantity + " of " + medsName);
                        return false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in quantity field for " + row[0]);
            }
        }
        System.out.println("Medication not found: " + medsName);
        return false;
    }

    public void checkLowStock() {

        System.out.println("Checking stock....\n");

        // Start from the second row if the first row is a header
        for (int i = 1; i < inventoryData.size(); i++) {
            String[] row = inventoryData.get(i);
            try {
                int quantity = Integer.parseInt(row[1]);
                int threshold = Integer.parseInt(row[2]);
                if (quantity <= threshold) {
                    System.out.printf("Warning: Low stock for %s - %d units remaining\n", row[0], quantity);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Non-numeric data found in stock or threshold fields for " + row[0]);
            }
        }
    }
}
