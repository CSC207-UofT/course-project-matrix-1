package exceptions;

/**
 * Exception is thrown when a user-related function is called when no user is currently logged in.
 *
 * @author Stanley Hua
 */
public class NotLoggedInException extends RuntimeException {
    public NotLoggedInException() {
        super("Function requires user is logged in!");
    }
}
