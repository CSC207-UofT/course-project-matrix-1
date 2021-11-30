package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import utilities.DistributionCalculator;
import utilities.FractionCalculator;
import utilities.Randomizer;

/**
 * Contains helper methods used by both fraction addition and subtraction of operands.
 *
 * @author Sean Jeong
 * @version 1.0
 * @since 2021-11-28
 */

public abstract class FractionAddSubOperands {
    protected Randomizer randomizer;

    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable operands for fraction
     * addition/subtraction.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     * @param randomizer     Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails fracEqnDetails, Randomizer randomizer) {
        this.randomizer = randomizer;
        int[] operandsN = new int[2];
        int[] operandsD = new int[2];
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        generateMostOperands(operandsN, operandsD, fracAddSubEqnDetails);
        generateOperand2Numerator(operandsN, operandsD, fracAddSubEqnDetails);
        makeOperandsNegative(fracAddSubEqnDetails.isNegAllowed(), operandsN);
        return new Value[]{new Fraction(operandsN[0], operandsD[0]), new Fraction(operandsN[1], operandsD[1])};
    }

    /**
     * If negative is allowed, the operands have a 50% chance of becoming negative.
     *
     * @param isNegAllowed whether negatives are allowed for this equation.
     * @param operandsN    the numerator operands as [numerator1, numerator2].
     */
    protected void makeOperandsNegative(boolean isNegAllowed, int[] operandsN) {
        if (isNegAllowed) {
            operandsN[0] = randomizer.makeNegativeRandom(operandsN[0]);
            operandsN[1] = randomizer.makeNegativeRandom(operandsN[1]);
        }
    }

    /**
     * Generates the second operand numerator.
     *
     * @param operandsN            the numerator operands as [numerator1, numerator2].
     * @param operandsD            the denominator operands as [denominator1, denominator2].
     * @param fracAddSubEqnDetails the parameters specific to addition subtraction fraction equation generation.
     */
    protected abstract void generateOperand2Numerator
    (int[] operandsN, int[] operandsD, FractionAddSubEquationDetails fracAddSubEqnDetails);

    /**
     * Creates both operand denominators and the first operand numerator. This is not specific to addition or
     * subtraction because these operand parts require the same distribution.
     *
     * @param operandsN            the numerator operands as [numerator1, numerator2].
     * @param operandsD            the denominator operands as [denominator1, denominator2].
     * @param fracAddSubEqnDetails the parameters specific to addition subtraction fraction equation generation.
     */
    protected void generateMostOperands(int[] operandsN, int[] operandsD, FractionAddSubEquationDetails
            fracAddSubEqnDetails) {
        operandsD[0] = randomizer.randomize(DistributionCalculator.getDenomDistribution());
        int answerD = FractionCalculator.calculateAnswerD(fracAddSubEqnDetails, operandsD[0], randomizer);
        operandsD[1] = FractionCalculator.calculateOperand2D(fracAddSubEqnDetails, operandsD[0], answerD, randomizer);
        operandsN[0] = randomizer.randomize(0, operandsD[0] * fracAddSubEqnDetails.getMaxOperandValue());
    }
}
