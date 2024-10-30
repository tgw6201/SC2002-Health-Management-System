import InformationAccess.InformationAccessManager;
import Logger.FileLogger;
import userLogin.UserLoginServices;

public class Testing {
    public static void main(String[] args) {
        InformationAccessManager AccessManager = new InformationAccessManager();

        String logFileName = "TestLog2.txt";

        FileLogger fileLogger = new FileLogger(logFileName);

        UserLoginServices userLoginServices = new UserLoginServices(fileLogger);
        userLoginServices.login("admin", "admin");
        userLoginServices.logout();
        fileLogger.stopLogging();

        
        //when patient is accessing information
        boolean x = AccessManager.checkInformationAccess("Blood Type");
        System.out.println(x);
    }
}
