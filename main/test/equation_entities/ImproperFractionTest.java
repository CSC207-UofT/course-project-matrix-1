package equation_entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ImproperFractionTest {
    private Fraction improperFraction;

    @Test
    public void testPositiveToString() {
        improperFraction = new ImproperFraction(20, 8);
        improperFraction.reduce();
        assertEquals("\\frac{5}{2}", improperFraction.toString());
    }

    @Test
    public void testNegativeToString() {
        improperFraction = new ImproperFraction(-20, 8);
        improperFraction.reduce();
        assertEquals("-\\frac{5}{2}", improperFraction.toString());
    }
}