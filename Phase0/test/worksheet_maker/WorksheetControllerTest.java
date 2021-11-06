package worksheet_maker;

import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class WorksheetControllerTest {
    //Temporary test case, just want to visually inspect if the equations look random enough.
    @Test
    public void WorksheetControllerVisualTest() throws IOException {
        HashMap<String, Object> myEquationDetails = new HashMap<>();
        myEquationDetails.put("numOfEquations", 100);
        myEquationDetails.put("operator", '*');
        myEquationDetails.put("operandRange1", new int[]{1, 9});
        myEquationDetails.put("operandRange2", new int[]{1, 9});
        myEquationDetails.put("negAllowed", true);

        HashMap<String, Object> myFormatDetails = new HashMap<>();
        myFormatDetails.put("equationFormat", "Horizontal");
        myFormatDetails.put("title", "pls work");
        myFormatDetails.put("numRows", 10);
        myFormatDetails.put("numColumns", 3);
        WorksheetController wc = new WorksheetController();
        wc.generateWorksheetAndPDF(myEquationDetails, myFormatDetails);
//        System.out.println(Arrays.deepToString(wc.generateWorksheetAndPDF(myEquationDetails, myFormatDetails)));
    }


}