package equation_builders;

import equation_entities.Fraction;
import equation_entities.Subtract;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import exceptions.InvalidInputException;
import utilities.DistributionCalculator;
import utilities.FactorFinder;

import java.util.ArrayList;
import java.util.Set;


public class FractionSubBuilder extends FractionBuilder {
    @Override
    protected void buildOperator() {
        bedmasEquation.setOperator(new Subtract());
    }

    @Override
    protected void buildOperands(EquationDetails fracEqnDetails, int seed) {
        FractionAddSubEquationDetails fracAddSubEqnDetails = (FractionAddSubEquationDetails) fracEqnDetails;
        int operand1D = rand.randomize(DistributionCalculator.getDenomDistribution(), seed);
        int answerD = calculateAnswerD(seed, fracAddSubEqnDetails, operand1D);
        int operand2D = calculateOperand2D(seed, fracAddSubEqnDetails, operand1D, answerD);
        int operand1N = rand.randomize(0, operand1D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        seed += 5;
        int operand2N;
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand2N = rand.randomize(0, operand2D * fracAddSubEqnDetails.getMaxOperandValue(), seed);
        }else{
            operand2N = rand.randomize(0, operand1N * operand2D / operand1D, seed);
        }
        if (fracAddSubEqnDetails.isNegAllowed()) {
            operand1N = rand.makeNegativeRandom(operand1N, seed);
            seed += 5;
            operand2N = rand.makeNegativeRandom(operand2N, seed);
        }
        bedmasEquation.setOperand1(new Fraction(operand1N, operand1D));
        bedmasEquation.setOperand2(new Fraction(operand2N, operand2D));
    }


}
