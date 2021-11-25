package equation_builders;

import equation_entities.Add;
import equation_entities.Fraction;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import utilities.DistributionCalculator;

public class FractionAddBuilder extends FractionBuilder {

    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Add());
    }

    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable operands for fraction addition.
     *
     * @param fracEqnDetails the parameters for fraction equation generation.
     * @param seed           random seed to fix random generation of operands
     */
    @Override
    protected void buildOperands(EquationDetails fracEqnDetails, int seed) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        int operand1D = rand.randomize(DistributionCalculator.getDenomDistribution(), seed);
        int answerD = calculateAnswerD(seed, fracAddSubEqnDetails, operand1D);
        int operand2D = calculateOperand2D(seed, fracAddSubEqnDetails, operand1D, answerD);
        int operand1N = rand.randomize(0, operand1D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        seed += 5;
        int operand2N = rand.randomize(0, operand2D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            seed += 5;
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        bedmasEquation.setOperand1(new Fraction(operand1N, operand1D));
        bedmasEquation.setOperand2(new Fraction(operand2N, operand2D));
    }



}

