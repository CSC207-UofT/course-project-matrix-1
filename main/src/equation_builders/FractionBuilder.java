package equation_builders;

import equation_entities.BedmasEquation;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import exceptions.InvalidInputException;
import utilities.DistributionCalculator;
import utilities.FactorFinder;
import utilities.Randomizer;

import java.util.*;

/**
 * An abstract builder for all fraction equation builders.
 *
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-30
 */
abstract class FractionBuilder{
    protected BedmasEquation bedmasEquation;
    protected Randomizer rand;


    protected BedmasEquation getBedmasEquation() {
        return bedmasEquation;
    }

    /**
     * Creates a new instance of the bedmas equation.
     */
    protected void createNewBedmasEquationProduct() {
        bedmasEquation = new BedmasEquation();
        rand = new Randomizer();
    }

    /**
     * Builds the bedmasEquation's operator.
     */
    protected abstract void buildOperator();

    /**
     * Builds the operands (first and second) for the bedmasEquation.
     * RANDOM SEED (for fixing random number generation):
     * First random operation uses the random seed. Succeeding operations increment the random seed by 5.
     *
     * @param fractionEquationDetails the parameters for fraction equation generation.
     * @param seed                    random seed to fix random generation of operands
     */
    protected abstract void buildOperands(EquationDetails fractionEquationDetails, int seed);



    /**
     * Builds the bedmasEquation's answer.
     */
    protected void buildAnswer() {
        bedmasEquation.solve();
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
    protected int calculateAnswerD(int seed, FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D) {
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
    protected int calculateOperand2D(int seed, FractionAddSubEquationDetails fracAddSubEqnDetails, int operand1D,
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
        DistributionCalculator.modifyWeights(possibleOperand2D);
        int operand2D = rand.randomize(possibleOperand2D, seed);
        if (fracAddSubEqnDetails.getMaxOperandValue() <= 0) {
            throw new InvalidInputException();
        }
        return operand2D;
    }
}
