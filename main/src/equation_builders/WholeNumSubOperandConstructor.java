package equation_builders;

import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;

/**
 * Handles the construction of operands for the whole number subtraction equations. If negatives are not specified, only
 * subtraction equations that yield positive answers are produced.
 *
 * @author Sean Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumSubOperandConstructor implements OperandConstructorInterface {
    /**
     * Creates operands (first and second) in the subtraction standard equation's question. If not negAllowed, prevent
     * operand 2 from being greater than operand 1.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param randomizer      Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, Randomizer randomizer) {
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        int operand1 = randomizer.randomize(wholeEquationDetails.getOperandRange1());
        int operand2;
        if (equationDetails.isNegAllowed()) {
            operand1 = randomizer.makeNegativeRandom(operand1);
            operand2 = randomizer.makeNegativeRandom(randomizer.randomize(wholeEquationDetails.getOperandRange2()));
        } else {
            operand2 = randomizer.randomize(wholeEquationDetails.getOperandRange2()[0], operand1);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
    }
}
