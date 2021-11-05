package equation_entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class BedmasEquationTest {

    @Test
    public void testSetEquation() {
        BedmasEquation bedmasEquation = new BedmasEquation();
        bedmasEquation.setOperator(new Add());
        bedmasEquation.setOperand1(new WholeNum(9));
        bedmasEquation.setOperand2(new WholeNum(-10));
        assertTrue(bedmasEquation.getEquation().get(0).indexOf('+') != -1);
        assertEquals(-1, bedmasEquation.getEquation().get(1).indexOf('+'));
    }

}