package equation_builders;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class EquationDirectorTest {


    @Before
    public void init() {

    }

    @Test
    public void testFindFactors() {
        assertEquals("[1, 3, 35, 21, 5, 7, 105, 15]", Arrays.toString(EquationDirector.findFactors(105).toArray()));
        //Second time should be identical
        assertEquals("[1, 3, 35, 21, 5, 7, 105, 15]", Arrays.toString(EquationDirector.findFactors(105).toArray()));
    }

    @Test
    public void testFindMultipleFactor() {
        assertEquals("[128, 64, 32, 16, 1, 2, 4, 8]", Arrays.toString(EquationDirector.findFactors(128).toArray()));
    }
}