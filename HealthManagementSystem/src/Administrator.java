//import FileManager.*;

public class Administrator extends  User
{
    private String userID;
    private String userRole;
    private Logger logger;
    
    Administrator(String userID, String userRole, Logger logger)
    {
        this.userID= userID;
        this.userRole = userRole;
        this.logger = logger;
    }
    public void menu()
    {
        System.out.println("Upcast with parameter successful "+ getUserID() + getRole()); //check that upcast is successful
        logger.log(); //to continue logging from  
        /*dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        hospitalStaffManagement staffManagement = new hospitalStaffManagement(reader, writer);
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        while(choice != 5){
            System.out.println("Input a choice from 1 to 5");
            System.out.println("1. Add Staff");
            System.out.println("2. Remove Staff");
            System.out.println("3. Update Staff");
            System.out.println("4. Filtered View");
            System.out.println("5. Exit");
            choice = sc.nextInt();
            sc.nextLine();
            if(choice == 1)
            {
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                System.out.println("Enter Staff Name:");
                String staffName = sc.nextLine();
                System.out.println("Enter Staff Role:");
                String staffRole = sc.nextLine();
                System.out.println("Enter Staff gender:");
                String staffGender = sc.nextLine();
                System.out.println("Enter Staff Age:");
                int staffAge = sc.nextInt();
                sc.nextLine();
                staffManagement.addStaff(staffID, staffName, staffRole, staffGender, staffAge);
            }
            if(choice == 2){
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                staffManagement.removeStaff(staffID);
            }
            if(choice == 3){
                System.out.println("Enter Staff ID:");
                String staffID = sc.nextLine();
                System.out.println("Enter Staff Name:");
                String staffName = sc.nextLine();
                System.out.println("Enter Staff Role:");
                String staffRole = sc.nextLine();
                System.out.println("Enter Staff gender:");
                String staffGender = sc.nextLine();
                System.out.println("Enter Staff Age:");
                int staffAge = sc.nextInt();
                sc.nextLine();
                staffManagement.updateStaff(staffID, staffName, staffRole, staffGender, staffAge);
            }
            if(choice == 4)
            {
                System.out.println("Enter Category:");
                String category = sc.nextLine();
                System.out.println("Enter Type:");
                String type = sc.nextLine();
                staffManagement.filteredView(category, type);
            }
            else
            {
                System.out.println("Invalid choice");
            }
            */

    }

    public String getUserID()
    {
        return userID;
    }
    public String getRole()
    {
        return userRole;
    }
}
