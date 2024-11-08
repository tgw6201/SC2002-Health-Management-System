package MedicalRecord;

//trying to do DIP (this is the high level module)
public class MedicalRecordManagement {
    
    private MedicalRecordService medicalRecordService;

    public MedicalRecordManagement(MedicalRecordService service){
        medicalRecordService = service;
    }

    public void viewMedicalRecord(String patientID){
        medicalRecordService.viewMedicalRecord(patientID);
    }

    public void updateInformation(String patientID, String information1, String information2){
        medicalRecordService.updateInformation(patientID, information1, information2);
    }
    
}
