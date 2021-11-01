package equation_builders;

import equation_entities.Multiply;
import equation_entities.WholeNum;

/**
 * Builds whole number multiplication equations. Both operands are uniformly distributed across their specified range.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeBedmasMultiplyBuilder extends WholeBedmasBuilder {
    /**
     * Assigns the multiplication operator to the equation.
     */
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator(new Multiply());
    }

    /**
     * Creates operands (first and second) in the multiplication bedmas equation's question.
     *
     * @param operandRange1 the inclusive absolute range of values that the first operand can be.
     * @param operandRange2 the inclusive absolute range of values that the second operand can be.
     * @param negAllowed    if true, each operand has a 50% of becoming negative after being randomly determined.
     */
    @Override
    public void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        //TODO: Duplicate code, how do we fix this?
        int operand1 = randomize(operandRange1);
        int operand2 = randomize(operandRange2);
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1);
            operand2 = makeNegativeRandom(operand2);
        }
        bedmasEquation.setOperand1(new WholeNum(operand1));
        bedmasEquation.setOperand2(new WholeNum(operand2));
    }

}
