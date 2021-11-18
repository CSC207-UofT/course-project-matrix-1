package equation_builders;

import equation_entities.Add;
import equation_entities.Fraction;
import equation_entities.WholeNum;
import equation_parameters.FractionEquationDetails;
import exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.Set;

public class FractionAddBuilder extends FractionBuilder {

    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Add());
    }

    /**
     * Uses the denominator distribution and the maximum possible denominator to get reasonable equations.
     *
     * @param fracEqnDetails
     * @param seed           random seed to fix random generation of operands
     */
    @Override
    protected void buildOperands(FractionEquationDetails fracEqnDetails, int seed) {
        int operand1D = rand.randomize(getDenomDistribution(), seed);
        int maxMultiple = fracEqnDetails.getMaxDenominator() / operand1D;
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
        if (fracEqnDetails.getMaxValue() <= 0) {
            throw new InvalidInputException();
        }
        int operand1N = rand.randomize(0, operand1D, seed) + operand1D * (fracEqnDetails.getMaxValue() - 1);
        int operand2N = rand.randomize(0, operand2D, seed) + operand2D * (fracEqnDetails.getMaxValue() - 1);
        if (fracEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        bedmasEquation.setOperand1(new Fraction(operand1N, operand1D));
        bedmasEquation.setOperand2(new Fraction(operand2N, operand2D));
    }

}

