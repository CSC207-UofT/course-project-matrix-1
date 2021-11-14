package exceptions;

/**
 * Exception is thrown when a specified user does not exist.
 *
 * @author Stanley Hua
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException() {
        super("User does not exist!");
    }
}
