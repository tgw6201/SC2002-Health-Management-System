package Appointment;

import java.util.ArrayList;
import java.util.List;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;

public class AppointmentSlotManager {
    private List<String[]> appointmentSlotListCsv;
    
    CsvFileReader csvFileReader = new CsvFileReader();
    CsvFileWriter csvFileWriter = new CsvFileWriter();    

    //sync the appointmentSlotList with CSV file
    public AppointmentSlotManager(){
        appointmentSlotListCsv = csvFileReader.readData("AvailabilitySlot_List.csv");
    }

    //doctor update availability --> existing appointmentslot / scheduled appointment
    public void setAppointmentAvailability(String appointmentSlotID, String availability){
        for(int i = 0; i<appointmentSlotListCsv.size(); i++){// iterates over each String[] (1 row in csv)
            String[] row = appointmentSlotListCsv.get(i);// get the row 
            if(row[0].equals(appointmentSlotID)){ // check if appointmentSlotID matches
                csvFileWriter.writeData("AvailabilitySlot_List.csv", i, 5, availability);
                System.out.println("Appointment Slot Availability updated");   
                //update appointmentSlotListCsv array
                row[5] = availability;
            }
        }
    }

    //doctor creates an appointmentSlot --> method assigns an appointmentID
    //assumes doctor is not dumb to create an appointmentSlot again when he has 1 already
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
        csvFileWriter.writeRow("AvailabilitySlot_List.csv", newRow);
        System.out.println("Appointment Slot Availability updated"); 
        //update appointmentSlotListCsv array
        String[] newArray = {appointmentSlotID, doctorID, doctorName, appointmentDate, appointmentTime, availability};
        appointmentSlotListCsv.add(newArray);
    }
    
    //patient view appointmentslots --> updated to only view available slots
    public void viewAvailableAppointmentSlots(){
        String[] labels = {
            "Appointment Slot ID: ",
            "Doctor ID: ",
            "Doctor Name: ",
            "Appointment Date: ",
            "AppointmentTime: ",
            "Availability: "
        };
        for(String[] row : appointmentSlotListCsv){
            
            if(!row[0].equals("appointmentSlotID") && row[5].equals("Available")){// row 0 is the header --> skip

                for(int i = 0; i < row.length; i++){
                    System.out.println(labels[i] + row[i]);
                }
    
                System.out.println();
            }

        }

    }

}

