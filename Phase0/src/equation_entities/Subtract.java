package equation_entities;

public class Subtract extends Operator {
    /**
     * Subtracts the two operands together.
     *
     * @param operand1 the first operand, on the left side of the operator.
     * @param operand2 the second operand, on the right side of the operator.
     * @return the difference of the operands.
     */
    @Override
    public Value solveBinaryExpression(Value operand1, Value operand2) {
        return operand1.subtract(operand2);
    }

    /**
     * Returns the minus operator as -
     *
     * @return the symbol -
     */
    @Override
    public String toString() {
        return "-";
    }
}
