import java.util.Scanner;
public class Patient 
{

    public Patient() 
    {
        
    }
    
    public void menu()
    {
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        while (choice !=9)
        {
            System.out.println("Input a choice from 1 to 9");
            System.out.println("[1] View Medical Record");
            System.out.println("[2] Update Personal Information");
            System.out.println("[3] View Available Appointment Slots");
            System.out.println("[4] Schedule an Appointment");
            System.out.println("[5] Reschedule an Appointment");
            System.out.println("[6] Cancel an Appointment");
            System.out.println("[7] View Scheduled Appointments");
            System.out.println("[8] View Past Appointment Outcome Records");
            System.out.println("[9] Logout");
            switch (choice)
            {
                case 1:
                    System.out.println("View medical record"); // wait rennie
                    break;
                case 2:
                    System.out.println("Update Personal Information"); // ask admin->SystemManagement to do so?
                    break;
                case 3:
                    System.out.println("View Available Appointment slots"); //
                    break;
                case 4:
                    System.out.println("Schedule an Appointment"); // wait dr
                    break;
                case 5:
                    System.out.println("Reschedule an Appointment"); //wait dr
                    break;
                case 6:
                    System.out.println("Cancel an Appointment");  //wait dr
                    break;
                case 7:
                    System.out.println("View Scheduled Appointments");
                    break;
                case 8:
                    System.out.println("View Past Appointment Outcome Recorrds");
                    break;
                case 9:
                    System.out.println("Logout");
                    break;                
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }
}
