package utilities;

import equation_entities.WholeNum;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FractionCalculatorTest {
    private FractionAddSubEquationDetails eqnDetails = new FractionAddSubEquationDetails();
    private Randomizer randomizer;

    @Before
    public void init() {
        eqnDetails.setMaxOperand2AndAnswerDenom(20);
        eqnDetails.setMaxOperandValue(2);
        randomizer = new Randomizer(10000);
    }

    @Test
    public void testOneAnswerD() {
        for (int i = 0; i < 100; i++) {
            int ans = FractionCalculator.calculateAnswerD(eqnDetails, 20, randomizer);
            System.out.println(ans);
            assertEquals(ans, 20);
        }
    }
    @Test
    public void testDivisibleAnswerD() {
        for (int i = 0; i < 100; i++) {
            int ans = FractionCalculator.calculateAnswerD(eqnDetails, 4, randomizer);
            System.out.println(ans);
            assertTrue(ans<=20 && ans >=4);
            assertEquals(0, ans % 4);
        }
    }


    @Test(expected = IllegalArgumentException.class)
    public void testImpossibleAnswerD() {
        for (int i = 0; i < 100; i++) {
            FractionCalculator.calculateAnswerD(eqnDetails, 21, randomizer);
        }
    }

    @Test
    public void testCalculateOperand2D(){
        for (int i = 0; i < 100; i++) {
            WholeNum operand1 = new WholeNum(4);
            WholeNum ans = new WholeNum(20);
            WholeNum operand2 = new WholeNum (FractionCalculator.calculateOperand2D(eqnDetails, operand1.getValue(), ans.getValue(), randomizer));
            assertEquals(((WholeNum)operand2.lcm(operand1)).getValue(), ans.getValue());
            System.out.println(operand2);
        }
    }
}