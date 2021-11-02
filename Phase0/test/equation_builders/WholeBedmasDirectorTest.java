package equation_builders;

import equation_entities.Symbol;
import equation_entities.WholeNum;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class WholeBedmasDirectorTest {
    private WholeBedmasDirector bed;

    @Before
    public void init() {
        bed = new WholeBedmasDirector();
    }

    @Test
    public void testAddPos() {
        bed.setBedmasEquationBuilder('+');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), false);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testAddNeg() {
        bed.setBedmasEquationBuilder('+');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), true);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 + operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testSubPos() {
        bed.setBedmasEquationBuilder('-');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), false);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(operand1 >= operand2);
        assertEquals(operand1 - operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testSubNeg() {
        bed.setBedmasEquationBuilder('-');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), true);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 - operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testMultPos() {
        bed.setBedmasEquationBuilder('*');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), false);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(0 <= operand1 && operand1 <= 10);
        assertTrue(0 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testMultNeg() {
        bed.setBedmasEquationBuilder('*');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), true);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertTrue(-10 <= operand1 && operand1 <= 10);
        assertTrue(-10 <= operand2 && operand2 <= 10);
        assertEquals(operand1 * operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testDivPos() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(1, 10), false);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(operand1 / operand2, answer);
        assertEquals(0, operand1 % operand2);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test
    public void testDivNeg() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(1, 10), true);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand1 = ((WholeNum) equation[0]).getValue();
        int operand2 = ((WholeNum) equation[2]).getValue();
        int answer = ((WholeNum) equation[3]).getValue();
        assertEquals(0, operand1 % operand2);
        assertEquals(operand1 / operand2, answer);
        System.out.println(bed.getBedmasEquation().getEquation()[0] + "=" + bed.getBedmasEquation().getEquation()[1]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUndivisibleDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(10, 10), generateRange(6, 7), false);
    }

    @Test
    public void testOneAnswerDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(10, 10), generateRange(5, 6), false);
        Symbol[] equation = bed.getBedmasEquation().getEquationParts();
        int operand2 = ((WholeNum) equation[2]).getValue();
        assertEquals(5, operand2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 5), generateRange(6, 10), false);
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}