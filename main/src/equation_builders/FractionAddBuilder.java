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
        int answerD = calculateAnswerD(seed, fracAddSubEqnDetails, operand1D);
        int operand2D = calculateOperand2D(seed, fracAddSubEqnDetails, operand1D, answerD);
        int operand1N = rand.randomize(0, operand1D, seed) + operand1D *
                (fracAddSubEqnDetails.getMaxOperandValue() - 1);
        int operand2N = rand.randomize(0, operand2D, seed) + operand2D *
                (fracAddSubEqnDetails.getMaxOperandValue() - 1);
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        bedmasEquation.setOperand1(new Fraction(operand1N, operand1D));
        bedmasEquation.setOperand2(new Fraction(operand2N, operand2D));
    }

    /**
     * Uses the operand1 denominator to generate a set of possible answer denominators, and randomly selects from
     * those possible answer denominators. Mathematically, answerD % operand1D = 0.
     *
     * @param seed                 random seed to fix random generation of operands
     * @param fracAddSubEqnDetails the parameters for specifically fraction addition/subtraction equation generation.
     * @param operand1D            the first operand denominator.
     * @return the answer denominator.
     */
    private int calculateAnswerD(int seed, FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D) {
        int maxMultiple = fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom() / operand1D;
        ArrayList<Integer> possibleAnswerD = new ArrayList<>();
        for (int i = 1; i < maxMultiple + 1; i++) {
            possibleAnswerD.add(i * operand1D);
        }
        return rand.randomize(possibleAnswerD, seed);
    }

    /**
     * Uses the answer denominator and operand1 denominator to generate an operand2 denominator that satisfies the
     * equation. Mathematically, this means LCM(operand1D, operand2D) = answerD.
     *
     * @param seed                 random seed to fix random generation of operands
     * @param fracAddSubEqnDetails the parameters for specifically fraction addition/subtraction equation generation.
     * @param operand1D            the first operand denominator.
     * @param answerD              the second operand denominator.
     * @return the second operand denominator.
     */
    private int calculateOperand2D(int seed, FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D,
                                   int answerD) {
        Set<Integer> necessaryOperand2DFactors = FactorFinder.primeFactorizeExponent(answerD);
        necessaryOperand2DFactors.removeAll(FactorFinder.primeFactorizeExponent(operand1D));
        int necessaryOperand2DValue = 1;
        for (int p : necessaryOperand2DFactors) {
            necessaryOperand2DValue *= p;
        }
        ArrayList<Integer> possibleOperand2D = new ArrayList<>();
        for (int p : FactorFinder.findFactors(answerD / necessaryOperand2DValue)) {
            possibleOperand2D.add(p * necessaryOperand2DValue);
        }
        //TODO: Modify this randomization algorithm to use the distribution calculator without recalculating.
        DistributionCalculator.modifyWeights(possibleOperand2D);
        int operand2D = rand.randomize(possibleOperand2D, seed);
        if (fracAddSubEqnDetails.getMaxOperandValue() <= 0) {
            throw new InvalidInputException();
        }
        return operand2D;
    }

}

