package exceptions;

/**
 * NotImplementedException is thrown when a not implemented method is called.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-24
 */
public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("Invalid method call! Called method is not implemented.");
    }
}
