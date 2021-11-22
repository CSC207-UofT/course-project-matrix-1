package equation_builders;

import equation_entities.Symbol;
import equation_entities.WholeNum;
import equation_parameters.FractionEquationDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class FractionDirectorTest {
    private FractionDirector director;
    private int randomSeed;
    private FractionEquationDetails fractionEquationDetails = new FractionEquationDetails(generateRange(5,10), 100, 1);

    @Before
    public void init() {
        director = new FractionDirector();
        randomSeed = new Random().nextInt(100000);
        fractionEquationDetails.setOperator('+');
        fractionEquationDetails.setNegAllowed(false);
    }

    @Test
    public void testAddPos() {
        director.setEquationBuilder('+');
        director.constructEquation(fractionEquationDetails, randomSeed + 5);
        System.out.println(director.getEquation().getEquation());
    }
    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}