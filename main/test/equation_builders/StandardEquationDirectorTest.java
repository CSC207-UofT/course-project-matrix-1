package equation_builders;

import equation_entities.Fraction;
import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import equation_parameters.WholeNumEquationDetails;
import org.junit.Test;
import utilities.Randomizer;

import java.util.Arrays;
import java.util.Map;
import java.util.Random;

import static constants.EquationParts.*;
import static constants.EquationType.FRACTION;
import static constants.EquationType.WHOLE_NUMBER;
import static constants.FractionFormats.IMPROPER;
import static constants.FractionFormats.MIXED;
import static constants.OperatorRep.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StandardEquationDirectorTest {
    private StandardEquationDirector director;
    private EquationDetails eqnDetails;
    private Randomizer randomizer = new Randomizer(100000);

    // Instantiate equation details
    public void initializeFractionAddSub() {
        eqnDetails = new FractionAddSubEquationDetails();
        eqnDetails.setNumOfEquations(20);
    }

    public void initializeFractionMultiDiv() {
        eqnDetails = new FractionMultiDivEquationDetails();
        eqnDetails.setNumOfEquations(20);
    }

    public void initializeWholeNum() {
        eqnDetails = new WholeNumEquationDetails();
        eqnDetails.setNumOfEquations(20);
    }

    // Populate equation details
    private void setupBasicWholeNum(String operator, boolean isNegative) {
        initializeWholeNum();
        eqnDetails.setOperator(operator);
        eqnDetails.setNegAllowed(isNegative);
        ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(1, 40));
        ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(1, 40));
        director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
        director.constructEquation();
    }

    //Whole number tests.
    @Test
    public void testAddPos() {
        setupBasicWholeNum(ADD, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 80) && (Integer.parseInt(eqn.get(ANSWER)) >= 1));
    }

    @Test
    public void testAddNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(ADD, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 80) && (Integer.parseInt(eqn.get(ANSWER)) >= -80));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testSubPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(SUB, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            System.out.println(director.getEquation().equationToHashMap());
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= Integer.parseInt(eqn.get(OPERAND1))) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 40) && (Integer.parseInt(eqn.get(ANSWER)) >= 0));
        }
    }

    @Test
    public void testSubNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(SUB, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 80) && (Integer.parseInt(eqn.get(ANSWER)) >= -80));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testMultPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(MULT, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            System.out.println(director.getEquation().equationToHashMap());
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 1600) && (Integer.parseInt(eqn.get(ANSWER)) >= 1));
        }
    }

    @Test
    public void testMultNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(MULT, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 1600) && (Integer.parseInt(eqn.get(ANSWER)) >= -1600));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }


    @Test
    public void testExpPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(EXP, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testExpNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(EXP, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }


    @Test
    public void testDivPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(DIV, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
            assertEquals(0, Integer.parseInt(eqn.get(OPERAND1)) % Integer.parseInt(eqn.get(OPERAND2)));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testDivNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(DIV, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            assertEquals(0, Integer.parseInt(eqn.get(OPERAND1)) % Integer.parseInt(eqn.get(OPERAND2)));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndivisibleDiv() {
        for (int i = 0; i < 100; i++) {
            initializeWholeNum();
            eqnDetails.setOperator(DIV);
            eqnDetails.setNegAllowed(false);
            ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(10, 10));
            ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(6, 7));
            director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
            director.constructEquation();
        }
    }

    @Test
    public void testOneAnswerDiv() {
        for (int i = 0; i < 100; i++) {
            initializeWholeNum();
            eqnDetails.setOperator(DIV);
            eqnDetails.setNegAllowed(false);
            ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(10, 10));
            ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(5, 6));
            director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
            director.constructEquation();
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertEquals(5, Integer.parseInt(eqn.get(OPERAND2)));
            assertEquals(2, Integer.parseInt(eqn.get(ANSWER)));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        for (int i = 0; i < 100; i++) {
            initializeWholeNum();
            eqnDetails.setOperator(DIV);
            eqnDetails.setNegAllowed(false);
            ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(0, 5));
            ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(6, 10));
            director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
            director.constructEquation();
        }
    }

    @Test
    public void testLCMPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(LCM, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 0));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 0));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testLCMNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(LCM, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testGCDPos() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(GCD, false);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 0));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 0));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    @Test
    public void testGCDNeg() {
        for (int i = 0; i < 100; i++) {
            setupBasicWholeNum(GCD, true);
            Map<String, String> eqn = director.getEquation().equationToHashMap();
            assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
            assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
            System.out.println(director.getEquation().equationToHashMap());
        }
    }

    // Populate equation details
    private void setupBasicFracAddSub(String operator, boolean isNegative) {
        initializeFractionAddSub();
        eqnDetails.setOperator(operator);
        eqnDetails.setNegAllowed(isNegative);
        ((FractionAddSubEquationDetails) eqnDetails).setMaxOperandValue(2);
        ((FractionAddSubEquationDetails) eqnDetails).setMaxOperand2AndAnswerDenom(20);
        ((FractionAddSubEquationDetails) eqnDetails).setOperand1DenomRange(generateRange(1, 20));
        ((FractionAddSubEquationDetails) eqnDetails).setFractionFormat(IMPROPER);
        director = new StandardEquationDirector(randomizer, eqnDetails, FRACTION);
        director.constructEquation();
    }

    // Populate equation details
    private void setupBasicFracMultDiv(String operator, boolean isNegative) {
        initializeFractionMultiDiv();
        eqnDetails.setOperator(operator);
        eqnDetails.setNegAllowed(isNegative);
        ((FractionMultiDivEquationDetails) eqnDetails).setComplexity(1);
        ((FractionMultiDivEquationDetails) eqnDetails).setAnsDenominatorRange(generateRange(5,20));
        ((FractionMultiDivEquationDetails) eqnDetails).setMaxAnsValue(2);
        ((FractionMultiDivEquationDetails) eqnDetails).setFractionFormat(IMPROPER);
        director = new StandardEquationDirector(randomizer, eqnDetails, FRACTION);
        director.constructEquation();
    }
    //Fraction tests
    @Test
    public void testFracAdd() {
        for (int i = 0; i < 100; i++) {
            setupBasicFracAddSub(ADD, false);
            int[] operand1 = ((Fraction) director.getEquation().getEquationParts()[0]).getFraction();
            int[] operand2 = ((Fraction) director.getEquation().getEquationParts()[2]).getFraction();
            assertTrue(operand1[0] / operand1[1] <= 2);
            assertTrue(operand2[0] / operand2[1] <= 2);
            assertTrue(operand1[1] <= 20 && operand1[1] >= 1);
            assertTrue(operand2[1] <= 20 && operand2[1] >= 1);
            System.out.println(Arrays.toString(director.getEquation().getEquationParts()));
        }
    }
    @Test
    public void testFracSub() {
        for (int i = 0; i < 100; i++) {
            setupBasicFracAddSub(SUB, true);
            int[] operand1 = ((Fraction) director.getEquation().getEquationParts()[0]).getFraction();
            int[] operand2 = ((Fraction) director.getEquation().getEquationParts()[2]).getFraction();
            assertTrue(Math.abs(operand1[0] / operand1[1]) <= 2);
            assertTrue(Math.abs(operand2[0] / operand2[1]) <= 2);
            assertTrue(operand1[1] <= 20 && operand1[1] >= 1);
            assertTrue(operand2[1] <= 20 && operand2[1] >= 1);
        }
    }
    @Test
    public void testFracMult() {
        for (int i = 0; i < 100; i++) {
            setupBasicFracMultDiv(MULT, false);
            int[] answer = ((Fraction) director.getEquation().getEquationParts()[3]).getFraction();
            assertTrue(answer[0] / answer[1] <= 2);
            System.out.println(Arrays.toString(director.getEquation().getEquationParts()));
        }
    }
    @Test
    public void testFracDiv() {
        for (int i = 0; i < 100; i++) {
            setupBasicFracMultDiv(DIV, true);
            int[] answer = ((Fraction) director.getEquation().getEquationParts()[3]).getFraction();
            assertTrue(Math.abs(answer[0] / answer[1]) <= 2);
            System.out.println(Arrays.toString(director.getEquation().getEquationParts()));
        }
    }
    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}