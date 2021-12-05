package equation_entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class LCMTest {
    private final Value operand1 = new WholeNum(10);
    private final Value operand2 = new WholeNum(5);
    private final LCM operatorSymbol = new LCM();

    @Test
    public void testSolveBinaryExpression() {
        assertEquals(operatorSymbol.solveBinaryExpression(operand1, operand2).toString(), "10");
    }

    @Test
    public void testToString() {
        assertEquals(operatorSymbol.toString(), "LCM");
    }

}