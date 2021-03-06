package equation_entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GCDTest {
    private final Value operand1 = new WholeNum(10);
    private final Value operand2 = new WholeNum(5);
    private final GCD operatorSymbol = new GCD();

    @Test
    public void testSolveBinaryExpression() {
        assertEquals(operatorSymbol.solveBinaryExpression(operand1, operand2).toString(), "5");
    }

    @Test
    public void testToString() {
        assertEquals(operatorSymbol.toString(), "GCD");
    }
}