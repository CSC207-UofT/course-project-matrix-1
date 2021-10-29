package equation_entities;

/**
 * Signifies that the user gave an invalid input, but the UI should have restricted it.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-29
 */
public class InvalidUIRestrictionException extends RuntimeException {
    public InvalidUIRestrictionException() {
        super("Invalid input - the UI did not properly prevent this input");
    }
}
