package equation_builders;

import equation_parameters.FractionEquationDetails;
import org.junit.Test;

public class FractionBuilderTest {
    @Test
    public void testAssignProbability(){
        FractionBuilder fb = new FractionAddOperandConstructor();
        fb.assignProbability(new FractionEquationDetails(new int[]{1,20},20,1));
        System.out.println(fb.getDenomDistribution());
    }
}