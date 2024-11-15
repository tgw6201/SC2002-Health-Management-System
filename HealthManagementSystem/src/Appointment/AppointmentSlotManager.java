package Appointment;

import java.util.ArrayList;
import java.util.List;

import FileManager.*;


/**
 * The AppointmentSlotManager class provides functionality for managing appointment slots,
 * including updating availability, creating new slots, and viewing available slots.
 * 
 * <p>
 * This class interacts with {@link DataProcessor} to manage data in the 
 * "AvailabilitySlot_List.csv" file.
 * </p>
 * 
 * @see AppointmentSlotService
 * @see DataProcessor
 * @see dataReader
 * @see dataWriter
 * 
 * @author Lee Xing Juan Rennie
 * @version 1.0
 * @since 2024-11-15
 */
public class AppointmentSlotManager implements AppointmentSlotService{
    private DataProcessor dataProcessor;
 
    /**
     * Constructs an AppointmentSlotManager instance with a data reader and writer.
     * 
     * @param reader The dataReader instance to read data.
     * @param writer The dataWriter instance to write data.
     */
    public AppointmentSlotManager(dataReader reader, dataWriter writer){
        dataProcessor = new DataProcessor(reader, writer);
    }

    /**
     * Updates the availability of an existing appointment slot based on the slot ID.
     * 
     * @param appointmentSlotID The ID of the appointment slot to update.
     * @param availability      The new availability status (e.g., "Available", "Booked").
     */
    public void setAppointmentAvailability(String appointmentSlotID, String availability){
        List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
        
        for(int i = 0; i<appointmentSlotListCsv.size(); i++){// iterates over each String[] (1 row in csv)
            String[] row = appointmentSlotListCsv.get(i);// get the row 
            if(row[0].equalsIgnoreCase(appointmentSlotID)){ // check if appointmentSlotID matches
                dataProcessor.writeData("AvailabilitySlot_List.csv", i, 5, availability);
                System.out.println("Appointment Slot Availability updated");          
                
            }
        }
    }


    /**
     * Creates a new appointment slot for a doctor and assigns an appointment slot ID.
     * 
     * @param doctorID       The ID of the doctor.
     * @param doctorName     The name of the doctor.
     * @param appointmentDate The date of the appointment slot.
     * @param appointmentTime The time of the appointment slot.
     * @param availability    The availability status of the slot (e.g., "Available").
     */
    public void setAppointmentAvailability(String doctorID, String doctorName, String appointmentDate, String appointmentTime, String availability){
       
        //creating appointmentSlotID with doctorid + date + time
        String day = appointmentDate.substring(0,2);
        String[] time = appointmentTime.split(":");
        String hour = time[0].trim();
        String minutes = time[1].substring(0,2);
        String appointmentSlotID = doctorID + day + hour + minutes;

        List<String> newRow = new ArrayList<String>();
        newRow.add(appointmentSlotID);
        newRow.add(doctorID);
        newRow.add(doctorName);
        newRow.add(appointmentDate);
        newRow.add(appointmentTime);
        newRow.add(availability);
        dataProcessor.writeRow("AvailabilitySlot_List.csv", newRow);
        System.out.println("Appointment Slot Availability updated"); 
        
    }

    
    /**
     * Displays all available appointment slots for patients.
     * Only slots marked as "Available" are displayed.
     */
    public void viewAvailableAppointmentSlots(){
        List<String[]> appointmentSlotListCsv = dataProcessor.readData("AvailabilitySlot_List.csv");
        
        String[] labels = {
            "Appointment Slot ID: ",
            "Doctor ID: ",
            "Doctor Name: ",
            "Appointment Date: ",
            "AppointmentTime: ",
            "Availability: "
        };
        System.out.println("Available appointment slots:");
        
        for(String[] row : appointmentSlotListCsv){
            
            if(!row[0].equalsIgnoreCase("AppointmentSlotID") && row[5].equalsIgnoreCase("Available")){// row 0 is the header --> skip

                for(int i = 0; i < row.length; i++){
                    System.out.println(labels[i] + row[i]);
                }
    
                System.out.println();
            }

        }

    }

}

