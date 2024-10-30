import InformationAccess.InformationAccessManager;

public class Testing {
    public static void main(String[] args) {
        InformationAccessManager AccessManager = new InformationAccessManager();
        
        //when patient is accessing information
        boolean x = AccessManager.checkInformationAccess("Blood Type");
        System.out.println(x);
    }
}
