package MedicalRecord;

public interface MedicalRecordService {

    //Both doctor and patient can use this
    public void viewMedicalRecord(String patientID);

    //Both doctor and patient can use this
    public void updateInformation(String patientID, String information1, String information2);


}
