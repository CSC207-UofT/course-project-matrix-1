package worksheet_maker;

import org.apache.pdfbox.pdmodel.PDDocument;
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
        PDDocument[] pdf = wc.generateWorksheetAndPDF(myEquationDetails, myFormatDetails);
        pdf[0].save("C:/Users/willj/Downloads/questions.pdf");
        pdf[0].close();
        pdf[1].save("C:/Users/willj/Downloads/answers.pdf");
        pdf[1].close();
    }


}