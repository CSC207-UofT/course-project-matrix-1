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
        assertEquals(bedmasEquation.getEquationParts()[1].toString(),"+");
    }

}