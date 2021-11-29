package equation_builders;

import equation_entities.Symbol;
import equation_entities.WholeNum;
import org.junit.Before;
import org.junit.Test;
import equation_parameters.WholeNumEquationDetails;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StandardDirectorWholeNumTest {
    private StandardEquationDirector director;
    private int seed;
    private final WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();

    @Before
    public void init() {
        seed = new Random().nextInt(100000);
        wholeNumEquationDetails.setOperandRange1(generateRange(1, 10));
        wholeNumEquationDetails.setOperandRange2(generateRange(1, 10));
        wholeNumEquationDetails.setNegAllowed(false);
    }

    @Test
    public void testAddPos() {
        wholeNumEquationDetails.setOperator("+");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, seed + 5);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testAddNeg() {
        for (int i=0;i<100;i++) {
            wholeNumEquationDetails.setOperator("+");
            director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
            wholeNumEquationDetails.setNegAllowed(true);
            director.constructEquation(wholeNumEquationDetails, seed + 10*i);
            Symbol[] equation = director.getEquation().getEquationParts();
            int operand1 = ((WholeNum) equation[0]).getValue();
            int operand2 = ((WholeNum) equation[2]).getValue();
            int answer = ((WholeNum) equation[3]).getValue();
            assertTrue(-10 <= operand1 && operand1 <= 10);
            assertTrue(-10 <= operand2 && operand2 <= 10);
            assertEquals(operand1 + operand2, answer);
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testSubPos() {
        wholeNumEquationDetails.setOperator("-");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, seed + 15);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(operand1 >= operand2);
        assertEquals(operand1 - operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testSubNeg() {
        wholeNumEquationDetails.setOperator("-");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, seed + 20);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 - operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testMultiplyPos() {
        wholeNumEquationDetails.setOperator("*");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, seed + 25);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testMultiplyNeg() {
        wholeNumEquationDetails.setOperator("*");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, seed + 30);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testDivPos() {
        wholeNumEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(false);
        director.constructEquation(wholeNumEquationDetails, seed + 35);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(operand1 / operand2, answer);
        assertEquals(0, operand1 % operand2);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test
    public void testDivNeg() {
        wholeNumEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setNegAllowed(true);
        director.constructEquation(wholeNumEquationDetails, seed + 40);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(0, operand1 % operand2);
        assertEquals(operand1 / operand2, answer);
        System.out.println(director.getEquation().equationToHashMap());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndivisibleDiv() {
        wholeNumEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,7));
        director.constructEquation(wholeNumEquationDetails, seed + 45);
    }

    @Test
    public void testOneAnswerDiv() {
        wholeNumEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setOperandRange1(generateRange(10,10));
        wholeNumEquationDetails.setOperandRange2(generateRange(5,6));
        director.constructEquation(wholeNumEquationDetails, seed + 50);
        Symbol[] equation = director.getEquation().getEquationParts();
        int operand2 = ((WholeNum) equation[2]).getValue();
        assertEquals(5, operand2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        wholeNumEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Whole Number", wholeNumEquationDetails);
        wholeNumEquationDetails.setOperandRange1(generateRange(0,5));
        wholeNumEquationDetails.setOperandRange2(generateRange(6,10));
        director.constructEquation(wholeNumEquationDetails, seed + 55);
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}