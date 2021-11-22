package equation_builders;

import equation_entities.Add;
import equation_entities.Fraction;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import exceptions.InvalidInputException;
import utilities.DistributionCalculator;
import utilities.FactorFinder;

import java.util.ArrayList;
import java.util.Set;

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
        int maxMultiple = fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom() / operand1D;
        ArrayList<Integer> possibleAnswerD = new ArrayList<>();
        for (int i = 1; i < maxMultiple + 1; i++) {
            possibleAnswerD.add(i * operand1D);
        }
        int answerD = rand.randomize(possibleAnswerD, seed);
        Set<Integer> operand1DFactors = FactorFinder.findFactors(operand1D);
        Set<Integer> necessaryOperand2DFactors = FactorFinder.primeFactorizeExponent(answerD);
        necessaryOperand2DFactors.removeAll(FactorFinder.primeFactorizeExponent(operand1D));
        int necessaryOperand2DValue = 1;
        for (int p : necessaryOperand2DFactors) {
            necessaryOperand2DValue *= p;
        }
        ArrayList<Integer> possibleOperand2D = new ArrayList<>();
        for (int p : FactorFinder.findFactors(answerD/necessaryOperand2DValue)) {
            possibleOperand2D.add(p * necessaryOperand2DValue);
        }
        int operand2D = rand.randomize(possibleOperand2D, seed);
        if (fracAddSubEqnDetails.getMaxOperandValue() <= 0) {
            throw new InvalidInputException();
        }
        int operand1N = rand.randomize(0, operand1D, seed) + operand1D * (fracAddSubEqnDetails.getMaxOperandValue() - 1);
        int operand2N = rand.randomize(0, operand2D, seed) + operand2D * (fracAddSubEqnDetails.getMaxOperandValue() - 1);
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        bedmasEquation.setOperand1(new Fraction(operand1N, operand1D));
        bedmasEquation.setOperand2(new Fraction(operand2N, operand2D));
    }

}

