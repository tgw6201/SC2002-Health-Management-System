package Appointment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import FileManager.*;

import FileManager.DataProcessor;
import FileManager.dataReader;
import FileManager.dataWriter;

/**
 * The AppointmentManager class provides functionality for managing 
 * appointment scheduling, rescheduling, cancellations, and viewing appointments.
 * 
 * <p>
 * This class implements both {@link AppointmentSchedulingService} and {@link ViewAppointment}
 * interfaces, allowing it to handle appointment-related functionalities such as booking,
 * modifying, and viewing schedules.
 * </p>
 * 
 * <p>
 * It interacts with {@link DataProcessor}, {@link dataReader}, and {@link dataWriter}
 * to manage appointment data and updates availability slots.
 * </p>
 * 
 * <p><b>Note:</b> Ensure "Appointment_List.csv" and "AvailabilitySlot_List.csv" files are 
 * available for correct functionality.</p>
 * 
 * @see AppointmentSchedulingService
 * @see ViewAppointment
 * @see DataProcessor
 * @see dataReader
 * @see dataWriter
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class AppointmentManager implements AppointmentSchedulingService, ViewAppointment {
    
       
    private DataProcessor dataProcessor;
    private dataReader fileReader;
    private dataWriter fileWriter;
    

    /**
     * Constructs an AppointmentManager instance and initializes the data processor.
     * 
     * @param reader The dataReader instance to read appointment data.
     * @param writer The dataWriter instance to write appointment data.
     */
    public AppointmentManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
        fileReader = reader;
        fileWriter = writer;
    }

    /**
     * Schedules an appointment for a patient based on the provided slot ID.
     * 
     * @param patientID The ID of the patient.
     * @param appointmentSlotID The ID of the desired appointment slot.
     */
    public void scheduleAppointment(String patientID, String appointmentSlotID){
        
        
        //Read existing data in csv
        List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");

        //check for invalid AppointmentSlotID
        boolean appointmentSlotIDExist = false;
        for (String[] row : appointmentSlotListCsv) {
            if (row[0].equalsIgnoreCase(appointmentSlotID)) {
                appointmentSlotIDExist = true;
                break;
            }
        }

        if(!appointmentSlotIDExist){
            System.out.println("Invalid appointment slot ID. Provide a valid appointment slot ID.");
            return;
        }

        for(String[] row1 : appointmentSlotListCsv){
            
            if(row1[0].equalsIgnoreCase(appointmentSlotID)){
            //Ensure that the patient cannot book a booked slot
                if(row1[5].equalsIgnoreCase("Booked")){ //Ensure that the patient cannot book a booked/unavailable slot
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }

                else if(row1[5].equalsIgnoreCase("Unavailable")){// can be further confirmed if there should be this 
                    System.out.println("Find another appointment slot. This slot is Unavailable.");
                    return;                    
                }

                else{// create appointment + update appointmentslot

                    //intialization
                    String doctorID = "NIL";
                    String appointmentDate = "NIL";
                    String appointmentTime = "NIL";

                    //Finding the appointmentSlot + get information 
                    for(String[] row2 : appointmentSlotListCsv){

                        if(row2[0].equalsIgnoreCase(appointmentSlotID)){
                            doctorID = row2[1];
                            appointmentDate = row2[3];
                            appointmentTime = row2[4];
                        }

                    }
                    //create appointmentID = patientID + doctorID + appointmentDate + appointmentTime

                    String date = appointmentDate.substring(0,2);
                    String[] time = appointmentTime.split(":");
                    String hour = time[0].trim();
                    String minutes = time[1].substring(0,2);

                    String appointmentID = patientID + doctorID + date + hour + minutes;

                    //unsure if it is really going to be called confirmed yet
                    String appointmentStatus = "Confirmed";
                    String appointmentOutcomeID = "NIL";

                    // Adding a new apppointment to csv
                    List<String> newRow = new ArrayList<String>();
                    newRow.add(appointmentID);
                    newRow.add(patientID);
                    newRow.add(doctorID);
                    newRow.add(appointmentStatus);
                    newRow.add(appointmentDate);
                    newRow.add(appointmentTime);
                    newRow.add(appointmentSlotID);
                    newRow.add(appointmentOutcomeID);
                    dataProcessor.writeRow("Appointment_List.csv", newRow);
                
                    //update appointment slot to Booked for the csvreader
                    AppointmentSlotManager slot = new AppointmentSlotManager(fileReader, fileWriter);
                    slot.setAppointmentAvailability(appointmentSlotID, "Booked");

                    //verify that the appointment is scheduled successfully
                    System.out.println("Appointment scheduled.");

                }
            }

        }

    }

    /**
     * Reschedules an existing appointment to a new slot.
     * 
     * @param appointmentID The ID of the appointment to reschedule.
     * @param newAppointmentSlotID The ID of the new appointment slot.
     */
    public void rescheduleAppointment(String appointmentID, String newAppointmentSlotID){

        //Read existing data in csv
        List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
        List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
        
        // check for Invalid appointmentID --> don't exist and those cancelled/completed
        boolean appointmentIDExist = false;
        for (String[] row : appointmentListCsv) {
            if (row[0].equalsIgnoreCase(appointmentID) && row[3].equalsIgnoreCase("Confirmed") ) {
                appointmentIDExist = true;
                break;
            }
        }

        if(!appointmentIDExist){
            System.out.println("Invalid appointment ID. Provide a valid appointment ID.");
            return;
        }

        //check for invalid newAppointmentSlotID
        boolean appointmentSlotIDExist = false;
        for (String[] row : appointmentSlotListCsv) {
            if (row[0].equalsIgnoreCase(newAppointmentSlotID)) {
                appointmentSlotIDExist = true;
                break;
            }
        }

        if(!appointmentSlotIDExist){
            System.out.println("Invalid appointment slot ID. Provide a valid appointment slot ID.");
            return;
        }


        //Initalization
        String oldAppointmentSlotID = "NIL";
        String doctorID = "NIL";
        String appointmentDate = "NIL";
        String appointmentTime = "NIL";
        String patientID = "NIL";
        String appointmentOutcomeID = "NIL";
        //default staus is confirmed
        String appointmentStatus = "Confirmed";

        AppointmentSlotManager slot = new AppointmentSlotManager(fileReader, fileWriter);
        
        // get old appointment slot id 
        for(String[] row : appointmentListCsv){
            if(row[0].equalsIgnoreCase(appointmentID)){
                oldAppointmentSlotID = row[6]; 
                break;
            }
        }


        for(String[] row1 : appointmentSlotListCsv){

            //make new appointmentslot booked
            if(row1[0].equalsIgnoreCase(newAppointmentSlotID)){
                
                //check if it is booked/unavailable --> choose a new appoitnment slot
                //Ensure that the patient cannot book a booked/unavailable slot

                if(row1[5].equalsIgnoreCase("Booked")){
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }
                else if(row1[5].equalsIgnoreCase("Unavailable")){// can be further confirmed if there should be this 
                    System.out.println("Find another appointment slot. This slot is Unavailable.");
                    return;                    
                }
                else{ //available appointmentslot

                    //get the doctor of the new appointmentSlot 
                    //get the time and date of new slot
                    doctorID = row1[1];
                    appointmentDate = row1[3];
                    appointmentTime = row1[4];

                    //change new appointmentSlot to booked
                    slot.setAppointmentAvailability(newAppointmentSlotID, "Booked");

                    //make old appointmentslot available
                    slot.setAppointmentAvailability(oldAppointmentSlotID, "Available");
                    
                    
                    // Get row index of the appointment 
                    int i = 0;
                    //update doctorID, appointmentStatus, appointmentDate, appointmentTime, appointmentSlotID
                    //patientID, appointmentID and appointmentOutcomeID remain the same
                    //Finding location of appointment in csv and rewrite the whole row
                    for(String[] row2 : appointmentListCsv){
                        
                        //find the row containing the appointment + make sure it is confirmed(cannot change cancelled ones)
                        if(row2[0].equalsIgnoreCase(appointmentID) && row2[3].equalsIgnoreCase("Confirmed")){
                            patientID = row2[1];
                            appointmentOutcomeID = row2[7];
                            // need use i to iterate over to find location of appointment
                            //use newAppoitnmentSlotID + update the details of the appointment
                            List<String> newRow = new ArrayList<String>();
                            newRow.add(appointmentID);
                            newRow.add(patientID);
                            newRow.add(doctorID);
                            newRow.add(appointmentStatus);
                            newRow.add(appointmentDate);
                            newRow.add(appointmentTime);
                            newRow.add(newAppointmentSlotID);
                            newRow.add(appointmentOutcomeID);
                            dataProcessor.writeRow("Appointment_List.csv", i, newRow);
                        
                            
                            //verify that the appointment is rescheduled successfully
                            System.out.println("Appointment is rescheduled.");
                        }
                        i++;
                    }

                    
                }

                }
            }

    }
    
    /**
     * Cancels an appointment based on the provided appointment ID.
     * 
     * @param appointmentID The ID of the appointment to cancel.
     */
    public void cancelAppointment(String appointmentID){
    
        //Read existing data in csv
        List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
        
        // check for Invalid appointmentID --> don't exist and those cancelled/completed
        boolean appointmentIDExist = false;
            for (String[] row : appointmentListCsv) {
                if (row[0].equalsIgnoreCase(appointmentID) && row[3].equalsIgnoreCase("Confirmed") ) {
                    appointmentIDExist = true;
                    break;
                }
            }
        
            if(!appointmentIDExist){
                System.out.println("Invalid appointment ID. Provide a valid appointment ID.");
                return;
            }
        

        //Intialization 
        String AppointmentSlotID = "NIL";
        //Track the row that has the appointmentStatus to change
        int i = 0; 
        //Find appointment
        for(String[] row : appointmentListCsv){
            //If it matches the appointmentID and must be confirmed
            if(row[0].equalsIgnoreCase(appointmentID) && row[3].equalsIgnoreCase("Confirmed")){
                //change the appointmentStatus to cancelled in csv
                dataProcessor.writeData("Appointment_List.csv", i, 3, "Cancelled");
                //get the appointmentSlotID
                AppointmentSlotID = row[6];
                break;
            }
            i++;
        }
        //Update appointmentSlot from booked to available
        AppointmentSlotManager slot = new AppointmentSlotManager(fileReader, fileWriter);
        System.out.println(AppointmentSlotID);
        slot.setAppointmentAvailability(AppointmentSlotID, "Available");

        //verify that the appointment has been cancelled
        System.out.println("Appointment cancelled.");
    }

    /**
     * Allows a doctor to accept or decline an appointment.
     * 
     * @param appointmentID The ID of the appointment.
     * @param decision Either "Accept" or "Decline".
     */
        public void acceptDeclineAppointment(String appointmentID, String decision){
            //Read existing data in csv
            List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
             
            //intialise
            String appointmentSlotID = "NIL";
            //i is used to keep track of the row that appoinment is in
            int i = 0;
           
    
    
            //change appointment Status to confirmed
            if(decision.equalsIgnoreCase("Accept")){
                //find apppointment
                for(String[] row :  appointmentListCsv){
                    //check for appointmentID
                    if(row[0].equalsIgnoreCase(appointmentID)){
                        //update appointmentStatus
                        dataProcessor.writeData("Appointment_List.csv", i, 3, "Confirmed");
                        
                        //get appointmentSlotID
                        appointmentSlotID = row[6];
                    }
                    i++;
                }
                //update appointmentSlot to booked
                AppointmentSlotManager slot = new AppointmentSlotManager(fileReader, fileWriter);
                slot.setAppointmentAvailability(appointmentSlotID, "Booked");
        
                //verify appointment status changes to "confirmed"
                System.out.println("Appointment status changed to confirmed");
    
    
            }
    
    
            //change appointmentStatus to cancelled and appointmentSlot to available
            else{
                //find appointment
                for(String[] row :  appointmentListCsv){
                    //check for appointmentID
                    if(row[0].equalsIgnoreCase(appointmentID)){
                        //update appointmentStatus
                        dataProcessor.writeData("Appointment_List.csv", i, 3, "Cancelled");
    
    
                        //get appointmentSlotID
                        appointmentSlotID = row[6];
                    }
                    i++;
                }
                AppointmentSlotManager slot = new AppointmentSlotManager(fileReader, fileWriter);
                slot.setAppointmentAvailability(appointmentSlotID, "Available");
                System.out.println("Appointment status changed to decline");
            }
        }

    /**
     * Displays all scheduled appointments for a specific patient.
     * 
     * @param patientID The ID of the patient.
     */
        public void viewScheduleAppointment(String patientID){
            //Read existing data in csv
            List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
            List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
            //find appointements for patient

            //initialise 
            String doctorName = "NIL";
            String appointmentDate = "NIL";
            String appointmentTime = "NIL";
            String appointmentStatus = "NIL";
            String appointmentSlotID = "NIL";

            for(String[] row : appointmentListCsv){
                //make sure appointmentID 
                if(row[1].equalsIgnoreCase(patientID)){
                    appointmentStatus = row[3];
                    appointmentDate = row[4];
                    appointmentTime = row[5];
                    appointmentSlotID = row[6];
                    //find doctorName
                    for(String[] row1 : appointmentSlotListCsv){
                        if(row1[0].equalsIgnoreCase(appointmentSlotID)){
                            doctorName = row1[2];
                        }
                    }
                    System.out.println("Doctor Name : " + doctorName + "\n" +  "Appointment Date: " + appointmentDate + "\n" + "Appointment Time: " +appointmentTime + "\n" + "Appointment Status: " + appointmentStatus);
                    System.out.println();
                }
            }

        }

    /**
     * Displays the personal schedule of a specific doctor.
     * 
     * @param doctorID The ID of the doctor.
     */
        public void viewPersonalSchedule(String doctorID){
            
            //Read existing data in csv
            List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
            List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
            
            //labels to format printing
            String[] appointmentLabels = {
                "Appointment ID: ",
                "Patient ID: ",
                "Doctor ID: ",
                "Appointment Status: ",
                "Appointment Date: ",
                "Appointment Time: ",
                "Appointment Slot ID: ",
                "Appointment Outcome ID: "
            };

            System.out.println("Upcoming appointments: ");

            //find the appointments that corresponds to doctorID and confirmed
            for(String[] row1 : appointmentListCsv){
                if(row1[2].equalsIgnoreCase(doctorID) && row1[3].equalsIgnoreCase("Confirmed")){
                    for(int i = 0; i < row1.length; i++){
                        System.out.println(appointmentLabels[i] + row1[i]);
                    }
                }
                System.out.println();
            }

            //labels to format printing
            String[] appointmentSlotLabels = {
                "Appointment Slot ID: ",
                "Doctor ID: ",
                "Doctor Name: ",
                "Appointment Date: ",
                "Apppointment Time: ",
                "Availability: "
            };

            System.out.println("Availability Slots: ");
            //find the appointmentSlot that corresponds to the doctorID 
            for(String[] row2 : appointmentSlotListCsv){
                if(row2[1].equalsIgnoreCase(doctorID)){ 
                    for(int i = 0; i<row2.length; i++){
                        System.out.println(appointmentSlotLabels[i] + row2[i]);
                    }
                }
                System.out.println();
            }
            
        }

    /**
     * Views all upcoming confirmed appointments for a specific doctor.
     * 
     * @param doctorID The ID of the doctor.
     */
