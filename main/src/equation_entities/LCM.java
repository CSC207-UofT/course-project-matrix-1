package equation_entities;
/**
 * Finds the least common multiple of the two numbers and returns the string representation of this operator.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-16
 */
public class LCM extends Operator {
    /**
     * Finds the least common multiple between two operands.
     *
     * @param operand1 the first number.
     * @param operand2 the second number.
     * @return the least common multiple between the two operands.
     */
    @Override
    public Value solveBinaryExpression(Value operand1, Value operand2) {
        return operand1.lcm(operand2);
    }

    /**
     * Returns the LCM operator.
     *
     * @return "LCM"
     */
    @Override
    public String toString() {
        return "LCM";
    }
}
