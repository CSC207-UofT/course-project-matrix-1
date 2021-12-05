package equation_entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StandardEquationTest {
    @Test
    public void testSetEquation() {
        StandardEquation standardEquation = new StandardEquation();
        standardEquation.setOperator(new Add());
        standardEquation.setOperand1(new WholeNum(9));
        standardEquation.setOperand2(new WholeNum(-10));
        assertEquals(standardEquation.getEquationParts()[1].toString(), "+");
    }
}