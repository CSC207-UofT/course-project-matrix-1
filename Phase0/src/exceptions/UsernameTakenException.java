package exceptions;

/**
 * Exception is thrown when a searched worksheet does not exist.
 *
 * @author Stanley Hua
 */
public class UsernameTakenException extends RuntimeException {
    public UsernameTakenException() {super("Username already taken!");}
}
