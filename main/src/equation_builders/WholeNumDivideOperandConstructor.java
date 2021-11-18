package equation_builders;

import equation_entities.Divide;
import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds whole number division equations. Only equations that yield whole number answers are built.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumDivideOperandConstructor implements OperandConstructorInterface {
    /**
     * Creates operands (first and second) in the division bedmas equation's question. Operand 1 must be divisible by
     * operand 2 to yield a negative answer.
     *
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails equationDetails, int seed, Randomizer rand) {
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        //TODO: Zeros are not allowed for operator 2. At UI level, restrict this.
        // TODO: Why restrict at UI level? 0 can be removed upon random number generation
        List<Integer> possibleOperand2 = restrictRanges(wholeEquationDetails.getOperandRange1(), wholeEquationDetails.getOperandRange2());
        int operand2 = rand.randomize(possibleOperand2, seed);
        int[] answerRange = new int[]{(int) Math.ceil((float) wholeEquationDetails.getOperandRange1()[0] / operand2),
                (int) Math.floor((float) wholeEquationDetails.getOperandRange1()[1] / operand2)};
        int answer = rand.randomize(answerRange, seed + 5);
        int operand1 = answer * operand2;
        if (wholeEquationDetails.isNegAllowed()) {
            operand1 = rand.makeNegativeRandom(operand1, seed + 10);
            operand2 = rand.makeNegativeRandom(operand2, seed + 15);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
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

