/**
 * Signifies that the user gave an invalid input.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class InvalidInputException extends RuntimeException {
    InvalidInputException() {
        super("Invalid input - we will deal with this eventually.");
    }
}