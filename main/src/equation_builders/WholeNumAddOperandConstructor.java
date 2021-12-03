package equation_builders;

import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;


/**
 * Handles the construction of operands for the whole number addition, multiplication, or exponentiation. Both operands
 * are uniformly distributed across their specified range.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumAddOperandConstructor implements OperandConstructorInterface {
    /**
     * Builds the operands (first and second) for the standardEquation.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param randomizer      Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, Randomizer randomizer) {
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        int operand1 = randomizer.randomize(wholeEquationDetails.getOperandRange1());
        int operand2 = randomizer.randomize(wholeEquationDetails.getOperandRange2());
        if (equationDetails.isNegAllowed()) {
            operand1 = randomizer.makeNegativeRandom(operand1);
            operand2 = randomizer.makeNegativeRandom(operand2);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
    }
}


