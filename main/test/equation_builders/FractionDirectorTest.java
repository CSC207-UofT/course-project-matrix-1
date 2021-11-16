package equation_builders;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class FractionDirectorTest {


    @Before
    public void init() {

    }

    @Test
    public void testFindFactors() {
        assertEquals(Arrays.toString(FractionDirector.findFactors(105).toArray()), "[1, 3, 35, 21, 5, 7, 105, 15]");
    }

}