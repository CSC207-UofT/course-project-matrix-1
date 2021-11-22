package equation_builders;

import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class FractionDirectorTest {
    private FractionDirector director;
    private int randomSeed;
    private FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
    private FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
    private int maxDenominator = 100;

    @Before
    public void init() {
        director = new FractionDirector();

        fractionAddSubEquationDetails.setMaxOperandValue(1);
        fractionAddSubEquationDetails.setOperand1DenomRange(generateRange(1, 50));
        fractionAddSubEquationDetails.setNegAllowed(true);
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(maxDenominator);

        fractionAddSubEquationDetails.setOperator('+');
        fractionAddSubEquationDetails.setNegAllowed(true);

        fractionMultiDivEquationDetails.setMaxAnsValue(1);
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setAnsDenominatorRange(generateRange(20, 40));
        fractionMultiDivEquationDetails.setOperator('*');
        fractionMultiDivEquationDetails.setNegAllowed(true);
    }

    @Test
    public void testAddPos() {
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.setEquationBuilder('+');
            director.constructEquation(fractionAddSubEquationDetails, randomSeed + 5);
            System.out.println(director.getEquation().getEquation());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
            assertTrue(Math.abs(Integer.parseInt(firstOperand.substring(firstOperand.indexOf('/') + 1))) <= Math.abs(maxDenominator));
        }
    }

    @Test
    public void testNegPos() {
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.setEquationBuilder('-');
            director.constructEquation(fractionAddSubEquationDetails, randomSeed + 5);
            System.out.println(director.getEquation().getEquation());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
            assertTrue(Math.abs(Integer.parseInt(firstOperand.substring(firstOperand.indexOf('/') + 1))) <= Math.abs(maxDenominator));
        }
    }
    @Test
    public void testMultPos() {
        for (int i = 0; i < 10; i++) {
            randomSeed = new Random().nextInt(100000);
            director.setEquationBuilder('*');
            director.constructEquation(fractionMultiDivEquationDetails, 10);
            System.out.println(director.getEquation().getEquation());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
//            assertTrue(Math.abs(Integer.parseInt(firstOperand.substring(firstOperand.indexOf('/') + 1))) <= Math.abs(maxDenominator));
        }
    }

    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}