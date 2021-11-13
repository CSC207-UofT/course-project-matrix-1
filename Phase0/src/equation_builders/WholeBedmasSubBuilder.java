package equation_builders;

import equation_entities.Subtract;
import equation_entities.WholeNum;
/**
 * Builds whole number subtraction equations. If negatives are not specified, only subtraction equations that yield
 * positive answers are produced.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-10-30
 */
class WholeBedmasSubBuilder extends WholeBedmasBuilder {

    /**
     * Assigns the subtraction operator to the equation.
     */
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Subtract());
    }

    /**
     * Creates operands (first and second) in the subtraction bedmas equation's question. If not negAllowed, prevent
     * operand 2 from being greater than operand 1.
     *
     * RANDOM SEED (for fixing random number generation):
     *      First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param operandRange1 the absolute range of values that the first operand can be.
     * @param operandRange2 the absolute range of values that the second operand can be.
     * @param negAllowed    specifies if the operands or answer are allowed to be negative.
     * @param seed          random seed to fix random generation of operands.
     */
    @Override
    protected void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        //TODO: Fix bad inputs (ex. operand2 range is greater than operand1)
        int operand1 = randomize(operandRange1, seed);
        int operand2;
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1, seed + 5);
            operand2 = makeNegativeRandom(randomize(operandRange2,seed + 10), seed + 15);
        } else {
            operand2 = randomize(operandRange2[0], operand1, seed + 5);
        }
        bedmasEquation.setOperand1(new WholeNum(operand1));
        bedmasEquation.setOperand2(new WholeNum(operand2));
    }
}
