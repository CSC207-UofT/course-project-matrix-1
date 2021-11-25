package equation_entities;
/**
 * Raises the first Value to the power of the second Value and returns the string representation of this operator.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-11-16
 */
public class Exponentiate extends Operator {
    /**
     * Multiplies the first operand by itself (second operand) number of times.
     *
     * @param operand1 the first operand, on the left side of the operator.
     * @param operand2 the second operand, on the right side of the operator.
     * @return operand1 raised to the operand2 power.
     */
    @Override
    public Value solveBinaryExpression(Value operand1, Value operand2) {
        return operand1.exponentiate(operand2);
    }

    /**
     * Returns the latex exponentiate operator "^"
     *
     * @return "^"
     */
    @Override
    public String toString() {
        return "^";
    }
}
