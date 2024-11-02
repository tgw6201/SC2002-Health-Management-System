package Appointment;
import java.util.*;
public class AppointmentOutcomeRecord {
    private String patientID;
    private String appointmentDate;
    private String typeOfService;
    private String prescribedMedication;
    private String prescriptionStatus;
    private String consultationNotes;

    public AppointmentOutcomeRecord(String patientID, String appointmentDate, String typeOfService, String prescribedMedication, String prescriptionStatus, String consultationNotes){
        this.patientID = patientID;
        this.appointmentDate = appointmentDate;
        this.typeOfService = typeOfService;
        this.prescribedMedication = prescribedMedication;
        this.prescriptionStatus = prescriptionStatus;
        this.consultationNotes = consultationNotes;
    }

    public String getPrescriptionStatus(){
        return prescriptionStatus;
    }

    public void setPrescriptionStatus(String prescriptionStatus){
        this.prescriptionStatus = prescriptionStatus;
    }


}
