package User;

public class User {
    private static final String DEFAULT_PASSWORD = "password"; // Default password constant
    private String hospitalID;
    private String password;
    private String role;

    // Constructor to initialize a new User with default password
    public User(String hospitalID, String role) {
        this.hospitalID = hospitalID;
        this.password = DEFAULT_PASSWORD; // Use constant for default password
        this.role = role;
    }

    // Getter methods for each attribute, excluding password for security
    public String getHospitalID() { return hospitalID; }
    public String getRole() { return role; }

    // toString method for debugging purposes, excluding sensitive information
    @Override
    public String toString() {
        return "User{hospitalID='" + hospitalID + "', role='" + role + "'}";
    }
}
