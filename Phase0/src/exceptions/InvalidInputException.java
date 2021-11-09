package exceptions;

/**
 * Signifies that the user gave an invalid input. These inputs should either be stopped at a UI level or will be
 * addressed in a future update.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Invalid input. These inputs should either be stopped at a UI level or will be addressed in a future" +
                "update.");
    }
}
