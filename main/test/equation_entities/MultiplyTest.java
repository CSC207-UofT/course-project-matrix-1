package equation_entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MultiplyTest {
    private final Value operand1 = new WholeNum(10);
    private final Value operand2 = new WholeNum(5);
    private final Multiply operatorSymbol = new Multiply();

    @Test
    public void testSolveBinaryExpression() {
        assertEquals(operatorSymbol.solveBinaryExpression(operand1, operand2).toString(), "50");
    }

    @Test
    public void testToString() {
        assertEquals(operatorSymbol.toString(), "\\times");
    }
}