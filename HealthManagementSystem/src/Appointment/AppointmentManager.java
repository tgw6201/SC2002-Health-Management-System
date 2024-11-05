package Appointment;

import java.util.ArrayList;
import java.util.List;

import FileManager.CsvFileReader;
import FileManager.CsvFileWriter;

public class AppointmentManager {
    
    private List<String[]> appointmentSlotListCsv;
    private List<String[]> appointmentListCsv;
    
    CsvFileReader csvFileReader = new CsvFileReader();
    CsvFileWriter csvFileWriter = new CsvFileWriter(); 

    public AppointmentManager(){
        //will need info from appointmentSlot to create / cancel appointment/ reschedule appointment
        appointmentSlotListCsv = csvFileReader.readData("AvailabilitySlot_List.csv");
        
        //contains all info of appointments
        appointmentListCsv = csvFileReader.readData("Appointment_List.csv");
    }

    // more efficient to use appointmentSlotID 
    public void scheduleAppointment(String patientID, String appointmentSlotID){
        
        //Read existing data in csv
        appointmentSlotListCsv = csvFileReader.readData("AvailabilitySlot_List.csv");
        appointmentListCsv = csvFileReader.readData("Appointment_List.csv");

        
        for(String[] row1 : appointmentSlotListCsv){
            
            if(row1[0].equals(appointmentSlotID)){
            //Ensure that the patient cannot book a booked slot
                if(row1[5].equals("Booked")){ //Ensure that the patient cannot book a booked/unavailable slot
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }
                else if(row1[5].equals("Unavailable")){// can be further confirmed if there should be this 
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

                        if(row2[0].equals(appointmentSlotID)){
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
                    csvFileWriter.writeRow("Appointment_List.csv", newRow);
                
                    //update appointment slot to Booked for the csv
                    AppointmentSlotManager slot = new AppointmentSlotManager();
                    slot.setAppointmentAvailability(appointmentSlotID, "Booked");

                    //verify that the appointment is scheduled successfully
                    System.out.println("Appointment scheduled.");

                }
            }

        }

    }

    public void rescheduleAppointment(String appointmentID, String newAppointmentSlotID){
            
        //Read existing data in csv
        appointmentSlotListCsv = csvFileReader.readData("AvailabilitySlot_List.csv");
        appointmentListCsv = csvFileReader.readData("Appointment_List.csv");

        //Initalization
        String oldAppointmentSlotID = "NIL";
        String doctorID = "NIL";
        String appointmentDate = "NIL";
        String appointmentTime = "NIL";
        String patientID = "NIL";
        String appointmentOutcomeID = "NIL";
        //default staus is confirmed
        String appointmentStatus = "Confirmed";

        AppointmentSlotManager slot = new AppointmentSlotManager();
        
        // get old appointment slot id
        for(String[] row : appointmentListCsv){
            if(row[0].equals(appointmentID)){
                oldAppointmentSlotID = row[6]; 
                break;
            }
        }


        for(String[] row1 : appointmentSlotListCsv){

            //make new appointmentslot booked
            if(row1[0].equals(newAppointmentSlotID)){
                
                //check if it is booked/unavailable --> choose a new appoitnment slot
                //Ensure that the patient cannot book a booked/unavailable slot

                if(row1[5].equals("Booked")){
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }
                else if(row1[5].equals("Unavailable")){// can be further confirmed if there should be this 
                    System.out.println("Find another appointment slot. This slot is Unavailable.");
                    return;                    
                }
                else{ //available appointmentslot

                    //get the doctor of the new appointmentSlot 
                    //get the time and date of new slot
                    doctorID = row1[1];
                    appointmentDate = row1[3];
                    appointmentTime = row1[4];

                    //change new appoointmentSlot to booked
                    slot.setAppointmentAvailability(newAppointmentSlotID, "Booked");

                    //make old appointmentslot available
                    slot.setAppointmentAvailability(oldAppointmentSlotID, "Available");
                    
                    
                    // Get row index of the appointment 
                    int i = 0;
                    //update doctorID, appointmentStatus, appointmentDate, appointmentTime, appointmentSlotID
                    //patientID, appointmentID and appointmentOutcomeID remain the same
                    //Finding location of appointment in csv and rewrite the whole row
                    for(String[] row2 : appointmentListCsv){
                        
                        //find the row containing the appointment
                        if(row2[6].equals(oldAppointmentSlotID)){
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
                            csvFileWriter.writeRow("Appointment_List.csv", i, newRow);
                        
                            
                            //verify that the appointment is rescheduled successfully
                            System.out.println("Appointment is rescheduled.");
                        }
                        i++;
                    }

                    
                }

                }
            }

    }

    public void cancelAppointment(String appointmentID){
    
        //Read existing data in csv
        appointmentSlotListCsv = csvFileReader.readData("AvailabilitySlot_List.csv");
        appointmentListCsv = csvFileReader.readData("Appointment_List.csv");

        //Intialization 
        String AppointmentSlotID = "NIL";
        //Track the row that has the appointmentStatus to change
        int i = 0; 
        //Find appointment
        for(String[] row : appointmentListCsv){
            //If it matches the appoointmentID
            if(row[0].equals(appointmentID)){
                //change the appointmentStatus to cancelled in csv
                csvFileWriter.writeData("Appointment_List.csv", i, 3, "Cancelled");
                //get the appointmentSlotID
                AppointmentSlotID = row[6];
                break;
            }
            i++;
        }
        //Update appointmentSlot from booked to available
        AppointmentSlotManager slot = new AppointmentSlotManager();
        slot.setAppointmentAvailability(AppointmentSlotID, "Available");

        //verify that the appointment has been cancelled
        System.out.println("Appointment cancelled.");
    }

    

}
