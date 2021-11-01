package equation_builders;

import equation_entities.Divide;
import equation_entities.WholeNum;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds whole number division equations. Only equations that yield whole number answers are built.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
public class WholeBedmasDivideBuilder extends WholeBedmasBuilder {
    /**
     * Assigns the division operator to the equation.
     */
    @Override
    public void buildOperator() {
        bedmasEquation.setOperator(new Divide());
    }

    /**
     * Creates operands (first and second) in the division bedmas equation's question. Operand 1 must be divisible by
     * operand 2 to yield a negative answer.
     *
     * @param operandRange1 the inclusive absolute range of values (min, max) that the first operand can be. The max of
     *                      operandRange1 must be greater than or equal to the min of operandRange2. At least one number
     *                      in this range must be divisible by a number in operandRange2.
     * @param operandRange2 the inclusive absolute range of values that the second operand can be.
     * @param negAllowed    if true, each operand has a 50% of becoming negative after being randomly determined.
     */
    @Override
    public void buildOperands(int[] operandRange1, int[] operandRange2, boolean negAllowed) {
        //TODO: Zeros are not allowed for operator 2. At UI level, restrict this.
        List<Integer> possibleOperand2 = restrictRanges(operandRange1, operandRange2);
        int operand2 = randomize(possibleOperand2);
        int[] answerRange = new int[]{(int) Math.ceil((float) operandRange1[0] / operand2),
                (int) Math.floor((float) operandRange1[1] / operand2)};
        int answer = randomize(answerRange);
        int operand1 = answer * operand2;
        if (negAllowed) {
            operand1 = makeNegativeRandom(operand1);
            operand2 = makeNegativeRandom(operand2);
        }
        bedmasEquation.setOperand1(new WholeNum(operand1));
        bedmasEquation.setOperand2(new WholeNum(operand2));
    }

    /**
     * Restricts the ranges of the two operands so that division is possible, and then further restricts operand2 into
     * an array list of possible list integers.
     *
     * @param operandRange1 the absolute range of values (min, max) that the first operand can be. The max of
     *                      operandRange1 must be greater than or equal to the min of operandRange2. At least one number
     *                      in this range must be divisible by a number in operandRange2.
     * @param operandRange2 the absolute range of values (min, max) that the second operand can be. Cannot include 0.
     * @return the possible integers operand2 can be.
     */
    private ArrayList<Integer> restrictRanges(int[] operandRange1, int[] operandRange2) {
        operandRange2[1] = Math.min(operandRange1[1], operandRange2[1]);
        operandRange1[0] = Math.max(operandRange1[0], operandRange2[0]);
        ArrayList<Integer> possibleOperand2 = new ArrayList<>();
        for (int operand2 = operandRange2[0]; operand2 <= operandRange2[1]; operand2++) {
            if ((operandRange1[0] % operand2 == 0) || (operandRange1[1] % operand2 == 0)) {
                possibleOperand2.add(operand2); //operand2 is divisible as long as the outer bounds are used.
            } else {
                int minQuotient = operandRange1[0] / operand2;
                int maxQuotient = operandRange1[1] / operand2;
                if (minQuotient != maxQuotient) { //If the truncated quotients are different, this means a whole number
                    //is in between and thus this is a possible operand
                    possibleOperand2.add(operand2);
                }
            }
        }
        return possibleOperand2;
    }
}

