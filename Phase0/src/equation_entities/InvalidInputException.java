<<<<<<< HEAD:Phase0/src/exceptions/InvalidInputException.java
package exceptions;
=======
package equation_entities;
>>>>>>> main:Phase0/src/equation_entities/InvalidInputException.java

/**
 * Signifies that the user gave an invalid input.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class InvalidInputException extends RuntimeException {
    public InvalidInputException() {
        super("Invalid input - we will deal with this eventually.");
    }
}
