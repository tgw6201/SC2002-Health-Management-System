package userLogin;
public interface userLogin {
    public boolean login(String username, String password);
    public boolean logout();
    public boolean register(String username, String password, String role);
    public boolean changePassword(String username, String password);
    public boolean resetPassword(String username);
    public String getRole();
}
