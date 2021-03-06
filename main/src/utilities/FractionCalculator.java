package utilities;

import equation_parameters.FractionAddSubEquationDetails;
import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Set;

public class FractionCalculator {

    /**
     * Uses the operand1 denominator to generate a set of possible answer denominators, and randomly selects from
     * those possible answer denominators. Mathematically, answerD % operand1D = 0.
     *
     * @param fracAddSubEqnDetails the parameters for specifically fraction addition/subtraction equation generation.
     * @param operand1D            the first operand denominator.
     * @return the answer denominator.
     */
    public static int calculateAnswerD(FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D,
                                       Randomizer rand) {
        int maxMultiple = fracAddSubEqnDetails.getMaxOperand2AndAnswerDenom() / operand1D;
        ArrayList<Integer> possibleAnswerD = new ArrayList<>();
        for (int i = 1; i < maxMultiple + 1; i++) {
            possibleAnswerD.add(i * operand1D);
        }
        return rand.randomize(possibleAnswerD);
    }

    /**
     * Uses the answer denominator and operand1 denominator to generate an operand2 denominator that satisfies the
     * equation. Mathematically, this means LCM(operand1D, operand2D) = answerD.
     *
     * @param fracAddSubEqnDetails the parameters for specifically fraction addition/subtraction equation generation.
     * @param operand1D            the first operand denominator.
     * @param answerD              the second operand denominator.
     * @return the second operand denominator.
     */
    public static int calculateOperand2D(FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D, int answerD,
                                         Randomizer rand) {
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
        DistributionCalculator.modifyWeights(possibleOperand2D);
        int operand2D = rand.randomize(possibleOperand2D);
        if (fracAddSubEqnDetails.getMaxOperandValue() <= 0) {
            throw new InvalidInputException();
        }
        return operand2D;
    }
}