package Inventory;

import FileManager.*;
import java.util.List;

public class InventoryManagement {

   private List <String[]> inventoryData;
   CsvFileWriter csvFileWriter = new CsvFileWriter();
   CsvFileReader csvFileReader = new CsvFileReader();
    
    public InventoryManagement(){
        inventoryData = csvFileReader.readData("Medicine_List.csv");
    }
    
    public void viewItems(){
        for (String[]row:inventoryData) {
            for(String cell:row){
                System.out.printf(cell + "\n");
            }
        }
    }

    public boolean restockItems(String medsName, int quantity){
        for (String[]row:inventoryData) {
            for(String cell:row){
                System.out.println(cell + "\n");
                if(row[0].equals(medsName)){
                    //csvFileWriter.writeData("Medicine_List.csv", "2", "1", "100");

                    /*
                    int initialQuantity = Integer.parseInt(row[1]);
                    initialQuantity += quantity;
                    row[1] = String.valueOf(initialQuantity);  // Update the quantity in the list
                    System.out.println("Updated quantity of " + medsName + " to " + row[1]);
                    */
                    return true;
                }
            }
        }
        return false;
    }

    public boolean dispenseItems(String medsName, int quantity){
        return false;
    }
}
