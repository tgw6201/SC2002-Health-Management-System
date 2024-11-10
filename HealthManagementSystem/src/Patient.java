import Appointment.*;
import FileManager.*;
import Logger.*;
import MedicalRecord.*;
import java.util.Scanner;

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
        logger.log("User logged in");//once enter, log user's login
        dataReader reader = new CsvFileReader();
        dataWriter writer = new CsvFileWriter();
        PatientMedicalRecordManager patientService =  new PatientMedicalRecordManager(reader, writer);
        MedicalRecordManagement patientMedRecord = new MedicalRecordManagement(patientService);
        AppointmentSlotManager slot = new AppointmentSlotManager(reader, writer);
        AppointmentOutcomeManager manager = new AppointmentOutcomeManager(reader, writer);
        AppointmentManager appointment = new AppointmentManager(reader,writer);
        Scanner sc = new Scanner(System.in);
        int choice = -1;
        String email;
        String number;
        String appointmentSlotID;
        String appointmentID;
        String newAppointmentSlotID;
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
            //choice = sc.nextInt();
            //sc.nextLine();
            try {
                choice = Integer.parseInt(sc.nextLine().trim()); // Parse choice as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 to 9.");
                continue; // Skip this iteration and prompt again
            }
            switch (choice)
            {
                case 1:
                    System.out.println("View medical record"); // wait rennie
                    logger.log("User viewed medical records");
                    patientMedRecord.viewMedicalRecord(userID);
                    break;
                case 2:
                    System.out.println("Update Personal Information"); // ask admin->SystemManagement to do so?
                    System.out.println("Enter email");
                    email= sc.nextLine();
                    System.out.println("Enter Contact Number"); 
                    number= sc.nextLine();
                    logger.log("User updated personal information");
                    patientMedRecord.updateMedicalRecord(userID,email,number);
                    break;
                case 3:
                    System.out.println("View Available Appointment slots"); //
                    logger.log("User viewed available appointment slots");
                    slot.viewAvailableAppointmentSlots();
                    break;
                case 4:
                    System.out.println("Schedule an Appointment"); // wait dr
                    System.out.println("Input appointment slot ID");
                    appointmentSlotID = sc.nextLine();
                    logger.log("User schedules an appointment");
                    appointment.scheduleAppointment(userID, appointmentSlotID);
                    break;
                case 5:
                    System.out.println("Reschedule an Appointment"); //wait dr
                    System.out.println("Input old appointment slot ID");
                    appointmentID = sc.nextLine();
                    System.out.println("Input new appointment slot ID");
                    newAppointmentSlotID = sc.nextLine();
                    logger.log("User reschedules an appointment");
                    appointment.rescheduleAppointment(appointmentID, newAppointmentSlotID);
                    break;
                case 6:
                    System.out.println("Cancel an Appointment");  //wait dr
                    System.out.println("Input appointment ID to be cancelled");
                    appointmentID = sc.nextLine();
                    logger.log("User cancels an appointment");
                    appointment.cancelAppointment(appointmentID);
                    break;
                case 7:
                    System.out.println("View Scheduled Appointments");
                    appointment.viewScheduleAppointment(userID);
                    logger.log("User view Scheduled Appointments");
                    break;
                case 8:
                    System.out.println("View Past Appointment Outcome Records");
                    logger.log ("User views past appointment outcome records");
                    manager.viewPastRecords(userID);
                    break;
                case 9:
                    System.out.println("Logging out...");//check with gw if need log here
                    return;                
                default:
                    System.out.println("Invalid choice");
                    logger.log ("User inputs an invalid choice");
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
