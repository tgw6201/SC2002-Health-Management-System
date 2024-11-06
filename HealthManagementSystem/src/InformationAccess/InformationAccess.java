package InformationAccess;

/**
 * The InformationAccess interface defines a method for checking access permissions
 * to specific pieces of information. Implementations of this interface can be used
 * to verify whether a user or system has the required access to view or modify
 * a particular variable or data element.
 *
 * <p>
 * This interface can be implemented to enforce data security policies,
 * validate user permissions, or control access to sensitive information.
 * </p>
 * 
 * @author Tan Guang Wei
 * @version 1.0
 * @since 2024-11-6
 */

public interface InformationAccess {
     /**
     * Checks whether access to a specified variable is allowed.
     * 
     * @param variable The name or identifier of the variable to check access for.
     * @return {@code true} if access is permitted for the specified variable;
     *         {@code false} otherwise.
     * 
     * <p>
     * This method should implement any necessary logic to validate access permissions
     * based on the variable's identity, user roles, or other conditions defined by
     * the implementing class.
     * </p>
     */
    public boolean checkInformationAccess(String variable);
}
