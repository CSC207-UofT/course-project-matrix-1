package worksheet_maker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class WorksheetGeneratorTest {
    WorksheetGenerator wg;
    Worksheet ws;
    HashMap<String, Object> myEquationDetails = new HashMap<>();

    @Before
    public void init() {
        int randomSeed = new Random().nextInt(100000);

        ws = new Worksheet();
        wg = new WorksheetGenerator(ws, randomSeed);
        myEquationDetails.put("numOfEquations", 5);
        myEquationDetails.put("operator", '+');
        myEquationDetails.put("operandRange1", new int[]{1, 10});
        myEquationDetails.put("operandRange2", new int[]{1, 10});
        myEquationDetails.put("negAllowed", true);
    }

    @Test
    public void testWorksheetSize() {
        wg.populateWorksheet(myEquationDetails);
        assertEquals(ws.getEquations().size(), 5);
    }

    @Test
    public void testWorksheetHasQuestionAndAnswer() {
        wg.populateWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            //Test if each equation has a question and an answer (greater than length of 2 list)
            assertTrue(ws.getEquations().get(i).getEquation().size()>2);
        }
    }

}