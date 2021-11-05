package equation_entities;
/**
 * Divides two Values and returns the string representation of this operator.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class Divide extends Operator {
    /**
     * Divides the two operands.
     *
     * @param operand1 the first operand, on the left side of the operator.
     * @param operand2 the second operand, on the right side of the operator.
     * @return the quotient of the operands.
     */
    @Override
    public Value solveBinaryExpression(Value operand1, Value operand2) {
        return operand1.divide(operand2);
    }

    /**
     * Returns the divide operator as \div
     *
     * @return \div
     */
    @Override
    public String toString() {
        return "\\div";
        //TODO: change test cases
    }

}
