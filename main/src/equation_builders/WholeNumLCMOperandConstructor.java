package equation_builders;

import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.WholeNumEquationDetails;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.Set;

/**
 * Handles the construction of operands for the whole number LCM equations.
 *
 * @author Stanley Hua
 * @version 1.0
 * @since 2021-10-30
 */
class WholeNumLCMOperandConstructor implements OperandConstructorInterface {
    /**
     * Creates operands (first and second) in the LCM bedmas equation's question.
     *
     * To avoid difficult LCM equations, only allow operands with at least 1 common factor.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param randomizer      Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, Randomizer randomizer) {
        WholeNumEquationDetails wholeEquationDetails = (WholeNumEquationDetails) equationDetails;
        int operand1 = randomizer.randomize(wholeEquationDetails.getOperandRange1());
        int operand2 = 0;
        Set<Integer> operand1Factors = FactorFinder.findFactors(operand1);

        boolean containsCommonFactor = false;
        while (!containsCommonFactor) {
            operand2 = randomizer.randomize(wholeEquationDetails.getOperandRange2());
            Set<Integer> operand2Factors = FactorFinder.findFactors(operand2);

            for (Integer i: operand1Factors) {
                if (operand2Factors.contains(i) && !i.equals(1)) {
                    containsCommonFactor = true;
                    break;
                }
            }
        }

        if (equationDetails.isNegAllowed()) {
            operand1 = randomizer.makeNegativeRandom(operand1);
            operand2 = randomizer.makeNegativeRandom(operand2);
        }
        return new Value[]{new WholeNum(operand1), new WholeNum(operand2)};
    }
}
