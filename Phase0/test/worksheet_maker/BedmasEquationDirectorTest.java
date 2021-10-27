package worksheet_maker;

import equation_builders.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class BedmasEquationDirectorTest {
    private final BedmasEquationDirector bed = new BedmasEquationDirector();
    private final int[] zeroToTen = new int[]{0, 10};

    @Test
    public void testAddPosAns() {
        BedmasEquationBuilder addBeb = new AddBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(addBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= 0 && answer <= 20);
    }

    @Test
    public void testAddNegAns() {
        BedmasEquationBuilder addBeb = new AddBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(addBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= -20 && answer <= 20);
    }

    @Test
    public void testSubPosAns() {
        BedmasEquationBuilder subBeb = new SubBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(subBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= 0 && answer <= 10);
    }

    @Test
    public void testSubNegAns() {
        BedmasEquationBuilder subBeb = new SubBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(subBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= -20 && answer <= 20);
    }

    @Test
    public void testDivNegAns() {
        BedmasEquationBuilder divBeb = new DivideBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(divBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int[] equationParts = bed.getBedmasEquation().getEquationParts();
        int answer = equationParts[2];
        int operand1 = equationParts[0];
        int operand2 = equationParts[1];
        assertEquals(operand1 / operand2, answer);
        assertEquals(operand1 % operand2, 0);
    }

    @Test
    public void testDivPosAns() {
        BedmasEquationBuilder divBeb = new DivideBedmasEquationBuilder();
        bed.setBedmasEquationBuilder(divBeb);
        bed.constructBedmasEquation(zeroToTen, zeroToTen, false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int[] equationParts = bed.getBedmasEquation().getEquationParts();
        int answer = equationParts[2];
        int operand1 = equationParts[0];
        int operand2 = equationParts[1];
        assertEquals(operand1 / operand2, answer);
        assertTrue(answer >= 0 && answer <= 10);
    }
}