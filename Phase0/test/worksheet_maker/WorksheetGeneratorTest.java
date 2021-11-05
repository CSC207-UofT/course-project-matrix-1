package worksheet_maker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotEquals;

public class WorksheetGeneratorTest {
    WorksheetGenerator wg;
    Worksheet ws;
    HashMap<String, Object> myEquationDetails = new HashMap<>();

    @Before
    public void init() {
        ws = new Worksheet();
        wg = new WorksheetGenerator(ws);
        myEquationDetails.put("numOfEquations", 5);
        myEquationDetails.put("operator", '+');
        myEquationDetails.put("operandRange1", new int[]{1, 10});
        myEquationDetails.put("operandRange2", new int[]{1, 10});
        myEquationDetails.put("negAllowed", true);
    }

    @Test
    public void testWorksheetSize() {
        wg.createWorksheet(myEquationDetails);
        assertEquals(ws.getEquations().size(), 5);
    }

    @Test
    public void testWorksheetHasQuestionAndAnswer() {
        wg.createWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            //Test if each equation has a question and an answer (length of 2 list)
            assertEquals(ws.getEquations().get(i).getEquation().size(), 2);
        }
    }

    @Test
    public void testWorksheetEquationsAddOperator() {
        myEquationDetails.replace("operator", '+');
        wg.createWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            assertNotEquals(ws.getEquations().get(i).getEquation().get(0).indexOf('+'), -1);
        }
    }

    @Test
    public void testWorksheetEquationsSubOperator() {
        myEquationDetails.replace("operator", '-');
        wg.createWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            assertNotEquals(ws.getEquations().get(i).getEquation().get(0).indexOf('-'), -1);
        }
    }

    @Test
    public void testWorksheetEquationsMultiplyOperator() {
        myEquationDetails.replace("operator", '*');
        wg.createWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            assertNotEquals(ws.getEquations().get(i).getEquation().get(0).indexOf('*'), -1);
        }
    }

    @Test
    public void testWorksheetEquationsDivOperator() {
        myEquationDetails.replace("operator", '/');
        wg.createWorksheet(myEquationDetails);
        for (int i = 0; i < ws.getEquations().size(); i++) {
            assertNotEquals(ws.getEquations().get(i).getEquation().get(0).indexOf('/'), -1);
        }
    }
}