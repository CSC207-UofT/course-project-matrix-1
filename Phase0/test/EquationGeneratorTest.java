import exceptions.InvalidInputException;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Will Jeong
 * @version 1.0
 * @since 2021-10-12
 */
public class EquationGeneratorTest {
    EquationGenerator eg = new EquationGenerator();

    @Test
    public void testCreateValidEquation() {
        String[] equation = eg.createEquation("standard add", 3);
        System.out.println(equation[0] + " = " + equation[1]);
        assertTrue(equation[0].length() < 10);
    }

    @Test
    public void testCreateInvalidEquation() {
        assertThrows(InvalidInputException.class,
                () -> eg.createEquation("standard add", 10)
        );
    }
}