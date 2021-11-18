package equation_builders;

import equation_entities.Add;
import equation_entities.Equation;
import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;


/**
 * Builds whole number addition equations. Both operands are uniformly distributed across their specified range.
 *
 * @author Sean Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumAddOperandConstructor implements OperandConstructorInterface {
    /**
     * Builds the operands (first and second) for the bedmasEquation.
     *
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, int seed, Randomizer rand){
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        int operand1 = rand.randomize(wholeEquationDetails.getOperandRange1(), seed);
        int operand2 = rand.randomize(wholeEquationDetails.getOperandRange2(), seed + 5);
        if (equationDetails.isNegAllowed()) {
            operand1 = rand.makeNegativeRandom(operand1, seed + 10);
            operand2 = rand.makeNegativeRandom(operand2, seed + 15);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
    }
}
