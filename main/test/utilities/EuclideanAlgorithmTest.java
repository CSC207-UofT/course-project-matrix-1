package utilities;

import org.junit.Test;

import static org.junit.Assert.*;

public class EuclideanAlgorithmTest {
    @Test
    public void testPrimeFindGreatestCommonDivisor(){
        assertEquals(EuclideanAlgorithm.findGreatestCommonDivisor(3,4), 1);
    }
    @Test
    public void testOperandIsGreatestCommonDivisor(){
        assertEquals(EuclideanAlgorithm.findGreatestCommonDivisor(2,4), 2);
    }
    @Test
    public void testComplexGreatestCommonDivisor(){
        assertEquals(EuclideanAlgorithm.findGreatestCommonDivisor(18,8), 2);
    }
}