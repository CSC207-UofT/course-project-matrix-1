package equation_entities;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MixedFractionTest {
    private Fraction mixedFraction;

    @Test
    public void testPositiveToString() {
        mixedFraction = new MixedFraction(20, 8);
        mixedFraction.reduce();
        assertEquals("2\\frac{1}{2}", mixedFraction.toString());
    }

    @Test
    public void testNegativeToString() {
        mixedFraction = new MixedFraction(-20, 8);
        mixedFraction.reduce();
        assertEquals("-2\\frac{1}{2}", mixedFraction.toString());
    }
}