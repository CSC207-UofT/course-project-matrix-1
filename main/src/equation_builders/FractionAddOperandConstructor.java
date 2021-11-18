package equation_builders;

import equation_entities.Add;
import equation_entities.Fraction;
import equation_entities.Value;
import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.FractionEquationDetails;
import equation_parameters.WholeNumEquationDetails;
import exceptions.InvalidInputException;
import utilities.Randomizer;

import java.util.ArrayList;
import java.util.Set;

public class FractionAddOperandConstructor implements OperandConstructorInterface {
    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable equations.
     *
     * RANDOM SEED (for fixing random number generation): First random operation uses the random seed. Succeeding
     * operations increment the random seed by 5.
     *
     * @param equationDetails contains the necessary parameters for equation generation.
     * @param seed random seed to fix randomness in generating of operands
     * @param rand Randomizer instance used to perform random number generation.
     * @return array of first operand and second operand values
     */
    public Value[] buildOperands(EquationDetails equationDetails, int seed, Randomizer rand) {
        FractionEquationDetails fracEquationDetails = (FractionEquationDetails) equationDetails;

        int operand1D = rand.randomize(getDenomDistribution(), seed);
        int maxMultiple = fracEquationDetails.getMaxDenominator() / operand1D;
        ArrayList<Integer> possibleAnswerD = new ArrayList<>();
        for (int i = 1; i < maxMultiple + 1; i++) {
            possibleAnswerD.add(i * operand1D);
        }
        int answerD = rand.randomize(possibleAnswerD, seed);
        Set<Integer> operand1DFactors = EquationDirector.findFactors(operand1D);
        Set<Integer> necessaryOperand2DFactors = EquationDirector.findFactors(answerD);
        necessaryOperand2DFactors.removeAll(operand1DFactors);
        int necessaryOperand2DValue = 1;
        for (int p : necessaryOperand2DFactors) {
            necessaryOperand2DValue *= p;
        }
        ArrayList<Integer> possibleOperand2D = new ArrayList<>();
        possibleOperand2D.add(necessaryOperand2DValue);
        for (int p : operand1DFactors) {
            int length = possibleOperand2D.size();
            for (int dIndex = 0; dIndex < length; dIndex++) {
                possibleOperand2D.add(p * possibleOperand2D.get(dIndex));
            }
        }
        int operand2D = rand.randomize(possibleOperand2D, seed);
        if (fracEquationDetails.getMaxValue() <= 0) {
            throw new InvalidInputException();
        }
        int operand1N = rand.randomize(0, operand1D, seed) + operand1D * (fracEquationDetails.getMaxValue() - 1);
        int operand2N = rand.randomize(0, operand2D, seed) + operand2D * (fracEquationDetails.getMaxValue() - 1);
        if (fracEquationDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        return new Value[]{new Fraction(operand1N, operand1D), new Fraction(operand2N, operand2D)};
    }

}

