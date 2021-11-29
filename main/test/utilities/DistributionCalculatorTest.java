package utilities;

import equation_parameters.FractionAddSubEquationDetails;
import org.junit.Test;

import java.util.ArrayList;

public class DistributionCalculatorTest {
    @Test
    public void testAssignProbability() {
        FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
        fractionAddSubEquationDetails.setOperand1DenomRange(new int[]{1, 100});
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(200);
        fractionAddSubEquationDetails.setMaxOperandValue(1);
        DistributionCalculator.assignProbability(fractionAddSubEquationDetails);
        System.out.println(DistributionCalculator.getDenomDistribution());
    }
    @Test
    public void testModifyWeights() {
        ArrayList<Integer> unweighted = new ArrayList<>();
        unweighted.add(2);
        unweighted.add(3);
        unweighted.add(4);
        unweighted.add(7);
        unweighted.add(64);
        DistributionCalculator.modifyWeights(unweighted);
        System.out.println(unweighted);
    }
}