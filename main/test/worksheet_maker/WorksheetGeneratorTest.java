package worksheet_maker;

import equation_parameters.WholeNumEquationDetails;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class WorksheetGeneratorTest {
    private final WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
    private WorksheetGenerator wg;
    private Worksheet ws;

    @Before
    public void init() {
        int randomSeed = new Random().nextInt(100000);

        ws = new Worksheet();
        wg = new WorksheetGenerator(ws, randomSeed);
        wholeNumEquationDetails.setNumOfEquations(5);
        wholeNumEquationDetails.setOperator("+");
        wholeNumEquationDetails.setOperandRange1(new int[]{1, 10});
        wholeNumEquationDetails.setOperandRange2(new int[]{1, 10});
        wholeNumEquationDetails.setNegAllowed(false);
    }

    @Test
    public void testWorksheetSize() {
        wg.populateWorksheet(wholeNumEquationDetails);
        assertEquals(ws.getEquations().size(), 5);
    }

    @Test
    public void testWorksheetHasQuestionAndAnswer() {
        wg.populateWorksheet(wholeNumEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            //Test if each equation has all the parts of an equation (should be 4)
            assertEquals(ws.getEquations().get(i).equationToHashMap().size(), 4);
        }
    }

}