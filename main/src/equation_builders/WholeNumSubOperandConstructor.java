package equation_builders;

import equation_entities.Subtract;
import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;

/**
 * Builds whole number subtraction equations. If negatives are not specified, only subtraction equations that yield
 * positive answers are produced.
 *
 * @author Sean Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumSubOperandConstructor implements OperandConstructorInterface {
    /**
     * Creates operands (first and second) in the subtraction bedmas equation's question. If not negAllowed, prevent
     * operand 2 from being greater than operand 1.
     *
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, int seed, Randomizer rand) {
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        //TODO: Fix bad inputs (ex. operand2 range is greater than operand1)
        int operand1 = rand.randomize(wholeEquationDetails.getOperandRange1(), seed);
        int operand2;
        if (equationDetails.isNegAllowed()) {
            operand1 = rand.makeNegativeRandom(operand1, seed + 5);
            operand2 = rand.makeNegativeRandom(rand.randomize(wholeEquationDetails.getOperandRange2(),seed + 10), seed + 15);
        } else {
            operand2 = rand.randomize(wholeEquationDetails.getOperandRange2()[0], operand1, seed + 5);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
    }
}
