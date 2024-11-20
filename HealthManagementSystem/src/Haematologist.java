import FileManager.dataReader;
import FileManager.dataWriter;
import Logger.*;
import java.util.Scanner;

public class Haematologist {

    private String userID;
    private String userRole;
    private Logger logger;

    //Object pointer initialization
    private Scanner sc = new Scanner(System.in);
    Haematologist(String userID, String userRole, Logger logger, dataReader reader, dataWriter writer)
    {
        this.userID= userID;
        this.userRole = userRole;
        this.logger = logger;
    }
    public void menu()
    {
        System.out.println("Reflection successful, Addition of new role done");
    }
}
