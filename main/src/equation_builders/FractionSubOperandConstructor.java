package equation_builders;

import equation_parameters.FractionAddSubEquationDetails;

/**
 * Handles the construction of operands for the fraction subtraction equations.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionSubOperandConstructor extends FractionAddSubOperandConstructor implements OperandConstructorInterface {

    /**
     * Generates the second operand numerator. If negative is not allowed, then it must be smaller than the first
     * numerator (when converted to improper form).
     *
     * @param operandsN            the numerator operands as [numerator1, numerator2].
     * @param operandsD            the denominator operands as [denominator1, denominator2].
     * @param fracAddSubEqnDetails the parameters specific to addition subtraction fraction equation generation.
     */
    @Override
    protected void generateOperand2Numerator(int[] operandsN, int[] operandsD, FractionAddSubEquationDetails
            fracAddSubEqnDetails) {
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operandsN[1] = randomizer.randomize(0, operandsD[1] * fracAddSubEqnDetails.getMaxOperandValue());
        } else {
            operandsN[1] = randomizer.randomize(0, operandsN[0] * operandsD[1] / operandsD[0]);
        }
    }

}
