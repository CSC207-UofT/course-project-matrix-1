package exceptions;

/**
 * Exception is thrown when an Operator is specified with an invalid Operand Type.
 *
 * @author Stanley Hua
 */
public class IllegalOperatorForOperandTypeException extends RuntimeException {
    public IllegalOperatorForOperandTypeException() {
        super("Combination of operator and operand type does not exist or is not implemented!");
    }
}
