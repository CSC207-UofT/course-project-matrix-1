package equation_builders;

import equation_entities.Add;
import equation_entities.Fraction;
import equation_entities.Value;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import exceptions.InvalidInputException;
import utilities.FractionCalculator;
import utilities.Randomizer;
import utilities.DistributionCalculator;
import utilities.FactorFinder;

import java.util.ArrayList;
import java.util.Set;

public class FractionAddOperandConstructor implements OperandConstructorInterface {
    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable operands for fraction addition.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    @Override
    public Value[] buildOperands(EquationDetails fracEqnDetails, int seed, Randomizer rand) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        int operand1D = rand.randomize(DistributionCalculator.getDenomDistribution(), seed);
        int answerD = FractionCalculator.calculateAnswerD(seed, fracAddSubEqnDetails, operand1D, rand);
        int operand2D = FractionCalculator.calculateOperand2D(seed, fracAddSubEqnDetails, operand1D, answerD, rand);
        int operand1N = rand.randomize(0, operand1D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        seed += 5;
        int operand2N = rand.randomize(0, operand2D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            seed += 5;
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        return new Value[]{new Fraction(operand1N, operand1D), new Fraction(operand2N, operand2D)};
    }

}

