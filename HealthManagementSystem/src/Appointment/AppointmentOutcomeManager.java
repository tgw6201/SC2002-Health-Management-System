package Appointment;

import java.util.*;
import FileManager.*;

/**
 * The {@code AppointmentOutcomeManager} class manages appointment outcomes, including recording,
 * updating, and viewing outcomes. It interacts with {@code DataProcessor} for data persistence 
 * in CSV files and supports functionality for both doctors and patients.
 * 
 * <p>This class implements the {@code AppointmentOutcomeService} interface to ensure 
 * consistency in managing appointment outcomes.</p>
 * 
 * <p>Common variables between appointment and outcome records include patient ID and 
 * appointment date. Each recorded outcome is linked to an appointment and tracked using 
 * unique identifiers.</p>
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class AppointmentOutcomeManager implements AppointmentOutcomeService {

    private DataProcessor dataProcessor;

    /**
     * Constructs an {@code AppointmentOutcomeManager} with the specified data reader and writer.
     * 
     * @param reader the {@code dataReader} used to read appointment and outcome records
     * @param writer the {@code dataWriter} used to write appointment and outcome records
     */
    public AppointmentOutcomeManager(dataReader reader, dataWriter writer) {
        dataProcessor = new DataProcessor(reader, writer);
    }

    /**
     * Records the outcome of an appointment, including prescribed medication, consultation notes, 
     * and prescription status. Updates the appointment status to "Completed" and creates a 
     * corresponding outcome record.
     * 
     * @param typeOfService              the type of service provided during the appointment
     * @param prescribedMedication       the medication prescribed to the patient
     * @param prescribedMedicationQuantity the quantity of medication prescribed
     * @param prescriptionStatus         the status of the prescription (e.g., filled, pending)
     * @param consultationNotes          notes recorded during the consultation
     * @param appointmentID              the unique identifier of the appointment
     */
    public void recordAppointmentOutcome(
        String typeOfService,
        String prescribedMedication,
        String prescribedMedicationQuantity,
        String prescriptionStatus,
        String consultationNotes,
        String appointmentID
    ) {
        List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");

        // Initialization
        String patientID = "NIL";
        String appointmentDate = "NIL";

        // Retrieve patientID and appointmentDate, and update appointment status
        int i = 0;
        for (String[] row : appointmentListCsv) {
            if (row[0].equalsIgnoreCase(appointmentID)) {
                patientID = row[1];
                appointmentDate = row[4];

                // Create appointmentOutcomeID
                String appointmentOutcomeID = patientID + appointmentID;

                // Update appointment status and outcome ID
                dataProcessor.writeData("Appointment_List.csv", i, 3, "Completed");
                dataProcessor.writeData("Appointment_List.csv", i, 7, appointmentOutcomeID);
            }
            i++;
        }

        // Create and write the outcome record
        String appointmentOutcomeID = patientID + appointmentID;
        List<String> newRow = Arrays.asList(
            appointmentOutcomeID,
            patientID,
            appointmentDate,
            typeOfService,
            prescribedMedication,
            prescribedMedicationQuantity,
            prescriptionStatus,
            consultationNotes,
            appointmentID
        );
        dataProcessor.writeRow("AppointmentOutcomeRecord_List.csv", newRow);

        // Verification
        System.out.println("Appointment Outcome recorded");
    }

    /**
     * Allows a patient to view all past appointment outcomes using their patient ID.
     * 
     * @param patientID the unique identifier of the patient whose records are to be viewed
     */
    public void viewPastRecords(String patientID) {
        List<String[]> appointmentOutcomeListCsv = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");

        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescribed Medication Quantity: ",
            "Prescription Status: ",
            "Consultation Notes: ",
            "Appointment ID: "
        };

        for (String[] row : appointmentOutcomeListCsv) {
            if (row[1].equalsIgnoreCase(patientID)) { // Match patient ID
                for (int i = 0; i < row.length; i++) {
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
            }
        }
    }

    /**
     * Allows viewing of a specific appointment outcome record using its unique identifier.
     * 
     * @param appointmentOutcomeID the unique identifier of the appointment outcome record
     */
    public void viewOutcomeRecord(String appointmentOutcomeID) {
        List<String[]> appointmentOutcomeListCsv = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");

        String[] labels = {
            "Appointment Outcome ID: ",
            "Patient ID: ",
            "Appointment Date: ",
            "Type of Service: ",
            "Prescribed Medication: ",
            "Prescribed Medication Quantity: ",
            "Prescription Status: ",
            "Consultation Notes: ",
            "Appointment ID: "
        };

        for (String[] row : appointmentOutcomeListCsv) {
            if (row[0].equalsIgnoreCase(appointmentOutcomeID) && !row[0].equalsIgnoreCase("appointmentOutcomeID")) { 
                for (int i = 0; i < row.length; i++) {
                    System.out.println(labels[i] + row[i]);
                }
                System.out.println();
            }
        }
    }
}
