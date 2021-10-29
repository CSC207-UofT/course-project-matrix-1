package worksheet_maker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

public class WorksheetGeneratorTest {
    WorksheetGenerator wg;
    Worksheet ws;

    @Before
    public void init() {
        ws = new Worksheet();
        wg = new WorksheetGenerator(ws);
        HashMap<String, Object> myEquationDetails = new HashMap<>();
        myEquationDetails.put("numOfEquations", 5);
        myEquationDetails.put("operator", "+");
        myEquationDetails.put("operandRange1", new int[]{1, 10});
        myEquationDetails.put("operandRange2", new int[]{1, 10});
        myEquationDetails.put("negAllowed", true);
        wg.createWorksheet(myEquationDetails);
    }

    @Test
    public void testWorksheetUpdated() {
        assertNotNull(ws);
    }
    @Test
    public void testWorksheetNumEquations() {
        //assertEquals(ws.equations.length, );
    }
}