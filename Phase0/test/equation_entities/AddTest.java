package equation_entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class AddTest {
    private Value operand1 = new WholeNum(10);
    private Value operand2 = new WholeNum(4);
    private String operator = "+";
    private Add operatorSymbol = new Add();
    @Test
    public void testSolveBinaryExpression(){
        assertEquals(operatorSymbol.solveBinaryExpression(operand1,operand2).toString(), "14");
    }
    @Test
    public void testToString(){
        assertEquals(operatorSymbol.toString(), "+");
    }
}