public void viewUpcomingAppointments(String doctorID) {
    //Read existing data in csv
    List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
    List<String[]> medicalRecordListCsv = dataProcessor.readData("MedicalRecord_List.csv");

    //labels for formatting 
    String[] labels = {
        "Patient ID: ", 
        "Name: ",
        "Date Of Birth: ",
        "Gender: ",
        "Phone Number: ",
        "Email Address: ",
        "Blood Type: "
    };

    System.out.println("Upcoming appointments:");
    System.out.println();

    for (String[] row : appointmentListCsv) {
        // Check if doctorID matches and appointmentStatus is Confirmed
        if (row[2].equalsIgnoreCase(doctorID) && row[3].equalsIgnoreCase("Confirmed")) {
            String appointmentID = row[0];
            String patientID = row[1];
            String appointmentDate = row[4];
            String appointmentTime = row[5];

            // Display appointment details
            System.out.println("Appointment ID: " + appointmentID);
            System.out.println("Appointment Date: " + appointmentDate);
            System.out.println("Appointment Time: " + appointmentTime);

            // Fetch and display patient details
            for (String[] row1 : medicalRecordListCsv) {
                if (row1[0].equalsIgnoreCase(patientID)) {
                    System.out.println("Patient Details:");
                    for (int i = 0; i < labels.length; i++) {
                        System.out.println(labels[i] + row1[i]);
                    }
                    break; // Exit the loop once the matching patient is found
                }
            }

            System.out.println(); // Add a blank line between appointments
        }
    }
}

    /**
     * Displays the details of all appointments, including outcomes for completed ones.
     */
        public void viewAppointmentDetails(){
                
            //Read existing data in csv
            List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
            List<String[]> appointmentOutcomeListCsv = dataProcessor.readData("AppointmentOutcomeRecord_List.csv");
            //intialization 
            String appointmentOutcomeID = "NIL";

            String[] appointmentLabels = {
                "Appointment ID: ",
                "Patient ID: ",
                "Doctor ID: ",
                "Appointment Status: ",
                "Appointment Date: ",
                "Appointment Time: ",
                "Appointment Slot ID: ",
                "Appointment Outcome ID: "
            };

            //print out appointment Outcome Record
            String[] labels = {
                "Appointment Outcome ID: ",
                "Patient ID: ",
                "Appointment Date: ",
                "Type of Service: ",
                "Prescribed Medication: ",
                "Prescribed Medication Quantity: ",
                "Prescription Status: ",
                "Consultation Notes: ",
                "Appointment Outcome ID: "
            };


            //print out appointment details
            System.out.println("Appointment Details: ");
            // track the row in appointmentList
            int i = 0;
            for(String[]row : appointmentListCsv){
                //skip row with headers
                if(i == 0){
                    i++;
                    continue;
                }
                //print out detials for each appointment

                for(int j = 0; j<row.length; j++){
                    System.out.println(appointmentLabels[j] + row[j]);
                }

                //get appointmentOutcomeID
                appointmentOutcomeID = row[7];

                //check status of appointment and print out appointment outcome record for completed
                if(row[3].equalsIgnoreCase("Completed")){
                    System.out.println();
                    System.out.println("Appointment Outcome Record: ");
                    for(String row1[] : appointmentOutcomeListCsv){
                        if(row1[0].equalsIgnoreCase(appointmentOutcomeID)){
                            for(int x = 0; x<row1.length; x++){
                                System.out.println(labels[x] + row1[x]);
                            }
                        }
                    }
                }
                System.out.println();
                i++;  
            }
            
        }

    /**
     * Displays the entire list of appointments from the CSV.
     */
        public void showAppointmentCsv(){
            List<String[]> appointmentListCsv = dataProcessor.readData("Appointment_List.csv");
            System.out.println("\nDisplaying all appointments:\n");

            for (String[] record : appointmentListCsv) {
                System.out.println(Arrays.toString(record));
            }
        }
    
}
