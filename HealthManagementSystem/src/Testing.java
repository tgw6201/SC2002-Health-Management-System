import Inventory.*;

public class Testing {
    public static void main(String[] args) {
        
        InventoryManagement inventoryManagement = new InventoryManagement();
        PrescriptionManagement prescriptionManagement = new PrescriptionManagement(inventoryManagement);

        //inventoryManagement.viewItems();
        //inventoryManagement.submitReplenishmentRequest("Paracetamol", 10, "P001");
        //inventoryManagement.updateLowStockThreshold("Ibuprofen", 20);
        //inventoryManagement.restockItemByName("Ibuprofen", 10);
        //inventoryManagement.restockItemByRequestID("d29eaf54-f13a-40b9-8ff6-2cdf0facd7b8", "A001");
        //inventoryManagement.checkLowStockForItem("Paracetamol");
        //inventoryManagement.viewPendingItems();
        //inventoryManagement.handleAllPending("A001");

        //prescriptionManagement.viewPendingItems();
        //prescriptionManagement.handleAllPending("P001");
        //prescriptionManagement.dispenseItemByAppointmentID("1001AB25");
        //prescriptionManagement.dispenseItemByMedicineName("Paracetamol", 20);
        prescriptionManagement.showAllAppointments();

        //CsvFileWriter csvFileWriter = new CsvFileWriter();
        //csvFileWriter.writeData("Medicine_List.csv", "2", "1", "5");
        
        /* 
        String username;
        String password;
        Scanner sc = new Scanner(System.in);
        FileLogger logger;
        System.out.println("Hospital Management System");
        System.out.println("Username: ");
        username = sc.nextLine();
        logger = new FileLogger(username+".txt");
        System.out.println("Password: ");
        password = sc.nextLine();
        UserLoginServices userLoginServices = new UserLoginServices(logger);
        boolean login = userLoginServices.login(username, password);
        System.out.println("Login Status:" + login);
        System.out.println("Role: " + userLoginServices.getRole());
        
        
        
        //when patient is accessing information
        InformationAccessManager AccessManager = new InformationAccessManager();
        boolean x = AccessManager.checkInformationAccess("Blood Type");
        System.out.println(x);
        */
        //Testing of CSV File reader

        /*
        CsvFileReader csvFileReader = new CsvFileReader();
        List<String[]> data = csvFileReader.readData("User_Accounts.csv");

        if(data.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (String[] row : data) {
            for (String cell : row) {
                System.out.print(cell + "\t");
            }
            System.out.println();
        }


        List<String[]> column = csvFileReader.readColumn("User_Accounts.csv", "0");
        if(column.isEmpty()) {
            System.out.println("No data found");
            return;
        }
        for (String[] cell : column) {
            System.out.print(cell[0] + ",");
        } */

    }
}
