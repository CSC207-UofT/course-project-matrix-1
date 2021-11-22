package utilities;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FactorFinderTest {
    @Test
    public void testFindFactors() {
        assertEquals("[1, 3, 35, 21, 5, 7, 105, 15]", Arrays.toString(FactorFinder.findFactors(105).toArray()));
        //Second time should be identical
        assertEquals("[1, 3, 35, 21, 5, 7, 105, 15]", Arrays.toString(FactorFinder.findFactors(105).toArray()));
    }

    @Test
    public void testFindMultipleFactor() {
        assertEquals("[128, 64, 32, 16, 1, 2, 4, 8]", Arrays.toString(FactorFinder.findFactors(128).toArray()));
    }

    @Test
    public void testFindPrimeFactors() {
        assertEquals("[2, 2, 2, 2, 2, 2]", Arrays.toString(FactorFinder.primeFactorize(64).toArray()));
    }
}