import java.util.Scanner;
import Logger.*;
import FileManager.*;
import Appointment.*;

public class Patient 
{
    private String userID;
    private String userRole;
    private Logger logger;


    public Patient(String userID, String userRole, Logger logger)
    {
        this.userID= userID;
        this.userRole = userRole;
        this.logger = logger;
    }
    
    public void menu()
    {   
        logger.log("User logged in");//once enter, log the file
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        PatientMedicalRecordManager patientService =  new PatientMedicalRecordManager(reader, writer);
        MedicalRecordManagement patientMedRecord = new MedicalRecordManagement(patientService);
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (choice !=9)
        {
            System.out.println("Input a choice from 1 to 9");
            System.out.println("1. View Medical Record");
            System.out.println("2. Update Personal Information");
            System.out.println("3. View Available Appointment Slots");
            System.out.println("4. Schedule an Appointment");
            System.out.println("5. Reschedule an Appointment");
            System.out.println("6. Cancel an Appointment");
            System.out.println("7. View Scheduled Appointments");
            System.out.println("8. View Past Appointment Outcome Records");
            System.out.println("9. Logout");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice)
            {
                case 1:
                    System.out.println("View medical record"); // wait rennie
                    MedicalRecordManager medical = new MedicalRecordManager();
                   //Check case 1 and 9
                    medical.viewMedicalRecord("P1001"); //how to input patient ID?
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
        logger.stopLogging();
        
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
