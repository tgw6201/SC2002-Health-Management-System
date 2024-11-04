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
        appointmentListCsv =csvFileReader.readData("Appointment_List.csv");
    }

    // more efficient to use appointmentSlotID 
    public void scheduleAppointment(String patientID, String appointmentSlotID){
        for(String[] row1 : appointmentSlotListCsv){
            
            if(row1[0].equals(appointmentSlotID)){

                if(row1[5].equals("Booked")){ //Ensure that the patient cannot book a booked/unavailable slot
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }
                else if(row1[5].equals("Unavailable")){// can be further confirmed if there should be this 
                    System.out.println("Find another appointment slot. This slot is Unavailable.");
                    return;                    
                }

                else{// create appointment + update appointmentslot

                    String doctorID = "NIL";
                    String appointmentDate = "NIL";
                    String appointmentTime = "NIL";

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
                
                    //update appointment slot to Booked
                    AppointmentSlotManager slot = new AppointmentSlotManager();
                    slot.setAppointmentAvailability(appointmentSlotID, "Booked");
                
                    //update array appointmenListCsv 
                    String[] newdata = { appointmentID, patientID, doctorID, appointmentStatus, appointmentDate, appointmentTime, appointmentSlotID, appointmentOutcomeID};
                    appointmentListCsv.add(newdata);

                }
            }

        }

    }

    public void rescheduleAppointment(String appointmentID, String newAppointmentSlotID){
        
        String oldAppointmentSlotID = "NIL";
        for(String[] row : appointmentListCsv){
            if(row[0].equals(appointmentID)){
                oldAppointmentSlotID = row[6]; // get old appointment slot id
                break;
            }
        }
        //initialise
        String doctorID = "NIL";
        String appointmentDate = "NIL";
        String appointmentTime = "NIL";

        for(String[] row2 : appointmentSlotListCsv){

            //make old appointmentslot available
            if(row2[0].equals(oldAppointmentSlotID)){
                row2[5] = "Available";
            }

            //make new appointmentslot booked
            if(row2[0].equals(newAppointmentSlotID)){
                //check if it is booked/unavailable --> choose a new appoitnment slot
                if(row2[5].equals("Booked")){ //Ensure that the patient cannot book a booked/unavailable slot
                    System.out.println("Find another appointment slot. This slot is Booked.");
                    return;
                }
                else if(row2[5].equals("Unavailable")){// can be further confirmed if there should be this 
                    System.out.println("Find another appointment slot. This slot is Unavailable.");
                    return;                    
                }
                else{ //available appointmentslot
                    //unsure if it is really going to be called confirmed yet
                    String appointmentStatus = "Confirmed";
                    doctorID = row2[1];
                    appointmentDate = row2[3];
                    appointmentTime = row2[4];

                    //update doctorID, appointmentStatus, appointmentDate, appointmentTime, appointmentSlotID
                    csvFileWriter.
                    
                }

                }
            }

        }

    }

}
