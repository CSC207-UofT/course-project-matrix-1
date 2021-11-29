package equation_builders;

import static constants.EquationType.*;
import static constants.OperatorRep.*;
import static constants.EquationParts.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import equation_parameters.EquationDetails;
import equation_parameters.FractionAddSubEquationDetails;
import equation_parameters.FractionMultiDivEquationDetails;
import org.junit.Test;
import equation_parameters.WholeNumEquationDetails;
import utilities.Randomizer;

import java.util.Map;

public class StandardEquationDirectorTest {
    private StandardEquationDirector director;
    private EquationDetails eqnDetails;
    private final Randomizer randomizer = new Randomizer(1000);

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
        setupBasicWholeNum(ADD, true);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 80) && (Integer.parseInt(eqn.get(ANSWER)) >= -80));
    }

    @Test
    public void testSubPos() {
        setupBasicWholeNum(SUB, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= Integer.parseInt(eqn.get(OPERAND1))) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 40) && (Integer.parseInt(eqn.get(ANSWER)) >= 1));
    }

    @Test
    public void testSubNeg() {
        setupBasicWholeNum(SUB, true);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 80) && (Integer.parseInt(eqn.get(ANSWER)) >= -80));
    }

    @Test
    public void testMultPos() {
        setupBasicWholeNum(MULT, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 1600) && (Integer.parseInt(eqn.get(ANSWER)) >= 1));
    }

    @Test
    public void testMultNeg() {
        setupBasicWholeNum(MULT, true);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(ANSWER)) <= 1600) && (Integer.parseInt(eqn.get(ANSWER)) >= -1600));
    }


    @Test
    public void testExpPos() {
        setupBasicWholeNum(EXP, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
    }

    @Test
    public void testExpNeg() {
        setupBasicWholeNum(EXP, true);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
    }


    @Test
    public void testDivPos() {
        setupBasicWholeNum(DIV, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= 1));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= 1));
        assertEquals(0, Integer.parseInt(eqn.get(OPERAND1)) % Integer.parseInt(eqn.get(OPERAND2)));
    }

    @Test
    public void testDivNeg() {
        setupBasicWholeNum(DIV, false);
        Map<String, String> eqn = director.getEquation().equationToHashMap();
        assertTrue((Integer.parseInt(eqn.get(OPERAND1)) <= 40) && (Integer.parseInt(eqn.get(OPERAND1)) >= -40));
        assertTrue((Integer.parseInt(eqn.get(OPERAND2)) <= 40) && (Integer.parseInt(eqn.get(OPERAND2)) >= -40));
        assertEquals(0, Integer.parseInt(eqn.get(OPERAND1)) % Integer.parseInt(eqn.get(OPERAND2)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIndivisibleDiv() {
        initializeWholeNum();
        eqnDetails.setOperator(DIV);
        eqnDetails.setNegAllowed(false);
        ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(10, 10));
        ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(6, 7));
        director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
        director.constructEquation();
    }

    @Test
    public void testOneAnswerDiv() {
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

    @Test(expected = IllegalArgumentException.class)
    public void testBadRangeDiv() {
        initializeWholeNum();
        eqnDetails.setOperator(DIV);
        eqnDetails.setNegAllowed(false);
        ((WholeNumEquationDetails) eqnDetails).setOperandRange1(generateRange(0, 5));
        ((WholeNumEquationDetails) eqnDetails).setOperandRange2(generateRange(6, 10));
        director = new StandardEquationDirector(randomizer, eqnDetails, WHOLE_NUMBER);
        director.constructEquation();
    }
    //TODO: LCM, GCD tests

    //Fraction tests


    public int[] generateRange(int min, int max) {
        return new int[]{min, max};
    }
}