package exceptions;

/**
 * Exception is thrown when a searched worksheet does not exist.
 *
 * @author Stanley Hua
 */
public class RecordDoesNotExistException extends RuntimeException {
    public RecordDoesNotExistException() {
        super("Specified worksheet does not exist!");
    }
}
