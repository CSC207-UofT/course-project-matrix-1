package equation_entities;

/**
 * Finds the greatest common divisor of the two numbers and returns the string representation of this operator.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-16
 */
public class GCD extends Operator {
    /**
     * Finds the greatest common divisor between two operands.
     *
     * @param operand1 the first number.
     * @param operand2 the second number.
     * @return the greatest common divisor between the two operands.
     */
    @Override
    public Value solveBinaryExpression(Value operand1, Value operand2) {
        return operand1.gcd(operand2);
    }

    /**
     * Returns the GCD operator.
     *
     * @return "GCD"
     */
    @Override
    public String toString() {
        return "GCD";
    }
}
