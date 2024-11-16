package userLogin;

/**
 * The userLogin interface defines the contract for a user authentication and management system.
 * Implementations of this interface provide methods for user login, logout, registration, password 
 * management, and role retrieval.
 * <p>
 * This interface is intended for managing user access, ensuring secure handling of credentials 
 * and roles within an application.
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 *     userLogin loginSystem = new SomeLoginSystemImplementation();
 *     loginSystem.register("user1", "password123", "admin");
 *     boolean success = loginSystem.login("user1", "password123");
 * </pre>
 * </p>
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public interface userLogin {
     /**
     * Authenticates a user based on their username and password.
     * 
     * @param username The username of the user attempting to log in.
     * @param password The password associated with the username.
     * @return true if the login is successful; false otherwise.
     */
    public boolean login(String username, String password);
    /**
     * Logs out the current user and ends their session.
     * 
     * @return true if the logout was successful; false otherwise.
     */
    public boolean logout();
    /**
     * Registers a new user with the specified username, password, and role.
     * 
     * @param username The username of the user to be registered.
     * @param password The password for the new user.
     * @param role The role of the new user (e.g., "admin", "user").
     * @return true if the registration is successful; false otherwise.
     */
    public boolean register(String username, String password, String role);
    /**
     * Changes the password for a given user.
     * 
     * @param username The username of the user whose password is to be changed.
     * @param password The new password for the user.
     * @return true if the password change was successful; false otherwise.
     */
    public boolean changePassword(String username, String password);
    /**
     * Resets the password for a user, typically initiated if the user forgot their password.
     * 
     * @param username The username of the user whose password is to be reset.
     * @return true if the password reset request was successful; false otherwise.
     */
    public boolean resetPassword(String username);
    /**
     * Retrieves the role of the currently logged-in user.
     * 
     * @return The role of the logged-in user (e.g., "admin", "user").
     *        Returns null if no user is currently logged in.
     */
    public String getRole();
}
