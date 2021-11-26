package equation_builders;

import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class StandardEquationDirectorFractionTest {
    private StandardEquationDirector director;
    private int randomSeed;
    private final FractionAddSubEquationDetails fractionAddSubEquationDetails = new FractionAddSubEquationDetails();
    private final FractionMultiDivEquationDetails fractionMultiDivEquationDetails = new FractionMultiDivEquationDetails();
    private final int maxDenominator = 100;

    @Before
    public void init() {

        fractionAddSubEquationDetails.setMaxOperandValue(1);
        fractionAddSubEquationDetails.setOperand1DenomRange(generateRange(1, 50));
        fractionAddSubEquationDetails.setNegAllowed(true);
        fractionAddSubEquationDetails.setMaxOperand2AndAnswerDenom(maxDenominator);
        fractionAddSubEquationDetails.setNegAllowed(true);

        fractionMultiDivEquationDetails.setMaxAnsValue(1);
        fractionMultiDivEquationDetails.setComplexity(1);
        fractionMultiDivEquationDetails.setAnsDenominatorRange(generateRange(20, 40));
        fractionMultiDivEquationDetails.setNegAllowed(true);
    }

    @Test
    public void testAddPos() {
        fractionAddSubEquationDetails.setOperator("+");
        director = new StandardEquationDirector("Fraction", fractionAddSubEquationDetails);
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.constructEquation(fractionAddSubEquationDetails, randomSeed + 5);
            System.out.println(director.getEquation().equationToHashMap());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
            assertTrue(Math.abs(Integer.parseInt(firstOperand.substring(firstOperand.indexOf("/") + 1))) <= Math.abs(maxDenominator));
        }
    }

    @Test
    public void testNegPos() {
        fractionAddSubEquationDetails.setOperator("-");
        director = new StandardEquationDirector("Fraction", fractionAddSubEquationDetails);
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.constructEquation(fractionAddSubEquationDetails, randomSeed + 5);
            System.out.println(director.getEquation().equationToHashMap());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
            assertTrue(Math.abs(Integer.parseInt(firstOperand.substring(firstOperand.indexOf("/") + 1))) <= Math.abs(maxDenominator));
        }
    }
    @Test
    public void testMultiplyPos() {
        fractionMultiDivEquationDetails.setOperator("*");
        director = new StandardEquationDirector("Fraction", fractionMultiDivEquationDetails);
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.constructEquation(fractionMultiDivEquationDetails, randomSeed);
            System.out.println(director.getEquation().equationToHashMap());
        }
    }
    @Test
    public void testDivPos() {
        fractionMultiDivEquationDetails.setOperator("/");
        director = new StandardEquationDirector("Fraction", fractionMultiDivEquationDetails);
        for (int i = 0; i < 100; i++) {
            randomSeed = new Random().nextInt(100000);
            director.constructEquation(fractionMultiDivEquationDetails, randomSeed);
            System.out.println(director.getEquation().equationToHashMap());
            String firstOperand = director.getEquation().getEquationParts()[0].toString();
        }
    }
    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}