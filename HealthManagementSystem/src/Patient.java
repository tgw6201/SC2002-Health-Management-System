import Appointment.*;
import FileManager.*;
import Logger.*;
import MedicalRecord.*;
import java.util.Scanner;

/**
 * The Patient class represents a patient user within the Hospital Management System.
 * It provides a menu-driven interface to allow patients to view and manage their medical records,
 * schedule and manage appointments, and update personal information.
 * 
 * This class interacts with multiple components such as:
 * - DataProcessor: for reading and writing data.
 * - PatientMedicalRecordManager: for managing patient medical records.
 * - AppointmentSlotManager: for viewing available appointment slots.
 * - AppointmentManager: for scheduling, rescheduling, and canceling appointments.
 * - AppointmentOutcomeManager: for viewing past appointment outcome records.
 * 
 * Logging of patient actions is handled through the Logger interface.
 */
public class Patient 
{
    private String userID;
    private String userRole;
    private Logger logger;

    //Object pointer initialization
    private Scanner sc = new Scanner(System.in);
    private DataProcessor dataProcessor;
    private PatientMedicalRecordManager patientService;
    private MedicalRecordManagement patientMedRecord;
    private AppointmentSlotManager slot;
    private AppointmentManager appointment;
    private AppointmentOutcomeManager manager;
        
    /**
     * Constructs a Patient instance with necessary components for accessing and managing
     * patient records and appointments.
     * 
     * @param userID the unique identifier of the patient user.
     * @param userRole the role of the user, specifically "Patient" for this class.
     * @param logger the logger to log actions performed by the patient.
     * @param reader the data reader for accessing file data.
     * @param writer the data writer for saving updates to file data.
     */
    public Patient(String userID, String userRole, Logger logger, dataReader reader, dataWriter writer)
    {
        this.userID= userID;
        this.userRole = userRole;
        this.logger = logger;
        this.dataProcessor = new DataProcessor(reader,writer);
        this.patientService = new PatientMedicalRecordManager(reader, writer);
        this.patientMedRecord = new MedicalRecordManagement(patientService);
        this.slot = new AppointmentSlotManager(reader, writer);
        this.appointment = new AppointmentManager(reader,writer);
        this.manager = new AppointmentOutcomeManager(reader, writer);
    }
    /**
     * Displays the main menu for the patient user and handles various patient operations
     * based on the user's input.
     */
    public void menu()
    {   
        logger.log("Patient " + userID + " logged in");//once enter, log user's login
        int choice = -1;
        while (choice !=9)
        {
            System.out.println("Patient Menu: ");
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
                    viewMedicalRecord();
                    break;
                case 2:
                    updatePersonalInformation();
                    break;
                case 3:
                    viewAvailableAppointments();
                    break;
                case 4:
                    scheduleAppointment();
                    break;
                case 5:
                    rescheduleAppointments();
                    break;
                case 6:
                    cancelAppointment();
                    break;
                case 7:
                    viewScheduledAppointments();
                    break;
                case 8:
                    viewPastAppointmentOutcomeRecords();
                    break;
                case 9:
                    break;                
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }
    /**
     * Displays the patient's medical records.
     */
    public void viewMedicalRecord()
    {
        
        
        System.out.println("View medical record");
        logger.log("User viewed medical records");
        patientMedRecord.viewMedicalRecord(userID);
    }
    /**
     * Allows the patient to update personal information such as email and contact number.
     */
    public void updatePersonalInformation()
    {
        String email;
        String number;
        System.out.println("Update Personal Information");
        System.out.println("Enter email");
        email= sc.nextLine();
        System.out.println("Enter Contact Number"); 
        number= sc.nextLine();
        logger.log("User updated personal information");
        patientMedRecord.updateMedicalRecord(userID,email,number);
    }
    /**
     * Displays available appointment slots for scheduling.
     */
    public void viewAvailableAppointments()
    {
        System.out.println("View Available Appointment slots");
        logger.log("User viewed available appointment slots");
        slot.viewAvailableAppointmentSlots();
    }
    /**
     * Allows the patient to schedule a new appointment by specifying an appointment slot ID.
     */
    public void scheduleAppointment()
    {
        String appointmentSlotID;
        System.out.println("Schedule an Appointment");
        System.out.println("Input appointment slot ID");
        appointmentSlotID = sc.nextLine();
        logger.log("User schedules an appointment");
        appointment.scheduleAppointment(userID, appointmentSlotID);
    }

    /**
     * Allows the patient to reschedule an existing appointment by specifying
     * an old appointment ID and a new appointment slot ID.
     */
    public void rescheduleAppointments()
    {
        String appointmentID;
        String newAppointmentSlotID;

        System.out.println("Reschedule an Appointment");
        System.out.println("Input old appointment ID");
        appointmentID = sc.nextLine();
        System.out.println("Input new appointment slot ID");
        newAppointmentSlotID = sc.nextLine();
        logger.log("User reschedules an appointment");
        appointment.rescheduleAppointment(appointmentID, newAppointmentSlotID);
    }   
    /**
     * Allows the patient to cancel an existing appointment by specifying the appointment ID.
     */
    public void cancelAppointment()
    {
        
        String appointmentID;
        System.out.println("Cancel an Appointment");
        System.out.println("Input appointment ID to be cancelled");
        appointmentID = sc.nextLine();
        logger.log("User cancels an appointment");
        appointment.cancelAppointment(appointmentID);
    }
    /**
     * Displays all scheduled appointments for the patient.
     */
    public void viewScheduledAppointments()
    {
        System.out.println("View Scheduled Appointments");
        appointment.viewScheduleAppointment(userID);
        logger.log("User view Scheduled Appointments");
    }
    /**
     * Displays past appointment outcome records for the patient.
     */
    public void viewPastAppointmentOutcomeRecords()
    {
        System.out.println("View Past Appointment Outcome Records");
        logger.log ("User views past appointment outcome records");
        manager.viewPastRecords(userID);
    }

    /**
     * Retrieves the patient's unique ID.
     *
     * @return the user's unique ID.
     */
    public String getUserID()
    {
        return userID;
    }
    /**
     * Retrieves the user's role within the system.
     *
     * @return the role of the user, specifically "Patient" in this context.
     */
    public String getRole()
    {
        return userRole;
    }
}
