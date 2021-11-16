package worksheet_maker;

import equation_parameters.WholeNumEquationDetails;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class WorksheetControllerTest {
    @Test
    public void WorksheetControllerVisualTest() throws IOException {
        WholeNumEquationDetails wholeNumEquationDetails = new WholeNumEquationDetails();
        wholeNumEquationDetails.setNumOfEquations(97);
        wholeNumEquationDetails.setOperator('/');
        wholeNumEquationDetails.setOperandRange1(new int[]{1, 100});
        wholeNumEquationDetails.setOperandRange2(new int[]{5, 9});
        wholeNumEquationDetails.setNegAllowed(true);

        HashMap<String, Object> myFormatDetails = new HashMap<>();
        myFormatDetails.put("equationFormat", "Horizontal");
        myFormatDetails.put("title", "Test Worksheet");
        myFormatDetails.put("numRows", 2);
        myFormatDetails.put("numColumns", 2);
        WorksheetController wc = new WorksheetController();
        PDDocument[] pdf = wc.generateWorksheetAndPDF(wholeNumEquationDetails, myFormatDetails, new Random().nextInt(100000));
        String path = "out/production/course-project-matrix-1/user_package/users_data/";
        pdf[0].save(path + "/questions.pdf");
        pdf[0].close();
        pdf[1].save(path + "/answers.pdf");
        pdf[1].close();
        assertEquals(2, pdf.length);
    }


}