package User;

import Appointment.AppointmentManager;
import Appointment.AppointmentOutcomeManager;
import Appointment.AppointmentSlotManager;
import Appointment.MedicalRecordManager;
import java.util.Scanner;

public class Doctor {
    // Managers for handling various appointment-related tasks
    private final MedicalRecordManager medicalRecordManager;
    private final AppointmentManager appointmentManager;
    private final AppointmentSlotManager appointmentSlotManager;
    private final AppointmentOutcomeManager appointmentOutcomeManager;
    private final Scanner sc;
    private final String doctorID;

    // Constructor to initialize Doctor with associated managers and doctor ID
    public Doctor(String doctorID, MedicalRecordManager medicalRecordManager, AppointmentManager appointmentManager, AppointmentSlotManager appointmentSlotManager, AppointmentOutcomeManager appointmentOutcomeManager) {
        this.doctorID = doctorID;
        this.medicalRecordManager = medicalRecordManager;
        this.appointmentManager = appointmentManager;
        this.appointmentSlotManager = appointmentSlotManager;
        this.appointmentOutcomeManager = appointmentOutcomeManager;
        this.sc = new Scanner(System.in);
    }

    // Main menu for doctor to select different options
    public void Menu() {
        int choice = -1;
        while (choice != 8) { // Loop until the doctor chooses to log out
            System.out.println("Doctor Menu:");
            System.out.println("1. View Patient Medical Records");
            System.out.println("2. Update Patient Medical Records");
            System.out.println("3. View Personal Schedule");
            System.out.println("4. Set Availability for Appointments");
            System.out.println("5. Accept or Decline Appointment Requests");
            System.out.println("6. View Upcoming Appointments");
            System.out.println("7. Record Appointment Outcome");
            System.out.println("8. Logout");

            try {
                choice = Integer.parseInt(sc.nextLine().trim()); // Parse choice as integer
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                continue; // Skip this iteration and prompt again
            }

            // Execute the selected option
            switch (choice) {
                case 1:
                    viewPatientRecords();
                    break;
                case 2:
                    updatePatientMedicalRecords();
                    break;
                case 3:
                    viewPersonalSchedule();
                    break;
                case 4:
                    setAvailability();
                    break;
                case 5:
                    manageAppointmentRequests();
                    break;
                case 6:
                    viewUpcomingAppointments();
                    break;
                case 7:
                    recordAppointmentOutcome();
                    break;
                case 8:
                    System.out.println("Logging out...");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // View a specific patient's medical records
    private void viewPatientRecords() {
        System.out.println("Enter Patient ID:");
        String patientID = sc.nextLine();
        medicalRecordManager.viewMedicalRecord(patientID); // Calls MedicalRecordManager to retrieve records
    }

    // Update a patient's medical records (diagnosis and treatment)
    private void updatePatientMedicalRecords() {
        System.out.println("Enter Patient ID:");
        String patientID = sc.nextLine();
    
        // Prompt for updated diagnosis and treatment
        System.out.println("Enter new Diagnosis:");
        String newDiagnosis = sc.nextLine();
        System.out.println("Enter new Treatment:");
        String newTreatment = sc.nextLine();
    
        medicalRecordManager.updatePatientMedicalRecords(patientID, newDiagnosis, newTreatment);
    }

    // View the doctor's personal schedule
    private void viewPersonalSchedule() {
        appointmentManager.viewPersonalSchedule(doctorID); // Calls AppointmentManager with doctorID
    }

    // Set availability for appointment slots (either create a new slot or update existing one)
    private void setAvailability() {
        System.out.println("Do you want to update an existing slot or create a new one? (Enter 'u' for update or 'c' for create):");
        String choice = sc.nextLine().trim().toLowerCase();

        if (choice.equals("u")) {
            // Update existing slot's availability status
            System.out.println("Enter Appointment Slot ID:");
            String appointmentSlotID = sc.nextLine();
            
            System.out.println("Set availability (Available/Unavailable):");
            String availability = sc.nextLine();
            
            appointmentSlotManager.setAppointmentAvailability(appointmentSlotID, availability);

        } else if (choice.equals("c")) {
            // Create a new appointment slot with specific details
            System.out.println("Enter Doctor Name:");
            String doctorName = sc.nextLine();
            
            System.out.println("Enter available date (format: YYYY-MM-DD):");
            String date = sc.nextLine();
            
            System.out.println("Enter available time (format: HH:MM):");
            String time = sc.nextLine();
            
            System.out.println("Set availability (Available/Unavailable):");
            String availability = sc.nextLine();
            
            appointmentSlotManager.setAppointmentAvailability(doctorID, doctorName, date, time, availability);
        } else {
            System.out.println("Invalid choice. Please enter 'u' for update or 'c' for create.");
        }
    }

    // Manage appointment requests (accept or decline based on input)
    private void manageAppointmentRequests() {
        System.out.println("Enter Appointment ID to accept/decline:");
        String appointmentID = sc.nextLine();
        System.out.println("Accept (A) or Decline (D):");
        String decisionInput = sc.nextLine();
    
        String decision = "";
    
        if (decisionInput.equalsIgnoreCase("A")) {
            decision = "Accept";
        } else if (decisionInput.equalsIgnoreCase("D")) {
            decision = "Decline";
        } else {
            System.out.println("Invalid choice.");
            return; // Exit method if invalid input
        }
    
        appointmentManager.acceptDeclineAppointment(appointmentID, decision); // Call AppointmentManager with decision
    }

    // View all upcoming appointments for the doctor
    private void viewUpcomingAppointments() {
        appointmentManager.viewUpcomingAppointments(doctorID); // Retrieve upcoming appointments by doctorID
    }

    // Record the outcome of an appointment
    private void recordAppointmentOutcome() {
        System.out.println("Enter Appointment ID:");
        String appointmentID = sc.nextLine();
        
        // Collect details for recording the outcome
        System.out.println("Enter Type of Service:");
        String typeOfService = sc.nextLine();
        
        System.out.println("Enter Prescribed Medication:");
        String prescribedMedication = sc.nextLine();
        
        System.out.println("Enter Prescribed Medication Quantity:");
        String prescribedMedicationQuantity = sc.nextLine();
        
        System.out.println("Enter Prescription Status:");
        String prescriptionStatus = sc.nextLine();
        
        System.out.println("Enter Consultation Notes:");
        String consultationNotes = sc.nextLine();
        
        // Record the outcome with AppointmentOutcomeManager
        appointmentOutcomeManager.recordAppointmentOutcome(typeOfService, prescribedMedication, prescribedMedicationQuantity, prescriptionStatus, consultationNotes, appointmentID);
    }
}
