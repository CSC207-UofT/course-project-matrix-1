package utilities;

import equation_builders.FractionAddBuilder;
import equation_parameters.FractionAddSubEquationDetails;
import org.junit.Test;

import static org.junit.Assert.*;

public class DistributionCalculatorTest {
    @Test
    public void testAssignProbability(){
        FractionAddBuilder fb = new FractionAddBuilder();
        FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
        fractionAddSubEquationDetails.setOperand1DenomRange(new int[]{1,20});
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(20);
        fractionAddSubEquationDetails.setMaxOperandValue(1);
        DistributionCalculator.assignProbability(fractionAddSubEquationDetails);
        System.out.println(DistributionCalculator.getDenomDistribution());
    }

}