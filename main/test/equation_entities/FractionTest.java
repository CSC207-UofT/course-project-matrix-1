package equation_entities;

import org.junit.Test;

import static org.junit.Assert.*;

public class FractionTest {

    @Test
    public void testLargeNumReduce(){
        Fraction f = new ImproperFraction(20,4);
        f.reduce();
        assertEquals(5,f.getFraction()[0]);
        assertEquals(1,f.getFraction()[1]);
    }

    @Test
    public void testLargeDenomReduce(){
        Fraction f = new ImproperFraction(4,20);
        f.reduce();
        assertEquals(1,f.getFraction()[0]);
        assertEquals(5,f.getFraction()[1]);
    }
    @Test
    public void testCannotReduce(){
        Fraction f = new ImproperFraction(31,20);
        f.reduce();
        assertEquals(31,f.getFraction()[0]);
        assertEquals(20,f.getFraction()[1]);
    }
    @Test
    public void testReduceByOtherNum(){
        Fraction f = new ImproperFraction(35,20);
        f.reduce();
        assertEquals(7,f.getFraction()[0]);
        assertEquals(4,f.getFraction()[1]);
    }
    @Test
    public void testComplexReduce(){
        Fraction f = new ImproperFraction(18135,2340);
        f.reduce();
        assertEquals(31,f.getFraction()[0]);
        assertEquals(4,f.getFraction()[1]);
    }
    @Test
    public void testAdd(){
        Fraction f = new ImproperFraction(702,117);
        Fraction f2 = new ImproperFraction(35,20);
        Fraction newFrac = (Fraction) f.add(f2);
        assertEquals(31,newFrac.getFraction()[0]);
        assertEquals(4,newFrac.getFraction()[1]);
    }
    @Test
    public void testSubtract(){
        Fraction f = new ImproperFraction(702,117);
        Fraction f2 = new ImproperFraction(35,20);
        Fraction newFrac = (Fraction) f.subtract(f2);
        assertEquals(17,newFrac.getFraction()[0]);
        assertEquals(4,newFrac.getFraction()[1]);
    }
    @Test
    public void testMultiply(){
        Fraction f = new ImproperFraction(702,117);
        Fraction f2 = new ImproperFraction(35,20);
        Fraction newFrac = (Fraction) f.multiply(f2);
        assertEquals(21,newFrac.getFraction()[0]);
        assertEquals(2,newFrac.getFraction()[1]);
    }
    @Test
    public void testDivide(){
        Fraction f = new ImproperFraction(702,117);
        Fraction f2 = new ImproperFraction(35,20);
        Fraction newFrac = (Fraction) f.divide(f2);
        assertEquals(24,newFrac.getFraction()[0]);
        assertEquals(7,newFrac.getFraction()[1]);
    }
}