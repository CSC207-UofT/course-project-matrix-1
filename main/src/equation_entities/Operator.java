package equation_entities;

/**
 * A BEDMAS operator. All Operators can solve binary expressions.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-28
 */
public abstract class Operator implements Symbol {
    /**
     * Solves a binary expression consisting of two operands for a given Value.
     *
     * @param operand1 the first operand, on the left side of the operator.
     * @param operand2 the second operand, on the right side of the operator.
     * @return the calculated value.
     */
    public abstract Value solveBinaryExpression(Value operand1, Value operand2);
}
