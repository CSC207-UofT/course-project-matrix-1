package equation_builders;

import equation_entities.Symbol;
import equation_entities.WholeNum;
import equation_parameters.WholeNumEquationDetails;
import org.junit.Before;
import org.junit.Test;
import utilities.EuclideanAlgorithm;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BedmasDirectorWholeNumTest {
    private BedmasEquationDirector director;
    private final WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();

    @Before
    public void init() {
        director = new BedmasEquationDirector("Whole Number");
        wholeNumEquationDetails.setOperandRange1(generateRange(1, 10));
        wholeNumEquationDetails.setOperandRange2(generateRange(1, 10));
        wholeNumEquationDetails.setNegAllowed(false);
    }

    @Test
    public void testAddPos() {
        director.setEquationBuilder("+");
        wholeNumEquationDetails.setOperator("+");
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testAddNeg() {
        director.setEquationBuilder("+");
        wholeNumEquationDetails.setOperator("+");
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testSubPos() {
        director.setEquationBuilder("-");
        wholeNumEquationDetails.setOperator("-");
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(operand1 >= operand2);
        assertEquals(operand1 - operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testSubNeg() {
        director.setEquationBuilder("-");
        wholeNumEquationDetails.setOperator("-");
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 - operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testMultiplyPos() {
        director.setEquationBuilder("*");
        wholeNumEquationDetails.setOperator("*");
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testMultiplyNeg() {
        director.setEquationBuilder("*");
        wholeNumEquationDetails.setOperator("*");
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testDivPos() {
        director.setEquationBuilder("/");
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(operand1 / operand2, answer);
        assertEquals(0, operand1 % operand2);
        System.out.println(director.getEquation().getEquation());
    }

    @Test
    public void testDivNeg() {
        director.setEquationBuilder("/");
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(0, operand1 % operand2);
        assertEquals(operand1 / operand2, answer);
        System.out.println(director.getEquation().getEquation());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndivisibleDiv() {
        director.setEquationBuilder("/");
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,7));
        director.constructEquation(wholeNumEquationDetails, generateSeed());
    }

    @Test
    public void testOneAnswerDiv() {
        director.setEquationBuilder("/");
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(5,6));
        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand2 = ((WholeNum) equation[2]).getValue();
        assertEquals(5, operand2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        director.setEquationBuilder("/");
        wholeNumEquationDetails.setOperator("/");
        wholeNumEquationDetails.setOperandRange1(generateRange(0,5));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,10));
        director.constructEquation(wholeNumEquationDetails, generateSeed());
    }

    @Test
    public void testLCMPositives() {
        director.setEquationBuilder("LCM");
        wholeNumEquationDetails.setOperator("LCM");
        wholeNumEquationDetails.setNegAllowed(false);
        wholeNumEquationDetails.setOperandRange1(generateRange(0,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(0,10));

        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();

        int gcd = EuclideanAlgorithm.findGreatestCommonDivisor(operand1, operand2);
        int lcm_expected = operand1 * operand2 / gcd;

        assertEquals(lcm_expected, answer);
    }

    @Test
    public void testGCDPositives() {
        director.setEquationBuilder("GCD");
        wholeNumEquationDetails.setOperator("GCD");
        wholeNumEquationDetails.setNegAllowed(false);
        wholeNumEquationDetails.setOperandRange1(generateRange(0,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(0,10));

        director.constructEquation(wholeNumEquationDetails, generateSeed());
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();

        int gcd_expected = EuclideanAlgorithm.findGreatestCommonDivisor(operand1, operand2);

        assertEquals(gcd_expected, answer);
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }

    public int generateSeed(){
        return new Random().nextInt(100000);
    }
}