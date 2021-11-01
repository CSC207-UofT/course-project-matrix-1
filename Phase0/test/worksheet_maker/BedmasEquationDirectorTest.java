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
    private final WholeBedmasDirector bed = new WholeBedmasDirector();

    @Test
    public void testAddPosAns() {
        bed.setBedmasEquationBuilder('+');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= 0 && answer <= 20);
    }

    @Test
    public void testAddNegAns() {
        bed.setBedmasEquationBuilder('+');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= -20 && answer <= 20);
    }

    @Test
    public void testSubPosAns() {
        bed.setBedmasEquationBuilder('-');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= 0 && answer <= 10);
    }

    @Test
    public void testSubNegAns() {
        bed.setBedmasEquationBuilder('-');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(0, 10), true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
        int answer = Integer.parseInt(equation[1]);
        assertTrue(answer >= -20 && answer <= 20);
    }

    @Test
    public void testDivNegAns() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(1, 10), true);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }

    @Test
    public void testDivPosAns() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 10), generateRange(1, 10), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }

    @Test
    public void testOperand1AlwaysSmallerThanOperand2() {
        try {
            bed.setBedmasEquationBuilder('/');
            bed.constructBedmasEquation(generateRange(0, 5), generateRange(10, 20), false);
            String[] equation = bed.getBedmasEquation().getEquation();
            System.out.println(Arrays.toString(equation));
        } catch (IllegalArgumentException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }

    @Test
    public void testImpossibleDivision() {
        try {
            bed.setBedmasEquationBuilder('/');
            bed.constructBedmasEquation(generateRange(19, 20), generateRange(6, 7), false);
            String[] equation = bed.getBedmasEquation().getEquation();
            System.out.println(Arrays.toString(equation));
        } catch (IllegalArgumentException e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    @Test
    public void testSmallOperandRangeDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(0, 2), generateRange(1, 10), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }

    @Test
    public void testGreaterOperandRangeTwoDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(1, 10), generateRange(5, 20), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }

    @Test
    public void testMuchGreaterOperandRangeOneDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(10, 20), generateRange(5, 7), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }



    @Test
    public void testOnePossibilityDiv() {
        bed.setBedmasEquationBuilder('/');
        bed.constructBedmasEquation(generateRange(19, 21), generateRange(6, 7), false);
        String[] equation = bed.getBedmasEquation().getEquation();
        System.out.println(Arrays.toString(equation));
    }
    @Test
    public void testStandardDiv() {
        System.out.println("================");
        for (int i=0;i<100;i++) {
            bed.setBedmasEquationBuilder('/');
            bed.constructBedmasEquation(generateRange(0, 100), generateRange(1, 10), false);
            String[] equation = bed.getBedmasEquation().getEquation();
            System.out.println(Arrays.toString(equation));
        }
        System.out.println("================");
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}