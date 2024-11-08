
import java.util.Scanner;
import Appointment.AppointmentManager;
import Appointment.MedicalRecordManager;
import Appointment.AppointmentSlotManager;
import Appointment.AppointmentOutcomeManager;
import User.Doctor;

public class DoctorTest {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initialize realistic instances of managers (replace with real implementations if available)
        MedicalRecordManager medicalRecordManager = new MedicalRecordManager();
        AppointmentManager appointmentManager = new AppointmentManager();
        AppointmentSlotManager appointmentSlotManager = new AppointmentSlotManager();
        AppointmentOutcomeManager appointmentOutcomeManager = new AppointmentOutcomeManager();

        // Set up a Doctor instance with a test doctor ID
        Doctor doctor = new Doctor("D123", medicalRecordManager, appointmentManager, appointmentSlotManager, appointmentOutcomeManager);

        // Print instructions for the tester
        System.out.println("Real-Life Testing for Doctor Class Menu");
        System.out.println("----------------------------------------");
        System.out.println("Follow the prompts to test each functionality of the Doctor menu.");
        System.out.println("You will be able to interact with options such as viewing medical records, updating records, setting availability, etc.");

        // Run the Doctor menu to simulate user interaction
        doctor.Menu();

        // Close scanner after testing is complete
        sc.close();
        System.out.println("Testing completed. Exiting...");
    }
}
