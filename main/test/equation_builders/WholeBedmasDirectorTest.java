package equation_builders;

import equation_entities.Symbol;
import equation_entities.WholeNum;
import org.junit.Before;
import org.junit.Test;
import equation_parameters.WholeNumEquationDetails;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WholeBedmasDirectorTest {
    private WholeNumDirector bed;
    private int randomSeed;
    private WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();

    @Before
    public void init() {
        bed = new WholeNumDirector();
        randomSeed = new Random().nextInt(100000);
        wholeNumEquationDetails.setOperandRange1(generateRange(1, 10));
        wholeNumEquationDetails.setOperandRange2(generateRange(1, 10));
        wholeNumEquationDetails.setNegAllowed(false);
    }

    @Test
    public void testAddPos() {
        bed.setEquationBuilder('+');
        wholeNumEquationDetails.setNegAllowed(false);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 5);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testAddNeg() {
        bed.setEquationBuilder('+');
        wholeNumEquationDetails.setNegAllowed(true);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 10);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testSubPos() {
        bed.setEquationBuilder('-');
        wholeNumEquationDetails.setNegAllowed(false);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 15);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(operand1 >= operand2);
        assertEquals(operand1 - operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testSubNeg() {
        bed.setEquationBuilder('-');
        wholeNumEquationDetails.setNegAllowed(true);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 20);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 - operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testMultPos() {
        bed.setEquationBuilder('*');
        wholeNumEquationDetails.setNegAllowed(false);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 25);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testMultNeg() {
        bed.setEquationBuilder('*');
        wholeNumEquationDetails.setNegAllowed(true);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 30);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testDivPos() {
        bed.setEquationBuilder('/');
        wholeNumEquationDetails.setNegAllowed(false);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 35);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(operand1 / operand2, answer);
        assertEquals(0, operand1 % operand2);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test
    public void testDivNeg() {
        bed.setEquationBuilder('/');
        wholeNumEquationDetails.setNegAllowed(true);
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 40);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(0, operand1 % operand2);
        assertEquals(operand1 / operand2, answer);
        System.out.println(bed.getEquation().getEquation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUndivisibleDiv() {
        bed.setEquationBuilder('/');
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,7));
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 45);
    }

    @Test
    public void testOneAnswerDiv() {
        bed.setEquationBuilder('/');
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(5,6));
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 50);
        Symbol[] equation = bed.getEquation().getEquationParts();
        int operand2 = ((WholeNum) equation[2]).getValue();
        assertEquals(5, operand2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        bed.setEquationBuilder('/');
        wholeNumEquationDetails.setOperandRange1(generateRange(0,5));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,10));
        bed.constructEquation(wholeNumEquationDetails, randomSeed + 55);
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}