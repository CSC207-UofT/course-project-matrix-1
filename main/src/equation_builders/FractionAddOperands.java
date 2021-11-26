package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import utilities.DistributionCalculator;
import utilities.FractionCalculator;
import utilities.Randomizer;

/**
 * Handles the construction of operands for the fraction addition equations.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionAddOperands implements OperandConstructorInterface {
    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable operands for fraction addition.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     * @param rand           Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails fracEqnDetails, Randomizer rand) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        int operand1D = rand.randomize(DistributionCalculator.getDenomDistribution());
        int answerD = FractionCalculator.calculateAnswerD(fracAddSubEqnDetails, operand1D, rand);
        int operand2D = FractionCalculator.calculateOperand2D(fracAddSubEqnDetails, operand1D, answerD, rand);
        int operand1N = rand.randomize(0, operand1D * fracAddSubEqnDetails.getMaxOperandValue());
        int operand2N = rand.randomize(0, operand2D * fracAddSubEqnDetails.getMaxOperandValue());
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N);
            operand2N = rand.makeNegativeRandom(operand2N);
        }
        return new Value[]{new Fraction(operand1N, operand1D), new Fraction(operand2N, operand2D)};
    }

}

