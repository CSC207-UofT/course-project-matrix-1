package equation_builders;

import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import utilities.DistributionCalculator;
import utilities.FractionCalculator;
import utilities.Randomizer;

/**
 * Handles the construction of operands for the fraction subtraction equations.
 *
 * @author Will Jeong, Stanley Hua
 * @version 1.0
 * @since 2021-11-22
 */
public class FractionSubOperandConstructor implements OperandConstructorInterface {

    @Override
    public Value[] buildOperands(EquationDetails fracEqnDetails, Randomizer randomizer) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;

        int operand1D = randomizer.randomize(DistributionCalculator.getDenomDistribution());
        int answerD = FractionCalculator.calculateAnswerD(fracAddSubEqnDetails, operand1D, randomizer);
        int operand2D = FractionCalculator.calculateOperand2D(fracAddSubEqnDetails, operand1D, answerD, randomizer);

        int operand1N = randomizer.randomize(0, operand1D * fracAddSubEqnDetails.getMaxOperandValue());
        int operand2N;
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand2N = randomizer.randomize(0, operand2D * fracAddSubEqnDetails.getMaxOperandValue());
        } else {
            operand2N = randomizer.randomize(0, operand1N * operand2D / operand1D);
        }
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = randomizer.makeNegativeRandom(operand1N);
            operand2N = randomizer.makeNegativeRandom(operand2N);
        }

        return new Value[]{new Fraction(operand1N, operand1D), new Fraction(operand2N, operand2D)};
    }


}
